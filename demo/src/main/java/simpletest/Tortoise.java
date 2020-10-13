package simpletest;

import org.junit.Test;

public class Tortoise {
    public static int[] race(int v1, int v2, int g) {

        if (v1 > v2) {
            return null;
        }

        double time = 1.0 * g / (v2 - v1);
        int hours = (int) time;
        double _temp = (time - hours) * 3600;
        int totalSeconds = getSuitable(_temp);

        int seconds = (int) (totalSeconds % 60);
        int minutes = (int) (((totalSeconds - seconds) / 60) % 60);

        return new int[] { hours, minutes, seconds };
    }

    private static int getSuitable(double _temp) {

        double _round = Math.round(_temp);
        if (_temp < _round && (_round - _temp < 0.0001)) {
            return (int) _round;
        } else {
            return (int) _temp;
        }
    }

    @Test
    public void test1() {
        int[] array = Tortoise.race(720, 850, 70);
        System.out.println(array[0] + ", " + array[1] + ", " + array[2]);
    }
}