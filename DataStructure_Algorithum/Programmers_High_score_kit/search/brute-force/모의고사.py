"""
[완전 탐색]
- 수포자 3명 중 가장 많은 답을 맞힌 인원 1, 2, 3 중 반환하기
- 다 동일한 갯수를 맞히면 동일한 갯수를 맞힌 인원 모두 반환한다. 
- 수포자 인원: 3명

각 수포자의 찍는 패턴
- 수포자 1: 1, 2, 3, 4, 5를 계속 반복 ex) 1, 2, 3, 4, 5, 1, 2, 3, 4, 5
- 수포자 2: 2, 1, 2, 3, 2, 4, 2, 5 를 반복
- 수포자 3: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5를 반복


정답 목록을 list로 주어진다. 

시험문제는 최대 10,000문제
"""
from typing import List
def main(answers: List) -> List:
    answer = []
    answer_count_per_student = [0 for _ in range(3)] # 1, 2, 3번 학생의 각 정답 갯수
    
    # 문제 수 맞추기
    total_problem = len(answers)
    first_pattern = [n for n in range(1, 6)]
    second_pattern = [2, 1, 2, 3, 2, 4, 2, 5]
    third_pattern = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    patterns = [first_pattern, second_pattern, third_pattern]
    for pattern in patterns:
        if len(pattern) < total_problem:
            if divmod(total_problem, len(pattern))[1] == 0:
                pattern *= divmod(total_problem, len(pattern))[0]
            else: 
                pattern *= divmod(total_problem, len(pattern))[0] + 1
    
    # 정답과 찍은 답 각각 비교하여 맞춘 문제 카운트하기 
    while total_problem > 0:
        idx = len(answers) - total_problem
        if answers[idx] == first_pattern[idx]:
            answer_count_per_student[0] += 1 
        if answers[idx] == second_pattern[idx]:
            answer_count_per_student[1] += 1 
        if answers[idx] == third_pattern[idx]:
            answer_count_per_student[2] += 1 
        total_problem -= 1 

    # 최대 정답 맞힌 수와 비교하여 총 맞힌 인원 찾기
    max_answer_count = max(answer_count_per_student)
    for n_th, answer_count in enumerate(answer_count_per_student):
        if max_answer_count == answer_count:
            answer.append(n_th + 1)
    return answer


if __name__ == "__main__":
    answers_list = [[1,2,3,4,5], [1,3,2,4,2]]
    for answers in answers_list:
        print(main(answers))