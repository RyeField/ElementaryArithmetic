package sj;

import sj.BinaryTree.TreeNode;


/**
 * @author: Jian Shi
 * @email: shijianhzchina@gmail.com
 * @date: 2019/12/23 22:18
 */

public class Calculator {

    /**
     * Get the String format of the formula, Core part is the steps of
     * generating the parentheses "(" ")"
     * The rule is referenced from https://blog.csdn.net/kuku713/article/details/12684509
     *
     * @param node root of the binary tree
     * @return the final formula including the parentheses
     */
    public static String getFormula(TreeNode node) {
        if (node != null) {
            String leftValue = getFormula(node.left);
            String rightValue = getFormula(node.right);
            if (util.isNumeric(node.value)) {
                return node.value;
            } else if (node.value.equals("/")) {
                if (node.left.value.equals("+") || node.left.value.equals("-")) {
                    leftValue = "(" + leftValue + ")";
                }
                if (!util.isNumeric(node.right.value)) {
                    rightValue = "(" + rightValue + ")";
                }
            } else if (node.value.equals("*")) {
                if (node.left.value.equals("+") || node.left.value.equals("-")) {
                    leftValue = "(" + leftValue + ")";
                }
                if (node.right.value.equals("+") || node.right.value.equals("-")) {
                    rightValue = "(" + rightValue + ")";
                }
            } else if (node.value.equals("-")) {
                if (node.right.value.equals("+") || node.right.value.equals("-")) {
                    rightValue = "(" + rightValue + ")";
                }
            }
            return leftValue + node.value + rightValue;
        }
        return "";
    }

    /**
     * Calculate the result of the generated formula
     *
     * @param node the root of the binary tree
     * @return the result of the formula
     * @throws ArithmeticException zero division exception
     */
    public static double getResult(TreeNode node) throws ArithmeticException {
        if (node != null) {
            double value1 = getResult(node.left);
            double value2 = getResult(node.right);
            switch (node.value) {
                case "+":
                    return value1 + value2;
                case "-":
                    return value1 - value2;
                case "*":
                    return value1 * value2;
                case "/":
                    if (value2 == 0){
                        throw new ArithmeticException();
                    }else{
                        return value1 / value2;
                    }
                default:
                    return Double.parseDouble(node.value);
            }
        }
        return 0;
    }
}
