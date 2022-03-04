# Python basic 1: 다양한 변수 선언법

> [1. 변수 할당 설명](##1.-변수-할당-설명)  
> [2. Object Identity](##2.-Object-References)  
> [3. 변수 네이밍 규칙과 예약어](##3.-다양한-변수-선언방법-과-예약어)  


<br>

## 1. 변수 할당 설명

### 1.1 기본 선언

- 다른 프로그래밍 언어는 변수 타입을 정하고 나서 값을 할당한다.
- 반면에, Python은 값을 정하고 나서 변수 타입을 알아서 할당해준다.
- 이 변수를 할당한다는 건 물리적으로 즉, 컴퓨터 내부적으로 컴퓨터 메모리의 일부를 할당하는 의미로, '고유 주소'가 지정된다는 걸 의미한다.
- 이 '고유 주소'는 `id` 라는 명령어로 확인할 수 있다.
- 함수 `id`: 객체(object)의 고유값 `id(identity)` 을 확인

```yml
# 700이라는 int type의 data를 n에 할당한다.
> n = 700

# 'n'이라는 변수의 data type을 확인해보자.
> print(type(n))

# 'n' 이라는 변수에 할당된 메모리 고유주소를 확인해보자.
> print(id(n))

```

**- 결론 : 선언을 한다는 건 메모리 값을 할당하는 걸 의미한다. 이는 `id` 값을 통해 `고유주소`를 확인하여 알 수 있다.**

### 1.2 동시 선언

- 만약 명칭만 다른 세 변수에 동일한 value가 할당된 경우, `id`는 어떻게 나올까?

```yml
# x, y, z에 동일한 값을 할당한다.
# 동일한 object 참조
> x = y = z = 700
> print(x, y, z)

# x의 id 와 y의 id는 같은가?
> print(id(x) == id(y))
> print(id(x), id(y))

# y의 id 와 z의 id는 같은가?
> print(id(y) == id(z))
> print(id(z))

```

- 위 두 질문의 결과는 `True`가 나온다. 동일한 값과 type을 가지고 있기 때문에 메모리 주소가 동일하다는 걸 알 수 있다.
- 즉, 3개를 선언했지만 실제로 존재하는 건 1개라는 의미다. 파이썬이 하나의 오브젝트로 생성해버린다.
- 이러한 걸 `동시선언` 이라고 하며, 파이썬에서는 가능하다.
- 이처럼 하나 하나 최적화를 시키면 원활하고 빠른 프로그램 실행 흐름이 가능하다.

**- 결론: 여러 변수에 똑같은 값을 할당하면 파이썬은 내부에서 하나만 만들어진다.**

<br>

## 1.3 재선언

- 변수의 명칭은 동일하나 다른 value를 할당해보겠다.

```yml

# 동일 명칭의 변수가 다른 object를 참조

# var이란 변수에 75라는 정수형 데이터를 할당되었다.
> var = 75
> print(var)
> print(id(var))

# int. 형이 아닌 string 형 데이터를 재할당한다.
> var = "Change Value"
> print(var)
> print(id(var))

```

- 결과는 다음과 같다.

```yml
<class 'int'>
2298218369712
Change Value
<class 'str'>
2298224531568
```

- data type과 value가 변하자 id값이 달라진 걸 알 수 있다.
- 이것이 가능한 이유가 파이썬에는 `garbage collector` 가 있기 때문이다.
- 실제로 `재선언`할 때는 프로그램의 흐름이 끝날 떄까지 잘 추적하는 게 중요하다. `재선언`을 통해서 프로그램의 흐름이 꼬여질 수도 있기 때문이다.
- 그래서 큰 project에서는 변수의 사전이 엑셀이나 기타 문서로 존재한다고 한다.

**- 결론: 동일한 명칭의 변수여도 할당된 값이 변하면 파이썬이 알아서 id 값을 바꾼다.**

---

<br>

# 2. Object References

변수가 할당 상태일 때, 아래와 같은 상태가 일어나는 걸 의미한다. (예 : n = 700이라 선언했을 때)

첫 번째, type에 맞는 object를 생성: type을 통해서 class 'int'라는 오브제트가 생성됨을 알 수 있다.  
두 번쨰, 값 생성: 700을 안에서 생성  
세 번째, 콘솔 출력 : 700이 출력된다.

- 더 다양한 예제를 살펴보자.

```yml
#예1) 아래 출력은 동일하다. 즉, int() 를 하지 않아도 내부적으로 처리된다.
> print(300)
> print(int(300))

#예2)
> n = 800
> print(n, type(n))
# int형의 오브젝트임로 생성되었다. 그리고 출력된다.

> m = n
> print(m, n)
> print(type(m), type(n))
> m = 400
> print(m, n)
> print(type(m), type(n))

> m = 800
> n = 600

#m과 n의 고유값이 다르다.
> print(id(m), id(n))
> print(id(m)==id(n))

```

<br>

# 3. 다양한 변수 선언방법 과 예약어

- 이 규칙들을 가지고 변수를 선언하면 세련되고, 코드를 재활용할 때 가독성이 좋은 소스 코드로 만들 수 있다.

> Camel Case : method를 선언할 때 사용. ex) numberOfCollegeGraduates
> Pascal Case : 언어 상관 없이 class를 주로 선언할 때 사용. ex) umberOfCollegeGraduates
> snake case: 파이썬에서 변수를 선언 시 사용. ex) number_of_college_graduates

Camel과 Pascal의 차이는 첫 문자가 소문자냐 대문자냐의 차이다.

- 이외에 허용하는 변수 선언 법은 다음과 같다.
  > 숫자로 시작하지 않는다.  
  > 변수는 되도록 snake case로 선언한다.  
  > 예약어 같이 문법에 사용되는 단어는 변수명으로 불가능하다.

```yml
> age = 1
> Age = 2
> aGe = 3
> AGE = 4
> a_g_e = 5
> _age = 6
> age_ = 7
> _AGE_ = 8
```

- 예약어는 `python reserved words`로 검색하면 나온다.
- 예약어의 종류는 다음과 같다.

```yml
False	def	if	raise
None	del	import	return
True	elif	in	try
and	else	is	while
as	except	lambda	with
assert	finally	nonlocal	yield
break	for	not
class	from	or
continue	global	pass
```

---

<br>

# Reference

- [Python tutorial](https://python-course.eu/python-tutorial/data-types-and-variables.php)
