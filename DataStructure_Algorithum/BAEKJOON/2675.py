"""
[problem: 2675]

- 문자열, 문자열의 각 문자 반복 횟수를 입력받아서 새로운 문자열을 출력하는 프로그램 작성하기

- 입력 형식
    - 첫째 줄에는 test case T의 개수가 주어진다.
    - 두 번째 줄에는 공백을 구분으로 반복 횟수 R과 문자열 S가 주어진다.
- 문자열 또한 숫자형 데이터처럼 더해진다.

"""


def main() -> str:
    T = int(input())  # Test case count
    while T > 0:
        R, S = input().split()  # Repeat times, String
        R = int(R)

        """
        for s in S:
            print(f"{s * R}", end='')
        print()
        
        위 방식보다는 아래 방식을 사용해보자.
        """

        text = ""
        for s in S:
            text += s * R
        print(text)
        T -= 1


if __name__ == "__main__":
    main()
