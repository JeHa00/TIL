"""
[랜선 자르기]

문제 설명
- K: 오영식이 이미 가지고 있는 랜선의 개수이며, 각 랜선의 길이는 제각각이다.
- N: 필요한 랜선의 수이며, 이 때 길이는 다 동일해야 한다.
- K <= N 

입력
- 첫째 줄에는 K, N이 입력

출력
- 첫째 줄에는 N개를 만들 수 있는 랜선의 최대 길이를 cm 단위의 정수로 출력
"""

import sys
import time

def first_solution(N, K):
    """
    결과가 틀린 방식.
    백준에서 알려준 예시 문제는 풀리지만, 제출하면 틀린다.
    """
    lines = []
    answer = 0
    for _ in range(N):
        line = int(sys.stdin.readline())
        lines.append(line)

    min_length = min(lines) // 100 * 100 

    for cutting_length in range(100, min_length+1, 100):
        for line in lines: 
            count = line // cutting_length 
            answer += count 
        if answer == K: 
            return answer
        else:
            answer = 0


def second_solution(K:int, N:int) -> int:
    """
    이분 탐색을 사용한 방법. 하지만 시간 초과 발생 (7.7s)
    N개를 만족하는 최소 길이를 구하는 방식.
    하지만, 문제에서는 N개를 만족하는 최대 길이를 구하라고 한다.
    """
    lines = [int(sys.stdin.readline()) for _ in range(K)]
    start = 100 
    end = (max(lines)//100) * 100 
    # mid = (start+end) // 2  이 부분에 작성하면 mid는 업데이트 되지 않는다.
    while True:
        answer = 0
        mid = (start+end) // 2 
        for length_of_line in lines:
            answer += length_of_line // mid 
        if answer >= N:
            return answer
        else:
            end -= 100 


def third_solution(K:int, N:int):
    """
    # 12s
    이분 탐색을 사용하여 N개 이상의 갯수를 만족하는 최대 길이를 구하는 방식
    """
    lines = [int(sys.stdin.readline()) for _ in range(K)]
    start = 100 
    end = (max(lines)//100) * 100

    while start <= end:
        mid = (start+end) // 2
        answer = 0 
        for length_of_line in lines:
            answer += length_of_line // mid 
        if answer >= N:
            start = mid + 1 
        else:
            end = mid - 1
    print(end)
    

if __name__ == "__main__":
    K, N = map(int, sys.stdin.readline().split())
    # answer = first_solution(N, K)
    start = time.time()
    # answer = second_solution(K, N)
    answer = third_solution(K, N)
    print(answer)
    end = time.time() 
    print(end-start)


