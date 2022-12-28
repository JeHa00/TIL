"""
유클리드 호제법: 두 정수(a, b)의 최대 공약수를 구하는 방법  
- a를 b로 나누어 떨어지면 b는 두 수의 최대 공약수다. (a > b)
- 나누어 떨어지지 않으면 b와 나머지 r에 대해서 동일한 과정을 수행하여 나머지가 0이 될 때까지 수행
"""

# while 문 사용
def gcd_while(a, b):
    if a < b:
        a, b = b, a
    
    while True:
        remainder = a % b 
        if remainder == 0: 
            return  b
        a, b = b, remainder

print(gcd_while(216, 60))

# 재귀 사용
def gcd_recursive(a, b):
    print(a, b)
    
    if a % b == 0: 
        return b
    return gcd_recursive(b, a % b)
    
