print('세 정수를 입력받아 최대값 구하기')
a = int(input('정수 a의 값을 입력하세요. : '))
b = int(input('정수 b의 값을 입력하세요. : '))
c = int(input('정수 c의 값을 입력하세요. : '))

maximum = a 
if b > maximum: 
    maximum = b
if c > maximum:
    maximum = c 

print('a,b,c 중 최대 정수 값은 { }입니다'.format('maximum'))