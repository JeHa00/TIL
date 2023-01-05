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
    파이썬 기본 sort method를 사용한 방식: 시간 초과
    """
    answer = []

    # counts_of_number 만큼 정수 입력받아 저장하기
    while counts_of_number != 0:
        number = int(sys.stdin.readline().strip())
        answer.append(number)
        counts_of_number -= 1

    answer.sort()
    return answer


def second_solution(counts_of_number: int):
    """
    - counting 정렬을 사용하여 정렬한 방법: 시간 초과 문제 해결 
    - 수가 10000 이하이므로 미리 생성하면 메모리 초과문제가 해결된다.
    """
    
    """
    count_array = [0] * (counts_of_number + 1) 
    위 코드는 counting 정렬을 제대로 이해하지 못해 작성된 코드다. 
    계수 정렬은 인덱스 값이 정답의 element가 되는 방식이다.
    만약 (counts_of_number + 1)로 하면 문제에서는 수의 범위가 100,000 이하라고 했지만, 1 ≤ N ≤ 10,000,000 범위처럼 
    10,000,000 까지 [0]이 생성되므로 당연히 메모리 초과가 발생될 수 밖에 없다. 
    그래서 다음과 같이 10001을 곱해야 한다. 
    """

    count_array = [0] * 10001

    for _ in range(counts_of_number):
        number = int(sys.stdin.readline())
        count_array[number] += 1

    for index, count in enumerate(count_array): 
        if count_array[index] != 0:
            for _ in range(count):
                print(index)

if __name__ == '__main__':
    counts_of_number = int(sys.stdin.readline())
    # first_result = first_solution(counts_of_number)
    # for number in first_result:
    second_solution(counts_of_number)
