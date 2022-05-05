# 0. Introduction

> 1. []()

<br>

- 해당 내용은 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e) 강의만 보고 정리한 내용이다.
- [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 책에는 있지 않은 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# Process Synchronization의 전형적인 문제 3가지

### 첫 번째 문제: Bounded-Buffer Problem (Producer - Consumer Problem)

- 임시로 데이터를 저장하는 공간을 `buffer` 라 한다.
- 2종류의 Process
  - Producer: 생산자 process
    - 공유 buffer에다가 데이터를 만들어서 집어넣는 역할
  - Consumer: 소비자 process
    - 공유 buffer에서 데이터를 꺼내는 역할
- Synchronization 문제
  - 생산자와 소비자가 공유 buffer에 도착했을 경우 동기화되지 않는다.
- Bounded-buffer로 인한 문제점
  - To producer
    - Full buffer가 가득 차고 & 소비자가 안오고 생산자만 오면, 생산할 수 있는 data에 한계가 있어서 생산할 수 없다.
  - To consumber
    - 소비자 process는 소비할 수 있는 buffer가 없으면 문제가 발생된다.
    - 생산자 프로세스가 내용을 넣어줄 때까지 계속 기다린다.
- Synchronization variables
  - mutual exclusion → binary semaphore를 사용 : 공유 데이터의 상호 배제를 위해
    - 사용 전후, lock을 걸고 푼다. → 2번~4번
  - resource count → couting semaphore 사용 : 가용 buffer 수 표시 → 1번
- 순서는 다음과 같다.
  ![Producer-consumer.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f1b156c3-b792-4ffa-98e5-a57864cc4df0/Producer-consumer.png)
- Semaphore로 이 문제를 풀어보자.
  - Synchronization variables (=semaphore variable)
    - semaphore full = 0, empty = n, mutex = 1 ;
      ![Producer-consumer_1.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/629d116d-26ad-4bd1-936a-07ca45758463/Producer-consumer_1.png)
  - Producer
    - P(empty): 빈 buffer를 확인하고, 있으면 획득한다.
      - 빈 buffer가 없으면 이 단계에서 대기한다.
      - 소비자가 소비한 buffer는 producer에게는 자원이다.
    - P(mutex): buffer에 data를 넣기 위해 lock을 건다.
    - V(mutex): buffer에 건 lock을 푼다.
    - V(full): 내용이 들어가 있는 buffer의 갯수를 1 증가시키는 연산
      - 내용이 들어가 있는 buffer는 소비자에게는 자원이다.
      - 또한, 소비자가 내용 있는 buffer가 없어서 대기하고 있었다면 wake up 해주는 연산
  - Consumer
    - P(full): 내용이 들어있는 buffer 획득
    - P(mutex): 획득한 buffer에 lock을 건다.
    - V(mutex): lock을 푼다.
    - V(empty): 비어있는 buffer의 수를 1 증가시킨다.
      - 그리고 비어진 buffer는 생산자에게 자원이 된다.

### 두 번째 문제: Readers and Writers Problem

- 한 process가 DB에 write 중일 때, 다른 process가 접근하면 안된다.
- read는 동시에 여럿이 해도 된다.
- Solution
  - Writer가 DB에 접근 허가를 아직 얻지 못한 상태에서는 모든 대기 중인 Reader들을 다 DB에 접근하게 한다.
  - Writer는 대기 중인 Reader가 하나도 없을 때 DB 접근이 허용된다.
  - 일단 writer가 DB에 접근 중이면 Reader들은 접근이 금지된다.
  - Writer가 DB에 빠져나가야만 Reader의 접근이 허용된다.
- Shared data
  - DB 자체
  - readcount: 현재 DB에 접근 중이 Reader의 수
- Synchronization variables
  - mutex: 공유 변수 readcount를 접근하는 코드(critical section)의 mutual exclusion 보장을 위해 사용한다.
  - db: Reader와 writer가 공유 DB 자체를 올바르게 접근하게 하는 역할

![writer_reader.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4a194161-0f38-4dee-b6aa-cdc336f18503/writer_reader.png)

- Writer
  - P(db)가 DB에 lock을 걸고 쓰는 작업을 한다.
  - 이 작업이 끝나면 V(db)를 통해서 lock을 푼다.
  - Starvation 문제 발생
    - writer가 Reader들이 다 작업을 완료할 때까지 기다리는 중에 또 다른 reader 들이 들어오면 더 오래 기다려야한다.
    - 위 코드에서는 starvation에 대한 대책 코드는 나와있지 않는다.
- Reader
  - readcount는 공유 변수이기 때문에, race condition을 방지하기 위해서 mutex 변수를 사용.
  - 그래서 P(mutex)에서 readcount 변수에 lock을 건다.
  - readcount == 1: 자신이 최초의 reader라는 의미이고, DB에 lock을 건다.
  - readcount가 1보다 크다면, 이미 최초의 reader가 DB에 lock을 걸었기 때문에, 추가로 DB에 lock을 걸지 않고 읽기만 한다.
  - readcount —: 다 읽고 빠져나가는 process를 말한다.
  - readcount == 0이면, writer가 작성할 수 있다.

### 세 번째 문제: Dining-Philosophers Problem = 식사하는 철학자 문제

![Dinning_philosophers.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b8825e4d-e7b3-49be-ac99-27c4c098d80c/Dinning_philosophers.png)

- P(chopstick[i]) 과 P(chopstic[(i+1) % 5] 는 양쪽 젓가락을 집는 것
- Deadlock의 문제점
  - Deadlock 가능성
  - 모든 철학자가 동시에 배가 고파져 왼쪽 젓가락을 집어버린 경우
- 해결 방안
  - 4명의 철학자만이 테이블에 동시에 앉을 수 있도록 한다.
  - 젓가락을 두 개 모두 집을 수 있을 때에만 젓가락을 집을 수 있게 한다.
  - 비대칭
    - 짝수(홀수) 철학자는 왼쪽(오른쪽) 젓가락부터 집도록

![Dinning_1.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d4d9aaa5-361a-431d-a927-c21ea22b65e5/Dinning_1.png)

- 5명의 철학자 i가 하는 일

```c
do {
			pickup(i);  /*호출 함수*/
			eat(); /*무한 반복*/
			putdown(i); /*호출 함수*/
			think();  /*무한 반복*/
} while(1)
```

- semaphore self [i] = 0 or 1
  - i 번째 철학자가 젓가락을 소유할 수 있는 권한 유무
  - 0 → 권한 X
  - 1 → 권한 O
- semaphore mutex = 1
  - 본인의 상태를 본인 뿐만 아니라 다른 철학자가 바꿀 수 있음을 나타내는 것

# Monitor

- Semaphore의 문제점
  - 코딩하기 힘들다.
  - 정확성(correctness)의 입증이 어렵다.
  - 자발적 협력이 필요하다.
  - 한 번의 실수가 모든 시스템에 치명적 영향(아래 예시)

```c
V(mutex)
Critical section
P(mutex)

# 연산 순서가 바껴서 Mutual exclusion이 깨진다.
```

```c
P(mutex)
Critical Section
P(mutex)

# Deadlock
```

- Solution: Monitor
  - Monitor: 동시 수행 중인 프로세스 사이에서 abstract data type의 안전한 공유를 보장하기 위한 high-level synchronization construct
  - Semaphore와의 차이점: lock을 걸 필요가 없다.
- 객체 지향 언어를 보면, 객체를 중심으로 연산이 결정되듯이, monitor는 공유 데이터에 접근하기 위해서는 monitor 라고 정의된 내부의 procedure를 통해서만 접근이 가능.

![monitor_2.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e7a603d6-f954-42c8-8cf0-55412eb83236/monitor_2.png)

- shared variable declarations 공유 데이터에 대한 선언
- 이 모니터를 어떻게 지원할지는 프로그래밍 언어마다 다르다.
- 공유 데이터에 접근하기 위해서는 모니터로 정의된 내부 프로시저를 통해서만 공유 데이터에 접근할 수 있다.
- 모니터 안에는
  ![Monitor 내부 구성.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/33e65348-5b6f-4716-8558-bd38db277b2b/Monitor_내부_구성.png)
  - shared data + 공유 데이터에 접근하는 operations + initialization code
    - 공유 데이터에 접근하고 싶으면 밑에 operations (processor)들을 통해서만 가능하다.
    - 그리고, 프로시저들은 동시에 실행되지 않고, 한 번에 한 프로시저만 실행되도록 설정한다. 이러면 lock 걸 필요가 없다.
    - 동시 접근을 막기 위해서 lock을 개발자가 구현해야하는데, 모니터가 알아서 하나의 프로세스만 접근하게 만든다.
  - 그래서 세마포어와의 차이점은 lock을 걸 필요가 없다는 것이다.
  - semaphore에서는 resource의 갯수를 세는 개 필요하듯이 monitor도 그러하다.
    - 자원이 있으면 접근을 허용하고, 없으면 대기한다.
  - 모니터 안에 하나의 프로세스만 활성화되기 때문에, 나머지 process는 위에 entry queue에 줄서서 기다리면 되고,
  - 모니터 안에서 공유자원의 갯수가 없어서 기다려야 하면, 내용이 있는prcess는 위에 x고, 내용이 없는 프로세스를 기다리는 것은 y다.
- 모니터 내에서는 한 번에 하나의 프로세스만이 활동 가능하다.
- 프로그래머가 동기화 제약 조건을 명시적으로 코딩할 필요없다.
- 프로세스가 모니터 안에서 기다릴 수 있도록 하기 위해 **condition variable 사용**한다.
  - semaphore 변수와 동일한 역할을 하는게 condition variable이다.
- condition variable은 **wait** 과 **signal** 연산에 의해서만 접근 가능하다.
  - **x.wait();**
    - x.wait()을 invoke한 프로세스는 다른 프로세스가 x.signal을 invoke 하기 전까지는 suspend된다.
  - **x.signal();**
    - x.signal은 정확하게 하나의 **suspend**된 프로세스를 resume한다. suspend된 프로세스가 없으면 아무 일도 일어나지 않는다.
- monitor는 lock이 필요없는 이유

  ```c
  monitor bounded_buffer
  {   int buffer[N];
  		condition full, empty;

  		void produce(int x)
  		{ if there is no empty buffer
  				empty.wait();
  			add x to an empty buffer
  			full.signal();
  		}

  		void consume (int *x)
  		{ if there is no full buffer
  				full.wait();
  			remove an item from buffer and store it to *x
  			empty.signal();
  		}
  }
  ```

  - full, empty 같은 condition var.은 값을 가지지 않고, 자신의 큐에 프로세스를 매달아서, sleep 시키거나 큐에서 process를 깨우는 역할만 한다.
    - full: 내용이 들어있는 buffer를 기다리면서 잠들게 하는 역할
    - empty: 내용이 없는 buffer를 기다리면서 잠들게 하는 역할
  - 작업을 하기 위해서는 모니터 내부 코드를 실행해야 한다.
  - 생산자 소비자 모두 하나의 프로세스 안에서 모니터가 활성화되기 때문에, 락을 걸지 않아도 race condition 문제를 고려하지 않아도 된다.
  - empty.wait()
    - 생산자 입장에서는 빈 buffer가 필요한데, 그런 경우, empty.wait을 통해서 빈 buffer에 줄 서서 기다리게 한다.
  - full.signal()
    - 내용이 들어 있는 buffer가 있으면 생산자를 깨운다.

- Semaphore에는 항상 lock을 나타내는 변수가 있었다. Bounded-buffer problem의 경우, mutex = 1 이다. monitor에서는 lock을 거는 변수가 없다.
- Monitor와 Semaphore의 목적은 서로 다르다.
  - Monitor: 동시 접근 막는 것을 지원
  - Semaphore는 자원을 얻기 위해서 프로그래머가 작성하는 것

<br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
