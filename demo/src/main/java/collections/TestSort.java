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
            for (int y = 0; y < arr.length - x - 1; y++) { // -x����ÿһ�αȽϵ�Ԫ�ؼ��٣�-1������Ǳ�Խ��
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
     * bubbleSort2()��Ч���Ը���bubbleSort()����, Ī������ΪbubbleSortÿ��Ҫ����arr.length - x - 1
     * 
     * @param intArray
     *            ���ڵ�����Ԫ�ؽ��бȽϣ����������������λ��ÿ�ν���ֵ�ŵ����(�����ڶ�������������������)
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
     * ѡ������
     * 
     * @param intArray
     *            ��һ��ʼ����0�Ǳ��Ԫ�غ�����Ԫ�رȽϣ�����С�Ĵ�������ѭ��ֱ����󣬵õ���С����������0�Ǳ��ϣ�
     *            �ڶ���ʼ����1�Ǳ��Ԫ�غ�1�Ǳ��Ժ������Ԫ�رȽϣ�����С�Ĵ�������ѭ��ֱ����󣬵õ��ڶ�С����������1�Ǳ��ϣ�
     *            �ظ�ֱ�����
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
     * ����������ǰѵ�ǰ�������Ԫ�ز��뵽һ���Ѿ��ź�����б�����
     * ������ĵڶ���Ԫ�ؿ�ʼ��ȡ�õ�ǰ�������Ԫ�أ����뵽��ǰԪ��֮ǰ�����������棬ֱ�������ĩβ��
     * ��������������ʱ����O(n2)�����Բ��������ŵ������㷨���ص��Ǽ򵥣�����Ҫ����Ĵ洢�ռ䣬��Ԫ���ٵ�ʱ�����úá�
     * 
     * @param intArray
     */
    public static void insertionSort(int[] intArray) {
        int in, out;

        for (out = 1; out < intArray.length; out++) {
            int temp = intArray[out];
            in = out;
            // ѭ���ж�֮ǰ��һ��Ԫ���ǲ��Ǳ�temp��
            while (in > 0 && intArray[in - 1] >= temp) {
                intArray[in] = intArray[in - 1];
                --in;
            }
            // ��tempֵ�������ȷ����in��λ��
            intArray[in] = temp;
        }
    }

    /**
     * ϣ������Ҳ�ǲ��������һ�֣��Ż��㷨
     * ����˼�룺�㷨�Ƚ�Ҫ�����һ������ĳ������d��n/2,nΪҪ�������ĸ������ֳ������飬ÿ���м�¼���±����d.��ÿ����ȫ��Ԫ�ؽ���ֱ�Ӳ�������
     * Ȼ������һ����С��������d/2���������з��飬��ÿ�����ٽ���ֱ�Ӳ������򡣵���������1ʱ������ֱ�Ӳ��������������ɡ�
     * 
     * @param intArray
     */
    public static void shellSort(int[] intArray) {
        int inner, outer;
        int temp;

        int h = 1; // h �����������ɱ��ֵһ��OK��
        while (h <= intArray.length / 3) {
            h = h * 3 + 1;
            System.out.println(h);
        }
        while (h > 0) {
            // outer��h��ʼ������
            for (outer = h; outer < intArray.length; outer++) {
                temp = intArray[outer];
                inner = outer;

                /**
                 * inner ������ڵ���h
                 * 
                 * ���intArray[inner - h] >= ��ǰ��temp�� ��intArray[inner -
                 * h]��ֵ����intArray[inner]�� ����inner - h
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
     * //��������: ���ȶ���ʱ�临�Ӷ� ������ O(nlogn) ���ʱ��O(n^2)
     * ��C.R.A.Hoare��1962�������һ�ֻ��ֽ���������������һ�ַ��εĲ��ԣ�ͨ������Ϊ���η�(Divide-and-
     * ConquerMethod)�� �÷����Ļ���˼���ǣ� 1���ȴ�������ȡ��һ������Ϊ��׼����
     * 2���������̣���������������ȫ�ŵ������ұߣ�С�ڻ����������ȫ�ŵ�������ߡ� 3���ٶ����������ظ��ڶ�����ֱ��������ֻ��һ����
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
