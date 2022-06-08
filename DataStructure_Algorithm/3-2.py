"""
for 문으로 작성한 선형 검색 알고리즘
"""

from typing import Any, Sequence 


def seq_search(a: Sequence, key: Any) -> int:
    """
    Sequence a에서 key와 값이 같은 원소를 선형 검색하여, 값을 찾을 경우 그 값의 인덱스를 반환하는 함수
    """
    
    # 이를 for문으로 입력하면 다음과 같다. 
    # while 문과 달리 key를 찾지 못 했을 경우는 for문이 자동적으로 끝난 경우다.

    for i in range(len(a)):
            if a[i] == key:
                return i
    return -1 
    

if __name__ == '__main__': 

    num = int(input('원하는 원소 갯수를 입력하세요. : '))


    """
    x = []  
    
    for _ in range(num):
        k = int(input('원하는 값을 입력하세요. : '))
        x.append(k)
    
    이 방법보다 아래 방법이 보다 직관적이다.  
    """
        
    # 원하는 갯수 만큼의 인덱스가 만들어진다.
    x = [None] * num   
    
    for i in range(num):
        x[i] = int(input('원하는 값을 입력하세요. : '))

    ky = int(input('찾고자 하는 값을 입력하세요. : '))


    idx = seq_search(x, ky)  

    if idx == -1:
        print('Don\' find it')
    
    else:
        print('The value is in x[{}]'.format(idx))

    t = (4, 7, 5.6, 2, 3.14, 1)
    s = 'string'
    a = ['DTS', 'AAC', 'FLAC']

    print(f'{t}에서 5.6의 인덱스는 {seq_search(t, 5.6)}이다.')
    print(f'{s}에서 "t"의 인덱스는 {seq_search(s, "t")}이다.')
    print(f'{a}에서 "DTS"의 인덱스는 {seq_search(a, "DTS")}이다.')
