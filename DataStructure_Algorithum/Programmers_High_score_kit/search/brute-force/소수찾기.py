"""
[소수 찾기]
정수가 문자열로 주어진다.
정수의 각 자리수 숫자를 조합하여 소수를 만들 수 있는 총 가지수를 알아보기
"""
from itertools import permutations

def main(numbers: str) -> int:
    numbers_separated = sorted(numbers)
    prime_numbers = set()
    for i in range(1, len(numbers_separated) + 1):
       prime_numbers = prime_numbers.union(set(map(int, map(''.join, permutations(numbers_separated, i)))))
    prime_numbers = prime_numbers.difference(range(2))
    for i in range(2, int(max(prime_numbers) ** 0.5) + 1):
        prime_numbers = prime_numbers.difference(set(range(i * 2, max(prime_numbers) + 1, i)))
    return len(prime_numbers)

if __name__ == "__main__":
    numbers_list = ["17", "011"]
    for numbers in numbers_list:
        print(f"answer: {main(numbers)}")