"""
[동적 계획법: Dynamic programming]

- 문제를 여러 개의 하위 문제로 나누어 해결한 뒤 결합하는 알고리즘  

- 이미 해결한 하위 문제의 결과를 저장하여 중복 계산을 방지한다. (Memorization) => 재귀와의 차이점

- DP 적합 문제 판단하기: 문제를 중복된 하위 문제로 나눌 수 있는 경우  

nCr 값을 구하는 걸 재귀와 동적 계획법을 통해서 구해본다.
"""

# 재귀법을 사용한 방식: 숫자가 커질수록 중복되는 부분이 많아지고, recursive depth에 한계가 존재
# 시간 복잡도: 2^(n)
def combination_recursive(n: int, r: int) -> int:
    """nCr의 값을 구하는 함수
    Args:
        n (int): nCr의 n
        r (int): nCr의 r
    Returns:
        int: nCr의 계산 결과를 반환한다.
    """

    if r == 0 or n == r:
        return 1
    elif r == 1:
        return n
    else:
        return combination_recursive(n - 1, r) + combination_recursive(n - 1, r - 1)


# 동적 게획법 방식
# bottom up 방식
# 시간 복잡도: O(n)
def combination_dynamic(n: int, r: int) -> int:

    total_items = n + 1
    pickup_items = r + 1

    # 이미 계산된 것을 저장하는 list
    result: list = [[0 for _ in range(pickup_items)] for _ in range(total_items)]
    for total_item in range(total_items):
        for pickup_item in range(pickup_items):
            if total_item == pickup_item or pickup_item == 0:
                result[total_item][pickup_item] = 1
            else:
                result[total_item][pickup_item] = (
                    result[total_item - 1][pickup_item - 1]
                    + result[total_item - 1][pickup_item]
                )
    print(result)
    return result[n][r]


print(combination_dynamic(4, 2))
