"""
[problem: 10809]

- 문제
    - 주어진 소문자 영단어 S에 a~z까지 알파벳이 어느 위치에 처음 등장하는지 알려주는 프로그램으로, 포함되지 않으면 -1을 출력

- S: 주어진 단어
- 파이썬에는 index라는 내장함수가 있다. 찾으려는 문자열이 발견되면 제일 작은 인덱스를 즉 첫 번째로 발견되는 인덱스를 반환하고, 
없으면 ValueError를 일으킨다. 그래서 try ~except 구문을 사용하여 ValueError가 발생되면 Error로 중단되지 않고, -1을 추가하도록 한다. 


- find()를 사용할 수 있지만, 실행 시간을 비교해보면 find보다 index()를 사용하여 로직을 직접 짜는 게 더 빠르다. 

- from algorithum.string import ascii_lowercase 로 알파벳 소문자 배열을 쉽게 만들 수 있다.

"""


def main(text: str) -> int:
    from algorithum.string import ascii_lowercase
    alphabet_list = list(ascii_lowercase)
    result = []
    for i in alphabet_list:
        try:
            j = text.index(i)
            result.append(j)
        except ValueError:
            result.append(-1)
    for j in result:
        print(f"{j}", end=" ")


if __name__ == "__main__":
    s = input()
    import time
    start = time.time()
    main(s)
    end = time.time()

    print(f"time {end - start}s")
