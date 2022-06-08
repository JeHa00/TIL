"""
이진 탐색(Binary Search) 알고리즘 구현
"""

from typing import Sequence, Any 


def binary_search(seq: Sequence, key: Any) -> int: 

    pl = 0  # 검색 범위의 맨 앞 원소의 index
    pr = len(seq) - 1   # 검색 범위의 맨 뒤 원소의 index


    while True:
        pc = (pr + pl) // 2 # 검색 범위의 중앙 원소의 index  
        
        if seq[pc] == key: # key를 찾았을 경우
            return pc 

        elif seq[pc] > key: # 값이 key 보다 클 경우 
            pr = pc - 1     # 검색 범위를 앞 쪽으로 좁힌다.

        else:               # 값이 key 보다 작을 경우
            pl = pc + 1     # 검색 범위를 뒤 쪽으로 좁힌다. 

        if pl > pr: 
            break 

    return -1 # pc를 결국 찾지 못 했을 경우 

if __name__ == '__main__':

    num = int(input('입력하기 원하는 성분 수를 입력하세요. :'))
    a = [None] * num 

    for i in range(num):
        a[i] = int(input('원하는 성분 값을 입력하세요: '))
    
    ky = int(input('찾기 원하는 값을 입력하세요: '))

    a.sort() # 오름차순으로 정렬 

    idx = binary_search(a, ky)

    if idx == -1:
        print('Don\'t find it')
    else:
        print(f'{ky}는 인덱스 {idx}에 있습니다.')