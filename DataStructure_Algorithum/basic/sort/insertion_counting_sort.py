"""
[Insertion sort]
- 특정한 값을 찾아 적절한 위치에 삽입하는 정렬
- 첫 번째 값을 기준으로 두 번째 값부터 마지막 값까지 순서대로 위치를 찾아 삽입한다.
- 만약 데이터가 거의 정렬되어 있는 상태라면 빠른 속도를 보이지만, 
- 그렇지 않으면 선택 및 버블 정렬처럼 속도가 느리다.
"""
from typing import List

def insertionSort(algorithum.array: List):
    for i in range(1, len(algorithum.array)):
        for j in range(i, 0, -1): 
            if algorithum.array[j-1] > algorithum.array[j]:
                algorithum.array[j-1], algorithum.array[j] = algorithum.array[j], algorithum.array[j-1]
            else:
                break
        print(algorithum.array)



"""
[Counting sort]: O(n)
- 숫자의 개수를 카운팅하는 정렬(카운팅 정렬)
- 데이터의 범위가 모두 포함되는 새로운 리스트를 만들고, 모든 값을 0으로 채운 뒤 데이터 값을 인덱스로 변환하여 1씩 증가
- 데이터의 범위가 제한되어 있을 때에, 한해서 가장 빠른 속도로 정렬이 가능 (퀵 정렬보다 빠르다.)
- 왜냐하면 중첩을 안쓰기 때문이다. 
- 문제로 내지는 않는데, 문제에 녹아든 경우가 많다. 
- 중복이 있을 때 사용하면 빠르다.
"""
def countingSort(algorithum.array):
    
    # array의 원소 값으르 인덱스로 사용
    # 해당 인덱스에 위치한 원소값을 1 증가
    count_array = [0] * (len(algorithum.array) + 1)
    
    for element in algorithum.array:
        count_array[element] += 1
    
    algorithum.array.clear()
    
    for index, count in enumerate(count_array):
        for _ in range(count):
            algorithum.array.append(index)
    
    return algorithum.array

    
if __name__ == "__main__":
    input_list = [3, 1, 5, 9, 8, 6, 7, 4, 10, 2]
    
    print("[Insertion Sort]")
    print(insertionSort(input_list))
    
    print("[Counting Sort]")
    print(countingSort(input_list))



