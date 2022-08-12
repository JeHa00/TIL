"""
[problem: 5622]

- 문제
    - 전화를 걸고 싶은 번호가 있으면 숫자 하나를 누른 다음 금속 핀이 있는 곳까지 시계방향으로 돌린다. 
    - 숫자를 하나 누르면 다이얼이 처음 위치로 돌아가므로, 다음 숫자는 처음 위치에서 다시 돌린다. 
    - 주어진 알파벳 대문자로 다이얼을 걸기 위해 필요한 최소시간을 출력하기 
    - 숫자 1을 걸기 위해서는 2초가 걸린다. 
    - 한 칸 옆에 있는 숫자를 걸기 위해서는 1초씩 더 걸린다. 
    - 숫자는 연속적으로 되어 있다. 

- 입력값
    - 알파벳 대문자

- 인덱스를 사용할 수도 있지만, 이전 문제들과 달리 dictionary로 알파벳 하나하나에 대해서 번호를 매핑하고 싶었다.  

- 5622_version2.py 와 비교했을 때, 딕셔너리로 dial 를 만드는 것보다 작은 list를 형성하여 index를 사용하는 방법이 훨씬
시간이 빠르고, 코드양도 더 적다는 걸 알 수 있었다. 탐색하는데 시간이 훨씬 오래 걸리기 때문에 시간 복잡도가 더 높아진다.
"""


def dial(spelling: str):
    """
    dial 자판을 만드는 함수
    """
    alphabet_number = {}
    alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    for i in alphabet:
        if i in ('A', 'B', 'C'):
            alphabet_number[i] = 2

        elif i in ('D', 'E', 'F'):
            alphabet_number[i] = 3

        elif i in ('G', 'H', 'I'):
            alphabet_number[i] = 4

        elif i in ('J', 'K', 'L'):
            alphabet_number[i] = 5

        elif i in ('M', 'N', 'O'):
            alphabet_number[i] = 6

        elif i in ('P', 'Q', 'R', 'S'):
            alphabet_number[i] = 7

        elif i in ('T', 'U', 'V'):
            alphabet_number[i] = 8

        else:
            alphabet_number[i] = 9

    return alphabet_number[spelling]


def findDialMinimumTime1(word: str) -> int:
    result = 0
    for s in word:
        result += dial(s) + 1
    print(result)


def findDialMinimumTime2(word: str) -> int:
    alphabet = ['ABC', 'DEF', 'GHI', 'JKL', 'MNO', 'PQRS', 'TUV', 'WXYZ']
    result = 0
    for s in word:
        for j in alphabet:
            if s in j:
                result += alphabet.index(j) + 3
    print(result)


if __name__ == "__main__":
    import time
    word = input()

    # dictrionary
    start = time.time()
    findDialMinimumTime1(word)
    end = time.time()
    print(f"dictrionary version: {end - start}s")  # UNUCIC일 때 9.203s

    # list
    start = time.time()
    findDialMinimumTime2(word)
    end = time.time()
    print(f"list version: {end - start}s")  # UNUCIC일 때 3.177s
