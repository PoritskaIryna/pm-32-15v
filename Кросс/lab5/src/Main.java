import java.io.Serializable;

abstract class Person implements Serializable {
    protected String name;
    protected int id;
    protected double hp;

    public Person(String name, int id, double hp) {
        this.name = name;
        this.id = id;
        this.hp = hp;
    }

    public abstract double calculateImpact();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}

class Warrior extends Person {
    private double power;

    public Warrior(String name, int id,double hp, double power) {
        super(name, id, hp);
        this.power = power;
    }

    @Override
    public double calculateImpact() {
        return power;
    }
}

class Molfar extends Person {
    private double magic;

    public Molfar(String name, int id, double hp, double magic) {
        super(name, id, hp);
        this.magic = magic;
    }

    @Override
    public double calculateImpact() {
        return hp * magic;
    }
}

class Chorakternyk extends Person {
    private double iq;

    public Chorakternyk(String name, int id,double hp, double iq) {
        super(name, id, hp);
        this.iq = iq;
    }

    @Override
    public double calculateImpact() {
        return hp*iq;
    }
}
