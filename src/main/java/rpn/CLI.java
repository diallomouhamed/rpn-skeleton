package rpn;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static final void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        System.out.println("About to evaluate '" + expression + "'");
        long result = evaluate(expression);
        System.out.println("> " + result);
    }

    static long evaluate(String expression) {
        Long tmp = 0l;
        String [] expressionSplit = expression.split(" ");
        Stack<Long> operandes = new Stack<Long>();

        for (int i = 0; i < expressionSplit.length; i++) {
            if(!estOperateur(expressionSplit[i])) {
                operandes.push(Long.parseLong(expressionSplit[i]));
            } else {
                tmp = calcule(expressionSplit[i], operandes.pop(), operandes.pop());
                operandes.push(tmp);
            }
        }

        return operandes.pop();
    }

    public static boolean estOperateur(String operateur) {
        if (operateur.equals("+") || operateur.equals("-") || operateur.equals("*") || operateur.equals("/")) {
            return true;
        }
        return false;
    }

    public static Long calcule(String operateur, Long operande1, Long operande2) {
        Long res = 0l;
        switch (operateur) {
            case "+":
                res = operande1+operande2;
                break;
            case "-":
                res = operande1-operande2;
                break;
            case "*":
                res = operande1*operande2;
                break;
            case "/":
                res = operande1/operande2;
                break;
            default:
                res = 0l;
        }
        return res;
    }
}
