package algorithum.StackQueue;

import java.util.*;

public class Problem08 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        ArrayDeque<Integer> patients = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            patients.add(input.nextInt());
        }

        ArrayDeque<Integer> patients01 = patients.clone();
        System.out.println(solution01(patients, m));
        System.out.println(solution02(patients01, m));
    }

    public static int solution01(ArrayDeque<Integer> patients, int m) {
        Integer[] sortedDangerScore = patients.toArray(Integer[]::new);
        Arrays.sort(sortedDangerScore, Comparator.reverseOrder());
        int nth = 0;

        for (int i = 0; i < sortedDangerScore.length; i++) {
            while (true) {
                Integer patientScore = patients.pollFirst();
                if (patientScore < sortedDangerScore[i]) {
                    patients.offerLast(patientScore);
                    m = (m == 0) ? patients.size() - 1 : --m;
                } else {
                    nth++;
                    break;
                }
            }

            if (m == 0) {
                break;
            }

            m--;
        }
        return nth;
    }

    public static int solution02(ArrayDeque<Integer> patients, int m) {
        ArrayDeque<Person> deque = new ArrayDeque<>();
        int size = patients.size();

        for (int i = 0; i < size; i++) {
            deque.add(new Person(i, patients.pollFirst()));
        }

        int nth = 0;
        while (true) {
            Person person = deque.pollFirst();
            for (Person p : deque) {
                if (person.priority < p.priority) {
                    deque.offerLast(person);
                    person = null;
                    break;
                }
            }

            if (person != null) {
                nth++;
                if (person.id == m) {
                    break;
                }
            }
        }

        return nth;
    }
}


class Person {
    public int id;
    public int priority;

    Person(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }
}
