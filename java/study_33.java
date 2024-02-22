// 반복문, 배열을 사용하지 않는다는 제약을 가지고 해당 문제 만들기  
// 반복문을 사용할 수 없기 때문에 재귀적 호출을 사용  
// 배열을 사용하지 못 하기 때문에 반드시 필요한 값만을 반환하도록 설계  

import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {
        quiz();
    }
    /*
    1. 두 숫자 입력받기

    2. 두 숫자의 덧셈 결과 맞추기
        - 첫 번째로 10번의 기회를 준다
            - 모두 다 정답 -> '정답입니다' 출력과 다음 문제로 진행
            - 정답 갯수와 오답 갯수가 섞여있는 경우
                - 정답 갯수가 더 많은 경우 -> '정답입니다' 출력과 다음 문제로 진행
                - 오답 갯수가 더 많은 경우 -> 또 10번의 기회를 준다. (최대 3번)
        - 오답 갯수가 더 많은 경우 -> 두 번째, 세 번째, 네 번째까지 10번의 기회를 더 받는다.
            - 각 기회마다 위 첫 번째 10번의 기회처럼 실행
        - 모든 기회를 받았어도 못 넘어가면 정답값을 출력하고, 프로그램을 종료하기

    3. 둘 중에 어떤 숫자가 더 큰지 고르기
        - 5번의 기회가 주어진다
            - 맞출 때마다 1번과 2번의 값이 바뀐다.
            - 정답 횟수가 더 많을 경우: '정답'이라는 문구와 함께 프로그램이 종료
            - 오답 횟수가 더 많을 경우: '오답'이라는 문구와 함께 답이 몇 번인지 공개 -> 다시 1번과 2번 중 고르라는 문제로 돌아감
    */

    public static boolean quiz() {
        Scanner input = new Scanner(System.in);

        // 두 숫자 입력 받기
        System.out.print("두 숫자를 입력합니다. 같은 숫자를 입력할 수 없습니다. 첫 번째 숫자를 입력하세요: ");
        int firstNumber = input.nextInt();

        System.out.print("두 숫자를 입력합니다. 같은 숫자를 입력할 수 없습니다. 두 번째 숫자를 입력하세요: ");
        int secondNumber = input.nextInt();
        if (secondNumber == firstNumber) {
            System.out.println("같은 숫자를 입력할 수 없습니다. 다른 숫자를 입력하세요: ");
            secondNumber = input.nextInt();
        }

        // 첫 번째 퀴즈
        boolean result = firstQuiz(firstNumber, secondNumber, 4, 10, 0, 0);

        // 두 번째 퀴즈
        if (result) {
            System.out.println("더 큰 숫자의 번호를 입력하세요.");
            result = secondQuiz(firstNumber, secondNumber, 5, 0, result);
        }

        return result;

    }

    public static boolean firstQuiz(int firstNumber, int secondNumber, int chance, int remainingCount, int winCount, int loseCount) {
        Scanner input = new Scanner(System.in);
        int answer = firstNumber + secondNumber;
        int inputAnswer;


        if (remainingCount > 0) {
            System.out.print(firstNumber + " + " + secondNumber + " = ");
            inputAnswer = input.nextInt();
            remainingCount--;

            if (answer == inputAnswer) {
                winCount++;
            } else {
                loseCount++;
            }

            return firstQuiz(firstNumber, secondNumber, chance, remainingCount, winCount, loseCount);

        } else { // remainingCount == 0
            if (winCount > loseCount) {
                System.out.println("정답입니다. 다음 문제로 넘어갑니다.");
                return true;
            } else {
                System.out.println("오답입니다.");
                if (chance == 0) {
                    System.out.println("정답은 " + (firstNumber + secondNumber) + "입니다.");
                    System.out.println("모든 기회를 썼으므로 프로그램을 종료합니다.");
                    return false;
                } else {
                    chance--;
                    remainingCount = 10;
                    return firstQuiz(firstNumber, secondNumber, chance, remainingCount, 0, 0);
                }
            }
        }
    }

    public static boolean secondQuiz(int firstNumber, int secondNumber, int chance, int winCount, boolean result) {
        Scanner input = new Scanner(System.in);
        int answer;

        if (chance == 5 && result) {
            System.out.println("1. " + firstNumber  + '\n' + "2. " + secondNumber);
            if (firstNumber > secondNumber) {
                answer = 1;
            } else {
                answer = 2;
            }

            // 값 입력하기 및 횟수 차감
            chance--;

            // 정답과 비교한 후 횟수 세기, 다음에 낼 문제 종료 정하기
            if (input.nextInt() == answer) {
                winCount++;
                result = true;
            } else {
                result = false;
            }

            return secondQuiz(firstNumber, secondNumber, chance, winCount, result);
        }

        else if (chance != 0 && chance % 2 == 0) {
            if (result) {
                System.out.println("1. " + secondNumber + '\n' + "2. " + firstNumber);
                if (firstNumber > secondNumber) {
                    answer = 2;
                } else {
                    answer = 1;
                }
            } else {
                System.out.println("1. " + firstNumber  + '\n' + "2. " + secondNumber);
                if (firstNumber > secondNumber) {
                    answer = 1;
                } else {
                    answer = 2;
                }
            }

            // 값 입력하기 및 횟수 차감
            chance--;

            // 정답과 비교한 후 횟수 세기, 다음에 낼 문제 종료 정하기
            if (input.nextInt() == answer) {
                winCount++;
                result = true;
            } else {
                result = false;
            }

            return secondQuiz(firstNumber, secondNumber, chance, winCount, result);
        }

        else if (chance != 5 && chance % 2 == 1) {
            if (result) {
                System.out.println("1. " + firstNumber  + '\n' + "2. " + secondNumber);
                if (firstNumber > secondNumber) {
                    answer = 1;
                } else {
                    answer = 2;
                }
            } else {
                System.out.println("1. " + secondNumber + '\n' + "2. " + firstNumber);
                if (firstNumber > secondNumber) {
                    answer = 2;
                } else {
                    answer = 1;
                }
            }

            // 값 입력하기 및 횟수 차감
            chance--;

            // 정답과 비교한 후 횟수 세기, 다음에 낼 문제 종료 정하기
            if (input.nextInt() == answer) {
                winCount++;
                result = true;
            } else {
                result = false;
            }

            return secondQuiz(firstNumber, secondNumber, chance, winCount, result);
        }

        // chance == 0
        else {
            if (winCount >= 3) {
                System.out.println("정답입니다.");
                return true;
            } else {
                System.out.println("오답입니다.");
                return secondQuiz(firstNumber, secondNumber, 5, 0, true);
            }
        }
    }

}
