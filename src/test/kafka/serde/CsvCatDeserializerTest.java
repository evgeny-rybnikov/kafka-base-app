package kafka.serde;

import kafka.model.Cat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvCatDeserializerTest {

    private Cat cat = new Cat("cat_name", 11);

    @Test
    void deserilze() {
        CsvCatDeserializer deser = new CsvCatDeserializer();
        byte[] bytes = "cat_name,11".getBytes();
        Cat newCat = deser.deserialize("ignored", bytes);
        assertTrue(cat.equals(newCat));
    }
}