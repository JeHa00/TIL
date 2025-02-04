package algorithum.RecursionGraph02.Packman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Refactoring {

    static Board board;
    static boolean[][] isVisited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};
    static boolean doesHavePath;
    static int MAX_ATTEMPT = 100;

    public static void main(String[] args) {
        boolean result = makeBoard();

        while (result) {
            board.show();

            Scanner input = new Scanner(System.in);
            System.out.print("입력 : ");
            String direction = input.next();

            int[] current = board.getCurrent();

            if (direction.equalsIgnoreCase("W")) {
                visit(current[0] - 1, current[1]);
            } else if (direction.equalsIgnoreCase("A")) {
                visit(current[0], current[1] - 1);
            } else if (direction.equalsIgnoreCase("S")) {
                visit(current[0] + 1, current[1]);
            } else if (direction.equalsIgnoreCase("D")) {
                visit(current[0], current[1] + 1);
            } else if (direction.equalsIgnoreCase("R")) {
                System.out.println("게임을 리셋합니다.");
                result = makeBoard();
            } else if (direction.equalsIgnoreCase("Q")) {
                System.out.println("게임을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력값입니다.");
            }

            if (doesGetToGoal()) {
                break;
            }
        }

    }

    static boolean makeBoard() {
        Scanner input = new Scanner(System.in);
        System.out.print("게임판의 길이 : ");
        int N = input.nextInt();

        int attempt = 0;
        while (true) {
            board = new Board(N, N * 4);
            checkToConnectWithInit(board.getStart(), board.getEnd(), board.totalPath, 0);
            if (doesHavePath) {
                break;
            }
            attempt++;
            if (attempt > MAX_ATTEMPT) {
                System.out.println("총시도 횟수를 초과했습니다. 게임판 생성을 종료합니다. 프로그램을 재시작하세요.");
                return false;
            }
        }

        attempt = 0;
        while (true) {
            board.makeCoin(3);
            checkToConnectBetweenCoin();
            if (doesHavePath) {
                break;
            }

            board.removeCoin();
            attempt++;

            if (attempt > MAX_ATTEMPT) {
                System.out.println("총시도 횟수를 초과했습니다. 게임판 생성을 종료합니다. 프로그램을 재시작하세요.");
                return false;
            }
        }

        return true;
    }

    static void checkToConnectWithInit(int[] start, int[] end, int totalPath, int passedPath) {
        isVisited = new boolean[board.N][board.N];
        doesHavePath = false;
        checkToConnectBetween(start, end, totalPath, passedPath);
    }

    static void checkToConnectBetween(int[] start, int[] end, int totalPath, int passedPath) {
        if (doesHavePath || totalPath == passedPath) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = start[0] + dy[i];
            int nx = start[1] + dx[i];
            int[] current = new int[]{ny, nx};

            if (board.isOutBoardOrOnWall(ny, nx) || isVisited[ny][nx]) {
                continue;
            }

            if (Arrays.equals(current, end)) {
                doesHavePath = true;
                return;
            }

            if (board.checkToBeEqual(ny, nx, board.PATH)) {
                isVisited[ny][nx] = true;
                checkToConnectBetween(current, end, totalPath, passedPath + 1);
                isVisited[ny][nx] = false;
            }
        }
    }

    static void checkToConnectBetweenCoin() {
        for (int i = 0; i < board.coins.size(); i++) {
            int[] start = Arrays.stream(board.coins.get(i)).mapToInt(Integer::intValue).toArray();

            for (int j = i + 1; j < board.coins.size(); j++) {
                int[] end = Arrays.stream(board.coins.get(j)).mapToInt(Integer::intValue).toArray();
                checkToConnectWithInit(start, end, board.totalPath, 0);
                if (!doesHavePath) {
                    return;
                }
            }
        }
    }

    static void checkToConnectBetweenCoinAndKey() {
        for (Integer[] coin : board.coins) {
            int[] start = Arrays.stream(coin).mapToInt(Integer::intValue).toArray();
            checkToConnectBetween(start, board.key, board.totalPath, 0);
            if (!doesHavePath) {
                break;
            }
        }
    }

    static void makeKey() {
        int attempt = 0;
        while (true) {
            board.makeKey();
            checkToConnectBetweenCoinAndKey();
            if (doesHavePath) {
                break;
            }

            board.removeKey();
            attempt++;
            if (attempt > MAX_ATTEMPT) {
                System.out.println("총시도 횟수를 초과했습니다. 다른 칸으로 이동 후 다시 시도하세요.");
                return;
            }
        }

        System.out.println("모든 코인을 먹었습니다.\nKEY가 랜덤으로 생성되었습니다.");
    }

    static void visit(int y, int x) {
        if (board.isOutBoardOrOnWall(y, x)) {
            System.out.println("이동할 수 없습니다.");
            return;
        }

        if (board.checkToBeEqual(y, x, board.GOAL) && !board.doesHaveKey) {
            System.out.println("입구에 들어갈 수 없습니다. 열쇠가 필요합니다.");
            return;
        }

        if (board.checkToBeEqual(y, x, board.COIN)) {
            board.countOnCoin++;
            System.out.println("코인 1개를 먹었습니다.\n누적 코인: " + board.countOnCoin + "개");

            if (board.doesHaveAllCoin()) {
                makeKey();
            }
        }

        if (board.checkToBeEqual(y, x, board.KEY)) {
            board.doesHaveKey = true;
            System.out.println("KEY를 먹었습니다. 출구를 열 수 있습니다");
        }


        if (board.checkToBeEqual(y, x, board.GOAL) && board.doesHaveKey) {
            System.out.println("탈출에 성공했습니다!");
        }

        board.moveTo(y, x);
    }

    static boolean doesGetToGoal() {
        return (Arrays.equals(board.getCurrent(), board.getEnd()) && board.doesHaveKey);
    }

    static class Board {
        int N;
        String[][] board;
        int[][] points = new int[3][2];
        ArrayList<Integer[]> coins = new ArrayList<>();
        int[] key = new int[2];
        int countOnCoin;
        boolean doesHaveKey;
        int totalPath;
        int initialWallSize;
        String START = "S";
        String CURRENT = "U";
        String GOAL = "G";
        String COIN = "C";
        String KEY = "K";
        String WALL = "X";
        String PATH = "_";

        Board() {

        }

        Board(int N) {
            this.N = N;
            board = new String[N][N];
            totalPath = N * N;
            initialWallSize = N * 3;
            init();
        }

        Board(int N, int wallSize) {
            this.N = N;
            board = new String[N][N];
            totalPath = N * N;
            initialWallSize = wallSize;
            init();
        }

        void init() {
            makePath();
            makeStartAndGoal();
            makeWall();
        }

        void makePath() {
            for (int i = 0; i < N; i++) {
                Arrays.fill(board[i], "_");
            }
        }

        void makeStartAndGoal() {
            Random random = new Random();

            int START = 0;
            int END = 2;

            while (true) {
                points[START] = new int[]{random.nextInt(N), random.nextInt(N)};
                points[END] = new int[]{random.nextInt(N), random.nextInt(N)};

                if ((Math.abs(getEnd()[0] - getStart()[0]) + Math.abs(getEnd()[1] - getStart()[1])) > 5) {
                    markValueOn(getStart()[0], getStart()[1], CURRENT);
                    markValueOn(getEnd()[0], getEnd()[1], GOAL);
                    setCurrent(getStart()[0], getStart()[1]);
                    break;
                }
            }
        }

        void makeWall() {
            Random random = new Random();

            for (int i = 0; i < initialWallSize; i++) {
                int[] wall = new int[]{random.nextInt(N), random.nextInt(N)};
                if (checkToBeEqual(wall[0], wall[1], PATH)) {
                    markValueOn(wall[0], wall[1], WALL);
                    totalPath--;
                }
            }
        }

        void makeCoin(int amountToMake) {
            Random random = new Random();
            int madeCoin = 0;

            while (true) {
                int y = random.nextInt(N);
                int x = random.nextInt(N);
                if (checkToBeEqual(y, x, PATH)) {
                    coins.add(new Integer[]{y, x});
                    markValueOn(y, x, COIN);
                    madeCoin++;
                }

                if (madeCoin == amountToMake) {
                    break;
                }
            }
        }

        void makeKey() {
            Random random = new Random();
            while (true) {
                int y = random.nextInt(N);
                int x = random.nextInt(N);

                if (checkToBeEqual(y, x, PATH)) {
                    key = new int[]{y, x};
                    markValueOn(y, x, KEY);
                    break;
                }
            }
        }

        void removeKey() {
            markValueOn(key[0], key[1], PATH);
            key = new int[2];
        }

        void removeCoin() {
            for (Integer[] coin : coins) {
                markValueOn(coin[0], coin[1], PATH);
            }
            coins.clear();
        }

        void markValueOn(int y, int x, String value) {
            board[y][x] = value;
        }

        String getValueOn(int y, int x) {
            return board[y][x];
        }

        int[] getStart() {
            return points[0];
        }

        int[] getCurrent() {
            return points[1];
        }

        void setCurrent(int y, int x) {
            points[1][0] = y;
            points[1][1] = x;
        }

        int[] getEnd() {
            return points[2];
        }

        void show() {
            System.out.println("==========================");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(getValueOn(i, j) + " ");
                }
                System.out.println();
            }
            System.out.println("==========================");
        }

        boolean doesHaveAllCoin() {
            return (countOnCoin == coins.size()) ? true :false;
        }

        void moveTo(int y, int x) {
            markValueOn(getCurrent()[0], getCurrent()[1], PATH);

            if (Arrays.equals(getStart(), getCurrent())) {
                markValueOn(getCurrent()[0], getCurrent()[1], START);
            }

            setCurrent(y, x);
            markValueOn(y, x, CURRENT);
        }

        boolean checkToBeEqual(int y, int x, String value) {
            return (getValueOn(y, x).equalsIgnoreCase(value));
        }

        boolean isOutBoardOrOnWall(int y, int x) {
            return (y < 0 || x < 0 || N == y || N == x || checkToBeEqual(y, x, WALL));
        }
    }
}
