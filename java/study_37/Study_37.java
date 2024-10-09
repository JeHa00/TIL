import java.util.Scanner;

public class Study_37 {
    public static void main(String[] args) {
        String[] foodMenus = {"타코야키", "떡볶이", "순대", "오뎅", "볶음밥"};
        int[] foodPrices = {2000, 3000, 2000, 1000, 3000};
        String[] otherMenus = {"환불", "돈 갚기", "다음 손님", "제어할 손님 선택", "종료"};

        int[] initialMoney = getMoneyOfCustomers();
        int[] currentMoney = makeCurrentMoneyArray(initialMoney);

        int debts[] = new int[225]; // 15명의 각 사람마다 빌린 돈 데이터를 가지고 있는 배열
        int financialInfo[] = new int[30]; // 15명 모두의 빌린 돈, 빌려준 돈 데이터를 가지고 있는 배열
        int totalFoodCount[] = new int[5];

        boolean END = false;
        int index = 1;
        int nthPersonOrdered = 1;
        int customerIndexToGoBack = 0;
        boolean useOption9 = false;

        while (!END) {
        // 새로운 손님 진입하는 루프
            System.out.println(nthPersonOrdered + "번째 손님입니다.");

            // 관련 값들 초기화
            int[] foodCountPerCustomer = new int[5];
            int[] myDebt = getMyInfo(15, index, debts);
            int[] myFinancialInfo = getMyInfo(2, index, financialInfo); // 빌린 금액, 빌려준 돈
            boolean doesOrder = false;

            // 손님 한 명이 계속 주문하는 루프
            while (true) {
                System.out.println("===== 메뉴 선택지에 진입했습니다. =====");
                int optionNumber = chooseOption(useOption9, foodMenus, foodPrices, otherMenus);

                if (optionNumber == 1) { // 타코야키 주문
                    boolean result = checkOrder(index - 1, currentMoney, optionNumber, foodPrices, foodCountPerCustomer, totalFoodCount);
                    if (result) {
                        doesOrder = true;
                    } else {
                        borrowMoney(index, currentMoney, myDebt, myFinancialInfo, financialInfo, foodMenus, foodPrices);
                    }

                } else if (optionNumber == 2) { // 떡볶이 주문
                    boolean result = checkOrder(index - 1, currentMoney, optionNumber, foodPrices, foodCountPerCustomer, totalFoodCount);
                    if (result) {
                        doesOrder = true;
                    } else {
                        borrowMoney(index, currentMoney, myDebt, myFinancialInfo, financialInfo, foodMenus, foodPrices);
                    }

                } else if (optionNumber == 3) { // 순대 주문
                    boolean result = checkOrder(index - 1, currentMoney, optionNumber, foodPrices, foodCountPerCustomer, totalFoodCount);
                    if (result) {
                        doesOrder = true;
                    } else {
                        borrowMoney(index, currentMoney, myDebt, myFinancialInfo, financialInfo, foodMenus, foodPrices);
                    }

                } else if (optionNumber == 4) { // 오뎅 주문
                    boolean result = checkOrder(index - 1, currentMoney, optionNumber, foodPrices, foodCountPerCustomer, totalFoodCount);
                    if (result) {
                        doesOrder = true;
                    } else {
                        borrowMoney(index, currentMoney, myDebt, myFinancialInfo, financialInfo, foodMenus, foodPrices);
                    }

                } else if (optionNumber == 5) { // 볶음밥 주문
                    boolean result = checkOrder(index - 1, currentMoney, optionNumber, foodPrices, foodCountPerCustomer, totalFoodCount);
                    if (result) {
                        doesOrder = true;
                    } else {
                        borrowMoney(index, currentMoney, myDebt, myFinancialInfo, financialInfo, foodMenus, foodPrices);
                    }

                } else if (optionNumber == 6) { // 환불
                    refund(index, useOption9, foodMenus, foodPrices, foodCountPerCustomer, currentMoney, totalFoodCount);

                } else if (optionNumber == 7) { // 돈 갚기
                   payBack(myDebt, debts, myFinancialInfo, currentMoney);

                } else if (optionNumber == 8) { // 다음 손님
                    if (useOption9) {
                        System.out.println("이전 손님으로 돌아가기 밖에 할 수 없습니다.");
                    } else {
                        System.out.println("주문을 종료합니다.");
                        printOrderAndDebt(index, initialMoney[index - 1], currentMoney[index - 1], foodCountPerCustomer, foodMenus, foodPrices, myFinancialInfo, totalFoodCount);
                        break;
                    }

                } else if (optionNumber == 9) { // 제어할 손님
                    if (!useOption9) {
                        customerIndexToGoBack = index;
                        useOption9 = true;
                        Scanner input = new Scanner(System.in);
                        System.out.print("몇 번 손님을 제어하시겠습니까? : ");
                        int indexToControl = input.nextInt();
                        index = indexToControl;
                        System.out.println(index + "번 손님을 제어합니다.");
                    } else {
                        System.out.println("이전 손님으로 돌아가기 밖에 할 수 없습니다.");
                    }
                } else if (optionNumber == 10) {
                    System.out.println("프로그램을 종료합니다.");
                    END = true;
                    break;
                } else if (useOption9 && optionNumber == 11) {
                    index = customerIndexToGoBack;
                    useOption9 = false;
                    System.out.println(customerIndexToGoBack + "번째 손님으로 돌아갑니다.");
                } else {
                    System.out.println("옵션 번호를 잘못 누르셨습니다.");
                }
            }
            if (doesOrder) {
                nthPersonOrdered++;
            }
            index++;
        }
    }

    // 메뉴 선택하여 선택된 메뉴 번호 반환하기: 문제 출제 1번
    public static int chooseOption(boolean userOption9, String[] foodMenus, int[] foodPrices, String[] otherMenus) {
        Scanner input = new Scanner(System.in);

        String menu = "";
        for (int i = 0; i < foodMenus.length; i++) {
            menu += (i + 1) + ". " + foodMenus[i] + "(" + foodPrices[i] + "원)  ";
        }

        if (otherMenus.length != 0) {
            for (int i = 0; i < otherMenus.length; i++) {
                menu += (i + foodMenus.length + 1) + ". " + otherMenus[i] + "  ";
            }
        }

        if (userOption9) {
            menu += "11. 이전 손님으로 돌아가기";
        }

        System.out.println(menu);
        System.out.print("선택할 메뉴 번호: ");
        return input.nextInt();

    }

    // 초기 가지고 있는 돈 정보
    public static int[] getMoneyOfCustomers() {
        Scanner input = new Scanner(System.in);
        int[] money = {20000, 20000, 20000, 20000, 20000, 10000, 10000, 10000, 10000, 10000, 0, 0, 0, 0, 0};;
        System.out.println("11번째 손님부터는 금액을 입력받아야 합니다.");
        for (int i = 10; i < money.length; i++) {
            System.out.print("    " + (i+1) + "번째 손님은 얼마를 가지고 있으신가요?: ");
            money[i] = input.nextInt();
        }
        return money;
    }


    // 현재 가지고 있는 돈 정보
    public static int[] makeCurrentMoneyArray(int[] initialMoney) {
        int[] currentMoney = new int[initialMoney.length];
        for (int i = 0; i < currentMoney.length; i++) {
            currentMoney[i] = initialMoney[i];
        }

        return currentMoney;
    }


    // 주문 유효성 검증하기
    public static boolean checkOrder(int index, int[] currentMoney, int optionNumber, int[] foodPrices, int[] foodCountPerCustomer, int[] totalFoodCount) {

        if (currentMoney[index] >= foodPrices[optionNumber - 1]) {
            currentMoney[index] -= foodPrices[optionNumber - 1];
            foodCountPerCustomer[optionNumber - 1]++;
            totalFoodCount[optionNumber - 1]++;
            System.out.println("주문 목록에 추가했습니다.");
            return true;
        } else {
            int countOnFoodsUnpurchased = 0;

            for (int foodPrice : foodPrices) {
                if (foodPrice > currentMoney[index]) {
                    countOnFoodsUnpurchased++;
                }
            }

            if (countOnFoodsUnpurchased == foodPrices.length) {
                System.out.println("소지금이 부족하여 어떤 메뉴도 구매할 수 없습니다.");
            } else {
                System.out.println("금액이 부족합니다.");
            }

            return false;

        }
    }


    // 모든 회원에 대한 정보를 가지고 있는 배열에서 특정 회원의 정보만을 가져와 새로운 정수 배열을 반환한다.
    public static int[] getMyInfo(int arraySize, int index, int[] totalInfoArray) {
        int myInfoArray[] = new int[arraySize];

        int startIndex = arraySize * (index - 1);

        for (int  i =0; i < myInfoArray.length; i++) {
            myInfoArray[i] = totalInfoArray[startIndex + i];
        }

        return myInfoArray;
    }

    // 주문 종료 시, 내가 한 주문 내역과 채무 내역을 출력한다.
    public static void printOrderAndDebt(int index, int initialMoney, int currentMoney, int[] countOfFoodOrder, String[] foodNames, int[] foodPrices, int[] myFinancialInfo, int[] totalFoodCount) {
        String guide = "=== 주문표 ===";
        for (int i = 0; i < totalFoodCount.length; i++)
            if (totalFoodCount[i] > 0) {
                guide += "\n    "+ foodNames[i] + " x " + totalFoodCount[i] + " = " + (totalFoodCount[i] * foodPrices[i]) + "원";
            }

        guide += "\n\n" + "===" + index + "번째 손님===";
        guide += "\n    원래 가지고 있던 금액: " + initialMoney + "원";

        System.out.println(guide);

    }


    // 다음 유저에게 돈을 빌립니다.
    public static void borrowMoney(int index, int[] currentMoney, int[] myDebt, int[] myFinancialInfo, int[] financialInfo, String[] foodMenus, int[] foodPrices) {

        Scanner input = new Scanner(System.in);

        int indexOfLender = index;
        boolean isBorrow = false;

        while (true) {
            indexOfLender++;

            if (!isBorrow) {
                System.out.println("구매 금액이 부족합니다. 아래 옵션 중 하나를 선택하세요.");
                System.out.println("1. 돈을 빌린다.  2. 구매를 취소한다.");
            } else {
                System.out.println("이미 돈을 빌리셨습니다. 아래 옵션 중 하나를 선택하세요. ");
                System.out.println("1. 돈을 더 빌린다. 2. 빌리지 않는다.");
            }
            int answer = input.nextInt();

            if (answer == 2) {
                if (!isBorrow) {
                    System.out.println("구매를 취소합니다.");
                    break;
                } else {
                    System.out.println("더 빌리지 않습니다.");
                    break;
                }
            } else {
                System.out.print(indexOfLender + "번째 사람에게 얼마를 빌리시겠습니까? : ");
                int moneyToBorrow = input.nextInt();

                if (moneyToBorrow > currentMoney[indexOfLender - 1]) {
                    System.out.println("대여인의 금액보다 많이 빌릴 수 없습니다.");
                } else {
                    isBorrow = true;
                    myFinancialInfo[0] += moneyToBorrow; // 나의 재무 정보에서 채무 정보 증가
                    myDebt[indexOfLender - 1] += moneyToBorrow; // 누구에게 돈을 빌렸는지 debt table에도 반영
                    currentMoney[index - 1] += moneyToBorrow; // 나의 보유 현금 증가
                    currentMoney[indexOfLender - 1] -= moneyToBorrow; // 대여인의 보유 현금 감소

                    // 대여인의 채권 금액 증가
                    int[] lenderFinancialInfo = getMyInfo(2, indexOfLender, financialInfo);
                    lenderFinancialInfo[1] += moneyToBorrow;

                    String guide = "";
                    for (int i = 0; i < foodMenus.length; i++) {
                        if (foodPrices[i] <= currentMoney[index - 1]) {
                            guide += foodMenus[i] +" ";
                        }

                        if (i == foodMenus.length - 1) {
                            guide += "를(을) 사먹을 수 있습니다.";
                        }
                    }

                    if (guide.equals("")) {
                        System.out.println("사먹을 수 있는 게 없습니다.");
                    }

                    System.out.println(guide);
                }
        }
    }
    }

    // 빌린 돈을 갚습니다.
    public static void payBack(int[] myDebt, int[] debts, int[] myFinancialInfo, int[] currentMoney) {
        Scanner input = new Scanner(System.in);

        String guide = "";
        boolean doesHaveDebt = false;

        for (int i = 0; i < myDebt.length; i++) {
            if (myDebt[i] > 0) {
                guide += "    - " + (i + 1) + "번째 사람에게 " + myDebt[i] + "원\n";
                doesHaveDebt = true;
            }
        }
        if (doesHaveDebt) {
            System.out.println("===== 손님이 가지고 있는 빚은 다음과 같습니다. =====");
            System.out.println(guide);
            System.out.println("빚이 있습니다.");
            System.out.println("==========================================");

            int nth;
            while (true) {
                System.out.println("어느 사람에게 빚을 갚으시겠습니까? (1 ~ 15번)");
                System.out.print("빚 갚는 것을 중단하고 싶으면 -1을 입력하세요. :");
                nth = input.nextInt();

                if (nth == -1) {
                    break;
                } else if (nth < 1 || 15 < nth) {
                    System.out.println("1번부터 15번 손님까지에게 갚을 수 있습니다.");
                } else {
                    System.out.println(nth + "번째 사람에게 빚을 갚습니다.");
                }
            }

            if (nth != -1) {
                int[] debtOfCustomerToLend = getMyInfo(2, nth - 1, debts);

                while (true) {
                    System.out.print("얼마를 갚으시겠습니까? :");
                    int price = input.nextInt();

                    if (currentMoney[nth - 1] < price) {
                        System.out.println("보유 금액보다 더 많은 빚을 갚을 수 없습니다.");
                        System.out.println("현재 " + price + "원을 가지고 있습니다.");
                    } else {
                        myDebt[nth - 1] -= price; // nth에게 빌린 금액 감소
                        myFinancialInfo[0] -= price;  // 나의 빌린 금액 총액 감소
                        debtOfCustomerToLend[1] -= price; // 빌려준 nth 번째 사람의 빌려준 금액 감소
                        currentMoney[nth - 1] += price; // 빌려준 금액을 받았으니 nth 번째 사람이 가지고 있는 금액 증가
                        System.out.println("해당 사람에게 빚을 갚았습니다.");
                        break;
                    }
                }
            }
        } else {
            System.out.println("빚을 가지고 있지 않습니다.");
        }
    }

    // 구매한 음식 결제를 취소합니다.
    public static void refund(int index, boolean useOption9, String[] foodMenus, int[] foodPrices, int[] foodCountPerCustomer, int[] currentMoney, int[] totalFoodCount) {
        System.out.println("===== 환불을 진행합니다. =====");
        System.out.println("몇 번 음식을 환불하시겠습니까?");
        int foodNumber = chooseOption(useOption9, foodMenus, foodPrices, new String[0]);
        if (foodCountPerCustomer[foodNumber - 1] > 0) {
            currentMoney[index - 1] += foodPrices[foodNumber - 1] * foodCountPerCustomer[foodNumber - 1];
            foodCountPerCustomer[foodNumber - 1] = 0;
            totalFoodCount[foodNumber - 1] = 0;
            System.out.println("===== 환불이 완료되었습니다. =====");
        } else {
            System.out.println("해당 음식을 주문하지 않았었기 때문에, 환불할 수 없습니다.");
        }
    }
}
