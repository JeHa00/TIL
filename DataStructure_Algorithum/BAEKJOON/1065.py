"""
[problem: 1065]


- 한수 구하기
    - 한수란? 정수의 각 자리들의 차이가 일정하여 등차수열을 이루는 숫자 
        - ex) 123, 321 같이 일정한 차이로 증가 또는 감소 또는 일정
        - ex) 1, 11, 22 또한 한수에 포함

- 각 자리 간 차이이기 때문에, string으로 conversion 후 index를 사용하여 원하는 자리수 값을 지정한 다음에 integer로 전환

"""


def main(N: int) -> int:
    number = 0
    for i in range(1, N + 1):
        if i < 100:
            number += 1
        else:
            i = str(i) 
            if int(i[1]) - int(i[0]) == int(i[2]) - int(i[1]):
                number += 1
    print(number)


if __name__ == "__main__":
    limit = int(input())
    while limit > 1000:
        print("1000 이하의 자연수를 입력하시오.")
        limit = int(input())
    main(limit)
