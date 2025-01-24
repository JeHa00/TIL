package algorithum.RecursionGraph02.Packman;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Board board;
    static boolean doesFindPath;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int attempt;
    static int coinAmountOfUser;
    static boolean doesHaveKey;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            try {
                initBoards();

                while (true) {
                    board.showBoards();
                    System.out.print("입력: ");
                    String value = input.next();
                    int[] currentPoint = board.getCurrentPoint();
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
                    } else if (value.equalsIgnoreCase("Q")) {
                        System.out.println("게임을 종료합니다.");
                        exit = true;
                        break;
                    } else {
                        System.out.println("다른 문자는 입력받지 않습니다.");
                    }

                    if (board.isEnd(doesHaveKey)) {
                        exit = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("프로그램을 다시 시작하세요.");
                break;
            }
        }
    }

    public static void initBoards() throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.print("게임판의 길이 : ");
        int N = input.nextInt();
        int tries = 10;

        while (!doesFindPath || tries > 1) {
            board = new Board(N);
            board.initPath();
            checkToConnectBetweenWithInit(board.getStartPoint(), board.getEndPoint(), 1, board.TOTAL_PATH);
            tries--;
        }

        if (!doesFindPath && tries == 1) {
            throw new Exception("Start again");
        }

        tries = 10;
        while (tries > 1) {
            if (addCoinToBoard() && checkToConnectBetweenCoins()) {
                break;
            }
            tries--;
        }

        if (tries == 1) {
            throw new Exception("Start again");
        }
    }

    public static void checkToConnectBetween(int[] startPoint, int[] endPoint, int amount, int totalPath) {
        if (doesFindPath || amount >= totalPath || attempt > 4000) {
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int ny = startPoint[0] + dy[i];
            int nx = startPoint[1] + dx[i];

            if (ny < 0 || nx < 0 || ny >= board.length() || nx >= board.length() || isVisited[ny][nx] || board.checkToBeEqual(ny, nx, "X")) {
                continue;
            }

            if (ny == endPoint[0] && nx == endPoint[1]) {
                doesFindPath = true;
                return;
            }

            if (board.checkToBeEqual(ny, nx, "_")) {
                isVisited[ny][nx] = true;
                checkToConnectBetween(new int[]{ny, nx}, endPoint, amount + 1, totalPath);
                isVisited[ny][nx] = false;
            }
        }

        attempt++;
    }

    public static void checkToConnectBetweenWithInit(int[] startPoint, int[] endPoint, int amount, int totalPath) {
        attempt = 0;
        doesFindPath = false;
        isVisited = new boolean[board.length()][board.length()];
        checkToConnectBetween(startPoint, endPoint, amount, totalPath);
    }


    public static boolean addCoinToBoard() {
        boolean[][] isVisitedForCoin = new boolean[board.length()][board.length()];
        Random random = new Random();
        int tries = 0;

        while (true) {
            int x = random.nextInt(board.length());
            int y = random.nextInt(board.length());

            if (!isVisitedForCoin[y][x] && board.checkToBeEqual(y, x, "_")) {
                checkToConnectBetweenWithInit(board.getStartPoint(), new int[]{y, x}, 0, board.TOTAL_PATH);

                if (doesFindPath) {
                    board.setPointValue(y, x, "C");
                    board.addCoin(y, x);
                }
            }

            if (board.getCoinAmount() == board.TOTAL_COIN_AMOUNT) {
                return true;
            }

            isVisitedForCoin[y][x] = true;
            if (checkToVisitAll(isVisitedForCoin) || tries > 500) {
                board.clearCoinPoint();
                return false;
            }
            tries++;
        }
    }

    public static boolean checkToConnectBetweenCoins() {
        for (int i = 0;  i < board.TOTAL_COIN_AMOUNT; i++) {
            for (int j = i + 1; j < board.TOTAL_COIN_AMOUNT; j++) {
                checkToConnectBetweenWithInit(board.getCoin(i), board.getCoin(j), 0, board.TOTAL_PATH);
                if (!doesFindPath) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean checkToVisitAll(boolean[][] isVisited) {
        boolean result = true;
        for (int y = 0; y < isVisited.length; y++) {
            for (int x = 0; x < isVisited.length; x++) {
                if (!isVisited[y][x]) {
                    result = false;
                }
            }
        }

        return result;
    }


    public static boolean canGoTo(int[] nextPoint) {
        int row = nextPoint[0];
        int column = nextPoint[1];

        if (row < 0 || board.length() <= row) {
            System.out.println("이동할 수 없습니다."); // Feedback 01로 추가된 코드
            return false;
        }

        if (column < 0 || board.length() <= column) {
            System.out.println("이동할 수 없습니다."); // Feedback 01로 추가된 코드
            return false;
        }
        if (board.checkToBeEqual(row, column, "X")) {
            System.out.println("이동할 수 없습니다."); // Feedback 01로 추가된 코드
            return false;
        }

        // Feedback 01로 추가된 코드
        if (board.checkToBeEqual(row, column, "G") && !doesHaveKey) {
            System.out.println("입구에 들어갈 수 없습니다. 열쇠가 필요합니다.");
            return false;
        }

        return true;
    }

    public static void visit(int[] nextPoint) {
        if (canGoTo(nextPoint)) {
            checkToGetCoinOrKey(nextPoint);
            int[] startPoint = board.getStartPoint();
            int[] currentPoint = board.getCurrentPoint();

            String value = (currentPoint[0] == startPoint[0] && currentPoint[1] == startPoint[1]) ? "S" : "_";
            board.setPointValue(currentPoint[0], currentPoint[1], value);

            board.setPointValue(nextPoint[0], nextPoint[1], "U");
            board.setCurrentPoint(nextPoint[0], nextPoint[1]);
        }
    }

    public static void checkToGetCoinOrKey(int[] nextPoint) {

        if (board.checkToBeEqual(nextPoint[0], nextPoint[1], "C")) {
            coinAmountOfUser++;
            System.out.println("코인 1개를 먹었습니다. \n누적 코인 : " + coinAmountOfUser + "개");

            if (coinAmountOfUser == board.TOTAL_COIN_AMOUNT) {
                getKey();
            }
        }

        if (board.checkToBeEqual(nextPoint[0], nextPoint[1], "K")) {
            doesHaveKey = true;
            System.out.println("KEY를 먹었습니다. 출구를 열 수 있습니다.");
        }

    }

    public static void getKey() {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(board.length());
            int y = random.nextInt(board.length());

            if (board.checkToBeEqual(y, x, "U")) {
                doesHaveKey = true;
                System.out.println("사용자 위치에서 생성되어 열쇠를 바로 얻었습니다.");
                break;
            }

            int[] key = {y, x};
            if (board.checkToBeEqual(y, x, "_") && checkToConnectFromCoinToKey(key) && checkToConnectFromKeyToGoal(key)) {
                board.setPointValue(y, x, "K");
                System.out.println("모든 코인을 먹었습니다. KEY가 랜덤으로 생성되었습니다.");
                break;
            }
        }
    }

    public static boolean checkToConnectFromCoinToKey(int[] key) {
        for (int i = 0; i < board.TOTAL_COIN_AMOUNT; i++) {
            checkToConnectBetweenWithInit(board.getCoin(i), key, 0, board.TOTAL_PATH);
            if (!doesFindPath) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkToConnectFromKeyToGoal(int[] key) {
        checkToConnectBetweenWithInit(key, board.getEndPoint(), 0, board.TOTAL_PATH);
        return doesFindPath;
    }
}
