package main.java;

class Point<T> {
    private int x, y;
    private T value;

    public Point(int x, int y, T value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public T getValue() {
        return value;
    }
}