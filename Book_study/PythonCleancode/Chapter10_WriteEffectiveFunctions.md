# 0. Introduction

> 1. [함수명](#1-함수명)  
> 2. [함수 크기의 트레이드 오프](#2-함수-크기의-트레이드-오프)  
> 3. [함수 파라미터와 인수](#3-함수-파라미터와-인수)  
> 4. [함수형 프로그래밍](#4-함수형-프로그래밍)  
> 5. [결과값은 항상 동일한 데이터 타입이어야 한다](#5-결과값은-항상-동일한-데이터-타입이어야-한다)  
> 6. [예외 발생시키기 vs 에러 코드 반환하기](#6-예외-발생시키기-vs-에러-코드-반환하기)  


- 아래 book study는 알 스웨이가트가 지었고, 박재호님이 번역하신 [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB) 를 읽고 진행한 book study 입니다. 영문 원본으로 온라인 공개된 자료가 있어서 영문으로 학습합니다.

- 기존에 읽었던 Clean Code는 자바 코드로 되어 있어서, 먼저 파이썬 클린 코드를 학습 후 시작할려고 합니다.

- 이번 book study를 진행하면서 code에 대한 철학이 생기고, code를 바라보는 눈이 깊어지고, 넓어지기를 바랍니다.

- 각 chapter를 읽고 내용 정리하는 식으로 진행합니다.

- 이번에 학습하는 chapter의 주제는 **'Chapter 11: Common Python Gotchas - Writing effective functions'** 입니다.


<br>

---

# 1. 함수명  

### 함수명: 동사 + 대상 명사
함수명은 식별자에 사용되는 규칙을 따라야 한다. 그런데, 함수는 통상적으로 동작을 수행하기 때문에 함수명에 동사가 들어가며, 행위 대상을 설명하는 명사가 들어가기도 한다. 
- ex) refreshConnection(), setPassword(), extract_version()


클래스나 메서드의 이름은 명사가 필요하지 않을 수 있다. 왜냐하면 이해를 위한 문맥이 제공되기 때문이다. Webbrowser 라는 class 안에 open() 이라는 메서드를 만들면 굳이 openWebbrowser를 하지 않아도, 웹 브라우저를 여는 함수인 걸로 이해할 수 있기 때문이다. 


### 약어나 너무 짧은 이름보다, 길고 충분히 설명이 되는 이름  

수학자라면 gcd가 뭔지 알 수 있다. 하지만 그 외의 사람들은 gcd를 보고 Greatest Common Denominator, 최대 공약수 를 생각하지 못 한다. 그래서 약어나 짧은 이름보다는 충분히 설명이 되는 이름으로 작성한다.

### 예약어를 피한다.
이미 파이썬 내장 함수로 사용되는 명칭들은 사용하지 않는다.  

<br>

---
# 2. 함수 크기의 트레이드 오프  

긴 코드로 큰 함수 하나를 만들건지, 아니면 짧은 코드로 여러 개의 함수를 만들 건지 고려해야 한다. 각각의 장단점에 대해 알아보자. 

- 짧은 함수의 장점
    - 함수 코드를 이해하기가 더 쉽다. 
    - 필요한 파라미터가 더 적다.   
    - 테스트와 디버그가 더 쉽다.  
    - 예외가 더 적게 발생한다.  

- 짧은 함수의 단점
    - 프로그램에서 필요한 함수 개수가 늘어나기 쉽다.  
    - 함수가 더 많다는 건 프로그램이 더 복잡해지는 걸 의미한다.  
    - 함수 간의 관계가 더욱 복잡해진다.  
    - 함수 개수가 늘어나면서 정확한 이름을 작성하기 어렵다.  
    - 함수를 많이 사용할수록 작성할 문서도 많아진다.  

그러면 실제로 긴 함수로 작성했을 때, 짧은 함수들로 작성했을 때를 비교해보자.  

### 한 개의 큰 함수

```python
def getPlayerMove(towers):
    """플레이어에게 이동 명령을 요청한다. (fromtower, toTower)를 반환한다."""

    while True: # 플레이어가 유효한 이동 명령을 입력할 때까지 계속 요청한다. 
        print('탑의 "시작"과 "끝"의 글자 또는 QUIT를 입력하세요.')
        print("(예: 탑 A에서 탑 B로 원판을 이동하려면 AB를 입력합니다.)")
        print()
        response = input("> ").upper().strip()

        if response == "QUIT":
            print("즐겁게 퍼즐을 풀어줘서 감사합니다.")
            sys.exit() 
        
        if response not in ("AB", "AC", "BA", "BC", "CA", "CB"):
            print("AB, AC, BA, BC, CA, CB 중 하나를 입력하십시오.")
            continue 
    
        fromTower, toTower = response[0], response[1]

        if len(towers[fromTower]) == 0:
            print("원판이 없는 탑을 선택했습니다.") # from 탑은 비어 있을 수 없다. 
            continue 
        elif len(towers[toTower]) == 0: 
            return fromTower, toTower
        elif towers[toTower][-1] < towers[fromTower][-1]:
            print("더 작은 원판에 더 큰 원판을 올릴 수 없습니다.")
            continue
    else:
        return fromTower, toTower  
```

### 짧은 여러 개의 함수  

```python 
def getPlayerMove(towers):
    """플레이어에게 이동 명령을 요청한다. (fromtower, toTower)를 반환한다."""

    while True: # 플레이어가 올바른 이동 명령을 입력할 때까지 계속 물어본다. 
        response = askForPlayerMove() 
        terminateIfResponseIsQuit(response)
        if not isvalidTowerLetters(response):
            continue 

        fromTower, toTower = response[0], response[1]

        if towerWithNoDiskSelected(towers, fromTower):
            continue 
        elif len(towers[toTower]) == 0: 
            return fromTower, toTower
        elif largerDiskIsOnSmallerDisk(towers, fromTower, toTower):
            continue 
        else:
            return fromTower, toTower 

def askForPlayerMove():
    """플레이어에게 질문 내용을 출력하고, 선택한 타워를 반환한다."""
    print('탑의 "시작"과 "끝"의 글자 또는 QUIT를 입력하십시오.')
    print('(예: 탑 A에서 탑 B로 원판을 이동하려면 AB를 입력합니다)')
    print()
    return input('> ').upper().strip() 

def terminateIfResponseIsQuit(response):
    """응답이 'QUIT'인 경우, 프로그램을 종료한다."""
    if response == 'QUIT':
        print('즐겁게 퍼즐을 풀어주셔서 감사합니다.')
        sys.exit()

def isValidTowerLetters(towerLetters):
    """towerLetters가 유효하면 true를 반환한다."""
    if towerLetters not in ("AB", "AC", "BA", "BC", "CA", "CB"):
        print("AB, AC, BA, BC, CA, CB 중 하나를 입력하십시오.")
        return False
    return True

def towerWithNoDiskSelected(towers, selectedTower):
    """selectedTower에 원판이 없으면 True를 반환한다."""
    if len(towers[selectedTower]) == 0:
        print("원판이 없는 탑을 선택했습니다.")
        return True
    return False

def largerDiskIsOnSmallerDisk(towers, fromTower, toTower):
    """더 큰 원판이 더 작은 원판으로 이동할 경우, True를 반환한다"""
    if towers[toTower][-1] < towers[fromTower][-1]
        print('더 작은 원판에 더 큰 원판을 올릴 수 없습니다.')
        return True 
    return False 
```

### 비교

위 두 가지를 비교해보자. 

원래 getPlayerMove() 함수보다 이해하기 쉽지만, 이 함수들의 집합은 복잡도를 증가시킨다. 그래서 이 코드를 읽는 사람들은 함수들이 어떻게 서로 맞아 떨어지는지 이해하는 데 어려움을 가진다.  또한, 각각의 새로운 함수에 대해 새로운 이름과 독스트링을 제안해야 한다.  

즉, 이 경우는 함수 자체는 단순해졌을 수도 있지만, 프로그램의 전반적인 복잡도가 대폭 상승했다. 

이 저자에 의하면 **_이상적인 함수는 30행 이하여야 하며, 결코 200행을 넘어서면 안된다_** 고 한다. 

함수는 최대한 짧게 만들되, 여기서 제시한 기준 이하로 줄이지는 말자.  


<br>

---
# 3. 함수 파라미터와 인수  


### parameter와 argument 의 차이   

> **_함수를 선언할 때 입력하는 변수 이름이 '매개변수', 함수를 호출할 때 입력하는 변수 이름이 '인수'_**  

parameter(매개변수)는 def 문의 괄호 사이에 있는 변수 이름인 반면 argument(인수)는 함수를 호출할 때 괄호 사이에 들어가는 값이다.

함수의 매개변수가 많아질수록 함수의 코드는 더 자유로운 구성과 일반화가 가능하지만, 복잡도가 커진다는 의미이기도 하다.  

**_매개변수도 함수 당 코드 행의 수처럼 0~3개 정도의 파라미터는 괜찮지만, 5~6개 이상은 아무래도 너무 많다는 규칙을 세워두는 편이 낫다._** 함수가 지나치게 복잡해진다면, 파라미터가 적고 길이도 짧은 함수로 분할하는 방법을 고려하는 게 가장 낫다.  


## 기본 인수

함수 파라미터의 복잡도를 줄이는 한 가지 방법은 파라미터에 대한 기본 인수를 제공하는 것이다.  

'기본 인수(Default argument)'는 함수를 호출할 때 변수를 지정하지 않는 경우, 인수로 사용되는 값이다. 기본 인수를 사용하면 반복적으로 인수를 입력할 필요 없이 해당 값을 기본 인수로 만들 수 있다.  

이 때 주의할 점으로 기본 인수가 있는 파라미터는 항상 기본 인수가 없는 파라미터 뒤에 위치해야 한다.  

```python
def introduction(name, greeting='Hello'):
    print(greeting + ', ' + name)

introduction('Alice') # Hello, Alice
```

이 기본 인수를 입력할 때 또 다른 유의할 점은 8장에서 알아봤다시피, 빈 리스트 또는 빈 딕셔너리 같은 Mutable object를 사용하는 걸 피해야한다.  


## * 와 **를 사용해 함수에 인수 전달하기

인수 그룹을 따로 따로 함수로 넘기기 위해 * 와 ** 구문을 사용할 수 있다.  

- * 구문을 사용하면 리스트나 튜플 같은 반복가능(iterable) 객체의 아이템을 전달할 수 있다.  

- ** 구문을 사용하면 딕셔너리 같은 매핑 객체의 키-값 쌍을 개별 인수들로 전달할 수 있다.  

예시 코드들을 보자.

```python
print('cat', 'dog', 'moose') # cat dog moose
args = ['cat', 'dog', 'moose'] 
print(args) # ['cat', 'dog', 'moose']

# 코드 가독성이 떨어지는 예제
args = ['cat', 'dog', 'moose'] 
print(args[0], args[1], args[2]) # cat dog moose

# 더 좋은 방법
print(*args) # cat dog moose
```

* 구문을 사용하면 리스트에 아이템이 여러 개 있더라도 이 아이템들을 함수에 개별적으로 전달할 수 있다.  


다음으로 ** 구문을 사용한 코드를 보자.  

** 구문을 사용하면 매핑 데이터 타입을 개별 키워드 인수로 전달할 수 있다.  

```python
kwargsForPrint = {'sep' : '-'}
print('cat', 'dog', 'moose', **kwargsForPrint) # cat-dog-moose
```

위와 같이 사용할 수 있다. 이 방식은 키워드 인수를 받아들이는 함수와 메소드에 특히 유용하다.  

## *를 사용해 가변인수 함수 만들기

## **를 사용해 가변인수 함수 만들기 

## *와 **로 래퍼 함수 만들기


<br>

---
# 4. 함수형 프로그래밍   

<br>

---
# 5. 결과값은 항상 동일한 데이터 타입이어야 한다  

<br>

---
# 6. 예외 발생시키기 vs 에러 코드 반환하기  

<br>

---




<br>

---

# Reference

- [클린 코드, 이제는 파이썬이다.](https://book.interpark.com/product/BookDisplay.do?_method=detail&sc.prdNo=355096830&gclid=Cj0KCQjw166aBhDEARIsAMEyZh4ltxiM-nlGaj3yjPIW82A6l-hPlXjmjBCqtmw6xzqRX8dc8Rk6PFMaAjm9EALw_wcB)