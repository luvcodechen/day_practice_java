package datastructures.graph;

/**
 * ClassName: OrthogonalList
 * Package: datastructures.graph
 * Description: Orthogonal List for directed graph.
 *
 * @Author: luv_x_c
 * @Create: 2023/5/28 14:53
 */
public class OrthogonalList {
    /**
     * An inner class for adjacent list.
     */
    static class OrthogonalNode {
        /**
         * The row index.
         */
        int row;

        /**
         * The column index.
         */
        int column;

        /**
         * The next out node.
         */
        OrthogonalNode nextOut;

        /**
         * The next in node.
         */
        OrthogonalNode nextIn;

        /**
         * The first constructor.
         *
         * @param paraRow    The row.
         * @param paraColumn The column.
         */
        public OrthogonalNode(int paraRow, int paraColumn) {
            row = paraRow;
            column = paraColumn;
            nextIn = null;
            nextOut = null;
        }// Of OrthogonalNode
    }// Of class OrthogonalNode

    /**
     * The number of nodes.
     */
    int numNodes;

    /**
     * The headers for each row.
     */
    OrthogonalNode[] headers;

    /**
     * The first constructor.
     *
     * @param paraMatrix The matrix indicting the graph.
     */
    public OrthogonalList(int[][] paraMatrix) {
        numNodes = paraMatrix.length;

        // Step1. Initialize. The data in the headers are not meaningful.
        OrthogonalNode tempPreciousNode, tempNode;

        headers = new OrthogonalNode[numNodes];

        // Step2. Link to its out nodes.
        for (int i = 0; i < numNodes; i++) {
            headers[i] = new OrthogonalNode(i, -1);
            tempPreciousNode = headers[i];
            for (int j = 0; j < numNodes; j++) {
                if (paraMatrix[i][j] == 0) {
                    continue;
                }// Of if

                // Create a new node.
                tempNode = new OrthogonalNode(i, j);

                // Link.
                tempPreciousNode.nextOut = tempNode;
                tempPreciousNode = tempNode;
            }// Of for j
        }// Of for i

        // Step3. Link to its in nodes.
        OrthogonalNode[] tempColumnNodes = new OrthogonalNode[numNodes];
        System.arraycopy(headers, 0, tempColumnNodes, 0, numNodes);

        for (int i = 0; i < numNodes; i++) {
            tempNode = headers[i].nextOut;
            while ((tempNode != null)) {
                tempColumnNodes[tempNode.column].nextIn = tempNode;
                tempColumnNodes[tempNode.column] = tempNode;

                tempNode = tempNode.nextOut;
            }// Of while
        }// Of for i
    }// Of the first constructor

    @Override
    public String toString() {
        String resultString = "Out arcs: ";

        OrthogonalNode tempNode;
        for (int i = 0; i < numNodes; i++) {
            tempNode = headers[i].nextOut;

            while (tempNode != null) {
                resultString += " (" + tempNode.row + ", " + tempNode.column + ")";
                tempNode = tempNode.nextOut;
            }// Of while
            resultString += "\r\n";
        }// Of for i

        resultString += "\r\nIn arcs: ";

        for (int i = 0; i < numNodes; i++) {
            tempNode = headers[i].nextIn;

            while (tempNode != null) {
                resultString += " (" + tempNode.row + ", " + tempNode.column + ")";
                tempNode = tempNode.nextIn;
            }// Of while
            resultString += "\r\n";
        }// Of for i
        return resultString;
    }// Of toString

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        int[][] tempMatrix = {{0, 1, 0, 0}, {0, 0, 0, 1}, {1, 0, 0, 0}, {0, 1, 1, 0}};
        OrthogonalList tempList = new OrthogonalList(tempMatrix);
        System.out.println("The data are:\r\n" + tempList);
    }// Of main
}// Of class OrthogonalList
