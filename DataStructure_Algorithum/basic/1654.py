"""
[랜선 자르기]

문제 설명
- K: 오영식이 이미 가지고 있는 랜선의 개수이며, 각 랜선의 길이는 제각각이다.
- N: 필요한 랜선의 수이며, 이 때 길이는 다 동일해야 한다.
- K <= N 

입력
- 첫째 줄에는 K, N이 입력

출력
- 첫째 줄에는 N개를 만들 수 있는 랜선의 최대 길이를 cm 단위의 정수로 출력
"""

import sys

def main(count_of_supply_line:int, count_of_need_line:int) -> int:

    answer = 0
    length_of_lines =[]

    # 랜선 제작을 위해 제공되는 각 랜선의 길이들
    while count_of_supply_line != 0:
        length = int(sys.stdin.readline().strip())
        length_of_lines.append(length)
        count_of_supply_line -= 1

    # count_of_need_line을 생성할 수 있는 길이 구하기
    min_length_of_lines = (min(length_of_lines)//100) * 100
    
    while True:
        count = 0
        answer = 0
        
        for line in length_of_lines:
            count = line // min_length_of_lines
            answer += count
            print(f'line: {line} / min_length_of_lines: {min_length_of_lines} / count: {count}')
        
        if answer != count_of_need_line and min_length_of_lines > 100: 
            min_length_of_lines -= 100 
        else:
            break
    
    return answer


if __name__ == "__main__":
    K, N = map(int, sys.stdin.readline().split()) # 4 11
    print(f'answer: {main(K, N)}')


