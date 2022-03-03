# Python basic 3: 숫자형 데이터 사용법

# Intro

> 1. [파이썬의 모든 자료형](https://github.com/JeHa00/TIL/edit/master/Python/basic/basic_3_numeric%20types.md#1-%ED%8C%8C%EC%9D%B4%EC%8D%AC%EC%9D%98-%EB%AA%A8%EB%93%A0-%EC%9E%90%EB%A3%8C%ED%98%95)
> 2. [숫자형 타입 선언](https://github.com/JeHa00/TIL/edit/master/Python/basic/basic_3_numeric%20types.md#2-%EB%8D%B0%EC%9D%B4%ED%84%B0-%ED%83%80%EC%9E%85-%EC%84%A0%EC%96%B8)
> 3. [연산자 활용](https://github.com/JeHa00/TIL/edit/master/Python/basic/basic_3_numeric%20types.md#3-%EC%97%B0%EC%82%B0%EC%9E%90-%ED%99%9C%EC%9A%A9)
> 4. [형 변환](https://github.com/JeHa00/TIL/edit/master/Python/basic/basic_3_numeric%20types.md#4-%ED%98%95-%EB%B3%80%ED%99%98)
> 5. [외부 모듈 사용](https://github.com/JeHa00/TIL/edit/master/Python/basic/basic_3_numeric%20types.md#5-%EC%99%B8%EB%B6%80-%EB%AA%A8%EB%93%88-%EC%82%AC%EC%9A%A9)

## 1. 파이썬의 모든 자료형

파이썬이 지원하는 자료형은 다음과 같다.

```yml
int: 정수
float: 실수
complex: 복소수
bool: 불린 (True or False)
str: 문자열(시퀀스)
list: 리스트(시퀀스)
tuple: 튜플(시퀀스)
set: 집합
dict: 사전
```

그러면 각 자료형의 구체적인 예를 알아보자.

```yml
# str : string 의 약어로, 문자열 자료형을 의미한다.
> str1 = "Jeha"
> str2 = "Anacondacong"

# bool: boolean 의 약어로, True or False 자료형을 의미한다.
> bool1 = True

# float: 실수형 자료형을 의미한다. 소수점이 존재한다.
# 소수점 아래가 0이어도, 소수점이 존재하므로 실수형 데이터다.
> float1 = 10.0

# int : integer의 약어로, 정수형 데이터를 말한다.
> int1 = 7

# list : 리스트형으로 대괄호 안에 열거된 데이터 타입을 말합니다.
> list1 = [str1, str2]

# tuple: 튜플이라 하며, 소괄호 또는 괄호 없이 , 마로만 열거된 형태를 말합니다.

# dict : dictionary의 약어로, 중괄호 안에 key : value 로 구성된 데이터 형태입니다.
> dict1 = {
    "name : "Machine Learning",
    "version" : 2.0
}

# set : 집합형 데이터 타입으로, dict 와 마찬가지로 중괄호 형태의 데이터입니다.
> set1 = {7, 8, 9}

```

각 데이터 타입 출력은 다음과 같이 한다.

```yml
> print(type(str1))
<class 'str'>


> print(type(bool1))
<class 'bool'>

> print(type(str2))
<class 'str'>

> print(type(float1))
<class 'float'>

> print(type(int1))
<class 'int'>

> print(type(dict1))
<class 'dict'>

> print(type(tuple1))
<class 'tuple'>

> print(type(set1))
<class 'set'>




```

**결론: 데이터 타입의 종류에는 숫자형(정수형, 실수형, 복소수형), 문자형, 리스트, 튜플, 딕셔너리가 있다.**

---

<br>

## 2. 숫자형 데이터 선언

```yml
# 정수 선언
> i = 77
> i2 = -14
> big_int = 888888888888888

#정수 출력
> print(i)
77

> print(i2)
-14

> print(big_int)
888888888888888


# 실수 선언
> f = 0.9999
> f2 = 3.141592358
> f3 = - 4.2

# 실수 출력
> print(f)
0.9999

> print(f2)
3.141592358

> print(f3)
-4.2


```

**결론: 변수명 = 할당할 value값**

---

<br>

## 3. 연산자 활용

숫자형 연산자 종류에는 다음과 같다.

```yml
+ : 덧셈
- : 뺼셈
* : 곱셈
/ = 나누기를 의마하며, 몫과 나머지로 구성된다.
// : 나누기의 몫 부분을 출력한다.
% : 나누기의 나머지 부분을 출력한다.
x ** y : 제곱으로, x의 y제곱 을 의미한다.

```

그러면 연산 실습을 해보겠다.

```yml

# 변수 선언
> i1 = 39
> i2 = 939
> big_int1 = 123456789123456789012345678901234567890
> big_int2 = 999999999999999999999999999999999999999
> f1 = 1.234
> f2 = 3.939

# +
> print("i1 + i2 : ", i1 + i2)
i1 + i2 : 978

> print("f1 + f2 : ", f1 + f2)
f1 + f2 : 5.173

> print("big_int1 + big_int2 : ", big_int1 + big_int2)
big_int1 + big_int2 : 1123456789123456789012345678901234567889
# 큰 값들도 연산이 가능하다.

> a = 3 + 1.0
> print(a, type(a))
4.1 <class 'float' >
# 정수형과 실수형을 같이 연산할 때, 정수형을 자동적으로 실수형으로 변환하여 계산한다.


# -
> print("i1 - i2: ", i1 - i2)
i1 - i2:  -900

> print("f1 - f2: ", f1 - f2)
f1 - f2:  -2.705

> print("big_int1 - big_int2: ", big_int1 - big_int2)
big_int1 - big_int2:  -876543210876543210987654321098765432109

# *
> print("i1 * i2: ", i1 * i2)
i1 * i2:  36621

> print("f1 * f2: ", f1 * f2)
f1 * f2:  4.860726

> print("big_int1 * big_int2: ", big_int1 * big_int2)
big_int1 * big_int2:  123456789123456789012345678901234567889876543210876543210987654321098765432110


# /
> print("i2 / i1: ", i2 / i1)
i2 / i1:  24.076923076923077

> print("f2 / f1: ", f2 / f1)
f2 / f1:  3.1920583468395463

> print("big_int2 / big_int1: ", big_int2 / big_int1)
big_int2 / big_int1:  8.10000006561


# //
> print("i2 // i1: ", i2 // i1)
i2 // i1:  24
# i2 / i1 의 연산값의 몫 부분임을 알 수 있다.

> print("f2 // f1: ", f2 // f1)
f2 // f1:  3.0
# f2 / f1 의 연산값의 몫 부분임을 알 수 있다.

> print("big_int2 // big_int1: ", big_int2 // big_int1)
big_int2 // big_int1:  8
# big_int2 / big_int1 의 연산값의 몫 부분임을 알 수 있다.



# %
> print("i2 % i1 :", i2 % i1)
i2 % i1 : 3
# i1 * (i2 // i1) 으로 i2를 나누고 나온 나머지값


> print("f2 % f1 :", f2 % f1)
f2 % f1 : 0.2370000000000001

> print("big_int1 % big_int2 :", big_int1 % big_int2)
big_int1 % big_int2 : 123456789123456789012345678901234567890


# ** 와 pow(x,y)
> print("2 ** 3: ", 2 ** 3)
> print("2 ** 3: ", pow(2,3))

2 ** 3:  8
2 ** 3:  8

> print("i1 ** i2: ", i1 ** i2)
> print("i1 ** i2: ", pow(i1,i2))
i1 ** i2:  102250631262663558380.....
i1 ** i2:  102250631262663558380.....
# 너무 길어서 생략

> print("f1 ** f2: ", f1 ** f2)
> print("f1 ** f2: ", pow(f1,f2))
f1 ** f2:  2.289235194260789
f1 ** f2:  2.289235194260789
```

<br>

## 4. 형 변환

형 변환 함수는 다음과 같다.

```yml
abs(x): absolute의 약어로, 절대값으로 변환한다.
int(x): 정수형으로 만듭니다. 실수를 입력했다면 실수의 정수 부분을 출력한다.
float(x): 실수형으로 만듭니다. 정수를 입력했다면 소수점 .0 으로 나온다.
complex(x): 복소수로 허수까지 포함해서 a+bi 형태로 변환한다.
pow(x, y): x의 y승 제곱으로 출력된다.
```

```yml
a = 3.
b = 9
c = .65
d = 13.55

# type 출력
> print(type(a), type(b), type(c), type(d))
<class 'float'> <class 'int'> <class 'float'> <class 'float'>

# 형 변환

> print(float(b))  #정수 -> 실수
9.0

> print(int(c)) # 실수 -> 정수
0

> print(ind(D)) # 실수 -> 정수
13

> print(int(True)) # Bool -> 정수
1

> print(float(True)) # Bool -> 실수
1.0

> print(int(False))
0

> print(float(False))
0.0

> print(complex(3)) # 정수 -> 복소수
3 +0j

> print(complex('3')) # 문자형 -> 복소수
3 + 0j
# string data type을 숫자형으로 바꾸고 나서 복소수를 처리해야 하는데, 바로 처리된다.

> print(complex(False)) # Bool -> 복소수
0j



# 수치 연산 함수
> print(abs(-7))
7

# divmod(x,y) : x를 y로 나눴을 때, 몫과 나머지를 반환한다. 많이 사용되는 함수로 중요하다.
> x, y = divmod(100, 8) #몫 과 나머지
> print(x, y)
12 4

> print(pow(5, 3))
> print(5**3)
125
125

```

## **결론: 다른 data type으로 형 변환이 가능하다.**

<br>

## 5. 외부 모듈 사용

> import 를 사용한다.

```yml
# 외부 모듈을 불러오는 함수
> import math

# math 모듈에서 ceil 이란 함수를 사용하겠다.
# ceil(x) : x 이상의 수중에서 가장 작은 정수를 반환한다.
> print(math.ceil(5.2))
6

> print(math.pi)
3.1415926535


```

**결론: import를 사용하여 외부 module을 가져온다.**

## Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
