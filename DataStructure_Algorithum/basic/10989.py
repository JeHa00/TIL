"""
[수 정렬하기(10989)] 
- 정렬을 사용하여 푸는 문제
- N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하기
- 대부분의 정렬 방법보다 파이썬의 정렬 함수 및 메소드가 더 빠르다. 
- 하지만 이 문제의 경우, 그렇게 하면 메모리 초과가 뜨기 때문에 계수 정렬을 사용해야만 한다.
- 이 문제가 바로 파이썬 정렬과 메소드를 사용하지 않아야하는 예외의 경우다.
"""

import sys

def first_solution(counts_of_number: int) -> list:
    """
    백준에 입력하면 메모리 초과가 뜬 방식
    """

    answer = []

    # counts_of_number 만큼 정수 입력받아 저장하기
    while counts_of_number != 0:
        number = int(sys.stdin.readline().strip())
        answer.append(number)
        counts_of_number -= 1

    # 정렬하기
    answer.sort()
    return answer


def second_solution(counts_of_number: int) -> list:

    answer = []


    return answer


if __name__ == '__main__':
    counts_of_number = int(sys.stdin.readline().strip())
    first_result = first_solution(counts_of_number)
    second_result = second_solution(counts_of_number)
    for number in first_result:
    # for number in second_result:
        print(number)