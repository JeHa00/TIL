"""
[오큰수]
- 각 인덱스에 해당하는 값을 기준으로 이 값보다 더 큰 값들 중에서 제일 왼쪽에 있는 것을 걸러내는 문제
- 없으면 -1을 반환한다.
- stack을 사용한다. 그리고, 이를 위해서 range의 next() 방향은 반대로 한다.
- 시간 제한 1초
"""

import sys

def main():
    total_counts = int(sys.stdin.readline())
    elements = list(map(int, sys.stdin.readline().split()))
    answer = [-1] * total_counts
    candidates = []

    for i, element in enumerate(elements): 
        for j in range(total_counts-1, i, -1):
            candidate = elements[j]
            if element < candidate:
                candidates.append(candidate)
        if len(candidates) != 0:
            answer[i] = candidates.pop() # candidates stack에서 제일 마지막에 있는 값이 element와 가장 가깝게 있으면서 큰 값.
        candidates.clear()

    print(*answer)


if __name__ == '__main__':
    main()