"""
[problem: 8959번]

- ox 퀴즈의 결과가 문자열로 주어진다. 

- o가 있으면 1점이 추가되고, 이 때부터 o가 총 연속되어 2번 있으면 그 다음 점수는 2점이 추가된다. 
    연속되어 맞춘만큼 추가되는 점수가 올라간다. 

- 출력 형식 제한: 얻은 점수만 출력한다. 

- 총 입력한 문제 수를 입력한 후, 각 문제의 처음부터 탐색을 진행하여 연속된 정답이 있으면 추가된 점수가 올라가므로 중첩 for문을 사용한다.
"""


def get_score(test_number: int, ) -> int:
    for i in range(test_number):
        ox_test_result = input()
        score = 0
        Add_score = 0
        for i in range(len(ox_test_result)):
            if ox_test_result[i] == "X":
                Add_score = 0
            else:
                Add_score += 1
                score += Add_score
        print(score)


if __name__ == "__main__":
    test_number = int(input())
    get_score(test_number)
