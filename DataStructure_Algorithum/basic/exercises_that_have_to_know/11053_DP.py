"""
[11053 - 가장 긴 증가하는 부분 수열]
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하기

입력값
- 수열 A의 크기
- A의 원소들

출력값
- 수열 A의 가장 긴 증가하는 부분: 수열의 길이
"""

import sys

def main() -> int:
    """
    예제는 맞았지만, 제출하니 틀렸다! 
    반례: [10 20 10 20 10 20]
    """

    # 10 20 10 20 10 20
    A = list(map(int, sys.stdin.readline().split())) 

    result = []

    for i in range(len(A)):
        if i == 0:
            result.append(A[i])
        else:
            if A[i] > A[i-1] :
                result.append(A[i])

    return len(result) 

def main2(N:int) -> int:
    """
    - 가장 긴 부분 수열의 크기이므로, 여러 개의 부분 수열 값들이 존재하고 그중에서 가장 큰 값을 반환하는 것
    - 주어진 배열의 크기 만큼의 배열을 생성하여 각 인덱스마다 조건에 맞을 때 크기를 고려한다.
    - 먼저 각 인덱스에서 성립될 수 있는 증가하는 배열들을 뽑아낸다. 
    - 그리고 나서야 이 배열들을 어떻게 코드로 작성할지를 생각해본다.
    """
    
    A = list(map(int, sys.stdin.readline().split())) 
    
    size_per_index = [1] * N

    for i in range(N):
        for j in range(i):
            if A[j] < A[i]:
                one_element_on_i = 1
                size_per_index[i] = max(size_per_index[i], size_per_index[j]+one_element_on_i)

    """
    i:1 -> j:0 -> 조건문 만족 o -> [1, 2, 1, 1, 1, 1] max(1, 1+1) => i보다 작은 값이고, 처음 나온 값이므로 반영
    i:2 -> j:0~1 -> 조건문 만족 x => i보다 같은 값이고, 큰 값이므로 증가하는 부분 수열 성립 x
    i:3 -> j:0 -> 조건문 만족 o -> [1, 2, 1, 2, 1, 1] max(1, 1+1) => (10, 30) 으로 증가하는 부분 순열 성립
        -> j:1 -> 조건문 만족 o -> [1, 2, 1, 3, 1, 1] max(2, 2+1) => (10, 20, 30) 으로 증가하는 부분 순열 성립
        -> j:2 -> 조건문 만족 x => 이미 10이 존재하여 size_per_index[j]에는 반영되지 않는다.
    i:4 -> j:0 -> 조건문 만족 o -> [1, 2, 1, 3, 2, 1] max(1, 1+1) => (10, 20)
        -> j:1 -> 조건문 만족 x => i와 j 에서 값이 동일
        -> j:2 -> 조건문 만족 x => i보다 작지만 똑같은 값이 또 나올 경우, 이전에 있던 값에서 추가되었기 때문에 반영 x
        -> j:3 -> 조건문 만족 x => i에서 값보다 j에서의 값이 더 크므로 x 
    i:5 도 이와 같은 흐름으로 흘러간다.
    """

    return max(size_per_index)


if __name__ == "__main__":
    N = int(sys.stdin.readline())
    print(main())
    main2(N)