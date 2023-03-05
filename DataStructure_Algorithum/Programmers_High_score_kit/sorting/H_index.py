"""
[H-index]
발표한 논문 n편 중 h번 이상 잉용된 논문이 h편 이상이고, 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 H-index 라고 한다. 

논문 인용 횟수를 담은 배열을 citations가 매개변수로 주어질 때, H-index를 return 하는 함수를 작성하기
"""


from typing import List

def solution(citations: List) -> int: 

    citations.sort(reverse=True)
    for i, citation in enumerate(citations):
        if i+1 == citation:
            return citation 

        elif i+1 > citation: # 테스트 11번, 16번
            return i # citations[i] 에서 i로 바꾸니 정답처리 되었다. 논문 수를 반환해야 한다. 

    return len(citations) # 테스트 9번 케이스

if __name__ == "__main__":
    citations = [3, 0, 6, 1, 5]
    print(solution(citations))