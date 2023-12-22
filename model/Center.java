package model;

import java.util.ArrayList;

public class Center {
    private static Center center;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Center() {

    }

    public static synchronized Center getCenter() {
        if (center == null) {
            center = new Center();
        }
        return center;
    }

    ArrayList<Prize> cat = new ArrayList<>();

    public ArrayList<Prize> getCat() {
        return cat;
    }

    public void addToCatalog(Prize prize) {
        cat.add(prize);
    }

}
