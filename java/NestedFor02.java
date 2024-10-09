// 중첩 만복문

public class NestedFor02 {
    public static void main(String[] args) {
        int rows = 5;
        for (int i = 1; i < rows + 1; i++){
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
