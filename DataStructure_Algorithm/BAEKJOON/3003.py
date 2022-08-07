"""
[Problem: 3003번] 

체스 게임 시작을 위한 말 종류와 각 종류마다의 갯수를 만족하기 위해서, 
입력값으로 주어진 종류와 갯수를 기준으로 무슨 종류의 말을 몇 개를 더하고 빼야하는지 출력하기  
"""

chess_standard_piece_number = [1, 1, 2, 2, 2, 8]

chess_piece_given = list(map(int, input().split()))

for i in range(len(chess_standard_piece_number)):
    print('{}'.format(
        chess_standard_piece_number[i] - chess_piece_given[i]), end=" ")
