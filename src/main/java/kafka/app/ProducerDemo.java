package kafka.app;

import kafka.utils.PropertyHelper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;

public class ProducerDemo {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerDemo.class);

    public static void main(String[] args) {
        String topic = args[0];
        LOG.debug("Starting ProducerDemo app, topic name: " + topic);

        Properties config = PropertyHelper.getConfig();
        config.put("acks", "all");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(config);
        for (int i = 0; i < 50; i++) {
            String key = Integer.toString(i);
            String value = UUID.randomUUID().toString();
            LOG.debug("sending message with key=" + key + ", value=" + value);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
            producer.send(record);
        }
        producer.close();
    }
}