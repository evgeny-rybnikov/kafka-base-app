package kafka.dto;

public class Cat {

    private String name;
    private Integer age;

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "cat:" + name + ",age:" + age;
    }
}
