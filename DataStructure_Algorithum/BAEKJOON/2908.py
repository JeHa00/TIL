"""
[problem: 2908]

- 문제
    - 세자리 상수 두 개가 주어졌을 때, 각 상수를 뒤집어서 대소비교를 한 후, 큰 값을 출력하기 

- 입력값
    - 세자리 상수 두 개: A B
    - 0은 포함 X 
    - A 와 B는 같지 않다.  
"""

from typing import List


def compareReverseValue(constants: List) -> int:
    result_int = []
    for number in constants:
        result = ''
        for s in range(2, -1, -1):
            result += str(number)[s]
        result_int.append(int(result))

    # sort()를 사용하여 맨 뒤 인덱스를 출력해도 되지만, sort 자체가 대소 비교를 하여 정렬하기 때문에, 시간이 더 빠르다.
    if result_int[0] > result_int[1]:
        print(result_int[0])
    else:
        print(result_int[1])


if __name__ == "__main__":
    constant = input().split()
    compareReverseValue(constant)
