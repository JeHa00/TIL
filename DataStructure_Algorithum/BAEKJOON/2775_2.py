"""
[problem: 2775]

2775_1.py 에서 언급한 것처럼 시간 초과로 인해 오답이 나왔다.
시간 문제를 해결한 접근법을 생각해보자. 

재귀 방식으로 인해 시간초과가 났다. 

제한 시간 1초 안에 실행해야 한다. 
재귀 방법일지라도 컴퓨터의 빠른 연산으로 1초 안에 가능할거라 생각했지만, 그렇지 않았다. 

"""

import time
import sys

def saveResident(floor: int, number: int) -> int:
    floor_zero = [i for i in range(number + 1)]
    
    if floor == 1: 
        floor_number = 0
        for j in range(1, number + 1): 
            floor_number += floor_zero[j]
    
        return floor_number

    else: 
        result = 0
        for j in range(1, number + 1):
            result += saveResident(floor - 1, j)
    
        return result

def main():
    k = int(sys.stdin.readline()) # 1 
    n = int(sys.stdin.readline()) # 1 
    print(saveResident(k, n))

if __name__ == "__main__":
    start = time.time()
    test_case = int(sys.stdin.readline())
    while test_case > 0:
        main()
        test_case -= 1 
    end = time.time()
    print(f'걸린 시간: {end - start}s')
