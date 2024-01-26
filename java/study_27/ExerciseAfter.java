/*
ExerciseBefore에 작성된 코드는 '단일 책임 원칙'을 준수하지 않아서 한 메서드가 다른 메서드에 의존성을 띈다.  
즉, 한 메서드를 수정하면 다른 메서드도 수정해야 한다. 그래서 다음과 같이 코드를 리팩토링했다.
*/
package study_27;

import java.util.Scanner;

public class ExerciseAfter {
    public static void main(String[] args) {
        
        // 이전 코드와 달리 하나의 메서드에 포함해서 최종적으로 주문 금액을 알려고 하는 프로그램의 역할에 맞게 작성
        int totalPrice = orderIngredient();
        System.out.print("구매 최종 비용: " + totalPrice);
    }

    public static int orderIngredient() {
        Scanner input = new Scanner(System.in);

        // 식재료 선택
        // 식재료를 새로 추가해도 다른 메서드를 수정하지 않아도 된다. 
        System.out.print("1.감자 2.옥수수 3.수박: ");
        int ingredient = input.nextInt();

        // 식재료 선택에 따른 전체 주문 가격 얻기
        if (ingredient == 1) {
            System.out.println(ingredient + "번 감자를 선택하셨습니다.");
            return getTotalPrice("감자", 1000, 2000, 3000);
        }
        else if (ingredient == 2) {
            System.out.println(ingredient + "번 옥수수를 선택하셨습니다.");
            return getTotalPrice("옥수수", 4000, 5000, 6000);
        }
        else if (ingredient == 3) {
            System.out.println(ingredient + "번 수박을 선택하셨습니다.");
            return getTotalPrice("수박", 10000, 20000, 30000);
        }
        return 0;
    }

    public static int getTotalPrice(String ingredient, int price1, int price2, int price3) {
        Scanner input = new Scanner(System.in);

        // 가격 선택
        System.out.print("1." + price1 + " 2." + price2 +" 3." + price3);
        int selectedPrice = input.nextInt();

        // 선택한 구매가격 안내 및 전체 구매 가격 반환
        if (selectedPrice == 1) {
            return informPriceAndOrderAmount(ingredient, price1);
        }
        else if (selectedPrice == 2) {
            return informPriceAndOrderAmount(ingredient, price2);
        }
        else if (selectedPrice == 3) {
            return informPriceAndOrderAmount(ingredient, price3);
        }
        return 0;
    }

    public static int informPriceAndOrderAmount(String ingredient, int price) {
        Scanner input = new Scanner(System.in);
        // 선택한 구매가격 안내
        System.out.println(ingredient +" " + price + "원어치를 선택하셨습니다.");

        // 수량 선택
        System.out.print("몇 개를 주문하시겠습니까?: ");
        int orderAmount = input.nextInt();

        // 전체 구매 가격 반환
        // 이전에 나눠놨던 구매 가격 메서드를 해당 메서드에 포함시킴. 왜냐하면 역할을 새로 정의했기 때문
        return orderAmount * price;
    }

}
