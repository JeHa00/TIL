"""
가장 긴 바이토닉 부분 수열 (11054)
- 주어진 수열에서 가장 긴 바이토닉 부분 수열의 길이를 찾기

바이토닉 수열
- 점차 값이 올라가다가 찍고, 점차 값이 내려오는 것  
- 계속 값이 증가만 하는 것
- 계속 값이 감소만 하는 것

바이토닉 수열이 아닌 조건
- 값이 올라가다가 찍고 내려온 후, 다시 올라가는 것

입력값
- 수열 A의 크기: N
- 수열 A의 성분들

출력값
- 주어진 수열에서 가장 긴 바이토닉 부분 수열 길이를 출력
"""


import sys
import copy

def main(number_of_counts: int) -> int: 
    """
    증가하다가 감소: 점차 증가 + 점차 감소
    """

    A = list(map(int, sys.stdin.readline().split()))
    A_reverse = list(reversed(A))

    result = [1] * number_of_counts
    increase_result = copy.deepcopy(result)
    decrease_result = copy.deepcopy(result)

    for i in range(number_of_counts):
        for j in range(i):
            if A[j] < A[i]:
                increase_result[i] = max(increase_result[i], increase_result[j]+1)
            
            if A_reverse[j] < A_reverse[i]:
                decrease_result[i] = max(decrease_result[i], decrease_result[j]+1)
    
    decrease_result.reverse()

    for i in range(number_of_counts):
        result[i] = increase_result[i] + decrease_result[i] -1

    return max(result)



if __name__ == "__main__":
    N = int(sys.stdin.readline())
    print(main(N))

        

