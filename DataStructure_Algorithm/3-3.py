"""
선형 검색 알고리즘을 보초법으로 수정하기

"""
from copy import copy
from typing import Any, Sequence

def seq_search(a: Sequence, key: Any) -> int:

    # 인자로 입력된 sequence에 직접 append를 하면 원본이 수정되기 때문에, deepcopy로 동일한 value를 가진 다른 객체를 만든다. 
    seq = copy.deepcopy(a)
    
    # 보초 추가 
    seq.append(key)

    i = 0

    while True: 
        if seq[i] == key:
            break 
        i += 1  
    return -1 if i == len(seq) else i 

if __name__ == '__main__':
    
    num = int(input('원하는 원소 갯수를 입력하세요. : '))

    x = [None] * num   
    
    for i in range(num):
        x[i] = int(input('원하는 값을 입력하세요. : '))

    ky = int(input('찾고자 하는 값을 입력하세요. : '))

    idx = seq_search(x, ky)  


    if idx == -1:
        print('Don\' find it')
    
    else:
        print('The value is in x[{}]'.format(idx))
