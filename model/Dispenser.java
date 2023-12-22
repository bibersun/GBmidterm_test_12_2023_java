package model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dispenser {
    private final Integer idD;
    private final String descr;
    private final Integer capacity;
    public Queue<Prize> queue;

    public Integer getIdD() {
        return idD;
    }

    public String getDescr() {
        return descr;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Queue getQueue() {
        return queue;
    }


    public Dispenser(Integer idD, String descr, Integer capacity) {
        this.idD = idD;
        this.descr = descr;
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    @Override
    public String toString() {
        LinkedList<Prize> prizes = (LinkedList<Prize>) queue;
        Map<String, Long> fr = prizes.stream().map(Prize::getDescr).collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
        if (fr.isEmpty()){
            return "Аппарат " + descr + " с id=" + this.getIdD() + " пуст, будет автоматически заполнен при взятии";
        }
        return "Содержимое аппарата № " + this.getIdD() + " (" + descr + ")\n" + fr;
    }

}
