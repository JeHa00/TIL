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

그래서 위 코드를 아래와 같이 수정하는 걸 권장한다. 'newClothes' 같은 새 리스트를 생성하여 사용한다.

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

### 아이템 삭제



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