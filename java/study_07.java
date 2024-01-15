public class WhileEx3 {
    public static void main(String[] args) {
        int max = 100;
        int sum1 = 0;

        System.out.println("while 문 버전");
        int i = 0;
        while (i < max + 1) {
            sum1 += i;
            i++;
        }
        System.out.println(sum1);

        System.out.println();

        int sum2 = 0;
        System.out.println("if 문 버전");
        for (int j = 1; j < max + 1; j++){
            sum2 += j;
        }
        System.out.println(sum2);

    }
}
