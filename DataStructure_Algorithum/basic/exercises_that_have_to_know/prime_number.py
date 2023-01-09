def checkPrimeNumber(input_number: int) -> bool:
    """
    입력값이 소수인지 판별하기
    소수이면 True, 아니면 False를 반환
    """

    n = input_number

    # n = 6

    for i in range(2, int(n ** (1/2)) + 1):
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
