/*

	다음 세 가지 중 고르시오.
	1. 감자. 2.옥수수 3. 수박
	1번을 누를 시
	1번 감자를 선택하셨습니다. 라는 문구가 나오도록.
	2번을 누를 시
	2번 옥수수를 선택하셨습니다.
	3번을 누를 시,
	3번 수박을 선택하셨습니다.
	>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	그 뒤 1번 감자에서 1000원어치, 2000원어치 3000원어치.
	2번을 누를 시 옥수수에서 4000원어치, 5000원어치 6000원어치,
	3번을 누를 시 수박에서 10000원어치, 20000원어치, 30000원어치.
	라는 문구가 각각 나오도록 설정하고,
	그 안에서 또 1번을 누를 시
	“감자 1000어치를 선택하셨습니다.” 라는 문구가 나오도록 하고,
	몇 개를 주문하겠냐고 질문한 뒤 그 개수에 따라 금액이 변동하도록 하시오.

*/
package study_27;

import java.util.Scanner;

public class ExerciseBefore {
    public static void main(String[] args) {
        int optionForItem, price;
        String itemName;
        price = 0;

        optionForItem = chooseOption();

        if (optionForItem == 1) {
            itemName = "감자";
            price = choosePrice(itemName, price);

        } else if (optionForItem == 2) {
            itemName = "옥수수";
            price = choosePrice(itemName, price);
        } else {
            itemName = "수박";
            price = choosePrice(itemName, price);
        }

        int amount = chooseAmount(itemName, price);
        System.out.println(itemName + " " + price + "원 어치를 총 " + amount + "개 주문하셨습니다.");
    }

    // 식재료를 선택하는 역할: 받는 인자값은 없고, 선택된 메뉴 번호를 반환
    public static int chooseOption() {
        Scanner input = new Scanner(System.in);
        System.out.println("다음 세 가지 중 고르시오.");

        // 만약 아래에 옵션 하나를 추가하면 choosePrice에도 하나 추가해야 한다. 이를 의존성을 띈다고 말하며 단일 책임 원칙에 어긋난다.  
        System.out.println("1. 감자.  2. 옥수수. 3. 수박. 4. ghqkr");
        return input.nextInt();
    }

    // 가격 선택 인터페이스를 제공하는 역할
    public static int choosePrice(String itemName, int price) {
        if (itemName.equals("감자")) {
            System.out.println(itemName + "를 선택하셨습니다.");
            price = choosePriceOption(1000, 2000, 3000);
        } else if (itemName.equals("옥수수")) {
            System.out.println(itemName + "를 선택하셨습니다.");
            price = choosePriceOption(4000, 5000, 6000);
        } else {
            System.out.println(itemName + "을 선택하셨습니다.");
            price = choosePriceOption(10000, 20000, 30000);
        }

        return price;
    }

    // 가격을 선택하는 역할
    public static int choosePriceOption(int option1Price, int option2Price, int option3Price) {
        Scanner input = new Scanner(System.in);
        int price;
        System.out.println("1. " + option1Price + "원 어치. 2. " + option2Price + "원 어치. 3. " + option3Price + "원 어치");
        int optionForPrice = input.nextInt();

        if (optionForPrice == 1) {
            price = option1Price;
        } else if (optionForPrice == 2) {
            price = option2Price;
        } else {
            price = option3Price;
        }

        return price;
    }

    // 양을 선택하는 역할
    public static int chooseAmount(String itemName, int price) {
        Scanner input = new Scanner(System.in);
        System.out.println(itemName + " " + price + "원 어치를 선택하셨습니다. 몇 개를 주문하시겠습니까?");

        return input.nextInt();
    }
}
