package datastructures.graph;

import datastructures.queue.CircleObjectQueue;
import datastructures.stack.ObjectStack;
import matrix.IntMatrix;

import java.util.Arrays;

/**
 * ClassName: Graph
 * Package: datastructures.graph
 * Description:  Directed graph. Note that undirected graphs are a special case of directed graphs.
 *
 * @Author: luv_x_c
 * @Create: 2023/5/23 10:36
 */
public class Graph {
    /**
     * The connected matrix.
     */
    IntMatrix connectivityMatrix;

    /**
     * The first constructor.
     *
     * @param paraNumNodes The number of nodes in the graph.
     */
    public Graph(int paraNumNodes) {
        connectivityMatrix = new IntMatrix(paraNumNodes, paraNumNodes);
    }// Of the first constructor

    /**
     * The second constructor.
     *
     * @param paraMatrix The data matrix.
     */
    public Graph(int[][] paraMatrix) {
        connectivityMatrix = new IntMatrix(paraMatrix);
    }// Of the second constructor

    @Override
    public String toString() {
        return "This is the connectivity matrix of the graph.\r\n" + connectivityMatrix;
    }// Of toString

    /**
     * Get the connectivity of the graph.
     *
     * @return Connective or not.
     * @throws Exception for internal error.
     */
    public boolean getConnectivity() throws Exception {
        // Step1. Initialize accumulated matrix: M_a = I.
        IntMatrix tempConnectivityMatrix = IntMatrix.getIdentityMatrix(connectivityMatrix.getData().length);

        // Step2. Initialize M^1.
        IntMatrix tempMultipliedMatrix = new IntMatrix(connectivityMatrix);

        // Step3. Determine the actual connectivity.
        for (int i = 0; i < connectivityMatrix.getData().length - 1; i++) {
            // M_a =M_a + M^k
            tempConnectivityMatrix.add(tempMultipliedMatrix);

            // M^k
            tempMultipliedMatrix = IntMatrix.multiply(connectivityMatrix, tempMultipliedMatrix);
        }// Of for i

        // Step4. Check the connectivity.
        System.out.println("The connectivity matrix is: " + tempConnectivityMatrix);
        int[][] tempData = tempConnectivityMatrix.getData();
        for (int i = 0; i < tempData.length; i++) {
            for (int j = 0; j < tempData.length; j++) {
                if (tempData[i][j] == 0) {
                    System.out.println("Node" + i + "cannot reach " + j);
                    return false;
                }// Of if
            }// Of for j
        }// Of for i

        return true;
    }// Of getConnectivity

    /**
     * Unit test for getConnectivity.
     */
    public static void getConnectivityTest() {
        // Test an undirected graph.
        int[][] tempMatrix = {{0, 1, 0}, {1, 0, 1}, {0, 1, 0}};
        Graph tempGraph2 = new Graph(tempMatrix);
        System.out.println(tempGraph2);

        boolean tempConnected = false;
        try {
            tempConnected = tempGraph2.getConnectivity();
        } catch (Exception e) {
            System.out.println(e);
        }// of try

        System.out.println("Is the graph connected? " + tempConnected);

        // Test a directed graph.
        // Remove one arc to from a directed graph.
        tempGraph2.connectivityMatrix.setValue(1, 0, 0);

        tempConnected = false;
        try {
            tempConnected = tempGraph2.getConnectivity();
        } catch (Exception e) {
            System.out.println(e);
        }// Of try.

        System.out.println("Is the graph connected? " + tempConnected);
    }// Of getConnectivity

    /**
     * Breadth first traversal.
     *
     * @param paraStartIndex The start index.
     * @return The sequence of the visit.
     */
    public String breadthFirstTraversal(int paraStartIndex) {
        CircleObjectQueue tempQueue = new CircleObjectQueue();
        String resultString = "";

        int tempNumNodes = connectivityMatrix.gerRows();
        boolean[] tempVisited = new boolean[tempNumNodes];

        //Initialize the queue and traversal.
        for (int i = 0; i < tempNumNodes; i++) {
            if (!tempVisited[paraStartIndex]) {
                tempVisited[paraStartIndex] = true;
                resultString += paraStartIndex;
                tempQueue.enqueue(paraStartIndex);
            }// Of if
            while (!tempQueue.isEmpty()) {
                int tempIndex = (int) tempQueue.dequeue();
                for (int j = 0; j < tempNumNodes; j++) {
                    if (tempVisited[j]) {
                        continue;
                    }// Of if

                    if (connectivityMatrix.getData()[tempIndex][j] == 0) {
                        continue;
                    }// Of if

                    // Visit and enqueue.
                    tempVisited[j] = true;
                    resultString += j;
                    tempQueue.enqueue(j);
                }// Of for j
            }// Of while
        }// Of for i
        return resultString;
    }// Of breadthFirstTraversal

    /**
     * Unit test for breadthFirstTraversal.
     */
    public static void breadthFirstTraversalTest() {
        // Test an undirected graph.
        int[][] tempMatrix = {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};
        Graph tempGraph = new Graph(tempMatrix);
        System.out.println(tempGraph);

        String tempSequence = "";
        try {
            tempSequence = tempGraph.breadthFirstTraversal(0);
        } catch (Exception e) {
            System.out.println(e);
        } // Of try.

        System.out.println("The breadth first order of visit: " + tempSequence);
    }//Of breadthFirstTraversalTest

    /**
     * Depth first traversal.
     *
     * @param paraStartIndex The start index.
     * @return The sequence of the visit.
     */
    public String depthFirstTraversal(int paraStartIndex) {
        ObjectStack tempStack = new ObjectStack();
        String resultString = "";

        int tempNumNodes = connectivityMatrix.gerRows();
        boolean[] tempVisitedArray = new boolean[tempNumNodes];

        // Initialize the stack and visit.
        tempVisitedArray[paraStartIndex] = true;
        resultString += paraStartIndex;
        tempStack.push(paraStartIndex);
        System.out.println("PUSH " + paraStartIndex);
        System.out.println("Visited " + resultString);

        // Now visit the remaining nodes.
        while (!tempStack.isEmpty()) {
            int tempIndex = (int) tempStack.getTop();
            boolean flag = false;
            // Find an unvisited neighbor.
            for (int i = 0; i < tempNumNodes; i++) {
                if (tempVisitedArray[i]) {
                    continue;
                }// Of if

                if (connectivityMatrix.getData()[tempIndex][i] == 0) {
                    continue;
                }// Of if

                flag = true;
                // Visit the node.
                tempVisitedArray[i] = true;
                resultString += i;
                tempStack.push(i);
                System.out.print("PUSH " + i);
                System.out.println(" Visit " + i);

                // One node is ok.
                break;
            }// Of for i

            if (flag == false) {
                tempIndex = (int) tempStack.pop();
                System.out.println("Pop " + tempIndex + ", The stack is: " + tempStack);
            }// Of if
        }// Of while

        return resultString;
    }// Of depthFirstTraversal

    /**
     * Unit test for depthFirstTraversal.
     */
    public static void depthFirstTraversalTest() {
        // Test an undirected graph.
        int[][] tempMatrix = {{0, 0, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 0}, {0, 1, 0, 0}};
        Graph tempGraph = new Graph(tempMatrix);
        System.out.println(tempGraph);

        String tempSequence = "";
        try {
            tempSequence = tempGraph.depthFirstTraversal(0);
        } catch (Exception e) {
            System.out.println(e);
        } // Of try.

        System.out.println("The depth first order of visit: " + tempSequence);
    }//Of depthFirstTraversalTest

    /**
     * Coloring. Output all possible schemes.
     *
     * @param paraNumColors The number of colors.
     */
    public void coloring(int paraNumColors) {
        // Step1. Initialize.
        int tempNumNodes = connectivityMatrix.gerRows();
        int[] tempColorScheme = new int[tempNumNodes];
        Arrays.fill(tempColorScheme, -1);

        coloring(paraNumColors, 0, tempColorScheme);
    }// of coloring

    /**
     * Coloring. Output all possible schemes.
     *
     * @param paraNumColors       The number of colors.
     * @param paraCurrentNumNodes The number of nodes that have been colored.
     * @param paraCurrentColoring The array recording the coloring scheme.
     */
    public void coloring(int paraNumColors, int paraCurrentNumNodes, int[] paraCurrentColoring) {
        // Step1. Initialize.
        int tempNumNodes = connectivityMatrix.gerRows();

        System.out.println("coloring: paraNumColors = " + paraNumColors + ", paraCurrentNumNodes = "
                + paraCurrentNumNodes + ", paraCurrentColoring" + Arrays.toString(paraCurrentColoring));
        // A completed scheme.
        if (paraCurrentNumNodes >= tempNumNodes) {
            System.out.println("Find one:" + Arrays.toString(paraCurrentColoring));
            return;
        }// Of if

        // Try all possible colors.
        for (int i = 0; i < paraNumColors; i++) {
            paraCurrentColoring[paraCurrentNumNodes] = i;
            if (!colorConflict(paraCurrentNumNodes + 1, paraCurrentColoring)) {
                coloring(paraNumColors, paraCurrentNumNodes + 1, paraCurrentColoring);
            }// Of if
        }// Of for i
    } // Of coloring

    /**
     * Coloring conflict or not. Only compare the current last node with previous ones.
     *
     * @param paraCurrentNumNodes The current number of nodes.
     * @param paraColoring        The current coloring scheme.
     * @return Conflict or not.
     */
    public boolean colorConflict(int paraCurrentNumNodes, int[] paraColoring) {
        for (int i = 0; i < paraCurrentNumNodes - 1; i++) {
            // No directed connection.
            if (connectivityMatrix.getValue(paraCurrentNumNodes - 1, i) == 0) {
                continue;
            }// Of if

            if (paraColoring[paraCurrentNumNodes - 1] == paraColoring[i]) {
                return true;
            }// Of if
        }// Of for i
        return false;
    }// Of colorConflict

    /**
     * Coloring test.
     */
    public static void coloringTest() {
        int[][] tempMatrix = {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 0}, {0, 1, 0, 0}};
        Graph tempGraph = new Graph(tempMatrix);

        tempGraph.coloring(3);
    }// Of coloringTest

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        Graph tempGraph = new Graph(3);
        System.out.println(tempGraph);

//         Unit test.
        getConnectivityTest();
        breadthFirstTraversalTest();
        depthFirstTraversalTest();
        coloringTest();
    }// Of main

}// Of class Graph
