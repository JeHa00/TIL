"""
[요세푸스 문제]
- deque를 선택하여 deque.rotate의 method를 사용하여 손쉽고, 직관적으로 푼다.
"""

import sys
from collections import deque

def main(n: int, k: int) -> list:
    circle = deque([x for x in range(1, n+1)])
    answer = []
    
    ROTATE_DIRECTION = -1 # 음수는 반시계방향 / 양수는 시계방향
    ROTATE_SIZE = k-1 # 한 번 회전 시, 크기
    ROTATE_VALUE = ROTATE_DIRECTION * ROTATE_SIZE


    while len(circle) != 0: 
        circle.rotate(ROTATE_VALUE)
        answer.append(circle.popleft())
    return answer
    
if __name__ == '__main__':
    n, k = map(int, sys.stdin.readline().split())
    answer = main(n, k)
    print('<', end='')
    print(','.join(map(str, answer)), end='')
    print('>')