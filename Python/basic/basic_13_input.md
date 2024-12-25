# Python basic 13: 파이썬 사용자 입력 (input 사용법)

<br>

## 0. Introduction

> 1. [사용자 입력](#1-사용자-입력)
> 2. [형 변환 입력](#2-형-변환-입력)

<br>

## 1. 사용자 입력

```yml
> name = input('Enter Your Name : ')
> grade = input('Enter Your grade : ')
> school = input('Enter Your school : ')
> print(name, grade, school)
> print(type(name), type(grade), type(school))
Jeha A+ here
<class 'str'> <class 'str'> <class 'str'>
```

---

<br>

## 2. 형 변환 입력

- `input` 함수는 `기본 타입`은 `algorithum.string` 이다.
- `algorithum.string` 외의 원하는 형태가 있다면 반드시 `형 변환`을 해야 한다.

<br>

```yml
> first_number = int(input("Enter number1 : "))
Enter number1 : 20

> second_number = int(input("Enter number2 : "))
Enter number2 : 15


> total = first_number + second_number
> print("fist_number + second_number : ", total)
fist_number + second_number :  35


> float_number = float(input("Enter a float number : "))
Enter a float number : 15

> print("input float : ", float_number)
input float : 15

> print("input type : ", type(float_number))
<class 'float'>
```

<br>

---

## Reference

- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
