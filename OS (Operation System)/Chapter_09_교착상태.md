# 0. Introduction

> 1. [The Deadlock problem](#1-the-deadlock-problem)
> 2. [Deadlock 발생의 4가지 조건](#2-deadlock-발생의-4가지-조건)
> 3. [Resource - Allocation graph](#3-resource---allocation-graph)
> 4. [Deadlock 처리 방법](#4-deadlock-처리-방법)

<br>

- 해당 내용은 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e) 강의만 보고 정리한 내용이다.
- [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 책에는 있지 않은 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 1. The Deadlock Problem

![Deadlock_1.PNG](https://user-images.githubusercontent.com/78094972/167450136-f73b9f27-c423-4563-b50a-2900a52e508f.png)

- **deadlock이 현실 사례 비유**
  - 누군가가 희생하지 않으면 교착 상태는 발생하지 않는다.
  - 즉, 각자 일부 자원을 가지고 있으면서, 상대방이 가지고 있는 걸 요구하는 상황

<br>

## 1.1 Deadlock이란??

> **일련의 프로세스들이 서로가 가진 자원을 기다리며 block된 상태**

<br>

## 1.2 Resource (자원)이란??

> - **하드웨어, 소프트웨어 등을 포함하는 개념**
> - (예) I/O device, CPU cycle, memory space, semaphore 등

- **프로세스가 자원을 사용하는 절차**

  - Request(요청) → Allocate(할당) → Use(사용) → Release(반납)

- **Deadlock Example 1**

  - 시스템에 2개의 tape drive가 있다.
  - 프로세스 P1과 P2 각각이 하나의 tape drive를 보유한 채 다른 하나를 기다리고 있다.

- **Deadlock Example 2**

  - Binary semaphore A and B
  - Po: P(A) → P(B)
  - P1: P(B) → P(A)
    - Po는 A를 획득한 후, B를 얻고 싶어한다. P1은 반대다.
    - 서로 반대 것을 가지고 있기 때문에, Deadlock 상황이다.

<br>

---

# 2. Deadlock 발생의 4가지 조건

> **아래 4가지 조건을 다 만족해야 deadlock이 발생한다.**

- **첫 번째: Mutal exclusion (상호 배제)**

  - 매 순간 하나의 프로세스만이 자원을 사용할 수 있다. (독점적 사용)

- **두 번째: No preemption (비선점)**

  - 프로세스는 자원을 스스로 내어놓을 뿐 강제로 빼앗기지 않는다.

- **세 번째: Hold and wait**

  - 자원을 가진 프로세스가 다른 자원을 기다릴 때, 보유 자원을 놓지 않고 계속 가지고 있는다.

- **네 번째: Circular wait**
  - 자원을 기다리는 프로세스 간에 사이클이 형성되어야 한다.
  - 프로세스 Po, P1, .... ,Pn이 있을 때
    - Po는 P1이 가진 자원을 기다린다.
    - P1은 P2가 가진 자원을 기다린다.
    - Pn-1은 Pn이 가진 자원을 기다린다.
    - Pn은 Po이 가진 자원을 기다린다.

<br>

---

# 3. Resource - Allocation Graph

> Deadlock 발생하는지 확인하기 위해, resource - allocation graph (자원 할당 그래프)를 그려본다.

<br>

## 3.1 Graph 설명

![Resource_allocation_graph.PNG](https://user-images.githubusercontent.com/78094972/167449422-f5cbb6bc-0ccf-4094-b5df-aa427f592ede.PNG)

- **Vertex (= 꼭지점, 정점)**

  - Process P = {P1. P2, ...., Pn}
  - Resource R = {R1, R2,...,Rm}

- **Edge (= 화살표 )**

  - Pi → Rj: request edge = Pi가 Rj 자원을 요청한다.
  - Rj → Pi: assignment edge = Rj 자원을 Pi 자원이 가지고 있다.

- **자원의 점은 instance를 의미한다**

<br>

## 3.2 deadlock 확인하기

그래프에 deadlock이 있는지 어떻게 알 수 있을까?

> graph에 cycle 유무에 따라 deadlock을 판단할 수 있다.

- 1번, Cycle 無 in graph -> **deadlock** X

- 2번 **Cycle 有 in graph ->**

  - 2-1번 one instance per resource -> **deadlock** O
  - 2-2번 multiple intsnace per resource -> **deadlock** X & 가능성 O

![Resource_allocation_graph_1.PNG](https://user-images.githubusercontent.com/78094972/167449427-18a2166c-537c-405e-afc1-5db0bf276b6c.PNG)

- **오른쪽 graph: 2-2번 case**

  - P4가 resource를 다 사용 후, 반납하면 P3는 이용가능해진다.
  - P2 또한 resource를 다 사용하고 반납하면 P1이 사용가능하다.
  - 한 resource에 여러 개의 instance가 존재하기 때문에 deadlock이 아니다.

- **왼쪽 graph: 2-1번 case**
  - R2 자원을 P1과 P2가 가지고 있으면서, P3가 이 자원을 요청하는 상황이기 때문에, deadlock 이다.

<br>

---

# 4. Deadlock 처리 방법

위로 올라갈수록 강한 방법이다. 하지만, 맨 마지막 방법을 대부분의 OS가 채택하는 이유는 deadlock을 탐색하는 것과 deadlock에 대처하는 것 모든 것이 overhead이기 때문이다.

- **첫 번째, Deadlock Prevention**

  - 자원 할당 시, deadlock의 4가지 필요 조건 중 **어느 하나가 만족되지 않도록 하는 것**

- **두 번째, Deadlock Avoidance**

  - 자원 요청에 대한 부가적인 정보를 이용해서 **deadlock의 가능성이 없는 경우에만 자원을 할당**
  - 시스템 state가 원래 state로 돌아올 수 있는 경우에만 자원 할당

- **세 번째, Deadlock Detection and recovery**

  - Deadlock 발생은 허용하되, 그에 대한 detection routine을 두어 deadlock 발견시 recover

- **네 번째, Deadlock Ignorance**
  - Deadlock을 시스템이 책임지지 않는다.
  - Unix를 포함한 대부분의 OS가 채택
    - Deadlock은 빈번히 발생하는 문제가 아니기 때문에, 이를 방지하기 위해 오히려 많은 overhead가 발생하기 때문에, 이 방식을 택한다.

<br>

## 4.1 Deadlock 처리 방법 첫 번째: deadlock prevention

> **Process가 resource를 요구하는 방식에 제한을 두는 방식으로, deadlock이 발생하는 4가지 필요 조건 중 어느 하나가 만족되지 않도록 하는 것**

- **Mutual Exclusion**

  - 공유해서는 안되는 자원의 경우, 반드시 성립해야 한다.
  - 따라서, 이 조건의 발생을 막아 deadlock을 해결하는 건 불가능하다.

- **Hold and wait 조건에 대한 방법**

  - 프로세스가 자원을 요청할 때, 다른 어떤 자원도 가지고 있지 않기

  - 방법 1: 프로세스 시작 시, 모든 필요한 자원을 할당받는 방법
    - 다 사용하고 나서 자원을 반납한다.
  - 방법 2: 자원이 필요할 경우, 보유 자원을 모두 놓고 다시 요청
    - hold한 자원을 다 뱉는다.
  - 하지만, 한 번에 한 프로세스만 자원을 소유할 수 있어서 효율적이지 않다.
  - starvation이 발생할 수 있고, throughput이 낮다.

- **No Preemption 조건에 대한 방법**

  - Preemption을 허락하기
  - Process가 어떤 자원을 기다려야 하는 경우, 이미 보유한 자원이 선점된다.
  - 모든 필요한 자원을 얻을 수 있을 때, 그 프로세스는 다시 시작된다.
    - 이 때, starvation이 발생할 수 있다.
  - state를 쉽게 save하고, restore할 수 있는 자원에서 주로 사용(CPU, memory)

- **Circular wait 막기**

  - 모든 자원 유형에 할당 순서를 정하여 정해진 순서대로만 자원을 할당하기
  - 예를 들어 순서가 3인 자원 Ri를 보유 중인 프로세스가 순서가 1인 자원 Rj를 할당받기 위해서는 우선 Rj를 release해야 한다.

하지만, 생기지도 않을 수 있는 이런 제약들로 인해서 다음과 같은 문제점을 낳기 때문에, 이 방법은 잘 사용하지 않는다.

⇒ **_Utilization 저하, throughout 감소, starvation 문제_**

<br>

---

## 4.2 Deadlock 처리 방법 두 번째: deadlock avoidance

> **_자원에 대한 사전 정보를 이용해서 deadlock의 발생 가능성을 계속 검사하여, resource-allocation state가 safe state인 경우에만 자원을 할당하는 방식_**

- **자원에 대한 사전 정보**

  - 가장 단순하고 일반적인 예: 프로세스들이 필요로 하는 **각 자원별 최대 사용량을 미리 선언**

- **safe state란??**

  - 시스템 내의 프로세스들에 대한 _safe sequence_ 가 존재하는 상태
  - 순서가 어떻든 safe sequence가 존재하면 safe state다.
  - 시스템이
    - safe state이면 no deadlock
    - unsafe state이면 possibility of deadlock 존재

- **safe sequence란??**

  - n개의 프로세스 중 하나인 Pi(1≤ i ≤ n)의 자원요청이 **_가용 자원 + 모든 P_j(j < i)의 보유자원_** 에 의해 충족되는 순서
  - safe sequence가 존재하면 cycle을 형성하지 않는다.
  - 조건을 만족하면 다음 방법으로 모든 프로세스의 수행을 보장한다.
    - Pi의 자원 요청이 즉시 충족될 수 없으면 모든 Pj(j <p i) 가 종료될 때까지 기다린다.
    - P(i-1)이 종료되면 Pi의 자원 요청을 만족시켜 수행한다.

- Deadlock Avoidance
  - 시스템이 unsafe state에 들어가지 않는 것을 보장한다.
  - 2가지 경우의 avoidance algorithum
    - **single instance** per resource types
      - **Resource Allocation Graph algorithum 사용**
    - **Multiple instances** per resource types
      - **Banker’s Algorithum (은행원 알고리즘) 사용**

<br>

### 4.2.1 Resource Allocation Graph algorithum: single instance per resource types

- 위의 resource allocation graph algorithum에 **_claim edge_** 를 추가한다.
- **Claim edge (점선):** Pi → Rj
  - 프로세스 Pi가 자원 Rj를 _미래에_ 요청할 수 있음을 뜻한다.
  - 프로세스가 해당 자원 요청 시 request edge로 바뀐다. (실선: 소유하고 있다)
  - Rj가 release되면 assignment edge는 다시 claim edge로 바뀐다.
- request edge의 assignment edge 변경 시(점선을 포함하여), cycle이 생기지 않는 경우에만 요청 자원을 할당.
- Cycle 생성 여부 조사 시, 프로세스의 수가 n일 때 O(n^2)의 Time Complexity 를 가진다.

![Resource_allocation_graph_algorithum.PNG](https://user-images.githubusercontent.com/78094972/167449436-43b0974d-ae85-4c50-b61d-7a5a70412b3e.PNG)

<br>

### 4.2.2 Banker’s Algorithum: multiple instances per resource types

> - 위의 single instance일 때를 넘어서 일반화하는 algorithum
> - 이용 가능한 자원으로 요청 양을 만족할 수 있는지 판단한다. 충족할 수 있으면 이 프로세스의 요청은 다 받아들이고, 충족되지 않으면 다 받아들여지지 않는다.

- **가정**

  - 모든 프로세스는 자원의 최대 사용량을 미리 명시
    - avoidance 설명대로 각 자원 별 최대 사용량을 미리 선언한 것
  - 프로세스가 요청 자원을 모두 할당받은 경우, 유한 시간 안에 이들 자원을 다시 반납
  - 이 알고리즘은 최악의 상황을 가정한다.

- **방법**

  - 기본 개념: 자원 요청 시, safe 상태를 유지할 경우에만 할당한다.
    - 충분히 할당할 수 있는 자원의 수가 있어도, safe 상태를 유지하지 못하면 할당하지 않는다는 의미
  - 총 요청 자원의 수가 가용 자원의 수보다 적은 프로세스를 선택
    - 그런 프로세스가 없으면 unsafe 상태
  - 그런 프로세스가 있으면 그 프로세스에게 자원을 할당
  - 할당(allocation)받은 프로세스가 종료되면 모든 자원을 반납 (available이 된다.)
  - 모든 프로세스가 종료될 때까지 이러한 과정 반복

- **사전 정보**

  - Allocation: 각 프로세스에 할당된 각 자원의 양
  - Max: 각 프로세스의 자원별로 최대 요구하는 자원의 양
  - Available: 각 자원 별로 현재 남아있는 자원의 양
  - Need: 각 프로세스의 추가로 요청 가능한 자원의 양

![Banker_algorithum.PNG](https://user-images.githubusercontent.com/78094972/167449405-f2a65211-5ccf-412b-b8da-6ea500ab81d3.PNG)

- **Resource**

  - 총 자원: A 10개,B 5개, C 7개
  - A = 2 + 3 + 2 = 7 이고, 가용자원으로 A가 3개임을 확인할 수 있다.
  - P0의 할당된 자원 B를 반납하면 이용가능한 B 자원의 수는 늘어난다.

- **safe sequence**

  - P0: P0에 추가로 할당할 수 있는 자원이 존재하지만, Need한 만큼 요청하면 가용자원만으로는 불가능하기 때문에, 요청을 받아들이지 않고, 기다린다.

  - P1: 최대 필요 요청은 가용 자원으로 충분히 가능하기 때문에, P0와 달리 받아들인다.

  - P1이 가용자원을 가져가서 다 사용 후, 자원을 반납하면 available resource에 추가되고 P3가 그걸로 가능하다.
    - 이런 순서가 나타나는게 P1,P3,P4,P2,P0다.
    - 이런 순서가 존재하면 절대 dead lock이 발생하지 않는 safe state다.

- 뱅커스 알고리즘 이렇게 최대요청을 해도 deadlock이 발생하지 않는 상황에서만 요청을 받아들여 deadlock을 피해간다.
  - 하지만, 이는 혹시 모를 상황을 대비하기 때문에 비효율적이다.

<br>

---

## 4.3 Deadlock Detection and Recovery

> 알고리즘을 통해 현재 시스템에 deadlock이 있는지 찾고, 알고리즘을 통해 deadlock을 복구하는 것

<br>

### 4.3.1 Single instance per resource type

> wait-for graph algorithum을 사용하며, deadlock detection을 하기 위해서는 wait-for graph에서 cycle이 있는지를 판단한다.

![deadlock_detection_recovery.PNG](https://user-images.githubusercontent.com/78094972/167449417-473a5dfa-cfdb-4ec7-9655-6338e5baca3b.PNG)

- **wait-for graph**

  - cycle이 곧 deadlock을 의미한다.
  - 자원 할당 그래프의 변형
  - 프로세스만으로 node 구성
  - Edge 의미

    - Pk → Pj: Pj가 가지고 있는 자원을 Pk가 기다리는 경우
    - R(resource) → P: 이 자원을 P가 소유하고 있다.
    - P -> R(resource): P가 이 자원을 요청한다.
    - 자원의 최대 사용량을 미리 알릴 필요가 없기 때문에, 그래프에 점선이 없다.

- **algorithum**

  - wait-for grpah에 cycle이 존재하는지를 주기적으로 조사
  - O(n^(2))

- **Resource-allocation graph에서 자원을 빼면 coreesponding wait-for graph가 된다.**

<br>

### 4.3.1 Multiple instance per resource type

> deadlock 찾는 방법은 banker’s algorithum과 유사한 방법 활용

![deadlock_detection_recovery_multiple instance.PNG](https://user-images.githubusercontent.com/78094972/167449419-f0d2688c-c311-47fb-b2a9-afd992598822.PNG)

- Deadlock 존재 유무를 판단하기 위해서는 deadlock avoidance와 반대로 매우 보수적인 판단이 아닌, 긍정적인 판단을 해야 한다.

  - 긍정적으로 바라보기 때문에, 각 프로세스는 가지고 있는 자원을 반납할 것이라 본다.

- **Deadlock 확인하기**

  1. 가용자원(Avaoilable)이 몇 개 있는지 확인한다.
  2. 요청하지 않은 프로세스의 자원도 가용자원으로 합친다.
  3. 합친 가용자원으로 처리 가능한지 확인한다.
  4. 처리 후 처리된 프로세스의 자원도 합쳐서, 모든 프로세스가 끝낼 수 있는지 확인한다.

- Deadlock detection과 recovery도 overhead가 크다.

<br>

### 4.3.2 Recovery

- **Process termination**

  - abort **_all_** deadlocked processes
  - abort **_one process at a time_** until the deadlock cycle is eliminated

- **Resource Preemption**
  - 비용을 최소화할 victim으로 선정
  - safe state로 rollback하여 process를 restart
  - starvation 문제
    - 동일한 프로세스가 계속해서 victim으로 선정되는 경우
    - cost factor에 rollback 횟수도 같이 고려

<br>

---

## 4.4 Deadlock Ignorance

> Deadlock이 일어나지 않는다고 생각하고, 아무런 조치도 취하지 않는 방식

- Deadlock이 매우 드물게 발생하므로, deadlock에 대한 조치 자체가 더 큰 overhead일 수 있다.
- 만약 시스템에 deadlock이 발생한 경우, 직접 process를 죽이는 등의 방법으로 대처한다.
  - 만약 한 번에 deadlock의 원인이 되는 process를 죽이면 효율적이지만, 원인이 되는 process가 죽을 수도 있다.
- UNIX, Windows 등 대부분의 범용 OS가 채택하는 방식이다.
  <br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
