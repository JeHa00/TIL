"""
[가장 큰 수]
숫자들이 담기 배열이 주어졌을 때, 정수들을 이어붙여서 가장 큰 수를 만들어 반환하기
"""

from typing import List
from collections import defaultdict
def solution(numbers: List) -> str:
    
    answer = ''
    only_max_digit_numbers = []
    counts_per_max_digit = defaultdict(int)
    
    for i, number in enumerate(numbers):
        max_digit = str(number)[0]
        only_max_digit_numbers.append([i, int(max_digit), number])
    
    only_max_digit_numbers = sorted(only_max_digit_numbers, key=lambda x: x[1], reverse=True)

    print(only_max_digit_numbers)

    
    # 만약 2개 이상이라면?
    
    # 값이 동일하다면? -> 
    ## 자리 수가 2개와 1개가 있다면 자리 수가 2개의 값의 그 다음 작은 자리 수를 자리 수가 1개인 것과 비교하여 큰 값이 앞으로 온다. 



    return answer



if __name__ == "__main__":
    input_values = [[6, 10, 2], [3, 30, 34, 5, 9]]
    for input_value in input_values:
        print(solution(input_value))