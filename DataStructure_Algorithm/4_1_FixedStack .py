"""
고정 길이 stack을 FixedStack class로 구현해보기 
"""

from inspect import stack
from typing import Any

class FixedStack: 
    """고정 길이 스택 클래스"""

    class Empty(Exception):
        """비어있는 FixedStack에 pop 또는 피크를 수행할 시 내보내는 예외 처리"""
        pass 

    class full(Exception):
        """가득 차 있는 FixedStack에 push를 수행할시 내보내는 예외 처리"""
        pass 

    def __init__(self, capacity: int) -> None: 
        self.capacity = capacity
        self.stk = [None] * self.capacity
        self.ptr = 0

    def __len__(self, key: int) -> int: 
        """FixedStack의 데이터 개수를 반환"""
        return self.ptr
    
    def is_empty(self) -> bool: 
        """스택이 비어있는지 판단"""
        return self.ptr <=  0   # 오류가 날 가능성을 방지하기 위해 부등호로 설계

    def is_full(self) -> bool: 
        """스택이 가득 차 있는지 판단"""
        return self.ptr  >= self.capacity   # 오류가 날 가능성을 방지하기 위해 부등호로 설계
    
    def push(self, value: Any) -> None: 
        """스택에 value를 push"""
        if self.is_full: 
            raise FixedStack.full
        self.stk[self.ptr] = value
        self.ptr += 1 
    
    def pop(self) -> int: 
        """stack에 데이터를 pop"""
        if self.is_empty: 
            raise FixedStack.Empty
        self.stk.pop()
        self.ptk -= 1 
        return self.stk[self.ptk]
    
    def peek(self) -> int: 
        """stack의 맨 꼭대기에 있는 value를 반환"""
        if self.is_empty:
            raise FixedStack.Empty
        return self.stk[self.ptr - 1] # ptr은 갯수이므로 indexing 할려면 -1을 해야 한다.  

    def clear(self) -> None: 
        """stack의 모든 value를 삭제"""
        if self.is_empty:
            raise FixedStack.Empty
        self.ptr = 0 
        # 실제로 삭제되는가? 
    
    def find(self, value: int) -> int:
        """stack 안에 value를 찾아 그 idex를 반환"""
        if self.is_empty:
            raise FixedStack.Empty
        for i in range(self.stk[self.ptk - 1], -1, -1):
            if self.stk[i] == value:
                return i
        print(f'{value}를 찾을 수 없습니다.')
        return -1 
    
    def count(self, value: int) -> int: 
        """stack 안에 value의 갯수를 반환"""
        x = 0
        for i in self.stk:
            if self.stk[i] == value:
                x += 1
        return x
    
    def __contains__(self, value) -> bool: 
        """stack에 value가 있는지 판단"""
        return self.count(value) > 0 
    
    def dump(self) -> None:
        """덤프(stack 안의 모든 데이터를 바닥부터 꼭대기 순으로 출력)"""
        if self.is_empty:
            print('스택이 비어있다.')
        else:
            print(self.stk[:self.ptr])

from enum import Enum

Menu = Enum('Menu', ['추가', '삭제', '피크', '찾기', '덤프', '종료'])

k = FixedStack(5)
