# Python basic 22 : 'NoneType' object is not subscriptable

<br>

## 0. Introduction

> 1. [Error 종류와 원인](#1-error-종류와-원인)
> 2. [Error 해결 과정](#2-error-해결과정)

<br>

## 1. Error 종류와 원인

- Python basic 과정 강의를 끝내고, 간단한 프로젝트로 Hangman game 만들기를 해봤다.
- 그 과정에서 발생한 오류를 기록하고자 한다.
- 만드는 과정에서 모르는 에러가 발생했다.
- 바로 이 에러(: `TypeError: 'NoneType' object is not subscriptable`) 다.

<br>

- 에러가 발생한 코드는 다음과 같다.

```yml
> import csv
> import random

> words = []

> with open('./resource/word_list.csv', 'r', encoding = 'utf-8') as f:
>   reader = csv.reader(f)
>   next(reader)
>   for v in reader:
>     words.append(v)

> q = random.shuffle(words)

# 이 line에서 에러가 발생했다.
> print(q[0])
```

<br>

## 2. Error 해결과정

위 코드를 작성한 이유는 words 의 성분들을 섞은 상태로 다른 변수에 할당하고 나서, Hangman game의 답을 random으로 q에서 뽑아내고자 했다. 지금 보면 터무니 없는 코드로 당연히 오류날만한 부분이었고, 너무 어렵게 생각했다. 왜 이렇게 작성한 것인지 원인을 생각해보았고, 어떻게 접근해야할지 생각해 보았다.

- random.shuffle() 의 내부 원리를 정확히 이해하지 못 했다.
- 급하게 생각하여 차근 차근 생각하지 못 했다. A 과정을 거쳐 B 과정을 수행한다고 했을 때, 각 과정을 위해서 무슨 함수를 사용할지 정한다.
- 각 함수의 기능을 영문으로 찾아보자.

<br>

그래서 결과부터 말하자면 위 코드는 다음과 같이 수정했다.

```yml
> random.shuffle(words)

> q = random.choice(words)

```

그러면 하나 하나 파악해보자.

<br>

제일 먼저 `.shuffle()`의 의미를 확인해보았다.

- random.shuffle(x) : Shuffle list x in place, and return None.

random.shuffle(x) 은 list x의 성분들의 순서를 섞지만, 아무것도 반환하지 않는 함수라는 의미다. q에는 아무것도 할당되지 않았다는 의미다.  
그렇기 때문에 `'Nonetype'`으로 object가 떴다. `subscriptable`은 구글 영문 사전, 네이버 영영 사전을 검색해도 의미가 나오지 않아, stackoverflow를 검색해보았다. [What does it mean if a Python object is "subscriptable" or not?](https://stackoverflow.com/questions/216972/what-does-it-mean-if-a-python-object-is-subscriptable-or-not) 이 글을 보면 다음과 같은 의미를 가진다.

<br>

```
 The [...] indexing syntax is called a subscript,
 because it's equivalent to mathematical notation that uses actual subscripts;
 e.g. a[1] is Python for what mathematicians would write as a₁.
 So "subscriptable" means "able to be subscripted".
 Which, in Python terms, means it has to implement __getitem__(),
 since a[1] is just syntactic sugar for a.__getitem__(1).
- Mark Reed   Apr 2, 2020 at 14:15

```

<br>

> [...] 는 인덱싱 문법에 사용되는 기호로, `subscript`라 한다. 왜냐하면 수학 표기법에서 a[1]은 a₁ 와 같기 때문이다. 즉, `subscriptable`는 `able to be subscripted`: `인덱싱에 사용할 수 있다`를 의미한다.
> 파이썬 용어의 관점에서 `[] indexing`은 `__getitem__`을 실행한다는 의미다. (ex) a[1] == `a.__getitem__(1)`

<br>

**결론**

**- `TypeError: 'NoneType' object is not subscriptable` : data type error의 종류이며, NoneType 객체는 인덱싱에 사용할 수 없다.**

**- 구글 번역도 좋지만 보다 직접 번역하며 분석하는 게 훨씬 공부에 도움이 된다.**

<br>

---

## Reference

- [What does it mean if a Python object is "subscriptable" or not?](https://stackoverflow.com/questions/216972/what-does-it-mean-if-a-python-object-is-subscriptable-or-not)
- [프로그래밍 시작하기: 파이썬 입문 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%9E%85%EB%AC%B8-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
