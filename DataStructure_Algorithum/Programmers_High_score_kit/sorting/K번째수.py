"""
[K번째 수]
임의의 수가 담겨진 배열 array와
[i, j, k]의 배열을 가진 값들을 가진 배열인 commands가 주어졌을 때 array에서 인덱스 i부터 j까지의 값을 슬라이싱한다.
이 슬라이싱한 걸 정렬한 후, k번째 값을 가져오는 프로그램을 작성한다. 
"""
from typing import List
def main(algorithum.array: List, commands: List) -> List:
    answer = []
    for command in commands: 
        i, j, k  = command
        slicing = algorithum.array[i-1:j]
        slicing.sort() 
        answer.append(slicing[k-1])

    return answer



if __name__ == "__main__":
    algorithum.array = [1, 5, 2, 6, 3, 7, 4]
    commands = [[2, 5, 3], [4, 4, 1], [1, 7, 3]]
    print(main(algorithum.array, commands))