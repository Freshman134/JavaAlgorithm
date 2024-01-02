package com.howieLuk.stack;

import com.howieLuk.linkedList.MyLinkedList;
import com.howieLuk.queue.ArrayQueue;
import com.howieLuk.queue.Queue;

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
            if (!hasNext()) {
                throw new RuntimeException("错误表达式: " + execStr + "\tlocation:" + (i - 1));
            }
            boolean notHaveSpot = true;
            StringBuilder sb = new StringBuilder();
            // 判断是否是可能一个负数
            if (execStr.charAt(i) == '-' && isNumber(execStr.charAt(i + 1))) {
                sb.append(execStr.charAt(i++));
            }
            while (hasNext() && (isNumber(execStr.charAt(i)) ||
                    (execStr.charAt(i) == '.' && notHaveSpot))) {
                Character c = execStr.charAt(i);
                if (c == '.') {
                    notHaveSpot = false;
                }
                sb.append(c);
                i++;
            }
            try {
                return notHaveSpot ? new NumberNode(Integer.parseInt(sb.toString()), false)
                        : new NumberNode(Double.parseDouble(sb.toString()), true);
            } catch (NumberFormatException e) {
                throw new RuntimeException("错误表达式: " + execStr + "\tlocation:" + i);
            }
        }

        public Character nextOp() {
            if (!hasNext()) {
                throw new RuntimeException("错误表达式: " + execStr + "\tlocation:" + (i - 1));
            }
            Character c = execStr.charAt(i);
            if (hasOp(c)) {
                i++;
                return c;
            }
            throw new RuntimeException("错误表达式: " + execStr + "\tlocation:" + i);
        }

        private boolean isNumber(char c) {
            return c >= '0' && c <= '9';
        }

    }

    private static class NumberNode {
        Number num;
        boolean isDouble;

        public NumberNode(Number num, boolean isDouble) {
            this.num = num;
            this.isDouble = isDouble;
        }

        @Override
        public String toString() {
            return num.toString();
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
                Character priOp = operationStack.peek();
                if (checkPriority(priOp, c) >= 0) {
                    // calculate
                    operate();
                }
                operationStack.push(c);
                getNum = true;
            }
        }
        // 先把运算符栈中，位于队尾的优先级高的运算符先运算
        if (!operationStack.isEmpty()) {
            while (checkPriority(operationStack.peek(), operationStack.peek(operationStack.size() - 2)) > 0) {
                operate();
            }
            // 反转栈，因为需要从左至右计算
            numberArrayStack = reverse(numberArrayStack);
            operationStack = reverse(operationStack);
            while (!operationStack.isEmpty()) {
                Character op = operationStack.pop();
                NumberNode a = numberArrayStack.pop();
                NumberNode b = numberArrayStack.pop();
                operate(a, b, op, numberArrayStack);
            }
        }
        return numberArrayStack.pop().num;
    }

    private void operate() {
        NumberNode a = numberArrayStack.pop();
        NumberNode b = numberArrayStack.pop();
        char op = operationStack.pop();
        operate(b, a, op, numberArrayStack);
    }

    private static void operate(NumberNode a, NumberNode b, Character op, ArrayStack<NumberNode> numberNodeStack) {
        NumberNode node;
        if (!a.isDouble && !b.isDouble) {
            node = getNumberNodeBy(a.num.intValue(), b.num.intValue(), op);
        } else {
            node = getNumberNodeBy(a.num.doubleValue(), b.num.doubleValue(), op);
        }
        numberNodeStack.push(node);
    }

    private static NumberNode getNumberNodeBy(Integer a, Integer b, char op) {
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

    private static NumberNode getNumberNodeBy(Double a, Double b, char op) {
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

    private static <T> ArrayStack<T> reverse(ArrayStack<T> stack) {
        ArrayStack<T> newStack = new ArrayStack<>();
        for (int i = stack.size() - 1; i >= 0; i--) {
            newStack.push(stack.peek(i));
        }
        return newStack;
    }

    public static void main(String[] args) {
        StackCalculator calculator = new StackCalculator();
        System.out.println(calculator.exec("-10+10.00--10.*2+5/5"));
    }
}
