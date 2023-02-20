"""
[프린터]
각 문서 순서대로 중요도가 담긴 배열이 주어질 때, n번째 있는 문서가 몇 번째 순서로 출력되는지 알아보는 함수
"""
from typing import List
from collections import deque
from itertools import islice

def main(priorities: List, location: List):
    
    # 몇 번쨰 순서로 출력된다 = 몇 번째 순서로 중요한 중요도를 가지냐  
    # 제일 중요한 것부터 출력한다. 

    index_priority_pair = deque(enumerate(priorities))
    nth_print = 0 
    
    # 출력되기 위해서는 제일 중요한 것을 앞으로 보내야 한다. -> 중요도를 비교
    while True:
        target_to_print = index_priority_pair.popleft()
        initial_index, priority_to_print = target_to_print
        if any(priority_to_print < priority[1] for priority in index_priority_pair):
                index_priority_pair.append(target_to_print)
        else: 
            nth_print += 1 
            if initial_index == location: 
                return nth_print


if __name__ == "__main__":
    priorities_list = [[2, 1, 3, 2], [1, 1, 9, 1, 1, 1], [1, 5, 3, 4, 9]]
    location_list = [2, 0, 2]
    for priorities, location in zip(priorities_list, location_list):
        print(main(priorities, location))

