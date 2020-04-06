package sj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jian Shi
 * @email: shijianhzchina@gmail.com
 * @date: 2019/12/23 22:52
 */

public class Generator {

    //Formula Results
    private List<String> formulas = new ArrayList<>();
    private List<String> results = new ArrayList<String>();
    private int formulaCount = 1;

    public Generator(int upperBound, int lowerBound, int operandCount,
                     boolean isHaveAddition, boolean isHaveSubtraction,
                     boolean isHaveMultiplication, boolean isHaveDivision,
                     int formulaCount, int decimalPrecision) {
        util.upperBound = upperBound;
        util.lowerBound = lowerBound;
        util.operandCount = operandCount;
        util.treeNodeCount = 2 * operandCount - 1;
        util.isFullBinaryTree = util.isPowerOfTwo(operandCount);
        util.fullTreeLeavesNum = util.floorPowerOfTwo(operandCount);
        util.decimalPrecision = decimalPrecision;
        util.generateOperators(isHaveAddition, isHaveSubtraction,
                isHaveMultiplication, isHaveDivision);
        this.formulaCount = formulaCount;
        this.generateRandomFormulas();
    }

    private void generateRandomFormulas() {
        int count = 0;
        while (count < this.formulaCount) {
            try {
                BinaryTree binaryTree = new BinaryTree();
                this.formulas.add(Calculator.getFormula(binaryTree.root));
                this.results.add(String.format("%." + util.decimalPrecision + "f",
                        Calculator.getResult(binaryTree.root)));
            } catch (ArithmeticException e) {
                count--;
                System.out.println("Error in calculating the formula - Zero " +
                        "appears in denominator, skip and calculate the next one.");
            }
            count++;
        }
    }

    public List<String> getFormulas() {
        return this.formulas;
    }

    public List<String> getResults() {
        return this.results;
    }
}
