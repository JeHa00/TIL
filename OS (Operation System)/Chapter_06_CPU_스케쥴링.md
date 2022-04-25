# 0. Introduction

> 1. [Bound process](#1-bound-process)
> 2. [CPU 스케쥴러](#2-cpu-스케쥴러)
> 3. [Dispatcher](#3-dispatcher)
> 4. [스케쥴링의 성능 척도](#4-스케쥴링의-성능-척도)
> 5. [스케쥴링 알고리즘](#5-스케쥴링-알고리즘)
> 6. [스케쥴링 알고리즘의 평가](#6-스케쥴링-알고리즘의-평가)

<br>

- 해당 내용은 [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 와 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)를 보고 정리한 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

## 1. Bound process

<br>

### 1.1 CPU란??

- **CPU(Central Processing Unit): PC(Program Counter)가 가리키는 주소의 기계어 명령을 실제로 수행하는 컴퓨터 내의 중앙처리장치**
  - PC: Program Counter로, 레지스터의 한 종류로서 현재 CPU에서 수행할 프로세스의 코드의 메모리 주소값을 가지고 있다.

<br>

### 1.2 기계어 명령의 종류

- **CPU에서 수행하는 기계어 명령어의 종류를 알아보자.**

- **1. CPU 내에서 수행되는 명령어**

  - Add 명령어: CPU 내의 레지스터에 있는 두 값을 더해 레지스터에 저장하는 명령어
  - CPU 내에서만 수행되므로 명령의 수행 속도가 매우 빠르다.

- **2. 메모리 접근을 필요로 하는 명령어**

  - Load 명령어: 메모리에 있는 데이터를 CPU로 읽어들이는 명령어
  - Store 명령어: CPU에서 계산된 결과값을 메모리에 저장하는 명령어
  - 1번보다 느리지만, 비교적 짧은 시간에 수행 가능하다.

- **3. 입출력 동반 명령어**

  - 입출력 작업(I/O 작업)이 필요한 경우, 사용하는 명령어
    - ex) 키보드로부터 입력을 받기, 화면에 출력하기
  - 입출력 수반 명령은 1번과 2번에 비해 대단히 오랜 시간이 걸린다.
  - 입출력 작업은 특권 명령으로 규정해서 user program이 직접 수행할 수 없고, OS를 통해서 서비스를 대행하도록 한다.

- **각 명령어 수행 속도 비교: 3번 > 2번 > 1번**

- **특권 명령과 일반 명령으로 분류**
  - 특권 명령: 3번
  - 일반 명령: 1번과 2번

<br>

### 1.3 CPU burst와 I/O burst

- **user program이 실행되는 과정은 CPU 작업 과 I/O 작업의 반복이다.**

- **즉, CPU burst 와 I/O burst가 번갈아 실행된다.**

  - CPU burst(버스트): user program이 CPU만 연속적으로 사용하여 instruction만 실행하는 일련의 단계 -> user mode
  - I/O burst(버스트): I/O 요청이 발생해 kernel에 의해 입출력 작업을 진행하는 비교적 느린 단계 -> kernel mode

- **위 2가지를 I/O 작업을 기준으로 분류해보자.**

  - CPU burst: program이 I/O를 한 번 완료한 후, 다음 번 I/O를 수행하기까지 직접 CPU를 가지고 명령을 수행하는 일련의 작업
  - I/O burst: I/O 작업이 요청된 후, 다시 CPU burst로 돌아가기까지 일어나는 일련의 작업

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164410780-5cab4ac8-b72b-430d-8eff-d26a77bf934e.PNG"/></p>

<br>

### 1.4 Bound process: CPU & I/O

- **각 program마다 CPU burst와 I/O burst의 비율이 균일하지 않다.**
- **그래서 CPU bound process와 I/O bound process로 나눠볼 수 있다.**

  - CPU bound process: 계산 위주의 jb

    - few very long CPU bursts
    - 입출력 작업 없이 CPU 작업에 소모하는 계산 위주의 프로그램이 해당된다.

  - I/O bound process: CPU를 잡고 계산하는 시간보다 I/O에 많은 시간이 필요한 job

    - Many short CPU bursts
    - 대화형 프로그램(interactive prgram)에 해당
    - 즉, 사용자에게 입력을 받아 CPU 연산을 수행하여 그 결과를 다시 출력하는 작업에 해당

<br>

### 1.5 CPU sheduling이 필요한 이유

- **여러 종류의 process(=job)이 동일한 시스템 내부에서 섞여 있기 때문에, CPU scheduling이 필요하다.**

  - I/O는 interactive job으로서 적절한 response 필요하다.
  - CPU와 I/O 장치 등 시스템 자원을 골고루 효율적으로 사용

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164412061-026db4a6-462c-40f8-8ae9-128ab40b7170.PNG"/></p>

- **특히, 이 CPU는 한 시스템 내에 하나 밖에 없으므로, 시분할 시스템에서 매우 효율적으로 관리해야 한다.**

- **대부분의 짧은 CPU burst + 극히 일부분의 긴 CPU burst**

  = 대부분 CPU를 오래 사용하기보다는 잠깐 사용하고, I/O 작업을 수행하는 process들이 많다.  
   = CPU busrt가 짧은 process는 대부분 대화형 작업이다.
  = CPU 스케쥴링을 할 때, CPU burst가 짧은 process에게 우선적으로 CPU를 사용할 수 있도록 하는 스케쥴링이 필요

- **그래서, I/O bound process의 우선순위를 높이는 것이 바람직한다.**
  - I/O bound process에게 늦게 주면 사용자는 답답함을 느낀다.

<br>

---

## 2. CPU 스케쥴러

- CPU 스케쥴러란?? **ready state에 있는 procese 중에서 이번에 CPU를 줄 프로세스를 결정하는 OS의 code**

  - HW가 아닌, os의 code 중 이 기능을 하는 부분을 CPU 스케쥴러라 부르는 것이다.

- **CPU 스케쥴링이 필요한 경우**

  1. I/O 요청 system call에 의해 running에서 blocked로 바뀐 경우
  2. Timer interrupt에 의해 running에서 ready로 바뀐 경우
  3. I/O 작업 요청으로 blocked 상태였던 process가 I/O 작업 완료에 의해 devce controller가 interrupt 발생하여 ready 상태로 바뀐 경우
  4. running 상태에 있는 프로세스가 종료(terminate)되는 경우

- **CPU 스케쥴링 방식 2가지: 비선점형(non-preemptive) 과 선점형(preemptive)**
  - 비선점형(preemptive): process가 작업완료 후, 자발적으로 CPU를 반납하는 방식 -> 1번과 4번
  - 선점형(preemptive): CPU를 계속 사용하기 원해도, 강제로 빼앗는 방법 -> 2번과 3번
    - ex) timer interrupt

<br>

---

## 3. Dispatcher

- Dispatcher란?? **CPU scheduler에 의해 새롭게 선택된 프로세스가 CPU를 할당받아 작업을 수행하도록 환경설정을 하는 OS의 code**

  - HW가 아닌, os의 code 중 이 기능을 하는 부분을 CPU 스케쥴러라 부르는 것이다.

- **Dispatcher 과정**

  - 현재 수행 중이던 process context를 이 process의 PCB에 저장한다.  
    -> 새로운 process의 PCB를 복원  
    -> user mode로 전환하여 CPU를 넘긴다.  
    -> 복원된 context의 program counter로 현재 수행할 주소를 찾는다.

- **Dispatch latency (디스패치 지연시간): 디스패치가 하나의 프로세스를 정지시키고 다른 프로세스에게 CPU를 전달하기까지 걸리는 시간**

  - Dispatcher 과정에서 1번부터 3번까지 걸린 시간
  - context switching의 overhead에 해당

<br>

---

## 4. 스케쥴링의 성능 척도

- **스케쥴링의 성능을 평가하기 위해 여러 지표들이 사용된다.**

  - 시스템 관점의 지표: CPU 이용률, 처리량(throughput)
  - 사용자 관점의 지표: 소요시간, 대기시간, 응답시간

- **시스템 관점의 지표**

  1. CPU 이용률(CPU utilization): 전체 시간 중 CPU가 일을 한 시간

     - 휴면 상태(idle)에 머무르는 시간을 최대한 줄이는 것이 CPU 스케쥴링의 중요한 목표

  2. 처리량(throughput): 주어진 시간 동안 ready queue에서 CPU burst를 완료한 프로세스의 개수

     - CPU burst가 짧은 process에게 할당할수록 증가한다.

- **사용자 관점의 지표**

  1. 소요시간(turnaround time): process가 CPU를 요청한 시점부터 자신이 원하는 만큼 CPU를 다 쓰고, CPU burst가 끝날 때까지 걸린 시간

     - 대기시간(waiting time) + 실제로 CPU를 이용한 시간의 합

  2. 대기시간(waiting time): CPU burst 기간 중 process가 ready queue에서 CPU를 얻기 위해 기다린 시간의 합

     - CPU burst 동안, CPU를 얻기 잃는 걸 반복한다.

  3. 응답시간(response time): process가 ready queue에 들어온 후, 첫 번째 CPU를 획득하기까지 기다린 시간

     - 사용자 응답하는 대화형 시스템에서 적합한 성능 척도
     - 사용자 관점 지표에서 가장 중요
     - timer interrupt가 빈번할수록 응답시간 감소

- **생활 속의 비유**: 중국집
  - 이용률과 처리량 -> 중국집 입장에서의 척도
    - 이용률: 전체 시간 중 주방장이 일한 시간의 비율
    - 처리량: 주방장이 주어진 시간 동안 몇 명의 손님에게 요리를 만들어주었는지
    - 중국집 입장에서는 주방장을 고용해서 가능한 많은 일을 시키는 것이 좋으므로, 이용률이 높은 것을 선호한다.
  - 소요시간, 대기시간, 응답시간 -> 손님 입장에서의 척도
    - 소요시간: 손님이 중국집에 들어와서 주문한 음식을 다 먹고 나가기까지 소요된 총 시간
    - 대기시간: 각각의 음식이 나오기까지 기다린 시간을 합한 것
    - 응답시간: 최초의 음식이 나오기까지 기다린 시간

<br>

---

## 5. 스케쥴링 알고리즘

<br>

### 5.1 선입선출 스케쥴링(FCFS: First-Come First-Served)

> - process가 ready queue에 도착한 순서대로 CPU를 할당하는 방식.
> - 비선점형이다.

- FCFS 스케쥴링은 먼저 도착한 프로세스의 성격에 따라 평균 대기시간이 크게 달라진다.

  - CPU burst가 긴 프로세스가 먼저 도착할 경우: 평균 대기시간이 길어진다. (Conboy effect)
  - CPU burst가 짧은 프로세스가 먼저 도착할 경우: 평균 대기시간이 짧아진다.

- **단점**

  - **콘보이 현상(Convoy effect)**: CPU burst가 긴 process가 짧은 process보다 먼저 도착하여 오랜 시간을 기다려야하는 현상으로, 평균 대기시간이 길어진다.
    - FCFS의 대표적인 단점

- 예시
  | 프로세스 | CPU burst 시간 |
  | --- | --- |
  | P1 | 24 |
  | P2 | 3 |
  | P3 | 3 |

  - 들어온 순서가 P1,P2,P3 일 때

    - 대기시간: P1 = 0, P2 = 24, P3 = 27
    - 평균 대기시간: (0 + 24 + 27 ) / 3 = 17
    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164449468-3bd69ed3-a8db-4080-b100-04cd83aa3c5f.PNG"  width = '400' height ='200'/></p>

  - 들어온 순서가 P2, P3, P1 일 때
    - 대기시간: P1 = 6, P2 = 0, P3 = 3
    - 평균 대기시간: (6+0+3)/3 = 3
    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164449789-cc9d22d7-f221-4f83-9f6b-bc897e191404.PNG"  width = '400' height ='200'/></p>

<br>

### 5.2 최단작업 우선 스케쥴링(Shortest-Job First: SJF)

> - CPU burst가 가장 짧은 process에게 제일 먼저 CPU를 할당하는 방식
> - 평균 대기시간을 가장 짧게 하는 최적 알고리즘(optimal algorithum)이지만 최고의 알고리즘은 아니다.

- **SJF algorithum의 방식: 비선점형(non-preemptive) 과 선점형(preemptive)**

- **효율적이지만, 형평성을 간과한 스케쥴링**

  - 비선점형(preemptive): 프로세스가 CPU를 자진 반납하기 전까지는 CPU를 빼앗지 않는 방식
  - 선점형(preemptive): ready queue에서 CPU burst가 가장 짧은 process에게 CPU를 할당했어도, 더 짧은 process가 도착할 경우, CPU를 빼앗아 더 짧은 process에게 부여하는 방식
    - **SRTF**(Shortest Remaining Time First)라고도 한다.
    - process들이 ready queue에 도착시간이 불규칙한 환경에서는 선점형이 평균 대기시간을 최소화하는 최적의 알고리즘이 된다.

- **SJF의 선점형 첫 번째 문제점: 기아 현상(starvation)**

  - 기아 현상(starvation): CPU burst가 짧은 process가 계속 도착할 경우, 한 process는 영원히 CPU를 할당받지 못하는 현상

- **SJF의 두 번째 문제점: 현실적으로 미리 알 수 없는 CPU burst 시간**

  - 하지만 과거의 data를 통해서 예측할 수 있다.

- 예시

  - 비선점형

  <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164451643-b6bc61bc-ea3f-4b9f-9097-1f088050736a.PNG"  width = '400' height ='200'/></p>

  - 선점형
  <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164451957-43b6277b-830a-4f09-819f-6c3d375989bb.PNG"  width = '400' height ='200'/></p>

<br>

### 5.3 우선순위 스케쥴링(Priority scheduling)

> - ready queue에서 기다리는 process 중 우선선위가 가장 높은 process에게 제일 먼저 CPU를 할당하는 방식
> - 우선순위는 우선순위값(priority number)을 통해 표시하며, 작을수록 높은 우선순위를 가지는 것으로 가정한다.

- **우선순위 스케쥴링도 비선점형 방식과 선점형 방식으로 각각 구현할 수 있다.**

- **SJF도 우선순위 스케쥴링의 한 종류다.**

  - 왜냐하면, CPU burst 시간을 우선순위값으로 정의하며 우선순위 스케쥴링은 SJF 알고리즘과 동일하다.

- **Problem: 우선순위 스케쥴링도 _기아 현상(starvation)_ 문제점이 있다.**

- **Solution: _노화 기법(aging)_ 을 사용한다.**
  - 기다리는 시간이 비례하여 우선순위를 높이는 것을 말한다.  
    ex) 버스나 지하철에서 나이 드신 분께 자리를 양보하는 것과 동일.

<br>

### 5.4 라운드 로빈 스케쥴링(Round Robin Scheduling)

> - 시분할 시스템의 성질을 가장 잘 활용한 스케쥴링 방식
> - 각 프로세스가 연속적으로 CPU를 사용할 수 있는 시간이 제한되며, 이 시간이 경과하면 CPU를 회수해 ready queue에 줄 슨다.

- **현대 CPU 스케쥴링의 기반 + CPU 설명의 기반 스케쥴링: 라운드 로빈 스케쥴링**

- **각 프로세스가 연속적으로 CPU를 사용할 수 있는 시간: 할당 시간(time quantum)**

  - 규모: 수십 밀리초 정도의 규모
  - 할당시간이 지나면 timer interrupt가 발생
  - CPU 사용 시간이 할당 시간보다 짧으면 스스로 반납한다.
  - 할당 시간이 너무 짧으면 문맥교환의 오버헤드가 증가하여, 전체 시스템 성능이 저하된다.

- **_대화형 프로세스의 빠른 응답 시간(response time)을 보장할 수 있다._**

- **라운드 로빈 스케쥴링의 기본적인 목적: 공정성**

  - CPU burst 시간이 짧은 프로세스가 빨리 CPU를 얻고, 동시에 CPU burst 시간이 긴 프로세스가 불이익 X
  - CPU를 사용하고자 하는 양에 비례하여 소요시간이 증가하므로 공정하다.

- **Round robine과 다른 scheduling 비교**

  - SJF와의 비교: SJF보다 평균 turnaround time이 길지만, response time은 더 짧다는 것이 중요한 장점이다.

  - FCFS와의 비교: 할당시간을 크게 하면 FCFS와 동일

    - **CPU 버스트 시간이 동일한 프로세스들일 경우,**

      - FCFS: CPU를 먼저 쓰고 나가는 프로세스의 소요시간 및 대기시간이 짧아진다.
      - Round robine: CPU를 조금씩 같이 쓰고, 거의 동시에 끝나게 되어 소요시간 및 대기시간이 가장 오래 기다린 프로세스에 맞춰진다.
      - 따라서 Round robine 스케쥴링은 FCFS의 평균 대기시간 및 평균 소요시간이 FCFS보다 거의 두 배로 더 길어진다.

    - **하지만, CPU burst 시간이 균일하지 않은 경우가 대부분이기 때문에, Round robine은 FCFS보다 합리적**

  <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164458802-d87defe3-7159-4e00-aaa8-6c97c05e8acc.PNG"  width = '400' height ='200'/></p>

<br>

### 5.5 멀티레벨 큐(Multi-level queue)

> - ready queue를 여러 개로 분할해 관리하는 스케쥴링 기법
> - 공정하지 않은 알고리즘이지만, 우선순위가 높은 프로세스가 더 빨리 CPU를 얻어야 하기 때문이다.

  <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164484103-7ed46059-0a87-4a76-b132-55a28eaf7623.png"/></p>

- **이 기법의 경우, 다음과 같은 문제점이 발생한다.**

  - 이 기법의 경우, 어떤 줄에 서 있는 프로세스를 우선적으로 스케쥴링할 것인가??
  - 프로세스가 도착했을 때, 어느 줄에 세워야할지 결정하는 메커니즘 필요

- **첫 번째 문제에 대한 해결책: 프로세스의 성격에 맞는 스케쥴링을 사용한다.**

  - 전위 큐(foreground queue): 대화형 작업(interactive job)을 담기 위한 전위
    -> 응답시간을 짧게 하기 위해 Round robin scheduling 사용
  - 후위 큐(background queue): 계산 위주의 작업을 담기 위한 후위
    -> 응답 시간이 큰 의미를 가지지 않기 때문에, 그리고 context switching overhead를 줄이기 위해 FCFS 사용

- **두 번째 문제에 대한 해결책: 고정 우선순위 방식(fixed priority scheduling)**

  - Fixed priority scheduling(고정 우선순위 방식)
    - Queue에 고정적인 우선순위를 부여하는 방식
      - 우선순위가 높은 큐를 먼저 서비스 -> 낮은 큐는 우선순위가 높은 큐가 비어있을 때만 서비스 실행.
    - 즉, 전위 큐에 있는 프로세스에게 우선적으로 CPU를 부여하고, 전위 큐가 비어 있는 경우에만 후위 큐에 있는 프로세스에게 CPU를 할당한다.
    - 하지만, _starvation_ 이 발생할 수 있다.

- **두 번재 문제에 대한 또 다른 해결책: time slice**
  - 각 queue에 CPU 시간을 적절한 비율로 할당
    - ex) RR인 전위 큐: 80% , FCFS인 후위 큐: 20%

<br>

### 5.6 멀티레벨 피드백 큐(Multi-level Feedback Queue)

> 멀티레벨 큐와 거의 다 동일하나, 차이점은 process가 하나의 queue에서 다른 큐로 이동이 가능하다.  
> 즉, 프로세스의 우선순위가 바뀔 수 있다.

  <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/164484385-69a85293-56f5-455a-b08b-1611d24335d2.PNG"/></p>

- **우선순위 스케쥴링의 aging 기법을 멀티레벨 피드백 큐 방식으로 구현하면,**

  - 기다렸으면 우선순위가 낮은 큐에서 높은 큐로 승격시키는 방식이다.
  - 차근 차근 시간을 늘려 때문에, CPU 사용 시간을 예측할 필요가 없다.

- **멀티레벨 피드백 큐를 정의하는 요소들**

  - 큐의 수
  - 각 큐의 스케쥴링 알고리즘
    - 프로세스를 상위 큐로 승격시키는 기준
    - 프로세스를 하위 큐로 강등시키는 기준
    - 프로세스가 도착했을 때, 들어갈 큐를 결정하는 기준 등등

- **멀티레벨 피드백 큐의 동작 예**
  - 프로세스가 준비 큐에 도착하면 우선순위가 가장 높은 큐(Round robine, 할당시간 8)에 줄을 선다.  
    -> CPU 사용시간이 짧은 대화형 프로세스라면 빨리 서비스 박고 작업완료할 수 있다.  
    -> CPU burst가 긴 process라면 하위 큐(Round robine, 할당시간 16)로 강등시킨다.
    -> 그럼에도 완료하지 못하면 계산위주의 프로세스로 간주하여 최하위 큐인 FCFS scheduling을 적용

<br>

### 5.7 다중처리기 스케쥴링(Multi-processor system)

> multi-processor 상황에서의 scheduling 기법

- 은행창구에서 번호표를 뽑아 기다리는 것처럼 **CPU가 알아서 다음 프로세스를 꺼내가도록 할 수 있다.**

- 하지만, **반드시 특정 CPU가 실행**해야 한다든가 ex) 미용실에서 특정 미용사로 예약한 경우

- **Load sharing**

  - 각 CPU 별 부하가 적절히 분산되도록 하는 매커니즘이 필요하다.

- **다중처리기 스케쥴링의 방식**

  - 대칭형 다중처리(SMP, Symetric Multi-Processing): 모든 CPU가 대등해서 각자 알아서 스케줄링을 결정하는 방식
  - 비대칭형 다중처리(asymmetric multiprogramming): 하나의 CPU가 다른 모든 CPU의 스케줄링 및 데이터 접근을 책임지고, CPU는 거기에 따라 움직이는 방식

<br>

### 5.8 실시간 스케쥴링(real-time system)

> 정해진 시간(dead line) 이내에 처리해야만 하는 스케줄링

- **경성 실시간 시스템(Hard real-time system)과 연성 실시간 시스템(soft real-time system)으로 나눠진다.**
  - 전자는 원자로 제어, 미사일 발사 등 시간을 정확히 지켜야하는 시스템
  - 후자는 데드라인이 존재하지만, 지키지 못했다고 하여 위험한 상황이 발생하지 않는다.

<br>

### 5.9 Thread scheduling

- **Thread를 구현하는 방식 2가지**
  - Local Scheduling (by user process)
    - User level thread의 경우, process가 thread를 직접 관리하고 OS는 thread의 존재를 모른다.
    - 그래서 OS는 이 thread에게 CPU를 줄지 결정한다.
    - 그리고, process 내부에서 어떤 thread에게 줄지를 결정한다.
  - Global Scheduling (by OS)
    - Kernel level thread의 경우, 일반 프로세스와 마찬가지로 커널의 단기 스케쥴러가 어떤 thread를 스케줄할지 결정
    - 즉, OS가 thread의 존재를 인지한다.

<br>

---

## 6. 스케쥴링 알고리즘의 평가

- **스케쥴링 알고리즘의 성능을 평가하는 방법에는 큐잉모델(Queuing model), 구현 및 실측(Implementation & measrrement), 시뮬레이션(Simulation)가 있다.**
  - Queueing model: 이론가들이 수행하는 방식
    - 수학적 계산을 통해 performance index(CPU 처리량, Process 평균 대기시간 등)를 구한다.
    - 밑에 방식이 훨씬 많이 사용된다.
  - Implementation & measurement: 이론가가 아닌 구현가들이 수행하는 방식
    - 동일한 program을 원래 kernel과 CPU scheduler code를 수정한 kernel에서 수행한 후, 실행시간을 측정하여 알고리즘을 평가한다.
    - 이 방법이 어려우면 밑에 방법을 사용한다.  
  - Simulation: 가상으로 CPU scheduling program을 작성하는 방식
    - 가상으로 CPU scheduling program을 작성한 후, 프로그램의 CPU 요청을 입력값으로 넣어 어떠한 결과가 나오는지 확인하는 방법
    - 그래서 가상으로 생성된 값과 실제 system에서 추출한 입력값(이를 trace라 한다.)을 비교한다.


<br>

---

# Reference

- [운영체제와 정보기술의 원리](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
- [컴퓨터 엔지니어로 살아남기](https://getchan.github.io/cs/OS_5/)
