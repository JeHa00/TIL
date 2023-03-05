"""
[가장 큰 수]
숫자들이 담기 배열이 주어졌을 때, 정수들을 이어붙여서 가장 큰 수를 만들어 반환하기
"""

from typing import List

def solution01(numbers: List) -> str:
    
    answer = ''
    only_max_digit_numbers = []
    
    for i, number in enumerate(numbers):
        max_digit = str(number)[0]
        only_max_digit_numbers.append([i, int(max_digit), number])
    
    only_max_digit_numbers.sort(key=lambda x: x[1], reverse=True)

        
    # 만약 2개 이상이라면?
    # 값이 동일하다면? -> 
    ## 자리 수가 2개와 1개가 있다면 자리 수가 2개의 값의 그 다음 작은 자리 수를 자리 수가 1개인 것과 비교하여 큰 값이 앞으로 온다. 
    """
    이 부분의 알고리즘을 손쉽게 해결한게 solution02 다. 
    """

    for number in only_max_digit_numbers:
        answer += str(number[-1])

    return answer


def solution02(numbers:List) -> str: 

    answer = ''
    numbers = list(map(str, numbers))
    numbers.sort(key=lambda x: x * 3, reverse=True)
    # answer = ''.join(numbers) 테스트 11번이 통과되지 않음 
    # 0000이 그대로 문자열로 출력되기 때문이다. 그래서 int로 전환한 후 문자열로 다시 전환한다.
    answer = str(int(''.join(numbers)))
    return answer

if __name__ == "__main__":
    input_values = [[6, 10, 2], [3, 30, 34, 5, 9]]
    for input_value in input_values:
        print(solution01(input_value))
        print(solution02(input_value))