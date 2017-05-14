import Jama.Matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorBaseVisitorImpl extends CalculatorBaseVisitor<Matrix> {

    @Override
    public Matrix visitPlus(CalculatorParser.PlusContext ctx) {
        return visit(ctx.plusOrMinus()).plus(visit(ctx.multOrDiv()));
    }

    @Override
    public Matrix visitMinus(CalculatorParser.MinusContext ctx) {
        return visit(ctx.plusOrMinus()).minus(visit(ctx.multOrDiv()));
    }

    @Override
    public Matrix visitMultiplication(CalculatorParser.MultiplicationContext ctx) {

        double[][] matrix = visit(ctx.multOrDiv()).getArray();

        if ((matrix.length == 1)&& (matrix[0].length == 1)){
            return visit(ctx.pow()).times(matrix[0][0]);
        }

        matrix = visit(ctx.pow()).getArray();

        if ((matrix.length == 1)&&(matrix[0].length == 1)){
            return visit(ctx.multOrDiv()).times(matrix[0][0]);
        }

        return visit(ctx.multOrDiv()).times(visit(ctx.pow()));
    }

    @Override
    public Matrix visitDivision(CalculatorParser.DivisionContext ctx) {
        return visit(ctx.multOrDiv()).times(visit(ctx.pow()));
    }

    @Override
    public Matrix visitToSetVar(CalculatorParser.ToSetVarContext ctx) {
        Matrix value = visit(ctx.plusOrMinus());
        Storage.set(ctx.ID().getText(), value);
        return value;
    }

//    @Override
//    public Double visitSetVariable(CalculatorParser.SetVariableContext ctx) {
//
//    }

    @Override
    public Matrix visitPower(CalculatorParser.PowerContext ctx) {
        if (ctx.TRANSP() != null)
            return visit(ctx.unaryMinus()).transpose();
        return visit(ctx.unaryMinus());
    }

    @Override
    public Matrix visitRunk(CalculatorParser.RunkContext ctx) {

        int runk = visit(ctx.plusOrMinus()).rank();
        double[][] runkArray = new double[1][1];
        runkArray[0][0] = runk;
        Matrix runkMatrix = new Matrix(runkArray);

        return runkMatrix;
    }

    @Override
    public Matrix visitChangeSign(CalculatorParser.ChangeSignContext ctx) {
        return visit(ctx.unaryMinus()).times(-1);
    }

    @Override
    public Matrix visitBraces(CalculatorParser.BracesContext ctx) {
        return visit(ctx.plusOrMinus());
    }


//    @Override
//    public Double visitInt(CalculatorParser.IntContext ctx) {
//        return Double.parseDouble(ctx.INT().getText());
//    }

    @Override
    public Matrix visitMatrix(CalculatorParser.MatrixContext ctx) {

        String matrixString = ctx.MATRIX().getText();

        int row = -1, col = 0;

        // count rows
        for (int i = 0; i < matrixString.length(); i++) {
            if (matrixString.charAt(i) == '[') {
                row++;
            }
        }

        // write all members of matrix
        ArrayList<Double> myDoubles = new ArrayList<>();
        Matcher matcher = Pattern.compile("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?").matcher(matrixString);

        while (matcher.find()) {
            double element = Double.parseDouble(matcher.group());
            myDoubles.add(element);
        }

        // count columns
        col = myDoubles.size()/row;

        double[][] matrixArray = new double[row][col];

        // fill matrix array
        for (int i=0; i< matrixArray.length; i++){
            for (int j = 0; j<matrixArray[0].length; j++){
                matrixArray[i][j] = myDoubles.get(col*i+j);
            }
        }


        Matrix matrix = new Matrix(matrixArray);

        return matrix;
    }

    @Override
    public Matrix visitVariable(CalculatorParser.VariableContext ctx) {
        return Storage.get(ctx.ID().getText());
    }

//    @Override
//    public Double visitDouble(CalculatorParser.DoubleContext ctx) {
//        return Double.parseDouble(ctx.DOUBLE().getText());
//    }

    @Override
    public Matrix visitCalculate(CalculatorParser.CalculateContext ctx) {
        return visit(ctx.plusOrMinus());
    }
}
