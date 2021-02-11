package kafka.utils;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Optional;
import java.util.Properties;

public class PropertyHelper {

    private static Optional<Properties> maybeConfig = Optional.empty();


    public static Properties getConfig() {
        return getConfig("java-kafka-app"); // java.util.UUID.randomUUID().toString()
    }

    public static Properties getConfig(String appName) {
        if (!maybeConfig.isPresent()) {
            maybeConfig = Optional.of(initConfig(appName));
        }
        return maybeConfig.get();
    }

    private static Properties initConfig(String appName) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, appName);
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "sandbox-hdp.hortonworks.com:6667");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass().getName());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass().getName());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        return config;
    }
}
