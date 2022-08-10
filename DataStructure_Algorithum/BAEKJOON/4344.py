"""
[problem: 4344번]

- 입력값
    - 총 테스트 케이스의 개수: C
    - 각 테스트 케이스에서의 학생 수: N
    - 이 N명의 각 점수: score 

- 출력값: 각 테스트 케이스에서의 평균값을 넘는 학생 비율을 소수점 셋째 자리까지 출력하기

- 입력값에서 N과 score가 같이 주어지기 때문에, unpacking을 사용하여 구분한다. 

- 소수점 자릿수는 formatting을 사용한다. 

"""

from typing import List


def getAveragePercentage(each_case_student_number: int, each_student_score: List) -> int:

    N = each_case_student_number
    score = each_student_score
    Average_score = sum(score) / N
    students_above_average = 0
    for i in range(N):
        if score[i] > Average_score:
            students_above_average += 1
    result = students_above_average / N * 100
    print('{:.3f}%'.format(result))


if __name__ == "__main__":
    C = int(input())
    for i in range(C):
        N, *score = list(map(int, input().split()))
        getAveragePercentage(N, score)


