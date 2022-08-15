"""
[problem: 2869]

- 문제
    - 달팽이가 나무 막대를 다 올라가는데 걸리는 날 수
    - 달팽이는 V meter의 나무 막대를 올라간다.  
    - 달팽이는 낮에 A meter 올라가고, 밤에 자는 동안 B meter 만큼 떨어진다. 
    - V meter에 도착하면 내려가지 않는다. 

- 입력값
    - A, B, V

- 출력값
    - 날 수 (X)


- 2869_1.py의 방식은  total_distance가 커질수록 비선형적으로 day가 증가하여 시간초과된다. 시간제한 0.15s 안에 실행하기 위해서는 반복문이 아닌 방정식처럼 값이 바로 나오도록 해야한다. 
    - 정상에 올라간 후에는 내려가지 않는다는 걸 유심히 생각해보자. 
    - 최소 날짜를 구해보라는 의미로서, 밤까지 지나서 내려간 거리가 그 다음 날 낮에 올라가서 원하는 목표에 도착한 거리와 같다면 그 날짜가 바로 최소날짜다.    
    - (A-B) * X 만큼 반복하고, 그 다음 날 A 만큼 올라간 거리가 목표 거리에 도달했음을 의미하는 식은 (A-B) * X + A = V 다. 
    - 만약 날 수가 소수점으로 나온다면, 날 수는 소수점일 수 없기 때문에 몫에 날 수 1일을 더한다. 
    - 그리고 기본적으로 1 day는 최소로 필요하다.
"""


def getDays(up: int, down: int, total_distance: int) -> int:
    
    X = (total_distance - up) / (up - down) + 1
    if (total_distance - up) % (up - down) == 0:
        print(int(X))
    else:
        print(int(X + 1))

    


if __name__ == "__main__":
    A, B, V = list(map(int, input().split()))
    getDays(A, B, V)
