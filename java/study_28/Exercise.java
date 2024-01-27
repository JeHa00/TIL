/*
	학점과 점수를 입력받고, 해당 학점과 점수가 정상적인지 알아내는 프로그램을 만들어보시오.


	점수 : 100
	학점 : A
	올바른 학점입니다. or 올바르지 않은 학점입니다.

	같이 나타나게 한다. 무조건 점수를 먼저 입력받는다.

	A >> 점 100이상
	B >> 점 90이상
	C >> 점 80이상
	D >> 점 70이상
	E >> 점 60이상
	F >> 점 50이상 
*/
package study_28;

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        checkScoreGradeIsNormal();
    }

    public static void checkScoreGradeIsNormal() {
        Scanner input = new Scanner(System.in);
        System.out.print("점수: ");
        int score = input.nextInt();

        System.out.print("학점 (예: A, B, C, D ...): ");
        String grade = input.next();

        if (grade.equals("A")) {
            getScoreGrade(grade, score, 100);
        }
        else if (grade.equals("B")) {
            getScoreGrade(grade, score, 90);
        }
        else if (grade.equals("C")) {
            getScoreGrade(grade, score, 80);
        }
        else if (grade.equals("D")) {
            getScoreGrade(grade, score, 70);
        }
        else if (grade.equals("E")) {
            getScoreGrade(grade, score, 60);
        }
        else {
            getScoreGrade(grade, score, 50);
        }
    }

    public static void getScoreGrade(String grade, int score,int standard) {
        if (score >= standard) {
            System.out.println("올바른 학점입니다.");
            System.out.println(grade + " >> " + "점 " + standard + "이상");
        } else {
            System.out.println("올바르지 않은 학점입니다.");
        }



    }

}
