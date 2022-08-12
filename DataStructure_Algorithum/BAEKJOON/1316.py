"""
[problem: 1316]

- 문제: 입력한 단어들 중에서 그룹 단어의 개수를 출력하기 
    - 그룹 단어: 스펠링이 연속해서 나타나는 단어. 만약 연속해서 나타나다가 떨어져서 다시 나오면 그룹 단어에 해당되지 않는다. 

- 문제를 제대로 파악하지 않아 오래 걸렸다. 


"""


def countGroupWord(input_words_times: int) -> int:
    N = input_words_times
    result = N 
    for _ in range(N):
        word = input()
        for i in range(len(word) - 1):
            if word[i] == word[i + 1]:
                pass
            elif word[i] in word[i + 2:]:
                result -= 1
                break

    print(result)


if __name__ == "__main__":
    N = int(input())
    countGroupWord(N)
