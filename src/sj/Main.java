package sj;


import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Parameters of generating the formulas
        int upperBound = 20;
        int lowerBound = 0;
        int operandCount = 5;
        boolean isHaveAddition = true;
        boolean isHaveSubtraction = true;
        boolean isHaveMultiplication = true;
        boolean isHaveDivision = true;
        int formulaCount = 1;
        int decimalPrecision = 3;

        //Store the generated results
        List<String> formulas;
        List<String> results;

        Generator generator = new Generator(upperBound, lowerBound,
                operandCount, isHaveAddition, isHaveSubtraction,
                isHaveMultiplication, isHaveDivision, formulaCount, decimalPrecision);

        formulas = generator.getFormulas();
        results = generator.getResults();

        for (int i = 0; i < formulaCount; i++) {
            System.out.println(formulas.get(i) + " = " + results.get(i));
        }

    }
}
