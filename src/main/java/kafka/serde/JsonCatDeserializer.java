package kafka.serde;

import kafka.dto.Cat;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;


public class JsonCatDeserializer implements Deserializer<Cat> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Cat deserialize(String s, byte[] bytes) {
        return null;
    }

    @Override
    public void close() {

    }
}
