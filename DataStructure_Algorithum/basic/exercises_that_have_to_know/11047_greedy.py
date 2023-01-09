"""
[동전 0]
입력된 동전의 종류들로 원하는 값을 만드는데 필요한 동전의 최소 갯수를 구하는 프로그램을 작성하기

입력값: N, K
- N: 가지고 있는 동전의 종류
- K: 만드려는 가격
- 각 동전의 종류들이 오름차순으로 입력

출력값: 필요한 동전 개수의 최솟값
"""

def main(kinds_of_coin:int, total_cost:int): 
    """
    반례: N, K = 2, 2 / coins = [1, 2]
    Output은 2지만, Answer는 1이다.

    예제는 맞았지만 틀렸다면 내부 부등호를 판단해보자. 과연 그 부등호가 맞는 것인지?
    """
    coins = []
    counts = 0
    
    for _ in range(kinds_of_coin):
        coin = int(input('coin price: '))
        coins.append(coin)
    
    coins.sort(reverse=True)

    for coin in coins:
        # if coin < total_cost:  -> coin과 total_cost가 동일한 경우에 답이 틀릴 수 있다. 
        if coin <= total_cost:
            counts += total_cost // coin
            total_cost %= coin
            if total_cost == 0:
                return counts


if __name__ == "__main__":
    N, K = map(int, input().split()) # 종류, 총 합 가격
    answer = main(N, K)
    print(answer)