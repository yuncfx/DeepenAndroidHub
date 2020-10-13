package collections;

public class TestSort {

    public static void main(String[] args) {
        int[] intArray = new int[] { 2, 6, 3, 8, 4, 9, 1, 10, 89, 0 };

        for (int i : intArray) {
            System.out.print(i + " ");
        }
        System.out.println();
        shellSort(intArray);

        for (int i : intArray) {
            System.out.print(i + " ");
        }
    }

    public static void bubbleSort(int[] arr) {
        int count = 0;
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr.length - x - 1; y++) { // -x，让每一次比较的元素减少，-1，避免角标越界
                if (arr[y] > arr[y + 1]) {
                    int temp = arr[y];
                    arr[y] = arr[y + 1];
                    arr[y + 1] = temp;
                }
                count++;
            }
        }
        System.out.println("count = " + count);
    }

    /**
     * bubbleSort2()的效率稍高于bubbleSort()方法, 莫非是因为bubbleSort每次要计算arr.length - x - 1
     * 
     * @param intArray
     *            相邻的两个元素进行比较，如果符合条件，则换位，每次将最值放到最后(倒数第二、倒数第三依次类推)
     *
     */
    public static void bubbleSort2(int[] intArray) {
        // int count = 0;
        int out, in;
        for (out = intArray.length - 1; out > 0; out--) {
            for (in = 0; in < out; in++) {
                if (intArray[in] > intArray[in + 1]) {
                    swap(intArray, in, in + 1);
                }
                // count++;
            }
        }
        // System.out.println("count = " + count);
    }

    /**
     * 选择排序
     * 
     * @param intArray
     *            第一次始终以0角标的元素和其它元素比较，将较小的存起来，循环直到最后，得到最小的数，放在0角标上；
     *            第二次始终以1角标的元素和1角标以后的其它元素比较，将较小的存起来，循环直到最后，得到第二小的数，放在1角标上；
     *            重复直到最后。
     */
    public static void selectionSort(int[] intArray) {
        for (int out = 0; out < intArray.length - 1; out++) {
            int min = out;
            for (int in = out + 1; in < intArray.length; in++) {
                if (intArray[in] < intArray[min])
                    min = in;
            }
            swap(intArray, out, min);
        }
    }

    /**
     * 插入排序就是把当前待排序的元素插入到一个已经排好序的列表里面
     * 从数组的第二个元素开始，取得当前待处理的元素，插入到当前元素之前的子数组里面，直到数组的末尾。
     * 插入排序的最坏运行时间是O(n2)，所以并不是最优的排序算法，特点是简单，不需要额外的存储空间，在元素少的时候工作得好。
     * 
     * @param intArray
     */
    public static void insertionSort(int[] intArray) {
        int in, out;

        for (out = 1; out < intArray.length; out++) {
            int temp = intArray[out];
            in = out;
            // 循环判断之前的一个元素是不是比temp大
            while (in > 0 && intArray[in - 1] >= temp) {
                intArray[in] = intArray[in - 1];
                --in;
            }
            // 把temp值放在最后确定的in的位置
            intArray[in] = temp;
        }
    }

    /**
     * 希尔排序也是插入排序的一种，优化算法
     * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
     * 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
     * 
     * @param intArray
     */
    public static void shellSort(int[] intArray) {
        int inner, outer;
        int temp;

        int h = 1; // h 是增量，换成别的值一样OK的
        while (h <= intArray.length / 3) {
            h = h * 3 + 1;
            System.out.println(h);
        }
        while (h > 0) {
            // outer从h开始递增，
            for (outer = h; outer < intArray.length; outer++) {
                temp = intArray[outer];
                inner = outer;

                /**
                 * inner 必须大于等于h
                 * 
                 * 如果intArray[inner - h] >= 当前的temp， 则将intArray[inner -
                 * h]的值赋给intArray[inner]， 并将inner - h
                 */
                while (inner > h - 1 && intArray[inner - h] >= temp) {
                    intArray[inner] = intArray[inner - h];
                    inner -= h;
                }
                intArray[inner] = temp;
            }
            h = (h - 1) / 3;
            System.err.println(h);
        }
    }

    /**
     * //快速排序: 不稳定，时间复杂度 最理想 O(nlogn) 最差时间O(n^2)
     * 是C.R.A.Hoare于1962年提出的一种划分交换排序。它采用了一种分治的策略，通常称其为分治法(Divide-and-
     * ConquerMethod)。 该方法的基本思想是： 1．先从数列中取出一个数作为基准数。
     * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。 3．再对左右区间重复第二步，直到各区间只有一个数
     * 
     * @param intArray
     */
    public static void quickSort(int[] intArray) {
        recQuickSort(intArray, 0, intArray.length - 1);
    }

    private static void recQuickSort(int[] intArray, int left, int right) {
        if (right <= left)
            return;
        else {
            int pivot = intArray[right];

            int partition = partitionIt(intArray, left, right, pivot);
            recQuickSort(intArray, left, partition - 1);
            recQuickSort(intArray, partition + 1, right);
        }
    }

    private static int partitionIt(int[] intArray, int left, int right, int pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (intArray[++leftPtr] < pivot)
                ;

            while (rightPtr > 0 && intArray[--rightPtr] > pivot)
                ;

            if (leftPtr >= rightPtr)
                break;
            else
                swap(intArray, leftPtr, rightPtr);
        }
        swap(intArray, leftPtr, right);
        return leftPtr;
    }

    public static void quickSort2(int[] intArray) {
        recQuickSort(intArray, 0, intArray.length - 1);
    }

    public static void recQuickSort2(int[] intArray, int left, int right) {
        int size = right - left + 1;
        if (size <= 3)
            manualSort(intArray, left, right);
        else {
            double median = medianOf3(intArray, left, right);
            int partition = partitionIt(intArray, left, right, median);
            recQuickSort(intArray, left, partition - 1);
            recQuickSort(intArray, partition + 1, right);
        }
    }

    public static int medianOf3(int[] intArray, int left, int right) {
        int center = (left + right) / 2;

        if (intArray[left] > intArray[center])
            swap(intArray, left, center);

        if (intArray[left] > intArray[right])
            swap(intArray, left, right);

        if (intArray[center] > intArray[right])
            swap(intArray, center, right);

        swap(intArray, center, right - 1);
        return intArray[right - 1];
    }

    public static int partitionIt(int[] intArray, int left, int right, double pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;

        while (true) {
            while (intArray[++leftPtr] < pivot)
                ;
            while (intArray[--rightPtr] > pivot)
                ;
            if (leftPtr >= rightPtr)
                break;
            else
                swap(intArray, leftPtr, rightPtr);
        }
        swap(intArray, leftPtr, right - 1);
        return leftPtr;
    }

    public static void manualSort(int[] intArray, int left, int right) {
        int size = right - left + 1;
        if (size <= 1)
            return;
        if (size == 2) {
            if (intArray[left] > intArray[right])
                swap(intArray, left, right);
            return;
        } else {
            if (intArray[left] > intArray[right - 1])
                swap(intArray, left, right - 1);
            if (intArray[left] > intArray[right])
                swap(intArray, left, right);
            if (intArray[right - 1] > intArray[right])
                swap(intArray, right - 1, right);
        }
    }

    public static void quickSort3(int[] intArray) {
        recQuickSort(intArray, 0, intArray.length - 1);
        insertionSort(intArray, 0, intArray.length - 1);
    }

    public static void recQuickSort3(int[] intArray, int left, int right) {
        int size = right - left + 1;
        if (size < 10)
            insertionSort(intArray, left, right);
        else {
            double median = medianOf3(intArray, left, right);
            int partition = partitionIt(intArray, left, right, median);
            recQuickSort(intArray, left, partition - 1);
            recQuickSort(intArray, partition + 1, right);
        }
    }

    public static double medianOf33(int[] intArray, int left, int right) {
        int center = (left + right) / 2;

        if (intArray[left] > intArray[center])
            swap(intArray, left, center);

        if (intArray[left] > intArray[right])
            swap(intArray, left, right);

        if (intArray[center] > intArray[right])
            swap(intArray, center, right);

        swap(intArray, center, right - 1);
        return intArray[right - 1];
    }

    public static int partitionIt2(int[] intArray, int left, int right, double pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;
        while (true) {
            while (intArray[++leftPtr] < pivot)
                ;
            while (intArray[--rightPtr] > pivot)
                ;
            if (leftPtr >= rightPtr)
                break;
            else
                swap(intArray, leftPtr, rightPtr);
        }
        swap(intArray, leftPtr, right - 1);
        return leftPtr;
    }

    public static void insertionSort(int[] intArray, int left, int right) {
        int in, out;

        for (out = left + 1; out <= right; out++) {
            int temp = intArray[out];
            in = out;

            while (in > left && intArray[in - 1] >= temp) {
                intArray[in] = intArray[in - 1];
                --in;
            }
            intArray[in] = temp;
        }
    }

    private static void swap(int[] intArray, int one, int two) {
        int temp = intArray[one];
        intArray[one] = intArray[two];
        intArray[two] = temp;
    }
}
