"""
[problem: 11654]

- 소문자, 대문자, 숫자 0~9 중 하나가 주어졌을 때, 주어진 글자의 아스키 코드를 출력하기

- 파이썬에서는 입력된 문자를 아스키 코드로 변환하는 ord() fuction이 있으므로, 이를 사용하면 간단히 구현할 수 있다.

"""

def main(value: str) -> str:

    result = ord(value)
    print(result)


if __name__ == "__main__":
    input_value = input()
    main(input_value)