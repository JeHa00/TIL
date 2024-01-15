public class WhileEx2 {
    public static void main(String[] args) {
        int count = 1;
        System.out.println("while문 버전");
        while (count < 21) {
            if (count % 2 == 0) {
            System.out.println(count);
            }
            count++;
        }
        System.out.println();
        System.out.println("for 문 버전");
        for (int i = 1; i < 21; i++){
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
