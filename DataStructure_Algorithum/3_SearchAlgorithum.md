# 1. 검색 알고리즘이란?

**_알고리즘이란 어떠한 문제를 해결하기 위해 정해 놓은 일련의 절차를 말하며, 올바른 알고리즘은 어떠한 경우에도 실행 결과가 똑같이 나오는 알고리즘을 말한다._**

**_검색이란 어떤 특정 조건을 만족하는 데이터를 찾아내는 것이다._**

그렇다면 검색 알고리즘이란 어떤 특정 조건을 만족하는 데이터를 찾아내기 위한 일련의 정해놓은 절차를 말한다는 걸 알 수 있다.

이 검색 알고리즘의 종류에는 다음과 같이 3가지가 있다.

- 배열 검색
- 연결 리스트 검색
- 이진 검색 트리 검색

이 중에서 배열 검색에 대해 먼저 알아볼 것이고, 이 배열 검색 또한 3가지 검색법이 있다.

- **_배열 검색_**
  - 선형 검색: 무작위로 늘어놓은 데이터 집합에서 검색을 수행한다.
  - 이진 검색: 일정한 규칙으로 늘어놓은 데이터 집합에서 아주 빠른 검색을 수행한다.
  - 해시법: 추가 삭제가 자주 일어나는 데이터 집합에서 아주 빠른 검색을 수행한다.
    - 체인법: 같은 해시값 데이터를 연결 리스트로 연결하는 방법
    - 오픈 주소법: 데이터를 위한 ㅐ시값이 충돌할 때, 재해시하는 방법

그러면 검색은 무조건 빠르게 수행하면 좋은 걸까??

그렇지 않다.

용도, 목적, 실행 속도, 자료 구조 등 여러 사항을 고려해서 선택해야 한다.

이제 배열 검색부터 알아보자.

<br>

---

# 2. 선형 검색(Linear search)

> **_선형으로 늘어선 배열에서 원하는 키값을 찾을 때까지 맨 앞부터 스캔하여 순서대로 검색하는 알고리즘_**

이 선형 검색이 종료가 될라면 검색이 실패하든가 성공해야 한다.

검색할 값을 찾지 못하고, 배열의 맨 끝을 지나가면 실패한 경우다.

검색할 값과 같은 원소를 찾았다면 당연히 성공한 경우다.

그러면 이 성공한 경우와 실패한 경우를 간단히 코드로 구현해보자.

```yml
# 배열 a에서 검색하는 프로그램
> i = 0
> while True:
>     if i == len(a): # 검색 실패
>     if a[i] == key: # 검색 성공
```

하지만 이 검색 종료를 판단하기 위한 횟수도 줄일 수록 오버헤드가 줄어드는 것이다.

이 오버헤드 비용을 반으로 줄이는 방법이 **_보초법(sentinel)_** 이다.

원래의 배열 맨 끝에 찾으려는 key 값을 추가한다. 그러면 위 코드에서 검색 실패에 해당하는 과정을 수행할 필요 없이, 검색 성공에 해당하는 경우만 판단하면 된다.

<br>

---

# 3. 이진 검색(Binary search)

> **_배열을 내림차순 또는 오름차순으로 정렬한 후, 검색 범위의 중간 위치에 있는 값이 key 값보다 작거나 크거나에 따라서 검색 범위를 점차 좁혀가는 검색하는 알고리즘으로, 선형 검색(순차 검색)보다 빠르다._**

[[코들리] 알고리즘 - 이진검색](https://www.youtube.com/watch?v=IfIuG95RH0o) 을 참고하자.

글로 더 자세히 설명하자면 다음과 같다.

배열 검색 범위의 맨 앞, 맨 끝, 중앙의 인덱스를 각각 pl, pr, pc 라고 하면, pl은 0, pr은 (n-1), pc는 (n-1) // 2로 초기화한다.

중앙 값을 기준으로 key값보다 크고 작냐에 따라서 범위가 점점 줄어들기 때문에, 선형 검색보다 탐색 횟수가 적어서 빠르다.

- a[pc] < key: 중앙에서 오른쪽으로 한 칸 이동하여, 새로운 왼쪽 끝 pl로 지정하고, 검색 범위를 뒤쪽 절반으로 좁힌다.
- a[pc] > key: 중앙에서 왼쪽으로 한 칸 이동하며, 새로운 오른쪽 끝 pc로 지정하고, 검색 범위로 앞쪽 절반으로 좁힌다.

그래서 이진 검색은 다음과 같은 두 가지 조건일 때, 검색이 종료된다.

- a[pc] 와 key가 일치하는 경우
- 검색 범위가 더 이상 없는 경우

<br>

### 복잡도(Complexity)

> **_알고리즘의 성능을 객관적으로 평가하는 기준_**

복잡도의 종류에는 다음 두 가지가 있다.

- 시간 복잡도(time complexity): 실행하는데 필요한 시간을 평가한다.
- 공간 복잡도(space complexity): 메모리(기억 공간)와 파일 공간이 얼마나 필요한지 평가한다.

복잡도는 **_Order의 첫 글자 O로 표시한다. _**

실행 횟수가 1이면 복잡도를 O(1)로 표시한다. 하지만, 실행 횟수가 n에 비례하는 경우 복잡도는 O(n)으로 표시한다.

그리고, 2가지 계산으로 구성된 알고리즘의 복잡도는 차원이 더 높은 쪽의 복잡도를 우선으로 하기 때문에, O(1)과 O(n)으로 구성되었다면 O(n)으로 여긴다.

복잡도 O에 대한 더 자세한 설명은 이 블로그 [알고리즘의 시간 복잡도와 Big-O 쉽게 이해하기](https://blog.chulgil.me/algorithm/)를 보자.

<br>

---

# 4. 해시법(Hasing)

> **_데이터의 추가, 삭제도 효율적으로 수행할 수 있는 검색법으로, 해시 함수(hash function)를 통해서 key를 해시값(hash value, key를 원소 갯수로 나눈 나머지)으로 데이터에 접근하는 방식_**

해시 테이블(hash table)에서 만들어진 원소를 **_버킷(bucket)_** 이라 한다.

그리고, key와 hash value는 일반적으로 다 대 1 (n:1) 이라서, 저장할 버킷이 중복되는 현상을 **_충돌(collision)_** 이라 한다.

이 충돌을 발생 시 대처 방법에는 아래와 같이 2가지가 있다.

- 체인법: 해시값이 같은 데이터 원소를 체인 모양의 연결 리스트롤 연결하는 방법을 말하며, 오픈 해시법(open hashing) 이라 한다. 
- 오픈 주소법: 빈 버킷을 찾을 때까지 해시를 반복한다.

<br>

---

# Reference

- [Do it! 자료구조와 함께 배우는 알고리즘 입문](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9791163031727)
- [[코들리] 알고리즘 - 이진검색](https://www.youtube.com/watch?v=IfIuG95RH0o)
- [알고리즘의 시간 복잡도와 Big-O 쉽게 이해하기](https://blog.chulgil.me/algorithm/)