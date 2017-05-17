import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CalculatorPrintEverything implements CalculatorListener {

    @Override
    public void enterToSetVar(CalculatorParser.ToSetVarContext ctx) {
        System.err.println("entering ToSetVar");
    }

    @Override
    public void exitToSetVar(CalculatorParser.ToSetVarContext ctx) {
        System.err.println("exiting ToSetVar");
    }

    @Override
    public void enterCalculate(CalculatorParser.CalculateContext ctx) {
        System.err.println("entering Calculate");
    }

    @Override
    public void exitCalculate(CalculatorParser.CalculateContext ctx) {
        System.err.println("exiting Calculate");
    }

    @Override
    public void enterToMult(CalculatorParser.ToMultContext ctx) {
        System.err.println("entering ToMult");
    }

    @Override
    public void exitToMult(CalculatorParser.ToMultContext ctx) {
        System.err.println("exiting ToMult");
    }

    @Override
    public void enterPlus(CalculatorParser.PlusContext ctx) {
        System.err.println("entering Plus");
    }

    @Override
    public void exitPlus(CalculatorParser.PlusContext ctx) {
        System.err.println("exiting Plus");
    }

    @Override
    public void enterMinus(CalculatorParser.MinusContext ctx) {
        System.err.println("entering Minus");
    }

    @Override
    public void exitMinus(CalculatorParser.MinusContext ctx) {
        System.err.println("exiting Minus");
    }

    @Override
    public void enterMultiplication(CalculatorParser.MultiplicationContext ctx) {
        System.err.println("entering Multiplication");
    }

    @Override
    public void exitMultiplication(CalculatorParser.MultiplicationContext ctx) {
        System.err.println("exiting Multiplication");
    }

    @Override
    public void enterToTransponation(CalculatorParser.ToTransponationContext ctx) {
        System.err.println("entering ToTransponation");
    }

    @Override
    public void exitToTransponation(CalculatorParser.ToTransponationContext ctx) {
        System.err.println("exiting ToTransponation");
    }

    @Override
    public void enterRang(CalculatorParser.RangContext ctx) {
        System.err.println("entering Rang");
    }

    @Override
    public void exitRang(CalculatorParser.RangContext ctx) {
        System.err.println("exiting Rang");
    }

    @Override
    public void enterRunk(CalculatorParser.RunkContext ctx) {
        System.err.println("entering Runk");
    }

    @Override
    public void exitRunk(CalculatorParser.RunkContext ctx) {
        System.err.println("exiting Runk");
    }


    @Override
    public void enterPower(CalculatorParser.PowerContext ctx) {
        System.err.println("entering Power");
    }

    @Override
    public void exitPower(CalculatorParser.PowerContext ctx) {
        System.err.println("exiting Power");
    }

    @Override
    public void enterChangeSign(CalculatorParser.ChangeSignContext ctx) {
        System.err.println("entering ChangeSign");
    }

    @Override
    public void exitChangeSign(CalculatorParser.ChangeSignContext ctx) {
        System.err.println("exiting ChangeSign");
    }


    @Override
    public void enterToAtom(CalculatorParser.ToAtomContext ctx) {
        System.err.println("entering ToAtom");
    }

    @Override
    public void exitToAtom(CalculatorParser.ToAtomContext ctx) {
        System.err.println("exiting ToAtom");
    }

    @Override
    public void enterMatrix(CalculatorParser.MatrixContext ctx) {
        System.err.println("entering Matrix");
    }

    @Override
    public void exitMatrix(CalculatorParser.MatrixContext ctx) {
        System.err.println("exiting Matrix");
    }

    @Override
    public void enterInteger(CalculatorParser.IntegerContext ctx) {
        System.err.println("entering Integer");
    }

    @Override
    public void exitInteger(CalculatorParser.IntegerContext ctx) {
        System.err.println("exiting Integer");
    }

    @Override
    public void enterDouble(CalculatorParser.DoubleContext ctx) {
        System.err.println("entering Double");
    }

    @Override
    public void exitDouble(CalculatorParser.DoubleContext ctx) {
        System.err.println("exiting Double");
    }


    @Override
    public void enterVariable(CalculatorParser.VariableContext ctx) {
        System.err.println("entering Variable");
    }

    @Override
    public void exitVariable(CalculatorParser.VariableContext ctx) {
        System.err.println("exiting Variable");
    }

    @Override
    public void enterBraces(CalculatorParser.BracesContext ctx) {
        System.err.println("entering Braces");
    }

    @Override
    public void exitBraces(CalculatorParser.BracesContext ctx) {
        System.err.println("exiting Braces");
    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        System.err.println("terminal : " + terminalNode.getText());

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
