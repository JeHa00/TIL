# Python basic 2: print 사용법

> **formatting을 하면 특정 케이스에 원하는 형식대로 출력할 수 있고, 가독성이 높아진다.**

# Intro

> 1. 기본 출력
> 2. String 문자열 출력
> 3. Integer 정수형 출력
> 4. Float 실수형 출력

---

<br>

## 1. 기본 출력

### 1.1 Escape 코드

```yml
\n: 개행
\t: 탭
\\: 문자
\': 문자
\": 문자
\000: 널 문자
```

### 1.2 기본 출력

> '' 또는 "" 를 자주 사용한다.

```yml
> print('JeHa start!')
> print("JeHa start!")
> print()
> print('''JeHa start!''')
> print('')
> print("""JeHa start!""")


# 아무것도 출력되지 않는다.
> pirnt('')
> pirnt()
```

결과는 다음과 같다.

```yml
JeHa start!
JeHa start!

JeHa start!

JeHa start!
```

### 1.3 Separator 옵션

> 여러 data를 열거하여 출력할 때, 분리할 string 을 입력하여 각 data 사이 사이에 입력할 수 있다.

> print('python', 'start!', sep='@')

```yml
> python @ start!
``

`

### 1.4 End 옵션

> print에는 자동적으로 행간을 나누는 기능이 있다. 이를 end를 통해서 합칠 수 있다.

### 1.5 file 옵션

<br>

# Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
```
