"""
[problem: 1152]

- 문제
    - 대소문자와 공백으로 섞인 명사구가 주어지면 총 단어의 갯수를 세어 그 수를 출력하는 문제

- 공백이 있기 때문에, split()를 사용하고, len() 한다. split()는 list를 반환한다.
"""


def main() -> int:
    noun_phrase = input().split()
    print(len(noun_phrase))


if __name__ == "__main__":
    main()
