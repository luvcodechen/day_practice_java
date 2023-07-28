package datastructures.tree;

/**
 * ClassName: BinaryCharTree_my
 * Package: datastructures.tree
 * Description: Binary tree with char type elements.
 *
 * @Author: luv_x_c
 * @Create: 2023/4/25 14:44
 */
public class BinaryCharTree_my {
    /**
     * 二叉树的根节点
     */
    private BinaryTreeNode root;

    private static class BinaryTreeNode {
        /**
         * The value in char.
         */
        private char value;
        /**
         * The left child.
         */
        private BinaryTreeNode left;
        /**
         * The right child.
         */
        private BinaryTreeNode right;

        /**
         * A constructor.
         *
         * @param paraValue The given value.
         */
        public BinaryTreeNode(char paraValue) {
            this.value = paraValue;
            this.left = null;
            this.right = null;
        }//
    }// Of class BinaryTreeNode

    /**
     * 构造函数
     */
    public BinaryCharTree_my() {
        root = null;
    }

    /**
     * ********************
     * Manually construct a tree. Only for testing.
     * ********************
     */
    public static BinaryCharTree_my manualConstructTree() {
        // Step 1. Construct a null tree.
        BinaryCharTree_my resultTree = new BinaryCharTree_my();
        
        // Step 2. Construct all nodes. The first node is the root.
        BinaryTreeNode tempNodeA = new BinaryTreeNode('a');
        BinaryTreeNode tempTreeB = new BinaryTreeNode('b');
        BinaryTreeNode tempTreeC = new BinaryTreeNode('c');
        BinaryTreeNode tempTreeD = new BinaryTreeNode('d');
        BinaryTreeNode tempTreeE = new BinaryTreeNode('e');
        BinaryTreeNode tempTreeF = new BinaryTreeNode('f');
        BinaryTreeNode tempTreeG = new BinaryTreeNode('g');

        // Step 3. Link all nodes.
        tempNodeA.left = tempTreeB;
        tempNodeA.right = tempTreeC;
        tempTreeB.left = tempTreeD;
        tempTreeB.right = tempTreeE;
        tempTreeC.left = tempTreeF;
        tempTreeC.right = tempTreeG;

        resultTree.root = tempNodeA;
        return resultTree;
    }// Of manualConstructTree

    /**
     * 先序遍历
     */
    public void preOrderVisit() {
        preOrderVisit(root);
    }// Of preOrderVisit

    private void preOrderVisit(BinaryTreeNode paraRoot) {
        if (paraRoot != null) {
            System.out.print("" + paraRoot.value + " ");
            preOrderVisit(paraRoot.left);
            preOrderVisit(paraRoot.right);
        }// Of if
    }// Of preOrderVisit

    /**
     * 中序遍历
     */
    public void inOrderVisit() {
        inOrderVisit(root);
    }

    private void inOrderVisit(BinaryTreeNode paraRoot) {
        if (paraRoot != null) {
            inOrderVisit(paraRoot.left);
            System.out.print("" + paraRoot.value + " ");
            inOrderVisit(paraRoot.right);
        }// Of if
    }// Of inOrderVisit

    /**
     * 后序遍历
     */
    public void postOrderVisit() {
        postOrderVisit(root);
    }

    private void postOrderVisit(BinaryTreeNode paraRoot) {
        if (paraRoot != null) {
            postOrderVisit(paraRoot.left);
            postOrderVisit(paraRoot.right);
            System.out.print("" + paraRoot.value + " ");
        }// Of if
    }// Of postOrderVisit

    // 计算树的高度
    public int getDepth() {
        return getDepth(root);
    }

    private int getDepth(BinaryTreeNode paraRoot) {
        return paraRoot == null ? 0 : 1 + Math.max(getDepth(paraRoot.left), getDepth(paraRoot.right));
    }

    /**
     * Get the number of nodes.
     *
     * @return The number of nodes.
     */
    public int getNumNodes() {
        return getNumNodes(root);
    }

    private int getNumNodes(BinaryTreeNode paraRoot) {
        if (paraRoot == null) {
            return 0;
        } else {
            return 1 + getNumNodes(paraRoot.left) + getNumNodes(paraRoot.right);
        }
    }// Of getNumNodes

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        BinaryCharTree_my tree = manualConstructTree();
        System.out.println("The tree's depth is: " + tree.getDepth());

        System.out.print("preOrderVisit is: ");
        tree.preOrderVisit();
        System.out.println();

        System.out.print("inOrderVisit is: ");
        tree.inOrderVisit();
        System.out.println();

        System.out.print("postOrderVisit is: ");
        tree.postOrderVisit();

        System.out.println("\nThe number of nodes is: " + tree.getNumNodes());
    }// Of main
}// Of class BinaryCharTree_my
