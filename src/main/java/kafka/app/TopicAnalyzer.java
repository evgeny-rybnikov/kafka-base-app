package kafka.app;

//import kafka.dto.Cat;
//import kafka.serde.JsonCatDeserializer;
//import kafka.serde.JsonCatSerializer;
//import kafka.utils.PropertyHelper;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.Consumed;
//import org.apache.kafka.streams.kstream.Produced;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.KStream;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Properties;
//
//
//public class TopicAnalyzer {
//
//    private static final Logger LOG = LoggerFactory.getLogger(TopicAnalyzer.class);
//
//    public static void main(String[] args) {
//        LOG.info("Hello");
//
//        Serde<Cat> catSerde = Serdes.serdeFrom(new JsonCatSerializer(), new JsonCatDeserializer());
//
//        Properties config = PropertyHelper.getConfig();
//        StreamsBuilder builder = new StreamsBuilder();
//
//        KStream<String, Cat> cats = builder.stream("cats-topic", Consumed.with(Serdes.String(), catSerde));
//
//        cats.to("out-topic", Produced.with(Serdes.String(), catSerde));
//    }
//}

import kafka.utils.PropertyHelper;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Usage example:
 * mvn clean test assembly:single
 * docker cp target/kafkastreamsapp-1.0-SNAPSHOT-jar-with-dependencies.jar sandbox-hdp:/home/kafka-app
 * java -cp kafkastreamsapp-1.0-SNAPSHOT-jar-with-dependencies.jar kafka.analyze.TopicAnalyzer hotels-data 5
 */
public class TopicAnalyzer {

    public static final Logger LOG = LoggerFactory.getLogger(TopicAnalyzer.class);


    public static void main(String[] args) {
        String topic = args[0];
        int limit = Integer.parseInt(args[1]);

        LOG.info("STARTED Analyzer for topic={}, first {} messages will be picked up", topic, limit);

        final StreamsBuilder builder = new StreamsBuilder();
        final KStream<byte[], byte[]> kstream = builder.stream(topic);

        AtomicInteger logged = new AtomicInteger(0);

        kstream.map((key, value) -> {
            if (logged.incrementAndGet() < limit) analyzeAndReport(key, value);
            return KeyValue.pair(key, value);
        });

        //kstream.to("SOME_OTHER_TOPIC");
        proceedStreams(builder);
    }

    private static void proceedStreams(StreamsBuilder builder) {
        String appName = UUID.randomUUID().toString();
        Properties config = PropertyHelper.getConfig(appName);
        final KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.cleanUp();
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private static void analyzeAndReport(byte[] key, byte[] value) {
        String keyStr;
        if (key == null) keyStr = "NULL-KEY";
        else if (key.length == 0) keyStr = "EMPTY-KEY";
        else keyStr = new String(key);

        String valueStr;
        if (value == null) valueStr = "NULL-VALUE";
        else if (value.length == 0) valueStr = "EMPTY-VALUE";
        else valueStr = new String(value);

        LOG.debug("key={} value={}", keyStr, valueStr);
    }
}