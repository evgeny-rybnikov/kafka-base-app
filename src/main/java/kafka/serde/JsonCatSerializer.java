package kafka.serde;

import kafka.dto.Cat;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonCatSerializer implements Serializer<Cat> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Cat cat) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
