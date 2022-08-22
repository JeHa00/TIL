"""
[problem: 2775번]

- 문제
    - a층의 b호에 살려면 자신의 아래 (a-1)층의 1호부터 b호까지 
    사람들의 수의 합만큼 사람들을 데려와 살아야한다
    - 0층부터 존재
    - 각층에는 1호부터 존재
    - 0층의 i호에는 i명이 산다. 

- 입력값
    - T: test case
    - k, n: k층의 n호  

- 출력값
    - 각 test case에 대해서 해당 집에 거주민 수를 출력하라.  

- 풀이
    - 재귀 방법을 사용하여 floor가 1층이 될 때까지 호출한다. 
    - 하지만 이 방법은 시간 초과가 나므로 다른 방법을 찾아보자. 
"""

### 시간 초과 코드

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
    k = int(input('구하고자 하는 층을 입력하세요.: ')) # 1 
    n = int(input('구하고자하는 호수를 입력하세요.: ')) # 3
    print(saveResident(k, n))

if __name__ == "__main__":
    test_case = int(input())
    while test_case > 0:
        main()
        test_case -= 1 

