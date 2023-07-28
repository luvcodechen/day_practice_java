package datastructures.tree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * ClassName: Huffman
 * Package: datastructures.tree
 * Description:Huffman tree, encoding, and decoding. For simplicity, only ASCII characters are supported.
 *
 * @Author: luv_x_c
 * @Create: 2023/5/17 14:27
 */
public class Huffman {
    /**
     * An inner class for huffman nodes.
     */
    static class HuffmanNode {
        /**
         * The char. Only valid for leaves.
         */
        char character;

        /**
         * Weight. It can also be double.
         */
        int weight;
        /**
         * The left child.
         */
        HuffmanNode leftChild;

        /**
         * The right child.
         */
        HuffmanNode rightChild;
        /**
         * The parent. It helps to construct the huffman code of each character.
         */
        HuffmanNode parent;

        /**
         * The first constructor.
         */
        public HuffmanNode(char paraCharacter, int paraWeight, HuffmanNode paraLeftChild, HuffmanNode paraRightChild,
                           HuffmanNode paraParentChild) {
            this.character = paraCharacter;
            this.weight = paraWeight;
            this.leftChild = paraLeftChild;
            this.rightChild = paraRightChild;
            this.parent = paraParentChild;
        }// Of HuffmanNode

        /**
         * To String.
         */
        public String toString() {
            return "(" + character + "," + weight + ")";
        }//Of toString
    }//Of class huffmanNode

    /**
     * The number of characters. 256 for ASCII.
     */
    public static final int NUM_CHARS = 256;

    /**
     * The input text. It is stored in a string for simplicity.
     */
    String inputText;

    /**
     * The length of the alphabet, also the number of leaves.
     */
    int alphabetLength;

    /**
     * The alphabet.
     */
    char[] alphabet;

    /**
     * The count of chars. The length is 2* alphabetLength-1 to include non-leaf nodes.
     */
    int[] charCounts;

    /**
     * The mapping of chars to the indices in the alphabet.
     */
    int[] charMapping;

    /**
     * Codes for each in the alphabet. It should have the same length as alphabet.
     */
    String[] huffmanCodes;

    /**
     * All nodes. The last node is the root.
     */
    HuffmanNode[] nodes;

    /**
     * The first constructor.
     *
     * @param paraFilename The tet name.
     */
    public Huffman(String paraFilename) {
        charMapping = new int[NUM_CHARS];

        readText(paraFilename);
    }// Of the first constructor

    private void readText(String paraFilename) {
        try {
            inputText = Files.newBufferedReader(Paths.get(paraFilename), StandardCharsets.UTF_8).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }// Of try

        System.out.println("The text is:\r\t" + inputText);
    }// Of readText

    /**
     * Construct the alphabet. The results are stored in the member variables charMapping and alphabet.
     */
    public void constructAlphabet() {
        // Initialize.
        Arrays.fill(charMapping, -1);

        // The count for each char. At most NUM_CHARS chars.
        int[] tempCharCounts = new int[NUM_CHARS];

        // The index of the char in the ASCII charset.
        int tempCharIndex;

        // Step1. Scan the string to obtain the counts.
        char tempChar;
        for (int i = 0; i < inputText.length(); i++) {
            tempChar = inputText.charAt(i);
            tempCharIndex = tempChar;

            System.out.println("" + tempCharIndex + " ");

            tempCharCounts[tempCharIndex]++;
        }// Of for i

        // Step2. Scan to determine the size of the alphabet.
        alphabetLength = 0;
        for (int i = 0; i < 255; i++) {
            if (tempCharCounts[i] > 0) {
                alphabetLength++;
            }// Of if
        }// Of for i

        //Step3. Compress to the alphabet.
        alphabet = new char[alphabetLength];
        charCounts = new int[2 * alphabetLength - 1];

        int tempCounter = 0;
        for (int i = 0; i < NUM_CHARS; i++) {
            if (tempCharCounts[i] > 0) {
                alphabet[tempCounter] = (char) i;
                charCounts[tempCounter] = tempCharCounts[i];
                charMapping[i] = tempCounter;
                tempCounter++;
            }// Of if
        }// Of for i

        System.out.println("The alphabet is: " + Arrays.toString(alphabet));
        System.out.println("Their counts are: " + Arrays.toString(charCounts));
        System.out.println("The char mappings are: " + Arrays.toString(charMapping));
    }// Of constructAlphabet

    /**
     * Construct the tree.
     */
    public void constructTree() {
        // Step1. Allocate space.
        nodes = new HuffmanNode[alphabetLength * 2 - 1];
        boolean[] tempProcessed = new boolean[alphabetLength * 2 - 1];

        // Step2. Initialize leaves.
        for (int i = 0; i < alphabetLength; i++) {
            nodes[i] = new HuffmanNode(alphabet[i], charCounts[i], null, null, null);
        }// Of for i

        // Step3. Construct the tree.
        int tempLeft, tempRight, tempMinimal;
        for (int i = alphabetLength; i < 2 * alphabetLength - 1; i++) {
            // Step3.1 Select the first minimal as the left child.
            tempLeft = -1;
            tempMinimal = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (tempProcessed[j]) {
                    continue;
                }// Of if

                if (tempMinimal > charCounts[j]) {
                    tempMinimal = charCounts[j];
                    tempLeft = j;
                }// Of if
            }// Of for j
            tempProcessed[tempLeft] = true;

            // Step3.2 Select the second minimal as the right child.
            tempRight = -1;
            tempMinimal = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (tempProcessed[j]) {
                    continue;
                }// Of if

                if (tempMinimal > charCounts[j]) {
                    tempMinimal = charCounts[j];
                    tempRight = j;
                }// Of if
            }// Of for j
            tempProcessed[tempRight] = true;
            System.out.println("Selecting " + tempLeft + " and " + tempRight);

            // Step3.3 Construct the new node.
            charCounts[i] = charCounts[tempLeft] + charCounts[tempRight];
            nodes[i] = new HuffmanNode('*', charCounts[i], nodes[tempLeft], nodes[tempRight], null);

            // Step3.4 Link with children.
            nodes[tempLeft].parent = nodes[i];
            nodes[tempRight].parent = nodes[i];
            System.out.println("The children of " + i + " are " + tempLeft + " and " + tempRight);
        }// Of for i
    }// Of constructTree

    /**
     * Get the root of the binary tree.
     *
     * @return The root.
     */
    public HuffmanNode getRoot() {
        return nodes[nodes.length - 1];
    }// Of getRoot

    /**
     * Pre-order visit.
     */
    public void preOrderVisit(HuffmanNode paraNode) {
        System.out.print("(" + paraNode.character + ", " + paraNode.weight + ") ");

        if (paraNode.leftChild != null) {
            preOrderVisit(paraNode.leftChild);
        }// Of if

        if (paraNode.rightChild != null) {
            preOrderVisit(paraNode.rightChild);
        }// Of if
    }// Of preOrderVisit

    /**
     * Generate codes for each character in the alphabet.
     */
    public void generateCodes() {
        huffmanCodes = new String[alphabetLength];
        HuffmanNode tempNode;
        for (int i = 0; i < alphabetLength; i++) {
            tempNode = nodes[i];
            // Use tempCharNode instead of tempNode such that is unlike.
            // tempNode.
            // This is an advantage of long names.
            String tempCharNode = "";
            while (tempNode.parent != null) {
                if (tempNode == tempNode.parent.leftChild) {
                    tempCharNode = "0" + tempCharNode;
                } else {
                    tempCharNode = "1" + tempCharNode;
                }// Of if

                tempNode = tempNode.parent;
            }// Of while

            huffmanCodes[i] = tempCharNode;
            System.out.println("The code of " + alphabet[i] + " is " + tempCharNode);
        }// Of for i
    }// Of generateCodes

    /**
     * Encode the given string.
     *
     * @param paraString The giving String.
     */
    public String coding(String paraString) {
        String resultCodeString = "";

        int tempIndex;
        for (int i = 0; i < paraString.length(); i++) {
            // From the original char to the location in the alphabet.
            tempIndex = charMapping[(int) paraString.charAt(i)];

            // From the location in the alphabet to the code.
            resultCodeString += huffmanCodes[tempIndex];
        }//Of for i
        return resultCodeString;
    }// Of coding

    /**
     * Decode the given string.
     *
     * @param paraString The given string.
     */
    public String decoding(String paraString) {
        String resultCodeString = "";

        HuffmanNode tempNode = getRoot();

        for (int i = 0; i < paraString.length(); i++) {
            if (paraString.charAt(i) == '0') {
                tempNode = tempNode.leftChild;
                System.out.println(tempNode);
            } else {
                tempNode = tempNode.rightChild;
                System.out.println(tempNode);
            }// Of if

            if (tempNode.leftChild == null) {
                System.out.println("Decode one :" + tempNode);
                // Decode one char
                resultCodeString += tempNode.character;

                // Return to the root.
                tempNode = getRoot();
            }// Of if
        }// Of for i
        return resultCodeString;
    }// Of deCoding

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        Huffman tempHuffman = new Huffman("E:\\workspace_idea1\\my_first_java\\src\\datastructures\\tree\\text.txt");
        tempHuffman.constructAlphabet();

        tempHuffman.constructTree();

        HuffmanNode tempRoot = tempHuffman.getRoot();
        System.out.println("The root is: " + tempRoot);
        System.out.println("Preorder visit:");
        tempHuffman.preOrderVisit(tempHuffman.getRoot());

        tempHuffman.generateCodes();

        String tempCoded = tempHuffman.coding("abcdb");
        System.out.println("Coded: " + tempCoded);
        String tempDecoded = tempHuffman.decoding(tempCoded);
        System.out.println("Decoded: " + tempDecoded);
    }// Of main

}// Of class Huffman
