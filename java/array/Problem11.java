package array;

import java.util.Scanner;

public class Problem11 {
    public static int solution01(int[][] table) {
        int count[] = new int[table.length];


        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int studentsAmount = input.nextInt();
        int TOTAL_GRADE = 5;
        int classes[][] = new int[studentsAmount][TOTAL_GRADE];

        for (int student_id = 0; student_id < studentsAmount; student_id++) {
            for (int grade = 0; grade < studentsAmount; grade++) {
                classes[student_id][grade] = input.nextInt();
            }
        }

        System.out.println(solution01(classes));
    }
}