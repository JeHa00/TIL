"""
[완주하지 못한 선수]

입력값
- participant: 마라톤에 참여한 선수들의 이름이 담긴 배열
- completion: 왅한 선수들의 이름이 담긴 배열  

출력값
- 완주하지 못한 선수의 이름을 반환하기  

제한 사항
- 경기에 참여한 선수 수: 1명 이상 100,000명 이하
- completion의 길이는 participant의 길이보다 1 작다. -> 1명만 완주하지 못한다. 미완주 인원은 고정되어 있다. 
- 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이뤄진다.  
- 참가자 중에는 동명이인이 있을 수 있다.  
"""
from typing import List
from copy import deepcopy

def solution_01(participant: List, completion: List) -> str:
    """마라톤에 참여한 선수 목록과 완주한 선수 목록이 주어졌을 때, 완주하지 못한 선수 이름을 반환하는 함수

    Args:
        participant (List): 마라톤에 참여한 선수 목록
        completion (List): 완주한 선수 목록

    Returns:
        str: 완주하지 못한 선수

    시간 초과 발생한 해결책으로, 해쉬를 사용하지 않았다.
    """

    uncomplete_player = deepcopy(participant)

    for complete_player in completion:
        uncomplete_player.remove(complete_player)

    return uncomplete_player


def solution_02(participant: List, completion: List) -> str:

    """
    participant의 각 성분들을 해쉬화 하여 이를 Key 값으로 하고, value에는 Player 이름을 매핑한다. 
    그러면 이 딕셔너리를 A라고 할 때, A에는 3개의 key가 존재한다. 이 key값을 모두 더하여 uncomplete_player 에 할당한다.
    완주한 선수들의 이름을 for문을 돌아가면서 해쉬화를 하고 이 값을 uncomplete_player에서 그 값들을 다 뺀다.
    for 문이 끝나고 나서 uncomplete_player에 남은 값을 Key값으로 participant에 접근하면 미완주자를 알 수 있다. 
    
    solution_01과 달리 효율성 테스트를 통과했다.
    """

    hashed_participant = {}
    uncomplete_player = 0

    for player in participant:
        hashed_player = hash(player)
        hashed_participant[hashed_player] = player
        uncomplete_player += hashed_player

    for completed_player in completion:
        uncomplete_player -= hash(completed_player)

    return hashed_participant[uncomplete_player]

if __name__ == '__main__': 
    participants = [["leo", "kiki", "eden"], ["marina", "josipa", "nikola", "vinko", "filipa"], ["mislav", "stanko", "mislav", "ana"]]
    completions = [["eden", "kiki"], ["josipa", "filipa", "marina", "nikola"], ["stanko", "ana", "mislav"]]
    for participant, completion in zip(participants, completions):
        # print(*solution_01(participant, completion))
        print(solution_02(participant, completion))
