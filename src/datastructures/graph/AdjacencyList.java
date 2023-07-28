    package datastructures.graph;

    import datastructures.queue.CircleObjectQueue;

    /**
     * ClassName: AdjacencyList
     * Package: datastructures.graph
     * Description: Adjacency list for directed graph.
     *
     * @Author: luv_x_c
     * @Create: 2023/5/27 10:34
     */
    public class AdjacencyList {
        /**
         * An inner class for adjacent node.
         */
        static class AdjacencyNode {
            /**
             * The column index.
             */
            int column;

            /**
             * The next adjacent node.
             */
            AdjacencyNode next;

            /**
             * The first constructor.
             *
             * @param paraColumn The column.
             */
            public AdjacencyNode(int paraColumn) {
                column = paraColumn;
                next = null;
            }// Of AdjacencyNode
        }// Of class AdjacencyNode

        /**
         * The number of nodes. This number variables may be redundant since it is always equals to  header's length.
         */
        int numNodes;

        /**
         * The headers for each row.
         */
        AdjacencyNode[] header;

        /**
         * The first constructor.
         *
         * @param paraMatrix The matrix indicating the graph.
         */
        public AdjacencyList(int[][] paraMatrix) {
            numNodes = paraMatrix.length;

            // Step1. Initialize.
            AdjacencyNode tempPreciousNode, tempNode;
            header = new AdjacencyNode[numNodes];


            // Step2. Set the linklist.
            for (int i = 0; i < numNodes; i++) {
                header[i] = new AdjacencyNode(-1);
                tempPreciousNode = header[i];
                for (int j = 0; j < numNodes; j++) {
                    if (paraMatrix[i][j] == 0) {
                        continue;
                    }// Of if

                    // Link.
                    tempNode = new AdjacencyNode(j);
                    tempPreciousNode.next = tempNode;
                    tempPreciousNode = tempNode;
                }// Of for j
            }// Of for i
        }// Of class AdjacencyList

        @Override
        public String toString() {
            String resultString = "";

            AdjacencyNode tempNode;
            for (int i = 0; i < numNodes; i++) {
                tempNode = header[i].next;

                while (tempNode != null) {
                    resultString += " ( " + i + " , " + tempNode.column + " ) ";
                    tempNode = tempNode.next;
                }// Of while
                resultString += "\r\t";
            }// Of for i

            return resultString;
        }// Of toString

        /**
         * Breadth first traversal.
         *
         * @param paraStartIndex The start index.
         * @return The sequence of the visit.
         */
        public String breadthFirstTraversal(int paraStartIndex) {
            CircleObjectQueue tempQueue = new CircleObjectQueue();
            String resultString = "";

            boolean[] tempVisitArray = new boolean[numNodes];

            // Initialize the queue and visit.
            tempQueue.enqueue(paraStartIndex);
            int tempIndex;
            AdjacencyNode tempNode;
            while (!tempQueue.isEmpty()) {
                // Dequeue and visit.
                tempIndex = (int) tempQueue.dequeue();
                if (!tempVisitArray[tempIndex]) {
                    tempVisitArray[tempIndex] = true;
                    resultString += tempIndex;
                }// Of if
                // Enqueue
                tempNode = header[tempIndex].next;
                while (tempNode != null) {
                    // Visit
                    if (!tempVisitArray[tempNode.column]) {
                        tempQueue.enqueue(tempNode.column);
                    }// Of if

                    tempNode = tempNode.next;
                }// Of while
            }// Of while

            return resultString;
        }// Of breadthFirstTraversal

        /**
         * Unit test for breadthFirstTraversal.
         */
        public static void breadthFirstTraversalTest() {
            // Test an undirected graph.
            int[][] tempMatrix = {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};
            AdjacencyList tempAdjacencyList = new AdjacencyList(tempMatrix);
            System.out.println(tempAdjacencyList);

            String tempSequence = "";
            tempSequence = tempAdjacencyList.breadthFirstTraversal(1);

            System.out.println(tempSequence);
        }// Of breadthFirstTraversalTest

        /**
         * The entrance of the program.
         *
         * @param args Not used now.
         */
        public static void main(String[] args) {
            breadthFirstTraversalTest();
        }// Of main
    }// Of class AdjacencyList
