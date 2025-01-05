package algorithum.RecursionGraph02;

import java.util.*;

public class MakeOptimalTimeTable {

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

//        dfsSolution(); 이 방법은 dfs를 사용한 방법이고
        greedy(); // 이 방법은 그리디와 dfs를 섞어서 사용한 방법입니다.


        System.out.println("나의 시간표: " + answer.size() + "개");
        for (Lecture lecture : answer) {
            System.out.println(lecture);
        }
    }

    public static void dfsSolution() {
        dfs(0);
        Collections.sort(answer);
    }

    static void dfs(int nth) {
        if (nth == list.size()) {
            if (answer.size() == 0 || answer.size() < schedule.size()) {
                answer = (ArrayList<Lecture>) schedule.clone();
            }
            return;
        }

        Lecture lecture = list.get(nth);
        if (canAttend(lecture)) {
            schedule.add(lecture);
            dfs(nth + 1);

            schedule.remove(lecture);
            dfs( nth + 1);
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

    static boolean canAttend(Lecture newLecture, LinkedList<Lecture> lecturesOnTheDay) {
        for (Lecture lecture : lecturesOnTheDay) {
            if (lecture.dayOfTheWeek == newLecture.dayOfTheWeek &&
                    (Math.max(lecture.startTime, newLecture.startTime) < Math.min(lecture.endTime, newLecture.endTime))) {
                return false;
            }
        }
        return true;
    }


    static void greedy() {
        ArrayList<ArrayList<Lecture>> lectures = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            lectures.add(new ArrayList<>());
        }

        for (Lecture lecture : list) {
            ArrayList<Lecture> lecturesOnTheDay = lectures.get(lecture.dayOfTheWeek);
            lecturesOnTheDay.add(lecture);
        }

        for (int i = 1; i < 6; i++) {
            ArrayList<Lecture> lecturesOnTheDay = lectures.get(i);
            ArrayList<Lecture> selectedLectures = new ArrayList<>();
            findMaxSchedule(0, lecturesOnTheDay, selectedLectures);
            schedule.clear();
            answer.addAll(selectedLectures);
        }
    }

    static void findMaxSchedule(int nth, ArrayList<Lecture> lecturesOnTheDay, ArrayList<Lecture> selectedLectures) {
        if (nth == lecturesOnTheDay.size()) {
            if (selectedLectures.size() == 0 || selectedLectures.size() < schedule.size()) {
                selectedLectures.clear();
                for (Lecture lecture: schedule) {
                    selectedLectures.add(lecture);
                }
            }
            return;
        }

        Lecture lecture = lecturesOnTheDay.get(nth);
        if (canAttend(lecture)) {
            schedule.add(lecture);
            findMaxSchedule(nth + 1, lecturesOnTheDay, selectedLectures);

            schedule.remove(lecture);
            findMaxSchedule( nth + 1, lecturesOnTheDay, selectedLectures);
        }
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