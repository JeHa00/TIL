"""
[최소 직사각형]
주어진 명함 가로 세로 길이를 모두 담을 수 있는 지갑 크기 결정하기
"""
from typing import List

def main(sizes: List) -> int:
    answer = 0 
    sorted_sizes = []
    for size in sizes:
        if size[0] < size[1]:
            sorted_sizes.append([size[1], size[0]])
        else:
            sorted_sizes.append([size[0], size[1]])
    max_width = sorted(sorted_sizes, key=lambda x: x[0], reverse=True)[0][0]
    max_height = sorted(sorted_sizes, key=lambda x:x[1], reverse=True)[0][1]
    answer = max_width * max_height
    return answer

if __name__ == "__main__":
    sizes_list = [[[60, 50], [30, 70], [60, 30], [80, 40]], [[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]], [[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]]
    for sizes in sizes_list:
        print(main(sizes))