from typing import List



def selectionSorting(array: List) -> List:
    
    for i in range(len(array)): 
        # 여기서 i는 최소값을 둘 자리를 의미 
        min_idx = i
        for j in range(i + 1, len(array)): 
            if array[min_idx] > array[j]:
                min_idx = j 
        
        array[i], array[min_idx] = array[min_idx], array[i]
        print(array)
    return array


def bubbleSorting(array: List) -> List: 
    for i in range(len(array) + 1):
        min_idx = i 
        for j in range(len(array) - 1 - i):
            if array[j] > array[j + 1]:
                array[j], array[j + 1] = array[j + 1], array[i]

    return array


# def insertionSorting(array: List) -> List:

if __name__ == "__main__":
    input_list = [3, 1, 5, 9, 8, 6, 7, 4, 10, 2]
    print(f"선택 정렬: {selectionSorting(input_list)}")
    print(f"버블 정렬: {bubbleSorting(input_list)}") 
    # print(f"삽입 정렬: {insertionSorting(input_list)}")