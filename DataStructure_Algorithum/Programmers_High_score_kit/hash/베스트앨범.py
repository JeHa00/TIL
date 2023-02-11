"""
[베스트 앨범]
https://school.programmers.co.kr/learn/courses/30/lessons/42579

스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시
노래를 수록하는 기준 
- 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
- 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
- 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

제한 사항
- genres와 plays의 길이는 동일  
- 장르 종류 < 100개
"""
from typing import List
def solution(genres:List, plays:List) -> List:

    hash_map = dict() 
    for i, genre in enumerate(genres):
        if genre not in hash_map.keys():
            hash_map[genre] = []
            hash_map[genre].append(i)
            
    
    # 장르의 총 재생 수를 계산
    # 총 재생 수가 많은 장르부터 앨범에 수록
    # 각 장르마다 최대 2곡만 수록 

    # 장르별 재생 수
    play_count = dict() 
    for key in hash_map.keys(): # classic, pop
        play_count[key] = 0
        for i in hash_map[key]: # [0, 2, 3] / [1, 4]
            play_count[key] += plays[i]
    # {'classic': 1450, 'pop': 3100}
    for item in play_count.items():
        pass


if __name__ == "__main__":
    genres = ["classic", "pop", "classic", "classic", "pop"]
    plays = [500, 600, 150, 800, 2500]
    print(solution(genres, plays))