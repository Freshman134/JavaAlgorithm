package com.howieLuk.stack;

import com.howieLuk.linkedList.MyLinkedList;
import com.howieLuk.queue.ArrayQueue;
import com.howieLuk.queue.Queue;
import com.howieLuk.stack.utils.ExecScanner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Deacription 双栈计算器
 * @Author HowieLuk
 * @Date 2024/1/1 11:41
 * @Version 1.0
 **/

public class StackCalculator {

    private static final Map<Character, Integer> opPriority = new HashMap<>();

    static {
        opPriority.put('+', 0);
        opPriority.put('-', 0);
        opPriority.put('*', 1);
        opPriority.put('/', 1);
        opPriority.put(null, -1);
    }

    private ArrayStack<ExecScanner.NumberNode> numberArrayStack = new ArrayStack<>();
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
//                    operate();
                    // 先把运算符栈中队尾优先级高的运算符处理
                    operate();
                    // 反转栈，因为需要从左至右计算
                    if (!operationStack.isEmpty()) {
                        numberArrayStack = reverse(numberArrayStack);
                        operationStack = reverse(operationStack);
                        while (!operationStack.isEmpty()) {
                            Character op = operationStack.pop();
                            ExecScanner.NumberNode a = numberArrayStack.pop();
                            ExecScanner.NumberNode b = numberArrayStack.pop();
                            operate(a, b, op, numberArrayStack);
                        }
                    }
                }
                operationStack.push(c);
                getNum = true;
            }

        }
        while (!operationStack.isEmpty()) {
            operate();
        }
        return numberArrayStack.pop().getNum();
    }

    private void operate() {
        ExecScanner.NumberNode a = numberArrayStack.pop();
        ExecScanner.NumberNode b = numberArrayStack.pop();
        char op = operationStack.pop();
        operate(b, a, op, numberArrayStack);
    }

    private static void operate(ExecScanner.NumberNode a, ExecScanner.NumberNode b, Character op,
                                ArrayStack<ExecScanner.NumberNode> numberNodeStack) {
        ExecScanner.NumberNode node;
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

    private int checkPriority(Character op1, Character op2) {
        return opPriority.get(op1) - opPriority.get(op2);
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
        System.out.println(calculator.exec("10+7*4-6-10+10.00--10.*2+5/5+2/4-5.5/2+10+5"));
        System.out.println(calculator.exec("10+7*4-6-10+10--10*2+5/5+2/4-5.5/2+10+5"));
        System.out.println(calculator.exec("10+28-6-10+10--20+1+0.5-2.75+10+5"));
        System.out.println(calculator.exec("10-2*2+10+5"));
    }
}
