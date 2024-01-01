package com.howieLuk.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * @Deacription 双栈计算器
 * @Author HowieLuk
 * @Date 2024/1/1 11:41
 * @Version 1.0
 **/

public class StackCalculator {

    private static class ExecScanner {
        String execStr;
        int i = 0;

        public ExecScanner(String execStr) {
            this.execStr = execStr;
        }

        public boolean hasNext() {
            return i < execStr.length();
        }

        public NumberNode nextNumberNode() {
            Character nextC = execStr.charAt(i);
            StringBuilder sb = new StringBuilder();
            boolean notHaveSpot = true;
            do {
                Character c = nextC;
                if (c == '.') {
                    notHaveSpot = false;
                }
                sb.append(c);
                try {
                    nextC = execStr.charAt(++i);
                } catch (StringIndexOutOfBoundsException ig) {
                    break;
                }
            } while ((nextC >= '0' && nextC <= '9') || (notHaveSpot && nextC.equals('.')));
            return notHaveSpot ? new NumberNode(Integer.parseInt(sb.toString()), false)
                    : new NumberNode(Double.parseDouble(sb.toString()), true);
        }

        public Character nextOp() {
            Character c = execStr.charAt(i++);
            if (hasOp(c)) {
                return c;
            }
            throw new RuntimeException("表达式有误！没有操作符(" + c + "):" + execStr.substring(0, i - 1));
        }
    }

    private static class NumberNode {
        Number num;
        boolean isDouble;

        public NumberNode(Number num, boolean isDouble) {
            this.num = num;
            this.isDouble = isDouble;
        }
    }

    private static final Map<Character, Integer> opPriority = new HashMap<>();

    static {
        opPriority.put('+', 0);
        opPriority.put('-', 0);
        opPriority.put('*', 1);
        opPriority.put('/', 1);
        opPriority.put(null, -1);
    }

    private ArrayStack<NumberNode> numberArrayStack = new ArrayStack<>();
    private ArrayStack<Character> operationStack = new ArrayStack<>();

    public Number exec(String str) {
        ExecScanner exec = new ExecScanner(str);
        boolean getNum = true;
        while (exec.hasNext()) {
            if (getNum) {
                numberArrayStack.push(exec.nextNumberNode());
                getNum = false;
            } else {
                Character c = exec.nextOp();
                Character priOp = operationStack.peak();
                if (checkPriority(priOp, c) >= 0) {
                    // calculate
                    operate();
                }
                operationStack.push(c);
                getNum = true;
            }
        }

        while (!operationStack.isEmpty()) {
            operate();
        }
        return numberArrayStack.pop().num;
    }

    private void operate() {
        NumberNode a = numberArrayStack.pop();
        NumberNode b = numberArrayStack.pop();
        char op = operationStack.pop();
        NumberNode node;
        if (!a.isDouble && !b.isDouble) {
            node = getNumberNodeBy(b.num.intValue(), a.num.intValue(), op);
        } else {
            node = getNumberNodeBy(b.num.doubleValue(), a.num.doubleValue(), op);
        }
        numberArrayStack.push(node);
    }

    private NumberNode getNumberNodeBy(Integer a, Integer b, char op) {
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
                return new NumberNode(doubleRes, true);
            }
        }
        return new NumberNode(res, false);
    }

    private NumberNode getNumberNodeBy(Double a, Double b, char op) {
        double res = 0;
        switch (op) {
            case '+': res = a + b; break;
            case '-': res = a - b; break;
            case '*': res = a * b; break;
            case '/': res = a / b; break;
        }
        return new NumberNode(res, true);
    }

    private int checkPriority(Character op1, Character op2) {
        return opPriority.get(op1) - opPriority.get(op2);
    }

    private static boolean hasOp(Character c) {
        Integer i = opPriority.get(c);
        return i != null && i >= 0;
    }

    public static void main(String[] args) {
        StackCalculator computer = new StackCalculator();
        System.out.println(computer.exec("1+4/2*5"));
    }
}
