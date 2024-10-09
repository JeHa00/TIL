import java.util.Scanner;

public class Assignment02 {
    public static void main(String[] args) {
        Exercise();
    }

    public static void Exercise() {

        int nth = 1;

        for (;;) { // 영업 계속 지속
            int totalPrice = 0;

            // 메뉴 선택하기
            for (boolean keepShopping = true; keepShopping;) {
                int optionNumber = showOption(); // 메뉴

                if (optionNumber == 1) {
                    totalPrice += addToTotalPrice(1, "장어덮밥", 5000);
                } else if (optionNumber == 2) {
                    totalPrice += addToTotalPrice(2, "옥수수콘", 10000);
                } else if (optionNumber == 3) {
                    totalPrice += addToTotalPrice(3, "감자튀김", 3000);
                } else {
                    System.out.println("감사합니다. 총 가격은 " + totalPrice + "원 입니다.");
                    keepShopping = selectToEndOrContinueShopping(nth);
                }
            }

            nth++; // 고객 순번 증가
        }
    }

    public static int showOption() {
        // 구매할 상품 번호와 가격 등을 보여주고 선택합니다
        Scanner input = new Scanner(System.in);

        System.out.println("1.장어덮밥(5000원) 2.옥수수콘(10000원) 3.감자튀김(3000원) 4.총 가격");
        System.out.print("선택할 번호 : ");

        return input.nextInt();
    }

    public static int addToTotalPrice(int optionNumber, String foodName, int foodPrice) {
        // 선택한 상품의 가격을 전체 구매 가격에 추가합니다
        System.out.println(optionNumber + "번 " + foodName + "을 선택하셨습니다.");
        return foodPrice;
    }

    public static boolean selectToEndOrContinueShopping(int nth) {
        // 쇼핑을 계속할지 그만할지 선택합니다
        Scanner input = new Scanner(System.in);

        System.out.println("계속 구매하시겠다면 1번, 아니면 2번을 눌러주세요.");
        int optionNumber = input.nextInt();

        if (optionNumber == 1) {
            System.out.println("계속 진행합니다.");
            return true;
        } else {
            System.out.println(nth + "번째 손님이 구매를 마치셨습니다.");
            return false;
        }
    }

}
