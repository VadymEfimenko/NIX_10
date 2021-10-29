package ua.com.alevel.level3;

public class Point {

    private int x, y;

    public Point(int a, int b) {
        x = a;
        y = b;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
