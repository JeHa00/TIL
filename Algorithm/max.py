from typing import Any, Sequence

def max_of(a: Sequence) -> Any:
    maximum = a[0]
    for i in range(1,len(a)):
        if a[i] > maximum:
            maximum = a[1]
    return maximum

if __name__ == '__main__':
    print(__name__)
    print('배열의 최댓값을 구합니다.')
    num = int(input('원소수를 입력하세요. :'))
    a = []
    for i in range(num):
        k = int(input('{}번째의 원소값을 입력하세요 : '.format(i+1)))
        a.append(k)

    print('최댓값은 {}입니다.'.format(max_of(a)))