package ds.hw4;

import java.util.Objects;

public class Rectangle {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(1, 3, 6, 6);
        Rectangle rectangle2 = new Rectangle(1, 3, 6, 6);
        System.out.println(rectangle1.equals(rectangle2));
    }

    private final int xLow, yLow, width, height;
    private Rectangle(int x, int y, int width, int height) {
        this.xLow = x;
        this.yLow = y;
        this.width = width;
        this.height = height;
    }
    private int getWidth() {
        return width;
    }
    private int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return xLow == rectangle.xLow && yLow == rectangle.yLow
                && width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xLow, yLow, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "xLow=" + xLow +
                ", yLow=" + yLow +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
