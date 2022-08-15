"""
[problem: 1193]

- 문제
    - 지그재그 순서대로 출력되는 프로그램의 규칙을 찾아서 구현해보는 문제
    - table 처럼 되어 있는 배열을 tree 배열로 기울여서 순서대로 본다면 규칙성을 쉽게 찾을 수 있다. 
    - 짝수층이냐 홀수층이냐에 따라서 방향이 달라진다. 
    - 그리고 각층의 맨 앞과 맨 뒤는 분모와 분자가 서로 반대이며, 분자가 1씩 작아지고, 분모는 1씩 커진다. 

- 입력값: N번째 분수를 출력하기 위한 N 값

- 출력값: N번째 분수  

"""


def findFraction(n: int) -> str:
    """
     n번째 분수 찾기
    """

    container = []
    i = 1
    while True:
        if n <= sum(range(i + 1)):
            print(f"i: {i}")
            layer_index = n - sum(range(i))
            print("layer_index: ", layer_index)
            for j in range(i):
                container.append(f"{i - j}/{j + 1}")
                print(f"container: {container}")

            if i % 2:  # 홀수
                print(container[layer_index - 1])
                break

            else:  # 짝수
                print(container[-layer_index])
                break

        else:
            i += 1


if __name__ == "__main__":
    x = int(input())
    findFraction(x)
