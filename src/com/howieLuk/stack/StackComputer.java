package com.howieLuk.stack;

/**
 * @Deacription TODO
 * @Author HowieLuk
 * @Date 2024/1/1 11:41
 * @Version 1.0
 **/

public class StackComputer {

    private ArrayStack<Number> numberArrayStack = new ArrayStack<>();
    private ArrayStack<Character> operationStack = new ArrayStack<>();

    public Number exec(String str) {
        int start;
        boolean isOp = false;
        boolean notHaveSpot = true;
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= '0' && c <= '9')) {
                tmp.append(c);
            } else if (c == '.' && notHaveSpot) {
                tmp.append(c);
                notHaveSpot = false;
            } else {
                Number num = notHaveSpot ? Integer.parseInt(tmp.toString()) : Double.parseDouble(tmp.toString());
                numberArrayStack.push(num);
                operationStack.push(c);
                tmp = new StringBuffer();
            }
        }
        numberArrayStack.printList();
        operationStack.printList();
        return 0;
    }

    public static void main(String[] args) {
        StackComputer computer = new StackComputer();
        computer.exec("7+88+44444/44.44*10-12.00.122");
    }
}
