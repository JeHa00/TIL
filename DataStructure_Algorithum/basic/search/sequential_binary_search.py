# 선형 탐색
# 데이터의 수에 비례하여 시간 복잡도가 커진다. 
# 데이터가 정렬되지 않은 상태에서도 사용 가능하다. 

def sequentialSearch(array, target):
    for i in range(len(array)): 
        if array[i] == target:
            return i 
    return False 

    """
    # while 문으로 작성하기
    i = 0
    while True: 
        if i == len(a):
            return False

        if array[i] == target:
            return i 
        
        i += 1
    """


li = [3, 1, 5, 9, 8, 6, 7, 4, 10, 2]

print("Sequential Search")
print(sequentialSearch(li, 6)) # 5
print(sequentialSearch(li, 2))# 9
print(sequentialSearch(li, 11)) # False

# 이진 탐색

# 탐색 범위를 반으로 줄여 나간다.  
# 이진 탐색은 정렬이 되어 있을 때, 효과적이다. 
li = [1, 3, 5, 6, 7, 8, 9, 13, 15, 17, 19]

def binarySearch(array, target):
    start = 0
    end = len(array) - 1

    while start <= end: 
        mid = (start+end) // 2
        if array[mid] > target: 
            end = mid - 1 

        elif array[mid] < target:
            start = mid + 1 
        else:
            return mid 
    
    return False

print(binarySearch(li, 6))
print(binarySearch(li, 15))
print(binarySearch(li, 20))