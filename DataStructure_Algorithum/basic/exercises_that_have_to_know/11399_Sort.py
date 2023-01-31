"""
[ATM - 11399]
ATM 앞에 N명의 사람이 있고, 각 N명이 인출하는데 걸리는 시간이 주어졌을 때,
모든 사람이 인출하는 데 필요한 시간의 최솟값을 구하기

입력값
- N: 사람 수
- P: 각 사람이 인출하는데 걸린 시간

출력값
- 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값
"""

import sys

def main(number_of_people: int) -> int:

    result = []
    times_to_extract_per_one = list(map(int, sys.stdin.readline().split()))
    times_to_extract_per_one.sort()

    for n_th in range(number_of_people):
        result[n_th] = sum(times_to_extract_per_one[:n_th+1])

    return sum(result)

if __name__ == "__main__":
    number_of_people = int(sys.stdin.readline())
    print(main(number_of_people))

