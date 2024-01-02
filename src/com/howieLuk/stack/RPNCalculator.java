package com.howieLuk.stack;

import com.howieLuk.stack.utils.ExecScanner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Deacription 逆波兰表达式计算器
 * @Author HowieLuk
 * @Date 2024/1/2 19:49
 * @Version 1.0
 **/
public class RPNCalculator {

    private static final Map<Character, Integer> opPriority = new HashMap<>();

    static {
        opPriority.put('+', 0);
        opPriority.put('-', 0);
        opPriority.put('*', 1);
        opPriority.put('/', 1);
        opPriority.put('(', -1);
        opPriority.put(null, -2);
    }

    public Number exec(String str) {
        // 中缀表达式转后缀表达式
        /*
        1+((2+3)*4)-5
        1 2 3 + 4 * + 5 -


        2 3 +
           +
        1     -
            *   5
           +  4
          2 3
       1 2 3 + 4 * 5 - +

       1*2-4
         *
       1   -
         2   4

         */
        ExecScanner scanner = new ExecScanner(str);
        ArrayStack<Object> s1 = new ArrayStack<>();
        ArrayStack<Character> s2 = new ArrayStack<>();
        boolean getNum = true;
        while (scanner.hasNext() || getNum) {
            if (getNum) {
                if (scanner.nextIsLeftBracket()) {
                    getNum = false;
                    continue;
                }
                s1.push(scanner.nextNumberNode());
                getNum = false;
            } else {
                Character c = scanner.nextOp();
                if (c == ')') {
                    boolean hadLeftBracket = false;
                    while (!s2.isEmpty()) {
                        Character popC = s2.pop();
                        if (popC == '(') {
                            hadLeftBracket = true;
                            break;
                        }
                        s1.push(popC);
                    }
                    if (!hadLeftBracket) {
                        throw new RuntimeException("表达式错误！:" + str);
                    }
                    continue;
                }
                if (checkPriority(c, s2.peek()) > 0 || c == '(') {
                    s2.push(c);
                } else {
                    s1.push(s2.pop());
                    s2.push(c);
                }
                getNum = true;
            }
        }
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return calculate(s1);
    }

    private Number calculate(ArrayStack<Object> stack) {
        ArrayStack<Object> queue = new ArrayStack<>();
        while (!stack.isEmpty()) {
            queue.push(stack.pop());
        }
        ArrayStack<ExecScanner.NumberNode> numberNodeStack = new ArrayStack<>();
        while (!queue.isEmpty()) {
            Object obj = queue.pop();
            if (obj instanceof ExecScanner.NumberNode) {
                numberNodeStack.push((ExecScanner.NumberNode)obj);
            } else {
                operate((Character)obj, numberNodeStack);
            }
        }
        return numberNodeStack.pop().getNum();
    }

    private void operate(Character op, ArrayStack<ExecScanner.NumberNode> numberNodeStack) {
        ExecScanner.NumberNode node;
        ExecScanner.NumberNode b = numberNodeStack.pop();
        ExecScanner.NumberNode a = numberNodeStack.pop();
        if (!a.isDouble() && !b.isDouble()) {
            node = getNumberNodeBy(a.getNum().intValue(), b.getNum().intValue(), op);
        } else {
            node = getNumberNodeBy(a.getNum().doubleValue(), b.getNum().doubleValue(), op);
        }
        numberNodeStack.push(node);
    }

    private static ExecScanner.NumberNode getNumberNodeBy(Integer a, Integer b, char op) {
        int res = 0;
        switch (op) {
            case '+': res = a + b; break;
            case '-': res = a - b; break;
            case '*': res = a * b; break;
            case '/': {
                if (a % b == 0) {
                    res = a / b;
                    break;
                }
                double doubleRes = a.doubleValue() / b.doubleValue();
                return new ExecScanner.NumberNode(doubleRes, true);
            }
        }
        return new ExecScanner.NumberNode(res, false);
    }

    private static ExecScanner.NumberNode getNumberNodeBy(Double a, Double b, char op) {
        double res = 0;
        switch (op) {
            case '+': res = a + b; break;
            case '-': res = a - b; break;
            case '*': res = a * b; break;
            case '/': res = a / b; break;
        }
        return new ExecScanner.NumberNode(res, true);
    }

    private static int checkPriority(Character op1, Character op2) {
        return opPriority.get(op1) - opPriority.get(op2);
    }

    public static void main(String[] args) {
        String test = "1+((2+3)*4)-5";
        RPNCalculator calculator = new RPNCalculator();
        System.out.println(calculator.exec(test));
        System.out.println(calculator.exec("10+7*4-6-10+10.00--10.*2+5/5+2/4-5.5/2+10+5"));
        System.out.println(calculator.exec("10+7*4-6-10+10--10*2+5/5+2/4-5.5/2+10+5"));
        System.out.println(calculator.exec("10+28-6-10+10--20+1+0.5-2.75+10+5"));
        System.out.println(calculator.exec("10-2*2+10+5"));
    }

}
