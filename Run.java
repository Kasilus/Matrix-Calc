import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String s;

        while ((s = scanner.nextLine()) != null) {

            ANTLRInputStream input = new ANTLRInputStream(s);
            CalculatorLexer lexer = new CalculatorLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CalculatorParser parser = new CalculatorParser(tokens);
            ParseTree tree = parser.input();

//            ParseTreeWalker walker = new ParseTreeWalker();
//            CalculatorListener listener = new CalculatorPrintEverything();
//            walker.walk(listener,tree);

            CalculatorBaseVisitorImpl calcVisitor;
            Value result;

            try {
                calcVisitor = new CalculatorBaseVisitorImpl();
                result = calcVisitor.visit(tree);

                if (result.isInteger()){
                    System.out.println(result.asInteger());
                } else {
                    double[][] d = result.asMatrix().getArray();
                    for (int i = 0; i < d.length; i++) {
                        for (int j = 0; j < d[0].length; j++) {
                            System.out.print(d[i][j] + " ");
                        }
                        System.out.println();
                    }
                }

            }catch (IllegalStateException e){
                System.out.println(e.getMessage());
            }catch (NullPointerException e){
                System.out.println("This variable has not initialized yet!");
            }



        }
    }
}
