# Intro

> 1. [Copy](#1-copy)
> 2. [Shallow copy](#2-shallow-copy)
> 3. [Deep copy](#3-deep-copy)
> 4. [Summary](#4-summary)

- [[TIL] Python basic 29: Data Model](https://jeha00.github.io/post/python_basic/python_basic_29_datamodel/#32-list-comprehension%EC%9D%98-%EC%A3%BC%EC%9D%98%EC%82%AC%ED%95%AD)에서 간단히 다뤘던 shallow copy와 deep copy에 대해 깊이 알아보자.

<br>

- shallow copy와 deep copy는 모든 분야에 통틀어 알고 있어야 하는 지식이다.
- Python에서는 모든 걸 객체취급하는데, 이 객체의 복사를 수행하는 명령어가 copy 명령어다.
  - 하지만, 어느 수준까지 복사가 되는 지에 따라 copy, shallow copy 그리고 deep copy로 나눠진다.
  - 각 copy에 대해 정확히 이해한 후, 프로그램 개발에 사용해야 문제가 발생하지 않고, 디버깅의 방해 요소가 되지 않는다.
  - 또한, 구현하려는 목적에 맞게 이 3가지 copy 방식을 구분해서 사용해야 한다.

<br>

---

# 1. Copy

```yml
# mutable data type
> a_list = [1, 2, 3, [4, 5, 6], [7, 8, 9]]

# call by reference
> b_list = a_list

# 동일한 id가 출력된다.
> print(id(a_list), id(b_list))
2230595359488  2230595359488

> print(id(a_list) == id(b_list))
True

# b_list를 수정해보자.
> b_list[2] = 100

# b_list만 수정했지만, 어째서인지 a_list까지 수정되었다.
> print(a_list)
[1, 2, 100, [4, 5, 6], [7, 8, 9]]

> print(b_list)
[1, 2, 100, [4, 5, 6], [7, 8, 9]]

# b_list를 다시 수정해보자.
> b_list[3][2] = 100

> print(a_list, b_list)
[1, 2, 100, [4, 5, 100], [7, 8, 9]]

> print(a_list, b_list)
[1, 2, 100, [4, 5, 100], [7, 8, 9]]
```

- 일반적인 copy 방식으로 `call by reference` 방식이다.
- 동일한 reference를 참조하기 때문에, `b_list`의 성분만을 수정했지만 `a_list` 까지 수정되었다.

<br>

---

# 2. Shallow Copy

> 중첩 data는 수정된다.

- shallow copy는 위에 일반 copy와 달리 `copy` module을 import 해야 한다.

```yml
> c_list = [1, 2, 3, [4, 5, 6], [7, 8, 9]]
> d_list = copy.copy(c_list)

# 위에 call by reference로 복사한 것과 달리  id 값이 다른 걸 확인할 수 있다.
> print(id(c_list), id(d_list))
1892474493824  1892474493568

# d_list 수정
> d_list[1] = 100

# c_list는 수정되지 않았다.
> print('c_list > ', c_list)
c_list > [1, 2, 3, [4, 5, 6], [7, 8, 9]]

# d_list만 수정되었다.
> print('d_list > ', d_list)
d_list > [1, 100, 3, [4, 5, 6], [7, 8, 9]]
```

- 여기까지만 보면 왜 shallow copy인지 이해가 안갈 것이다.
- 왜냐하면 d_list를 수정해도 c_list가 수정되지 않기 때문이다.
- 그러면 list 안에 list 성분을 수정해보자.

```yml
> d_list[3].append(1000)
> d_list[4][1] = 10000

# d_list만 시도했지만, c_list까지 중첩 data가 수정되었다.
> print('c_list > ', c_list)
 [1, 2, 3, [4, 5, 6, 1000], [7, 10000, 9]]

> print('d_list > ', d_list)
 [1, 100, 3, [4, 5, 6, 1000], [7, 10000, 9]]
```

- mutable 안에 중첩 data는 동일한 reference를 참조한다는 걸 알 수 있다.
- 이를 id() function으로 확인해보자.

```yml
> print('nested data - ',id(c_list[3]), id(d_list[3]))
nested data -  2343545032000 2343545032000

> print('c_list > ', id(c_list))
c_list >  2636124593152
> print('d_list > ', id(d_list))
d_list >  2636124592896
```

- 중첩된 data 까지는 독립된 reference를 가지지 않는 걸 확인했다.

<br>

---

# 3. Deep Copy

> 중첩된 data까지 독립된 id를 가진다.

- Deep copy도 `copy` module을 import 하는 것부터 시작한다.
- 내장된 data가 있을 경우, data는 `참조하는 객체` 와 `참조값`으로 구성된다고 말할 수 있다.
- shallow copy는 `참조값`만 복사하는 방식이면, deep copy는 `참조값` 뿐만 아니라 `참조하는 객체`까지 복사한다.
- 그래서 deep copy를 `깊은 복사` 말고, `전체 복사`라고도 한다.

```yml
> c_list = [1, 2, 3, [4, 5, 6], [7, 8, 9]]
> d_list = copy.deepcopy(c_list)

# 다른 id 값을 확인할 수 있다.
> print('Ex3 > ', id(c_list))
Ex3 >  2636124592960
> print('Ex3 > ', id(d_list))
Ex3 >  2636124593408

# 내장된 data도 독립된 id값을 가진 걸 확인할 수 있다.
> print('nested data > ', id(c_list[3]))
nested data >  1329303493056
> print('nested data > ', id(d_list[3]))
nested data >  1329303494272
```

<br>

---

# 4. Summary

- 다음 image로 shallow copy와 deep copy 복사 정도를 비교하면 쉽게 알 수 있다.
- 같은 색상의 block이 동일한 id를 가지고 있다.

- shallow copy는 객체만을 복사하고, 객체의 성분은 복사하지 않는다.

  - 객체는 call by value, 객체의 성분은 call by reference

- 하지만, deep copy는 객체와 객체의 성분까지 복사한다.

  - 객체와 객체의 성분도 call by value

- shallow copy

    <p align="center"><image src ="https://user-images.githubusercontent.com/78094972/162728048-5826d4e3-0857-43b4-b6bc-9b941e42901c.png"/></p>

- deep copy

    <p align="center"><image src ="https://user-images.githubusercontent.com/78094972/162728047-aec3b86b-989d-4107-9bde-f4193b3cc004.png"/></p>

<br>

---

# Reference

- [모두를 위한 파이썬 : 필수 문법 배우기 Feat. 오픈소스 패키지 배포 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B3%A0%EA%B8%89/dashboard)
- [[TIL] Python basic 29: Data Model](https://jeha00.github.io/post/python_basic/python_basic_29_datamodel/#32-list-comprehension%EC%9D%98-%EC%A3%BC%EC%9D%98%EC%82%AC%ED%95%AD)
