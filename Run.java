import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String s;

        while ((s = scanner.nextLine()) != null) {

            try {
                if (s.equals("")) {
                    throw new IllegalStateException("Empty line!");
                }

                ANTLRInputStream input = new ANTLRInputStream(s);
                CalculatorLexer lexer = new CalculatorLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                CalculatorParser parser = new CalculatorParser(tokens);
                ParseTree tree = parser.input();

//                ParseTreeWalker walker = new ParseTreeWalker();
//                CalculatorListener listener = new CalculatorPrintEverything();
//                walker.walk(listener, tree);

                CalculatorBaseVisitorImpl calcVisitor;
                Value result;


                calcVisitor = new CalculatorBaseVisitorImpl();
                result = calcVisitor.visit(tree);

                NumberFormat formatter = NumberFormat.getNumberInstance(Locale.ROOT);
                DecimalFormat dformatter = (DecimalFormat) formatter;
                dformatter.applyPattern("0.00");

                if (result.isDouble()) {
                    System.out.println(result.asDouble());
                } else {
                    double[][] d = result.asMatrix().getArray();
                    for (int i = 0; i < d.length; i++) {
                        for (int j = 0; j < d[0].length; j++) {
                            System.out.print(dformatter.format(d[i][j]) + " ");
                        }
                        System.out.println();
                    }
                }

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("This variable has not initialized yet!");
            } catch (IllegalArgumentException e) {
                System.out.println("This operation is wrong!");
            }
        }
    }
}
