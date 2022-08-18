
# 약수의 개수 구하기
# n을 입력받아 n의 약수의 개수 구하기


def countDivisor():

    n = int(input('약수를 구할 n:'))
    k = 1
    result = 0
    while True:
        if n % k == 0:
            if k ** 2 <= n:
                result += 2
                k += 1
                continue
            else:
                print(f"약수의 갯수: {result}")
                break
        else:
            k += 1
            continue


# OX 개수 구하기
# text를 입력받아 o의 개수, x의 개수 구하기

def countOX():

    text = input('text: ')
    o_count = 0
    x_count = 0
    for i in text:
        if i == 'o':
            o_count += 1
        elif i == 'x':
            x_count += 1
        else:
            continue

    print(f'O의 갯수: {o_count} / X의 갯수: {x_count}')


# 평균 이상 개수 구하기
# 여러 개의 숫자를 입력받아 평균을 구하고, 평균 이상의 숫자 개수 구하기

def countNumsAbove():

    numbers = list(map(int, input('numbers: ').split()))
    avg = sum(numbers) / len(numbers)
    results = 0
    for i in numbers:
        if i > avg:
            results += 1
    print(f'평균 이상의 개수는 {results}개 입니다.')


if __name__ == "__main__":
    countDivisor()
    countOX()
    countNumsAbove()
