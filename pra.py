def main(values: str) -> str: 
    """
    입력된 문자열이 VPS인지 판단하는 함수
    """
    point = 0

    for value in values:
        if value == '(':
            point += 1
        else: 
            point -= 1 
    
    if point != 0:
        print('Yes')
    else: 
        print('NO')

if __name__ == "__main__":
    T = int(input())
    while T >= 0 :
        test_data = input()
        main(test_data)
        T -= 1


