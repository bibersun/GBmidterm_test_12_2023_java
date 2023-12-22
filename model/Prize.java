package model;

public class Prize {
    private final Integer id;
    private final String descr;
    private final String code;
    private final Integer weight;

    public Integer getId() {
        return id;
    }

    public String getDescr() {
        return descr;
    }

    public String getCode() {
        return code;
    }

    public Integer getWeight() {
        return weight;
    }

    public Prize(Integer id, Integer weight, String descr, String code) {
        this.id = id;
        this.weight = weight;
        this.descr = descr;
        this.code = code;
    }

    public Prize(Prize prize) {
        this.id = prize.getId();
        this.weight = prize.getWeight();
        this.descr = prize.getDescr();
        this.code = prize.getCode();
    }

    @Override
    public String toString() {
        return  descr + ": " +
                "id= " + id +
                ", code= " + code +
                ", weight= " + weight;
    }
}
