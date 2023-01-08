"""
[10872 - factorial]
- 0보다 큰 정수 N이 주어졌을 때 N!의 값을 구하는 프로그램 작성하기
- 입력: N
- 출력: N!
"""

import sys

def factorial_01(n: int) -> int:
    """
    RecursionError가 발생된 코드
    """


    if n == 1:
        return 1 
    else:
        return n * factorial_01(n-1)


def factorial_02(n: int) -> int:

    if n == 1 or n == 0:
        return 1 
    else:
        return n * factorial_02(n-1)


def factorial_dp(n: int) -> int: 

    result = [0 for _ in range(n+1)]

    for i in range(n+1):
        if i == 0 or i == 1:
            result[i] = 1
        else: 
            result[i] = i * result[i-1]

    return result[-1]

if __name__ == "__main__":
    N = int(sys.stdin.readline().strip())
    print(factorial_01(N))
    print(factorial_02(N))
    print(factorial_dp(N))