# Python basic 11: python 흐름 제어문 (While 반복문)

<br>

# Introduction

- for, if, while 문을 `흐름 제어문` 이라 한다.
- **for 문**은 원하는 `<collection>`의 갯수만큼 반복한다면, **while 문** 은 **if 문** 처럼 조건을 만족할 때까지 계속 반복한다.
- **While문** 은 **if문** 처럼 조건이 들어간다. 그런데, 무한 반복될 수도 있기 때문에, **_조건의 변화_** 가 필요하다. 아니면 `break` 를 사용한다.
  - 조건을 만족하면 **while문** 을 빠져나온다.
  - python 공식 사이트에서 `while` 의 구조는 다음과 같다.
  - `[expr]`은 expression을 의미한다.

```yml
> while [expr]:
> [statement(s)]
```

<br>

---

# 1. While 기본 사용법

- whlie 문은 **무한 반복문** 이 되지 않도록, **_조건의 변화 를 일으키는 코드_** 를 넣어야 한다.
- 그래서 `while`문의 경우, 눈으로 중간 결과를 디버깅해서 **무한 반복문** 인지 확인한다.

```yml
# 예제 1
> m = 3
> while m > 0:
>   print(m)
# 여기까지 쓰면 계속해서 m > 0 이므로 `무한 반복문`이 된다.
>   m -= 1
3
2
1

# 예제 2
> z = ['foo', 'bar', 'baz']
> whlie z:
# z 변수 안에 데이터가 존재하므로 True 상태이기 때문에 무한 반복문이다.
# 위험한 코드이므로, 조건의 변화를 일으켜 무한 반복문을 방지한다.
>   print(a.pop())
# pop으로 성분의 갯수가 0이 되면 False가 되므로 중단된다.

```

<br>

---

# 2. Break, continue

- **_조건의 변화_** 를 일으키면서, 원하는 조건에서 중단하거나, 조건 판단문으로 되돌아가기 위해서 `break`와 `continue`를 사용한다.
- `break` 문과 `continue`문은 **while문** 과 자주 사용된다.
- 중간에 `if` 조건문이 껴져 있는 방식이 많다.

```yml
# 예제 3
> m = 5
> while m > 0:
>     m -= 1
>    if m == 2:
>         break
>     print(m)
> print('Loop Ended')
4
3
Loop Ended

# 예제 4
> m = 5
> while m > 0:
>     m -= 1
>    if m == 2:
# 위에서 break를 continue로 바꿨다.
>         continue
>     print(m)
> print('Loop Ended')
4
3
1
0
Loop Ended

# 예제 5
> i = 1
> while i <= 10:
>     print('i : ', i)
>     if i ==6:
>         break
>     i += 1
i : 1
i : 2
i : 3
i : 4
i : 5
i : 6
```


❗ pass에 대해 설명하자면 다른 언어의 경우 빈 줄로 넘겨도 Error가 나지 않지만, 파이썬은 Error가 나기 때문에 나중에 작성하고자 빈란으로 남기면 안된다. 그럴 때 파이썬은 pass를 사용한다.

<br>

---

# 3. While ~ else 구문

```yml
# 예제 6
> n = 5
> while n > 0:
>    print(n)
>     n -= 1
>     if n == 3:
>       break
> else:
>    print('else out.')
5
4

# 예제 7
> g = ['red', 'blue', 'white', 'black']
> c = 'black'
> i = 0

> while i < len(g):
>     if g[i] == c:
>         print('find {}'.format(c))
>         break
>     i += 1
> else:
>     print('not found in lise.')
find black

```

<br>

---

# 4. 무한 반복 구문

- 무한정 반복되는 구문을 말한다.
- 이 구문이 실행되면 다운되므로, `break`와 `continue`로 방지한다.
- 무한 반복문의 한 예가 다음과 같다.

```yml
> while True:
>   print('Foo')
```

- 무한 반복문을 방지하기 위해 다음과 같이 수정한다.

```yml
> a = ['foo', 'bar', 'baz']
> while True:
>   if not a:
>       break
>   print(a.pop())
baz
bar
foo
```

- a의 원소가 존재하지 않으므로 `if not a`가 참이 되어 `break`가 실행된다.

<br>

---

# Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
