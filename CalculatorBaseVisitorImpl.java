import java.util.HashMap;

public class CalculatorBaseVisitorImpl extends CalculatorBaseVisitor<Double> {

    @Override
    public Double visitPlus(CalculatorParser.PlusContext ctx) {
        return visit(ctx.plusOrMinus()) + visit(ctx.multOrDiv());
    }

    @Override
    public Double visitMinus(CalculatorParser.MinusContext ctx) {
        return visit(ctx.plusOrMinus()) - visit(ctx.multOrDiv());
    }

    @Override
    public Double visitMultiplication(CalculatorParser.MultiplicationContext ctx) {
        return visit(ctx.multOrDiv()) * visit(ctx.pow());
    }

    @Override
    public Double visitDivision(CalculatorParser.DivisionContext ctx) {
        return visit(ctx.multOrDiv()) / visit(ctx.pow());
    }

    @Override
    public Double visitToSetVar(CalculatorParser.ToSetVarContext ctx) {
        Double value = visit(ctx.plusOrMinus());
        Storage.set(ctx.ID().getText(), value);
        return value;
    }

//    @Override
//    public Double visitSetVariable(CalculatorParser.SetVariableContext ctx) {
//
//    }

    @Override
    public Double visitPower(CalculatorParser.PowerContext ctx) {
        if (ctx.TRANSP() != null)
            return visit(ctx.unaryMinus()) * 2;
        return visit(ctx.unaryMinus());
    }

    @Override public Double visitRunk(CalculatorParser.RunkContext ctx){
        return visit(ctx.plusOrMinus())*3;
    }

    @Override
    public Double visitChangeSign(CalculatorParser.ChangeSignContext ctx) {
        return -1*visit(ctx.unaryMinus());
    }

    @Override
    public Double visitBraces(CalculatorParser.BracesContext ctx) {
        return visit(ctx.plusOrMinus());
    }

    @Override
    public Double visitConstantPI(CalculatorParser.ConstantPIContext ctx) {
        return Math.PI;
    }

    @Override
    public Double visitConstantE(CalculatorParser.ConstantEContext ctx) {
        return Math.E;
    }

    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.parseDouble(ctx.INT().getText());
    }

    @Override
    public Double visitVariable(CalculatorParser.VariableContext ctx) {
        return Storage.get(ctx.ID().getText());
    }

    @Override
    public Double visitDouble(CalculatorParser.DoubleContext ctx) {
        return Double.parseDouble(ctx.DOUBLE().getText());
    }

    @Override
    public Double visitCalculate(CalculatorParser.CalculateContext ctx) {
        return visit(ctx.plusOrMinus());
    }
}
