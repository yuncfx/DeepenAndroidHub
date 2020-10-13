package collections;

import java.util.BitSet;

import org.junit.Test;

/**
 * A BitSet is a dynamically growing array containing bits.
 * 
 * This dynamically growing structure has two values describing its internal
 * dimensions: a size and a length.
 * 
 */
public class TestBitSet {

    BitSet sked;

    public TestBitSet() {
        sked = new BitSet(365);
        int[] holiday = { 1, 15, 50, 148, 185, 246, 281, 316, 326, 359 };
        for (int i = 0; i < holiday.length; i++) {
            addHoliday(holiday[i]);
        }
    }

    public void addHoliday(int dayToAdd) {
        sked.set(dayToAdd);
    }

    public boolean isHoliday(int dayToCheck) {
        boolean result = sked.get(dayToCheck);
        return result;
    }

    public static void main(String[] arguments) {
        TestBitSet cal = new TestBitSet();
        if (arguments.length > 0) {
            try {
                int whichDay = Integer.parseInt(arguments[0]);
                if (cal.isHoliday(whichDay)) {
                    System.out.println("Day number " + whichDay + " is a holiday.");
                } else {
                    System.out.println("Day number " + whichDay + " is not a holiday.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error: " + nfe.getMessage());
            }
        }
    }

    /**
     * public void and(BitSet set) public void or(BitSet set) public void
     * xor(BitSet set) public void andNot(BitSet set)
     */
    @Test
    public void test1() {
        BitSet bites = new BitSet();
        bites.set(0);
        bites.set(1);
        bites.set(2);
        bites.set(3);

        System.out.println(bites.get(3)); // true
        System.out.println(bites.get(10)); // false

        System.out.println(bites); // {0, 1, 2, 3}
        System.out.println(bites.size()); // 64
        System.out.println(bites.length());// 4

        BitSet bitSetClone = (BitSet) bites.clone();
        System.out.println(bitSetClone); // {0, 1, 2, 3}

        bites.and(bites);
        System.out.println(bites); // {0, 1, 2, 3}

        bites.or(bites);
        System.out.println(bites); // {0, 1, 2, 3}

        bites.xor(bites);
        System.out.println(bites); // {}

        bites.andNot(bites);
        System.out.println(bites); // {}
    }
}
