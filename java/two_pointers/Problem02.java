package two_pointers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem02 {
    public static void solution01() {

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int size = input.nextInt();
        Set<Integer> set01 = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set01.add(input.nextInt());
        }

        size = input.nextInt();
        Set<Integer> set02 = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set02.add(input.nextInt());
        }

        set01.retainAll(set02);
        Integer[] answer = set01.toArray(new Integer[set01.size()]);
        Arrays.sort(answer);
        for (Integer integer : answer) {
            System.out.print(integer.intValue() + " ");
        }
    }
}
