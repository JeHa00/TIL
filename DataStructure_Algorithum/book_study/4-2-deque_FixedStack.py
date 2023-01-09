"""
collections.deque를 사용하여 고정 길이 stack 구현하기 
"""

from typing import Any
from collections import deque 

class Stack(): 
    """고정 길이 스택 클래스 (collections.deque를 사용)"""

    def __init__(self, maxlen: int) -> None: 
        """스택 초기화"""
        self.capacity = maxlen 
        self.__stk = deque([], maxlen) 

    def __len__(self) -> int:
        return len(self.__stk)

    def is_empty(self) -> bool: 
        """stack이 비어 있는지 판단"""
        return not self.__stk

    def is_full(self) -> bool: 
        """stack이 가득 차 있는지 판단"""
        return len(self.__stk) == self.capacity
    
    def push(self, value: int) -> None: 
        """stack에 value를 푸시"""
        return self.__stk.append(value)
    
    def pop(self) -> Any: 
        """stack에서 data를 pop"""
        return self.__stk.pop() 
    
    def peek(self) -> Any: 
        """stack에서 data를 피크"""
        return self.__stk[-1]

    def clear(self) -> None: 
        """stack을 비움"""
        self.__stk.clear()
    
    def find(self, value: int) -> Any: 
        """stack에서 value를 찾아 인덱스를 반환(찾지 못하면 -1을 반환)"""
        try:
            return self.__stk.index(value)
        except ValueError:
            return -1 
    
    def count(self, value: int) -> any: 
        """stack에서 value의 갯수를 반환"""
        return self.__stk.count(value)
    
    def __contains__(self, value: int) -> bool: 
        """stack에 value가 있는지 확인"""
        return self.count(value)
    
    def dump(self) -> int: 
        """stack 안에서 모든 데이터를 나열"""
        print(list(self.__stk))