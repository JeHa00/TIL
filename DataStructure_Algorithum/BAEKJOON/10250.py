"""
[problem 10250]

- 문제
    - 호텔 정문으로부터 걷는 거리가 가장 짧도록 방을 배정하는 프로그램  
    - N 번째로 도착한 손님에게 배정될 방 번호를 계산하여 출력한다. 
    - 각 방 끼리는 이동거리가 1이다. 
    - 엘리베이터를 타고 이동하는 거리는 신경 쓰지 않는다. 
    - 걷는 거리가 동일할 때, 상대적으로 아래층의 방을 선호 
        - 엘리베이터에서 더 가깝기 때 

- 입력값
    - T: 테스트 데이터의 갯수 
    - H, W, N: 호텔의 층 수, 각 층의 방수, 몇 번째 손님인지

- 출력값
    - 방 번호

- 풀이
    - 몫과 나머지를 사용하여 N번째의 방의 번호를 구한다. 
    - 나머지: 층수, 몫: 호수 

- 결국 틀렸다.
    - 풀이 게시판을 통해 반례를 찾았고, 이에 따라 궁금한 게 생겼다. 
    - 반례는 어떻게 찾을 것인가???

- 원인
    - 몫과 나머지에서 이 나머지가 0일 때를 분류했어야 했다. 
    - N이 H보다 크고, 작고가 기준이 아니라 나머지가 0일 때와 아닐 때가 기준이다. 
    - 이처럼 문제에 몫과 나머지를 활용하는 문제가 있다면 나머지가 0일 때를 생각해보자.


[오답] 
T = int(input())
for i in range(T):
    H, W, N = map(int, input().split()) # N = 10, H = 1

    if N > H: # 10 > 2  -> 205 
        number, floor = divmod(N, H)
        print(floor * 100 + number + 1) 

    elif N == H: # 200/200 = 1 
        print(H * 100 + 1)

    else:
        print(N * 100 + 1)
"""


T = int(input())
for i in range(T):
    H, W, N = map(int, input().split())

    location, floor = divmod(N, H)

    if floor == 0:
        floor = H
        print(H * 100 + location)
    else:
        print(floor * 100 + location + 1)
