public class Practice {
    public static void main(String[] args) {

        // int 및 문자열 문제
        int totalPerson = 10;
        int moneyHaving = 100000;

        String string1 = "은 ";
        String string2 = "는 ";
        String string4 = "을 ";
        String string5 = "의 ";
        String won = "원";
        String achi = "어치";

        String ifsum = "합치면";
        String lack = "부족한";
        String toPay = "내야만";

        String food1 = "떡볶이";
        int food1Price = 6470000;

        String food2 = "감자튀김";
        int food2Price = 6988000;

        String food3 = "모듬 튀김";
        int food3Price = 666777;

        String food4 = "김말이 튀김";
        int food4Price = 8900000;

        String food5 = "계란 튀김";
        int food5Price = 70000;

        String food6 = "순대";
        int food6Price = 438000;

        String food7 = "우동";
        int food7Price = 660000;

        String food8 = "카라멜 마끼아또";
        int food8Price = 9900;

        String food9 = "믹스 커피";
        int food9Price = 999999;

        String food10 = "볶음밥";
        int food10Price = 690000;

        String food11 = "김치 볶음밥";
        int food11Price = 5800080;

        String food12 = "계란 볶음밥";
        int food12Price = 4385000;

        String food13 = "음료수";
        int food13Price = 190000;

        String food14 = "과자";
        int food14Price = 150000;

        int totalPrice = food1Price + food2Price + food3Price + food4Price + food5Price + food6Price + food7Price + food8Price + food9Price + food10Price + food11Price + food12Price + food13Price + food14Price;

        int moneyToPay = totalPrice - moneyHaving;

        System.out.println("총 " + totalPerson + string5 + "사람이 다 함께 음식점에 도착하였다.");
        System.out.println("총 " + totalPerson + "명이 모은 금액" + string1 + moneyHaving + won + "이다.");

        System.out.println();

        System.out.println(food1 + string2 + food1Price + won + achi + ",");
        System.out.println(food2 + string1 + food2Price + won + achi + ",");
        System.out.println(food3 + string1 + food3Price + won + achi + ",");
        System.out.println(food4 + string1 + food4Price + won + achi + ",");
        System.out.println(food5 + string1 + food5Price + won + achi + ",");
        System.out.println(food6 + string2 + food6Price + won + achi + ",");
        System.out.println(food7 + string1 + food7Price + won + achi + ",");
        System.out.println(food8 + string2 + food8Price + won + achi + ",");
        System.out.println(food9 + string2 + food9Price + won + achi + ",");
        System.out.println(food10 + string1 + food10Price + won + achi + ",");
        System.out.println(food11 + string1 + food11Price + won + achi + ",");
        System.out.println(food12 + string1 + food12Price + won + achi + ",");
        System.out.println(food13 + string2 + food13Price + won + achi + ",");
        System.out.println(food14 + string2 + food14Price + won + achi + "를 사먹게 되었다.");

        System.out.println();

        System.out.println("위 음식" + string5 + "가격" + string4 + "모두 " + ifsum + totalPrice + won + "이고, ");
        System.out.println("현재 " + moneyToPay + won + "이 " + lack + " 상태다.");
        System.out.println("따라서, " + totalPerson + "명" + string1 + moneyToPay + won + string4 + "더 " + toPay +" 한다.");


    }
}
