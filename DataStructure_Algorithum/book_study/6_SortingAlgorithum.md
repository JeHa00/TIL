# 0. Introduction

> 1. [정렬 알고리즘(Sorting algorithum)이란?](#1-정렬-알고리즘sorting-algorithum이란)
> 2. [버블 정렬(Bubble sort)](#2-버블-정렬bubble-sort)
> 3. [단순 선택 정렬(Straight selection sort)](#3-단순-선택-정렬straight-selection-sort)
> 4. [단순 삽입 정렬(Straight insertion sort)](#4-단순-삽입-정렬straight-insertion-sort)
> 5. [셸 정렬(Shell sort)](#5-셸-정렬shell-sort)
> 6. [퀵 정렬(Quick sort)](#6-퀵-정렬quick-sort)
> 7. [병합 정렬(Merge sort)](#7-병합-정렬merge-sort)
> 8. [힙 정렬(Heap sort)](#8-힙-정렬heap-sort)
> 9. [도수 정렬(Counting sort)](#9-도수-정렬counting-sort)

<br>

---

# 1. 정렬 알고리즘(Sorting algorithum)이란?

**_정렬(sorting)이란 이름, 학번, 학점 등의 키(key)를 항목값의 대소 관계에 따라 데이터 집합을 일정한 순서로 바꾸어 늘어놓는 작업을 말한다._**

이 정렬 알고리즘의 핵심은 **_교환, 선택, 삽입_** 이다.

정렬 알고리즘은 데이터를 위 3가지 작업을 통해서 정렬을 완료한다. 이 3가지를 응용하여 밑에 두 번째에서 언급한 것처럼 8가지 종류의 정렬 알고리즘을 구현한다.

정렬 알고리즘의 8 종류에는 버블, 단순 선택, 단순 삽입, 셸, 퀵, 병합, 힙, 도수 정렬이 있다.

이 외에도 정렬 알고리즘을 나누는 기준은 다음과 같다.

첫 번째, 정렬 방식에는 2가지 종류가 있다.

- **_오름차순(ascending order): '값이 작은' 데이터를 앞쪽에 늘어놓는 것_**
- **_내림차순(descending order): '값이 큰' 데이터를 앞쪽에 늘어놓는 것_**

![image](https://user-images.githubusercontent.com/78094972/173237785-717bf2e7-1fc9-4006-95bb-ed1bf4015366.jpg)

두 번째, 정렬 알고리즘 종류에는 대표적으로 8가지가 있지만, 이는 **_안정적인(stable) 정렬 알고리즘과 불안정한(unstable) 정렬 알고리즘으로 크게 나눠진다._**

- **_안정적인 정렬 알고리즘이란 정렬한 후에도 원래의 순서가 유지되는 알고리즘_**
- **_불안정적인 정렬 알고리즘이란 정렬 후에는 원래의 순서가 유지되는 것을 보장할 수 없는 알고리즘_**

아래 이미지는 안정적인 정렬을 보여준다. 안정적이지 않은 정렬은 아래 이미지의 반대로 생각하면 된다.

![image](https://user-images.githubusercontent.com/78094972/173238157-c6d93b62-1643-46ee-b3b6-e82d23e74105.jpg)

세 번째, 정렬 알고리즘을 나누는 또 다른 기준에는 **_내부 정렬(internal sorting) 과 외부 정렬(external sorting)이 존재한다._**

- **_내부 정렬(internal sorting): 정렬할 모든 데이터를 "하나의 배열에 저장할 수 있는 경우에 사용"하는 알고리즘_**
- **_외부 정렬(external sorting): 정렬할 데이터가 많아서 "하나의 배열에 저장할 수 없는 경우에 사용"하는 알고리즘_**

여기서 외부 정렬은 내부 정렬을 응용한 것으로, 이를 구현하기 위해서는 별도로 작업용 파일이 필요하여 알고리즘이 복잡하기 때문에, [Do it! 자료구조와 함께 배우는 알고리즘 입문](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9791163031727)을 통해 학습하는 모든 알고리즘은 내부 정렬이다.

<br>

---

# 2. 버블 정렬(Bubble sort)

버블 정렬이란 **_이웃한 두 원소의 대소 관계를 비교하여 필요에 따라 교환을 반복하는 알고리즘으로, 또는 단순 교환 정렬_** 이라 한다.

아래의 순서와 정보가 있는 배열이 있다. 이를 오름차순으로 바꿀려고 한다.

| 6   | 4   | 3   | 7   | 1   | 9   | 8   |
| --- | --- | --- | --- | --- | --- | --- |

버블 정렬의 정의처럼 이웃한 두 원소의 대소 관계를 비교한다. 먼저 '9'와 '8'을 비교한다. '9'가 훨씬 크기 때문에, 뒤로 교환하면 다음과 같이 된다.

| 6   | 4   | 3   | 7   | 1   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- |

그 다음으로 '1' 과 '8'을 비교한다. '1'이 작기 때문에, 그대로 유지한다.

이렇게 **_n개의 배열을 n-1 번 비교 및 교환하여 가장 작은 원소를 앞으로 이동하는 과정을 "패스(pass)" 라 한다._**

가장 작은 원소를 제외한 다음 작은 원소 선정을 위해서 패스 과정을 또 겪습니다. 이 때 패스 과정에서의 비교 횟수는 n-2번이다.

그 다음으로 '1'과 '8'을 비교한다. 이미 작은 값이 앞에 있으므로 교환 작업을 실행되지 않는다.

'7'과 '1'을 비교한다. 더 큰 숫자가 앞에 있으므로 교환 작업이 실행되고, 그 다음 '3'과 '1'도 교환 작업이 이뤄진다.

'4'와 '1'도, '6'과 '1'도 그러하다. 그러면 다음과 같이 순서가 바뀐다.

| 1   | 6   | 4   | 3   | 7   | 8   | 9   |
| --- | --- | --- | --- | --- | --- | --- |

이 다음에는 맨 앞에 `index(1)`은 제외한 인덱스 1 ~ 6까지 탐색한다. 그래서 점차 앞에 인덱스가 제외되면서 정렬이 맞춰진다.

이 버블 정렬을 개선한 알고리즘을 **_셰이커 정렬(shaker sort)_** 라고 한다.

이는 각 패스를 홀수 패스, 짝수 패스로 나눠서 접근하는데, 홀수 패스는 가장 작은 원소를 맨 앞으로 이동시키고, 짝수 패스는 가장 큰 원소를 맨 뒤로 이동시켜 패스의 스캔 방향을 번갈아 진행하는 방식이다.

<br>

---

# 3. 단순 선택 정렬(Straight selection sort)

단순 선택 정렬이란 **_버블 정렬처럼 방향성과 좌우 위치를 기준으로 접근하는 것이 아닌 크기만을 기준으로, 가장 작은 원소부터 선택해 알맞은 위치로 옮기는 알고리즘_** 이다.

이 알고리즘은 서로 이웃하지 않는 떨어져 있는 원소를 교환하므로, 안정적이지 않다.

배열이 초기 정렬이 다음과 같다고 하자.

| 3   | 4   | 2   | 3   | 1   |
| --- | --- | --- | --- | --- |

여기서 맨 앞 3을 3(L), 뒤에 3을 3(R) 이라 하자.

| 3(L) | 4   | 2   | 3(R) | 1   |
| ---- | --- | --- | ---- | --- |

그러면 단순 선택 정렬을 실행해보자.

첫 번째, '1'과 '3(L)' 을 바꿔보자.

| 1   | 4   | 2   | 3(R) | 3(L) |
| --- | --- | --- | ---- | ---- |

두 번째로, 1 이후부터 맨끝까지의 범위에서 맨 앞 원소와 제일 작은 원소 '4'와 '2'를 바꾼다.

| 1   | 2   | 4   | 3(R) | 3(L) |
| --- | --- | --- | ---- | ---- |

세 번째 또한, 2 이후부터 맨끝까지 맨 앞 원소와  작은 원소인 '4'와 더 가까이에 있는 '3(R)'을 바꾼다.

| 1   | 2   | 3(R) | 4   | 3(L) |
| --- | --- | ---- | --- | ---- |

다섯 번째, '4'와 더 가까이에 있는 '3(L)' 과 먼저 바꾼다.

| 1   | 2   | 3(R) | 3(L) | 4   |
| --- | --- | ---- | ---- | --- |

그러면 처음과 비교해보면 3(R) 과 3(L)의 위치가 바뀐 것을 알 수 있다.

이처럼 **_단순 선택 정렬_** 은 안정적이지 않다.

즉, 단순 선택 정렬은 점차 범위를 좁혀가면서 해당 범위의 맨 앞 원소와 그 범위에서 제일 작은 원소의 위치를 바꿔간다.

<br>

---

# 4. 단순 삽입 정렬(Straight insertion sort)

<br>

---

# 5. 셸 정렬(Shell sort)

<br>

---

# 6. 퀵 정렬(Quick sort)

<br>

---

# 7. 병합 정렬(Merge sort)

<br>

---

# 8. 힙 정렬(Heap sort)

<br>

---

# 9. 도수 정렬(Counting sort)

<br>

---

# Reference

- [Do it! 자료구조와 함께 배우는 알고리즘 입문](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9791163031727)
