# 0. Introduction

> 6. [프로세스 동기화의 첫 번째 문제](#6-프로세스-동기화의-첫-번째-문제)
> 7. [프로세스 동기화의 두 번째 문제](#7-프로세스-동기화의-두-번째-문제)
> 8. [프로세스 동기화의 세 번째 문제](#8-프로세스-동기화의-세-번째-문제)
> 9. [Monitor](#9-monitor)

<br>

- 해당 내용은 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e) 강의만 보고 정리한 내용이다.
- [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 책에는 있지 않은 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 6. 프로세스 동기화의 첫 번째 문제

> **Bounded-buffer problem (producer-consumber problem)** 으로서, **생산자와 소비자가 공유 buffer에 도착했을 경우, 동기화되지 않는 문제**

- **buffer: 임시로 데이터를 저장하는 공간**
- **두 종류의 process**

  - Producer: 생산자 process로서, 공유 buffer에다가 데이터를 만들어서 집어넣는 역할
  - Consumer: 소비자 process로서, 공유 buffer에서 데이터를 꺼내는 역할

- **Bounded-buffer로 인한 문제점**

  - To producer
    - buffer가 가득 차고, 소비자가 안오는 상황이라면 data를 더 이상 생산할 수 없다는 문제가 발생된다.
  - To consumer
    - 소비자 process는 소비할 수 있는 buffer가 없으면 문제가 발생된다.
    - 생산자 process가 내용을 넣어줄 때까지 계속 기다린다.

- **Synchronization variable**
  - mutual exclusion: 공유 데이터의 상호 배제를 위해서 `binary semaphore`를 사용
  - resource count: 가용 buffer 수를 표시하기 위해서 `counting semaphore`를 사용

![image](https://user-images.githubusercontent.com/78094972/166848937-b879d706-82f5-427b-8046-8d973224f2c7.PNG)

<br>

## 6.1 Semaphore로 문제 해결하기

- **Synchronization variable (= semaphore variable)**
  - semaphore full = 0, empty = n, mutex = 1

![image](https://user-images.githubusercontent.com/78094972/166849850-8daed179-abf7-4879-ba9a-049482a31c31.PNG)

- **Producer**

  - P(empty): 빈 buffer를 확인하고, 있으면 획득한다.
    - 빈 buffer가 없으면 이 단계에서 대기한다.
    - 소비자가 소비한 buffer는 producer에게는 자원이다.
  - P(mutex): buffer에 data를 넣기 위해 lock을 건다.
  - V(mutex): buffer에 건 lock을 푼다.
  - V(full): 내용이 들어가 있는 buffer의 갯수를 1 증가시키는 연산
    - 내용이 들어가 있는 buffer는 소비자에게는 자원이다. 그리고, 소비자가 내용 있는 buffer가 없어서 대기하고 있으면 소비자를 wake up 해주는 연산

- **Consumer**

  - P(full): 내용이 있는 buffer 획득
  - P(mutex): 획득한 buffer에 lock을 건다.
  - V(mutex): lock을 푼다.
  - V(empty): 비어있는 buffer의 수를 1 증가시킨다. 그리고, 비어진 buffer는 생산자에게 자원이 된다.

<br>

---

# 7. 프로세스 동기화의 두 번째 문제

> **Readers and writers problem**

- **Solution**

  - Writer가 DB에 접근 허가를 아직 얻지 못한 상태에서는 모든 대기 중인 Reader들을 다 DB에 접근하게 한다.
  - Writer는 대기 중인 Reader가 하나도 없을 때, DB 접근이 허용된다.
  - 일단 Writer가 DB에 접근 중이면 Reader들은 접근이 금지된다.
  - Writer가 DB에 빠져나가야만 Reader의 접근이 허용된다.
  - read는 동시에 여러 개가 접근해도 된다.

- **shared data**

  - DB 자체
  - readcount: 현대 DB에 접근 중인 reader의 수

- **Synchronization variables**

  - mutex: 공유 변수 readcount를 접근하는 코드(critical section)의 mutual exclusion 보장을 위해 사용
  - DB: reader와 writer가 공유 DB 자체를 올바르게 접근하는 역할

![image](https://user-images.githubusercontent.com/78094972/166869415-0175c2d0-9b29-4589-87f3-8a3c282db047.PNG)

- **Writer**

  - P(db)가 DB에 lock을 걸고 쓰는 작업을 수행
  - 이 작업이 끝나면 V(db)를 통해서 lock을 푼다.
  - starvation 문제 발생
    - write가 reader들이 다 작업을 완료할 때까지 기다리는 중에, 또 다른 reader들이 들어오면 더 오래 기다려야 한다.
    - 위 코드에서는 starvation에 대한 대책 코드는 나와있지 않는다.

- **Reader**

  - readcount는 공유 변수이기 때문에, race condition을 방지하기 위해서 mutex 변수를 사용한다.
  - 그래서 P(mutex)에서 readcount 변수에 lock을 건다.
  - readcount == 1: 자신이 최초의 reader라는 의미이고, DB에 lock을 건다.
  - if readcount > 1: 이미 최초의 reader가 DB에 lock을 걸었기 때문에, 추가로 DB에 lock을 걸지 않고 읽기만 한다.
  - readcount - -: process가 다 읽고 빠져나가기 때문에, 1 감소된다.
  - if readcount == 0: writer가 작성할 수 있다.

<br>

---

# 8. 프로세스 동기화의 세 번째 문제

> **Dinning-philosophers problem (식사하는 철학자 문제)**

- **Deadlock 발생지점**

  - 모든 철학자가 동시에 배가 고파서 왼쪽 젓가락을 집어버린 경우

- **Solution**

  - 4명의 철학자만이 테이블에 동시에 앉을 수 있도록 한다.
  - 젓가락을 두 개 모두 집을 수 있을 때에만 젓가락을 집을 수 있게 한다.
  - 비대칭
    - 짝수(홀수) 철학자는 왼쪽(오른쪽) 젓가락부터 집도록한다.
    - Semaphore code

![image](https://user-images.githubusercontent.com/78094972/166869411-8d01e5ef-6226-4790-abaa-3f52a28a498e.PNG)

- **Synchronization variables**

  - enum {thinking, hungry, eating} state [5]
  - semaphore self[5] = 0 or 1
    - i 번째 철학자가 젓가락을 소유할 수 있는 권한 유무
    - 0 -> 권한 X
    - 1 -> 권한 O
  - semaphore mutex = 1

    - 본인의 상태를 본인 뿐만 아니라, 다른 철학자가 바꿀 수 있음을 나타내는 것

- Philosopher i: 5명의 철학자가 하는 일을 의미

  - putdown: 젓가락 내려놓기
  - pickup: 젓가락 집기
  - test

- 밑 단원 monitor 개념을 이용한 식사하는 철학자 문제

![image](https://user-images.githubusercontent.com/78094972/166918673-cd640e40-09ba-4899-af70-186e6602872f.PNG)

    - semaphore code와 비교하기

<br>

---

# 9. Monitor

## 9.1 Semaphore의 문제점

- 코딩하기 어렵다.
- 정확성 입증이 어렵다.
- 자발적 협력이 필요하다.
- 한 번의 실수가 모든 시스템에 치명적인 영향을 준다.

```yml
# Deadlock 발생 경우
P(mutex)
Critical section
P(mutex)

# Deadlock 발생하지 않는 경우
V(mutex)
Critical section
P(mutex)
```

<br>

## 9.2 Monitor

> **동시 수행 중인 프로세스 사이에서 abstract data type의 안전한 공유를 보장하기 위한 고수준의 동기화 구조체**

- **Semaphore와의 차이점**

  - lock을 걸 필요가 없다.
  - Monitor: 동시 접근 막는 것을 지원
  - semaphore: 자원을 얻기 위해서 프로그래머가 작성

- **monitor는 공유 데이터에 접근하기 위해서 monitor 라고 정의된 내부 procedure를 통해서만 접근이 가능하다.**

![image](https://user-images.githubusercontent.com/78094972/166883364-8f1bfcff-871b-4902-88e1-8f55bf210716.PNG)

- 이 monitor를 어떻게 지원할지는 프로그래밍 언어마다 다르다.

### 9.2.1 Monitor 내부 구조

![image](https://user-images.githubusercontent.com/78094972/166883357-5f2c5c89-91c0-45d3-bef5-88bc15c00e86.PNG)

> **shared data + shared data에 접근하는 operations + initialization code**

- **Process**

  - shared data에 접근하고 싶으면 밑에 operations (process) 들을 통해서만 가능하다.
  - process들은 동시에 실행되지 않고, 한 번에 한 process만 실행되도록 설정하므로, lock이 불필요하다. 그래서 개발자가 별도로 lock을 구현할 필요가 없다. (semaphore와의 차이점 이유)
  - monitor 안에 하나의 process만 활성화되기 때문에, 나머지 process는 이에 entry queue에 줄서서 기다린다.
  - monitor 안에 공유자원의 갯수가 없어서 기다려야 하면,
    - 내용 있는 process를 기다리는 queue는 x이고,
    - 내용 없는 process를 기다리는 queue는 y다.

- **semaphore에서는 resource의 갯수를 세는 개 필요하듯이 monitor도 그러하다.**

  - 자원이 있으면 접근 허용, 자원이 없으면 대기한다.

- **Condition variable**

  - monitor 안에서 process가 기다릴 수 있도록 하기 위해 condition variable을 사용한다.
    - semaphore 변수와 동일한 역할을 한다.
  - condition variable은 wait과 signal 연산에 의해서만 접근 가능하다.
    - x.wait(): x.wait()을 invoke한 process는 다른 프로세스가 x.signal을 invoke하기 전까지는 suspend 된다.
    - x.signal(): x.signal을 정확하게 하나의 suspend된 프로세스를 resume 한다. suspend된 프로세스가 없으면 아무 일도 일어나지 않는다.

- **monitor가 lock이 필요없는 이유**

```yml
> monitor bounded_buffer
> {   int buffer[n];
>   condition full, empty;

>   void produce(int x)
>   { if there is no empty buffer
>       empty.wait();
>     add x to an empty buffer
>       full.signal ();
>   }

>   void consume(int *x)
>   { if there is no full buffer
>       full.wait();
>     remove an item from buffer and store it to *x
>       empty.signal();
>   }
> }
```

- full, empty 같은 condition var.을 가지지 않고, 자신의 queue에 process를 매달아서, sleep시키거나, queue에서 process를 깨우는 역할만 한다.
  - full: 내용이 들어 있는 buffer를 기다리면서 잠들게 하는 역할
  - empty: 내용이 없는 buffer를 기다리면서 잠들게 하는 역할
- 작업을 하기 위해서는 모니터 내부 코드를 실행해야 한다.
- 생산자 소비자 모두 하나의 프로세스 안에서 활성화되기 때문에, 락을 걸지 않아도 race condition 문제를 고려하지 않아도 된다.
- empty.wait()
  - 생산자 입장에서는 빈 buffer가 필요한데, 그런 경우 empty wait을 통해서 빈 buffer에 줄 서서 기다린다.
- full.signal()
  - 내용이 들어 있는 buffer가 있으면 생산자를 깨운다.

<br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
