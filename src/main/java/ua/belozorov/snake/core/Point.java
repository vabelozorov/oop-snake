package ua.belozorov.snake.core;

public record Point(int x, int y) {

    public static Point xy(int x, int y) {
        return new Point(x, y);
    }

    public Point withXDecrement() {
        return new Point(x - 1, y);
    }

    public Point withYDecrement() {
        return new Point(x, y - 1);
    }

    public Point withXIncrement() {
        return new Point(x + 1, y);
    }

    public Point withYIncrement() {
        return new Point(x, y + 1);
    }

    public Point withPlusXY(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

}
