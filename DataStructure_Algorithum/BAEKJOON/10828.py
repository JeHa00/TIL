"""
[problem: 10828
- 입력값
    - N: 명령의 수 
    - 1<= N <= 100,000 
    - 입력 명령이 push인 경우, 공백으로 띄어서 push할 값을 준다. 
    - 입력 명령이 pop인 경우, 공백이면 -1을 대신 출력한다. 

- 출력값
    - 입력값에 해당되는 값이 한 줄에 하나씩 출력
"""

import sys

def main():
    instructions_times = int(sys.stdin.readline()) 
    stack = []
    
    while instructions_times > 0:    
        text = sys.stdin.readline().strip() 
    
        if text.split()[0] == 'push':
            stack.append(text.split()[1])

        elif text == 'pop':
            print(stack.pop() if stack else -1)
        
        elif text == 'size':
            print(len(stack))
        
        elif text == 'empty':
            print( 0 if stack else 1)
        
        elif text == 'top':
            print(stack[-1] if stack else -1)
    
        instructions_times -= 1 

if __name__ == "__main__":
    main()


