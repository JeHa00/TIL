"""
[접미사 배열 (11656)]

문자열 S가 주어졌을 때, 모든 접미사를 사전순으로 정렬한 다음 출력하는 프로그램을 작성하세요.
"""
import sys

def main(value: str) -> list:
    answer = []

    # 입력된 value str의 접미사들을 answer에 저장하기
    for i in range(len(value)):
        answer.append(value[i:])
    
    # 저장된 answer를 정렬하기      
    answer.sort()
    return answer

if __name__ == '__main__':
    S = sys.stdin.readline().strip()
    answer = main(S)
    for element in answer: 
        print(element)