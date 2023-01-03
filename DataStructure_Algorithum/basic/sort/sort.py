# 선택 정렬
a = [8, 6, 4, 1, 2, 3, 5, 10, 9, 7]
count = 0
b = a

print(f"Before sorting: {a}")
for i in range(len(a)): # 범위가 점차 좁혀가는 걸 구현
    print(f"count: {count}")
    print(a)
    min = i # 해당 범위의 맨 앞 원소가 제일 작은 것이라 가정  

    # 위와 같이 가정하는 이유는 오름차순으로 정리할 것이기 때문 

    # 밑에 코드는 최대 최소를 구하는 함수
    for j in range(i, len(a)):
        if a[j] < a[min]:
            min = j
    a[min], a[i] = a[i], a[min]
    count += 1

print(f"After sorting: {a}")

# 버블 정렬
a = [8, 6, 4, 1, 2, 3, 5, 10, 9, 7]
for i in range(len(a)):
    for j in range(len(a) - 1):
        if a[j] > a[j + 1]:
            a[j], a[j + 1] = a[j + 1], a[j]
        else:
            pass 
    print(a)