# Python basic 15: 파이썬 모듈(module)

<br>

# 0. Introduction

> 1. [Module이란?](#1-module이란)
> 2. [If \_\_name\_\_ \== "\_\_main\_\_\"](#2-if-name--"main")

<br>

---

# 1. Module이란??

> **_'Module'이란 하나의 파일 안에 함수, 변수, 클래스 등 파이썬 구성 요소 등을 모아놓아 공용적으로 쓸 수 있도록 만든 파일_**

- 공용적으로 쓸 수 있는 파일이기 때문에, '타인'으로부터 '웹'으로부터 가져다 사용한다.
- module 을 사용하기 위해서, 사용하기 전에 **_모듈파일이 있는 경로를 추가_** 해야한다.
  - 일시적 등록: `sys.path` 사용하기
  - 영구적 등록: `환경 변수`에 있는 python path에 추가하기
- 이 `Module` 파일이 모여지면 `Package`가 된다.

<br>

## 1.2 Import 사용하기

- 외부 module을 사용하기 위해서는 `import` 를 사용한다.

  ```yml
  > import math
  > print(math.pi)
  3.1415926535

  # random은 0부터  1 사이의 난수를 출력한다.
  > import random
  > random.random())
  0.71924824
  ```

<br>

## 1.3 경로 추가하기

- import 된 module 파일의 module 타입을 알 수 있다.

  ```yml
  > import sys
  > print(sys)
  <module 'sys' (built-in)>
  ```

  - built-in은 내장 파일을 말한다.
  - sys는 파이썬의 내장 모듈 파일임을 알 수 있다.

- python 파일 설치 경로 확인하기

  - 아래 이 경로들에 있는 파일을 파이썬이 가져다가 사용하는 것이 파이썬의 원리다.
  - Python 파일이 설치된 모듈 파일 경로들이 출력된다.
  - 파이썬 내부에 있기 때문에 import로 가져다가 사용할 수 있다.

  ```yml
  > print(sys.path)
  ['c:\\Users\\rudtl\\Desktop\\Dev\\Python 강의\\Inflearn Original
  \\Level 1 입문_프로그래밍 시작하기', ....]
  ```

- Module 경로 추가하기

  - 추가하기 위해 모듈 경로의 데이터 타입을 확인한다.
  - 경로를 추가하기 전에 밑에 경로로 동일한 명칭의 폴더를 만들어놔야 한다.

  ```yml
  > print(type(sys.path))
  <class 'list'>

  # 끝에 경로가 추가된 걸 확인할 수 있다.
  > sys.path.append('C:/math')
  > print(sys.path)
  ['c:\\Users\\...\\Inflearn Original\\Level 1 입문_프로그래밍 시작하기',..., 'C:/math']
  ```

- module 파일을 만들고, 추가한 경로 폴더에 넣기

  - 'module_test` 란 이름으로 파이썬 파일을 만든다.
  - module 파일의 내용은 다음과 같다.

  ```yml
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
  ```

- module file 실행해보기

  ```yml
  > import module_test

  # import가 되자마다 바로 명령어들이 실행된다.
  ---------------
  called! inner!
  10
  10
  25
  5.0
  125
  ---------------

  # import된 module 내의 함수를 사용해보기
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
  ```

<br>

---

# 2. If \_\_name\_\_ \== "\_\_main\_\_\"

- **Problem**

  - 위와 같은 방식은 module을 외부에서 가져오는 사람들에게 편의성이 좋지 않다.

- **Solution**

> `if __name__ == "__main__":` 을 추가하기

- 그래서 호출 시, 즉시 출력을 방지하기 위해서 print 문을 삭제하거나 주석처리를 하는 방법을 사용할 수도 있다.
- python에서는 이에 대해 다른 방법을 만들었는데, `예약어`를 사용하여 아래 2가지로 나눠서 실행할 수 있다.

  - 다른 곳에서 외부적으로 import를 할 경우
  - 자기 자신을 스스로 실행할 경우

- `if __name__ == "__main__":` 명령어를 추가하여 해당 모듈이 **_import 된 경우가 아니라, 인터프리터에서 직접 실행된 경우에만_** if 문 이하의 코드를 돌리라를 명령이다.

- module 파일에 아래 코드를 추가한다.

  ```yml
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

- `main`: 실행되는 주체가 이 코드가 작성된 파일인 경우를 의미
  - **_import를 하면 바로 실행되지 않는다._** 왜냐하면 실행되는 주체가 다른 파일이기 때문이다. 하지만, module **_파일에서 인터프리터로 직접 실행하면_**, 실행주체이기 때문에 출력된다.
- import 한 파일에서는 `명령어`를 입력하면 실행된다.

```yml
> import module_test
> print(module_test.power(10,3))
1000
```

- 그래서 바로 실행되지 않도록 `if __name__ == "__main__"`놔둔다.

- 더 자세한 내용은 [[TIL] Python basic 23: if \_ \_name \_ \_ == ' \_ \_main \_ \_'](https://jeha00.github.io/post/python_basic/python_basic_23_ifnamemain/) 을 참고한다.

<br>

---

# Reference

- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
