# 0. Introduction

> 1. [흔히 잘못 사용되는 구문](#1-흔히-잘못-사용되는-구문)  
> 1. [문자열 포맷팅](#2-문자열-포매팅)  
> 2. [리스트의 얕은 사본 만들기](#2-문자열-포매팅)  
> 3. [파이썬다운 딕셔너리 사용법](#3-리스트의-얕은-사본-만들기)  
> 4. [조건식: 파이썬의 보기 흉한 3항 연산자](#4-파이썬다운-딕셔너리-사용법)  
> 5. [변수값 작업](#6-변수값-작업)  

- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 06: Pythonic Code'** 입니다.


<br>

---
# 1. 흔히 잘못 사용되는 구문

## 1.1 range() 보다는 enumerate()를 사용하자.

아래의 i는 index를 의미한다.

```python
# Not pythonic code
animals = ['cat', 'dog', 'moose']
for i in range(len(animals)):
    print(i, animals[i])
```

위 코드를 파이썬스러운 코드로 바꿔보자.

```python
# Pythonic code
animals = ['cat', 'dog', 'moose']
for i, animal in enumerate(animals):
    print(i, animal)
```

보다 코드가 훨씬 깔끔해졌다. 

range(len())은 구식 규약이므로 enumerate를 사용하자. 

만약 animal만 원한다면 range(len())을 사용하기보다는 다음과 같이 작성하자.

```python
for animal in animals:
    print(animal)
```

훨씬 깔끔하고, 직관적이다.

<br>

## 1.2 open() 과 close()보다는 with문을 사용하자.

open()과 close()를 통해서 파일 객체에 접근하기보다는 `with` 문을 사용하자.

open() 함수는 파일을 읽거나 쓰는 메소드가 포함된 파일 객체를 반환한다.

그리고, close() 함수로 파일을 닫아 다른 프로그램에서 읽고 쓸 수 있게 한다.

그러면 파이썬답지 않은 코드를 보자. 

```python
fileObj = open('spam.txt', 'w')
fileObj.write('Hello, world!') 
fileObj.close()
```

이를 pythonic code로 작성해보자. 

```python
with open('spam.txt', 'w') as fileObj:
    fileObj.write('Hello, world!')
```

close()에 대한 명시적인 호출이 없어도, 실행 흐름이 블록을 벗어나면 with 선언문은 close 호출을 자동적으로 실행한다.

<br>

## 1.3 == 대신 is를 써서 None과 비교하자.

== (equality operator)는 두 객체의 value를 비교하는 반면, is (identity operator)는 두 객체의 id를 비교한다. 값이 동일해도 id값은 다를 수 있다. 

어떤 파이썬 프로그램에서도 None 객체는 하나밖에 없다. 그래서 변수가 None으로 설정되어 있다면 is None 비교는 항상 참이다. 그래서 None과 비교할 때는 == 가 아닌 `is`를 사용해야 한다.

None 값을 비교할 때 `==`를 사용하고 싶으면 다음과 같이 오버라이딩을 해야 한다.

```python
class Practice:
    def __eq__(self, other):
        if other is None:
            return True


spam = Practice()
print(spam == None) # True
```

하지만, 대체로 `== None` 보다는 `is None`을 사용한다는 걸 기억하자.

True와 False는 `is` 연산자가 아닌 `==` 를 사용하여 판단한다. id보다는 그 값 자체의 판단에서 사용되기 때문이다. 이 때 `if <변수> == True:` 또는 `if <변수> == False:` 보다는 `if <변수>:` 또는 `if not <변수>:` 처럼 연산자와 boolean 값을 완전히 생략하는게 파이썬에서 일반적인 사용 방식이다.

<br>

---
# 2. 문자열 포매팅

## 2.1 문자열에 백슬래시가 많은 경우에는 원시 문자열을 사용하자.

원시 무자열이란 'r' 접두사가 붙은 문자열 리터럴을 말한다.

```python
# Not pythonic code
print('The file is in C:\\Users\\Al\\Desktop\\Info\\Archive\\Spam')


# Pythonic code
print(r'The file is in C:\Users\Al\Desktop\Info\Archive\Spam')
```


## 2.2 f-string을 사용한 문자열 formatting

처음에는 `print('Hello,' + name)` 처럼 연산자와 문자열, 변수를 조합하여 출력하는 방식이었다가

`%s` 를 사용하는 방식에서 `.format()` 을 사용하는 방식으로 발전했다. 

하지만, 이는 가독성이 좋지 않다.

그래서 파이썬 3.6에서 f-string 방식을 도입했다. 

문자열 내부에 변수 이름과 표현식을 인라인으로 넣을 수 있기 때문에 코드의 가독성이 개선된다.

<br>

---
# 3. 리스트의 얕은 사본 만들기

얕은 사본을 만드는 방식에 다음과 같이 작성할 수 있다. 

```python
spam = ['cat', 'dog', 'rat', 'eel']
eggs = spam[:]

id(spam) == id(eggs) # False
```

슬라이스를 사용해서 새로운 객체를 생성할 수 있다. 

하지만 이는 파이썬스럽지 않다. 

파이썬스럽게 작성한다면 다음과 같다.

```python
import copy
spam = ['cat', 'dog', 'rat', 'eel']
eggs = copy.copy(spam)
```

같은 값의 다른 객체를 생성하고 싶으면 `copy` module을 사용해보자.


<br>

---
# 4. 파이썬다운 딕셔너리 사용법 

## 4.1 dictionary에서 get()과 setdefault()를 사용하자.

### get()
존재하지 않은 딕셔너리 키에 접근하고자하여 KeyError가 발생해서 다음과 같이 파이썬스럽지 않은 코드를 작성하기도 한다.

```python
# Not pythonic code
numberOfPets = {'dogs': 2}
if 'cats' in numberOfPets:
    print('I have', numberOfPets['cats'], 'cats')
else:
    print('I have 0 cats')
```

위 코드보다는 파이썬스럽게 작성한다면 다음과 같이 작성한다.

```python
# Pythonic code 
numberOfPets = {'dogs': 2}
print(f'I have {numberOfPets.get('cats', 0)}, cats')
```

<br>

### setdefault() 

만약 해당 키가 없어서 기본값을 설정한다면? 

```python
# Not Pythonic code
numberOfPets = {'dog': 2}
if 'cats' not in numberOfPets:
    numberOfPets['cats'] = 0

numberOfPets['cats'] += 10 
numberOfPets['cats']

# Pythonic code
numberOfPets = {'dogs': 2}
numberOfPets.setdefault('cats', 0)
numberOfPets['cats'] += 10
```

<br>

## 4.2 기본값을 위해 collections.defaultdict를 사용하자.

그리고 기본값을 위해 계속해서 setdefault()를 사용하기보다는 defaultdict를 사용하는 걸 추천한다.

다음 코드는 int를 넘겼을 때, int의 최소값이 기본값으로 자동적으로 들어가는 코드다.

```python
import collections

# int
scores = collections.defaultdict(int)
scores['Al']
scores['Zophie']
scores # defaultdict(int, {'Al': 1, 'Zophie': 0})
```

아래 코드는 list를 넘겼을 때, 키값만 입력하면 빈 리스트가 자동적으로 값으로 들어가는 코드다.

```python
# list
scores = collections.default(list)
scores # defaultdict(list, {'Al': [], 'Zophie': []})
```

<br>

## 4.3 switch 문 대신에 dictionary를 사용하자.

if-elif 조건문을 dictionary로 대체하자.

```python
# if-elif case
if season == 'Winter':
    holiday = 'New year\'s Day'
elif season == 'Spring':
    holiday = 'May Day':
elif season == 'Summer':
    holiday = 'Juneteenth': 
elif season == 'Fall':
    holiday = 'Halloween':
else:
    holiday = 'Personal day off'

# dictionary case
holiday = {'Winter' : 'New Year\'s Day',
    'Spring': 'Man day',
    'Summer': 'Juneteenth',
    'Fall': 'Halloween',
}.get(season, 'Personal day off')
```

위 season에 할당된 값에 따라서 다른 것을 반환한다. 만약 season에 할당된 값이 없다면 기본값을 반환한다.

if-elif case 보다 코드가 훨씬 짧아진 걸 알 수 있다. 하지만 가독성은 떨어진다.

<br>

---
# 5. 조건식: 파이썬의 보기 흉한 3항 연산자

밀도가 높은 한 줄이지만 읽을 때는 이해가 안될 정도로 답답함을 주는 코드가 3항 연산자다. 그런데 왜 이것을 사용하는 걸까?

많은 개발자들이 파이썬이 3항 연산자를 갖지를 원해서, 보기 흉할지라도 파이썬의 가치관과 벗어날지라도 사용하기를 선택된 사례다. 대다수의 개발자들은 3항 연산자가 익숙하기 때문이다.


```python
# 3항 연산자를 사용하지 않은 경우
condition = True
if condition:
    message = 'Access granted'
else:
    message = 'Access denied'

message # 'Access granted'

# 3항 연산자를 사용하는 경우
condition = False
valueIfTrue = 'Access granted'
valueIfFalse = 'Access denied'
message = valueIfTrue if condition else valueIfFalse
message # Access denied
```


<br>

---
# 6. 변수값 작업

## 6.1 체이닝 할당(Chaining Assignment)과 비교 연산자(Comparison Operators)



특정 변수가 원하는 범위 안에 있는지 확인하기 위해서 다음과 같이 작성할 때가 있다.

```python
# Unpythonic code
if 42 < spam and spam < 99:
```

하지만, 파이썬에서는 chaining Comparison Operators (체이닝 비교 연산자)를 사용하기 때문에 다음과 같이 작성하는 게 보다 파이썬다운 코드다.

```python
# Pythonic code
if 42 < spam < 99:
```

또한, 체이닝을 사용해서 동일한 값을 여러 변수들에게 한 번에 할당할 수도 있고, 같은지 유무를 확인할 수 있다. 


```python
# Pythonic code
spam = eggs = bacon = 'string'
print(spam, eggs, bacon)

spam == eggs == bacon == 'string'
```

하지만, 체이닝을 연달아 사용했을 때, 발생할 수 있는 버그도 존재한다. 이는 8장에서 알아본다.

<br>

## 6.2 변수가 여러 값 중 하나인지 여부를 확인하자.

위 경우와 반대로 변수가 여러 값 중 하나인지는 어떻게 확인할 수 있을까? 

```python
# Not pythonic code
spam == 'cat' or spam == 'dog' or spam == 'moose'

# Pythonic code
spam = 'cat'
spam in ('cat', 'dog', 'moose')
```

파이썬스럽지 않은 코드보다 파이썬답게 작성한 코드가 실행속도가 보다 더 빠르다는 걸 `timeit` module을 통해서 확인할 수 있다.


<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 