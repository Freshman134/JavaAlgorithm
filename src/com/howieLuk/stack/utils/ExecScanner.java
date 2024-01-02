package com.howieLuk.stack.utils;

/**
 * @Deacription 表达式扫描器
 * @Author HowieLuk
 * @Date 2024/1/2 19:52
 * @Version 1.0
 **/
public class ExecScanner {

    public static class NumberNode {
        private final Number num;
        private final boolean isDouble;

        public NumberNode(Number num, boolean isDouble) {
            this.num = num;
            this.isDouble = isDouble;
        }

        public Number getNum() {
            return num;
        }

        public boolean isDouble() {
            return isDouble;
        }

        @Override
        public String toString() {
            return num.toString();
        }
    }

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

    public boolean nextIsLeftBracket() {
        return execStr.charAt(i) == '(';
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean hasOp(Character character) {
        return character == '+' || character == '-' || character == '*' || character == '/'
                || character == '(' || character == ')';
    }

}
