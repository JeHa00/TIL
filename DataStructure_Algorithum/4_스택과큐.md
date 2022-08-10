# 1. 스택(Stack)

> **_데이터를 임시 저장할 때 사용하는 자료 구조로, 데이터의 입력과 출력 순서는 후입선출(LIFO: Last In, First Out) 방식이다._**

이 스택에 데이터를 넣는 작업을 **_푸시(push)_** 라 하며, 스택에서 데이터를 꺼내는 작업을 **_팝(pop)_** 이라 한다.

회전 초밥 집에서 먹은 후, 쌓인 접시를 생각해보자. 마지막에 먹는 초밥 접시가 맨 위에 있고, 이 접시들을 꺼낸다고 하면 마지막에 먹은 초밥 접시가 제일 먼저 꺼내진다.

이렇게 쌓인 스택 자료 구조에서 쌓인 데이터의 윗 부분, 아랫 부분을 별도로 부르는 명칭이 있다.

윗 부분은 **_꼭대기(top)_**, 아랫 부분은 **_바닥(bottom)_** 이라 한다.

<br>

## 1.1 스택: list형 배열

이 스택(stack, stk)은 list형 배열이다.

list의 index가 0으로 갈수록 위의 **_바닥(bottom)_** 과 가까워지고, 바깥쪽으로 갈수록 **_꼭대기(top)_** 에 가까워진다고 생각하자.

또한, 이 스택의 크기는 **_capacity_** 단어가 의미하며, 스택에 쌓여 있는 데이터의 개수를 나타내는 정수값을 **_스택 포인터(stack pointer, ptr)_** 라 한다.

그래서, stk가 비어 있으면 ptr은 0이 되고, stk가 가득 차면 capacity와 동일한 값이 된다.

![image](https://user-images.githubusercontent.com/78094972/172626905-1855bada-f74e-4bbd-af2b-e9133dec2d61.jpg)

<br>

## 1.2 고정 길이 스택을 구현하기 위한 클래스와 메서드 종류

그러면 고정 길이 스택을 구현하기 위한 클래스와 메서드를 알아보자.

아래와 같이 정리하는 이유는 이 스택을 구현하기 위한 기본 틀을 정립하기 위함이다.

메서드 명이 정확히 일치하지 않을지라도 다음과 같은 기능을 구현할 필요가 있다는 걸 알기 위함이다.

- 초기화 \_\_init\_\_ method

  - 스택 배열을 생성하는 준비 작업을 수행하는 함수로서, capacity 만큼으로 스택 크기가 결정된다. 첫 모든 원소는 None이 list가 생성된다.

- 데이터 갯수를 알 수 있는 \_\_len\_\_ method

  - stack에 쌓여 있는 데이터 개수를 반환한다.

- stack이 비어 있는지 판단하는 is_empty() method

  - stack이 비어 있는지 판단하여, 비어있으면 True, 그렇지 않으면 False를 반환한다.

- stack이 가득 차 있는지를 판단하는 is_full method

  - stack이 가득 차 있는지 판단하여, 가득차면 True, 그렇지 않으면 False를 반환한다.

- 예외 처리 클래스 Empty와 Full

  - Empty class는 pop() 함수를 호출할 때, 비어있으면 내보내는 예외처리 class다.
  - Full class는 push() 함수를 호출할 때, 가득 차 있으면 내보내는 예외처리 class다.

- 데이터를 푸시하는 push() method

  - stack에 데이터를 추가한다.

- 데이터를 팝하는 pop() method

  - stack의 top에서 데이터를 꺼내어 그 값을 반환한다.

- 데이터를 들여보는 peek() method

  - stack의 꼭대기 data를 들여다본다.

- 스택의 모든 데이터를 삭제하는 clear() method

  - stack에 쌓여 있는 데이터를 모두 삭제하여 빈 스택을 만든다.

- 스택의 데이터를 검색하는 find() method

  - 스택 본체의 배열 stk 안에 value와 값이 같은 데이터가 포함되어 있는지 확인

- 데이터 갯수를 세는 count() method

  - stack에 쌓여 있는 데이터의 갯수를 구한다.

- 데이터가 포함되어 있는지 판단하는 \_\_contains\_\_ method

<br>

---

# 2. 큐(Queue)

> **_데이터를 임시 저장할 때 사용하는 자료 구조로, 데이터의 입력과 출력 순서는 선입선출(FIFO: First In, First Out) 방식이다._**

스택과 동일하게 list형 배열이며, 은행 창구에서 차례를 기다리거나, 마트에서 계산을 기다리는 줄을 생각하면 된다.

데이터를 꺼내는 쪽을 **_프런트(Front)_** 라고 하며, 맨 앞 원소를 가리킨다.

데이터를 넣는 쪽을 **_리어(rear)_** 라고 하며, 맨 끝 원소를 가리킨다.

**_리어_** 에 데이터를 추가하는 작업을 **_인큐(enqueue)_** 라고 하며, **_프런트_** 에서 데이터를 꺼내는 작업을 **_디큐(dequeue)_** 라고 한다.

스택과는 달리 데이터를 추가하고 꺼내는 작업의 방향이 다르다는 걸 아래 이미지로 볼 수 있다.

![image](https://user-images.githubusercontent.com/78094972/172626927-58936a8d-c0bf-4f3b-bc5c-27b71a330eb4.jpg)

이미지 상의 차이로 큐는 디큐를 하면 전체 배열을 위로 하나씩 올려야 하는 비용이 들고 복잡도로 판단하자면 O(n) 이다.

그래서 이를 해결하는 방법이 **_링 버퍼(ring buffer)_** 다. 이 방식은 인큐와 디큐에 따라 시작 원소와 끝 원소가 달라지는 상황에 맞춰서 **_전체 원소를 옮기는 것이 아닌 프런트(Front) 와 리어(Rear)의 각 인덱스를 계속해서 바꾸는 것이다._** 이런 경우 복잡도는 O(1) 이다. 아래 이미지를 참조하자.

![image](https://user-images.githubusercontent.com/78094972/172816375-639e69a6-a57a-4ac2-8982-8a38d86979fe.jpg)

from: [RingBuffer aka Circular Queue](https://iosexample.com/ringbuffer-aka-circular-queue/)

<br>

그러면, 이 큐를 구현하기 위한 클래스와 메서드에는 무엇이 필요할까???

고정 길이 큐를 구현하기 위한 클래스와 메서드 종류는 위에 스택과 동일하므로, 스택을 참고하자.  

<br>

---

# Reference

- [Do it! 자료구조와 함께 배우는 알고리즘 입문](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9791163031727)
- [RingBuffer aka Circular Queue](https://iosexample.com/ringbuffer-aka-circular-queue/)
