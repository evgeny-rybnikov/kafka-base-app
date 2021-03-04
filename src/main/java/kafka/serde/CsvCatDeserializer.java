package kafka.serde;

import kafka.model.Cat;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CsvCatDeserializer implements Deserializer<Cat> {


    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Cat deserialize(String s, byte[] bytes) {
        String catAsString = new String(bytes);
        String[] parts = catAsString.split(",");
        String name = parts[0];
        Integer age = Integer.parseInt(parts[1]);
        return new Cat(name, age);
    }


    @Override
    public void close() {

    }
}
