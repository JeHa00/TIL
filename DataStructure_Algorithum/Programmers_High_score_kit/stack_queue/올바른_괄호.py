"""
[올바른 괄호]
괄호로만 이뤄진 문자열이 올바른 괄호인지 아닌지 판단하는 프로그램을 작성하세요.
"""

def main(str) -> bool:
    """
    효율성 테스트 (수정 전)
    #1: 17.17ms
    #2: 19.26ms

    효율성 테스트 (수정 후)
    #1: 7.49ms
    #2: 6.83ms
    """

    # 수정 후
    stack = []
    for s in str:
        if '(' in stack and s == ')':
            stack.pop()
        else:
            stack.append(s)
    """
    수정 전
    stack = []
    for s in str:
        stack.append(s)
        if stack[-2:] == ['(', ')']:
            del stack[-2:]
    """

    return True if len(stack) == 0 else False


if __name__ == "__main__":
    s_list = ["()()", "(())()", ")()(", "(()("]
    for s in s_list:
        print(main(s))