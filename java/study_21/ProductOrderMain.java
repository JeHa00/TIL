package study_21;

import java.util.Scanner;

public class ProductOrderMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("입력할 주문의 개수를 입력하세요: ");
        int countOfOrders = input.nextInt();
        ProductOrder[] orders = new ProductOrder[countOfOrders];

        for (int i = 0; i < countOfOrders; i++) {
            System.out.println((i + 1) + "번째 주문 정보를 입력하세요.");

            System.out.print("상품명: ");
            String productName = input.next();

            System.out.print("가격: ");
            int price = input.nextInt();

            System.out.print("수량: ");
            int quantity = input.nextInt();

            orders[i] = createProductOrder(productName, price, quantity);
        }

        printOrders(orders);
        getTotalAmount(orders);
    }

    static ProductOrder createProductOrder(String productName, int price, int quantity) {
        ProductOrder order = new ProductOrder(); // 객체이므로 실제 값이 아닌 참조값이 할당
        order.productName = productName; 
        order.price = price; 
        order.quantity = quantity;
        return order;
    }

    static void printOrders(ProductOrder[] orders) {
        // intellij 로 iter을 입력하면 자동으로서 향상된 for문이 입력됨  
        for (ProductOrder order : orders) {
            System.out.println("상품명: " + order.productName + ", 가격: " + order.price + ", 수량: " + order.quantity);
        }
    }

    static void getTotalAmount(ProductOrder[] orders) {
        int sum = 0;
        for (ProductOrder order : orders) {
            sum += order.price * order.quantity;
        }
        System.out.println("총 결제 금액: " + sum);
    }
}
