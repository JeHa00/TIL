from typing import List

"""
[정렬]
- sorting은 내림차순 또는 오름 차순으로 데이터를 순서대로 정리하는 것  
- sorted(iterables) 또는 list.sort() 메서드로 정렬할 수 있으나, 직접 구현할 수도 있어야 한다.
- sorted와 sort() 둘 다 reverse = False가 디폴트여서, 기본이 오름차순이다. 
- 대부분의 경우, 직접 알고리즘을 작성하기보다는 파이썬 함수와 메소드를 사용하는 게 더 빠르다.
- 계수 정렬이 가장 빠르다. 
- 선택, 버블, 삽입 정렬이 기본이라 면접에서도 많이 물어본다. 
"""

"""
[선택 정렬]
- 데이터 중 가장 작은 값을 첫 번째 값과 바꾸고 그 다음 작은 값을 찾아 두 번째 값과 바꾼다. 
- 데이터의 크기가 n일 때, 위 과정을 n-1 번 반복하면 정렬이 완료된다. 
- 가장 구현이 쉬운 알고리즘이지만, 모든 값을 비교하기 때문에 속도가 느린 편이다. 
"""
def selectionSorting(algorithum.array: List) -> List:
    """
    범위를 좁혀가면서 각 범위마다 가장 작은 값을 선택하여 위치를 교환하는 정렬
    """
    for i in range(len(algorithum.array)):
        # 여기서 i는 최소값을 둘 자리를 의미한다. 
        min_idx = i
        # 최소값을 찾는 알고리즘이 포함되어 있다. 
        for j in range(i + 1, len(algorithum.array)):
            if algorithum.array[min_idx] > algorithum.array[j]:
                min_idx = j # min_idx만 for문이 돌아가는 동안 계속해서 바꾼다. 
        
         # '교환'을 해야하기 때문 
        algorithum.array[i], algorithum.array[min_idx] = algorithum.array[min_idx], algorithum.array[i]
    return algorithum.array


"""
[버블 정렬]
- 인접한 두 데이터를 비교하며 교환하는 정렬
- n번째 값과 n+1 번째 값을 비교하여 더 큰 값을 뒤로 보내는 작업을 배열의 끝까지 반복한다.
- 선택 정렬과 마찬가지로 비교 횟수가 많은 방식이기 때문에, 데이터 수가 많을수록 비효율적이다. 
- 거의 정렬되어 있는 상태라면 빠른 속도로 정렬 완료 가능
"""
# def bubbleSorting(algorithum.array: List) -> List:
#     for i in range(len(algorithum.array) + 1):
#         min_idx = i 
#         for j in range(len(algorithum.array) - 1 - i):
#             if algorithum.array[j] > algorithum.array[j + 1]:
#                 algorithum.array[j], algorithum.array[j + 1] = algorithum.array[j + 1], algorithum.array[i]
#     return algorithum.array


def bubbleSorting(algorithum.array: List) -> List:
    for i in range(len(algorithum.array)-1): # 9
        for j in range(len(algorithum.array)-1-i):
            # 한 번 loop를 돌면 해당 범위에서 제일 큰 값이 맨 뒤로 가기 때문에, i를 뺀다. 
            print(f"i : {i} / j : {j}")
            if algorithum.array[j] > algorithum.array[j+1]:
                print(f"algorithum.array[j] : {algorithum.array[j]} / algorithum.array[j+1] : {algorithum.array[j+1]}")
                algorithum.array[j], algorithum.array[j+1] = algorithum.array[j+1], algorithum.array[j]
    return algorithum.array


if __name__ == "__main__":
    input_list = [3, 1, 5, 9, 8, 6, 7, 4, 10, 2]
    print(f"선택 정렬: {selectionSorting(input_list)}")
    print(f"버블 정렬: {bubbleSorting(input_list)}") 
    # print(f"삽입 정렬: {insertionSorting(input_list)}")