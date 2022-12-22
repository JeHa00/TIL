# deque로 stack 만들기
# size(). isEmpty(), push(), pop(), top()
# push(7) -> push(5) -> push(3) -> push(2) -> pop() -> pop() -> push(4)

from collections import deque



def size():
    print(len(stack))

def isEmpty():
    if len(stack) == 0:
        return True
    else:
        return False 

def push(n):
    stack.append(n)


def pop():
    if isEmpty():
        return 
    else:
        n = stack[len(stack) - 1]
        stack.pop() 
        return n 

def top():
    if isEmpty():
        return 
    else:
        return stack[len(stack) - 1]


stack = deque() 

pop()
top()
print(stack)

