"""
[Problem: 3003번] 

체스 게임 시작을 위한 말 종류와 각 종류마다의 갯수를 만족하기 위해서, 
입력값으로 주어진 종류와 갯수를 기준으로 무슨 종류의 말을 몇 개를 더하고 빼야하는지 출력하기  

- 출력 형식: 1 1 1 1 1 1 으로, 입력값과 동일한 형식으로 출력되야 한다. 

- 전제: 입력되는 갯수 순서의 체스 피스 종류는 변동되지 않는다. 또한 체스 룰은 변경되지 않는다.

- standard는 수정되지 않고, 입력값이 실행되는 동안 변경되지 않는다. 
  또한, 각 말의 차이는 indexing을 사용할 것이므로  tuple 을 사용한다.  

"""

# 체스는 킹, 퀸, 록, 비숍, 나이트, 폰 종류의 말을 가지고 있고, 언급된 순서와 동일한 index로 갯수를 입력했다.
chess_standard_piece_number = (1, 1, 2, 2, 2, 8)


# 입력값은 공백을 기준으로 한 줄에 주어진다.
chess_piece_given = tuple(map(int, input().split()))

for i in range(len(chess_standard_piece_number)):
    print('{}'.format(
        chess_standard_piece_number[i] - chess_piece_given[i]), end=" ")
