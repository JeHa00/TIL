"""
[다리를 지나는 트럭]
다리의 길이, 다리가 지탱할 수 있는 트럭 무게, 각 트럭의 무게들을 값으로 주어졌을 때
모든 트럭이 다리를 다 건너는데 몇 초나 걸리는지 알려주는 프로그램 짜기
"""

from typing import List
from copy import deepcopy
from collections import deque

def solution(bridge_length: int, weight: int, truck_weights: List ) -> int:
    """트럭이 다리를 건너는데 총 몇 초가 걸리는지 알려주는 프로그램

    Args:
        bridge_length (int): 다리의 길이이면서 허용할 수 있는 트럭 수. 1초에 bridge_length 1만큼 이동
        weight (int): 다리가 견딜 수 있는 최대 무게
        truck_weights (List): 각 트럭의 무게들

    Returns:
        int: 트럭이 다리를 건너는데 걸린 총 시간

    트럭의 상태에는 총 3가지 종류가 있다.
    - 대기
    - 진행 중: 얼마나 진행 중이냐는 필요 없다. 대기 상태에서 바로 진행 중으로 이동
    - 완료 
    """

    answer = 0
    will_state = deque(deepcopy(truck_weights))
    doing_state = deque([])
    done_state = deque([])
    total_truck_count_on_bridge = 0 # 다리 위에 트럭 총 수
    proceed_rate_on_bridge = [0] * bridge_length # 다리 위 진행도 

    while done_state != truck_weights: # done_state가 truck_weights와 동일해질 때까지
        
        # doing_state에 오기
            # weight와 bridge_length 조건에 만족하면 오기
        if proceed_rate_on_bridge != bridge_length:
            if bridge_length >= total_truck_count_on_bridge and weight >= sum(doing_state): # 전체 트럭 댓수 이하일 때
                if will_state: 
                    doing_state.append(will_state.popleft())
                    total_truck_count_on_bridge += 1
                proceed_rate_on_bridge += 1 
            else:
                proceed_rate_on_bridge += 1 
        else: 
            # done_state에 오기 
            # 새롭게 doing_state에 추가하여 weight와 bridge_length 조건을 넘으면 done_state에 와야 한다.
            if bridge_length < total_truck_count_on_bridge and weight < sum(doing_state):
                done_state.append(doing_state.popleft())
        
        # 시간 증가
        answer += 1

    return answer


if __name__ =="__main__":
    input_values = [[2, 10, [7,4,5,6]], [100, 100, [10]], [100, 100, [10,10,10,10,10,10,10,10,10,10]]]
    for bridge_length, weight, truck_weights in input_values:
        print(solution(bridge_length, weight, truck_weights))
