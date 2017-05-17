import Jama.Matrix;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorBaseVisitorImpl extends CalculatorBaseVisitor<Value> {

    @Override
    public Value visitPlus(CalculatorParser.PlusContext ctx) {

        Value value1 = visit(ctx.plusOrMinus());
        Value value2 = visit(ctx.mult());

        if ((value1.isDouble())&&(value2.isDouble())){
            Value sumInt = new Value(value1.asDouble() + value2.asDouble());
            return sumInt;
        }

        if ((value1.isDouble())||(value2.isDouble())){
            throw new IllegalStateException("In addition must be just matrices or ints!");
        }

        Value sum = new Value((value1.asMatrix()).plus(value2.asMatrix()));

        return sum;
    }

    @Override
    public Value visitMinus(CalculatorParser.MinusContext ctx) {

        Value value1 = visit(ctx.plusOrMinus());
        Value value2 = visit(ctx.mult());

        if ((value1.isDouble())&&(value2.isDouble())){
            Value subInt = new Value(value1.asDouble() - value2.asDouble());
            return subInt;
        }

        if ((value1.isDouble())||(value2.isDouble())){
            throw new IllegalStateException("In subtraction must be just matrices or ints!");
        }

        Value sub = new Value((value1.asMatrix()).minus(value2.asMatrix()));

        return sub;
    }

    @Override
    public Value visitMultiplication(CalculatorParser.MultiplicationContext ctx) {

        Value value1 = visit(ctx.mult());
        Value value2 = visit(ctx.transponation());
        Value valueMult;


        if ((value1.isDouble())&&(value2.isDouble())){
            valueMult = new Value(value1.asDouble() * value2.asDouble());
            return valueMult;
        }


        if (value1.isDouble()){
            valueMult = new Value(value2.asMatrix().times(value1.asDouble()));
            return valueMult;
        }

        if (value2.isDouble()){
            valueMult = new Value(value1.asMatrix().times(value2.asDouble()));
            return valueMult;
        }


        return new Value(visit(ctx.mult()).asMatrix().times(visit(ctx.transponation()).asMatrix()));
    }


    @Override
    public Value visitToSetVar(CalculatorParser.ToSetVarContext ctx) {

        Value value = visit(ctx.plusOrMinus());

        if (value!=null) {
            if (value.isMatrix()) {
                Matrix matrixValue = value.asMatrix();
                Storage.set(ctx.ID().getText(), matrixValue);
                return value;
            } else {
                throw new IllegalStateException("You can assign just matrices!");
            }
        } else {
            throw new IllegalStateException("Wrong input!");
        }
    }


    @Override
    public Value visitPower(CalculatorParser.PowerContext ctx) {

        if (ctx.TRANSP() != null) {

            Value value = new Value(visit(ctx.unaryMinus()).asMatrix().transpose());
            return value;
        }

        return visit(ctx.unaryMinus());
    }

    @Override
    public Value visitRunk(CalculatorParser.RunkContext ctx) {

        Value value = visit(ctx.plusOrMinus());

        if (value.isDouble()){
            throw new IllegalStateException("You can take rang just of matrix!");
        }

        Double runk = Double.valueOf(value.asMatrix().rank());

        return new Value(runk);
    }

    @Override
    public Value visitChangeSign(CalculatorParser.ChangeSignContext ctx) {

        Value value = visit(ctx.unaryMinus());

        if (value.isDouble()){
            return new Value((value.asDouble()*(-1)));
        }

        return new Value(value.asMatrix().times(-1));
    }

    @Override
    public Value visitBraces(CalculatorParser.BracesContext ctx) {
        return visit(ctx.plusOrMinus());
    }

    @Override
    public Value visitMatrix(CalculatorParser.MatrixContext ctx) {

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

        Value value = new Value(matrix);

        return value;
    }

    @Override public Value visitInteger(CalculatorParser.IntegerContext ctx) {
        return new Value(Double.parseDouble(ctx.INT().getText()));
    }

    @Override public Value visitDouble(CalculatorParser.DoubleContext ctx) {
        return new Value(Double.parseDouble(ctx.DOUBLE().getText()));
    }

    @Override
    public Value visitVariable(CalculatorParser.VariableContext ctx) {
        return new Value(Storage.get(ctx.ID().getText()));
    }


    @Override
    public Value visitCalculate(CalculatorParser.CalculateContext ctx) {
        return visit(ctx.plusOrMinus());
    }
}
