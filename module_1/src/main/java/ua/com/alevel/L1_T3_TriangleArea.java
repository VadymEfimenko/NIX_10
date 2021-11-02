package ua.com.alevel;

import java.util.Scanner;

public class L1_T3_TriangleArea {

    public void areaOfTriangle() {

        double abLength;
        double bcLength;
        double acLength;

        double semiPerimeter;
        double triangleArea;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите Аx");
        double Ax = scanner.nextDouble();

        System.out.println("Введите Аy");
        double Ay = scanner.nextDouble();

        System.out.println("Введите Bx");
        double Bx = scanner.nextDouble();

        System.out.println("Введите By");
        double By = scanner.nextDouble();

        System.out.println("Введите Cx");
        double Cx = scanner.nextDouble();

        System.out.println("Введите Cy");
        double Cy = scanner.nextDouble();

        if (isOnLine(Ax, Ay, Bx, By, Cx, Cy)) {
            System.out.println("Точки на одной прямой, треугольник невозможен!");
        } else {

            abLength = vectorLength(Ax, Ay, Bx, By);
            bcLength = vectorLength(Bx, By, Cx, Cy);
            acLength = vectorLength(Ax, Ay, Cx, Cy);

            semiPerimeter = (abLength + bcLength + acLength) / 2;
            triangleArea = Math.sqrt(semiPerimeter * (semiPerimeter - abLength) *
                    (semiPerimeter - bcLength) *
                    (semiPerimeter - acLength));
            System.out.println("Площадь вашего треугольника");
            System.out.printf( "%.4f", triangleArea);
            System.out.println();
        }
    }

    private double vectorLength(double pointFirstX, double pointFirstY,
                                double pointSecX, double pointSecY) {
        double xCoord = Math.abs(pointFirstX - pointSecX);
        double yCoord = Math.abs(pointFirstY - pointSecY);

        double length = Math.sqrt((xCoord * xCoord) + (yCoord * yCoord));
        return length;
    }

    private boolean isOnLine(double Ax, double Ay,
                             double Bx, double By,
                             double Cx, double Cy) {
        boolean onLine = (Cx * (By - Ay) - Cy * (Bx - Ax) == Ax * By - Bx * Ay);
        return onLine;
    }
}

class TriangleAreaRun {
    L1_T3_TriangleArea triangleArea = new L1_T3_TriangleArea();
    Scanner scanner = new Scanner(System.in);

    public void triangleAreaRun() {
        try {
            int selector = 1;
            while (selector != 0) {
                triangleArea.areaOfTriangle();

                System.out.println("Решить задачу еще раз?\n" +
                        "1 - ДА\n" +
                        "0 - Вернуться в меню");
                selector = scanner.nextInt();
            }
        } catch (Exception e) {
            int selector = 1;
            System.out.println("Введены некоректные данные\n" +
                    "нажмите 1 чтобы вернуться в меню и запустите задачу снова");
            selector = scanner.nextInt();
        }
    }
}
