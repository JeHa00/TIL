package Week13;

import java.util.Scanner;

public class Week13 {
    public static void main(String[] args) {
        program();
    }

    public static void program() {
        // 프로그램 종료 관련 플래그 변수
        boolean END = false;

        // 월-일 정보
        int month = 1;
        int day = 1;
        int DAYS_IN_A_MONTH = 31; // 한 달은 31일로 되어 있다고 가정

        // 손님 수 관련 정보
        int DAILY_MAX_CUSTOMERS = 10; // 일일 최대 손님 수
        int customers[] = new int[3]; // 오늘로 이전된 손님 수, 오늘 입력받은 손님, 내일로 이전할 손님 수

        // 옵션 정보
        int FOOD_MENU_COUNT = 3;
        String[] foodOptionNames = {"베이컨", "아이스크림", "감자콩"};
        String[] otherOptionNames = {"구매 종료 및 다음 사람", "프로그램 종료", "포인트 적립", "다음 날", "영수증 조회"};

        // 달력 배열
        String[] month1 = new String[DAYS_IN_A_MONTH];
        String[] month2 = new String[DAYS_IN_A_MONTH];
        String[] month3 = new String[DAYS_IN_A_MONTH];
        String[] month4 = new String[DAYS_IN_A_MONTH];
        String[] month5 = new String[DAYS_IN_A_MONTH];
        String[] month6 = new String[DAYS_IN_A_MONTH];
        String[] month7 = new String[DAYS_IN_A_MONTH];
        String[] month8 = new String[DAYS_IN_A_MONTH];
        String[] month9 = new String[DAYS_IN_A_MONTH];
        String[] month10 = new String[DAYS_IN_A_MONTH];
        String[] month11 = new String[DAYS_IN_A_MONTH];
        String[] month12 = new String[DAYS_IN_A_MONTH];

        while (!END) {// 프로그램 실행 루프

            int nth = 1; // 일일 몇 번째 손님인지 나타내는 정보

            customers[1] = getTotalPersonDaily(month, day); // 일일 손님 수

            int totalMenuCountDaily[] = new int[FOOD_MENU_COUNT]; // 일일 각 메뉴 주문 갯수

            // 월별 영수증
            String receiptsOfDaysOnMonth[] = getReceipts(month, month1, month2, month3, month4, month5, month6, month7, month8, month9, month10, month11, month12);

            // 일일 영수증
            String[] receiptOfEachCustomerDaily = new String[DAILY_MAX_CUSTOMERS + 1];
            receiptOfEachCustomerDaily[0] = "\n========== [" + month + "월 " + day + "일] ==========";

            boolean moveTomorrow = false; // 다음 날 이동 유무를 나타내는 정보

            while (!END && !moveTomorrow) {// 일일 영업 루프
                System.out.println("----------" + nth + "번째 손님입니다. ----------");
                boolean customerMoved; // 오늘로 넘어온 손님인지 나타내는 정보
                int point = 0;

                if (nth <= customers[0]) {
                    customerMoved = true;
                } else {
                    customerMoved = false;
                }

                int[] foodPrices = getFoodPrices(customerMoved); // 메뉴 가격 정보

                int moneyOfCustomer[] = new int[2];
                moneyOfCustomer[0] = inputMoney(); // 초기 금액 보유량
                moneyOfCustomer[1] = moneyOfCustomer[0]; // 현재 금액 보유량

                int eachMenuCountOfCustomer[] = new int[FOOD_MENU_COUNT]; // 손님 메뉴 주문 갯수


                while (true) { // 고객 1명의 주문 루프
                    int optionNumber = inputOptionNumber(foodOptionNames, otherOptionNames, foodPrices);

                    if (optionNumber == 1) { // 베이컨
                        purchase(optionNumber, eachMenuCountOfCustomer, totalMenuCountDaily, moneyOfCustomer, foodPrices);

                    } else if (optionNumber == 2) { // 아이스크림
                        purchase(optionNumber, eachMenuCountOfCustomer, totalMenuCountDaily, moneyOfCustomer, foodPrices);

                    } else if (optionNumber == 3) { // 감자콩
                        purchase(optionNumber, eachMenuCountOfCustomer, totalMenuCountDaily, moneyOfCustomer, foodPrices);

                    } else if (optionNumber == 4) { // 구매 종료 및 다음 사람
                        boolean result = checkItemAmount(eachMenuCountOfCustomer);
                        if (result) {
                            // nth 번째 손님 영수증 만들기
                            receiptOfEachCustomerDaily[nth] = receiptOnCustomer(customerMoved, nth, moneyOfCustomer, eachMenuCountOfCustomer, foodPrices, point, foodOptionNames);
                            nth++;
                            break;
                        }

                    } else if (optionNumber == 5) { // 프로그램 종료
                        boolean result = checkItemAmount(eachMenuCountOfCustomer);
                        if (result) {
                            System.out.println("프로그램을 종료합니다.");
                            END = true;
                            break;
                        }

                    } else if (optionNumber == 6) { // 포인트 적립
                        int[] totalPricePerMenu = getTotalPricePerMenu(eachMenuCountOfCustomer, foodPrices);
                        point = calculatePoint(totalPricePerMenu);

                    } else if (optionNumber == 7) { // 다음 날
                        boolean result = checkItemAmount(eachMenuCountOfCustomer);
                        if (result) {
                            moveTomorrow = true;

                            // 일일 손님 수 및 전날로부터 이전된 손님 수 정보
                            customers[1] = customers[0] + customers[1] - DAILY_MAX_CUSTOMERS;


                            // 최대 손님 수에 해당하는 순서가 오고 나서 바로 다음 날을 누르면 영수증이 만들어지지 않으므로 아래 작업이 필요
                            receiptOfEachCustomerDaily[0] += receiptOnTotalOrder(totalMenuCountDaily, foodPrices, foodOptionNames);
                            receiptOfEachCustomerDaily[nth] = receiptOnCustomer(customerMoved, nth, moneyOfCustomer, eachMenuCountOfCustomer, foodPrices, point, foodOptionNames);

                            // 오늘 날짜 영수증 만들기
                            receiptsOfDaysOnMonth[day] = makeReceiptToday(receiptOfEachCustomerDaily);
                            break;
                        }

                    } else { // 영수증 조회
                        int[] monthDay = chooseMonthDay();
                        String[] selectedMonthReceipt = getReceipts(monthDay[0], month1, month2, month3, month4, month5, month6, month7, month8, month9, month10, month11, month12);
                        System.out.println(selectedMonthReceipt[monthDay[1]]);
                    }
                }
            }

            // 일일 영업 종료 시, 월-일 정보 업데이트
            day++;
            if (day == DAYS_IN_A_MONTH) {
                month++;
                day = 1;
            }
            customers[0] = customers[1]; // 다음 날로 이동됨에 따라, 내일로 이전할 손님 수 정보를 오늘로 이전할 손님 수로 업데이트
        }


    }

    // 일일 손님 수를 입력받는다
    public static int getTotalPersonDaily(int month, int day) {
        Scanner input = new Scanner(System.in);
        System.out.print(month + "월 " + day + "일 인원 수: ");
        return input.nextInt();
    }

    // 손님이 가지고 있는 돈을 입력받는다
    public static int inputMoney() {
        // 가지고 있는 금액을 입력받아, 그 값을 반환하는 역할
        int MAX_MONEY = 100000;
        int money = MAX_MONEY + 1;

        Scanner input = new Scanner(System.in);

        while (money > MAX_MONEY) {
            System.out.print("얼마를 가지고 있으신가요?: ");
            money = input.nextInt();
            if (money > MAX_MONEY) {
                System.out.println(MAX_MONEY + "원을 초과하여 입력할 수 없습니다.");
            }
        }

        return money;

    }

    // 이전된 손님인지 아닌지에 따라 음식 정보를 반환하는 함수
    public static int[] getFoodPrices(boolean personMovedToday) {
        int foodMenuPrices[] = {5000, 3000, 1000};
        double DISCOUNT = 0.05;

        if (!personMovedToday) {
            return foodMenuPrices;
        } else {
            for (int i = 0; i < foodMenuPrices.length; i++) {
                foodMenuPrices[i] = (int) (foodMenuPrices[i] * (1 - DISCOUNT));
            }
            return foodMenuPrices;
        }
    }

    // 주문 옵션 번호를 입력받는다
    public static int inputOptionNumber(String[] foodOptionNames, String[] otherOptionNames,  int[] foodPrices) {
        // 메뉴 보여주고, 선택한 번호를 반환하는 역할
        Scanner input = new Scanner(System.in);
        System.out.println();
        for (int i = 0; i < foodOptionNames.length; i++) { // 음식 메뉴 선택지
            System.out.println((i + 1) + ". " + foodOptionNames[i] + " " + foodPrices[i] + "원");
        }

        for (int i = 0; i < otherOptionNames.length; i++) { // 음식 외 메뉴 선택지
            System.out.println((i + foodOptionNames.length + 1) + ". " + otherOptionNames[i]);
        }
        System.out.print("\n선택할 메뉴 번호: ");
        return input.nextInt();
    }

    // 음식 구매 관련 유효성 검증을 실행한 후, 통과하면 음식을 구매한다.
    public static void purchase(int optionNumber, int[] eachMenuCountOfCustomer, int[] totalMenuCountDaily, int[] moneyOfCustomer, int[] foodPrices) {
        if (moneyOfCustomer[1] >= foodPrices[optionNumber - 1]) {
            eachMenuCountOfCustomer[optionNumber - 1]++;
            totalMenuCountDaily[optionNumber - 1]++;
            moneyOfCustomer[1] -= foodPrices[optionNumber - 1];
        } else {
            System.out.println("해당 음식은 보유 금액이 부족하여 구매할 수 없습니다.");
        }
    }

    // 손님의 각 메뉴 전체 금액을 계산하는 함수
    public static int[] getTotalPricePerMenu(int[] eachCountOfMenu, int[] foodMenuPrices) {
        int TOTAL_MENU_COUNT = 3;
        int totalPricePerMenu[] = new int[TOTAL_MENU_COUNT];
        for (int i = 0; i < foodMenuPrices.length; i++) {
            totalPricePerMenu[i] = foodMenuPrices[i] * eachCountOfMenu[i];
        }

        return totalPricePerMenu;
    }

    // getTotalPricePerMenu에서 반환된 배열을 가지고 포인트를 계산하는 함수
    public static int calculatePoint(int[] totalPricePerMenu) {
        double totalPrice = 0;
        for (int price : totalPricePerMenu) {
            totalPrice += price;
        }
        return (int) (totalPrice * 0.01);
    }

    // 일일 영수증 정보를 모아놓는 배열의 값들을 한 문자열로 만드는 함수
    public static String makeReceiptToday(String[] receiptOfEachCustomerDaily) {
        for (int i =1; i < receiptOfEachCustomerDaily.length; i++) {
            receiptOfEachCustomerDaily[0] += receiptOfEachCustomerDaily[i];
        }

        return receiptOfEachCustomerDaily[0];
    }

    // 영수증 내역에서 손님의 구매 내역 부분을 만드는 함수
    public static String receiptOnCustomer(boolean customerMoved, int nth, int[] moneyOfCustomer, int[] eachMenuCountOfCustomer, int[] foodPrices, int point, String[] foodOptionNames) {
        int[] totalPricePerMenu = getTotalPricePerMenu(eachMenuCountOfCustomer, foodPrices);

        String receipt = "\n\n-----------" + nth + "번째 손님 -----------";
        if (customerMoved) {
            receipt += "\n    5% 할인 적용 O";
        }
        receipt += "\n    원래 가지고 있던 금액: " + moneyOfCustomer[0] + "원";

        for (int i = 0; i < eachMenuCountOfCustomer.length; i++) {
            if (eachMenuCountOfCustomer[i] > 0) {
                receipt += "\n    " + foodOptionNames[i] + " x " + eachMenuCountOfCustomer[i] + " = " + totalPricePerMenu[i] + "원";
            }
        }

        if (point > 0) {
            receipt += "\n    포인트: " + point + "원";
        }

        receipt += "\n    현재 가지고 있는 금액: " + moneyOfCustomer[1] + "원\n";

        return receipt;
    }

    // 영수증 내역에서 일일 전체 주문 현황을 만드는 함수
    public static String receiptOnTotalOrder(int[] totalMenuCountDaily, int[] foodPrices, String[] foodOptionNames) {
        String receipt = "\n----------- 주문표 -----------";

        for (int i = 0; i < totalMenuCountDaily.length; i++) {
            if (totalMenuCountDaily[i] > 0) {
                receipt += "\n    " + foodOptionNames[i] + " x " + totalMenuCountDaily[i] + " = " + (totalMenuCountDaily[i] * foodPrices[i]) + "원";
            }
        }

        receipt += "\n";

        return receipt;
    }

    // 8번 영수증 조회 시, 월-일을 선택하는 함수
    public static int[] chooseMonthDay() {
        Scanner input = new Scanner(System.in);
        int monthDay[] = new int[2];

        System.out.print("몇 월 영수증을 출력하시겠습니까?: ");
        monthDay[0] = input.nextInt();

        System.out.print(monthDay[0] + "월의 몇 일자를 출력하시겠습니까?: ");
        monthDay[1] = input.nextInt();
        return monthDay;
    }

    // 입력된 달에 따라 해당 달의 영수증 배열을 반환하는 함수
    public static String[] getReceipts(int month, String[] month1, String[] month2, String[] month3, String[] month4, String[] month5, String[] month6, String[] month7, String[] month8, String[] month9, String[] month10, String[] month11, String[] month12) {

        if (month == 1) {
            return month1;
        } else if (month == 2) {
            return month2;
        } else if (month == 3) {
            return month3;
        } else if (month == 3) {
            return month4;
        } else if (month == 4) {
            return month5;
        } else if (month == 5) {
            return month5;
        } else if (month == 6) {
            return month6;
        } else if (month == 7) {
            return month7;
        } else if (month == 8) {
            return month8;
        } else if (month == 9) {
            return month9;
        } else if (month == 10) {
            return month10;
        } else if (month == 11) {
            return month11;
        } else {
            return month12;
        }
    }

    // 구매 수량의 유효성을 검증하는 함수
    public static boolean checkItemAmount(int[] eachMenuCountOfCustomer) {
        int iceCreamAmount = eachMenuCountOfCustomer[1];
        int potatoBeanAmount = eachMenuCountOfCustomer[2];
        boolean result = true;

        if (iceCreamAmount % 10 != 0) {
            System.out.println("아이스크림 구매 수량이 10의 배수가 아닙니다.");
            System.out.println("현재 아이스크림 수량은 " + iceCreamAmount + "개입니다.");
            System.out.println("아이스크림을 " + (10 - iceCreamAmount % 10) + "개를 추가 구매해야 합니다.");
            result = false;
        }

        if (potatoBeanAmount < iceCreamAmount) {
            System.out.println("감자콩의 구매 수량이 아이스크림보다 적습니다.");
            System.out.println("현재 아이스크림 수량은 " + iceCreamAmount + "개이고, 현재 감자콩의 구매 수량은 " + potatoBeanAmount + "개입니다.");
            System.out.println("감자콩을 " + (iceCreamAmount - potatoBeanAmount) + "개 더 구매해야 합니다.");
            result = false;

        }
        return result;
    }
}
