# 계단
n = int(input('n: '))

for i in range(n):
    print(f"{' ' * i}{'*' * n}")

print()

# 삼각형
n = int(input('n: '))

for i in range(1, n + 1):
    print(f"{'*' * i}{' ' * (n - i)}")

for i in range(1, n + 1):
    print(f"{' ' * (n - i)}{'*' * i}")

print()

# 역삼각형
for i in range(n, 0, -1):
    print(f"{'*' * i}{' ' * (n - i)}")

for i in range(n, 0, -1):
    print(f"{' ' * (n - i)}{'*' * i}")

print()

# 피라미드
for i in range(1, n + 1, 2):
    print(f"{' ' * ((n - i) // 2)}{'*' * i}{' ' * ((n - i) // 2)}")

print()

# 역피라미드
for i in range(n, 0, 2):
    print(f"{' ' * ((n - i) // 2)}{'*' * i}{' ' * ((n - i) // 2)}")
