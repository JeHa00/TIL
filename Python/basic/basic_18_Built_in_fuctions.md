# Python basic 18: Built-in functions (내장 함수)

- 내장 함수란 프로그래밍 언어의 라이브러리에 이미 등록되어 있는 함수를 말한다. 그래서 별도의 정의나 설치가 필요없다.
- 예시를 사용하여 내장함수에 대해 알아보겠다.
- 아래에 예시로 사용하는 내장함수는 반드시 알고 있자.

<br>

- `abs()`: 입력된 숫자형 데이터를 절대값으로 반환해주는 함수

```yml
> print(abs(-3))
3
```

<br>

- `all()` , `any`: interable 요소를 검사하여 성분이 참인지 거짓인지 검사하는 함수 (True or False)
- `all()` 은 안에 있는 요소가 논리 연산자 `and`처럼 다 True여야 True를 반환한다.
- `any()` 는 논리 연산자 `or`처럼 안에 있는 요소들 중 하나라도 True가 있으면 True다.

```yml
> print(all([1, 2, 3]))
True
> print(all([False, True]))
False
> print(any([False, True]))
True
> print(any([False, False]))
False
```

<br>

- `chr()`: 아스키 코드를 문자로 반환하는 함수
- `ord()`: 문자를 아스키 코드로 반환하는 함수

```yml
> print(chr(67))
C
> print(ord('c'))
67
```

<br>

- **`enumerate()`: index + Iterable 객체(list, tuple, dictionary, set)을 생성한다.**

```yml
> for i, name in enumerate(['abc', 'bcd', 'eft']):
>    print(i, name)
0 abc
1 bcd
2 efg
```

<br>

- `filter()`: Iterable 객체를 `지정한 함수 조건`에 맞는 값만 추출한다.
- filter(function or None, iterable) --> filter object

```yml
> def conv_positive(x):
>    return abs(x) > 2

# 위에서 지정한 함수 조건은 'conv_posotive' 에 의해서 주어지고, interable 데이터가 입력된다.
> print(filter(conv_positive, [1, -3, 2, 0, -5, 6]))
<filter object at 0x0000025A0B362FA0>

> print(list(filter(conv_pos, [1, -3, 2, 0, -5, 6])))
[-3, -5, 6]

## 단 한 번 쓸 함수를 위해 위에처럼 정의하면 분량이 늘어난다. 이럴 때, lamda 함수를 사용한다.
> print(list(filter(lambda x: abs(x) > 2, [1, -3, 2, 0, -5, 6])))
```

<br>

- `id()`: 객체의 주소값(reference)를 반환한다.
- `id()`: Return the identity of an object.

```yml
> print(id(5))
2144671066544

> print(id(float(4)))
2144671700368
```

<br>

- `len`: 요소의 길이를 반환한다.
- `len`: Return the number of items in a container.

```yml
> print(len('123456789'))
9

> print(len([1,2,3,4,5,6,7]))
7
```

<br>

- `max`: 입력된 iterable 자료형 중에 가장 큰 값을 반환한다.
- `max`: With a single iterable argument, return its biggest item. With two or more arguments, return the largest argument.
- `min`: `max`와 반대로 가장 작은 값을 반환한다.

```yml
> print(max([1,2,3]))
3

## 오름차순 시, y가 제일 크다.
> print(max('python study'))
y

> print(min([1,2,3]))
1

# blank가 제일 작은 값이라, 아무것도 없어보인다.
> print(min('python study'))


```

<br>

- **`map`: iterable 객체 요소를 `지정한 함수`를 실행 후 추출**
- **`map`: map(func, \*iterables) --> map object**
  - **Make an iterator that computes the function using arguments from each of the iterables.**
- 데이터 전처리 과정에서 많이 사용한다.
-

```yml
>  def conv_abs(x):
>      return abs(x)

>  print(list(map(conv_abs,[1,-3,2,0,-5,6])))
[1, 3, 2, 0, 5, 6]

## 또는 위에 함수 정의를 하지 않고, 람다 함수를 사용한다.


>  print(list(map(lambda x:abs(x),[1,-3,2,0,-5,6])))
[1, 3, 2, 0, 5, 6]

```

<br>

- `pow` : 제곱값 반환

```yml
> print(pow(2,10))
1024
```

<br>

- `range`: 반복가능한 객체(Iterable) 반환

```yml
> print(range(1,10,2))
range(1, 10, 2)

> print(list(range(1,10,2)))
[1, 3, 5, 7, 9]

> print(list(range(0,-15,-1))
[0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14]

```

<br>

- `round`: 반올림

```yml
## 소수점 둘쨰자리에서 반올림한다.
> print(round(6.5781, 2))
6.58
> print(round(5.6))
6
```

<br>

- `sum`: 반복가능한 객체(Iterable) 합 반환

```yml
> print(sum([6, 7, 8, 9, 10]))
40
> print(sum(range(1,101)))
5050
```

<br>

- `type`: 자료형의 type을 확인

```yml
> print(type(3))
<class 'int'>

> print(type({}))
<class 'dict'>

> print(type(()))
<class 'tuple'>

> print(type([]))
<class 'list'>
```

<br>

- `zip`: Iterable 객체의 요소를 묶어서 tuple type으로 반환
- `zip`: A zip object yielding tuples until an input is exhausted.

```yml
> print(list(zip([10,20,30],[40,50,777])))
[(10, 40), (20, 50), (30, 777)]

# 짝이 맞는 것만 반환한다.
# list 안에 tuple type의 argument가 담겨져있다.
> print(list(zip([10,20,],[40,50,777])))
[(10, 40), (20, 50)]

> print(type(list(zip([10,20,30],[40,50,777]))[0]))
<class 'tuple'>
```
