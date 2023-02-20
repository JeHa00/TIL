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

    answer = [0] * len(prices)

    """
    ## 첫 번째 값을 기준
    # 기준값 다음 인덱스부터 기준 값 이상인 값이 나오면 answer에 += 1 
    
    ## n 번째 값을 기준
    # n+1 번재 숫자부터 기준값 이상인 값이 나오면 answer의 해당 index 값 += 1 
    """
    
    
    return 


if __name__=="__main__":
    prices_list = [[1, 2, 3, 2, 3]]
    for prices in prices_list:
        print(main01(prices))
        print(main02(prices))