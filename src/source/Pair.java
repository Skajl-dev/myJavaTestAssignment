package source;

public class Pair {
    protected int x;
    protected int y;
    private static int maxValue = 0; // визначаємо максимальне значення координат для побудови підходящої координатної сітки
    private static int maxY = 0;
    private static int minY = 214748364;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
        if (x > maxValue) {
            maxValue = x;
        }
        if (y > maxValue) {
            maxValue = y;
        }
        if (y > maxY)
            maxY=y;
        if (y < minY)
            minY = y;
    }

    public static int getMaxValue() {
        return maxValue;
    }

    public static int getMinY() {
        return minY;
    }

    public static int getMaxY() {
        return maxY;
    }

}
