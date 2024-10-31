package array;

import java.util.Scanner;

public class Problem11 {
    public static int solution01(int[][] table) {
        int count[] = new int[table.length];
        for (int grade = 1; grade < 6; grade++) {
            for (int studentId = 1; studentId < table.length; studentId++) {
                for (int otherStudentId = 1; otherStudentId < table.length; otherStudentId++) {
                    if (studentId == otherStudentId) {
                        continue;
                    }

                    if (table[studentId][grade] == table[otherStudentId][grade]) {
                        count[studentId]++;
                    }
                }
            }
        }

        int max = 0;
        int answer = 0;
        for (int i = 1; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                answer = i;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int studentsAmount = input.nextInt() + 1;
        int TOTAL_GRADE = 5 + 1;
        int classes[][] = new int[studentsAmount][TOTAL_GRADE];

        for (int student_id = 1; student_id < studentsAmount; student_id++) {
            for (int grade = 1; grade < TOTAL_GRADE; grade++) {
                classes[student_id][grade] = input.nextInt();
            }
        }

        System.out.println(solution01(classes));
    }
}