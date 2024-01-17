import java.util.Scanner;

public class ArrayEx7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("학생 수를 입력하세요: ");
        int totalNumberOfStudents = input.nextInt();

        String [] subjects = {"국어", "영어", "수학"};

        int [][] scoresOfStudents = new int[totalNumberOfStudents][subjects.length];

        for (int i = 0; i < totalNumberOfStudents; i++) {
            System.out.println((i + 1) + "번 학생의 성적을 입력하세요: ");

            for (int j = 0; j < subjects.length; j++) {
                System.out.print(subjects[j] + " 점수: ");
                scoresOfStudents[i][j] = input.nextInt();
            }
        }

        for (int i = 0; i < totalNumberOfStudents; i++) {
            int totalScore = 0;
            double average = 0;

            for (int j = 0; j < subjects.length; j++) {
                totalScore += scoresOfStudents[i][j];
            }

            average = (double) totalScore / subjects.length;

            System.out.println((i + 1) + "번 학생의 총점: " + totalScore + ", 평균: " + average);



        }

    }
}

