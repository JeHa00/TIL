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

다음으로 이를 list comprehension으로 작성해보자. 훨씬 더ㅓ 간결하고, 리스트를 변경할 때 발생하는 함정을 피할 수 있다.

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

<br>

---

# 2. copy.copy()나 copy.deepcopy() 없이 가변 값을 복사하지 말자

<br>

---


# 3. 기본 인수에 가변 객체는 사용하지 말자

<br>

---


# 4. 문자열을 문자열 연결로 생성하지 말자  

<br>

---


# 5. sort()가 알파벳 순으로 정렬하리라 기대하지 말자

<br>

---

# 6. 부동소수가 완벽히 정확할 거라고 가정하지 말자

<br>

---

# 7. 부등 연산자 !=를 연달아 쓰지 말자

<br>

---

# 8. 단일 아이템 튜플에서는 쉼표를 잊지 말자



<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 