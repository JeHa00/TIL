package algorithum.RecursionGraph02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MakeOptimalTimeTable {
    static String[][] boards;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] isVisited;
    static int[] currentPoint; // row, column
    static int[] endPoint;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            initBoards();

            while (true) {
                showBoards();
                System.out.print("입력: ");
                String value = input.next();
                if (value.equalsIgnoreCase("R")) {
                    break;
                } else if (value.equalsIgnoreCase("W")) {
                    int[] nextPoint = new int[]{currentPoint[0] - 1, currentPoint[1]};
                    visit(nextPoint);
                } else if (value.equalsIgnoreCase("A")) {
                    int[] nextPoint = new int[]{currentPoint[0], currentPoint[1] - 1};
                    visit(nextPoint);
                } else if (value.equalsIgnoreCase("S")) {
                    int[] nextPoint = new int[]{currentPoint[0] + 1, currentPoint[1]};
                    visit(nextPoint);
                } else if (value.equalsIgnoreCase("D")) {
                    int[] nextPoint = new int[]{currentPoint[0], currentPoint[1] + 1};
                    visit(nextPoint);
                } else {
                    System.out.println("게임을 종료합니다.");
                    exit = true;
                    break;
                }

                if (isEnd()) {
                    exit = true;
                    break;
                }
            }
        }
    }

    public static void initBoards() {
        Scanner input = new Scanner(System.in);
        System.out.print("게임판의 길이 : ");
        int N = input.nextInt();
        int totalPath = 0;

        boolean exit = false;
        while (!exit) {
            boards = new String[N][N];
            isVisited = new boolean[N][N];
            currentPoint = new int[2];
            endPoint = new int[2];

            boolean alreadyHaveStart = false;
            boolean alreadyHaveEnd = false;

            String[] kindsOfPath = {"X", "_", "G", "U", "_"};
            for (int row = 0; row < N; row++) {
                for (int column = 0; column < N; column++) {
                    while (true) {
                        Random random = new Random();
                        String path = kindsOfPath[random.nextInt(5)];
                        if (path.equalsIgnoreCase("G") && !alreadyHaveEnd) {
                            boards[row][column] = path;
                            endPoint = new int[]{row, column};
                            alreadyHaveEnd = true;
                            break;
                        } else if (path.equalsIgnoreCase("U") && !alreadyHaveStart) {
                            boards[row][column] = path;
                            currentPoint = new int[]{row, column};
                            isVisited[row][column] = true;
                            alreadyHaveStart = true;
                            break;
                        } else if (boards[row][column] == null && (path.equalsIgnoreCase("X") || path.equals("_"))) {
                            boards[row][column] = path;
                            if (path.equals("_")) {
                                totalPath++;
                            }
                            break;
                        }
                    }
                }
            }
//            exit = dfs(currentPoint, 0, totalPath);
            exit = bfs();
        }
    }
//
//    public static boolean dfs(int[] currentPoint, int amount, int totalPath) {
//        if (amount == totalPath) {
//            max = (max == 0) ? amount : Math.max(max, amount);
//            System.out.println(1);
//            return (max == 0) ? false : true;
//        }
//
//        for (int i = 0; i < dx.length; i++) {
//            int ny = currentPoint[0] + dy[i];
//            int nx = currentPoint[1] + dx[i];
//
//            if (ny < 0 || nx < 0 || boards.length <= ny || boards.length <= nx || isVisited[ny][nx] || boards[ny][nx].equalsIgnoreCase("X")) {
//                continue;
//            }
//
//            if (boards[ny][nx].equalsIgnoreCase("G")) {
//                max = Math.max(max, amount);
//                System.out.println(2);
//                return true;
//            }
//
//            if (boards[ny][nx].equalsIgnoreCase("_")) {
//                isVisited[ny][nx] = true;
//                dfs(new int[]{ny, nx}, amount + 1, totalPath);
//                isVisited[ny][nx] = false;
//            }
//        }
//        return false;
//    }

    public static boolean bfs() {
        ArrayDeque<Integer[]> queue = new ArrayDeque<>();
        queue.offerFirst(new Integer[]{currentPoint[0], currentPoint[1]});
        int level = 0;

        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                Integer[] point = queue.pollLast();

                for (int j = 0; j < dx.length; j++) {
                    int ny = point[0] + dy[j];
                    int nx = point[1] + dx[j];

                    if (ny < 0 || nx < 0 || boards.length <= ny || boards.length <= nx || isVisited[ny][nx] || boards[ny][nx].equalsIgnoreCase("X")) {
                        continue;
                    }

//                    if (boards[ny][nx].equalsIgnoreCase("G") && level > 4) {
//                        return true;
//                    }
                    if (boards[ny][nx].equalsIgnoreCase("G")) {
                        return (level >= 5) ? true : false;
                    }

                    if (boards[ny][nx].equalsIgnoreCase("_")) {
                        isVisited[ny][nx] = true;
                        queue.offerFirst(new Integer[]{ny, nx}); // queue.offerFirst(point)를 왼쪽 코드로 수정했습니다.
                    }
                }
            }
            level++;
        }
        return true;
    }

    public static void showBoards() {
        System.out.println("=============");
        for (int row = 0; row < boards.length; row++) {
            for (int column = 0; column < boards[row].length; column++) {
                System.out.print(boards[row][column]);
            }
            System.out.println();
        }
        System.out.println("=============");
    }

    public static boolean canGoTo(int[] nextPoint) {
        int row = nextPoint[0];
        int column = nextPoint[1];

        if (row < 0 || boards.length <= row) {
            return false;
        }

        if (column < 0 || boards.length <= column) {
            return false;
        }

        if (boards[row][column].equalsIgnoreCase("X")) {
            return false;
        }

        return true;
    }

    public static void visit(int[] nextPoint) {
        if (canGoTo(nextPoint)) {
            boards[currentPoint[0]][currentPoint[1]] = "_";
            boards[nextPoint[0]][nextPoint[1]] = "U";
            currentPoint = nextPoint;
            return;
        }

        System.out.println("이동할 수 없습니다.");
    }

    public static boolean isEnd() {
        if (currentPoint[0] == endPoint[0] && currentPoint[1] == endPoint[1]) {
            System.out.println("축하합니다! 미로를 통과하셨습니다.");
            return true;
        }

        return false;
    }
}
