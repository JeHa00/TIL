# 0. Introduction

> 1. [code smell: 1) 복사 붙여넣기한 코드](#code-smell-1-복사-붙여넣기한-코드)  
> 2. [code smell: 2) 의도가 드러나지 않은 값들](#code-smell-2-의도가-드러나지-않은-값들)  
> 3. [code smell: 3) 죽은 코드](#code-smell-3-죽은-코드)  
> 4. [code smell: 4) 숫자 접미사가 붙은 변수](#code-smell-4-숫자-접미사가-붙은-변수)  
> 5. [code smell: 5) 그냥 함수나 모듈이어야하는 클래스](#code-smell-5-그냥-함수나-모듈이어야하는-클래스)  
> 6. [code smell: 6) 중첩된 list comprehension](#code-smell-6-중첩된-list-comprehension)  
> 7. [code smell: 7) 빈 예외 처리 블록과 부실한 에러 메시지](#code-smell-7-빈-예외-처리-블록과-부실한-에러-메시지)  
> 8. [Debug](#debug)    
> 9. [code smell에 관한 잘못된 통념](#code-smell에-관한-잘못된-통념)


- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 05: Finding Code Smells'** 입니다.


<br>

---

# code smell: 1) 복사 붙여넣기한 코드 

중복되는 코드 부분이 많아지고 길어질수록, 중복 제거를 더 해야합니다.

저자는 프로그램에 3 ~ 4개의 사본이 있다면 코드 중복 제거를 고려하기 시작한다고 한다.

중복 코드는 한 부분을 수정하면 다른 복사 붙여넣기한 코드도 수정해야한다.

복사 붙여넣기한 모든 코드 부분을 다 수정하면 좋겠지만, 한 부분만 수정하고 나머지 수정하는 걸 잊을 수도 있고, 한 부분만 놓쳐서 error를 발생할 수 있다.

그래서 더욱 함수로 만들어야 한다.

아래는 중복 코드가 많은 잘못된 예시다.

```python
# Bad case
print('Good morning!')
print('How are you feeling?')
feeling = input()
print('I am happy to hear that you are feeling' + feeling)

print('Good afternoon!')
print('How are you feeling?')
feeling = input()
print('I am happy to hear that you are feeling' + feeling)

print('Good evening!')
print('How are you feeling?')
feeling = input()
print('I am happy to hear that you are feeling' + feeling)
```

## 해결책: 함수화

아래는 위 잘못된 케이스를 개선한 예시다.

```python
# Good case
def ask_feeling():
    print('How are you feeling?')
    feeling = input()
    print('I am happy to hear that you are feeling' + feeling)

print('Good morning!')
ask_feeling()
print('Good afternoon!')
ask_feeling()
print('Good evening!')
ask_feeling()
```

다시 위 코드를 개선해보자.

```python
for time_of_day in ['morning', 'afternoon', 'evening']
    print(f'Good {time_of_day} !')
    ask_feeling()
```


이처럼 코드를 더 적은 수로, 목적이 확실하게, 유지보수성이 좋게 바꿀 수 있다.


<br>

---

# code smell: 2) 의도가 드러나지 않은 값들

코드를 보면 뜬끔없이 숫자(magic number)나, 문자열이 나타나질 때가 있다.

의도, 목적이 명확하게 드러나지 않는 부분 또한 code smell이 나는 부분이다.


## 해결책: 숫자를 상수로 사용하기

숫자를 숫자로 그대로 사용하면 몇 달 후, 작성자가 다시 볼 때나 다른 사람이 볼 때 이 숫자의 의미를 알 수 없다.

```python
# Bad case
expiration = time.time() + 604800
```

그래서 이를 옆에 주석을 달아서 다음과 같이 작성할 수 있지만, 이보다는 상수 변수로 사용하는 게 더 좋은 방식이다.

```python
# Bad case
expiration = time.time() + 604800 # Expire in one week

# Good case
SECONDS_PER_MINUTE = 60
SECONDS_PER_HOUR = 60 * SECONDS_PER_MINUTE 
SECONDS_PER_DAY = 24 *  SECONDS_PER_HOUR
SECONDS_PER_WEEK = 7 * SECONDS_PER_DAY  

expiration = time.time() + SECONDS_PER_WEEK
```

이 방식으로 작성했을 때, 또 다른 장점을 예시를 들어 언급해보겠다.

이렇게 동일한 값이어도 다른 상수로 구분했을 경우, NUM_CARDS_IN_DECK 를 수정해도 NUM_WEEKS_IN_YEAR 에 영향을 주지 않는다.

```python
# Good case
NUM_CARDS_IN_DECK = 52
NUM_WEEKS_IN_YEAR = 52

print('This deck contains', NUM_CARDS_IN_DECK, 'cards.')
print('The 2-year contract lasts for', 2 * NUM_WEEKS_IN_YEAR, 'weeks.')
```

<br>

## 해결책: 문자열을 상수로 사용하기

문자열로 입력을 하면 이 문자열이 무슨 목적과 의도를 가지는지 모른다. 그렇다고 변수로 입력하면 undefind 가 뜬다. 

아래 코드를 예를 들어 설명해보자.


```python
NORTH = 'north'
SOUTH = 'south'
EAST = 'east'
WEST = 'west'

while True: 
    print('태양광 패널 방향을 정해라.')
    direction = input().lower()
    if direction in (NORTH, SOUTH, EAST, WEST):
        break 
print(f'태양광 패널의 방향은 {direction} 이다.')

# Bad case 1
if direction == 'nrth':
    print('북쪽을 바라보는 것은 효율적이지 않다.')

# Bad case 2
if direction == NRTH:
    print('북쪽을 바라보는 것은 효율적이지 않다.')
```

아래처럼 `direction == 'nrth'` 로 입력하면 `'nrth'` 가 무엇을 의미하는지 모르고, `NRTH`로 입력하면 undefined가 뜬다.



<br>

---
# code smell: 3) 죽은 코드  

## 죽은 코드란? 

코드를 일시적으로 주석 처리하는 방식이 테스트 과정에서 흔히 사용된다.

그런데, 주석 처리된 코드가 그대로 남아있으면, 왜 주석처리되었는지 모르는 상태가 된다. 

또한, 항상 false 조건인 if block이나, 절대 호출되지 않는 함수 등

**_작성한 의미가 없는 코드_** 를 모두 **죽은 코드** 라고 한다.


```python
import random
def coinFlip():
    if random.randint(0, 1):
        return 'Heads!'
    else:
        return 'Tails!'
    return 'The coin landed on its edge!'

print(coinFlip)
```

위 실행에서 맨 마지막 줄인 `return 'The coin landed on its edge!'`은 실행되지 않는 코드다. 이것이 바로 죽은 코드다.

<br>

## 죽은 코드의 예외: stub

**_stub_** 이란 **_함수가 아직 호출될 준비가 되지 않은 것을 보여주는 코드_** 다.

다음과 같이 함수가 실행될 때 error가 발생하지 않도록 아무 작업도 수행하지 않는 pass문이나, `raise NotImplemnetedError` 로 stub을 걸 수 있다.

```python
def example_function():
    pass

def example_function():
    raise NotImplementedError
```


<br>

---
# code smell: 4) 숫자 접미사가 붙은 변수

> **_숫자 접미사가 붙은 변수를 사용하지 말고, 그 변수가 3개 이상일 경우 collection을 사용하자_**

이 부분은 이전에 학습한 Chapter 04의 naming에도 해당된다. 

숫자 접미사가 붙은 변수란 `password1`, `password2`, `pet1Name`, `pet2Name`, `pet3Name` 처럼 접미사로 숫자가 붙은 것 뿐만 아니라 숫자로 구분된 변수를 의미한다. 

이렇게 작성하면 **_변수들 간의 차이점을 알 수 없다._** 

그래서 변수마다 고유한 이름을 작성하자.  

좌표계 개념을 변수에 도입하고 싶으면 x1, x2, y1, y2가 아니라 start_x, end_x, start_y, end_y 로 작성하자. 

<br>

## 숫자 접미사가 3개 이상인 경우

이 때는 **각 데이터를 독립적인 변수에 담는 것이 아닌 list, set, dictionary 같은 collection으로 저장** 하자.

```python
# Bad case
pet1Name = 'one'
pet2Name = 'two'
pet3Name = 'three'

# Good case
petNames = ['one', 'two', 'three']
petNames = {'one', 'two', 'three'}
```

<br>

## 예외: 숫자가 고유 이름의 일부일 경우  

enableIPv6 인 경우, IPv6는 고유 이름으로 상관없다.

<br>

---
# code smell: 5) 그냥 함수나 모듈이어야하는 클래스

> **_일회성 함수를 만들 때는 클래스 메서드를 만들지 말자._**

일회성 함수를 만들 때는 클래스를 사용하지 않는다. 

그리고, 함수를 그룹화할 때는 클래스가 아닌 모듈을 사용한다. 

클래스로 잘못만든 함수의 예시를 보자.

```python
import random
class Dice:
    def __init__(self, sides=6):
        self.sides = sides
    
    def roll(self):
        return random.randint(1, self.sides)

a = Dice()
print(a.roll())
```

이 클래스는 사실 아래 함수와 같다.

```python
print(random.randint(1, 6))
```

클래스 작성에 대한 객체지향 설계 원칙은 Chapter 15 ~ 17을 참고하자.


<br>

---
# code smell: 6) 중첩된 list comprehension

> **_중첩 list를 만들 때는 '가독성'을 위해서 list comprehension 하나에 for 루프문을 사용하여 만들자_**

## 여러 종류의 comprehension

파이썬에는 list comprehension 외에도 set comprehension, dictionary comprehension이 존재한다.

```python
# list comprehension 사용 x
span = []
for number in range(100):
    if number % 5 != 0:
        span.append(str(number))

# list comprehension 사용 o
span = [str(number) for number in range(100) if number % 5 != 0]

# set comprehension
span = {str(number) for number in range(100) if number % 5 != 0}

# dictionary comprehension
span = {str(number): number for number in range(100) if number % 5 !=0}
```

<br>

## list comprehension을 사용하여 중첩 리스트 만들기

또한 중첩 리스트를 list comprehension으로 생성할 수 있다.

```python
# nested list comprehension
nested_int_list = [[0, 1, 2, 3], [4], [5, 6], [7, 8, 9]]
nested_str_list = [[str(i) for i in sublist] for sublist in nested_int_list]
```

<br>

## 중첩 리스트 생성 시, 가독성 향상시키는 방법

하지만 이 코드는 가독성이 좋지 않기 때문에, 중첩 리스트를 생성할 때는 for 루프문을 하나 이상 사용해서 기존의 리스트 컴프리헨션을 확장하는 게 낫다. 

```python
nested_int_list = [[0, 1, 2, 3], [4], [5, 6], [7, 8, 9]]
nested_str_list = []
for sublist in nested_int_list:
    nested_str_list.append([str(i) for i in sublist])

# 결과
>>> nested_str_list
[['0', '1', '2', '3'], ['4'], ['5', '6'], ['7', '8', '9']]
```

<br>

---
# code smell: 7) 빈 예외 처리 블록과 부실한 에러 메시지

> **_해당 코드를 사용하는 사용자가 조치를 어떻게 취하라는 에러 메시지를 가진 예외 처리 블록을 만들자. 프로그램에서 발생할 수 있는 모든 예외를 처리하지 않으면 프로그램 개발은 완료된 것이 아니다._**


## 빈 예외처리 블록

예외 처리 블록(except)이 없으면 파이썬 프로그램은 즉시 멈추면서 충돌을 일으켜서 저장되지 않은 작업이 손실되거나, 파일이 반만 완료된 상태로 남아 있을 수 있다.

그래서 개발자들은 이 부분을 비어있지 않도록 만들기 위해서 다음과 같이 작성하는 경우가 있다.

```python
try:
    num = input('Enter a number: ')
    num = int(num)
except ValueError:
    pass 
```

<br>

이는 except 블록이 존재하지만 사실상 예외 처리를 하지 않는 것이다. 이렇게 프로그램이 넘어가면 에러를 처리하기보다는 감춰 버려서 더 심각한 버그가 만들어질 수 있다. 

<br>

## 부실한 에러 메세지

그래서 다음과 같이 수정해보자.

```python
try: 
    num = input('Enter a number: ')
    num = int(num)
except ValueError:
    print('An incorrect value was passed to int()')
```

구체적인 문제 상황을 알 수 있지만, 사용자가 어떻게 할 수 있는 게 아니다. 이보다는 사용자가 여기서 무엇을 어떻게 해야할지를 설명하는 게 더 성숙한 코드라고 생각한다. 

그래서 `print('You have to input a number')` 로 하는 게 더 낫다고 판단된다. 

<br>

---
# Debug

debug 시에 많은 사람들이 디버깅 정보를 출려갛기 위해 print() 호출을 사용한다. 

쉬운 방법이지만, 장기적으로 버그를 진단하기 위해서는 debugger와 log에 의존하는 게 더 빠르다.

<br>

---
# code smell에 관한 잘못된 통념  

code smell(코드 악취)인 줄 알았지만, 사실 아닌 것들에 대해 정리해본다.

## 함수 마지막에는 return 문이 하나만 있여야 한다?

하나의 입구, 하나의 출구라는 아이디어는 어셈블리어 언어와 포트란 언어로 프로그래밍하던 시절에 나온 조언을 잘못 해석한데에서 비롯된다.  

함수나 메소드마다 return 문을 하나씩만 유지하려면, 여러 개의 if-else 문으로 작성되어 분기처리하기 때문에 return 문이 둘 이상 있어도 괜찮다.

<br>

## 함수에는 try 문이 둘 이상 있으면 안된다?

함수와 메서드는 한 가지일만 해야하는 건 맞지만, 예외 처리도 별도의 함수에서 발생해야 한다는 의미는 아니다.

별도의 함수에서 발생해야한다는 사람들은 아래 코드를 다음과 같이 수정해야 한다고 말한다.

```python
# before
import os 
def deleteWithConfirmation(filename):
    try:
        if (input('Delete' + filename + ', are you sure ? Y/N') == 'Y'):
            os.unlink(filename)
    except FileNotFoundError:
        print('That file already did not exist.')

# after
import os 
def handleErrorForDeleteWithConfirmation(filename):
    try:
        _deleteWithConfirmation(filename)
    except FileNotFoundError:
        print('That file already did not exist.')

def _deleteWithConfirmation(filename):
    if input('Delete' + filename + ', are you sure ? Y/N') == 'Y'"
        os.unlink(filename)
```

위와 같이 함수로 '한 가지 일'에 집중하여 나눴지만, 오히려 복잡하다. 

이 '일'을 어떻게 정의해야할지 생각해보자.

<br>

## 플래그 인수는 나쁘다?

**플래그 인수** 란 함수 또는 메소드 호출의 boolean 인수를 말한다.  

이 플래그 인수는 다음과 같이 사용된다.

```python
def someFunction(flagArgument):
    if flagArgument:
        # 특정 코드 실행
    else:
        # 완전히 다른 특정 코드 실행  
```

이 값이 True냐 false이냐에 따라서 다르게 작동한다.

이 인수를 사용하는 예가 `sorted()` 다.  reverse 키워드 인자에 boolean 값을 전달해서 정렬 순서를 정할 수 있다. 

그런데 왜 플래그 인수를 code smell로 여길까?

이 값에 따라 실행되는 내용이 달라서 2개의 함수를 만들어야 한다는 통념 때문이다. 

오히려 2개의 함수로 나누면 더 복잡해진다. 


<br>

## 전역 변수는 나쁘다?

함수와 메서드가 전역 변수를 사용한다면 변수가 잊혀지는 격리 기능을 일부 잃어버린다.

이 격리 기능으로 올바르게 작동될 수도 있다.

하지만, 이 전역 변수를 많이 사용하면 인수가 많아져서 복잡도가 높아지듯이 복잡해지고, 버그 발생 가능성도 높아진다.

이 전역 변수를 사용하는 함수에서 에러가 발생됬을 경우 프로그램 전체에서 다 찾아봐야하기 때문에 전역 변수 사용을 권장하지 않는다. 

하지만 이 책에서 말하고 싶은 건 '모든' 전역 변수는 나쁜 아니라는 사실이다.

<br>

## 주석은 불필요하다?

이런 주장이 나온 이유는 나쁜 주석이 달린 코드는 주석이 전혀 없는 것보다 더 나쁘기 때문이다.

하지만, 주석을 간결하고 효과적으로 작성한다면 훨씬 도움이 된다. 

실제 프로그램에서는 주석이 너무 많거나 잘못된 주석이 문제라기보다는 주석이 아예 없거나 부족한 경우가 대부분이다.

주석을 효과적으로 작성하는 방법은 Chapter 10에서 학습한다.

<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 