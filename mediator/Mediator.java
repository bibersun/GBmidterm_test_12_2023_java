package mediator;
import model.Center;
import model.Dispenser;
import model.Prize;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Mediator {
    Center center;
    ArrayList<Dispenser> permission = new ArrayList<>();


    public void setCenter(Center center) {
        this.center = center;
    }

    public Center getCenter() {
        return center;
    }

    public boolean checkPermission(Dispenser dispenser) {
        return permission.contains(dispenser);
    }

    public ArrayList<Dispenser> getPermission() {
        return permission;
    }

    public void setPermission(Dispenser dispenser) {
        permission.add(dispenser);
    }

    public void deletePermission(Dispenser dispenser) {
        permission.remove(dispenser);
    }


    public int calcCountQuantum(Dispenser dispenser) {
        if (getMinQuantum() != 0) {
            return dispenser.getCapacity() / getMinQuantum();
        }
        return 0;
    }

    public void put(LinkedList<Prize> pack, Dispenser dispenser) {
        Collections.shuffle(pack);
        dispenser.queue.addAll(pack);
        System.out.println("Аппарат загружен " + pack.size() + " призами");
    }


    public boolean checkEmpty(@NotNull Dispenser dispenser) {
        return dispenser.getQueue().isEmpty();

    }


    public void printCatalog() {
        System.out.println("Каталог товаров");
        for (Prize prize : center.getCat()) {
            System.out.println(prize);
        }
    }


    public LinkedList<Prize> getMinLinkedListQuantum() {
        LinkedList<Prize> minQuantum = new LinkedList<>();
        for (Prize prize : center.getCat()) {
            for (int i = 0; i < prize.getWeight(); i++) {
                minQuantum.add(new Prize(prize));
            }
        }
        return minQuantum;
    }

    public LinkedList<Prize> getMaxLinkedListQuantum(Dispenser dispenser) {
        LinkedList<Prize> maxQuantum = new LinkedList<>();
        int tmp = calcCountQuantum(dispenser);
        for (int i = 0; i < tmp; i++) {
            maxQuantum.addAll(getMinLinkedListQuantum());

        }
        Collections.shuffle(maxQuantum);
        return maxQuantum;
    }

    public void putGDS(Dispenser dispenser) {
        if (!checkEmpty(dispenser)) {
            System.out.println("Аппарат нельзя загружать, он не пуст");
        } else if (checkPermission(dispenser)) {
            put(getMaxLinkedListQuantum(dispenser), dispenser);
        } else {
            System.out.println("Нельзя загружать, не в списке");
        }
    }

    public Integer getMinQuantum() {
        return center.getCat().stream().map(Prize::getWeight).reduce(0, Integer::sum);

    }

    public String get(Dispenser dispenser) {
        if (checkEmpty(dispenser)) {
            putGDS(dispenser);
        }
        Prize tmp;
        if (!checkEmpty(dispenser)) {
            tmp = dispenser.queue.poll();
        } else {
            return "Аппарат пуст и загрузка невозможна";
        }

        return "Выдан приз " + tmp.getDescr() + " с id " + tmp.getCode();
    }


    public Dispenser getDispenserById(int integer) throws NoSuchElementException{
        List<Dispenser> ttt = getPermission().stream().filter(x -> x.getIdD() == integer).toList();
        if (!ttt.isEmpty()){
        return ttt.get(0);
        } else {
            throw new NoSuchElementException();
        }
    }
}
