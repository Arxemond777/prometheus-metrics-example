package example;

public class APIDTO {
    APIDTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GreetingAPIDTO{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
