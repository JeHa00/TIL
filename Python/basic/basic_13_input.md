# Python basic 13: 파이썬 사용자 입력 (input 사용법)

<br>

## Intro

> 1. [사용자 입력]()
> 2. [형 변환 입력]()

<br>

- `input` 함수는 `기본 타입`은 `string` 이므로, `string` 외의 원하는 형태가 있다면 반드시 `type conversion(형 변환)`을 해야 한다.

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

---

<br>
