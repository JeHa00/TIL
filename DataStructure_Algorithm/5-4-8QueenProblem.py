# 8퀸 문제 알고리즘 구현하기 

pos = [0] * 8 # 각 열에 배치한 퀸의 위치 

def put() -> None: 
    """각 열에 배치한 퀸의 위치를 출력"""
    for i in range(8):
        print(f'{pos[i]:2}')
flag_a = [False] * 8 # 각 행에 퀸을 배치했는지 체크

flag_b = [False] * 15 # 대각선 방향(/)으로 퀸을 배치했는지 체크 
flag_c = [False] * 15 # 대각선 방향(\)으로 퀸을 배치했는지 체크 


"""
j행에 퀸을 배치하면 flag[j]를 True로, 배치하지 않으면 False로 한다.  
"""