# 0. Introduction

> 1. [데이터 저장과 연산 순서](#1-데이터-저장과-연산-순서)
> 2. [Race condition](#2-race-condition)
> 3. [OS에서의 race condition 3가지](#3-os에서의-race-condition-3가지)
> 4. [Critical section problem](#4-critical-section-problem)
> 5. [Semaphores](#5-semaphores)

<br>

- 해당 내용은 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e) 강의만 보고 정리한 내용이다.
- [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 책에는 있지 않은 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 1. 데이터 저장과 연산 순서

- storage로부터 data를 가져와 연산한 후, 다시 storage에 저장한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150665-b6be593e-349a-45ac-b456-ca7012b1c258.PNG"/></p>

- 각 box 예시
  | E-box | S-box |
  |----|----|
  |CPU|Memory|
  |컴퓨터 내부| 디uy7스크|
  |프로세스| 그 프로세스의 주소 공간|

<br>

---

# 2. Race condition

<br>

## 2.1 Race condition이란??

> **여러 프로세스/스레드가 동시에 shared data를 조작할 때, 한 연산자가 stroage에서 가져와 작업 중인데, 작업 중인 data를 다른 연산자가 가져가서 작업하여 동기화되지 않는 현상**

<br>

## 2.2 Race condition 발생 배경과 원인

- **공유 데이터(shared data)의 동시 접근(concurrent access) -> 데이터의 불일치(inconsistency) 발생**

  - 특

- **그래서 concurrent process는 동기화가 필요하다.**

  - 일관성(consistency) 유지를 위해 협력 프로세스(cooperating process) 간의 실행 순서(oderly execution)을 정해주는 메커니즘이 필요하다.

- **데이터 불일치가 발생하는 상황들**

  - Multiprocessor system (Memory ~ CPU)
  - shared memory를 사용하는 process들 (address space ~ process)
  - kernel 내부 data를 접근하는 routine들 간 발생

-

<br>

---

# 3. OS에서의 race condition 3가지

> 원인: kernel address space를 공유할 때, race condition이 발생한다.

<br>

## 3.1 Interrupt handler vs kernel

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150669-f384805c-84d2-47ff-988d-2d7f1c78fc13.PNG"/></p>

- **연산 과정**

  - 첫 번째: load
    - 메모리에 있는 값을 CPU 내의 register로 불러들인다.
  - 두 번째: Increase
    - 불러들인 값을 증가시킨다.
  - 세 번째: Store
    - memory에 저장시킨다.

- **Race condition 발생**

  - Load 후, interrupt가 들어왔을 경우 ->
  - `Count++` 작업을 중단한 후, interrupt service routine으로 넘어간다. ->
  - interrupt handler 이기 때문에, `kernel address space`를 공유한다. ->
  - 이 상황에서 `Count --`를 실행하여, 완료하면 interrupt 당한 작업으로 돌아온다. ->
  - 돌아와서 interrupt handler 작업을 완료한 context 부터 작업을 실행해야 하지만, interrupt 당하기 전 load 한 context부터 실행된다.
  - 즉, `count--`는 실행이 안된 것과 동일하다.

- **Solution: 먼저 하던 작업을 끝낸 후, 넘긴다.**
  - 중요한 변수값을 건드리는 동안에는 인터럽트가 들어와도 인터럽트 처리 루틴으로 바로 넘어가지 않는다.
  - 인터럽트를 `disable` 시켰다가, 작업이 다 끝난 다음에 interrupt service routine으로 넘긴다.

<br>

## 3.2 Preempt a process running in kernel

- **한 프로세스의 system call을 통한 mode 전환 이미지**

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150673-e2cc077e-d9c6-4e5f-aa27-a5f5c9d9cb76.PNG"/></p>

<br>

- **kernel mode로 실행 중 system call로 인한 CPU 선점**
  - 두 프로세스의 address space 간에는 data sharing이 없다.
    - 그러나 'Pa의 CPU 할당시간 만료'로, Pa는 kernel mode로 실행 중 Pb에게 'CPU를 선점'당한다.
  - system call을 하는 동안, kernel address space의 data에 접근한다.
  - 이로 인해 `race condition`이 발생한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150674-b4f63a05-e17a-4a1c-9696-5a94efd2da9f.PNG"/></p>

- **Solution**
  - 첫 번째: kernel mode에서 수행 중일 때는 CPU를 빼앗지 않는다.
  - 두 번째: kernel mode에서 user mode로 돌아갈 때 CPU를 빼앗는다.

<br>

## 3.3 Multi-processor

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150667-a0483412-2fc6-488c-a463-98f43ea5c353.PNG"/></p>

- **Problem**

  - multi-processor인 경우, interrupt enable 과 disable로 해결되지 않는다.
  - CPU 한 쪽의 interrupt를 막았어도, 다른 CPU가 남아있기 때문이다.

- **Solution**
  - 해결책 1: 한 번에 하나의 CPU만이 kernel에 들어갈 수 있도록 하는 방법
  - 해결책 2: shared data in kernel에 접근할 때마다 이 데이터에 대해 lock 과 unlcok을 하는 방법
    - kernel 전체를 하나의 lock으로 막고, kernel에서 나올 때는 unlock 한다.

<br>

---

# 4. Critical section problem

## 4.1 Critical section(임계구역)이란??

> 각 process가 shared data에 접근하기 위해 가지고 있는 code

## 4.2 Critical section problem이란 무엇인가??

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150661-dec4d572-8875-4297-ab31-3a4d3f25cb4f.PNG"/></p>

- **Problem**
  - 2개 이상의 process가 shard data를 동시에 사용하기를 원하는 경우, 각 프로세스의 critical section을 통해서 접근해야한다.
  - A process가 critical section에 있을 때 = 공유 데이터에 접근하는 코드를 실행하고 있을 때,
  - A process의 CPU 할당 시간이 끝나서 다른 process에게 CPU를 넘겼다.
  - 하지만, A process가 critical section에 있기 때문에, 다른 process가 CPU를 받아도 critical section에 들어갈 수 없고, 대기해야 한다.
  - 이를 `Critical section problem` 이라 한다.

<br>

## 4.3 SW 해결법의 충족 조건 3가지(requirements)

- **가정(Assumption)**

  - 모든 process의 수행 속도는 0보다 크다.
  - process들 간의 상대적인 수행 속도는 가정하지 않는다.

- **첫 번째 requirement: Mutual Exclusion**

  - process P_i가 critical section 부분을 수행 중이면 다른 모든 process들은 그들의 critical section에 들어가면 안된다.

- **두 번째 requirement: Progress**

  - 아무도 critical section에 있지 않은 상태에서 critical section에 들어가고자 하는 process가 있으면 critical section에 들어가도록 해야한다.
    - 첫 번째 requirement의 부작용으로 critical section에 어떤 process도 들어가지 않은 상황이 발생한다.

- **세 번째 requirement: Bounded waiting**

  - Process가 critical section에 들어가려고 요청한 순간부터 그 요청이 허용될 때까지 다른 process들이 critical section에 들어가는 횟수에 한계가 있어야 한다.
    - 횟수 한계가 없으면 `starvation` 문제가 발생한다.

<br>

## 4.4 위 조건을 해결하기 위한 SW solution: Peterson's Algorithum

- **SW 방법으로 해결하기 위한 code의 일반적인 구조**

```yml
do {
    entry section  # 다른 process는 못 들어오게 shared data를 lock 하는 code
    critical section  # shared data에 접근하려는 코드
    exit section   # 다 처리 후, 다른 process가 들어오도록 unlock하는 코드
    remainder section  # 못 들어온 process를 의미하는 코드
}  while(1)
```

- **process들은 수행의 동기화(synchronization)을 위해 몇 몇 변수를 공유할 수 있다.**

  - synchronization varible

- **Algorithum이 필요한 이유**

  - 고급 언어는 단일 instruction이 아니기 때문에, instruction 수행 중 CPU를 빼앗길 수 있기 때문이다.
  - 그래서 이를 방지하고자 알고리즘으로 구현한다.

- **Synchronization variables(동기화 변수)**

```yml
boolean flag [2]  # process 0과 1의 flag
initially flag [모두] = false; # no one is in Critical Section
```

- **Process Pi가 CPU를 잡고 있는 상황**

```yml
do {
    flag [i] = true; # critical section에 들어가겠다는 의미
    turn = j;  # turn을 상대방 turn을 바꾼다.

    # 상대방이 깃발을 들고 있고, 이번이 상대방 차례인 조건을 만족하면 기다린다.
    while (flag [j] && turn == j)

    critical section
    flag [i] = false; # 깃발을 내린다.
    remainder section
} while (1)
```

- **모든 요구 조건들을 만족하지만, 그래도 문제점이 존재한다.**
  - `busy waiting` (= spin lock): 계속 CPU와 memory의 할당 시간을 쓰면서 기다리는 현상
    - while 문을 돌면서( **spin** ) 계속 **lock** 을 걸어서 상대방이 못 들어온다.
    - A process가 critical section에 들어가 있는 상태에서 B process가 CPU를 받아서 작동할 때, B process의 CPU 할당 시간 동안 while문이 만족되는지 체크한다.
    - 하지만, A process가 CPU를 잡아서 조건을 바꿔줘야 B process가 들어올 수 있다.
    - 그래서 이를 busy waiting이라 한다.

<br>

## 4.5 위 조건을 해결하기 위한 HW solution

- **Synchronization HW**

  - **_HW 적으로 Test & modify_** 를 **_atomic_** 하게 수행할 수 있도록 지원하는 경우, 앞의 문제는 간단히 해결된다.

    - HW 적으로 lock을 읽고 setting하는 작업을 말한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150660-e4eec628-47df-4643-8a27-79146c253c1e.PNG"/></p>

- **_atomic_** instruction이란??

  - 실행 중간에 간섭받거나 중단되지 않는다.
  - 같은 메모리 영역에 대해 동시에 실행되지 않는다.

- Test_and_set (a)

  - a라는 데이터를 읽은 후, 1로 쓰는 Instruction
  - 읽는 작업과 쓰는 작업을 동시에 지원하는 HW가 있다면 쉽게 해결할 수 있다.

- Mutual exclusion with Test & Set

```yml
Synchronization variable:
        boolean lock = false; # 다른 process가 lock이 걸려 있는지 확인

Process Pi
        do {
            while(Test_and_Set(lock));
            critical section
            lock = false; # lock에 0 할당
            remainder section
        }
```

- lock = false : critical section에 아무도 안들어간 상태
  - 들어가고 나서, Test_and_Set에 의해 lock = True로 바뀌면서 lock 이 걸린다.
- lock = true: critical section에 프로세스가 들어간 상태
  - 들어가지 못 하고, 계속 while문을 돈다.
  - critical section에서 나오면서 lock = false로 재설정하여 while문에서 돌고 있는 process가 들어오게 한다.

<br>

---

# 5. Semaphores

<br>

## 5.1 Semaphores 란??

> 공유 자원을 얻고 반납해주는 작업을 위해서 lock & unlock 작업을 도와주는 추상 자료형

- **추상화**

  - 세부 구현으로부터 분리하여 개념을 일반화시키는 것
  - what은 정의하지만, 언어를 사용하여 어떻게 구현할지 How는 정의하지 않는다.

- **추상 자료형**

  - 추상화를 통해 얻어낸 자료형
  - 구성: object + operation
    - operation의 구현은 system 마다 다르다.

- **_atomic_** instruction이란??

  - 실행 중간에 간섭받거나 중단되지 않는다.
  - 같은 메모리 영역에 대해 동시에 실행되지 않는다.

- **Semaphores S**

  - 위 SW와 HW 알고리즘들 방식을 추상화시켜, 보다 효율적으로 관리한다.
  - integer variable (=Semaphore variable)
    - semaphore variable = 자원의 갯수
      - ex) semaphore variable = 5: 자원의 갯수가 5개라는 의미
    - 자원을 획득하는 연산을 사용하면 자원의 갯수는 감소
    - 자원을 반납하는 연산을 사용하면 자원의 갯수는 증가
  - 정의된 2가지 **_atomic_** operation: P, V

    - Semaphore variable을 가지고 수행하는 연산
    - P(S) operation: 공유 데이터 자원을 획득하고 lock을 거는 연산

      ```yml
      // S가 음수라는 건 자원이 없다는 걸 의미한다.
      // 자원이 없기 때문에, while문에서 계속 반복하며 기다린다.
      while(S <= 0) do no-op;

      // 자원을 획득하여 P 연산을 시작하므로, S 값을 1 감소시킨다.
      S--;
      ```

    - V(S) operation: 공유 데이터 자원을 다 사용하고 나서 반납하고, unlock하는 연산

      ```yml
      // operation 사용이 끝나면 V 연산을 하여 S 값을 1 증가시킨다.

      S++
      ```

- **Semaphore의 문제점**
  - while문에서 계속 기다리기 때문에 `busy & wait` 문제가 존재한다.

<br>

## 5.2 Criticall section of n process

> critical section에 semaphore 사용하기

- **mutex = mutual exclusion**

```yml
Synchronization variable
semaphore mutex: # initally 1

Process Pi:
do {

    # 진입할 때 사용하는 연산. 이 조건에 만족하면 critical section에 진입하고 semaphore 감소, 그렇지 않으면 대기.
    P(mutex):

    critical section

    V(mutex): # 빠져나올 때 사용하는 연산으로, semaphore 증가

    remainder section

} while(1)
```

- **busy-wait(spin lock) 방식은** CPU를 할당받았지만, while문에서 대기하는 걸로 할당시간을 낭비하기 때문에 **효율적이지 못하다**.
- 그래서 **block & wake up (=sleep lock)** 방식으로 구현한다.
  - shared data를 쓰고 있는 process가 criticial section 실행을 완료할 때까지, 대기 중인 process는 `block` 상태에 있어서 CPU를 얻지 못하고,
  - 사용 중이던 process가 데이터를 내놓으면 `block` 상태에 있는 process는 `wake up` 하여 ready queue에 들어와서 대기하는 방식

<br>

## 5.3 Block & Wakeup implementation

- **Semaphore 정의**

```yml
typeef struct
{ int value; # semaphore를 의미
  struct process *L;  # queue for process wait
} semaphore;
```

- **Block & Wakeup**

  - Block
    - kernel은 block을 호출한 process를 suspend 시킨다.
    - 이 process의 PCB를 semaphore에 대한 wait queue에 넣는다.
  - Wake up
    - block state인 process P를 wake up
    - 이 process의 PCB를 ready queue로 옮긴다.

- **정의된 Semaphore 연산**

  - **P(S)**: resource 획득 연산
    - S: semaphore variable

  ```yml
  S.value --; # 자원을 획득하기 때문에 감소
  if (S.value < 0 ) # 음수이면 들어가지 못 한다.
  {
      # 음수면 이 프로세스를 queue에서 대기하도록 추가한다. 그 후, block로 둔다.
      add this process to S.L;
      block
  }
  ```

  - **V(S)**: resource 반납 연산과 이 자원을 기다리면 잠든 프로세스를 깨우는 연산
    - S: semaphore variable

  ```yml
  S.value ++;

  # 자원을 내놓았는데도 0이거나 음수라는 건, 어떤 프로세스가 P 연산에 의해  block 상태임을 의미
  if (S.value <= 0 )
  {   # queue에서 제거한다.
      remove this process to S.L;
      wake(P);
  }
  ```

## 5.4 Busy-wait VS Block & wake-up

> critical section의 길이에 따라 달라진다.

- **critical section의 길이가 긴 경우**

  - block / wakeup이 필수
    - 오랫 동안 풀지 않은 lock을 풀기 위해 CPU를 얻어도 계속 while문에서 대기하는 게 길기 때문이다.

- **critical section의 길이가 짧은 경우**

  - block/wakeup overhead가 busy-wait overhead보다 더 커질 수 있다.
  - 일반적으로는 block/wakeup 방식이 더 좋다.

<br>

## 5.5 Two types of semaphores

> Counting semaphores and Binary semaphores (=mutext)

| Attribute | Counting semaphore | Binary semaphore      |
| --------- | ------------------ | --------------------- |
| resource  | resource >= 0      | resource = 1          |
| purpose   | resource counting  | mutext(lock / unlock) |

<br>

## 5.5 Semaphore 주의사항: Deadlock and Starvation

<br>

### 5.5.1 Deadlock

> Deadlock: 둘 이상의 process가 서로 상대방에 의해 충족될 수 있는 event를 무한히 기다리는 현상

- 어떤 일을 하기 위해서, S와 Q를 모두 획득해야지만 일할 수 있고, S와 Q를 모두 반환한다.
- S와 Q: 서로 배타적으로 사용할 수 있는, 1로 초기화된 semaphore

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166642365-7d72467b-bb72-4202-9deb-ade65d90868f.PNG"/></p>

    - Process 모두 하나씩 차지한 상황
        - P0가 S를 먼저 작업하다가 CPU를 빼앗겨서 P1이 CPU와 Q semaphore 를 얻어서 작업한다.
    - 그런데, 상대방의 것을 서로 요구한다. 하지만, 서로 가지고 있기 때문에, 영원히 기다려야 한다.
    - 왜냐하면 다 사용하고 나서 반환하기 때문이다.

- 이 문제 `Deadlock` 이라 한다.

- **Solution**

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166642369-2ede3e17-6535-4426-b629-e0fb499786ca.PNG"/></p>

- 서로 다른 프로세스여도 같은 순서로 정한다.
  - Q를 획득하려면 S를 먼저 획득하라는 의미

<br>

### 5.5.2 Starvation

> starvation = infinite blocking: process가 suspend된 이유에 해당하는 semaphore queue에서 빠져나갈 수 없는 현상

- 특정 process자원을 독점하여 나머지 프로세스가 자원을 얻지 못하고 무한히 기다리는 현상
  - Deadlock과 유사하지만, 다르다.

<br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
