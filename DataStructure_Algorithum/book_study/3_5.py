"""
체인법으로 해시 함수 구현하기 
"""

from __future__ import annotations
from typing import Any, Type 
import hashlib 

class Node:

    def __init__(self, key: Any, value: Any, next: Node) -> None: 
        """초기화"""
        self.key = key # 키
        self.value = value # 값
        self.next = next  # 뒤 쪽 노드 참조  
    

class ChainedHash:
    def __init__(self, capacitiy: int) -> None: 
        """초기화"""
        self.capacity = capacitiy
        self.table = [None] * capacitiy # empty hash table이 만들어진다. 
    
    def hash_value(self, key: Any) -> int: 
        '''해시값을 구한다'''
        if isinstance(key, int):
            return key % self.capacity 

        '''
        hashlib.sha256에 바이트 문자열을 입력하면 hash 값으로 변환한다. 
        sha256에 바이트 문자열을 전달하기 위해  key를 string으로 변환 후, 
        encode() 에 전달하여 문자열을 바이트 문자열을 생성한다. 
        여기서 int(A , 16)은 16진수 문자열로 하는 int 형으로 전환한다. 
        '''
        return(int(hashlib.sha256(str(key).encode()).hexdigest(), 16) % self.capacity)

    def search(self, key: Any) -> Any: 
        """키가 key인 원소를 검색하여 값을 반환한다. """
        hash = self.hash_value(key) # 검색하는 키의 해시값
        p = self.table[hash] # 노드를 주목

        while p is not None: 
            if p.key == key: 
                return p.value 
            p = p.next 
        return None
    
    def add(self, key: Any, value: Any) -> bool: 
        '''키가 key이고, 값이 value인 원소 추가'''
        hash = self.hash_value(key)
        p = self.table[hash]
        print('p > ', p)
        while p is not None:
            if p.key == key:
                return False # 추가 실패 
            p = p.next  # 뒤쪽 노드를 주목

        temp = Node(key, value, self.table[hash])
        self.table[hash] = temp  # 노드를 추가
        return True  # 추가 성공 
    
    def remove(self, key: Any) -> bool: 
        '''키가 key인 원소를 삭제'''
        hash = self.hash_value(key)
        p = self.table[hash]
        pp = None 

        while p is not None:
            if p.key == key: 
                if pp is None: 
                    self.table[hash] = p.next 
                else:
                    pp.next = p.next 
                return True
            pp = p 
            p = p.next 
        return False

    def dump(self) -> None: 
        '''해시 테이블을 덤프'''
        for i in range(self.capacity):
            p = self.table[i]
            print(i, end = '')
            while p is not None: 
                print(f' -> {p.key} ({p.value})', end = '')
                p = p.next 
            print()

from enum import Enum 

Menu = Enum('Menu', ['추가', '삭제', '검색', '덤프', '종료']) # 메뉴를 선언

def select_menu() -> Menu: 
    s = [f'({m.value}){m.name}' for m in Menu]
    while True: 
        print(*s, sep = '    ', end = '')
        n = int(input(': '))
        if 1 <= n <= len(Menu):
            return Menu(n)

hash = ChainedHash(13)

while True: 
    menu = select_menu() # 메뉴 선택 

    if menu == Menu.추가: #추가
        key = int(input('추가할 키를 입력하세요. : '))
        val = input('추가할 값을 입력하세요. : ')
        if not hash.add(key, val):
            print('추가를 실패했습니다.')
    
    elif menu == Menu.삭제:  # 삭제
        key = int(input('삭제할 키를 입력하세요. : '))
        if not hash.remove(key):
            print('삭제를 실패했습니다.!')

    elif menu == Menu.검색: # 검색
        key = int(input('검색할 키를 입력하세요. : '))
        t = hash.search(key)
        if t is not None: 
            print(f'검색할 키를 갖는 값은  {t}입니다.')
        else:
            print('검색할 데이터가 없습니다.')
    
    elif menu == Menu.덤프: # 덤프 
        hash.dump()
    
    else:
        break 