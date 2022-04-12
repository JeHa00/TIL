# Intro

> 1. [Object reference](#1-object-reference)
> 2. [Call by Object reference](#2-call-by-object-reference)

- python의 변수를 만드는 원리 그리고,
- 호출 방식에 대해 알아보자.

<br>

---

# 1. Object reference

> 파이썬의 변수들은 객체에 대한 참조들이다.  
> 파이썬의 변수들은 객체들을 가리키고, 객체들은 임의의 data type을 가질 수 있다.  
> 또한, 실제 데이터는 객체들 안에 포함되어 있다.

- 컴퓨터 프로그램에서 사용되는 한 물리적인 메모리 위치를 나타내는 상징적으로 나타내는 이름이 '변수'다.
- 이 변수에는 여러 가지 data type들이 담겨질 수 있다.
- 그래서 변수를 container로 생각하자.
- program이 실행되는 동안, 이 변수에 접근할 수 있고, 변경할 수도 있다.
- 하지만, 변수를 만드는 원리가 C,C++, JAVA 와 Python에는 차이가 있다.

- C,C++, JAVA 의 예
- 먼저 변수 이름을 아래와 같이 선언한다.

```yml
> int x
> int y
```

- 위 코드를 그림으로 표현하면 다음과 같다.

<p align="center"> <image src ="https://python-course.eu/images/python-tutorial/shoebox_variables_1_300w.webp" width = '500' height ='350'/></p>

<br>

- 그리고 나서 변수들에 값을 할당한다.
- 할당하는 건 `=`을 사용한다.

```yml
> x = 42
> y = 78
```

- 위 코드를 그림으로 표현하면 다음과 같다.

<p align="center"> <image src ="https://python-course.eu/images/python-tutorial/shoebox_variables_3_300w.webp" width = '500' height ='350'/></p>

<br>

- 변수를 할당할 때는 처음에 `int`로 정했기 때문에, integer type만 할당할 수 있다.

- 다음으로 Python에서의 variable을 알아보자.
- 파이썬에서는 선언하는 게 필요 없고, 바로 변수의 이름과 이 변수에 할당할 data type과 value만 정하면 된다.

```yml
> x = 42
```

- 아래 이미지는 파이썬에서 x = 42를 그림으로 구현할 것이다.

<p align="center"> <image src ="https://python-course.eu/images/python-tutorial/python_variable_1_400w.webp" width = '500' height ='350'/></p>

```yml
> y = x
```

- - 아래 이미지는 파이썬에서 y = x를 그림으로 구현할 것이다.

<p align="center"> <image src ="https://python-course.eu/images/python-tutorial/python_variable_2_500w.webp" width = '500' height ='350'/></p>

---

<br>

## 2. Call by object reference

- python은 공식문서에 따르면 **_'call by object reference'_** 방식으로 호출한다.
- **_call by value_** 와 **_call by reference_** 의 중간적인 방식인데, 인수의 data type에 따라 달라진다.

  - 값에 의한 호출(call by value): immutable object
  - 참조에 의한 호출(call by reference): mutable object

- 각 방식에 대해 code로 구현해보자.
- data type에 따라 id 값이 변하는 것과 변하지 않는 것에 대한 설명은 [[TIL] Python basic 29: Data Model](https://jeha00.github.io/post/python_basic/python_basic_29_datamodel/#32-list-comprehension%EC%9D%98-%EC%A3%BC%EC%9D%98%EC%82%AC%ED%95%AD)를 참조한다.

<br>

## 2.1 Call by value

- 실제 인수의 `값`을 매개변수에 복사하는 방식이다.
- `값에 의한 호출` 이라 한다.
- 변수에 할당된 값만을 복사해서 함수의 인자로 넘긴다.
- 파이썬은 immutable object일 때 이 방식을 사용한다.

```yml
> def foo(s):
>   print(id(s), s)
>   s += " is Best"
>   print(id(s),s)
>   return s

> msg = "Python"
> print('msg = ', msg, id(msg))
msg =  Python 2072884335536

> foo(msg)
2072884335536 Python
2072889254896 Python is Best

> print('msg = ', msg, id(msg))
msg =  Python 2072884335536
```

- msg에 할당된 값만을 복사해서 foo의 매개변수로 넘어간다.
- foo에서 처음 print가 실행되어 확인한 id 값은 동일하다.
- 하지만, value가 수정됨에 따라 참조 객체가 달라진다.

<br>

## 2.2 Call by reference

- 실제 인수의 `참조`를 매개변수에 복사하여, 매개변수가 실제 인수와 같아지는 방식이다.
- `참조에 의한 호출` 이라 한다.
- 함수의 인자로 넘어간 값이 함수 내부에서 변경되면, 실제로 값이 변경된다.
- 파이썬은 mutable object일 때 이 방식을 사용한다.
- mutable object가 넘어갈 때, object reference가 넘어가기 때문에 값을 바꿀 수 있다.

```yml
> def foo(s):
>     print(s, id(s))
>     s.append(10)
>     print(s, id(s))

> msg = [s for s in range(1,6)]
> print('msg = ', msg, id(msg))
msg =  [1, 2, 3, 4, 5] 2211845893440

> foo(msg)
[1, 2, 3, 4, 5] 1764496575680
[1, 2, 3, 4, 5, 10] 1764496575680

> print('msg = ', msg, id(msg))
msg =  [1, 2, 3, 4, 5, 10] 2211845893440
```

- `foo` function에서 수정했지만, 그것이 global scope에서 출력 시에도 영향을 준다.
- 즉, 참조값이 수정된 걸 의미한다.

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [[TIL] Python basic 29: Data Model](https://jeha00.github.io/post/python_basic/python_basic_29_datamodel/#32-list-comprehension%EC%9D%98-%EC%A3%BC%EC%9D%98%EC%82%AC%ED%95%AD)
- [Python tutorials Data type](https://python-course.eu/python-tutorial/data-types-and-variables.php)
- [call-by-value, call-by-reference](https://www.youtube.com/watch?v=IfVT3Cpdays&t=1144s)
