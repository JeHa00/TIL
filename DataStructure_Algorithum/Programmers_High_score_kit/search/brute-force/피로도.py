"""
[피로도]

던전을 탐험하기 위해서는 "최소 필요 피로도"가 요구되며, 던전 탐험을 완료하면 "소모 피로도" 만큼 소모됩니다. 

현재 피로도 k 정수와 "최소 필요 피로도", "소모 피로도" 를 가지고 있는 dungeons 2차 list가 주어진다. 

2차 list로 주어진 각 던전 정보는 

그러면 유저가 탐험할 수 있는 최대 던전 수를 반환하는 프로그램을 짜세요.
"""

from typing import List
from itertools import permutations

def main(k: int, dungeons: List) -> List: 
    answer = []
    for case in permutations(dungeons, len(dungeons)):
        energy = k
        answer.append(0)
        for fatigue, gauge_to_consume in case:
            if energy >= fatigue and energy >= gauge_to_consume:
                energy -= gauge_to_consume
                answer[-1] += 1 
    return max(answer)



if __name__ == "__main__":
    k = 80
    dungeons = [[80,20],[50,40],[30,10]]
    print(main(k, dungeons))