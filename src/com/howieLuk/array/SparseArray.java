package com.howieLuk.array;

import java.util.Scanner;

/**
 * @Deacription 稀疏数组问题
 * @Author HowieLuk
 * @Date 2023/12/29 14:44
 * @Version 1.0
 **/
public class SparseArray {
    private static class Gobang {
        private static final int GOBANG_SIZE = 3;
        public static final int WHITE = 1;
        public static final int BLACK = 2;
        private final SparseArray chessArr = new SparseArray(GOBANG_SIZE, GOBANG_SIZE);

        /**
         * -1: 暂无胜负
         * 0: 平局
         * 1: 黑胜
         * 2: 白胜
         * @return 胜负关系
         */
        private int judge() {
            return -1;
        }

        private int setChess(int chess, Scanner scanner) {
            String side = chess == BLACK ? "黑方" : "白方";
            System.out.print(side + "下的位置，格式：x y（输入-1退出游戏）:");
            String posStr = scanner.nextLine();
            if (posStr.equals("-1")) {
                return -1;
            }
            String [] pos = posStr.split(" ");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            if (chessArr.get(x, y) == 0) {
                chessArr.set(x, y, chess);
            } else {
                System.out.println(side + "下错位置！");
                return 2;
            }
            return 1;
        }

        public void startGame() {
            System.out.println("==========开始五子棋==========");
            boolean black = true; // 黑方先走
            Scanner scanner = new Scanner(System.in);
            boolean quit = false;
            while (!quit && judge() == -1) {
                printChess();
                System.out.println("==============================");
                int flag;
                if (black) {
                    flag = setChess(BLACK, scanner);
                    if (flag == 1) {
                        black = false;
                    }
                } else {
                    flag = setChess(WHITE, scanner);
                    if (flag == 1) {
                        black = true;
                    }
                }
                if (flag == -1) {
                    quit = true;
                }
                System.out.println();
            }
            scanner.close();
            System.out.println("==========游戏结束==========");
        }

        public void printChess() {
            for (int i = 0; i < GOBANG_SIZE; i++) {
                for (int j = 0; j < GOBANG_SIZE; j++) {
                    int chess = chessArr.get(i, j);
                    String chessStr = "□";
                    if (chess == BLACK) {
                        chessStr = "●";
                    } else if (chess == WHITE) {
                        chessStr = "○";
                    }
                    System.out.print(chessStr + "\t");
                }
                System.out.println();
            }
        }

    }

    public static final int X = 0;

    public static final int Y = 1;

    public static final int VAL = 2;

    public static final int INIT_SIZE = 10;

    private int [][] sparseArray;

    private int xSize;

    private int ySize;

    private int top;

    private int nullVal;

    public SparseArray(int x, int y) {
        this(x, y, 0);
    }

    public SparseArray(int x, int y, int nullVal) {
        sparseArray = new int[INIT_SIZE][3];
        xSize = x;
        ySize = y;
        top = -1;
        this.nullVal = nullVal;
    }

    private void add(int x, int y, int val) {
        top+=1;
        if (top == sparseArray.length) {
            int[][] newArray = new int[sparseArray.length + INIT_SIZE][3];
            for (int i = 0; i < sparseArray.length; i++) {
                System.arraycopy(sparseArray[i], 0, newArray[i], 0, sparseArray[i].length);
            }
            sparseArray = newArray;
        }
        sparseArray[top][X] = x;
        sparseArray[top][Y] = y;
        sparseArray[top][VAL] = val;
    }

    public void set(int x, int y, int val) {
        if (x >= xSize || y >= ySize) {
            throw new ArrayIndexOutOfBoundsException("Array index out of range(" + xSize + "," + ySize + "): "
                    + "(" + x + "," + y + ")");
        }
        for (int i = 0; i <= top; i++) {
            if (sparseArray[i][X] == x && sparseArray[i][Y] == y) {
                sparseArray[i][X] = x;
                sparseArray[i][Y] = y;
                sparseArray[i][VAL] = val;
                return;
            }
        }
        add(x, y, val);
    }

    public int get(int x, int y) {
        for (int[] elem : sparseArray) {
            if (elem[X] == x && elem[Y] == y) {
                return elem[VAL];
            }
        }
        return nullVal;
    }

    /**
     * 打印二维数组
     */
    public void printArray() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                System.out.print(get(i, j) + "\t");
            }
            System.out.println();
        }
    }

    public int[] indexOf(int val) {
        if (val == nullVal) {
            return null;
        }
        for (int[] elem : sparseArray) {
            if (elem[VAL] == val) {
                return new int[]{elem[X], elem[Y]};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Gobang gobang = new Gobang();
        gobang.startGame();
    }
}
