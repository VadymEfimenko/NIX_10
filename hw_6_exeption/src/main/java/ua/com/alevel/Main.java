package ua.com.alevel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для демонстрации работы MyCalendar введите 3 даты в следующих форматах:\n " +
                "\n1/10/34 - система преобразует в 1 октября 34 года 0 часов 0 минут 0 секунд 0 миллисекунд;\n" +
                " /5/47 00:24:00:000 - система преобразует в 1 мая 47 года 0 часов 24 минуты 0 секунд 0\n" +
                "миллисекунд;\n" +
                " /2/ :2 - система преобразует в 1 февраля 0 года 2 часа 0 минут 0 секунд 0 миллисекунд;\n" +
                " 1256 14:59 - система преобразует в 1 января 1256 года 14 часов 59 минут 0 секунд 0\n" +
                "миллисекунд");

        MyCalendar myCalendar1 = null;
        MyCalendar myCalendar2 = null;
        MyCalendar myCalendar3 = null;
        try {
            myCalendar1 = new MyCalendar(scanner.nextLine());
            myCalendar2 = new MyCalendar(scanner.nextLine());
            myCalendar3 = new MyCalendar(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Введены некорректные даты, создадим три даты со следующими значениями:");
            myCalendar1 = new MyCalendar("1/11/34");
            myCalendar2 = new MyCalendar("1/5/1010");
            myCalendar3 = new MyCalendar("1/2/2021");
            myCalendar1.printDDMMYY();
            myCalendar2.printDDMMYY();
            myCalendar3.printDDMMYY();
        }
        System.out.println("---------------------------------------ГЛАВНОЕ МЕНЮ--------------------------------------------");
        System.out.println("Найти разницу в между датами в миллисекундах, секундах, минутах, часах, днях и годах: нажмите 1");
        System.out.println("прибавлять к дате миллисекунды, секунды, минуты, часы, дни и года: нажмите 2");
        System.out.println("вычитать из даты миллисекунды, секунды, минуты, часы, дни и года: нажмите 3");
        System.out.println("вывести даты по убыванию: нажмите 4");
        System.out.println("вывести даты по возрастанию: нажмите 5");
        System.out.println("ВЫЙТИ -> 0");
        int choice = scanner.nextInt();
        System.out.println("Введите формат вывода: \n" +
                "dd/mm/yy: нажмите 1\n" +
                "m/d/yyyy: нажмите 2\n" +
                "mmm-d-yy: нажмите 3\n" +
                "dd-mmm-yyyy 00:00: нажмите 4\n");
        int format = scanner.nextInt();
        while (choice != 0)
            switch (choice) {
                case 1 -> {
                    System.out.println("Разница между датой 1 и датой 2");
                    myCalendar1.difference(myCalendar2);
                    System.out.println("Разница между датой 1 и датой 3");
                    myCalendar1.difference(myCalendar3);
                    System.out.println("Разница между датой 2 и датой 3");
                    myCalendar2.difference(myCalendar3);
                    System.out.println("Выберите действие из ГЛАВНОГО МЕНЮ еще раз");
                    choice = scanner.nextInt();
                }
                case 2 -> {
                    System.out.println("Прибавить миллисекунды: 1");
                    System.out.println("Прибавить секунды: 2");
                    System.out.println("Прибавить минуты: 3");
                    System.out.println("Прибавить часы: 4");
                    System.out.println("Прибавить дни: 5");
                    System.out.println("Прибавить месяцы: 6");
                    System.out.println("Прибавить годы: 7");
                    int addChoice = scanner.nextInt();
                    System.out.println("Введите количество: ");
                    int amount = scanner.nextInt();
                    if (addChoice == 1) {
                        System.out.println("Прибавить к первой дате введенное количество миллисекунд:");
                        System.out.println("Прибавить ко второй дате введенное количество миллисекунд:");
                        System.out.println("Прибавить к третьей дате введенное количество миллисекунд:");
                        myCalendar1.addMilliseconds(amount);
                        myCalendar2.addMilliseconds(amount);
                        myCalendar3.addMilliseconds(amount);
                    } else if (addChoice == 2) {
                        System.out.println("Прибавить к первой дате введенное количество секунд:");
                        myCalendar1.addSeconds(amount);
                        System.out.println("Прибавить ко второй дате введенное количество секунд:");
                        myCalendar2.addSeconds(amount);
                        System.out.println("Прибавить к третьей дате введенное количество секунд:");
                    } else if (addChoice == 3) {
                        System.out.println("Прибавить к первой дате введенное количество минут:");
                        myCalendar1.addMinutes(amount);
                        System.out.println("Прибавить ко второй дате введенное количество минут:");
                        myCalendar2.addMinutes(amount);
                        System.out.println("Прибавить к третьей дате введенное количество минут:");
                        myCalendar3.addMinutes(amount);
                    } else if (addChoice == 4) {
                        System.out.println("Прибавить к первой дате введенное количество часов:");
                        myCalendar1.addHours(amount);
                        System.out.println("Прибавить ко второй дате введенное количество часов:");
                        myCalendar2.addHours(amount);
                        System.out.println("Прибавить к третьей дате введенное количество часов:");
                        myCalendar3.addHours(amount);
                    } else if (addChoice == 5) {
                        System.out.println("Прибавить к первой дате введенное количество дней:");
                        myCalendar1.addDays(amount);
                        System.out.println("Прибавить ко второй дате введенное количество дней:");
                        myCalendar2.addDays(amount);
                        System.out.println("Прибавить к третьей дате введенное количество дней:");
                        myCalendar3.addDays(amount);
                    } else if (addChoice == 6) {
                        System.out.println("Прибавить к первой дате введенное количество месяцев:");
                        myCalendar1.addMonths(amount);
                        System.out.println("Прибавить ко второй дате введенное количество месяцев:");
                        myCalendar2.addMonths(amount);
                        System.out.println("Прибавить к третьей дате введенное количество месяцев:");
                        myCalendar3.addMonths(amount);
                    } else if (addChoice == 7) {
                        System.out.println("Прибавить к первой дате введенное количество лет:");
                        myCalendar1.addYears(amount);
                        System.out.println("Прибавить ко второй дате введенное количество лет:");
                        myCalendar2.addYears(amount);
                        System.out.println("Прибавить к третьей дате введенное количество лет:");
                        myCalendar3.addYears(amount);
                    }
                    if (format == 1) {
                        myCalendar1.printDDMMYY();
                        myCalendar2.printDDMMYY();
                        myCalendar3.printDDMMYY();
                    } else if (format == 2) {
                        myCalendar1.printMDYYYY();
                        myCalendar2.printMDYYYY();
                        myCalendar3.printMDYYYY();
                    } else if (format == 3) {
                        myCalendar1.printMMMDYY();
                        myCalendar2.printMMMDYY();
                        myCalendar3.printMMMDYY();
                    } else if (format == 4) {
                        myCalendar1.printDDMMMYYYYTime();
                        myCalendar2.printDDMMMYYYYTime();
                        myCalendar3.printDDMMMYYYYTime();
                    }
                    System.out.println("Выберите действие из ГЛАВНОГО МЕНЮ еще раз");
                    choice = scanner.nextInt();
                }
                case 3 -> {
                    System.out.println("Вычесть миллисекунды: 1");
                    System.out.println("Вычесть секунды: 2");
                    System.out.println("Вычесть минуты: 3");
                    System.out.println("Вычесть часы: 4");
                    System.out.println("Вычесть дни: 5");
                    System.out.println("Вычесть месяцы: 6");
                    System.out.println("Вычесть годы: 7");
                    int removeChoice = scanner.nextInt();
                    System.out.println("Введите количество: ");
                    int amount = scanner.nextInt();
                    if (removeChoice == 1) {
                        System.out.println("Вычесть из первой даты введенное количество миллисекунд:");
                        myCalendar1.removeMilliseconds(amount);
                        System.out.println("Вычесть из первой даты введенное количество миллисекунд:");
                        myCalendar2.removeMilliseconds(amount);
                        System.out.println("Вычесть из первой даты введенное количество миллисекунд:");
                        myCalendar3.removeMilliseconds(amount);
                    } else if (removeChoice == 2) {
                        System.out.println("Вычесть из первой даты введенное количество секунд:");
                        myCalendar1.removeSeconds(amount);
                        System.out.println("Вычесть из первой даты введенное количество секунд:");
                        myCalendar2.removeSeconds(amount);
                        System.out.println("Вычесть из первой даты введенное количество секунд:");
                        myCalendar3.removeSeconds(amount);
                    } else if (removeChoice == 3) {
                        System.out.println("Вычесть из первой даты введенное количество минут:");
                        myCalendar1.removeMinutes(amount);
                        System.out.println("Вычесть из первой даты введенное количество минут:");
                        myCalendar2.removeMinutes(amount);
                        System.out.println("Вычесть из первой даты введенное количество минут:");
                        myCalendar3.removeMinutes(amount);
                    } else if (removeChoice == 4) {
                        System.out.println("Вычесть из первой даты введенное количество часов:");
                        myCalendar1.removeHours(amount);
                        System.out.println("Вычесть из первой даты введенное количество часов:");
                        myCalendar2.removeHours(amount);
                        System.out.println("Вычесть из первой даты введенное количество часов:");
                        myCalendar3.removeHours(amount);
                    } else if (removeChoice == 5) {
                        System.out.println("Вычесть из первой даты введенное количество дней:");
                        myCalendar1.removeDays(amount);
                        System.out.println("Вычесть из первой даты введенное количество дней:");
                        myCalendar2.removeDays(amount);
                        System.out.println("Вычесть из первой даты введенное количество дней:");
                        myCalendar3.removeDays(amount);
                    } else if (removeChoice == 6) {
                        System.out.println("Вычесть из первой даты введенное количество месяцев:");
                        myCalendar1.removeMonth(amount);
                        System.out.println("Вычесть из первой даты введенное количество месяцев:");
                        myCalendar2.removeMonth(amount);
                        System.out.println("Вычесть из первой даты введенное количество месяцев:");
                        myCalendar3.removeMonth(amount);
                    } else if (removeChoice == 7) {
                        System.out.println("Вычесть из первой даты введенное количество лет:");
                        myCalendar1.removeYear(amount);
                        System.out.println("Вычесть из первой даты введенное количество лет:");
                        myCalendar2.removeYear(amount);
                        System.out.println("Вычесть из первой даты введенное количество лет:");
                        myCalendar3.removeYear(amount);
                    }
                    if (format == 1) {
                        myCalendar1.printDDMMYY();
                        myCalendar2.printDDMMYY();
                        myCalendar3.printDDMMYY();
                    } else if (format == 2) {
                        myCalendar1.printMDYYYY();
                        myCalendar2.printMDYYYY();
                        myCalendar3.printMDYYYY();
                    } else if (format == 3) {
                        myCalendar1.printMMMDYY();
                        myCalendar2.printMMMDYY();
                        myCalendar3.printMMMDYY();
                    } else if (format == 4) {
                        myCalendar1.printDDMMMYYYYTime();
                        myCalendar2.printDDMMMYYYYTime();
                        myCalendar3.printDDMMMYYYYTime();
                    }
                    System.out.println("Выберите действие из ГЛАВНОГО МЕНЮ еще раз");
                    choice = scanner.nextInt();
                }
                case 4 -> {
                    MyCalendar[] descSort = MyCalendar.sortDesc(myCalendar1, myCalendar2, myCalendar3);
                    for (int i = 0; i < descSort.length; i++) {
                        if (format == 1) {
                            descSort[i].printDDMMYY();
                        } else if (format == 2) {
                            descSort[i].printMDYYYY();
                        } else if (format == 3) {
                            descSort[i].printMMMDYY();
                        } else if (format == 4) {
                            descSort[i].printDDMMMYYYYTime();
                        }
                    }
                    System.out.println("Выберите действие из ГЛАВНОГО МЕНЮ еще раз");
                    choice = scanner.nextInt();
                }
                case 5 -> {
                    MyCalendar[] sortAsc = MyCalendar.sortAsc(myCalendar1, myCalendar2, myCalendar3);
                    for (int i = 0; i < sortAsc.length; i++) {
                        if (format == 1) {
                            sortAsc[i].printDDMMYY();
                        } else if (format == 2) {
                            sortAsc[i].printMDYYYY();
                        } else if (format == 3) {
                            sortAsc[i].printMMMDYY();
                        } else if (format == 4) {
                            sortAsc[i].printDDMMMYYYYTime();
                        }
                    }
                    System.out.println("Выберите действие из ГЛАВНОГО МЕНЮ еще раз");
                    choice = scanner.nextInt();
                }
            }
    }
}