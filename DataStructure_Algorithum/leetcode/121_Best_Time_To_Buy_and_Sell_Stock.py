"""
최대 이익 = 최대 값 - 최소 값 

max_value, min_value 의 초기값은 값의 범위에서 벗어나는 값으로 설정

첫 인덱스 값은 min_value에 할당 

그 다음 값을 (min_value가 할당된 상태 and max_value는 아직 값이 할당되지 않은 상태)
- min_value와 비교해서 더 작으면 min_value에 할당 
- min_value와 비교해서 더 크면 max_value에 해당  

min_value와 max_value가 모두 할당된 상태
- 다음 값이 min_value보다 작으면 min_value에 할당
- 다음 값이 max_value보다 크면 max_value에 할당 

하지만, 또 다른 조건은 먼저 min_value에서 사고, max_value에서 팔아야 최대 이익이 난다. 

값이 계속 감소하는 경우라면?


인덱스 정보와 함께 고려해야 한다. 

"""
from typing import List
import sys

input_data = [
    [7, 1, 5, 3, 6, 4],
    [7, 6, 4, 3, 1],
    [2, 4, 1],
    [2, 1, 2, 1, 0, 1, 2],
]


def solution01(input_data: List[int]) -> int:
    """
    위 4가지 경우에서 마지막 경우가 통과되지 못함
    """
    stack = []

    for v in input_data:
        if not stack:
            stack.append(v)
        elif len(stack) == 1:
            if stack[-1] > v:
                stack.pop()
                stack.append(v)
            elif stack[-1] < v:
                stack.append(v)
        else:
            if stack[-1] < v:
                stack.pop()
                stack.append(v)

    return stack[-1] - stack[0] if len(stack) == 2 else 0


def solution02(prices: List[int]) -> int:
    """
    solution01과의 차이는 profit 값을 미리 기억 해둔다는 걸 의미한다.
    그리고, 이익이 최대이기 때문에 min 값을 먼저 설정한다는 걸 알 수 있다.
    현재 price가 최저가라서 min_price가 변경되었다면 profit은 0이 된다.
    profit이기 때문에 price - min_price로 계산한다.
    """

    # 문제의 범위를 보고 설정
    min_value = 10001

    # 최저 수익은 0
    profit = 0

    for price in prices:
        min_price = min(min_value, price)
        profit = max(profit, price - min_price)

    return profit


def solution03(prices: List[int]) -> int:
    """
    그렇다면 손해가 제일 큰 지점을 보기 위해서는 어떻게 작성해야할까?
    """
    max_price = 0
    loss = 1000000

    for price in prices:
        max_price = max(max_price, price)
        loss = min(loss, price - max_price)

    return loss


for data in input_data:
    print(solution02(data))
    print(solution03(data))
