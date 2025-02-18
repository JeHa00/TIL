package algorithum.RecursionGraph02;

import java.util.*;

public class FindElevatorBestPath {
    static int N, M, S, T;
    static boolean[][] visited;
    static Map<Integer, ArrayList<Integer>> floorsPerElevator = new HashMap<>();
    static Map<Integer, ArrayList<Integer>> elevatorsPerFloor = new HashMap<>();
    static List<Integer> answer = new ArrayList<>();


    public static void main(String[] args) {
        initialize();
        bfs();
        showResult(answer);
    }


    public static void initialize() {
        Scanner sc = new Scanner(System.in);

        System.out.print("N=");
        N = sc.nextInt();
        System.out.print("M=");
        M = sc.nextInt();
        visited = new boolean[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            sc = new Scanner(System.in);
            ArrayList<Integer> elevator = new ArrayList<>();
            floorsPerElevator.putIfAbsent(i, elevator);
            System.out.print("엘리베이터" + i + ": ");
            String[] floors = sc.nextLine().split(" ");

            for (String floor : floors) {
                int f = Integer.parseInt(floor);
                elevator.add(f);
                elevatorsPerFloor.putIfAbsent(f, new ArrayList<>());
                elevatorsPerFloor.get(f).add(i);
            }
        }

        System.out.print("S=");
        S = sc.nextInt();
        System.out.print("T=");
        T = sc.nextInt();
    }

    public static void bfs() {
        Deque<CurrentFloor> queue = new ArrayDeque<>();
        addStartPoint(queue);

        while (!queue.isEmpty()) {
            CurrentFloor current = queue.pollLast();

            ArrayList<Integer> elevators = elevatorsPerFloor.get(current.floor);
            for (int elevator : elevators) {
                for (int floor : floorsPerElevator.get(elevator)) {

                    if (floor == T) {
                        if (!current.path.contains(elevator)) {
                            current.path.add(elevator);
                        }

                        List<Integer> newPath = new ArrayList<>(current.path);
                        answer = (answer.size() > newPath.size() || answer.size() == 0) ? newPath : answer;
                        markToVisit(elevator, floor);
                        continue;
                    }

                    if (!isVisit(elevator, floor)) {
                        List<Integer> newPath = new ArrayList<>(current.path);
                        if (!current.path.contains(elevator)) {
                            newPath.add(elevator);
                        }
                        queue.offerFirst(new CurrentFloor(floor, newPath));
                        markToVisit(elevator, floor);
                    }
                }
            }
        }
    }

    public static boolean isVisit(int elevator, int floor) {
        return visited[elevator][floor];
    }

    public static void markToVisit(int elevator, int floor) {
        visited[elevator][floor] = true;
    }

    public static void addStartPoint(Deque<CurrentFloor> queue) {
        ArrayList<Integer> elevatorOnStart = elevatorsPerFloor.get(S);
        for (int elevator : elevatorOnStart) {
            queue.add(new CurrentFloor(S, new ArrayList<>(List.of(elevator))));
            markToVisit(elevator, S);
        }
    }

    public static void showResult(List<Integer> route) {
        if (route.size() == 0) {
            System.out.println("Impossible");
            return;
        }

        System.out.println("최소 환승 횟수: " + (route.size() - 1));
        System.out.print("이동 경로: [");
        for (int i = 0; i < route.size(); i++) {
            System.out.print("엘리베이터" + route.get(i));
            if (i != route.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("]");
    }
}


class CurrentFloor {
    int floor;
    List<Integer> path;

    CurrentFloor(int floor, List<Integer> path) {
        this.floor = floor;
        this.path = path;
    }
}

