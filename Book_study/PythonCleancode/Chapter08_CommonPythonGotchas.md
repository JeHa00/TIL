# 0. Introduction

> 1. [루프문 진행 중에는 리스트에서 아이템을 추가/삭제하지 말자](#1-루프문-진행-중에는-리스트에서-아이템을-추가삭제하지-말자)  
> 2. [copy.copy()나 copy.deepcopy() 없이 가변 값을 복사하지 말자](#2-copycopy나-copydeepcopy-없이-가변-값을-복사하지-말자)  
> 3. [기본 인수에 가변 객체는 사용하지 말자](#3-기본-인수에-가변-객체는-사용하지-말자)  
> 4. [문자열을 문자열 연결로 생성하지 말자](#4-문자열을-문자열-연결로-생성하지-말자)  
> 5. [sort()가 알파벳 순으로 정렬하리라 기대하지 말자](#5-sort가-알파벳-순으로-정렬하리라-기대하지-말자)  
> 6. [부동소수가 완벽히 정확할 거라고 가정하지 말자](#6-부동소수가-완벽히-정확할-거라고-가정하지-말자)  
> 7. [부등 연산자 !=를 연달아 쓰지 말자](#7-부등-연산자-를-연달아-쓰지-말자)  
> 8. [단일 아이템 튜플에서는 쉼표를 잊지 말자](#8-단일-아이템-튜플에서는-쉼표를-잊지-말자)  


- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 08: Common Python Gotchas - 파이썬에서 빠지기 쉬운 함정들'** 입니다.

<br>

---

# 1. 루프문 진행 중에는 리스트에서 아이템을 추가/삭제하지 말자

> **_루프문 진행 중에는 리스트에서 아이템을 추가/삭제하지 말고, 새로운 리스트를 생성해서 하자._**

list에 'sock'이라는 문자열이 발견될 때마다 이와 일치하는 'sock'을 삽입하여 해당 'sock'의 갯수를 짝수로 만드는 코드다. 

### 아이템 추가

```python
clothes = ['skirt', 'red sock']  
for clothing in clothes: # 리스트를 반복 
    if 'sock' in clothing: # 'sock' 문자열 찾기
        clothes.append(clothing) # 일치하는 'sock' 짝을 추가
        print('Added a sock:', clothing) # 사용자에게 알림

Added a sock: red sock
Added a sock: red sock
Added a sock: red sock
...
Added a sock: red sock
...
```

clothing에 추가된 아이템이 다음 반복에서 참조된다. 아래 이미지와 같이 'red sock'이 추가된다. 컴퓨터의 메모리가 부족해지거나 사용자가 중단해야 멈춘다. 

![image](https://user-images.githubusercontent.com/78094972/210774947-265b78e4-300f-4e49-87f2-479222147d26.png)


이 코드에서 알려주는 교훈은 list를 반복하는 동안 이 리스트에 새 아이템을 추가하면 안된다는 점이다. 의도치 않게 계속 반복되기 때문이다. 

그래서 위 코드를 아래와 같이 변경하는 걸 권장한다. 'newClothes' 같은 새 리스트를 생성하여 사용한다.

```python
clothes = ['skirt', 'red sock', 'blue sock']  
newClothes = []
for clothing in clothes:
    if 'sock' in clothing: 
        print('Appending:', clothing)
        newClothes.append(clothing)
print(newClothes)
clothes.extend(newClothes)

Append: red sock
Append: blue sock
['red sock', 'blue sock']

>> clothes.extend(newClothes)
['skirt', 'red sock', 'blue sock', 'red sock', 'blue sock']  
```

<br>

### 아이템 삭제

for 루프문을 실행하는 과정에서 아이템을 '추가'하면 안되듯 '삭제'해서도 안된다. 삭제를 하여 index가 수정되면 이 과정에서 원소를 조사하지 않고 지나치기 때문이다. 

아래 코드를 보면 'mello'는 삭제되었지만 'yello'는 삭제되지 않았다. 위 설명대로 'mello'가 삭제되는 순간 index가 댕겨지면서 index `[2]`까지는 확인했기 때문에, 삭제된 후 `[3]`을 확인하게 된 것이다.

```python
greetings = ['hello', 'hello', 'mello', 'yello', 'hello']
for i, word in enumerate(greetings):
    if word != 'hello':
        del greetings[i]

print(greetings) # ['hello', 'hello', 'yello', 'hello']
```

![image](https://user-images.githubusercontent.com/78094972/210934440-b99e6c0d-abb4-4f97-896c-88eaba35cd9e.png)

그렇다면 어떻게 코드를 작성하는게 좋을까?

삭제할 아이템을 제외한 모든 아이템을 복사하는 새 리스트를 만든 다음, 원래 리스트를 교체하자. 

```python
greetings = ['hello', 'hello', 'mello', 'yello', 'hello']
newGreetings = []
for word in greetings:
    if word == 'hello':
        newGreetings.append(word)

greetings = newGreetings
print(greetings) # ['hello', 'hello', 'hello']
```

다음으로 이를 list comprehension으로 작성해보자. 훨씬 더 간결하고, 리스트를 변경할 때 발생하는 함정을 피할 수 있다.

```python
greetings = ['hello', 'hello', 'mello', 'yello', 'hello']
newGreetings = [word for word in greetings if word == 'hello']
```

<br>

### 참조, 메모리 사용, sys.getsizeof() method

원본 대신 새로운 리스트를 생성하면 메모리 낭비로 보일 수 있다. 하지만, 변수가 실제 값 대신에 '값에 대한 참조'를 포함하는 것처럼 리스트도 값에 대한 참조를 포함한다. 

`newGreetings.append(word)` 행은 word 변수에 있는 문자열을 복사하는 게 아니라, 문자열의 참조를 복사하기 때문에 훨씬 적은 메모리를 차지한다. `sys.getsizeof()`를 통해 확인해보자.


```python
>> import sys
>> sys.getsizeof('cat') 
52 # 52바이트
>> sys.getsizeof('a much longer string than just "cat"')
85 # 85바이트

>> sys.getsizeof(['cat']) 
64 # 64바이트
>> sys.getsizeof(['a much longer string than just "cat"'])
64 # 64바이트
```

list에 담겨진 문자열을 크기와 상관없이 64바이트를 차지한다. 왜냐하면 실제 문자열을 포함하는 게 아닌, 문자열을 참조할 뿐이다. 

**_🔆 참조는 참조된 데이터의 크기에 관계없이 크기가 동일하다._**

그러므로 원래 리스트를 반복하면서 해당 리스트를 수정하지 않고 새로운 리스트를 생성한다고 해서 메모리를 낭비한다고 생각해서는 안된다. 

리스트를 수정하는 코드가 언뜻 동작하듯이 보여도, 에러 발견과 수정에 오랜 시간이 걸리는 미묘한 버그의 원인이 될 수 있다. 차라리 컴퓨터 메모리를 낭비하는 편이 낫고, 프로그래머의 시간을 허비하는 것은 훨씬 고비용이다.

### 아이템 변경은 가능하다.
루프문에서 리스트를 실행할 때, 아이템을 추가하거나 제거하지 않아야 하지만 리스트의 내용은 수정해도 좋다.

```python
>> numbers = ['1', '2', '3', '4', '5']
>> for i, number in enumerate(numbers):
>>    numbers[i] = int(number)

>> numbers 
[1, 2, 3, 4, 5]
```

### 안전하게 아이템을 삭제, 추가하는 또 다른 방법: 거꾸로 반복하기

아이템을 삭제하거나 추가하기 위한 또 다른 방법은 **_리스트를 끝에서부터 앞으로 거꾸로 반복하는 것_** 이다.

예를 들어 다음 코드를 보자. 리스트에서 루프 반복문이 실행되는 동안 삭제되므로 IndexError가 발생된다

```python
someInts = [1, 7, 4, 5]

for i in range(len(someInts)):
    if someInts[i] % 2 == 0:
        del someInts[i] # IndexError: list index out of range
```

하지만, 이번에는 거꾸로 반복해보자.

```python
someInts = [1, 7, 4, 5]
for i in range(len(someInts)-1, -1, -1):
    if someInts[i] % 2 == 0:
        del someInts[i]

someInts # [1, 7, 5]
```

위 코드가 잘 동작하는 이유는 for 루프 내에서 반복될 아이템은 인덱스가 바뀌는 것이 하나도 없기 때문이다.

삭제뿐만 아니라, 추가도 잘 동작이 되지만, 살짝만 바꿔도 버그가 등장하므로 제대로 하기 까다롭다. 

그렇기 때문에 ❗️ **_원본 리스트 수정보다는 신규 리스트 생성이 훨씬 더 간단하다._**

🔆 그래서 파이썬의 핵심 개발자 레이먼드 헤팅어는 이렇게 말한다.

> Question: 루프문을 돌면서 리스트를 수정하는 가장 좋은 방법은?  
> Answer: 없다. 생각도 하지 마라.


<br>

---

# 2. copy.copy()나 copy.deepcopy() 없이 가변 값을 복사하지 말자

변수는 객체를 포함하는 상자라기보다는 **_객체를 참조하는 레이블 또는 이름 태그_** 로 생각하자. 

### 변수 할당 시

파이썬에서 할당문은 절대로 객체를 복사하는 게 아닌, 객체에 대한 참조를 복사할 뿐이다.

```python
spam = ['cat', 'dog', 'eel']
cheese = spam 
spam # ['cat', 'dog', 'eel']
cheese # ['cat', 'dog', 'eel']
spam[2] = 'MOOSE'
spam # ['cat', 'dog', 'MOOSE']
cheese # ['cat', 'dog', 'MOOSE']
id(cheese), id(spam)# 235696337288
```

분명히 spam만 수정했지만, cheese까지 수정된 걸 확인할 수 있다. 

이렇게 된 이유는 **_할당문은 객체를 복사하지 않고, 객체에 대한 참조만 복사하기 때문이다._**

리스트 객체를 중복으로 만들지 않는다.

### 인자로 전달 시

이는 할당뿐만 아니라, 함수 호출에 인자로 전달된 객체에도 동일한 원리가 전달된다.

```python
def printIdOfParam(theList):
    print(id(theList))

eggs = ['cat', 'dog', 'eel']
print(id(eggs)) # 2356893256136
printIdOfParam(eggs) # 2356893256136
```

❗️ 만약 파이썬이 참조가 아닌 전체 리스트를 복사했다고 생각해보자. 

eggs에는 단 3개가 아닌 10억 개의 아이템이 들어있는 상황에서 인자로 넘기면 이거에 대한 리스트를 전부 복사해야 한다.

따라서 간단한 함수를 호출하는데 메모리는 매우 많이 잡아먹는다. 그래서 파이썬 할당이 참조만 복사하고 객체는 절대 복사하지 않는 이유다.

### 해결책 

이런 함정에서 벗어나는 방법은 `copy.copy()` 메소드로 단순히 참조가 아닌 복사본을 만드는 것이다.

```python
import copy
bacon = [2, 4 ,8, 16]
ham = copy.copy(bacon)
id(bacon) == id(ham) # False
```

그러나 변수가 객체를 포함하는 상자가 아닌 객체에 대한 레이블이나 이름표와 같듯이, 중첩 리스트의 경우에는 리스트 안에 객체를 참조하는 레이블이나 이름표가 포함된다. 그래서 중첩 리스트의 경우에는 copy.copy()를 할지라도 내부 리스트에 대해서는 참조만 복사한다.

```python
import copy
bacon = [[1, 2], [3, 4]]
ham = copy.copy(bacon)
bacon.append('APPENDED')
bacon # [[1, 2], [3, 4], 'APPENDED']
ham # [[1, 2], [3, 4]]
bacon[0][0] = 'CHANGED'
bacon # [['CHANGED', 2], [3, 4], 'APPENDED']
ham # [['CHANGED', 2], [3, 4]]
id(bacon[0]) == id(ham[0]) # False
```

위 코드의 결과 copy.copy()를 사용했음에도 불구하고 두 변수에 모두 반영되었다.  

이런 경우, **_copy.deepcopy()_** 를 사용하면 리스트 객체 내의 모든 리스트 객체를 복사할 수 있다.


## copy.deepcopy를 권장

객체를 복사할 때 2가지 방법이 있음을 알았다. 이 중에서 copy.deepcopy()를 권장한다. 왜냐하면 미묘한 버그까지 예방할 수 있기 때문이다. copy.copy()에 비해서 약간 느리지만 눈치채기 어려운 정도라고 한다.


<br>

---


# 3. 기본 인수에 가변 객체는 사용하지 말자

> **_함수의 기본 인수는 함수가 호출될 때마다 생성되는 게 아니라, def문이 실행될 대 한 번만 생성되기 때문에 def 문에서 가변객체를 기본 인수로 사용해서는 안된다._**

파이썬에선 정의된 함수에서 파라미터에 대한 기본 인수(default argument)를 설정할 수 있다. 개발자가 파라미터를 명시적으로 사용하지 않으면 기본 인수를 사용해서 함수가 실행된다. 예를 들어서 `'cat dog'.split()`는 `'cat dog'.split(None)`를 호출하는 경우와 동일하다.

그러면 다음으로 기본 인수로 가변 객체를 사용한 경우를 보자.

```python
def addIngredient(ingredient, sandwich=['bread', 'bread']):
    sandwich.insert(1, ingredient)
    return sandwich

mySandwich = addIngredient('avocado')
mySandwich # ['bread', 'avocado', 'bread']
```

위와 같이 작성할 수 있다. 하지만, 기본 인자로 가변 객체를 사용할 경우 다음 코드와 같은 문제점이 발생할 수 있다. 위 코드에 이어서 작성 후 실행했다.

```python
anotherSandwich = addIngredient('lettuce')
anotherSandwich # ['bread', 'lettuce', 'avocado', 'bread']
```

anotherSandwich를 선언한 건 처음인데, 어째서 avocado가 있는 것일까?

addIngredient()가 호출될 때마다 이 기본 인자 리스트를 재사용하기 때문에 이처럼 예상치 못한 동작으로 이어진다.

함수 def문은 매번 함수를 호출할 때마다 실행되는 게 아니라 한 번만 실행되기 때문에, 오직 `['bread', 'bread']` 하나만 생성된다. 

```python
mySandwich = ['bread', 'cheese', 'bread']
mySandwich = addIngredient('butter', mySandwich)
mySandwich # ['bread', 'butter', 'cheese', 'bread']
```

### 해결책

그래서 리스트 또는 딕셔너리 같은 가변 객체를 기본 인수로 사용해야하는 경우, **파이썬다운 해법은 기본 인수를 None으로 설정하는 것이다.** 그리고, 이를 확인하고 함수가 호출될 때마다 새로운 리스트나 딕셔너리를 제공하는 코드를 작성한다. 

```python
def addIngredient(ingredient, sandwich=None):
    if sandwich is None:
        sandwich = ['bread', 'bread']
    sandwich.insert(1, ingredient)
    return sandwich

firstSandwich = addIngredient('cranberries')
firstSandwich # ['bread', 'cranberries', 'bread']

secondSandwich = addIngredient('lettuce')
secondSandwich # ['bread', 'lettuce', 'bread']

id(firstSandwich) == id(secondSandwich) # False
```

🔆 가변 데이터 타입에는 리스트, 딕셔너리, 집합, 클래스 문으로 만들어진 객체가 포함된다. 이러한 유형의 객체를 def 문에 기본 인수로 넣어서는 안된다.

<br>

---


# 4. 문자열을 문자열 연결로 생성하지 말자  

파이썬에서 문자열은 immutable이다. 문자열을 수정하는 것처럼 보이는 코드도 실제로는 새로운 문자열 객체를 생성한다.

새로운 문자열 객체를 생성하면 예전 문자열 객체를 버리는데 수 많은 문자열 연결로 문자열을 만들면 프로그램이 느려질 수 있다.

아래 코드의 경우 매우 심각한 메모리 낭비를 초래한다. 

```python
final_string = ''
for _ in range(100000):
    final_string += 'spam'

final_string # spam spam spam spam spam ..... --생략--
```

위 코드는 맨 마지막 문자열만 필요하고 나머지는 불필요하다.

그래서 위 코드를 보다 파이썬다운 방법으로 작성한다면 다음과 같다. 

```python
final_string = []
for _ in range(100000):
    final_string.append('spam ')

final_string = ''.join(finalString)
final_string
```

이전 코드와 똑같이 십만 개의 문자열 객체를 생성하지만, join을 호출할 때 한 번만 문자열 연결을 수행한다.

시간을 측정한 결과 첫 번째 경우보다 세 번째 경우가 3배 빠른 걸 확인했다.

파이썬은 여러 가지 동작 세부 사항에 대한 고민거리로부터 개발자를 해방시켜준다.  이를 통해 프로그래머는 소프트웨어를 빨리 작성할 수 있다.  

프로그래머의 시간은 CPU의 시간보다 더 가치 있다.  

하지만, 연결을 통한 문자열 생성과 같은 실수를 피하기 위해 불변의 문자열과 가변 리스트의 차이 등 세세한 부분가지 이해하는 편이 좋은 경우도 있다.

<br>

---


# 5. sort()가 알파벳 순으로 정렬하리라 기대하지 말자

아스키라 읽히는 'ASCII'는 정보 교환을 위한 미국 표준 코드를 의미하는데, 숫자코드와 텍스트 문자 사이의 대표적인 인코딩 방식이다.  

sort()는 알파벳 정렬이 아닌 아스키 정렬을 사용한다.  그래서 대문자가 소문자보다 훨씬 앞에 온다. 대문자 A는 코드 포인트 65, 소문자 a는 코드 포인트 97, 대문자 Z는 코드 포인트 90이다.  

문자의 코드 포인트를 알고 싶으면 `ord()`를 사용한다. 또한, 코드 포인트의 문자열을 알고 싶으면 `chr()`를 사용한다. 

```python
ord('a') # 97
chr(97) # 'a'
```

알파벳 정렬을 하고 싶다면 다음과 같이 sort method에 key 값을 사용해보자. 리스트는 값이 lower() 문자열 메소드에서 호출된 것처럼 정렬된다.

```python
letters = ['z', 'A', 'a', 'Z']
letters.sort(key=str.lower)
letters # ['A', 'a', 'z', 'Z']
```

파이썬의 sort()는 병합정렬과 삽입 정렬을 혼합한 형태의 알고리즘으로서 팀 피터스에 의해서 만들어졌다.


<br>

---

# 6. 부동소수가 완벽히 정확할 거라고 가정하지 말자

## 문제점

컴퓨터는 0과 1만 저장할 수 있다. 이는 소수점이 존재하는 실수도 마찬가지다. 그래서 실제 실수 계산할 때 다음과 같이 사람의 생각과 다른 결과가 나온다.

```python
0.1 + 0.1 + 0.1 # 0.30000000000000004
0.3 == (0.1 + 0.1 + 0.1) # False
```

이러한 결과가 나온 게 컴퓨터가 부동소수를 다루는 방식에 의해 야기되는 **반올림 에러(rounding error)** 의 결과다.  이는 파이썬만의 문제가 아니라, CPU의 부동소수점 회로에 직접 구현된 하드웨어 표준 때문이다. 


## 해결방식

이 부동소수점 계산은 은행이나 원자로 같은 실시간성이 강하고, 사람 생명이나 금전적인 피해를 일으킬 가능성이 있는 업무의 경우에는 매우 중요하다. 
위 같은 업무처럼 정확성이 요구된다면 어떻게 이를 해결할 수 있을까? 

파이썬의 내장 모듈이 `decimal`을 사용하자.  [docs python - decimal](https://docs.python.org/3/libary/decimal.html) 에 잘 정리되어 있다.   
속도는 느리지만 Decimal 객체는 부동소수 값을 정확하게 대체한다.  

`decimal.Decimal('0.1')`을 사용하면 정확한 숫자 0.1을 나타내는 객체가 생성된다.

하지만 0.1을 문자열이 아니라 정수로 입력하면 다음과 같다.

```python
import decimal
decimal.Decimal(0.1) # Decimal('0.1000000000000000055511151231257827021181583404541015625')
decimal.Decimal('0.1') # Decimal('0.1')
```

하지만, 위 코드에서 본 것처럼 정수로 입력하면 Decimal 객체의 정밀도는 무한하지 않다. 

예측 가능하고 안정적인 수준의 정밀도를 지원할 뿐이다. 

만약 소수점 자리수를 원하는 자리수로 제한하고 싶다면?

```python
import decimal 
decimal.getcontext().prec # 28 
decimal.Decimal(1) / 3 # Decimal('0.3333333333333333333333333333')

decimal.getcontext().prec = 2
decimal.Decimal(1) / 3 # Decimal('0.33')
```


`decimal.getcontext().prec`에 원하는 자리수를 할당하여 출력한다. 

<br>

---

# 7. 부등 연산자 !=를 연달아 쓰지 말자

`(18 < age) and (age < 35)` 를 연달아 작성하면 `18 < age < 35` 다. `six = 6; halfDozen = 6`을 연달아 작성하면 `six = halfDozen = 6` 이다. 
이처럼 파이썬은 연달아 작성할 수 있다. 

하지만, 이러지 말아야하는 게 있는데, 바로 '부등 연산자 !=' 다. 아래 코드를 보자.

```python
a = 'cat'
b = 'dog'
c = 'moose'
a != b != c # True
```

위 코드는 연달아 비교된 걸로 생각이 들 것이다. 하지만 사실은 `(a != b)`와 `(b != c)`만 적용된 것이다. 

그래서 다음과 같이 작성해도 동일한 결과가 나온다.

```python
a = 'cat'
b = 'dog'
c = 'cat'
a != b != c # True
```

그래서 오해의 소지가 있기 때문에 부등 연산자를 연달아 사용하지 말자.


<br>

---

# 8. 단일 아이템 튜플에서는 쉼표를 잊지 말자

단일 아이템 튜플에서는 쉼표를 반드시 입력해야 튜플로 인식된다.

```python
type((1)) # int
type((1,)) # tuple

type(('cat')) # str
type(('cat',)) # tuple
```


<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 