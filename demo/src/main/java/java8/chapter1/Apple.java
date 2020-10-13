package java8.chapter1;

public class Apple {

    private String color;
    private int weight;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isGreenApple() {
        return "green".equals(color);
    }

    public boolean isHeavyApple() {
        return weight > 150;
    }
}
