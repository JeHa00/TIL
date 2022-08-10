"""
[problem: 4673번]

- 문제
    - 10,000보다 작거나 같은 셀프 넘버를 출력하는 프로그램 작성

- 문제 설명
    - d(n)은 n과 n의 각 자리 수를 더하는 함수  
    - 여기서 n이 생성자인데, 생성자에 의해서 생성될 수 없는 숫자가 셀프 넘버

- 입력값: X

- 출력 형식
    - 셀프 넘버를 한 줄에 하나씩 출력

- 해결 과정 중 부딪힌 문제 
    - d = i + i //10 + i % 10 으로 인해서 두 자리수만 셀프 넘버가 정확히 출려되고, 자리 수가 커지면 그러지 못 했다. 
    - 그래서 몫과 나머지로 접근하지 않고, 문자열로 변환하여 접근하는 방식을 취했더니 원하는 결과값이 나왔다.

- 소스 코드 설명
    - lambda를 사용한 이유는 함수를 일시적으로 사용할 것이기 때문이다. 
    - set을 data type을 사용한 이유는 차집합을 사용하여 셀프 넘버를 걸러내기 위해서다.
"""

from typing import List


def notSelfNumber() -> List:
    answer = set([])
    for i in range(1, 10001):
        for j in str(i):
            i += int(j)
        answer.add(i)
    # answer = set(filter(lambda x: x <= 10000, answer))
    # 변수 b가 10000까지이고, difference는 b 안에서 제거되는 것이기 때문에, filter 는 필요없다.
    return answer


def selfNumber():
    b = set(range(1, 10001))
    not_self_number = notSelfNumber()
    b = sorted(list(b.difference(not_self_number)))
    for i in b:
        print(i)


if __name__ == "__main__":
    selfNumber()
