"""
[problem: 11720]

- 공백 없이 주어진 N개의 숫자를 모두 더한 값을 구하기 


- 설명
    - 파이썬에서 합계를 구하는 함수인 sum()을 사용하기 위해서 문자열을 숫자형 데이터로 바꿔야 한다.

    - 이 때, 공백없이 주어졌기 때문에 바로 정수형으로 바꾸면 N개의 숫자가 N 자리를 가지는 한 숫자로 바뀌기 때문에, 쪼개는 게 필요하다.

    - 숫자형 데이터들을 가지는 container를 인자로 넘기면 이를 정렬하여 반환하는 sorted 내장함수는 문자열을 받게 되면 최소 단위로 쪼개진다. 

    - 이 sorted는 최소단위로 쪼개진 list data type을 반환하기 때문에, list는 __iter__를 가지고 있으므로, iterable하여 map에 사용할 수 있다.


"""

def main() -> int:
    
    result = list(map(int, sorted(input())))
    print(sum(result))


if __name__ == "__main__":
    N = int(input())
    main()
