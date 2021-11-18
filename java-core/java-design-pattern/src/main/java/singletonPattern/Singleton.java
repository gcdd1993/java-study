package singletonPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public enum Singleton {
    INSTANCE;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

