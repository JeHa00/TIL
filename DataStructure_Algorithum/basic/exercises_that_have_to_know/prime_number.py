"""
소수란 1과 자기 자신 이외의 약수를 가지지 않는 수를 말한다. 
그래서 소수를 판단할 때, 1과 자기 자신 이외의 약수를 가지는지를 판단하면 된다.
이 소수 외에 많은 수들은 대칭 구조를 가진다. 약수들을 구하려는 수가 n이라고 할 때 math.sqrt(n) 을 기준으로 수의 좌우가 대칭된다. 
그래서 한 쪽만 약수가 존재하는지 검사하면 다른 쪽은 당연히 알 수 있다. 
"""

import math

def checkPrimeNumber(input_number: int) -> bool:
    """
    입력값이 소수인지 판별하기
    소수이면 True, 아니면 False를 반환
    """

    n = input_number

    # n = 6

    # 2, 3, 4, 5, 6 으로 나눠진다. 그런데 math.sqrt(6)으로 정수 2까지만 판별하면 된다. 
    # 이 루트까지 진행했을 때, 약수가 없으면 소수이고, 있으면 소수가 아니다. 

    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0:
            return False

    return True


def getPrimeNumber(input_number: int):
    """
    주어진 범위 내의 소수 구하기
    """

    numbers = []

    for i in range(1, input_number + 1):
        if checkPrimeNumber(i) is True:
            numbers.append(i)

        else:
            continue

    print(numbers)


if __name__ == "__main__":
    n = int(input('소수 판별할 정수값 입력: '))
    print(checkPrimeNumber(n))
    getPrimeNumber(n)
