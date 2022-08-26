"""
[problem: 2775]

2775_1.py 에서 언급한 것처럼 시간 초과로 인해 오답이 나왔다.
시간 문제를 해결한 접근법을 생각해보자. 

재귀 방식으로 인해 시간초과가 났다. 

제한 시간 1초 안에 실행해야 한다. 
재귀 방법일지라도 컴퓨터의 빠른 연산으로 1초 안에 가능할거라 생각했지만, 그렇지 않았다. 


그 아래층의 해당 호수까지의 누적 합이 반복되기 때문에, 재귀함수를 사용했지만 다른 풀이를 보니
재귀 함수를 사용하지 않고, 중첩 for문을 사용하여 해결했다. 
"""

iteration_times = int(input())
for _ in range(iteration_times):
    k = int(input('구하고자 하는 층: '))
    n = int(input('구하고자 하는 호수: '))

    # [1, 2, 3]
    apartment = [i for i in range(1, n + 1)]
    for _ in range(k): # range(1)
        # 1, 2
        for j in range(1, n): 
            apartment[j] += apartment[j - 1]
            #apartment[1] += apartment[0]
    print(apartment[n - 1])