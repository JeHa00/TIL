def factorial(x):
    """양의 정수 n의 팩토리얼 값을 재귀적으로 구함"""
    if x > 0:
        return x * factorial(x-1)
    else:
        return 1 

print(factorial(5))


def gcd(x: int, y: int) -> int: 
    """유클리드 호제법를 재귀적으로 구현"""
    if y == 0:
        return x 
    else:
        return gcd(y, x % y)


print(gcd(22, 8))