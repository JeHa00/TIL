"""
[problem: 1110번]

- 주어진 숫자를 재조합하는 과정에서 원래 주어진 숫자가 나오는 반복 횟수 구하기 

- 출력값: 반복되는 횟수 


- 개선된 점:
    - 처음 코드는 입력값을 string으로 형변환 후, 인덱싱을 사용하여 각 자리 합과 새로운 수로 조합했다. 
    - 이 방법보다 %, // 인 나머지와 몫을 구하는 방법을 사용하여 구현하는 방법이 훨씬 간결했다. 

"""


def find_given_number(given_number: int) -> int:
    """

    주어진 인자값을 재조합하는 과정을 반복하여 

    다시 주어진 인자값이 나오는 횟수 찾기

    """

    times = 0
    number = given_number

    while True:

        times += 1

        sum = number // 10 + number % 10

        number = number % 10 * 10 + sum % 10

        if given_number == number:
            print(times)
            break


if __name__ == "__main__":
    given_number = int(input())
    find_given_number(given_number)
