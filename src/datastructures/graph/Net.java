package datastructures.graph;

import matrix.IntMatrix;

import java.util.Arrays;

/**
 * ClassName: Net
 * Package: datastructures.graph
 * Description: Weighted graphs are called nets.
 *
 * @Author: luv_x_c
 * @Create: 2023/5/29 9:28
 */
public class Net {
    /**
     * The maximal distance.
     */
    public static final int MAX_DISTANCE = 10000;

    /**
     * The number of nodes.
     */
    int numNodes;

    /**
     * The weight matrix.
     */
    IntMatrix weightMatrix;

    /**
     * The first constructor.
     *
     * @param paraNumNodes The number of nodes in the graph.
     */
    public Net(int paraNumNodes) {
        numNodes = paraNumNodes;
        weightMatrix = new IntMatrix(numNodes, numNodes);
        for (int i = 0; i < numNodes; i++) {
            Arrays.fill(weightMatrix.getData()[i], MAX_DISTANCE);
        }// Of for i
    }//Of the first constructor

    /**
     * The second constructor
     *
     * @param paraMatrix The data matrix.
     */
    public Net(int[][] paraMatrix) {
        weightMatrix = new IntMatrix(paraMatrix);
        numNodes = weightMatrix.gerRows();
    }// Of the second constructor

    @Override
    public String toString() {
        return " This is the weighted matrix of the graph.\r\n" + weightMatrix;
    }// Of toString

    /**
     * Dijkstra algorithm: shortest path from the source to all nodes.
     *
     * @param paraSource The source node.
     * @return The distance array.
     */
    public int[] dijkstra(int paraSource) {
        // Step1. Initialize.
        int[] tempDistanceArray = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            tempDistanceArray[i] = weightMatrix.getData()[paraSource][i];
        }// Of for i

        int[] tempParentArray = new int[numNodes];
        Arrays.fill(tempParentArray, paraSource);
        // -1 for no parent
        tempParentArray[paraSource] = -1;

        // Visited nodes will not be considered further.
        boolean[] tempVisitedArray = new boolean[numNodes];
        tempVisitedArray[paraSource] = true;

        // Step2. Main loops.
        int tempMinDistance;
        int tempBestNode = -1;
        for (int i = 0; i < numNodes - 1; i++) {
            // Step2.1 find the best next node.
            tempMinDistance = Integer.MAX_VALUE;
            for (int j = 0; j < numNodes; j++) {
                //This node is visited.
                if (tempVisitedArray[j]) {
                    continue;
                }// Of if

                if (tempMinDistance > tempDistanceArray[j]) {
                    tempMinDistance = tempDistanceArray[j];
                    tempBestNode = j;
                }// Of if
            }// Of for j

            tempVisitedArray[tempBestNode] = true;

            // Step2.2 Prepare for the next round.
            for (int j = 0; j < numNodes; j++) {
                // This node is visited.
                if (tempVisitedArray[j]) {
                    continue;
                }// Of if

                // This node cannot reach.
                if (weightMatrix.getData()[tempBestNode][j] >= MAX_DISTANCE) {
                    continue;
                }// Of if

                if (tempDistanceArray[j] > tempDistanceArray[tempBestNode] + weightMatrix.getData()[tempBestNode][j]) {
                    // Change distance.
                    tempDistanceArray[j] = tempDistanceArray[tempBestNode] + weightMatrix.getValue(tempBestNode, j);

                    // Change parent.
                    tempParentArray[j] = tempBestNode;
                }// Of if
            }// Of for j

            // For test
            System.out.println("The distance to each node :" + Arrays.toString(tempDistanceArray));
            System.out.println("The parent of each node :" + Arrays.toString(tempParentArray));
        }//Of for i

        // Step3. Output for debug.
        System.out.println("Finally");
        System.out.println("The distance to each node :" + Arrays.toString(tempDistanceArray));
        System.out.println("The parent of each node :" + Arrays.toString(tempParentArray));
        return tempDistanceArray;
    }// Of dijkstra

    /**
     * The minimal spanning tree.
     *
     * @return The total cost of the tree.
     */
    public int prim() {
        // Step1. Initialize.
        // Any node can be the source.
        int tempSource = 0;
        int[] tempDistanceArray = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            tempDistanceArray[i] = weightMatrix.getValue(tempSource, i);
        }// Of for i

        int[] tempParentArray = new int[numNodes];
        Arrays.fill(tempParentArray, tempSource);
        //-1 for no parent.
        tempParentArray[tempSource] = -1;

        // Visited nodes will not be considered further.
        boolean[] tempVisitedArray = new boolean[numNodes];
        tempVisitedArray[tempSource] = true;

        //Step2. Main loops.
        int tempMinDistance;
        int tempBestNode = -1;
        for (int i = 0; i < numNodes - 1; i++) {
            // Step2.1 find the best next node.
            tempMinDistance = Integer.MAX_VALUE;
            for (int j = 0; j < numNodes; j++) {
                //This node is visited.
                if (tempVisitedArray[j]) {
                    continue;
                }// Of if

                if (tempMinDistance > tempDistanceArray[j]) {
                    tempMinDistance = tempDistanceArray[j];
                    tempBestNode = j;
                }// Of if
            }// Of for j

            tempVisitedArray[tempBestNode] = true;

            // Step2.2 Prepare for the next round.
            for (int j = 0; j < numNodes; j++) {
                // This node is visited.
                if (tempVisitedArray[j]) {
                    continue;
                }// Of if

                // This node cannot reach.
                if (weightMatrix.getData()[tempBestNode][j] >= MAX_DISTANCE) {
                    continue;
                }// Of if

                if (tempDistanceArray[j] > weightMatrix.getData()[tempBestNode][j]) {
                    // Change distance.
                    tempDistanceArray[j] = weightMatrix.getValue(tempBestNode, j);

                    // Change parent.
                    tempParentArray[j] = tempBestNode;
                }// Of if
            }// Of for j

            // For test
            System.out.println("The selected distance for each node: " + Arrays.toString(tempDistanceArray));
            System.out.println("The parent of each node: " + Arrays.toString(tempParentArray));
        }//Of for i

        int resultCost = 0;
        for (int i = 0; i < numNodes; i++) {
            resultCost += tempDistanceArray[i];
        }// Of for i

        // Step3. Output for debug.
        System.out.println("Finally");
        System.out.println("The parent of each node :" + Arrays.toString(tempParentArray));
        System.out.println("The total cost :" + resultCost);

        return resultCost;
    }// Of prim

    /**
     * Critical path. Net validity checks such as loop check not implemented.
     * The source should be 0 and the destination should be n-1.
     *
     * @return The node sequence of the path.
     */
    public boolean[] criticalPath() {
        // One more value to save simple computation.
        int tempVale;

        // Step 1. The in-degree of each node.
        int[] tempInDegree = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (weightMatrix.getValue(i, j) != -1) {
                    tempInDegree[j]++;
                }// Of if
            }// Of for j
        }// Of for i
        System.out.println("In-degree of nodes: " + Arrays.toString(tempInDegree));

        // Step 2. Topology sorting.
        int[] tempEarliestTimeArray = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            // This node cannot be removed.
            if (tempInDegree[i] > 0) {
                continue;
            }// Of if

            System.out.println("Removing " + i);

            for (int j = 0; j < numNodes; j++) {
                if (weightMatrix.getValue(i, j) != -1) {
                    tempVale = tempEarliestTimeArray[i] + weightMatrix.getValue(i, j);
                    if (tempEarliestTimeArray[j] < tempVale) {
                        tempEarliestTimeArray[j] = tempVale;
                    }// Of if
                    tempInDegree[j]--;
                }// Of if
            }// Of for j
        }// Of for i

        System.out.println("Earliest start time : " + Arrays.toString(tempEarliestTimeArray));

        // Step 3. The out-degree of each node.
        int[] tempOutDegree = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (weightMatrix.getValue(i, j) != -1) {
                    tempOutDegree[i]++;
                }// Of if
            }// Of for j
        }// Of for i
        System.out.println("Out-degree of nodes: " + Arrays.toString(tempOutDegree));

        // Step 4. Reverse the topology sorting.
        int[] tempLatestTimeArray = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            tempLatestTimeArray[i] = tempEarliestTimeArray[numNodes - 1];
        }// Of for i

        for (int i = numNodes-1; i >=0; i--) {
            // This node cannot be removed.
            if (tempOutDegree[i] != 0) {
                continue;
            }// Of if

            System.out.println("Removing " + i);

            for (int j = 0; j < numNodes; j++) {
                if (weightMatrix.getValue(j, i) != -1) {
                    tempVale = tempLatestTimeArray[i] - weightMatrix.getValue(j, i);
                    if (tempLatestTimeArray[j] > tempVale) {
                        tempLatestTimeArray[j] = tempVale;
                    }// Of if
                    tempOutDegree[j]--;
                    System.out.println("The out-degree of " + j + " decreases by 1.");
                }// Of if
            }// Of for j
        }// Of for i

        System.out.println("Latest start time :" + Arrays.toString(tempLatestTimeArray));

        boolean[] resultCriticalArray = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++) {
            if (tempLatestTimeArray[i] == tempEarliestTimeArray[i]) {
                resultCriticalArray[i] = true;
            }// Of if
        }// Of for i

        System.out.println("Critical array: " + Arrays.toString(resultCriticalArray));
        System.out.print("Critical nodes:");
        for (int i = 0; i < numNodes; i++) {
            if (resultCriticalArray[i]) {
                System.out.print(" " + i);
            }// Of if
        }// Of for i
        System.out.println();

        return resultCriticalArray;
    }// Of criticalPath

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        Net tempNet0 = new Net(3);
        System.out.println(tempNet0);

        int[][] tempMatrix = {{0, 9, 3, 6}, {5, 0, 2, 4}, {3, 2, 0, 1}, {2, 8, 7, 0}};
        Net tempNet1 = new Net(tempMatrix);
        System.out.println(tempNet1);

        // Dijkstra
        tempNet1.dijkstra(0);

        // An undirected net is required.
        int[][] tempMatrix2 = {{0, 7, MAX_DISTANCE, 5, MAX_DISTANCE}, {7, 0, 8, 9, 7},
                {MAX_DISTANCE, 8, 0, MAX_DISTANCE, 5}, {5, 9, MAX_DISTANCE, 0, 15},
                {MAX_DISTANCE, 7, 5, 15, 0}};
        Net tempNet2 = new Net(tempMatrix2);
        tempNet2.prim();

        // A directed net without loop is required.
        // Node cannot reach itself. It is indicated by -1.
        int[][] tempMatrix3 = {{-1, 3, 2, -1, -1, -1}, {-1, -1, -1, 2, 3, -1},
                {-1, -1, -1, 4, -1, 3}, {-1, -1, -1, -1, -1, 2}, {-1, -1, -1, -1, -1, 1},
                {-1, -1, -1, -1, -1, -1}};

        Net tempNet3 = new Net(tempMatrix3);
        System.out.println("-------critical path");
        tempNet3.criticalPath();
    }// Of main
}// Of class Net

