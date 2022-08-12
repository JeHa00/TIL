"""
[problem: 1712]

- 문제: 손익분기점 구하기 
    - 고정 비용: 노트북 생산 대수와 상관없이 드는 비용 
    - 가변 비용: 노트북 1대 생산하는데 드는 비용  

- 입력값 : A B C
    - A: 고정비용
    - B: 가변 비용
    - C: 노트북 판매금액 

- break_even_poinrt 식을 만드는 것을 어렵지 않기 때문에, 
  각 입력값에 해당되는 값을 입력했을 때, 그에 따라 예외처리를 어떻게 하냐가 이 문제의 포인트라고 생각한다. 

"""


def findBreakEvenPoint(fixed_cost: int, variable_cost: int, income: int) -> int:

    try:
        break_even_point = fixed_cost / (income - variable_cost)
        if break_even_point > 0:
            if break_even_point is not int:
                break_even_point = int(break_even_point) + 1
                print(break_even_point)

        else:
            print(-1)

    except ZeroDivisionError:
        print(-1)


if __name__ == "__main__":
    A, B, C = list(map(int, input().split()))
    findBreakEvenPoint(A, B, C)
