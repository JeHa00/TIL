# 0. Introduction

> 1. [프로세스의 개념](#1-프로세스의-개념)
> 2. [프로세스의 상태(Process state)](#2-프로세스의-상태-process-state)
> 3. [프로세스 제어블록(Process Control block)](#3-프로세스-제어블록-process-control-block)
> 4. [문맥교환(Context switch)](#4-문맥교환-context-switch)
> 5. [프로세스를 스케쥴링 하기 위한 큐](#5-프로세스를-스케쥴링-하기-위한-큐)
> 6. [스케쥴러(Scheduler)](#6-스케쥴러-scheduler)
> 7. [프로세스의 생성](#7-프로세스의-생성)
> 8. [프로세스 간의 협력](#8-프로세스-간의-협력)
> 9. [Thread](#9-thread)

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

# 7. 프로세스의 생성

<br>

## 7.1 Process creation (프로세스 생성) : COW(Copy-On-Write)

- **OS가 process를 전부 생성하는 게 아닌, 부팅 후 최초의 process는 운영체제가 직접 생성한다. 그 다음부터는 이미 존재하는 process가 다른 process를 복제 생성한다.**
- **process를 생성하는 process를 `부모 프로세스`라 하고, 생성된 process를 `자식 프로세스`라 한다.**
  - 부모 프로세스 1개가 자식 프로세스 최소 1개를 **복제 생성** 한다.
  - 또한, 자식 프로세스가 또 process를 생성할 수 있다.
  - 프로세스의 트리(계층 구조) 형성
- **작업 수행을 위한 자원**
  - 부모 프로세스는 OS로부터 받는다.
  - 자식 프로세스는 부모 프로세스와 **공유** 한다.
    - 부모와 자식 프로세스가 **서로 모든 자원을 공유** 하는 모델
    - **일부를 공유** 하는 모델
    - 전혀 공유하지 않는 모델
- **주소 공간 (Address space)**
  - process 생성의 첫 번째: 부모 공간을 복사 → 두 번째: 복사한 공간에 새로운 프로그램의 주소 공간을 덮어씌운다.
- **Process 와 관련한 system call (특권 명령 )**
  - fork() : create a child (copy)
  - exec() : overlay new image = 새로운 프로그램으로서 덮어씌운다.
  - wait() : sleep until child is done
  - exit() : frees all the resources, notify parent
- UNIX의 예
  - os에게 fork() system call 요청하여, 새로운 process를 생성
    - 부모를 그대로 복사하고, 주고 공간을 할당
    - **복사할 때, 부모 프로세스의 process ID는 제외한다.**
  - fork () 다음에 이어지는 exec () system cal을 통해 새로운 프로그램을 메모리에 올린다.
  - fork () 와 exec () 둘 다 system call을 통해서 실행되므로, 운영체제가 생성한다.

<br>

## 7.2 Process Termination (프로세스 종료)

- **프로세스가 마지막 명령을 수행한 후, 운영체제에게 이를 알려준다. (’exit’ system call)**
  - 자식이 부모에게 output data를 보낸다. (via ‘**wait’ system call**)
  - 프로세스의 각종 자원들이 운영체제에게 반납된다.
  - 자식 프로세스가 먼저 종료 후 부모 프로세스가 종료되야 한다.
- **부모 프로세스에게 자식의 수행을 종료시킨다. (abort)**
  - 자식이 할당 자원의 한계치를 넘어설 때
  - 자식에게 할당된 task가 더 이상 필요하지 않을 때 (자식 프로세스를 만든 이유가 일을 시키기 위함이기 때문)
  - 부모가 종료(exit)할 때
    - 운영체제는 부모프로세스가 종료하는 경우, 자식이 더 이상 수행되도록 두지 않는다.
    - 단계적인 종료( 손자 → 자식 → 부모 )가 지켜져야 한다.

<br>

## 7.3 fork () system call

- **creats a new address space that is a duplicate of the caller**
- **자식 process를 만들 때, 부모 process의 program counter까지 복사된다.**
  - 부모 process와 자식 process의 차이는 **식별자** 다.
- **그래서, program counter는 fork () 실행 후, 다음 코드를 가리키기 때문에, 자식 process는 fork ()한 이후부터 코드를 실행한다.**
  - 자식 process라 부르지만, 복제인간이라 생각하는 게 정확한다.
  - 또한, 복제된 process는 자신을 원본이라 생각한다.
- **복제된 process인지 아닌지 구분하는 방법**
  - fork 함수의 결과값으로 자식 process 는 0을, 부모 process에게는 양수를 준다.

<Br>

## 7.4 exec () system call

- fork () 한 후, exec () system call을 통해서 자식 프로세스를 새로운 program으로 대체한다. (overwrite)
- 한 번 만들어지면 다시 되돌아갈 수 없다.

<br>

## 7.5 wait () system call

- wait () system call은 자식 process가 종료될 때까지 process A를 blocked state로 만든다.
- child process가 종료되면 kernel은 process A를 ready state로 변경하여, ready queue에 진입.

<br>

## 7.6 exit() system call

- process의 종료
  - 자발적 종료
    - 마지막 statement 수행 후, OS에게 exit () system call로 자신이 종료됨을 알린다.
    - 명시적으로 exit ()을 호출하지 않아도, main () 함수가 반환되는 위치에 compiler가 자동으로 삽입해 프로세스 종료 직전에 항상 호출한다.
  - 비자발적 종료 (자식 프로세스 밖에서 종료시키는 경우)
    - 부모 프로세스가 자식 프로세스를 강제 종료시킨다.
    - When??
      - 자식 프로세스가 한계치를 넘어서는 할당 자원 요청을 할 때
      - 자식에게 할당된 task가 더 이상 필요하지 않을 때
    - 프로그램 종료 버튼을 누르는 경우나, 키보드로 kill, break 등을 친 경우
    - 부모가 종료하는 경우
      - 부모 프로세스가 종료하기 전에 자식들이 먼저 종료된다.
- 프로그램을 강제 종료시킨 후, 계속 수행시켜야하는 경우에는 종료되지 않는 다른 자식 프로세스로 이양시켜서, 기존 부모 프로세스가 종료된 후에도 다른 프로세스 아래에서 계속 수행한다.
  - 부모가 죽기 전에 자식이 먼저 죽는다는 원칙은 여전히 지켜진다.

<br>

---

# 8. 프로세스 간의 협력

<br>

## 8.1 Process 간 협력하는 이유

- 독립적 프로세스 (Independent process)
  - 프로세스는 각자의 주소 공간을 가지고 수행되므로, 원칙적으로 하나의 프로세스는 다른 프로세스의 수행에 영향을 미치지 못한다.
- 하지만, process 간 협력한다면 왜 하고 어떻게 하는 것일까??
- 협력( Cooperating process)
  - Why?
    - 업무의 효율성 증대: 부분적인 처리 결과나 정보를 공유할 수 있고, 처리 속도가 향상.
  - How?
    - IPC (Inter-Process Communication): process 간 통신과 동기화를 이루기 위한 메커니즘

<br>

## 8.2 IPC의 대표적인 방법: 2가지

- Message passing: 메시지 전달 방식

  - Message passing의 특징
    - 프로세스 사이에 `공유 변수(shared variable)을 일체 사용하지 않고` 통신하는 시스템.
    - 중간에 `kernel을 통해서` 하는데, 명시적으로 process의 이름을 표시하냐 안하냐의 차이.
    - kernel에 의해 `send(message)`와 `receive(message)`라는 두 가지 연산을 제공받는다.
      - 즉, 이 두 가지 연산은 특권명령이다.
  - Message passing 방식 2가지

    - 직접 통신 (direct communication)
    - 간접 통신 (Indirect communication)

     <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163955059-05996ad0-a585-4b3c-975a-2608a4829d3a.png"/></p>

- Shared memory: 공유 메모리 방식

<br>

## 8.3 Message passing 방식: 2가지

- **Message passing 방식에는 직접통신(direct communication)과 간접통신(indirect communication) 으로 나뉜다.**
- **Direct communication**

 <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163952882-fb4ef53a-5293-4ddd-b5a7-86463ee14d82.png"/></p>

    - 통신하려는 프로세스의 이름을 명시적으로 표시한다.
    - Send (Q, message): process Q에게 메시지를 전송하는 것을 의미
    - Receive (P, message): process P로부터 메시지를 전달받는 것을 의미
    - link는 자동적으로 생성되며, 하나의 link는 정확히 한 쌍의 process에게 할당된다.
    - 각 쌍의 process에게는 오직 하나의 link만이 존재한다.

- **Indirect communication**

 <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163952887-cddbe514-95a3-4e50-a325-f1dc43e7b01a.png"/></p>

    - 통신하려는 프로세스의 이름을 명시적으로 표시하지 않는다.
    - mailbox ( or port)를 통해 메시지를 간접 전달한다.
    - mailbox에는 고유의 ID가 있다.
    - 이 mailbox를 공유하는 process 들끼리만 서로 통신할 수 있다.
    - Send(M, message): M이라는 mailbox에 message를 전달하는 것
    - Receive(M, message): M이라는 mailbox로부터 메시지를 전달받는 것
    - mailbox를 3개 이상의 process가 공유할 경우, 각각의 프로세스에게 링크를 따로 생성가능.

<br>

## 8.4 Shared memory

- 서로 다른 process 간에도 일부 주소 공간을 공유하게 하는 shared memory mechanism이 있다.
- 두 process가 서로 신뢰할 수 있는 process여야 한다.
- kernel에게 system call 후, memory가 공유된다.
- 물리적인 공간에 mapping 할 때, 공유된 상태로 진행한다.
- 이 방법에서 동기화 문제는 kernel 책임지지 않고, 공유되는 process 들이 책임져야 한다.

<br>

---

# 9. Thread

## 9.1 Thread란??

- **A Thread (or lightweight process) is a basic unit of CPU utilization**
  - CPU의 기본 실행 단위를 `Thread` 라 한다.
- **Thread의 구성**
  - Program counter
  - register set
  - stack space
    - stack space에서 여러 thread로 나눠진다.
- **process 내부에서 thread가 동료 thread와 공유하는 부분 = task**
  - code section
  - data section
  - OS resources
- **heavyweight process 는 하나의 thread를 가지고 있는 task다.**

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163952889-5ec16d16-59e5-41d0-87d7-fe0478ffb2e0.png"/></p>

    <p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/163952874-762af2a9-2ab7-4c21-930f-be722d55da8d.png"/></p>

  - CPU가 명령을 수행하기 위해서는 코드의 실행될 부분을 가리키는 `program counter`가 있어야 한다.
  - 또한, memory에 `register 값` 을 세팅해야 한다.
  - 그리고, OS는 process를 관리하기 위해 process마다 1개의 PCB를 둔다.
  - 이 PCB를 보면 여러 thread로 구성된 걸 확인할 수 있다.

<br>

## 9.2 Thread의 장점

- **Responsiveness: 응답성**
  - eg) multi-thread: 하나의 서버 thread가 blocked state 인 동안에도, 동일한 task 내의 다른 thread가 계속 실행되어 빠른 처리를 할 수 있다.
- **Resource sharing: 자원의 효율적인 관리**
  - 여러 thread가 process의 code, data, resource를 공유하기 때문에, 자원 관리가 효율적.
- **Economy: 경제성**
  - process를 새로 생성하는 것보다 thread를 새로 생성하는 게 오버헤드가 훨씬 적다.
  - process 간의 switching보다, thread 간의 switching이 오버헤드가 훨씬 적다.
- **Utilization of MP Architectures**
  - 병렬로 thread가 실행될 수 있다.
  - 동일한 일을 수행하는 동안 다중 thread가 협력하여 높은 처리율과 성능 향상을 얻는다.

<br>

## 9.3 Implementation of threads

- **Some are supported by kernel ⇒ Kernel threads**
  - thread가 여러 개인 것을 운영체제가 알고 있음
  - 예)
    - Windows 95, 98 / NT
    - Solaris
    - Digital UNIX, Mach
- **Others are supported by library ⇒ User Threads**
  - 운영체제가 프로세스 안에 thread가 여러개인 걸 모른다.
  - 즉, User program이 thread를 관리한다.
  - 예)
    - POSIX Pthreads
    - MAch C-threads
    - Solaris threads
- **Some are real time threads**
  - real time을 지원하는 thread

<br>

---

# Reference

- [운영체제와 정보기술의 원리](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
