"""
[H-index]
발표한 논문 n편 중 h번 이상 잉용된 논문이 h편 이상이고, 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 H-index 라고 한다. 

논문 인용 횟수를 담은 배열을 citations가 매개변수로 주어질 때, H-index를 return 하는 함수를 작성하기
"""


from typing import List

def solution(citations: List) -> int:

    answer = 0
    count = 0
    count_per_citation = []
    
    for citation in citations:
        count = [citation, 0]
        for value in citation:
            if citation >= value:
                count[1] += 1
        count_per_citation.append(count)
    
    return answer

if __name__ == "__main__":
    citations = [3, 0, 6, 1, 5]
    print(solution(citations))