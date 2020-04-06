package sj;

import java.util.*;

/**
 * @author: Jian Shi
 * @email: shijianhzchina@gmail.com
 * @date: 2020/1/1 15:47
 */

public class BinaryTree {
    static class TreeNode {
        public String value;
        public TreeNode left;
        public TreeNode right;
        public int index;

        public TreeNode(String value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private int leafLoBound;
    private int leafHiBound;
    private int randomLeavesCount = 0;
    private int[] randomLeavesIndexes;
    public TreeNode root;

    public BinaryTree() {
        System.out.println(util.fullTreeLeavesNum);
        this.leafLoBound = util.fullTreeLeavesNum;
        this.leafHiBound = 2 * leafLoBound - 1;
        if (!util.isFullBinaryTree) {
            this.randomLeavesCount = util.operandCount - util.fullTreeLeavesNum;
            this.getRandomLeavesIndex();
        }
        this.root = this.insertLevelOrder(1);
    }

    /**
     * Generate the binary tree level by level
     *
     * @param count the node count start from 1 (root count start from 1)
     * @return the root of the tree
     */
    private TreeNode insertLevelOrder(int count) {
        //Generate the full binary tree (apart from the last level) first
        //Tree level which is not last of second last level is all operators
        TreeNode temp;
        if (count < this.leafLoBound) {
            temp = new TreeNode(util.getRandomOperator(), count);
            temp.left = insertLevelOrder(2 * count);
            temp.right = insertLevelOrder(2 * count + 1);
        } else {
            //last level or second last level nodes
            if (util.isFullBinaryTree) {
                temp = new TreeNode(String.valueOf(util.getRandomOperand()), count);
                temp.left = null;
                temp.right = null;
            } else {
                if (this.contains(count)) {
                    temp = new TreeNode(util.getRandomOperator(), count);
                    temp.left = new TreeNode(String.valueOf(util.getRandomOperand()), 2 * count);
                    temp.right = new TreeNode(String.valueOf(util.getRandomOperand()), 2 * count + 1);
                } else {
                    temp = new TreeNode(String.valueOf(util.getRandomOperand()), count);
                    temp.left = null;
                    temp.right = null;
                }
            }
        }
        return temp;
    }

    /**
     *  Helper function that generate the random leaves node index
     *  If the operandCount could not generate a FULL Binary Tree, then need
     *  to randomly generate a set of leaves index in the last level between
     *  the {leafLoBound, LeafHiBound }
     */
    private void getRandomLeavesIndex() {
        //Performance NOT that efficient, but a nice one line code
        randomLeavesIndexes = new Random().ints(leafLoBound, leafHiBound).distinct().limit(randomLeavesCount).toArray();
        //A O(n) solution
        /*Random random = new Random();
        randomLeavesIndexes = new int[randomLeavesCount];
        //create a array containing the numbers need to be chosen
        int[] temp = new int[util.fullTreeLeavesNum];
        int tempIndex= leafLoBound;
        for (int i = 0; i < util.fullTreeLeavesNum; i++) {
            temp[i] = tempIndex++;
        }
        //iterative through the array to get random numbers
        int tempCount = util.fullTreeLeavesNum;
        for (int k = 0; k < randomLeavesCount; k++) {
            int randomIndex = random.nextInt(tempCount);
            randomLeavesIndexes[k] = temp[randomIndex];
            temp[randomIndex] = temp[(tempCount--)-1];
        }*/
    }

    /**
     * Helper function to identify whether the node count is been chosen as
     * a leaf index in previous function
     *
     * @param num the node count passed in
     * @return true or false
     */
    private boolean contains(int num) {
        return Arrays.stream(randomLeavesIndexes).anyMatch(i -> i == num);
        /*Arrays.sort(randomLeavesIndexes);
        return Arrays.binarySearch(randomLeavesIndexes, num) >= 0;*/
    }
}
