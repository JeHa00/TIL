"""
[11053 - 가장 긴 증가하는 부분 수열]
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하기

입력값
- 수열 A의 크기
- A의 원소들

출력값
- 수열 A의 가장 긴 증가하는 부분: 수열의 길이
"""

import sys

def main(length_of_list:int) -> int:
    """
    예제는 맞았지만, 제출하니 틀렸다! 
    """

    A = list(map(int, sys.stdin.readline().split()))

    result = []

    for i in range(len(A)):
        if i == 0:
            result.append(A[i])
        else:
            if A[i] > A[i-1]:
                result.append(A[i])

    return len(result)

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    print(main(N))