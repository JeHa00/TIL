"""
[10870 - 피보나치 수]
F(n) = F(n-1) + F(n-2)


입력값
- n  (n >= 2)

출력값
- n번째 피보나치 수를 출력
"""

import sys

# 재귀 사용 -> RecursionError: maximum recursion depth exceeded in comparison
def fibonacci_recursive(n_th: int) -> int:

    if n_th <= 1:
        return 1 
    else:
        return fibonacci_recursive(n_th-1) + fibonacci_recursive(n_th-2)


# dp 사용: memorization -> 
def fibonacci_dp(n_th: int) -> int: 

    n = n_th + 1

    result: list = [0 for _ in range(n)]
    for i in range(n):
        if i == 0:
            result[i] = 0
        elif i == 1 or i == 2:
            # i == 1 or 2를 하면 2가 인식되지 않는다.
            result[i] = 1
        else:
            result[i] = result[i-1] + result[i-2]
    return result[n_th]

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    # print(fibonacci_recursive(N))
    print(fibonacci_dp(N))