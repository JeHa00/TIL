# Python basic 19: External functions (외장 함수)

- 여러 외장 함수들 중 sys, pickle, os, time, random, webbrowser 에 대해 예제 실습으로 알아보겠다.
- 특히 sys, os, time은 훨씬 자주 사용되므로 중요하다.
- 외장함수는 `import`를 하는 것부터 시작한다.
- 이 포스팅은 외장 함수에는 이런 것들이 있다는 정도로만 정리하자.
- 해당 포스팅으로는 각 외장 모듈을 다 알 수 없다.
- 추가적인 학습을 반드시 해서 TIL에 올리자.

### 1. sys

- `sys` 모듈은 파이썬 인터프리터가 제공하는 변수와 함수를 직접 제어할 수 있게 해주는 모듈이다.

- `sys.argv`는 명령행에 인수를 전달하도록 하는 명령어다.

```yml

```

---

<br>

### 2. pickle

- `pickle` 모듈은 텍스트 상태의 데이터가 아닌 객체의 형태를 그대로 유지하면서, 파일에 저장하고 불러올 수 있게 하는 모듈이다.

```yml

```

---

<br>

### 3. os

- `OS 모듈`은 환경 변수나 디렉터리, 파일 등의 OS 자원을 제어할 수 있게 해주는 모듈이다.

```yml
> import os

# 사용자의 운영체제 정보를 파이썬에게 넘겨준다.
> print(os.environ)
environ({{'ALLUSERSPROFILE': 'C:\\ProgramData', 'APPDATA':  C:\\Users\\rudtl\\AppData\\Roaming', ....})


> print(os.environ['USERNAME'])
rudtl

```

---

<br>

### 4. time

- `time 모듈`은 시간과 관련된 모듈이다.

```yml
> import time

## 1970년 1월 1일 0시 0분 0초를 기준으로 지난 시간을 초 단위로 반환한다.
> print(time.time())
1646220813.7009172

## 하지만 위 방식은 알아보기가 힘들다.
# time.time이 반환한 시간을 사용하여 연도,월,일,시,분,초의 형태로 바꿔주는 함수다.
> print(time.localtime(time.time()))
time.struct_time(tm_year=2022, tm_mon=3, tm_mday=2, tm_hour=20, tm_min=41, tm_sec=0, tm_wday=2, tm_yday=61, tm_isdst=0)

## local.time 보다 더 간단히 표현하는 모듈이다.
> print(time.ctime())
Wed Mar  2 20:42:50 2022

## 원하는 형식으로 시간을 출력해주는 모듈이다.
# Year, Month, Day, hour , Minute, Second
> print(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())))
2022-03-02 20:42:50


## 시간 간격 발생
# 출력 간 delay를 1초로 한다.
> for i in range(5):
>   print(i)
>   time.sleep(1)
0
1
2
3
4
```

---

<br>

### 5. random

- `random`은 난수(규칙이 없는 임의의 수)를 발생시키는 모듈

```yml
> import random

## 0에서 1 사이의 아무 난수를 반환한다.
> print(random.random())


## 1에서 45 사이의 정수값을 랜덤으로 갖고 온다.
> print(random.randint(1, 45))

## 1에서 44 사이의 정수값을 랜덤으로 갖고 온다.
> print(random.randrange(1, 45))

## Iterable object의 argument의 순서를 섞어서 출력한다.
# 출력할 때마다 순서가 바뀐다.
> d = [1, 2, 3, 4, 5]
> random.shuffle(d)
> print(d)
[2, 5, 3, 4, 1]
[2, 4, 1, 3, 5]
...

## Iterable object의 argument들 중 무작위로 선택하는 함수
> d = [1, 2, 3, 4, 5]
> c = random.choice(d)
> print(c)
1
4
3
2

```

---

<br>

### 6. webbrowser

- `webbrowser`는 본인 OS의 web browser를 실행한다.

```yml
> import webbrowser

## 기본 웹 브라우저를 사용하여 아래 url로 자동으로 이동한다. 이미 실행된 상태라면 기존에 있던 tab이 이동된다.
> webbrowser.open("https://google.com")

## 기본 웹 브라우저에 새로운 탭이 생기면서 아래 url로 이동한다.
> webbrowser.open_new("https://google.com")


```

<br>

---

## Reference

- [라이브러리](https://wikidocs.net/33)
