package kafka.serde;

import kafka.model.Cat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class CsvCatSerializerTest {

    private Cat cat = new Cat("cat_name", 11);


    @Test
    void serialize() {
        CsvCatSerializer ser = new CsvCatSerializer();
        byte[] bytes = ser.serialize("ignored", cat);
        assertArrayEquals("cat_name,11".getBytes(), bytes);
    }

    @Test
    void demo() {

        Cat cat = new Cat("cat_name", 11);
        System.out.println(cat.toString());
    }
}