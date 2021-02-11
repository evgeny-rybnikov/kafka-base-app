package kafka.app;

import kafka.utils.PropertyHelper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class ConsumerDemo {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerDemo.class);

    public static void main(String[] args) {
        String topic = args[0];

        LOG.debug("Starting ConsumerDemo app, topic name:" + topic);

        Properties config = PropertyHelper.getConfig();
        config.put("group.id", UUID.randomUUID().toString());
        config.put("enable.auto.commit", "true");
        config.put("auto.commit.interval.ms", "1000");
        config.put("session.timeout.ms", "10000");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records) {
                LOG.debug("retrieving records..");
                LOG.debug("offset={} key={}, value={}", record.offset(), record.key(), record.value());
            }
        }
    }

}
