"""
[주식 가격]
초 단위로 기록된 주식가격이 담긴 배열이 주어질 때, 가격이 떨어지지 않은 기간이 몇 초인지 반환하는 함수 작성하기
"""
from typing import List

def main01(prices:List) -> List:
    """
    시간 초과 발생
    """
    # answer = [0 for _ in range(len(prices))]
    answer = [0] * len(prices) # list comprehension 보다 더 빠른 결과

    for i, selected_price in enumerate(prices):
        for price_per_second in prices[i+1:]:
            if selected_price <= price_per_second:
                answer[i] += 1

    return answer


def main02(prices:List) -> List:
    """
    첫 번째 방식과의 차이점은 range를 사용하냐 아니면 슬라이싱을 사용하냐의 차이다. 
    슬라이싱으로 그 때 그 때 생성하다보니 range보다 확실히 오래걸리는 걸로 판단된다. 
    하지만, 이 방식도 시간이 꽤 걸린다. 그리고 range(len()) 파이썬에서 권장하지 않는 방식이다.
    """

    answer = [0] * len(prices)
    for i, selected_price in enumerate(prices):
        for j in range(i+1, len(prices)):
            answer[i] += 1 
            if selected_price > prices[j]:
                break

    return answer


def main03(prices:List) -> List: 
    
    return 


if __name__=="__main__":
    prices_list = [[1, 2, 3, 2, 3]]
    for prices in prices_list:
        print('main01: ', main01(prices))
        print('main02: ', main02(prices))
        print('main03: ', main03(prices))