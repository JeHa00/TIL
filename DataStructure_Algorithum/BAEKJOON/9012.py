def main(values: str) -> bool: 
    """
    입력된 문자열이 VPS인지 판단하는 함수
    """
    stk = []
    isVPS = True

    for value in values:
        if value == '(':
            stk.append('(')
        elif value == ')':
            if stk:
                stk.pop()
            elif not stk: # stk에 값이 존재하지 않으면 
                isVPS = False
                break 
    
    if not stk and isVPS: # stk에 값이 존재하지 않거나, isVPS가 True이면
        print('Yes')
    elif stk or not isVPS:
        print('NO')

if __name__ == "__main__":
    T = int(input())
    while T > 0 :
        test_data = input()
        main(test_data)
        T -= 1

