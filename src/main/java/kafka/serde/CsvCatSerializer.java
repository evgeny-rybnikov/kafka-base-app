package kafka.serde;

import kafka.model.Cat;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CsvCatSerializer implements Serializer<Cat> {


    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, Cat cat) {
        return cat.toString().getBytes();
    }

    @Override
    public void close() {

    }
}
