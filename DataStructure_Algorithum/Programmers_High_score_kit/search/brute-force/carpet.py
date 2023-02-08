"""
[카펫]
https://school.programmers.co.kr/learn/courses/30/lessons/42842
"""

import math
from typing import List

def solution(brown: int, yellow: int) -> List:
    """갈색 카펫의 갯수와 노란색 카펫 갯수가 주어졌을 때, 전체 카펫 사이즈를 구하는 함수

    Args:
        brown (int): 갈색의 카펫 칸 수
        yellow (int): 노란색의 카펫 칸 수

    Returns:
        List: 전체 카펫의 폭과 높이가 성분인 리스트를 반환
    """

    answer = []
    total_carpet_count = brown + yellow
    for width in range(3, int(math.sqrt(total_carpet_count)) + 1):
        if divmod(total_carpet_count, width)[1] == 0:
            height = divmod(total_carpet_count, width)[0]
            # 노란색은 네 방향으로 1줄씩 갈색으로 둘러쌓여져있기 때문에, 두 줄씩 뺀다.  
            if (width-2) * (height-2) == yellow: 
                answer.extend([height, width])

    return answer

if __name__ == "__main__":
    brown_yellow_carpet_list = [(10, 2), (8, 1), (24, 24)]
    for brown, yellow in brown_yellow_carpet_list:
        print(*solution(brown, yellow))
        # [4, 3], [3, 3], [8, 6]