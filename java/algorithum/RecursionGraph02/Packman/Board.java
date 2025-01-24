package algorithum.RecursionGraph02.Packman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    String[][] board;
    ArrayList<int[]> points;
    int TOTAL_PATH;
    int TOTAL_COIN_AMOUNT = 3;
    ArrayList<int[]> coinPoints = new ArrayList<>(TOTAL_COIN_AMOUNT);

    Board(int N) {
        int TWO_DIMENSION = 2;

        board = new String[N][N];
        points = new ArrayList();
        TOTAL_PATH = N * N;
        for (int i = 0; i < 3; i++) {
            points.add(new int[TWO_DIMENSION]);
        }
    }

    int[] getStartPoint() {
        return points.get(0);
    }

    int[] getCurrentPoint() {
        return points.get(1);
    }

    int[] getEndPoint() {
        return points.get(2);
    }

    void setStartPoint() {
        Random random = new Random();

        int y = random.nextInt(board.length);
        int x = random.nextInt(board.length);
        points.set(0, new int[]{y, x});
        board[y][x] = "U";

        setCurrentPoint(y, x);
    }

    void setEndPoint() {
        Random random = new Random();

        while (true) {
            int y = random.nextInt(board.length);
            int x = random.nextInt(board.length);
            points.set(2, new int[]{y, x});

            int[] startPoint = getStartPoint();
            int MIN_PATH_LENGTH = 4;
            if ((Math.abs(startPoint[1] - x) + Math.abs(startPoint[0] - y) > MIN_PATH_LENGTH)) {
                board[y][x] = "G";
                break;
            }
        }
    }

    void setWall() {
        Random random = new Random();

        for (int pointCount = 0; pointCount < board.length * 3; pointCount++) {
            int x = random.nextInt(board.length);
            int y = random.nextInt(board.length);
            if (checkToBeEqual(y, x, "_")) {
                board[y][x] = "X";
                TOTAL_PATH--;
            }
        }
    }

    void setCurrentPoint(int y, int x) {
        int[] currentPoint = getCurrentPoint();
        currentPoint[0] = y;
        currentPoint[1] = x;
    }

    void makePath() {
        for (int row = 0; row < board.length; row++) {
            Arrays.fill(board[row], "_");
        }
    }

    void initPath() {
        makePath();
        setStartPoint();
        setEndPoint();
        setWall();
    }

    void showBoards() {
        System.out.println("=============");
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
        System.out.println("=============");
    }

    int length() {
        return board.length;
    }

    String getValueOf(int y, int x) {
        return board[y][x];
    }

    void setPointValue(int y, int x, String value) {
        board[y][x] = value;
    }

    boolean isEnd(boolean doesHaveKey) {
        int[] currentPoint = getCurrentPoint();
        int[] endPoint = getEndPoint();
        if (currentPoint[0] == endPoint[0] && currentPoint[1] == endPoint[1]) {
            if (doesHaveKey) {
                System.out.println("탈출에 성공했습니다!");
                return true;
            }
        }

        return false;
    }

    boolean checkToBeEqual(int y, int x, String value) {
        return (getValueOf(y, x).equalsIgnoreCase(value));
    }

    void addCoin(int y, int x) {
        coinPoints.add(new int[]{y, x});
    }

    int[] getCoin(int index) {
        return coinPoints.get(index);
    }

    void clearCoinPoint() {
        coinPoints.clear();
    }

    int getCoinAmount() {
        return coinPoints.size();
    }
}
