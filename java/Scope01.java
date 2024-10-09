// 변수 범위
public class Scope01 {
    public static void main(String[] args) {
        int m = 10;
        int temp = 0 ;
        if (m > 0) {
            temp = m * 2;
            System.out.println(temp);
        }
        System.out.println(m);

        // 개선
//        int m = 10;
//        if (m > 0) {
//            int temp = 2 * m;
//            System.out.println(temp);
//        }
        System.out.println(m);
    }
}

