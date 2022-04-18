p# 0. Introduction

> 1. []()

<br>

- 해당 내용은 [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 와 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)를 보고 정리한 내용입니다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 1. 프로세스의 개념

## 1.1 Process (프로세스)

- **_is a prgram in execution_**
  - **프로세스 = 실행 중인 프로그램**
  - 디스크에 실행파일 형태로 존재하는 상태(프로그램) → 메모리에 올라감 → 실행
  - 이 실행 중일 때를 process라 한다.

<br>

## 1.2 Process context (프로세스 문맥 )

- **_process의 수행 상태를 정확히 아는데 필요한 모든 요소_**
- **_process conetxt를 알아야 하는 이유???_**
  - CPU는 시분할 시스템으로, timer interrupt에 의해서 여러 process가 돌아가면서 CPU를 사용한다.
  - 이런 상황에서, 한 process가 CPU를 다른 process에게 이양했다가 다시 획득했을 때, `직전 수행 시점의 정확한 상태` 를 재현하기 위해서 필요하다.
- **_Process context의 분류_**

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820432-78db934f-e417-470c-bba1-9b762dcbfe1d.PNG"/></p>

- **Hardware context**
  - Program counter
  - 각종 register
    - 이 register에 저장된 값들
- **Process의 address space**
  - code, data, stack
    - process만의 독자적인 주소 공간
- **process 관련 kernel 상의 문맥**
  - PCB (Process Control Block)
  - Kernel stack
    - OS가 process를 관리하기 위해 유지하는 자료구조들: PCB, kernel stack

<br>

---

# 2. 프로세스의 상태 (Process state)

<br>

## 2.1 Process의 상태도

- **_Process는 다음 상태 중 어느 한 상태에 머무르며, 시간의 흐름에 따라 변한다._**
- **_Process의 상태를 나누는 이유는_** `컴퓨터의 자원을 효율적으로 관리`**_하기 위함_**
- **_Process의 상태도_**

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820864-6ac76cf5-16ca-44cb-921a-81603cd2a278.png"/></p>

  - **Running:**
    - CPU를 잡고 instruction을 수행 중인 상태
  - **Ready:**
    - 다른 조건은 다 만족하고, 메모리에는 올라와 CPU만 기다리는 상태
  - **Blocked( wait, sleep ):**
    - CPU를 할당받아도 당장 instruction을 수행할 수 없는 상태
    - process 자신이 요청한 even가 즉시 만족되지 않아 이를 기다리는 상태
      - ex) disk에서 file을 읽어와야 하는 경우 (I/O 작업)
  - **New**:
    - process가 시작되어 자료구조는 생성되었지만, 메모리 획득을 승인받지 못한 상태
  - **Terminated:**
    - execution(실행)이 끝났지만, 자료 구조 정리는 완료하지 못한 상태

- **_Dispatch:_**
  - CPU를 할당받을 process를 선택한 후, 실제로 CPU의 제어권을 넘겨받는 과정

<br>

## 2.2 Process 상태 변화 예시

- **_입출력을 요청한 프로세스의 상태 변화_**
  **Running state**
  - A process가 CPU를 할당 받아 기계어 명령을 하나씩 수행  
    **→ I/O 요청**
  - 파일의 내용을 disk에서 읽어와야 명령이 진행될 수 있으므로, 입출력 요청을 한다.  
    **→ Blocked state**
  - 입출력 요청이 완료될 때까지 CPU를 반환한 다음, disk 입출력 서비스를 기다리며 봉쇄 상태로 바뀐다.
  - 그리고, 해당 process는 device I/O queue 뒤에 줄슨다.  
    **→ Ready state의 process 중 선정**
  - CPU를 할당받을 process를 선택하기 위해, ready 상태의 process 들 중에서 CPU scheduler가 적절한 process를 하나 선정하여 CPU를 할당한다.  
    **→ Running state**
  - B process가 CPU를 받아 자신의 code를 실행한다.  
    **→ device controller 가 interrupt 발생**
  - I/O 작업을 하던 controller가 interrupt를 발생하여 CPU에게 I/O 작업 완료를 알림  
    **→ B process를 user mode에서 kernel mode 진입**
  - interrupt의 발생 원인이 B process와 상관없어도, CPU가 현재 사용하고 있던 process가 kernel mode로 진입했다고 판단.  
    **→ Ready state**
  - HW interrupt에 의해서 A process를 blocked state에서 ready state로 바꾼 후, CPU의 ready queue에 줄을 세운다.
  - 그리고, device의 local buffer에 있는 내용을 memory로 이동한다.

<br>

---

# 3. 프로세스 제어블록 (Process Control Block)

## 3.1 PCB란 ??

- **_운영체제가 각 process를 관리하기 위해,_**
- **_process 마다 유지하는 정보들을 담는,_**
- **_커널 내의 자료구조_**

<br>
  
## 3.2 PCB의 구성 요소

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820308-930acc88-ce46-40a9-9b49-662d5e4e6364.PNG"/></p>

- **_1) OS가 관리를 위해 사용하는 정보_**
  - Process state, process ID
    - process state: CPU를 할당해도 되는지 여부를 결정하기 위해
    - process ID: 효율적인 관리를 위해 process 마다 매긴 고유 번호
  - scheduling information, priority
- **_2) CPU 수행 관련 HW 값_**
  - program counter: 다음에 수행할 명령의 위치를 가리킨다.
  - registers
- **_3) 메모리 관련_**
  - code, data, stack
- **_4) 파일 관련_**
  - open file descriptors: 입출력 관련 상태 정보

<br>

---

# 4. 문맥교환 (Context switch)

<br>

## Context switch란??

- **_CPU를 한 프로세스에서_** `다른 프로세스`**_로 넘겨주는 과정_**
- **_문맥 교환 중, OS가 실행하는 것들_**

  - CPU를 내어주는 process의 state를 이 process의 PCB에 저장
  - CPU를 새롭에 얻는 process의 state를 PCB에서 읽어온다.

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820300-8838c513-97f3-4790-aef9-fd106932a80b.PNG"/></p>

- **_context switch가 일어나는 경우와 그렇지 않은 경우_**

  - System call이나 interrupt 발생 시, 반드시 문맥교환이 일어나는 게 아니다.

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820304-52c54fbd-6ef6-43a4-8356-1ad303d1bf9b.PNG"/></p>

    - 첫 번째 경우, 단지 같은 process의 mode가 바뀌는 경우
    - 두 번째 경우가 context switch다.
    - 첫 번째 경우도 CPU 수행 정보 등 context의 일부를 PCB에 저장해야 하지만, context switch를 하는 경우, 오버헤드가 훨씬 크다. (eg. cache memory flush)
      - A process의 address space의 code를 실행하다가, kernel address space의 code를 실행하는 것이기 때문에, PCB에 저장해야 한다.

- 문맥교환에 소요되는 시간은 일종의 오버헤드다.
  - 그래서, timer로 CPU 할당시간을 아주 작게 세팅하면 문맥교환이 빈번히 발생하기 때문에, 오버헤드가 상당히 커진다.
  - 하지만, CPU 할당 시간을 너무 크게 설정하면 시분할 시스템의 의미가 퇴색된다.
  - 그러므로, 적절한 CPU 할당시간을 정해야 한다.

<br>

---

# 5. 프로세스를 스케쥴링 하기 위한 큐

<br>

## 5.1 kernel의 process 상태 관리

- process의 상태 관리는 **_kernel의 address space 영역 중 data_** 에 다양한 queue를 두어 수행한다.
- **_process들은 각 queue들을 오가며 수행한다._**

<br>

## 5.2 다양한 queue 종류

   <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820309-3c8a43a4-72cf-4296-8abb-a55e111e8da5.PNG"/></p>

- **_Job queue_**
  - 현재 시스템 내에 있는 모든 프로세스를 관리하기 위한 큐
  - 모든 process가 속한다.
  - ready queue와 device queue가 다 포함된다.
  - ready 큐에 포함하면 device 큐에는 포함되지 않는다.
- **_Ready queue_**
  - 현재 메모리 내에 있으면서 CPU를 잡아서 실행되기를 기다리는 프로세스의 집합
- **_Device queues (장치 큐) = HW queue_**
  - 각 I/O device의 service를 기다리는 process의 집합
  - 각 deivce마다 있기 때문에, 다양하다.
  - 예)
    - device controller가 줄 서 있는 순서대로 I/O 작업 수행 → 작업 완료하면 controller가 interrupt 발생 → interrupt service routine에 의해서 입출력 작업이 완료된 process는 I/O queue에서 나와 CPU대기 queue 슨다.
- **_resource queue = SW resource_**
  - **_SW queue가 필요한 이유??_**
    - SW resource인 공유 데이터에 여러 process가 접근할 경우, `데이터의 일관성 훼손` 이 발생할 수 있다.
    - 그래서, 공유 데이터에는 매 시점 하나의 프로세스만이 접근하도록 한다.
      - SW resource에 접근 중인 process가 다 사용하고 반납할 때까지, 다른 process가 CPU를 할당받았어도 접근하지 말고 공유 데이터 queue에서 기다려야 한다.

<br>

## 5.3 Process scheduling queue의 모습

 <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820315-d2f69068-caa3-410c-bdf1-e7126f93e98e.PNG"/></p>

- **_위 image는 OS가 queue를 어떻게 자료구조로 구현하는지 보여준다._**
- **_queue는 각 process의 PCB를 연결 list 형태로 관리하며, pointer를 사용해 순서를 정한다._**
- **_Queue header_**
  - 큐의 가장 앞부분
  - PCB의 pointer 부분이 이어진다.
- **_queue 흐름 설명_**
  - process가 CPU 할당받고 수행 중, I/O 요청이 발생하면 해당 device의 queue에 줄을 슨다.
  - device queue에 속한 process 들은 blocked state였다가, 해당 장치의 서비스를 받으면, device controller가 interrupt를 발생시켜 ready state로 바뀌어, ready queue로 이동한다.
  - ready queue에는 PCB 7 다음에, PCV 2가 대기하고 있다.
  - magnetic tape에는 아무것도 대기하지 않는다.
  - disk queue에는 PCB 3 ← PCB 14 ← PCB 6 순서로 대기하고 있다.
  - terminal queue에는 PCB 5 만 대기하고 있다.

<br>

---

# 6. 스케쥴러 (Scheduler)

<br>

## 6.1 Long - term scheduler ( 장기 스케쥴러 or job scheduler)

- 시작 프로세스 중 어떤 것들을 **_ready queue_** 로 보낼지 결정
- **process에 memory (및 각종 자원) 을 주는 문제**
  - 메모리를 어느 것에 줄지를 결정
  - 현대의 컴퓨터는 메모리를 기본적으로 바로 준다.
- **degree of Multiprogramming** 제어
  - multi-programming: 메모리에 여러 프로그램이 동시에 올라가는 것을 의미
  - 이 메모리에 올라가는 수를 제어하는 것 → 컴퓨터 성능에 영향을 줌
  - 현대 컴퓨터에는 장기 스케쥴러는 없고, 프로그램이 시작하면 다 ready 상태로 들어간다.
- time sharing system에는 보통 장기 scheduler가 없음 (무조건 ready)

<br>

## 6.2 Short-term schduler (단기 scheduler or CPU scheduler)

- 어떤 프로세스를 다음 번에 **running** 시킬지 결정
- 프로세스에 **CPU** 를주는문제
- 충분히 빨라야 함 (milli-second 단위)

<br>

## 6.3 Medium-Term Scheduler (중기 스케쥴러 or Swapper)

- 여유 공간 마련을 위해 프로세스를 통째로 메모리에서 디스크로 쫓아낸다.
- 프로세스에게서 **memory** 를 뺏는 문제
  - 메모리에 프로그램이 너무 많이 올라가면, 쫓아내어 전체적인 컴퓨터 성능을 개선.
  - 시스템 입장에서는 장기 스케쥴러보다 중기 스케쥴러를 주는 게 더 이득
- **degree of multiprogramming** 을 제어
  - 현재 multi-programming을 제어하는 scheduler
  - 이 중기 스케쥴러가 들어가 있기 때문에, 프로세스의 상태 3가지에 추가된 게 suspended다.

<br>

## 6.4 추가된 프로세스 상태도

 <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163820311-f438ce19-30fe-46b7-aa55-9a0749ab4a70.PNG"/></p>

- 중기 스케쥴러에 의해 suspended state가 추가되었다.
- **_Running_**
  - CPU를 잡고 instruction을 수행 중인 상태
- **_Ready_**
  - CPU를 기다리는 상태( 메모리 등 다른 조건을 모두 만족하고)
- **_Blocked (wait, sleep)_**
  - I/O 등의 event를 스스로 기다리는 상태
  - 예) 디스크에서 file을 읽어와야 하는 경우
- **_Suspended (stopped)_**
  - 외부적인 이유로 **강제로** 프로세스의 수행이 정지된 상태
    - 중기 스케쥴러에 의해 강제로 뺏긴 상태
  - 이 상태의 프로세스는 통째로 디스크에 swap out 된다.
  - 예) 사용자가 프로그램을 일시 정지시킨 경우 (break key). 이 경우에는 사람이 재개시켜야 위의 상태가 된다.
    - 시스템이 여러 이유로 프로세스를 잠시 중단시킴 → 중기 스케쥴러
    - (메모리에 너무 많은 프로세스가 올라와 있을 때)
- **_blocked와 suspended 구분하기_**
  - Blocekd: **자신이 요청한 event가 만족되면(자신이 요청한 작업이 완료되면) Ready**
  - Suspended: **외부에서 정지된 상태이기 대문에, 외부에서 resume 해 주어야 Active**

<br>

---

# Reference

- [운영체제와 정보기술의 원리](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
