package collections;

import java.lang.reflect.Array;
import java.text.Collator;
import java.util.Arrays;

import org.junit.Test;

public class ArrayUtils {
    public static void main(String[] args) {

    }

    public static boolean isSameType(Object array1, Object array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        return array1.getClass().getName().equals(array2.getClass().getName());
    }

    
    public static boolean isArray(final Object obj) {
        if (obj != null)
            return obj.getClass().isArray();
        return false;
    }

    @Test
    public void createNewArray() {
        int array[] = (int[]) Array.newInstance(int.class, 5);
        for (int i : array) {
            System.out.println(i);
        }
    }

    @Test
    public void dumpContent() {
        String s[] = { "a", "b", "c", "d" };
        double d[][] = { { 0.50, 0.20, 0.20, 0.30 }, { 0.50, 1.10, 0.50, 0.80 }, { 0.50, 0.70, 0.40 }, { 0.50, 0.70 },
                { 0.50 }, };
        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.deepToString(d));
        /**
         * 
            [a, b, c, d]
            [[0.5, 0.2, 0.2, 0.3], [0.5, 1.1, 0.5, 0.8], [0.5, 0.7, 0.4], [0.5, 0.7], [0.5]]

         * 
         */
    }

    @Test
    public void sortcase_insensitive() {
        String[] myArray = new String[] { "A", "B", "b" };
        Arrays.sort(myArray, Collator.getInstance());

        System.out.println(Arrays.toString(myArray));
    }

    @Test
    public void createNewArrayDimensions() {
        int dimensions[] = { 3, 4 };
        int array[][] = (int[][]) Array.newInstance(int.class, dimensions);

        for (int[] inner : array) {
            for (int i : inner) {
                System.out.println(i);
            }
        }
    }

    /**
     * equals，但是不相等==
     */
    @Test
    public void checkEqualtity() {
        int[] i = new int[] { 1, 2, 3 };
        int[] j = new int[] { 1, 2, 3 };
        int[] k = { 1, 2, 3 };
        int[] m = { 1, 2, 3 };
        System.out.println(Arrays.equals(i, j));
        System.out.println(Arrays.equals(k, j));
        System.out.println(Arrays.equals(k, m));

    }

    @Test
    public void checkTypeAndLength() {
        int[] object = { 1, 2, 3 };
        Class type = object.getClass();
        if (type.isArray()) {
            Class elementType = type.getComponentType();
            System.out.println("Array of: " + elementType);
            System.out.println("Length: " + Array.getLength(object));
        }
    }

    @Test
    public void fill() {
        Object array = Array.newInstance(int.class, 3);

        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            int value = i;
            Array.setInt(array, i, value);
        }

        for (int i : (int[]) array) {
            System.out.println(i);

        }
    }

    @Test
    public void fill2() {
        char[] newPassword1 = new char[10];
        Arrays.fill(newPassword1, ' ');
    }

    public Object doubleArray(Object original) {
        Object returnValue = null;
        Class type = original.getClass();
        if (type.isArray()) {
            int length = Array.getLength(original);
            Class elementType = type.getComponentType();
            returnValue = Array.newInstance(elementType, length * 2);
            System.arraycopy(original, 0, returnValue, 0, length);
        }
        return returnValue;
    }

    @Test
    public void sort() {
        int array[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
        Arrays.sort(array, 3, 7);
        for (int i : array) {
            System.out.println(i);
        }
    }

}
