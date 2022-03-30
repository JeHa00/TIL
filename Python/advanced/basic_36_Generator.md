
# 1. Generator

<br>

## 1.2 Generator란?

> - 모든 값을 메모리에 올려두고 이용하는 게 아닌, 필요할 때마다 생성해서 반환하는 객체.
> - 그래서 메모리를 효율적으로 사용할 수 있다.

- generator function은 lazy iterator를 반환하는 함수의 특별한 종류다.
- 이 function object는 list와 동일한 괄호를 사용한다.
- 하지만, list와 달리 메모리에 a lazy iterator의 성분을 저장하지 않는다.

- Generator function이 일반 function과의 차이는 `yield` statement다.
- `yield`는 generator를 반환한다.
- `return`을 사용할 경우 지역 변수가 사라지지만, `yield`는 local을 나가도 사라지지 않는다.

- **Generator의 장점**

  1. list comprehension, dictionary comprehension, sets comprehension 등 데이터 양이 증가하면 메모리 사용량이 증가하는데, 이 때 제네레이터를 사용하여 메모리 사용량을 줄일 수 있다.
  2. 단위 실행 가능한 코루틴(Coroutine) 구현과 연동이 가능하다.
  3. 작은 메모리 조각으로 사용 가능하다.

- 위에 class로 구현한 코드를 제네레이터로 구현해보자.

```yml
> class WordSplitter:
>   def __init__(self, text):

# 인덱스를 기억하지 않아도 된다.
>       self._text = text.split('')

>   def __next__(self):
>       for word in self._text:

# 이것이 제네레이터이며, 이 제네레이터가 위치 정보를 기억한다.
# 따로 예외처리를 하지 않아도 된다.
>           yield word
>       return

>   def __repr__(self):
>       return 'WordSplit(%s)' % (self._text)


> wi = WordSplitter('Do today what you could do tomorrow')

> wt = iter(wg)

# 출력 코드는 동일.
```

## 1.3 Generator 예제

<br>

## 1.4 Generator 관련 중요 함수들

<br>
