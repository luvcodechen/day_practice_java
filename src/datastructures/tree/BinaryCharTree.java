package datastructures.tree;

import datastructures.queue.CircleIntQueue;
import datastructures.queue.CircleObjectQueue;
import datastructures.stack.ObjectStack;

import java.util.Arrays;

/**
 * ClassName: BinaryCharTree
 * Package: datastructures.tree
 * Description:Binary tree with char type elements.
 *
 * @Author: luv_x_c
 * @Create: 2023/4/26 16:57
 */
public class BinaryCharTree {
    /**
     * The value in char.
     */
    char value;

    /**
     * The left child.
     */
    BinaryCharTree leftChild;

    /**
     * The right child.
     */
    BinaryCharTree rightChild;

    /**
     * The first constructor.
     *
     * @param paraName The value.
     */
    public BinaryCharTree(char paraName) {
        value = paraName;
        leftChild = null;
        rightChild = null;
    }// Of the constructor

    /**
     * Manually construct a tree. Only for testing.
     */
    public static BinaryCharTree manualConstructTree() {
        // Step 1. Construct a tree with only one node.
        BinaryCharTree resultTree = new BinaryCharTree('a');

        // Step 2. Construct all nodes. The first node is the root.
        // BinaryCharTreeNode tempTreeA = resultTree.root;
        BinaryCharTree tempTreeB = new BinaryCharTree('b');
        BinaryCharTree tempTreeC = new BinaryCharTree('c');
        BinaryCharTree tempTreeD = new BinaryCharTree('d');
        BinaryCharTree tempTreeE = new BinaryCharTree('e');
        BinaryCharTree tempTreeF = new BinaryCharTree('f');
        BinaryCharTree tempTreeG = new BinaryCharTree('g');

        // Step 3. Link all nodes.
        resultTree.leftChild = tempTreeB;
        resultTree.rightChild = tempTreeC;
        tempTreeB.rightChild = tempTreeD;
        tempTreeC.leftChild = tempTreeE;
        tempTreeD.leftChild = tempTreeF;
        tempTreeD.rightChild = tempTreeG;

        return resultTree;
    }// Of manualConstructTree

    /**
     * ********************
     * Pre-order visit.
     * ********************
     */
    public void preOrderVisit() {
        System.out.print("" + value + " ");

        if (leftChild != null) {
            leftChild.preOrderVisit();
        } // Of if

        if (rightChild != null) {
            rightChild.preOrderVisit();
        } // Of if
    }// Of preOrderVisit

    /**
     * ********************
     * In-order visit.
     * ********************
     */
    public void inOrderVisit() {
        if (leftChild != null) {
            leftChild.inOrderVisit();
        } // Of if

        System.out.print("" + value + " ");

        if (rightChild != null) {
            rightChild.inOrderVisit();
        } // Of if
    }// Of inOrderVisit

    /**
     * ********************
     * Post-order visit.
     * ********************
     */
    public void postOrderVisit() {
        if (leftChild != null) {
            leftChild.postOrderVisit();
        } // Of if

        if (rightChild != null) {
            rightChild.postOrderVisit();
        } // Of if

        System.out.print("" + value + " ");
    }// Of postOrderVisit

    /**
     * In-order visit with stack.
     */
    public void inOrderVisitWithStack() {
        ObjectStack tempStack = new ObjectStack();
        BinaryCharTree tempNode = this;

        while (tempNode != null || !tempStack.isEmpty()) {
            if (tempNode != null) {
                tempStack.push(tempNode);
                tempNode = tempNode.leftChild;
            } else {
                tempNode = (BinaryCharTree) tempStack.pop();
                System.out.print("" + tempNode.value + " ");
                tempNode = tempNode.rightChild;
            }// Of if
        }// Of while
    }// Of inOrderVisitWithStack

    /**
     * Pre-order visit with stack.
     */
    public void preOrderVisitWithStack(){
        ObjectStack tempStack=new ObjectStack();
        BinaryCharTree tempNode=this;

        while (tempNode!=null||!tempStack.isEmpty()){
            if(tempNode!=null){
                System.out.print("" + tempNode.value + " ");
                tempStack.push(tempNode);
                tempNode=tempNode.leftChild;
            }else {
                tempNode=(BinaryCharTree)tempStack.pop();
                tempNode= tempNode.rightChild;
            }// Of if
        }// Of while
    }// of preOrderVisitWithStack

    /**
     * Post-order visit with stack.
     */
    public void postOrderVisitWithStack(){
        ObjectStack tempStack=new ObjectStack();
        BinaryCharTree tempNodeA=this;
        BinaryCharTree tempNodeB=null;

        while(tempNodeA!=null||!tempStack.isEmpty()){
            if(tempNodeA!=null){
                tempStack.push(tempNodeA);
                tempNodeA=tempNodeA.leftChild;
            }else {
                tempNodeA=(BinaryCharTree) tempStack.getTop();
                if(tempNodeA.rightChild!=null&&tempNodeB!=tempNodeA.rightChild){
                    tempNodeA=tempNodeA.rightChild;
                }else
                {
                    tempNodeA=(BinaryCharTree) tempStack.pop();
                    System.out.print("" + tempNodeA.value + " ");
                    tempNodeB=tempNodeA;
                    tempNodeA=null;
                }//Of if
            }//Of if
        }// Of while
    }//of postOrderVisitWithStack

    /**
     * ********************
     * Get the depth of the binary tree.
     *
     * @return The depth. It is 1 if there is only one node, i.e., the root.
     * ********************
     */
    public int getDepth() {
        // It is a leaf.
        if ((leftChild == null) && (rightChild == null)) {
            return 1;
        } // Of if

        // The depth of the left child.
        int tempLeftDepth = 0;
        if (leftChild != null) {
            tempLeftDepth = leftChild.getDepth();
        } // Of if

        // The depth of the right child.
        int tempRightDepth = 0;
        if (rightChild != null) {
            tempRightDepth = rightChild.getDepth();
        } // Of if

        // The depth should increment by 1.
        if (tempLeftDepth >= tempRightDepth) {
            return tempLeftDepth + 1;
        } else {
            return tempRightDepth + 1;
        } // Of if
    }// Of getDepth

    /**
     * ********************
     * Get the number of nodes.
     *
     * @return The number of nodes.
     * ********************
     */
    public int getNumNodes() {
        // It is a leaf.
        if ((leftChild == null) && (rightChild == null)) {
            return 1;
        } // Of if

        // The number of nodes of the left child.
        int tempLeftNodes = 0;
        if (leftChild != null) {
            tempLeftNodes = leftChild.getNumNodes();
        } // Of if

        // The number of nodes of the right child.
        int tempRightNodes = 0;
        if (rightChild != null) {
            tempRightNodes = rightChild.getNumNodes();
        } // Of if

        // The total number of nodes.
        return tempLeftNodes + tempRightNodes + 1;
    }// Of getNumNodes

    /**
     * The value of nodes according to breath first traversal.
     */
    char[] valuesArray;
    /**
     * The indices in the complete binary tree.
     */
    int[] indicesArray;

    /**
     * Convert the tree to data arrays, including a char array and an int array.
     * The results are stored in two member variables.
     */
    public void toDataArray() {
        // Initialize arrays.
        int tempLength = getNumNodes();

        valuesArray = new char[tempLength];
        indicesArray = new int[tempLength];
        int i = 0;

        // Traverse and convert at the same time.
        CircleObjectQueue tempQueue = new CircleObjectQueue();
        tempQueue.enqueue(this);
        CircleIntQueue tempIntQueue = new CircleIntQueue();
        tempIntQueue.enqueue(0);

        BinaryCharTree tempTree = (BinaryCharTree) tempQueue.dequeue();
        int tempIndex = tempIntQueue.dequeue();

        while (tempTree != null) {
            valuesArray[i] = tempTree.value;
            indicesArray[i] = tempIndex;
            i++;

            if (tempTree.leftChild != null) {
                tempQueue.enqueue(tempTree.leftChild);
                tempIntQueue.enqueue(tempIndex * 2 + 1);
            }// Of if

            if (tempTree.rightChild != null) {
                tempQueue.enqueue(tempTree.rightChild);
                tempIntQueue.enqueue(tempIndex * 2 + 2);
            }// Of if

            tempTree = (BinaryCharTree) tempQueue.dequeue();
            tempIndex = tempIntQueue.dequeue();
        }// Of while
    }// Of toDataArray

    /**
     * Convert the tree to data arrays, including a char array and an int array.
     * The results are stored in two member variables.
     */
    public void toDataArraysObjectQueue() {
        // Initialize arrays.
        int tempLength = getNumNodes();

        valuesArray = new char[tempLength];
        indicesArray = new int[tempLength];
        int i = 0;

        //Traverse and convert at the same time.
        CircleObjectQueue tempQueue = new CircleObjectQueue();
        tempQueue.enqueue(this);
        CircleObjectQueue tempIntQueue = new CircleObjectQueue();
        Integer tempIndexInteger = Integer.valueOf(0);
        tempIntQueue.enqueue(tempIndexInteger);

        BinaryCharTree tempTree = (BinaryCharTree) tempQueue.dequeue();
        int tempIndex = ((Integer) tempIntQueue.dequeue()).intValue();
        System.out.println("tempIndex = " + tempIndex);
        while (tempTree != null) {
            valuesArray[i] = tempTree.value;
            indicesArray[i] = tempIndex;
            i++;

            if (tempTree.leftChild != null) {
                tempQueue.enqueue(tempTree.leftChild);
                tempIntQueue.enqueue(Integer.valueOf(tempIndex * 2 + 1));
            }// Of if

            if (tempTree.rightChild != null) {
                tempQueue.enqueue(tempTree.rightChild);
                tempIntQueue.enqueue(Integer.valueOf(tempIndex * 2 + 2));
            }// Of if

            tempTree = (BinaryCharTree) tempQueue.dequeue();
            if (tempTree == null) {
                break;
            }// Of if

            tempIndex = ((Integer) ((Integer) tempIntQueue.dequeue()).intValue());
        }// Of while
    }// Of toDataArraysObjectQueue

    /**
     * The second constructor. The parameters must be correct since no validity
     *
     * @param paraDataArray    The array for data.
     * @param paraIndicesArray The array for indices.
     */
    public BinaryCharTree(char[] paraDataArray, int[] paraIndicesArray) {
        // Step1. Use a sequential list to store all nodes.
        int tempNumNodes = paraDataArray.length;
        BinaryCharTree[] tempAllNodes = new BinaryCharTree[tempNumNodes];
        for (int i = 0; i < tempNumNodes; i++) {
            tempAllNodes[i] = new BinaryCharTree(paraDataArray[i]);
        }// Of for i

        // Step2. Link these nodes.
        for (int i = 1; i < tempNumNodes; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println("indices " + paraIndicesArray[j] + " vs. " + paraIndicesArray[i]);
                if (paraIndicesArray[i] == paraIndicesArray[j] * 2 + 1) {
                    tempAllNodes[j].leftChild = tempAllNodes[i];
                    System.out.println("Linking " + j + " with " + i);
                    break;
                } else if (paraIndicesArray[i] == paraIndicesArray[j] * 2 + 2) {
                    tempAllNodes[j].rightChild = tempAllNodes[i];
                    System.out.println("Linking  " + j + " with " + i);
                    break;
                }// Of if
            }// Of j
        }// Of i

        // Step3. The root is the first node.
        value = tempAllNodes[0].value;
        leftChild = tempAllNodes[0].leftChild;
        rightChild = tempAllNodes[0].rightChild;
    }// Of the second constructor


    /**
     * ********************
     * The entrance of the program.
     *
     * @param args Not used now.
     *             ********************
     */
    public static void main(String[] args) {
        BinaryCharTree tempTree = manualConstructTree();
        System.out.println("\r\nPreorder visit:");
        tempTree.preOrderVisit();
        System.out.println("\r\nIn-order visit:");
        tempTree.inOrderVisit();
        System.out.println("\r\nPost-order visit:");
        tempTree.postOrderVisit();

        System.out.println("\r\n\r\nThe depth is: " + tempTree.getDepth());
        System.out.println("The number of nodes is: " + tempTree.getNumNodes());

        tempTree.toDataArray();
        System.out.println("The values are；" + Arrays.toString(tempTree.valuesArray));
        System.out.println("The indexes are；" + Arrays.toString(tempTree.indicesArray));

        tempTree.toDataArraysObjectQueue();
        System.out.println("Only object queue.");
        System.out.println("The values are: " + Arrays.toString(tempTree.valuesArray));
        System.out.println("The indices are: " + Arrays.toString(tempTree.indicesArray));

        char[] tempCharArray = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] tempIndicesArray = {0, 1, 2, 4, 5, 12};
        BinaryCharTree tempTree2 = new BinaryCharTree(tempCharArray, tempIndicesArray);

        System.out.println("\r\nPreorder visit:");
        tempTree2.preOrderVisit();
        System.out.println("\r\nIn-order visit:");
        tempTree2.inOrderVisit();
        System.out.println("\r\nPost-order visit:");
        tempTree2.postOrderVisit();

        System.out.println("\r\nIn-order visit with stack:");
        tempTree2.inOrderVisitWithStack();
        System.out.println("\r\nPre-order visit with stack:");
        tempTree2.preOrderVisitWithStack();
        System.out.println("\r\nPost-order visit with stack:");
        tempTree2.postOrderVisitWithStack();

    }// Of main

}// Of BinaryCharTree
