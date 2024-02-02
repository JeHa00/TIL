/*
문제:
	총 네 명이 가위바위보를 하려 한다.
	이름을 입력받고, 각각 가위, 바위, 보를 낼 수 있게 만든 다음,
	누가 1등인지, 2등인지, 3등인지를 나타내시오.
	단, 무승부는 없으며 한 판당 무조건 한 사람만 지게 됩니다.
    한 판당 지면 다음 판에 참여할 수 없다. 
    - 1,2,3 등을 가리기 위해서 총 3판을 치룬다.
    - 무승부는 없기 때문에 가위, 가위, 가위, 보 또는 보 보 보 주먹 이런 식으로 내게 된다.
    - 먼저 가위바위보를 낸 종류에 따라 다음 사람이 낼 수 있는 가위바위보 경우의 수가 제한된다.

    문법 제한: 반복문, 배열, 클래스를 사용할 수 없다. 

*/

import java.util.Scanner;

public class Exercise {
    public static void main(String[] args) {
        playRCP();
    }

    public static void playRCP() {
        // 이름 입력 받기
        String person1Name = getName(1);
        String person2Name = getName(2);
        String person3Name = getName(3);
        String person4Name = getName(4);

        // 가위 바위 보 토너먼트 시작하기
        startTournamentOfRCP(person1Name, person2Name, person3Name, person4Name);
    }

    public static String getName(int personNumber) {
        Scanner input = new Scanner(System.in);

        // 이름 입력 받기
        if (personNumber == 1) {
        System.out.print("첫 번째 사람 이름: ");
        String person1Name = input.next();
        return person1Name;
        }
        else if (personNumber == 2) {
        System.out.print("두 번째 사람 이름: ");
        String person2Name = input.next();
            return person2Name;
        }
        else if (personNumber == 3) {
            System.out.print("세 번째 사람 이름: ");
            String person3Name = input.next();
            return person3Name;
        }
        else {
            System.out.print("네 번째 사람 이름: ");
            String person4Name = input.next();
            return person4Name;
        }
    }

    public static void startTournamentOfRCP(String person1Name, String person2Name, String person3Name, String person4Name) {
        // RCP 첫 번째 판
        String loser = getLoser(person1Name, person2Name, person3Name, person4Name);

        // RCP 두 번째 판
        String firstStageWinner1 = resetPersonName(1, person1Name, person2Name, person3Name, person4Name, loser);
        String firstStageWinner2 = resetPersonName(2, person1Name, person2Name, person3Name, person4Name, loser);
        String firstStageWinner3 = resetPersonName(3, person1Name, person2Name, person3Name, person4Name, loser);

        String thirdWinner = getThirdWinner(firstStageWinner1, firstStageWinner2, firstStageWinner3);

        // RCP 세 번째판
        String secondStageWinner1 = resetPersonName(1, firstStageWinner1, firstStageWinner2, firstStageWinner3, "", thirdWinner);
        String secondStageWinner2 = resetPersonName(2, firstStageWinner1, firstStageWinner2, firstStageWinner3, "", thirdWinner);

        String secondWinner = getSecondWinner(secondStageWinner1, secondStageWinner2);
        String firstWinner = getFirstWinner(secondStageWinner1, secondStageWinner2, secondWinner);

        // 등수 출력
        System.out.println("1등: " + firstWinner);
        System.out.println("2등: " + secondWinner);
        System.out.println("3등: " + thirdWinner);

    }

    // 각 사람마다 가위 바위 보를 입력 받기
    public static String getRCP(String personName, boolean rock, boolean scissors, boolean paper) {
        Scanner input = new Scanner(System.in);

        String rcp = "";
        if (scissors == true) {
            rcp += "가위 ";
        }

        if (rock == true) {
            rcp += "바위 ";
        }

        if (paper == true) {
            rcp += "보 ";
        }

        System.out.print(personName + "님 차례입니다. " + rcp + "중 하나를 내세요. : ");
        return input.next();
    }

    // 꼴등 가리기
    public static String getLoser(String person1Name, String person2Name, String person3Name, String person4Name) {

        String person1RCP = getRCP(person1Name, true, true, true);
        String person2RCP = getRCP(person2Name, true, true, true);
        String person3RCP = "";

        String loser = "";

        if (person1RCP.equals(person2RCP)) { // 첫 번째 사람과 두 번째 사람의 가위바위보가 같은 경우
            if (person1RCP.equals("가위")) {
                person3RCP = getRCP(person3Name, false, true, true);

                if (person3RCP.equals("가위")) {
                    getRCP(person4Name, false, false, true); // 가위 가위 가위 보
                    loser = person4Name;
                }
                else if (person3RCP.equals("보")) {
                    getRCP(person4Name, false, true, false); // 가위 가위 보 가위
                    loser = person3Name;
                }
            } else if (person1RCP.equals("바위")) { //  바위 바위
                if (person3RCP.equals("바위")) {
                    getRCP(person4Name, false, true, false); // 바위 바위 바위 가위
                    loser = person4Name;
                }
                else if (person3RCP.equals("가위")) {
                    getRCP(person4Name, true, false, false); // 바위 바위 가위 바위
                    loser = person3Name;
                }
            } else {
                if (person3RCP.equals("보")) {
                    getRCP(person4Name, true, false, false); // 보 보 보 바위
                    loser = person4Name;
                }
                else if (person3RCP.equals("바위")) {
                    getRCP(person4Name, false, false, true); // 보 보 바위 보
                    loser = person3Name;
                }
            }
        } else { // 첫 번째 사람과 두 번째 사람의 가위바위보가 다른 경우
            if (person1RCP.equals("가위")) {
                if (person2RCP.equals("바위")) { // 가위 바위 바위 바위
                    loser = person1Name;
                    getRCP(person3Name, true, false, false);
                    getRCP(person4Name, true, false, false);
                }
                else if (person2RCP.equals("보")) { // 가위 보 가위 가위
                    loser = person2Name;
                    getRCP(person3Name, false, true, false);
                    getRCP(person4Name, false, true, false);
                }
            }
            else if (person1RCP.equals("바위")) {
                if (person2RCP.equals("보")) { // 바위 보 보 보
                    loser = person1Name;
                    getRCP(person3Name, false, false, true);
                    getRCP(person4Name, false, false, true);
                }
                else if (person2RCP.equals("가위")) { // 바위 가위 바위 바위
                    loser = person2Name;
                    getRCP(person3Name, true, false, false);
                    getRCP(person4Name, true, false, false);
                }
            }
            else {
                if (person2RCP.equals("가위")) { // 보 가위 가위 가위
                    loser = person1Name;
                    getRCP(person3Name, false, true, false);
                    getRCP(person4Name, false, true, false);

                }
                else if (person2RCP.equals("바위")) { // 보 바위 보 보
                    loser = person2Name;
                    getRCP(person4Name, false, false, true);
                    getRCP(person3Name, false, false, true);
                }
            }

        }
        return loser;
    }

    // person1, person2, person3 이름 재설정
    public static String resetPersonName(int personNumber, String person1Name, String person2Name, String person3Name, String person4Name, String loser) {
        if (loser.equals(person1Name)) {
            person1Name = person2Name;
            person2Name = person3Name;
            person3Name = person4Name;
        } else if (loser.equals(person2Name)) {
            person2Name = person3Name;
            person3Name = person4Name;
        } else if (loser.equals(person3Name)) {
            person3Name = person4Name;
        }

        if (personNumber == 1) {
            return person1Name;
        } else if (personNumber == 2) {
            return person2Name;
        } else {
            return person3Name;
        }
    }

    // 3등 가리기
    public static String getThirdWinner(String person1Name, String person2Name, String person3Name) {

        String person1RCP = getRCP(person1Name, true, true, true);
        String person2RCP = getRCP(person2Name, true, true, true);

        String loser = "";

        if (person1RCP.equals(person2RCP)) { // 첫 번째 사람과 두 번째 사람의 가위바위보가 같은 경우
            loser = person3Name;
            if (person1RCP.equals("가위")) { // 가위 가위 보
                getRCP(person3Name, false, false, true);
            } else if (person1RCP.equals("바위")) { //  바위 바위 가위
                getRCP(person3Name, false, true, false);
            } else { // 보 보 주먹
            getRCP(person3Name, true, false, false);
            }
        } else { // 첫 번째 사람과 두 번째 사람의 가위바위보가 다른 경우
            if (person1RCP.equals("가위")) {
                if (person2RCP.equals("바위")) { // 가위 바위 바위
                    loser = person1Name;
                    getRCP(person3Name, true, false, false);
                } else if (person2RCP.equals("보")) { // 가위 보 보
                    loser = person2Name;
                    getRCP(person3Name, false, false, true);
                }
            } else if (person1RCP.equals("바위")) {
                if (person2RCP.equals("보")) { // 바위 보 보
                    loser = person1Name;
                    getRCP(person3Name, false, false, true);
                } else if (person2RCP.equals("가위")) { // 바위 가위 바위
                    loser = person2Name;
                    getRCP(person3Name, true, false, false);
                }
            } else {
                if (person2RCP.equals("가위")) { // 보 가위 가위
                    loser = person1Name;
                    getRCP(person3Name, false, true, false);
                } else if (person2RCP.equals("바위")) { // 보 바위 보
                    loser = person2Name;
                    getRCP(person3Name, false, false, true);
                }
            }

        }

        return loser;
    }

    // 1등 2등 가리기
    public static String getSecondWinner(String person1Name, String person2Name) {

        String person1RCP = getRCP(person1Name, true, true, true);
        String person2RCP = "";

        String loser = "";

        if (person1RCP.equals("가위")) {
            person2RCP = getRCP(person2Name, true, false, true);
            if (person2RCP.equals("바위")) { // 가위 바위
                loser = person1Name;
            } else { // 가위 주먹
                loser = person2Name;
            }
        } else if (person1RCP.equals("바위")) {
            person2RCP = getRCP(person2Name, false, true, true);
            if (person2RCP.equals("보")) { // 바위 보
                loser = person1Name;
            } else { // 바위 가위
                loser = person2Name;
            }
        } else {
            person2RCP = getRCP(person2Name, true, true, false);
            if (person2RCP.equals("가위")) {
                loser = person1Name;
            } else {
                loser = person2Name;
            }
        }

        return loser;
    }

    // 1등 정보 얻기
    public static String getFirstWinner(String person1Name, String person2Name, String loser) {

        if (loser.equals(person1Name)) {
            return person2Name;
        } else {
            return person1Name;
        }
    }
}

