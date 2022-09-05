"""
[Insertion sort]
- 특정한 값을 찾아 적절한 위치에 삽입하는 정렬
- 첫 번째 값을 기준으로 두 번째 값부터 마지막 값까지 순서대로 위치를 찾아 삽입한다.
- 만약 데이터가 거의 정렬되어 있는 상태라면 빠른 속도를 보이지만, 
- 그렇지 않으면 선택 및 버블 정렬처럼 속도가 느리다.
"""
from typing import List

def insertionSort(array: List) -> List:
    for i in range(1, len(array)):
        for j in range(i, 0, -1): 
            if array[j-1] > array[j]:
                array[j-1], array[j] = array[j], array[j-1]
            else:
                break
        print(array)



"""
[Counting sort]
- 숫자의 개수를 카운팅하는 정렬(카운팅 정렬)
- 데이터의 범위가 모두 포함되는 새로운 리스트를 만들고, 모든 값을 0으로 채운 뒤 데이터 값을 인덱스로 변환하여 1씩 증가
- 데이터의 범위가 제한되어 있을 때에, 한해서 가장 빠른 속도로 정렬이 가능 (퀵 정렬보다 빠르다.)
- 왜냐하면 중첩을 안쓰기 때문이다. 
- 문제로 내지는 않는데, 문제에 녹아든 경우가 많다. 
"""
def countingSort(array: List) -> List: 
    count_array = [0] * (len(array)+1)

    for i in range(len(array)):
        count_array[array[i]] += 1 
        print(f"count array: {count_array}")
    
    array.clear()

    for i in range(len(count_array)):
        for _ in range(count_array[i]):
            array.append(i)
        print(f"array: {array}")

    return array


if __name__ == "__main__":
    input_list = [3, 1, 5, 9, 8, 6, 7, 4, 10, 2]
    
    print("[Insertion Sort]")
    print(insertionSort(input_list))
    
    print("[Counting Sort]")
    print(countingSort(input_list))



