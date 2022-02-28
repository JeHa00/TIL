# Python basic 15: 파이썬 모듈(module)

<br>

## Module이란?? 그리고 Module 관련 함수

- `Module`이란 하나의 파일 안에 함수, 변수, 클래스 등 파이썬 구성 요소 등을 모아놓아 공용적으로 쓸 수 있도록 만든 파일
- 타인으로부터 웹으로부터 가져다 사용한다.
- 이 `Module` 파일이 모여지면 `Package`가 된다.
- 외부 module을 사용하기 위해서는 `import` 를 사용한다.
- module 을 사용하기 위해서, 사용하기 전에 모듈파일이 있는 경로를 추가해야한다.
- `sys.path`는 영구적으로 경로를 등록하는 것이 아닌 `일시적으로` 등록하는 방법이다.
- 영구적으로 등록하고 싶으면 `환경 변수`에 있는 python path에 추가하기

```yml
> import math
> print(math.pi)
3.1415926535

# random은 0부터  1 사이의 난수를 출력한다.
> import random
> random.random())
0.71924824

## import 된 module 파일의 module 타입을 알 수 있다.
> import sys
> print(sys)
<module 'sys' (built-in)>
# built-in은 내장 파일을 말한다.
# sys는 파이썬의 내장 모듈 파일임을 알 수 있다.


## Python 파일이 설치된 경로들이 출력된다.
## 이 경로들에 있는 파일을 파이썬이 가져다가 사용하는 것이 파이썬의 원리다.
# 파이썬 모듈 파일들의 경로들이다.
# 파이썬 내부에 있기 때문에 import로 가져다가 사용할 수 있다.
> print(sys.path)
['c:\\Users\\rudtl\\Desktop\\Dev\\Python 강의\\Inflearn Original\\Level 1 입문_프로그래밍 시작하기',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\python39.zip',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\DLLs',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\lib',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\lib\\site-packages']


## 모듈 경로 추가하기
# 추가하기 위해 모듈 경로의 데이터 타입을 확인한다.
> print(type(sys.path))
<class 'list'>

# 경로를 추가하기 전에 밑에 경로로 동일한 명칭의 폴더를 만들어놔야 한다.
# 끝에 경로가 추가된 걸 확인할 수 있다.
> sys.path.append('C:/math')
> print(sys.path)
['c:\\Users\\rudtl\\Desktop\\Dev\\Python 강의\\Inflearn Original\\Level 1 입문_프로그래밍 시작하기',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\python39.zip',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\DLLs',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\lib',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39',
'C:\\Users\\rudtl\\AppData\\Local\\Programs\\Python\\Python39\\lib\\site-packages',
'C:/math']

# 폴더를 만들었으면 module 파일을 이 폴더에 넣어놔야 한다.
# 'module_test` 란 이름으로 파이썬 파일을 만든다.
# module 파일의 내용은 다음과 같다.

>>>>>
> def add(x, y):
>    return x + y

> def subtract(x, y):
>     return x - y

> def multiply(x, y):
>    return x * y

> def divide(x , y):
>    return x / y

> def power(x, y):
>    return x ** y


> print('-' * 15)
> print('called! inner!')
> print(add(5,5))
> print(subtract(15,5))
> print(multiply(5,5))
> print(divide(10,2))
> print(power(5,3))
> print('-' * 15)
>>>>>


## import를 실행할 파일로 돌아온 후, import 를 실행한다.
> import module_test

## import가 되자마다 바로 명령어들이 실행된다.
---------------
called! inner!
10
10
25
5.0
125
---------------

## import된 module 내의 함수를 사용해보기
> import module_test
> print(test_module.power(10, 3))
---------------
called! inner!
10
10
25
5.0
125
---------------
1000
```

- 위와 같은 방식은 module을 외부에서 가져오는 사람들에게 편의성이 좋지 않다.

<br>

- `called! innter!` 출력을 방지하기 위해서 print 문을 삭제하거나 주석처리를 하는 방법을 사용할 수도 있다.
- 하지만, python에서는 이에 대해 다른 방법을 만들어놨다. `예약어`를 사용하여 아래 2가지로 나눠서 실행할 수 있다.

  - 다른 곳에서 외부적으로 import를 할 경우
  - 자기 자신을 스스로 실행할 경우

- `if __name__ == "__main__":` 을 추가한다.

  - 이 명령어는 해당 모듈이 `import 된 경우가 아니라`, `인터프리터에서 직접 실행된 경우에만` if 문 이하의 코드를 돌리라를 명령이다.

- module 파일에 아래 코드를 추가한다.

```yml
## __name__ 사용
> if __name__ == "__main__":
>     print('-' * 15)
>     print('called! __main__')
>     print(add(5,5))
>     print(subtract(15,5))
>     print(multiply(5,5))
>     print(divide(10,2))
>     print(power(5,3))
>     print('-' * 15)
```

- `main`은 실행되는 대상을 의미한다.
- import를 하면 바로 실행되지 않는다. 왜냐면 실행되는 대상이 아니기 때문이다.
- 하지만, module 파일에서 인터프리터로 직접 실행하면, 실행대상이기 때문에 출력된다.
- import 한 파일에서는 `명령어`를 입력하면 실행된다.

```yml
> import module_test
> print(module_test.power(10,3))
1000
```

- 그래서 바로 실행되지 않도록 `if __name__ == "__main__"`놔둔다.

---
