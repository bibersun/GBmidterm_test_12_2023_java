package view;

import mediator.Mediator;
import model.Dispenser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;


public class View {
    Mediator mediator;

    public View(Mediator mediator) {
        this.mediator = mediator;
    }

    public void statusDispenser(Dispenser dispenser) {
        System.out.println("Проверка статуса аппарата " + dispenser.getDescr());
        if (mediator.checkEmpty(dispenser)) {
            System.out.println("Аппарат пуст, будет автоматически заполнен при следующем взятии приза");
        } else {
            System.out.println(dispenser);
        }
    }

    public void statusDispenserById(Integer integer) {
        try {
            System.out.println();
            statusDispenser(mediator.getDispenserById(integer));
        } catch (NoSuchElementException e){
            System.out.println("Нет такого аппарата");
        }
        System.out.println();
    }

    public void statusDispensers() {
        System.out.println();
        System.out.println("Проверка статуса всех доступных аппаратов");
        System.out.println("******************************");
        for (Dispenser dispenser : mediator.getPermission()) {
            statusDispenser(dispenser);
            System.out.println("******************************");
        }
        System.out.println();
    }

    public void catalogue(){
        System.out.println();
        mediator.printCatalog();
        System.out.println();
    }

    public void get(Dispenser dispenser){
        String tmp = mediator.get(dispenser);
        System.out.println("Аппарат " + dispenser.getDescr() + ". " + tmp);
        try(FileWriter file = new FileWriter("prizes.txt", true ))
        {
            file.write("Аппарат " + dispenser.getDescr() + ". " + tmp + "\n");

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getById(int ids){
        System.out.println();
        try {
            get(mediator.getDispenserById(ids));
        } catch (NoSuchElementException e){
            System.out.println("Нет такого аппарата");
        }
        System.out.println();
    }

    public void clearFile() {
        System.out.println();
        try (FileWriter file = new FileWriter("prizes.txt", false)) {
            System.out.println("Файл prizes.txt очищен");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }



}

