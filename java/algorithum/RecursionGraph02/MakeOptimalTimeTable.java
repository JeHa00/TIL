package algorithum.RecursionGraph02;

import java.util.*;

/**
 * 2. 강의 시간표 짜기
 * 대학교 강의 시간표를 짜는 프로그램을 만들어보도록 합시다.
 * 모든 강의는 요일, 시작시간, 끝나는 시간, 교수명과 강의명을 저장합니다.
 * 나는 지난 학기에 학사경고를 받아, 이번 학기에 최대한 많은 수업을 들어 성적을 극복하려고 합니다.
 * 어떻게 해야 최대한 많은 강의를 듣는 시간표를 짤 수 있을까요?
 * 수업은 겹쳐 들을 수 없으며, 가장 최적의 시간표를 출력해야합니다.
 *
 * 최대한 많은 강의를 들을 수 있는 시간표 조합:
 *
 * 예시1
 * 저장해야 하는 강의 :
 * 대학물리1(김영명, 09:00-11:50, 월)
 * 대학물리1(김영명, 14:00-16:50, 목)
 * 미분적분학1(윤기현, 13:00-17:50, 금)
 * 미분적분학1(윤기현, 10:00-12:50, 목)
 * 실무소프트웨어(박연희, 13:00-15:00, 화)
 * 소프트웨어분석및설계(한미현, 14:00-15:00, 수)
 * 공학윤리와사상(김지민, 15:00-16:00, 금)
 * 일상생활과 법(고연준, 14:00-16:00, 목)
 * IT의 미래(민성준, 16:00-17:00, 화)
 * 지구의 지질과 자연재해(현이현, 13:00-14:00, 화)
 * 종료
 *
 * 출력
 * 나의 시간표 :
 * 대학물리1(김영명, 09:00-11:50, 월)
 * 실무소프트웨어(박연희, 13:00-15:00, 화)
 * IT의 미래(민성준, 16:00-17:00, 화)
 * 소프트웨어분석및설계(한미현, 14:00-15:00, 수)
 * 미분적분학1(윤기현, 10:00-12:50, 목)
 * 대학물리1(김영명, 14:00-16:50, 목)
 * 미분적분학1(윤기현, 13:00-17:50, 금)
 *
 * 예시2
 * 생활속의 소프트웨어(송기현, 15:00-17:00, 수)
 * 대학물리1(김영명, 09:00-11:50, 월)
 * 대학물리1(김영명, 14:00-16:50, 목)
 * 소프트웨어분석및설계(한미현, 14:00-15:00, 수)
 * 영어회화1(Kutress Michale, 14:00-15:30, 월)
 * 미분적분학1(윤기현, 13:00-17:50, 금)
 * 미분적분학1(윤기현, 10:00-12:50, 목)
 * 실무소프트웨어(박연희, 13:00-15:00, 화)
 * 공학윤리와사상(김지민, 15:00-16:00, 금)
 * 지구의 지질과 자연재해(현이현, 13:00-14:00, 화)
 * 영어회화1(Richard R.Hushle, 10:00-11:50, 월)
 * 기업용소프트웨어납품(송기현, 11:00-13:00, 수)
 * 일상생활과 법(고연준, 14:00-16:00, 목)
 * IT의 미래(민성준, 16:00-17:00, 화)
 *
 * 출력
 * 나의 시간표 :
 * 대학물리1(김영명, 09:00-11:50, 월)
 * 영어회화1(Kutress Michale, 14:00-15:30, 월)
 * 실무소프트웨어(박연희, 13:00-15:00, 화)
 * IT의 미래(민성준, 16:00-17:00, 화)
 * 기업용소프트웨어납품(송기현, 11:00-13:00, 수)
 * 소프트웨어분석및설계(한미현, 14:00-15:00, 수)
 * 생활속의 소프트웨어(송기현, 15:00-17:00, 수)
 * 미분적분학1(윤기현, 10:00-12:50, 목)
 * 대학물리1(김영명, 14:00-16:50, 목)
 * 미분적분학1(윤기현, 13:00-17:50, 금)
 *
 */
public class MakeOptimalTimeTable {

    static ArrayList<Lecture> list, answer, schedule;
    static ArrayDeque<Lecture> queue;
    static boolean[][] isIncluded = new boolean[7][24 + 1];

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

        bfs();

        Collections.sort(answer);
        System.out.println("나의 시간표: ");
        for (Lecture lecture : answer) {
            System.out.println(lecture);
        }
    }

    static void bfs() {
        for (Lecture lecture : list) {
            schedule.add(lecture);
            markAsTrueInTimeTable(lecture);

            for (Lecture next : list) {
                if (!schedule.contains(next) && canAttend(next)) {
                    schedule.add(next);
                    markAsTrueInTimeTable(next);
                }
            }

            if (answer.size() == 0 || answer.size() < schedule.size()) {
                answer = new ArrayList<>(schedule);
            }

            isIncluded = new boolean[7][24 + 1];
            schedule.clear();
        }
    }

    static void markAsTrueInTimeTable(Lecture lecture) {
        for (int hour = lecture.startTime; hour <= lecture.endTime; hour++) {
            isIncluded[lecture.dayOfTheWeek][hour] = true;
        }
    }

    static boolean canAttend(Lecture lecture) {
        for (int hour = lecture.startTime; hour <= lecture.endTime; hour++) {
            if (isIncluded[lecture.dayOfTheWeek][hour]) {
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
