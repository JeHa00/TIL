# 선형 탐색
li = [1, 6, 4, 2, 3, 10, 8, 7, 5, 9]
n = int(input('1 ~ 10:'))
for i in range(len(li)):
    if li[i] == n:
        print(i)
        break



# 이진 탐색
# 이진 탐색은 정렬이 되어 있을 때, 효과적이다. 
li = [1, 3, 5, 6, 7, 8, 9, 13, 15, 17, 19]
n = int(input('1, 3, 5, 6, 7, 8, 9, 13, 15, 17, 19: '))


s_index = 0
e_index = len(li) - 1

while s_index <= e_index: 
    m_index = (s_index + e_index) // 2
    
    if li[m_index] < n:
        s_index = m_index + 1 
    
    elif li[m_index] > n:
        e_index = m_index - 1
    
    else:
        print(m_index)
        break 