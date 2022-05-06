# 0. Introduction

> 1. []()
> 2. []()
> 3. []()

<br>

- 해당 내용은 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e) 강의만 보고 정리한 내용이다.
- [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 책에는 있지 않은 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 9장 교착 상태 (Deadlock)

Deadlock_1

![Deadlock_1.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a6834e24-1e03-4a2c-bbb9-45280fc4f125/Deadlock_1.png)

- 누군가가 희생하지 않으면 교착 상태는 발생하지 않는다.
- 각자 일부 자원을 가지고 있으면서, 상대방이 가지고 있는 걸 요구하는 상황

# The Deadlock Problem

- Deadlock
  - 일련의 프로세스들이 서로가 가진 자원을 기다리며 block된 상태
- Resource (자원)
  - 하드웨어, 소프트웨어 등을 포함하는 개념
  - (예) I/O device, CPU cycle, memory space, semaphore 등
  - 프로세스가 자원을사용하는 절차
    - Request(요청) → Allocate(할당) → Use(사용) → Release(반납)
- Deadlock Example 1
  - 시스템에 2개의 tape drive가 있다.
  - 프로세스 P1과 P2 각각이 하나의 tape drive를 보유한 채 다른 하나를 기다리고 있다.
- Deadlock Example 2
  - Binary semaphore A and B
    - Po: P(A) → P(B)
    - P1: P(B) → P(A)
    - Po는 A를 획득한 후, B를 얻고 싶어한다. P1은 반대다.
    - 서로 반대 것을 가지고 있기 때문에, Deadlock 상황이다.

# Deadlock 발생의 4가지 조건

4가지 조건을 다 만족해야 deadlock이 발생한다.

- Mutal exclusion (상호 배제)
  - 매 순간 하나의 프로세스만이 자원을 사용할 수 있다. (독점적 사용)
- No preemption (비선점)
  - 프로세스는 자원을 스스로 내어놓을 뿐 강제로 빼앗기지 않는다.
- Hold and wait
  - 자원을 가진 프로세스가 다른 자원을 기다릴 때, 보유 자원을 놓지 않고 계속 가지고 있다.
- Circular wait
  - 자원을 기다리는 프로세스 간에 사이클이 형성되어야 한다.
  - 프로세스 Po, P1, .... ,Pn이 있을 때
    - Po는 P1이 가진 자원을 기다린다.
    - P1은 P2가 가진 자원을 기다린다.
    - Pn-1은 Pn이 가진 자원을 기다린다.
    - Pn은 Po이 가진 자원을 기다린다.

# Resource - Allocation Graph (자원할당그래프)

Deadlock 발생하는지 알기 위해서 이 그래프를 그려본다.

resource_allocation_graph.png

![Resource_allocation_graph.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0ee4dbfc-df13-4b6e-97f7-01460a158c33/Resource_allocation_graph.png)

- Vertex
  - Process P = {P1. P2, ...., Pn}
  - Resource R = {R1, R2,...,Rm}
- Edge
  - request edge P1 → Rj
  - assignment edge Rj → Pi
- R → P: 이 프로세스가 해당 자원을 가지고 있다.
- P → R: 이 프로세스가 이 자원을 요청하고 있지만, 아직 획득하지 못했다.
- 자원의 점은 instance를 의미

그러면 그래프에 deadlock이 있는지 어떻게 알 수 있을까?

resource_allocaiton_graph_1

![Resource_allocation_graph_1.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d2fb756e-2497-4d36-9539-31b807c75d8a/Resource_allocation_graph_1.png)

- 그래프에 cycle이 없으면 **deadlock** 이 아니다.
- 그래프에 cycle이 있으면
  - if **only one instance** per resource per resource type, then **deadlock**
  - if serveral instance per resource type, possibility of deadlock
- 왼쪽은 deadlock이고, 오른쪽은 deadlock의 가능성은 있으나, 아니다.
  - 오른쪽의 P4가 다 사용하고 반납하면 P3는 이용가능해진다. P2 또한 다 사용하고 반납하면 P1이 사용가능하다.
  - 자원에 여러 개의 instance가 존재하기 때문에 deadlock이 아니다.

# Deadlock 처리 방법

위로 올라갈수록 강한 방법이다.

- Deadlock Prevention
  - 자원 할당 시, deadlock의 4가지 필요 조건 중 어느 하나가 만족되지 않도록 하는 것
- Deadlock Avoidance
  - 자원 요청에 대한 부가적인 정보를 이용해서 deadlock의 가능성이 없는 경우에만 자원을 할당
  - 시스템 state가 원래 state로 돌아올 수 있는 경우에만 자원 할당
- Deadlock Detection and recovery
  - Deadlock 발생은 허용하되, 그에 대한 detection routine을 두어 deadlock 발견시 recover
- Deadlock Ignorance
  - Deadlock을 시스템이 책임지지 않는다.
  - Unix를 포함한 대부분의 OS가 채택
  - Deadlock은 빈번히 발생하는 문제가 아니기 때문에, 이를 방지하기 위해 오히려 많은 overhead가 발생하기 때문에, deadlock을 방지하는 방식을 택한다.

# Deadlock 처리 방법 첫 번째: deadlock prevention

> **제일 강력한 방법**

- Mutual Exclusion: 자원을 배타적으로 사용해야 한다.
  - 공유해서는 안되는 자원의 경우, 반드시 성립해야 한다.
- Hold and wait: 자신의 자원을 반납하지 않으면서 자원 받기를 원하는 것
  - 프로세스가 자원을 요청할 때, 다른 어떤 자원도 가지고 있지 않아야 한다.
    - 방법 1: 프로세스 시작 시, 모든 필요한 자원을 할당받는 방법
      - 다 사용하고 나서 자원을 반납한다.
    - 방법 2: 자원이 필요할 경우, 보유 자원을 모두 놓고 다시 요청
      - hold한 자원을 다 뱉는다.
  - 자진해서 반납하여 해결하는 것
- No Preemption: 자원을 뺏을 수가 없어서 deadlock이 발생되는데, 자원을 선점할 수 있게 하는 것
  - Process가 어떤 자원을 기다려야 하는 경우, 이미 보유한 자원이 선점된다.
  - 모든 필요한 자원을 얻을 수 있을 때, 그 프로세스는 다시 시작된다.
  - state를 쉽게 save하고, restore할 수 있는 자원에서 주로 사용(CPU, memory)
- Circular wait 막기
  - 모든 자원 유형에 할당 순서를 정하여 정해진 순서대로만 자원 할당
  - 예를 들어 순서가 3인 자원 Ri를 보유 중인 프로세스가 순서가 1인 자원 Rj를 할당받기 위해서는 우선 Rj를 release해야 한다.

생기지도 않을 수 있는 이런 제약들로 인해서 다음과 같은 문제점을 낳기 때문에, 잘 사용하지 않는다.

⇒ Utilization 저하, throughout 감소, starvation 문제

# Deadlock 처리 방법 두 번째: deadlock avoidance

프로세스가 시작될 때 평생에 쓸 자원량을 알고 있다고 가정하여 데드락을 피해간다.

어떤 프로세스가 자원요청을 했을 때, 데드락을 발생할 가능성이 있으면, 애시당초 자원을 할당하지 않는다.

- Deadlock avoidance
  - 자원 요청에 대한 부가정보를 이용해서 자원 할당이 deadlock으로부터 안전(safe)한지를 동적으로 조사해서 안전한 경우에만 할당
  - 가장 단순하고 일반적인 모델은 프로세스들이 필요로 하는 각 자원별 최대 사용량을 미리 선언하도록 하는 방법
- safe state
  - 시스템 내의 프로세스들에 대한 safe sequence가 존재하는 상태
- safe sequence
  - 프로세스의 sequence <P1,P2,....,Pn>이 safe하려면 Pi(1≤i ≤ n)의 자원요청이 “가용 자원 + 모든 Pj(j<i)이 보유자원”에 의해 충족되어야 한다.
  - 조건을 만족하면 다음 방법으로 모든 프로세스의 수행을 보장
    - Pi의 자원 요청이 즉시 충족될 수 없으면 모든 Pj(j<i)가 종료될 때까지 기다린다.
    - P\_(i-1)이 종료되면 Pi의 자원 요청을 만족시켜 수행한다.

## Resource Allocation Graph algorithum: 자원 당 인스턴스 하나

- **Claim edge** Pi → Rj
  - 프로세스 Pi가 자원 Rj를 미래에 요청할 수 있음을 뜻한다. (점선으로 표시한다)
  - 프로세스가 해당 자원 요청 시 request edge로 바뀐다(실선) 소유하고 있다.
  - Rj가 release되면 assignment edge는 다시 claim edge로 바뀐다.
- request edge의 assignment edge 변경 시( 점선을 포함하여 ) cycle이 생기지 않는 경우에만 요청 자원을 할당한다.
- Cycle 생성 여부 조사 시, 프로세스의 수가 n일 때 O 시간이 걸린다.

resoure allocation graph algorithum

![Resource_allocation_graph_algorithum.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f02a7316-827f-4c20-9ea8-fc0f377c2095/Resource_allocation_graph_algorithum.png)

## Banker’s Algorithum: 자원 당 인스턴스 2개 이상

- 가정
  - 모든 프로세스는 자원의 최대 사용량을 미리 명시
  - 프로세스가 요청 자원을 모두 할당받은 경우, 유한 시간 안에 이들 자원을 다시 반납
- 방법
  - 기본 개념: 자원 요청 시, safe 상태를 유지할 경우에만 할당
  - 총 요청 자원의 수가 가용 자원의 수보다 적은 프로세스를 선택
    - 그런 프로세스가 없으면 unsafe 상태
  - 그런 프로세스가 있으면 그 프로세스에게 자원을 할당
  - 할당받은 프로세스가 종료되면 모든 자원을 반납
  - 모든 프로세스가 종료될 때가지 이러한 과정 반복\

![Banker_algorithum.PNG](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7b2edf95-0325-4c30-83af-40c9d4d34ac3/Banker_algorithum.png)

Need: 추가로 요청 가능한 양

<br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
