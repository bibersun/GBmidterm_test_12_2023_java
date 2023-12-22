import mediator.Mediator;
import model.Center;
import model.Dispenser;
import model.Prize;
import view.View;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Center center = Center.getCenter();
        center.setName("Центр вселенной");
        center.addToCatalog(new Prize(1, 1, "Конструктор", "1"));
        center.addToCatalog(new Prize(3, 3, "Кукла", "3"));
        center.addToCatalog(new Prize(2, 1, "Робот", "2"));
//        center.addToCatalog(new Prize(4, 3, "Барабашка", "4"));
        Dispenser dispenser1 = new Dispenser(1, "Красная площадь", 10);
        Dispenser dispenser2 = new Dispenser(2, "Тверская площадь", 20);
        Mediator mediator = new Mediator();
        mediator.setPermission(dispenser1);
        mediator.setPermission(dispenser2);
//        mediator.deletePermission(dispenser1);
        mediator.setCenter(center);
        View view = new View(mediator);
//******************************
        boolean tmp = true;
        while (tmp){
            System.out.println("Наберите пункт меню и нажмите Enter");
            System.out.println("""
                    1 - вывод состояния всех аппаратов
                    2 - вывод состояния по id
                    3 - взять из аппарата по id
                    4 - показать каталог
                    5 - очистить выходной файл
                    0 - выйти
                    """
            );
            switch (scanner.nextInt()){
                case (0):
                    tmp = false;
                    break;
                case (1):
                    view.statusDispensers();
                    break;
                case (2):
                    System.out.print("Введите id аппарата: ");
                    view.statusDispenserById(scanner.nextInt());
                    break;
                case (3):
                    System.out.print("Введите id аппарата: ");
                    view.getById(scanner.nextInt());
                    break;
                case (4):
                    view.catalogue();
                    break;
                case (5):
                    view.clearFile();
                    break;
            }
        }

    }

}
