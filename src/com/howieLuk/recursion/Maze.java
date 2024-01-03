package com.howieLuk.recursion;

import com.howieLuk.linkedList.MyLinkedList;
import com.howieLuk.linkedList.MyList;


/**
 * @Deacription 迷宫回溯
 * @Author HowieLuk
 * @Date 2024/1/3 19:23
 * @Version 1.0
 **/
public class Maze {
    private static final int SIZE = 8;
    private static final int[][] maze = new int[SIZE][SIZE];
    private static final int BLOCK = 1;
    private static final int HAD_READ = 2;
    private static final int[][] direction = new int[4][2];

    static {
        for (int i = 0; i < SIZE; i++) {
            maze[i][0] = BLOCK;
            maze[i][SIZE - 1] = BLOCK;
            maze[0][i] = BLOCK;
            maze[SIZE - 1][i] = BLOCK;
        }
        maze[3][1] = maze[3][2] = BLOCK;
        direction[0] = new int[]{1, 0};
        direction[1] = new int[]{0, 1};
        direction[2] = new int[]{-1, 0};
        direction[3] = new int[]{0, -1};
    }

    /**
     * 如果找到返回路径列表，否则返回null
     * @param enterI 初始位置i
     * @param enterJ 初始位置j
     * @param exitI  终点位置i
     * @param exitJ  终点位置j
     * @return
     */
    public static MyList<int[]> getWay(int enterI, int enterJ, int exitI, int exitJ) {
        if (enterI == exitI && enterJ == exitJ) {
            MyList<int[]> exit = new MyLinkedList<>();
            exit.add(new int[]{enterI, enterJ});
            return exit;
        }
        maze[enterI][enterJ] = HAD_READ;
        for (int i = 0; i < direction.length; i++) {
            int[] d = direction[i];
            int nextI = enterI + d[0];
            int nextJ = enterJ + d[1];
            if (maze[nextI][nextJ] != HAD_READ && maze[nextI][nextJ] != BLOCK) {
                MyList<int[]> path = getWay(nextI, nextJ, exitI, exitJ);
                if (path != null) {
                    path.insert(0, new int[]{enterI, enterJ});
                    return path;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MyList<int[]> list = getWay(1, 1, 6, 6);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < list.size(); i++) {
            int [] pos = list.get(i);
            System.out.println("["+ pos[0] + "," + pos[1] + "]");
        }
    }
}
