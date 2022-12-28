"""
[greedy]
- 현재 상황에서 가장 좋은 선택만을 반복해서 고르는 알고리즘  

- 모든 상황에서 최적의 해를 구할 수는 없지만, 최적의 해가 보장되는 조건에서는 쉽고 바르게 문제를 해결할 수 있다.

- 그리디 알고리즘이 적합한 문제
    - 탐욕 속성이 포함된 경우(최소 비용, 최대 넓이, 최단 경로 등)
    - 문제의 해결 방법이 반복되는 하위 반복 문제에 대해서도 함께 적용하는 경우
"""

"""
[잔돈의 최소 갯수 구하기]
1000원에서 입력한 금액을 뺀 잔돈을 동전으로 줄 때, 최소 몇 개 가능한지?

"""

def exchange(price: int) -> int: 
    exchange = 1000 - price
    coin = [500, 100, 50]
    coin_index = 0
    count = 0
    
    if exchange < 0:
        raise ValueError('1000원 이하의 가격을 입력하세요.')

    while exchange != 0:
        if exchange >= coin[coin_index]:
            exchange -= coin[coin_index]
            count += 1 
        else:
            coin_index += 1 
    
    return count

exchange(750)

    
    
    