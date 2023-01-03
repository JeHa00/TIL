from typing import List

def quick_sort(array: List) -> List:
    """Quick Sorting algorithum
    pivot 을 기준으로 분류하고, 최소 크기로 쪼개어 정렬하는 방법

    Args:
        array (List): 정렬할 리스트

    Returns:
        List: 정렬된 리스트
    """
    if len(array) <= 1:
        return array
    
    else: 
        pivot = array[0]

        greater_list = []
        less_list = []
        eq_list = []
        for element in array:
            if element < pivot:
                less_list.append(element)
            
            elif element == pivot: 
                eq_list.append(element)
            
            else: 
                greater_list.append(element)
        
        return quick_sort(less_list) + eq_list + quick_sort(greater_list)


if __name__ == "__main__":
    item_list = [2, 5, 1, 9, 2, 3, 2, 1]
    result = quick_sort(item_list)
    print(result)