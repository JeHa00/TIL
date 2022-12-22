# 띄어쓰기 없애기

text = input('text: ').split()
for i in text:
    print(i, end='')

print()

# 대소문자 바꾸어 출력하기
text = input('text: ')
for i in text:
    if i.isupper():
        print(i.lower(), end='')
    elif i.islower():
        print(i.upper(), end='')
    else:
        print(i, end='')

print()

# 이름 찾기

names = ['김철수', '김영희', '홍길동', '코딩', '파이썬']

for name in names:
    if '김' in name:
        print(name)
