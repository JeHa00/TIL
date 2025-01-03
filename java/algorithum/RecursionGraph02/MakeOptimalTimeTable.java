import java.util.*;

public class Exercise04 {

    static ArrayList<Lecture> list, answer, schedule;
    static ArrayDeque<Lecture> queue;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        list = new ArrayList<>();
        answer = new ArrayList<>();
        schedule = new ArrayList<>();
        queue = new ArrayDeque<>();

        System.out.println("저장해야 하는 강의: ");
        while (true) {
            String lecture = input.nextLine();
            if (lecture.equals("종료")) {
                break;
            }
            list.add(new Lecture(lecture));
        }

        dfs(0);

        Collections.sort(answer);
        System.out.println("나의 시간표: " + answer.size() + "개");
        for (Lecture lecture : answer) {
            System.out.println(lecture);
        }
    }

    static void dfs(int length) {
        if (length == list.size()) {
            if (answer.size() == 0 || answer.size() < schedule.size()) {
                answer = (ArrayList<Lecture>) schedule.clone();
            }
            return;
        }

        Lecture lecture = list.get(length);
        if (canAttend(lecture)) {
            schedule.add(lecture);
            dfs(length + 1);
            schedule.remove(lecture);
        }

        dfs( length + 1);

    }

    static void bfs() {
        for (Lecture lecture : list) {
            schedule.add(lecture);

            for (Lecture next : list) {
                if (!schedule.contains(next) && canAttend(next)) {
                    schedule.add(next);
                }
            }

            if (answer.size() == 0 || answer.size() < schedule.size()) {
                answer = new ArrayList<>(schedule);
            }

            schedule.clear();
        }
    }


    static boolean canAttend(Lecture newLecture) {
        for (Lecture lecture : schedule) {
            if (lecture.dayOfTheWeek == newLecture.dayOfTheWeek &&
                    (Math.max(lecture.startTime, newLecture.startTime) < Math.min(lecture.endTime, newLecture.endTime))) {
                return false;
            }
        }
        return true;
    }

    static class Lecture implements Comparable<Lecture>{
        String fullName;
        String name;
        String professor;
        int startTime;
        int endTime;
        int dayOfTheWeek;

        Lecture(String lecture) {
            this.fullName = lecture;
            lecture = lecture.replace("(", ",").replace(")", ",").replace(" ", "");
            String[] values = lecture.split(",");
            name = values[0];
            professor = values[1];
            dayOfTheWeek = convertToInt(values[3]);

            String[] times = values[2].split("-");
            int startTime = Integer.valueOf(times[0].split(":")[0]);
            int endTime = Integer.valueOf(times[1].split(":")[0]);
            this.startTime = startTime;
            this.endTime = endTime;
        }

        int convertToInt(String day) {
            if (day.equals("월")) {
                return 1;
            } else if (day.equals("화")) {
                return 2;
            } else if (day.equals("수")) {
                return 3;
            } else if (day.equals("목")) {
                return 4;
            } else if (day.equals("금")) {
                return 5;
            } else {
                return 6;
            }
        }

        @Override
        public String toString() {
            return fullName;
        }

        @Override
        public int compareTo(Lecture o1) {
            if (dayOfTheWeek < o1.dayOfTheWeek) {
                return -1;
            } else if (dayOfTheWeek == o1.dayOfTheWeek) {
                if (startTime < o1.startTime) {
                    return -1;
                } else if (startTime == o1.startTime) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

}
