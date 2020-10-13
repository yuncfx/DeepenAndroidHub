package collections;

import java.lang.reflect.Array;

public class Main {
    public static void main(String args[]) {
        // String[][] data = new String[3][4];
        // System.out.println(getDimension(data));
        long[] a = add(null, 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static int getDimension(Object array) {
        int dim = 0;
        Class c = array.getClass();
        while (c != null && c.isArray()) {
            c = c.getComponentType();
            dim++;
        }

        return dim;
    }

    /**
     * copy传入的array，并返回一个新array2，array2比array多一个element在尾部。
     * 
     * @param array
     *            需要操作的数组，可能为null
     * @param element
     *            添加的元素
     * @return 新数组
     */
    public static long[] add(long[] array, long element) {
        long[] newArray = (long[]) copyArrayGrow1(array, Long.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * Returns a copy of the given array of size 1 greater than the argument.
     * The last value of the array is left to the default value.
     * 
     * @param array
     *            The array to copy, must not be <code>null</code>.
     * @param newArrayComponentType
     *            If <code>array</code> is <code>null</code>, create a size 1
     *            array of this type.
     * @return A new copy of the array of size 1 greater than the input.
     */
    private static Object copyArrayGrow1(Object array, Class newArrayComponentType) {
        if (array != null) {
            int arrayLength = Array.getLength(array);
            Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }

}