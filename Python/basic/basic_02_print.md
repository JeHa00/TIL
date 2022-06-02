# Python basic 2: print 사용법

**formatting을 하면 특정 케이스에 원하는 형식대로 출력할 수 있고, 가독성이 높아진다.**

# 0. Introduction

> 1. [기본 출력](#1-기본-출력)
> 2. [String 문자열 출력](#2-String-문자열-출력)
> 3. [Integer 정수형 출력](#3-Integer-정수형-출력)
> 4. [Float 실수형 출력](#4-Float-실수형-출력)

<br>

# 1. 기본 출력

## 1.1 Escape 코드

```yml
\n: 개행
\t: 탭
\\: 문자
\': 문자
\": 문자
\000: 널 문자
```

<br>

## 1.2 기본 출력

- '' 또는 "" 를 자주 사용한다.

```yml
> print('JeHa start!')
> print("JeHa start!")
> print()
> print('''JeHa start!''')
> print('')
> print("""JeHa start!""")


# 아무것도 출력되지 않는다.
> print('')
> print()
```

- 결과는 다음과 같다.

```yml
JeHa start!
JeHa start!

JeHa start!

JeHa start!
```

<br>

## 1.3 Separator 옵션

- 여러 data를 열거하여 출력할 때, 각 data 사이 사이를 분리할 string을 입력할 수 있다.

```yml
> print('python', 'start!', sep='@')
python @ start!

> print('p','y','t','h','o','n')
p y t h o n

> print('p','y','t','h','o','n', sep = '')
python

> print('010', '6677', '6677', sep = '-' )
010-6677-6677

> print('python', 'google.com', sep = '@')
python@google.com
```

<br>

## 1.4 End 옵션

- **_print에는 자동적으로 행간을 나누는 기능이 있다._**
- 이를 end를 통해서 합칠 수 있다.

```yml
> print('I')
> print('love')
> print('u')

I
Love
u

> print('I', end = ' ')
> print('love', end = ' ')
> print('u')

I love u
```

<br>

## 2. String 문자열 출력 (s)

> **_formatting을 하면 특정 케이스에 원하는 형식대로 출력할 수 있고, 가독성이 높아진다._**

- d는 정수, s는 string, f는 실수를 의미한다.
- `' % ' % ()` 로 formatting 하는 방법과 `' {} '.format()` 을 사용해서 formatting하는 방법이 있다.
  - 다 익숙해져야 하지만, 후자를 더 빈번히 사용한다.

```yml
# 순서에 맞게 자동적으로 mapping 해준다.
> print('%s %s' % ('one','two))
> print('{} {}'.format('one','two'))

# 순서를 지정해서도 할 수 있다.
# index[1] 은 two, index[0]은 one 이므로, 교차해서 mapping 된다.
> print('{1} {0}'.format('one','two'))

one two
one two
two one
```

- 전체 자릿수를 지정하는 방법과 정렬 방향을 바꾸는 방법을 알아보겠다.

```yml
# 문자열 총 자리 수는 10자리를 의미한다.
# 오른쪽 정렬
# 즉, blank 시작은 왼쪽부터다.
> print('%10s' % (likelike))
# 왼쪽 blank는 2칸이다.
  likelike

# 방향을 반대로 하기 위해서는 (-)를 붙힌다: 왼쪽정렬
> print('%-10s' % (likelike))
likelike
```

- 위 내용을 `.format`으로 표현해보자.
- **`.format`은 string을 입력할 때 's'를 입력하지 않아도 된다.**

```yml

# 오른쪽 정렬
> print('{:>10}'.format('likelike'))
 likelike

# 왼쪽 정렬
> print('{:<10}'.format('likelike'))
likelike

> print('{:10}'.format('likelike'))
likelike


# 가운데 정렬
> print('{:^10}'.format('likelike'))
 likelike

# blank에는 언더바가 있도록 하는 것
> print('{:_>10}'.format('like'))
______like

```

- 그러면 지정한 자릿수보다 문자열이 더 길다면??

```yml
> print('%5s' % ('likelike'))
likelike
# 다 출력된다.
```

- 지정한 자릿수를 넘는 문자열 부분들을 절삭하고 싶다면??

```yml
# . 점을 아래와 같이 표시한다.
> print('%.5s' % 'likelike')
likel

# 이것은 어떻게 출력될까??
> print('%10.5s' % ('likelike'))

# 지정한 문자열 총 자리수는 10자리고, 5자리를 넘으면 절삭한다.
# blank가 5자리고, 왼쪽에서부터 오른쪽 방향으로 채워진다.
# 나머지 5자리에 문자가 채워진다.
_____likel

# format으로 표현해보자
> print('{:>10.5}'.format('likelike'))
```

<br>

## 결론

- print('%-n1.n2s' % ('출력하기 원하는 문자열'))
  - n1은 전체 자릿수
  - n2는 출력되길 원하는 문자열의 총 자리수를 의미
  - '-'는 정렬 방향을 역으로 한다.
  - 정렬 방향 default는 오른쪽, (-)는 왼쪽 정렬을 의미
- print('{:^ > < n1.n2}'.format('string'))
  - n1은 전체 자릿수
  - n2는 출력되길 원하는 문자열의 총 자리수를 의미
  - '^' 는 가운데 정렬
  - '>'은 오른쪽 정렬, '<' 는 왼쪽 정렬을 의미
  - `.format`은 `s`를 입력하지 않는다.

---

<br>

# 3. Integer 정수형 출력 (d)

- `.format` 은 문자열 `s`는 입력하지 않는다.
- 정수형 `d` or `i`, 실수형 `f` 은 입력한다.

```yml
> print('%d %d' % (1, 2))
1,2

> print('{} {}'.format(1,2))
1,2

> print('{1} {0}.format(1,2)')
2,1

> print('%4d' % (24))
> print('{:>4d}'.format(24))
> print('{:4d}'.format(24))
  24

> print('%-4d' % (24))
> print('{:<4d}'.format(24))
24

> print('{:^4d}'.format(24))
 24

> print('{:_>4d}'.format(24))
__24

> print('{:_<4d}'.format(24))
24__

> print('{:_^4d}'.format(24))
_24_

```

<br>

## 결론

- **print('%-n1d' % (integer))**

  - n1은 전체 자릿수
  - '-'는 정렬 방향을 역으로 한다.
  - 정렬 방향 default는 오른쪽, (-)는 왼쪽 정렬을 의미

- **print('{:^ > < n1d}'.format(integer))**
  - n1은 전체 자릿수
  - '^' 는 가운데 정렬
  - default와 '>'은 오른쪽 정렬, '<' 는 왼쪽 정렬을 의미

---

<br>

# 4. Float 실수형 출력 (f)

```yml

# 전체 실수자리를 따로 지정하지 않으면 8자리까지 출력된다.
> print('%f' % (3.1415926535))
> print('{:f}'.format(3.1415926535))
3.141593
# 3.141592가 아니고, 반올림되어 3.141593 이다.


# 소수 부분 8자리
> print('%.8f' % (3.1415926535))
3.14159265
# 반올림 되어 맨 마지막 자리 수가 5다.

# 소수 부분 12자리
> print('%.12f' % (3.1415926535))
3.1415926535

# 소수점(.)까지 포함하여 6자리고, 소수 부분은 2자리, 빈 부분은 0으로 표시한다.
> print('%06.2f' % (3.1415926535))
> print('{:06.2f}'.format(3.1415926535))
003.14

# 소수점(.)까지 포함하여 6자리고, 소수 부분은 2자리, 빈 부분은 blank로 둔다.
# blank가 있기 때문에 정렬 개념이 적용된다. 그래서 오른쪽 정렬된 상태
> print('%6.2f' % (3.1415926535))
   3.14
# 왼쪽 정렬 상태
> print('%-6.2f' % (3.1415926535))
3.14
```

<br>

## 결론

- **print('%n1.n2f' % ())**
- **print('{:n1.n2f}'.format())**

  - n1 은 전체 자릿수로서, 소수점을 포함한다.
  - n2는 소수점 자리를 말한다.
  - n1 앞에 0이 붙으면 정수 부분에서 비어있는 자리수는 0으로 표기된다.

---

<br>

# Reference

- [Python tutorial](https://www.python-course.eu/python3_formatted_output.php)
- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
