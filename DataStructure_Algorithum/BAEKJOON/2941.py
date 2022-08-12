"""
[problem: 2941]

- 문제
    - 주어진 입력 단어에서 알파벳의 갯수를 세기
    - 단, 크로아티아 알파벳의 갯수를 고려한 후 나머지 문자열에서 일반 알파벳의 갯수를 중복 없이 샌다. 

- 해당 문제는 크로아티아 문자 1개는 알파벳 2개 이상으로 되어있기 때문에, 글자수를 판단하기 위해서는 1개의 문자로 대체하는 게 필요하다.

- 그래서 replace(i, '*')를 사용한다.

"""


def main(word: str) -> int:
    counts = 0
    croatia = ['c=',  'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']

    for i in croatia:
        if i in word:
            word = word.replace(i, '*')
    counts += len(word)
    print(counts)


if __name__ == "__main__":
    word = input()
    main(word)
