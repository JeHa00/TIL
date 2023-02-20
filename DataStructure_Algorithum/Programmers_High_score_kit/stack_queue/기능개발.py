"""
[기능 개발]
현재 진행률을 나타낸 기능 서비스 목록들과 각 서비스의 하루 진척도를 나타내는 2개의 배열 정보를 줍니다.

그러면 날마다 배포하는 기능의 수를 배열로 반환합니다. 

앞의 기능이 미완성되고 그 뒤의 기능이 완성되었을 때, 앞의 기능이 완성되지 않으면 뒤의 기능은 배포되지 못합니다.
"""

from typing import List
def main(progresses: List, speeds: List) -> List:
    answer = [0]
    days_until_deployment_per_program = []

    # 각 기능마다 배포까지 남은 날 수 계산
    for progress, speed in zip(progresses, speeds):
        if divmod(100-progress, speed)[1] != 0:
            day_until_deployment = divmod(100-progress, speed)[0] + 1
            days_until_deployment_per_program.append(day_until_deployment)
        else:
            day_until_deployment = divmod(100-progress, speed)[0]
            days_until_deployment_per_program.append(day_until_deployment)

    # 위 날 수를 기준으로 각 배포마다 몇 개의 기능이 배포되는지 계산
    max_days_until_deployment = days_until_deployment_per_program[0]
    for day in days_until_deployment_per_program:
        if max_days_until_deployment >= day:
            answer[-1] += 1
        else: 
            answer.append(1)
            max_days_until_deployment = day 

    return answer


if __name__=="__main__":
    progresses_list = [[93, 30, 55], [95, 90, 99, 99, 80, 99]]
    speeds_list = [[1, 30, 5], [1, 1, 1, 1, 1, 1]]
    for progresses, speeds in zip(progresses_list, speeds_list):
        print(main(progresses, speeds))
