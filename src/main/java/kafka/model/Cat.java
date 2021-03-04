package kafka.model;

public class Cat {

    private String name;
    private Integer age;

    public Cat() {
        this(null, null);
    }

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return name + "," + age;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        Cat cat = (Cat) obj;
        return (this.age.equals(cat.age) && this.name.equals(cat.name));
    }
}
