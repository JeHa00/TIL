"""
problem 9번: 시험 등수 구하기 

- Input 값: 
    - score1 = [90, 87, 87, 23, 35, 28, 12, 46]
    - score2 = [10, 20, 20, 30]

- 결과값
    - 위 입력값의 점수값 순서대로 전체 순위 표시하기
    - score1에 대한 원하는 값: [1, 2, 2, 7, 5, 6, 8, 4]
    - score2에 대해 원하는 값: [4, 2, 2, 1]


- score에서 std_value 번째 값과 비교하여 큰 값이 존재하면 1을 하나씩 추가하는 방식 

"""

def solution(score):
    score_len = len(score)
    answer = [1 for _ in range(score_len)]

    std_value = 0 
    # std_value는 계속해서 증가하기 때문에 최대값은 score의 길이만큼이다. 
    while std_value < score_len: 
        for j in range(score_len): 

            # std_value와 비교해서 큰 값을 찾으면 += 1 을 하면 등수를 내린다. 
            if score[std_value] < score[j]: 
                answer[std_value] += 1  
            print(f"std_value: {std_value} / answer: {answer}")

        # 다음 value로 이동
        std_value += 1



if __name__ == "__main__": 
    score1 = [90, 87, 87, 23, 35, 28, 12, 46]
    ret1 = solution(score1)

    print("solution 함수의 반환 값은", ret1, "입니다.")

    score2 = [10, 20, 20, 30]
    ret2 = solution(score2)

    print("solution 함수의 반환 값은", ret2, "입니다.")