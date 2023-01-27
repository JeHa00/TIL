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
    이분 탐색을 사용하여 N개 이상 만들 수 있는, 최대 길이를 구하는 방식
    - second_solution은 10의 자리수 길이를 구하는데 ZeroDivisionError가 발생된다.
    - 시간 초과 발생
    - second_solution에서 이분 탐색을 적용하라고 했지만, 완벽히 이해하지 못하고 적용했음을 알았다.
    """

    lines = [int(sys.stdin.readline()) for _ in range(K)]
    lines_satisfied_condition = []
    start = 1
    end = max(lines)

    while start <= end:
        answer = 0
        mid = (start+end) // 2 
        for length_of_line in lines:
            answer += length_of_line // mid 
        
        """
        N에 answer를 조절하기 위해서는 mid를 조절해야 한다. 그런데, 여기서 포인트는 answer와 mid는 반비례한다.
        mid가 커지면 answer는 작아진다. mid가 작아지면 answer는 커진다.
        mid가 커지기 위해서는 start = mid + 1을 한다. end = mid + 1도 있지만 이는 증가시키는 게 아닌 감소시키는 것이다.
        mid가 작아지기 위해서는 end = mid - 1을 한다. start = mid - 1도 있지만 이는 감소시키는 게 아닌 증가시키는 것이다.
        """
    
        if answer >= N: # answer가 작아져야 하므로, mid는 커져야 한다.
            """
            여기서 하나 의문점이 든다. N개보다 많이 만드는 것도 N개를 만드는 것에 포함되기 때문에
            answer >= N 이다. 
            그러면 N개보다 커지면 바로 끝나는 건가? 그렇지 않다.
            만들 수 있는 최대 랜선의 길이를 구하는 프로그램이기 때문에, N개보다 커지면 길이가 최대 길이가 안된다. 
            그래서 사실상 N개보다 많이 생성하면 안된다.
            """
            start = mid + 1 
            lines_satisfied_condition.append(mid)
        else:  # answer가 커져야 하므로, mid는 작아져야 한다.
            end = mid - 1
            
    return max(lines_satisfied_condition)
    

if __name__ == "__main__":
    K, N = map(int, sys.stdin.readline().split()) # 가지고 있는 랜선의 수, 필요한 랜선의 수
    start = time.time()
    # answer = first_solution(N, K)
    # answer = second_solution(K, N)
    print(third_solution(K, N))
    end = time.time() 
    print(f"time: {end-start}")


