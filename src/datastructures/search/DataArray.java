package datastructures.search;

import java.util.Arrays;

/**
 * ClassName: DataArray
 * Package: datastructures.search
 * <p>
 * Description: Data array for searching and sorting algorithms.
 *
 * @Author: luv_x_c
 * @Create: 2023/6/1 16:26
 */
public class DataArray {
    /**
     * An inner class.
     */
    class DataNode {
        /**
         * The key.
         */
        int key;

        /**
         * The data content.
         */
        String content;

        /**
         * The first constructor.
         */
        public DataNode(int paraKey, String paraContent) {
            this.key = paraKey;
            this.content = paraContent;
        }// Of the first constructor

        @Override
        public String toString() {
            return "(" + key + " , " + content + ") ";
        }// Of toString
    }//Of class DataNode

    /**
     * The data array.
     */
    DataNode[] data;

    /**
     * The length of the data array.
     */
    int length;

    /**
     * The first constructor.
     *
     * @param paraKeyArray     The array of the keys.
     * @param paraContentArray The array of the contents.
     */
    public DataArray(int[] paraKeyArray, String[] paraContentArray) {
        this.length = paraKeyArray.length;
        data = new DataNode[length];

        for (int i = 0; i < length; i++) {
            data[i] = new DataNode(paraKeyArray[i], paraContentArray[i]);
        }// Of for i
    }// Of the first constructor

    /**
     * The second constructor. For hash.
     *
     * @param paraKeyArray     The array of the keys.
     * @param paraContentArray The array of the contents.
     * @param paraLength       The space for the hash table.
     */
    public DataArray(int[] paraKeyArray, String[] paraContentArray, int paraLength) {
        // Step 1. Initialize.
        length = paraLength;
        data = new DataNode[length];

        for (int i = 0; i < length; i++) {
            data[i] = null;
        }// Of for i

        // Step 2. Fill the data.
        int tempPosition;

        for (int i = 0; i < paraKeyArray.length; i++) {
            // Hash.
            tempPosition = paraKeyArray[i] % length;

            // Find a position.
            while (data[tempPosition] != null) {
                tempPosition = (tempPosition + 1) % length;
                System.out.println("Collision, move forward for key  " + paraKeyArray[i]);
            }// Of while

            data[tempPosition] = new DataNode(paraKeyArray[i], paraContentArray[i]);
        }// Of for i
    }// Of the second constructor

    /**
     * Hash search
     *
     * @param paraKey The given key.
     * @return The content of the key.
     */
    public String hashSearch(int paraKey) {
        int tempPosition = paraKey % length;
        while (data[tempPosition] != null) {
            if (data[tempPosition].key == paraKey) {
                return data[tempPosition].content;
            }// Of if
            System.out.println("This position: " + tempPosition + " is not for " + paraKey);
            tempPosition = (tempPosition + 1) % length;
        }//Of while

        return null;
    }//Of hashSearch

    /**
     * Test unit.
     */
    public static void hashSearchTest() {
        int[] tempUnsortedKeys = {16, 33, 38, 69, 57, 95, 86};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents, 19);

        System.out.println(tempDataArray);

        System.out.println("Search result of 95 is: " + tempDataArray.hashSearch(95));
        System.out.println("Search result of 38 is: " + tempDataArray.hashSearch(38));
        System.out.println("Search result of 57 is: " + tempDataArray.hashSearch(57));
        System.out.println("Search result of 4 is: " + tempDataArray.hashSearch(4));
    }// Of hashSearchTest

    /**
     * insertionSort
     */
    public void insertionSort() {

        for (int i = 2; i < length; i++) {
            data[0] = data[i];
            int j;
            for (j = i - 1; data[j].key > data[0].key; j--) {
                data[j + 1] = data[j];
            }//Of for j

            data[j + 1] = data[0];

            System.out.println("Round " + (i - 1));
            System.out.println(this);
        }// Of for i
    }// Of insertionSort

    /**
     * Test unit.
     */
    public static void insertionSortTest() {
        int[] tempUnsortedKeys = {100, 5, 3, 6, 10, 7, 1, 9};
        String[] tempContents = {"null", "if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        tempDataArray.insertionSort();
        System.out.println("Result\r\n" + tempDataArray);
    }// Of insertionSortTest

    /**
     * Shell sort.
     */
    public void shellSort() {
        DataNode tempNode;
        int tempRound = 1;
        int[] tempJumpArray = {5, 3, 1};
        int p = 0;
        for (int tempJump; p < tempJumpArray.length; p++) {
            tempJump = tempJumpArray[p];
            for (int j = tempJump; j < length; j++) {
                tempNode = data[j];
                int i;
                for (i = j - tempJump; i >= 0 && tempNode.key < data[i].key; i -= tempJump) {
                    data[i + tempJump] = data[i];
                }// Of for i
                data[i + tempJump] = tempNode;
            }//Of for j

            System.out.println("Round " + (tempRound++));
            System.out.println(this);
        }// Of for tempJump
    }// Of shellSort

    /**
     * Teat unit.
     */
    public static void shellSortTest() {
        int[] tempUnsortedKeys = {5, 3, 6, 10, 7, 1, 9, 12, 8, 4};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while", "throw", "until", "do"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        tempDataArray.shellSort();
        System.out.println("Result\r\n" + tempDataArray);
    }// Of shellSortTest


    @Override
    public String toString() {
        return "DataArray{" +
                "data=" + Arrays.toString(data) +
                ", length=" + length +
                '}';
    }// Of toString

    /**
     * Sequential search. The index 0 is not used.
     *
     * @param paraKey The given key.
     * @return The content of the key.
     */
    public String sequentialSearch(int paraKey) {
        // 用作岗哨，保证表里可以找到，避免越界的麻烦
        data[0].key = paraKey;

        int i = length - 1;
        while (data[i].key != paraKey) {
            i--;
        }

        return data[i].content;
    }// Of sequentialSearch

    /**
     * Test unit.
     */
    public static void sequentialTest() {
        int[] tempUnsortedKeys = {-1, 5, 3, 6, 10, 7, 1, 9};
        String[] tempContents = {"null", "if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        System.out.println("Search result of 10 is: " + tempDataArray.sequentialSearch(10));
        System.out.println("Search result of 5 is: " + tempDataArray.sequentialSearch(5));
        System.out.println("Search result of 4 is: " + tempDataArray.sequentialSearch(4));
    }// Of sequentialSearchTest

    /**
     * Binary search. The keys must be sorted.
     *
     * @param paraKey The given key.
     * @return The content of the key.
     */
    public String binarySearch(int paraKey) {
        int tempLeft = 0;
        int tempRight = length - 1;
        int tempMid;

        while (tempLeft <= tempRight) {
            tempMid = (tempLeft + tempRight) / 2;
            if (data[tempMid].key == paraKey) {
                return data[tempMid].content;
            } else if (data[tempMid].key > paraKey) {
                tempRight = tempMid - 1;
            } else {
                tempLeft = tempMid + 1;
            }//Of if
        }// Of while

        return null;
    }//Of binarySearch

    /**
     * Test unit.
     */
    public static void binarySearchTest() {
        int[] tempSortedKeys = {1, 3, 5, 6, 7, 9, 10};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempSortedKeys, tempContents);

        System.out.println(tempDataArray);

        System.out.println("Search result of 10 is: " + tempDataArray.binarySearch(10));
        System.out.println("Search result of 5 is: " + tempDataArray.binarySearch(5));
        System.out.println("Search result of 4 is: " + tempDataArray.binarySearch(4));
    }// Of binarySearchTest

    /**
     * Bubble sort.
     */
    public void bubbleSort() {
        boolean tempSwapped;
        DataNode tempNode;
        for (int i = 0; i < length; i++) {
            tempSwapped = false;
            for (int j = length - 1; j > i; j--) {
                if (data[j].key < data[j - 1].key) {
                    tempSwapped = true;
                    tempNode = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = tempNode;
                }// Of if
            }//Of for j
            if (!tempSwapped) {

                System.out.println("Premature ");
                break;
            }// Of if

            System.out.println("Round " + (i + 1));
            System.out.println(this);
        }// Of for i
    }// Of bubbleSort

    /**
     * Test unit.
     */
    public static void bubbleSortTest() {
        int[] tempUnsortedKeys = {1, 3, 6, 10, 7, 5, 9};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        tempDataArray.bubbleSort();
        System.out.println("Result\r\n" + tempDataArray);
    }// Of bubbleSortTest

    /**
     * Quick sort recursive.
     *
     * @param paraStart The start index.
     * @param paraEnd   The end index.
     */
    private void quickSortRecursive(int paraStart, int paraEnd) {
        if (paraStart < paraEnd) {
            int tempPivot = pivot(paraStart, paraEnd);
            System.out.print("From " + paraStart + " to " + paraEnd + " :");
            System.out.println(this);
            quickSortRecursive(paraStart, tempPivot - 1);
            quickSortRecursive(tempPivot + 1, paraEnd);
        }// Of if
    }// Of quickSortRecursive

    private int pivot(int paraStart, int paraEnd) {
        DataNode tempNode = data[paraEnd];

        while (paraStart < paraEnd) {
            while (paraStart < paraEnd && data[paraStart].key < tempNode.key) paraStart++;
            data[paraEnd] = data[paraStart];
            while (paraStart < paraEnd && data[paraEnd].key > tempNode.key) paraEnd--;
            data[paraStart] = data[paraEnd];
        }// Of wile
        data[paraStart] = tempNode;
        return paraStart;
    }//Of pivot

    public void quickSort() {
        quickSortRecursive(0, length - 1);
    }// Of quickSort


    /**
     * Test unit
     */
    public static void quickSortTest() {
        int[] tempUnsortedKeys = {1, 3, 12, 10, 5, 7, 9};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        tempDataArray.quickSort();
        System.out.println("Result\r\n" + tempDataArray);
    }// Of quickSortTest

    /**
     * Selection sort.
     */
    public void selectionSort() {
        DataNode tempNode;
        int tempIndexForSmallest;
        for (int i = 0; i < length - 1; i++) {
            tempNode = data[i];
            tempIndexForSmallest = i;
            for (int j = i; j < length; j++) {
                if (tempNode.key > data[j].key) {
                    tempNode = data[j];
                    tempIndexForSmallest = j;
                }// Of if
            }// Of for j
            data[tempIndexForSmallest] = data[i];
            data[i] = tempNode;
        }// Of for i
    }// Of selectionSOrt

    /**
     * Test unit.
     */
    public static void selectionSortTest() {
        int[] tempUnsortedKeys = {5, 3, 6, 10, 7, 1, 9};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        tempDataArray.selectionSort();
        System.out.println("Result\r\n" + tempDataArray);
    }// Of selectionSortTest

    /**
     * Heap sort.
     */
    public void heapSort() {
        DataNode tempNode;
        // Step 1. Initialize the initial heap.
        for (int i = length / 2 - 1; i >= 0; i--) {
            adjustHeap(i, length);
        }//Of for i
        System.out.println("The initial heap :" + this + "\r\n");

        // Step 2. Swap and reconstruct.
        for (int i = length - 1; i > 0; i--) {
            tempNode = data[i];
            data[i] = data[0];
            data[0] = tempNode;

            adjustHeap(0, i);
            System.out.println("Round " + (length - i) + ":" + this);
        }// Of for i
    }// Of heapSort

    /**
     * Adjust the heap.
     *
     * @param paraStart  The start of the index.
     * @param paraLength The length of the heap.
     */
    private void adjustHeap(int paraStart, int paraLength) {
        DataNode tempNode = data[paraStart];
        int tempParent = paraStart;
        int tempKey = data[paraStart].key;

        for (int tempChild = 2 * tempParent + 1; tempChild < paraLength; tempChild = tempChild * 2 + 1) {
            // Chose the biggest child.
            if (tempChild < paraLength - 1 && data[tempChild].key < data[tempChild + 1].key) {
                tempChild++;
            }// Of if

            System.out.println("The parent position is " + tempParent + " and the child is " + tempChild);
            if (tempKey < data[tempChild].key) {
                // The child is bigger.
                data[tempParent] = data[tempChild];
                System.out.println("Move " + data[tempChild].key + " to position " + tempParent);
                tempParent = tempChild;
            } else {
                break;
            }// Of if
        }// Of for tempChild

        data[tempParent] = tempNode;
        System.out.println("Adjust " + paraStart + " to " + paraLength + ": " + this);
    }// Of adjustHeap

    /**
     * Test unit.
     */
    public static void heapSortTest() {
        int[] tempUnsortedKeys = {5, 3, 6, 10, 7, 1, 9};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        tempDataArray.heapSort();
        System.out.println("Result\r\n" + tempDataArray);
    }// Of heapSortTest

    /**
     * Merge sort recursive.
     *
     * @param paraStart The start index.
     * @param paraEnd   The end index.
     */
    private void mergeSortRecursive(int paraStart, int paraEnd) {
        if (paraStart < paraEnd) {
            mergeSortRecursive(paraStart, (paraStart + paraEnd) / 2);
            mergeSortRecursive((paraStart + paraEnd) / 2 + 1, paraEnd);
            merge(paraStart, (paraStart + paraEnd) / 2, paraEnd);
            System.out.println(this);
        }// Of if
    }// Of mergeSortRecursive

    private void merge(int paraLeft, int paraRight, int paraRightEnd) {
        DataNode[] tempDataArray = new DataNode[paraRightEnd - paraLeft + 1];
        int tempDataArrayIndex = 0, tempLeftIndex = paraLeft, tempRightIndex = paraRight + 1;

        // Compare and copy.
        while (tempLeftIndex <= paraRight && tempRightIndex <= paraRightEnd) {
            if (data[tempLeftIndex].key < data[tempRightIndex].key) {
                tempDataArray[tempDataArrayIndex++] = data[tempLeftIndex++];
            } else {
                tempDataArray[tempDataArrayIndex++] = data[tempRightIndex++];
            }// Of if
        }// Of while

        // Copy to rest data.
        while (tempLeftIndex <= paraRight) {
            tempDataArray[tempDataArrayIndex++] = data[tempLeftIndex++];
        }// Of while
        while (tempRightIndex <= paraRightEnd) {
            tempDataArray[tempDataArrayIndex++] = data[tempRightIndex++];
        }// Of while

        System.arraycopy(tempDataArray, 0, data, paraLeft, tempDataArray.length);
    }// Of merge

    /**
     * Merge sort.
     */
    public void mergeSort() {
        mergeSortRecursive(0, length - 1);
    }// Of mergeSort

    /**
     * Test unit.
     */
    public static void mergeSortTest() {
        int[] tempUnsortedKeys = {5, 3, 6, 10, 7, 1, 9};
        String[] tempContents = {"if", "then", "else", "switch", "case", "for", "while"};
        DataArray tempDataArray = new DataArray(tempUnsortedKeys, tempContents);

        System.out.println(tempDataArray);

        tempDataArray.mergeSort();
        System.out.println(tempDataArray);
    }// Of mergeSortTest

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        System.out.print("The sequentialTest:\n");
        sequentialTest();

        System.out.println("\nThe binaryTest:");
        binarySearchTest();

        System.out.println("\nThe hashSearchTest:");
        hashSearchTest();

        System.out.println("\nThe insertionSortTest:");
        insertionSortTest();

        System.out.println("\nThe shellSortTest:");
        shellSortTest();

        System.out.println("\nThe bubbleSortTest:");
        bubbleSortTest();

        System.out.println("\nThe quickSortTest:");
        quickSortTest();

        System.out.println("\nThe selectionSortTest:");
        selectionSortTest();

        System.out.println("\nThe heapSortTest:");
        heapSortTest();

        System.out.println("\nThe mergeSortTest:");
        mergeSortTest();

    }// Of main
}// Of class DataArray
