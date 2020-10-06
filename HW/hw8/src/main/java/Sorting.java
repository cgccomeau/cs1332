import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Charlie Comeau
 * @userid ccomeau7
 * @GTID 903359699
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null!");
        }

        for (int i = 1; i < arr.length; i++) {
            int internali = i;
            for (int j =  i - 1; j >= 0; j--) {
                if (comparator.compare(arr[internali], arr[j]) < 0) {
                    swap(arr, internali, j);
                    internali--;
                } else {
                    j = -1;
                }
            }
        }
    }

    /**
     * private helper method that swaps 2 elements within an array
     * @param arr array to undergo swap
     * @param index1 index of first index to be swapped
     * @param index2 index of second index to be swapped
     * @param <T> data type to be swapped
     */
    private static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *  unstable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null!");
        }

        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[minIndex], arr[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null!");
        }

        if (arr.length == 1) {
            return;
        }
        int midArrLength = arr.length / 2;

        T[] arrLeft = (T[]) new Object[midArrLength];
        for (int i = 0; i < arrLeft.length; i++) {
            arrLeft[i] = arr[i];
        }
        T[] arrRight = (T[]) new Object[arr.length - arrLeft.length];
        for (int i = 0, j = midArrLength; i < arrRight.length; i++, j++) {
            arrRight[i] = arr[j];
        }

        mergeSort(arrLeft, comparator);
        mergeSort(arrRight, comparator);
        int i = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (i < arr.length) {
            if (rightIndex == arrRight.length || leftIndex < arrLeft.length
                    && comparator
                    .compare(arrLeft[leftIndex], arrRight[rightIndex]) <= 0) {
                arr[i++] = arrLeft[leftIndex++];
            } else {
                arr[i++] = arrRight[rightIndex++];
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     *  in-place
     *  unstable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null!");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Random cannot be null!");
        }

        quickSortHelper(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * recursive private helper method for quicksorting
     * @param arr array to be sorted
     * @param comparator comparator object for compare method
     * @param rand random object for generating random pivot
     * @param start start of array section to be sorted
     * @param end end of array section to be sorted
     * @param <T> data type to be sorted
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator,
                                            Random rand, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = rand.nextInt(end - start + 1) + start;
        int i = start + 1;
        int j = end;
        swap(arr, pivotIndex, start);
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], arr[start]) < 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], arr[start]) > 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i++, j--);
            }
        }
        swap(arr, start, j);

        quickSortHelper(arr, comparator, rand, start, j - 1);
        quickSortHelper(arr, comparator, rand, j + 1, end);
    }
    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array cannot be null!");
        }

        LinkedList<Integer>[] buckets =
                (LinkedList<Integer>[]) new LinkedList[19];
        int div = 1;
        int mod = 10;
        int k = 0;
        int numOfDigits = 0;
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            while (curr != 0) {
                curr = curr / 10;
                numOfDigits++;
            }
            if (numOfDigits > k) {
                k = numOfDigits;
            }
            numOfDigits = 0;
        }

        int arrIndex = 0;
        for (int i = 0; i < k; i++) {
            for (int num: arr) {
                if (buckets[num / div % mod + 9] == null) {
                    buckets[num / div % mod + 9] = new LinkedList<>();
                }
                buckets[num / div % mod + 9].add(num);
            }
            for (int j = 0; j < buckets.length; j++) {
                if (buckets[j] != null) {
                    for (int num: buckets[j]) {
                        arr[arrIndex++] = num;
                    }
                    buckets[j].clear();
                }
            }
            div *= 10;
            arrIndex = 0;
        }
    }
}
