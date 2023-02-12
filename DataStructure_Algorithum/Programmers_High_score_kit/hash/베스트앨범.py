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
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
- 모든 장르는 재생된 횟수가 다르다. 
"""
from typing import List
from collections import defaultdict

def solution(genres:List, plays:List) -> List:
    answer = []

    # key에 대한 기본 데이터 형태가 정해지면 defaultdict를 사용하기  
    total_info = defaultdict(list)
    songs_per_genre = defaultdict(int)
    
    for i, (genre, play_count) in enumerate(zip(genres, plays)): 
        # {'classic': [(0, 500), (2, 150), (3, 800)], 'pop': [(1, 600), (4, 2500)]}
        total_info[genre].append((i, play_count))

        # {'classic': 1450, 'pop': 3100}
        songs_per_genre[genre] += play_count

    # item의 1 인덱스에 있는 게 재생 횟수이므로, 이를 기준으로 내림차순으로 정렬한다. 
    # 정렬하여 재생횟수가 제일 많은 것부터 플레이 리스트에 추가한다.
    for genre, _ in sorted(songs_per_genre.items(), key=lambda x:x[1], reverse=True):
        for i, _ in sorted(total_info[genre], key=lambda x:x[1], reverse=True)[:2]:
            answer.append(i)

    return answer

if __name__ == "__main__":
    genres = ["classic", "pop", "classic", "classic", "pop"]
    plays = [500, 600, 150, 800, 2500]
    print(solution(genres, plays))