"""
[Problem: 25304번] 

- 쇼핑 구입 총 금액 일치 여부 확인 문제

- 구매한 물건들의 가격과 갯수를 총 계산했을 때, 영수증에 적혀있는 총 금액과 일치하는지 확인 

- 입력값으로 주어지는 것들  
    - 영수증에 적힌 총 금액 : X
    - 영수증에 적힌 구매한 물건의 총 종류: N
    - 물건의 가격: a 
    - 물건의 개수: N 

- 출력 제한 조건:
    - 일치하면 Yes, 아니면 No 출력
"""


def total_price_check(receipe_total_price: int, items_kinds: int) -> str:
    X = receipe_total_price
    N = items_kinds
    total_price = [None] * N
    for i in range(N):
        a, b = list(map(int, input().split()))
        total_price[i] = a * b

    if X == sum(total_price):
        return "Yes"
    else:
        return "No"


if __name__ == "__main__":
    X = int(input())
    N = int(input())
    result = total_price_check(X, N)
    print(result)
