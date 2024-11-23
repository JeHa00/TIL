package algorithum.sorting_searching;

import java.util.Arrays;
import java.util.Scanner;

public class LeastRecentlyUsed {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int s = input.nextInt();
        int n = input.nextInt();
        int values[] = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = input.nextInt();
        }

        solution01(s, values);
    }

    // deque을 사용해도 된다.
    // 만들어진 것을 사용하기보다는 직접 기능을 구현해서 해라. 면접 때 만들어보라고 질문이 들어온다.
    public static void solution01(int s, int[] values) {
        Array cache = new Array(s);
        for (int value : values) {
            if (cache.contains(value)) {
                cache.removeByValue(value); // deque.remove(value)
            }

            cache.add(value, 0); // deque.offerFirst(value);

            while (cache.size() > s) {
                cache.removeByIndex(cache.size() - 1); // deque.popLast(value);
            }
        }

        for (Integer value : cache.getValues()) {
            if (value != 0) System.out.print(value.intValue() + " ");
        }
    }
}


class Array {
    int DEFAULT_SIZE = 5;
    int[] values = new int[DEFAULT_SIZE];
    int size;

    Array() {

    }

    Array(int size) {
        values = new int[size];
    }

    void add(int value) {
        if (values.length == size) {
            grow();
        }

        values[size] = value;
    }

    void add(int value, int index) {
        if (values.length == size) {
            grow();
        }

        shiftFromLeftToRight(index);
        values[index] = value;
        size++;
    }

    void grow() {
        values = Arrays.copyOf(values, size * 2);
    }

    int get(int index) {
        return values[index];
    }

    int getIndex(int value) {
        for (int i = 0; i < size; i++) {
            if (values[i] == value) {
                return i;
            }
        }
        return -1;
    }

    void removeByValue(int value) {
        int index = getIndex(value);
        shiftFromRightToLeft(index);
        size--;
    }

    void removeByIndex(int index) {
        shiftFromRightToLeft(index);
        size--;
    }
    void shiftFromRightToLeft(int index) {
        for (int i = index + 1; i < size; i++) {
            values[i - 1] = values[i];
        }
        values[size - 1] = 0;
    }

    void shiftFromLeftToRight(int index) {
        for (int i = size - 1; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = 0;
    }

    boolean contains(int value) {
        for (int v : values) {
            if (v == value) {
                return true;
            }
        }

        return false;
    }

    int size() {
        return size;
    }

    int[] getValues() {
        return values;
    }
}
