package vcs.example.wafdeser;

/* loaded from: waf-deser-0.0.1-SNAPSHOT.jar:BOOT-INF/classes/vcs/example/wafdeser/User.class */
public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }
}