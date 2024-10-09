import java.util.Scanner;

public class ScannerWhileEx4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int priceToPay = 0;

        while (true) {
            System.out.println("1: 상품입력, 2: 결제, 3: 프로그램 종료");
            int option = input.nextInt();

            if (option < 1 || 3 < option) {
                System.out.println("올바른 옵션을 선택해주세요");
                continue;
            } else if (option == 2) {
                System.out.println("총 비용: " + priceToPay);
                priceToPay = 0;
                continue;
            } else if (option == 3) {
                break;
            }

            System.out.print("상품명을 입력하세요: ");
            String name = input.next();

            System.out.print("상품의 가격을 입력하세요: ");
            int price = input.nextInt();

            System.out.print("구매 수량을 입력하세요: ");
            int amount = input.nextInt();

            int totalPricePerItem = price * amount;

            System.out.println("상품명: " + name + "가격: " + price + "수량: "+ amount + "합계: " +  totalPricePerItem);

            priceToPay += totalPricePerItem;
        }
        System.out.println("프로그램을 종료합니다.");
    }

}
