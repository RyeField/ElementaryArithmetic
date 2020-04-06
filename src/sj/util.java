package sj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: Jian Shi
 * @email: shijianhzchina@gmail.com
 * @date: 2020/1/1 15:55
 */

public class util {
    public static List<String> operators = new ArrayList<>();
    public static int operatorCount = 0;

    //Bound means operation interval, including result and operand.
    public static int upperBound = 100;
    public static int lowerBound = 0;
    //Number of operands
    public static int operandCount = 2;

    //Property of Binary Tree
    public static int treeNodeCount = 0;
    public static boolean isFullBinaryTree;
    public static int fullTreeLeavesNum = 0;

    //Others
    public static int decimalPrecision = 2;

    public static void generateOperators(boolean isHaveAddition,
                                         boolean isHaveSubtraction,
                                         boolean isHaveMultiplication,
                                         boolean isHaveDivision) {
        if (isHaveAddition) {
            operators.add("+");
            operatorCount++;
        }
        if (isHaveSubtraction) {
            operators.add("-");
            operatorCount++;
        }
        if (isHaveMultiplication) {
            operators.add("*");
            operatorCount++;
        }
        if (isHaveDivision) {
            operators.add("/");
            operatorCount++;
        }
    }

    public static String getRandomOperator() {
        Random random = new Random();
        return operators.get(random.nextInt(operatorCount));
    }

    public static int getRandomOperand() {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static boolean isPowerOfTwo(int num) {
        return (num & (num - 1)) == 0;
    }

    public static int floorPowerOfTwo(int num) {
        int floor = 1;
        while (floor <= num) {
            floor <<= 1;
        }
        return floor >> 1;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
