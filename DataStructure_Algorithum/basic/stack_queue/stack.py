# 후입선출 구조

# 데이터 삽입: push / 데이터 제거: pop

# list를 통해 stack 구현

# 큐에서 밑 부분이 막혀있는 데이터 구조: 적재된 박스

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
    n = stack[len(stack) - 1]
    stack.pop()
    return n 

def top():
    return stack[len(stack) - 1]


stack = []

push(7)
push(5)
push(3)
push(2)
pop()
pop()
push(4)
print(stack)








# import sys

# def main(): 
    
#     text = sys.stdin.readline()
#     stack = []

#     if text == 'push':
#         stack.append()

#     elif text == 'pop':
#         stack.pop()
    
#     elif text == 'empty':
#         print(1 if stack else -1)


#     elif text == 'top':
#         print(stack[-1] if stack else -1)    
    







# if __name__ == '__main__':
#     main()