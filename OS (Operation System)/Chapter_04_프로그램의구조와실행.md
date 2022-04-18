# 0. Introduction

> 1. [프로그램의 구조와 인터럽트](#1-프로그램의-구조와-인터럽트)
> 2. [컴퓨터 시스템의 작동 개요](#2-컴퓨터-시스템의-작동-개요)
> 3. [프로그램의 실행](#3-프로그램의-실행)
> 4. [사용자 프로그램이 사용하는 함수](#4-사용자-프로그램이-사용하는-함수)
> 5. [인터럽트](#5-인터럽트)
> 6. [시스템 콜](#6-시스템-콜)
> 7. [프로세스의 두 가지 실행 상태](#7-프로세스의-두-가지-실행-상태)

<br>

- 해당 내용은 [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 와 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)를 보고 정리한 내용입니다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 1. 프로그램의 구조와 인터럽트

- 프로그램이 CPU에서 명령을 수행하기 위해서는 명령을 담은 프로그램의 주소 영역이 메모리에 올라가 있어야 한다.
- 왜냐하면 CPU는 메모리에 있는 instruction만을 보고 실행하기 때문이다.

<br>

## 1.1 프로그램의 주소 영역

- _프로그램의 주소영역 = Code + Data + Stack 영역_
- Code 영역
  - 작성한 함수의 코드가 CPU에서 수행하는 **기계어 형태로 변환되어 저장** 되는 공간
- Data 영역
  - 전역 변수(global variable) 등 **프로그램이 사용하는 데이터를 저장** 하는 공간
- Stack 영역
  - 함수 호출 시의 **복귀 주소 및 데이터를 저장** 하는 공간
  - 예)
    - X 함수 수행 → Y 함수 호출 → 이 때 X 함수에서 Y 함수를 호출하는 지점을 stack 영역에 저장 → Y 함수가 호출되어 실행할 명령의 메모리 위치가 바뀜 → Y 함수 수행 완료 → stack에 저장된 X 함수의 주소 위치로 돌아와 코드를 계속 수행

## 1.2 PCB: 프로그램 수행의 복귀 위치

- **_인터럽트가 발생할 경우 복귀 위치: PCB(Process Control block)에 저장한다._**
- interrupt가 발생한 시점에서 프로그램의 어느 부분까지 수행했는지를 PCB에 저장
- 과정
  - A 프로그램이 CPU를 할당받아 명령을 수행 → interrupt 발생 → 현재 수행 중인 명령 위치를 PCB에 저장 → CPU 제어권을 OS에게 양도 → 인터럽트 처리 완료 후, PCB에 저장된 작업 지점으로 돌아와 계속 수행

<br>

---

# 2. 컴퓨터 시스템의 작동 개요

- 컴퓨터 시스템의 작동
  - CPU에서 명령을 수행하는 부분 + 컴퓨터 외부장치와 입출력이 이루어지는 부분

<br>

## 2.1 프로그램 카운터(Program Counter(PC))

- **_PC = Program Counter = CPU가 수행해야할 메모리 주소를 담고 있는 레지스터_**
  - CPU는
    - 빠른 속도의 연산 능력은 가지고 있지만, 결정 능력을 가지고 있지 않다.
    - 단지 매번 프로그램 카운터가 가리키는 메모리 위치의 명령을 처리한다.
  - 통상 프로그램 카운터가 다음 명령어를 가리키어 CPU 명령은 순차적으로 수행된다.
    - 반복문이나 함수 호출 등으로 바로 다음 주소가 아닌 명령을 수행할 수도 있다.
- **_Program Counter_** 가
  - **OS가 존재하는 메모리 위치** 를 가리키면 **CPU가 'kernel mode' 에서 수행 중**
  - **사용자 프로그램의 메모리 위치** 를 가리키면 **CPU가 'user mode' 에서 수행 중**

<br>

## 2.2 일반 명령과 특권 명령

- **일반 명령**
  - 메모리에서 자료를 읽어와 CPU에서 연산을 하고, 그 결과를 메모리에 쓰는 명령
  - 모든 프로그램이 수행 가능
  - mode bit가 1일 때
- **특권 명령**
  - 보안이 필요한 명령
  - 각종 장치에 접근하는 명령
  - 운영체제만이 수행
  - mode bite가 0일 때
- **운영체제를 향한 사용자 프로그램의 대행 요청: system call**
  - 사용자 프로그램이 특권 명령을 사용하고자 할 때, 사용자 프로그램이 특권 명령을 수행할 수 없으므로 운영체제에게 대행 요청 **system call** 을 한다.
  - 그러면 CPU의 제어권이 운영체제에게 넘어가서 특권 명령을 수행한다.

<br>

## 2.3 인터럽트 라인을 세팅하는 이유

- Problem
  - CPU는 프로그램 카운터가 가리키는 메모리 위치의 명령만 계속 수행하여, **_주변장치의 상태를 지속적으로 파악할 수 없다._**
- Solution
  - 주변 장치들이 CPU의 도움이 필요할 때 **_인터럽트 라인(interrupt line)을 세팅한다._**
    - CPU는 매번 명령을 수행한 후, 인터럽트 라인을 체크하여 요청 유무를 확인한다.
    - 또한, 인터럽트의 원인이 다양하기 때문에, **인터럽트 라인을 다르게 해서 구분**한다.

<br>

---

# 3. 프로그램의 실행

- **“프로그램이 실행(program execution)되고 있다”**
  - = disk에 존재하던 실행 파일이 메모리에 적재된다
  - = program이 CPU를 할당받고 instruction을 수행하고 있는 상태
- **“프로그램이 동시에 실행된다”**
  - = 여러 프로그램이 짧은 시간 단위로 CPU를 나누어 사용한다.
  - = 프로그램이 메모리에 동시에 적재되어 있을 수 있으므로

<br>

## 3.1 가상 메모리(Virtual Memory)

- 프로그램은 **실행 파일 형태**로 하드 디스크에 저장한다.
- **파일 실행 → `가상 메모리(Virtual Memory)` 생성 → `Address transition` → `물리적 메모리(Physical Memory)` 에 올라감**

  - `가상 메모리(address space, logical memory)` : 프로그램마다 가지는 독자적인 주소 영역
  - `물리적 메모리(Physical Memory)` : 0번지부터 시작
  - `Address transition` : 가상 메모리 주소를 물리적 메모리 주소로 변환하는 것으로, 하드웨어 장치가 수행

<p align="center"> <image src ="https://woovictory.github.io/img/address_translation.png"/></p>

- **Virtual memory = 주소 영역 = Address space = code + data + stack**
- **OS의 주소 영역**
  - kernel의 code
    1. **자원 관리**를 위한 부분
    2. 사용자에게 **편리한 인터페이스를 제공**하기 위한 부분
    3. **system call 및 interrup를 처리**하기 위한 부분
  - kernel의 data
    - 하드웨어와 소프트웨어(ex: 사용자 프로그램)를 포함하는 시스템 내의 모든 자원을 관리하기 위한 자료구조를 유지 ex) PCB
  - kernel의 stack
    - 현재 수행 중인 프로세스마다 별도의 스택을 두어 관리.
      - Reason 1: system call로 특권 명령 대행을 요청한 후, 운영체제가 system call 내부의 다른 함수를 호출할 경우 복귀 주소는 커널 내의 주소가 되기 때문에
      - Reason 2: kernel은 일종의 공유 코드로서, 모든 프로세스가 system call을 통해 kernel 함수를 접근할 수 있으므로, 각 프로세스마다 커널 내에 별도의 스택을 둔다.
- **함수 호출 복귀 시 저장 장소**
  - `'____'` 코드 수행 중 이루어지는 함수 호출로 인한 복귀 주소 유지는 `'____'` 을 사용
    - process → **자신의 address space 내의 stack**
    - kernel → **kernel stack**
  - CPU 수행 주체가 OS로 바뀔 때 직전 수행 프로그램의 복귀 정보는 stack이 아닌 `PCB`에 저장

<p align="center"> <image src ="https://woovictory.github.io/img/address_structure_of_os.png"/></p>

<br>

## 3.2 Swap area

- Problem
  - 프로그램이 프로세스가 되었을 때 생성되는 address space를 물리적 메모리에 다 올리지 않는다.
  - Why?? 다 올리면 메모리 낭비가 심하기 때문
- Solution
  - 바로 필요한 코드 부분만 memory에 올린다.
  - 그 외 부분은 보조기억장치에 놔두는데, 이 영역을 `swap area`라 한다.
  - swap area는 메모리 용량 한계로 메모리 연장 용도로 사용한다.
  - 하지만, 프로그램이 파일 형태로 저장되는 보조기억장치의 disk 영역은 비휘발성 용도로 저장한다.

<br>

---

# 4. 사용자 프로그램이 사용하는 함수

- **_프로그램이 사용하는 함수의 종류_**
  - **사용자 정의 함수**: 프로그래머 본인이 직접 작성한 함수
  - **라이브러리 함수**: 자신의 프로그램에서 정의하지 않고 가져다 쓴 함수로, 자신의 프로그램의 실행 파일에 포함되어 있다.
  - **커널 함수**: kernel의 코드에 정의된 함수 = system call 함수 + interrupt 처리 함수
    1. system call 함수: 사용자 프로그램이 운영체제의 서비스를 요청하기 위해 호출함수
    2. interrupt 처리 함수: 각종 HW 및 SW가 CPU의 서비스를 요청하기 위한 함수
       - kernel의 address space에 code가 정의되기 때문에, system call로 kernel mode로 바꿔야 실행 가능하다.
- **_사용자 정의 함수와 라이브러리 함수_** 는
  - 프로그램의 코드 영역에 기계어 명령 형태로 존재 → 프로그램 실행 시, 해당 프로세스의 address space에 포함
  - 함수 호출 시에도, 프로세스의 address process에 있는 stack 영역을 사용
  - 프로세스의 address space의 code 영역 안에서 메모리 상의 점프를 한다.
  - user mode에서 실행된다.

<br>

---

# 5. 인터럽트

<br>

## 5.1 Interrupt 작동 순서 복습

- CPU는 프로그램 카운터가 가리키는 명령만 쉬지 않고 수행하기 때문에, 다른 명령을 수행하기 위해서는 interrupt를 걸어야 한다.
- CPU는 program counter가 가리키는 명령을 하나씩 수행한 후, interrupt line이 세팅되었는지 확인한다.
- interrupt line setting을 통해 interrupt가 발생했으면 현재 수행하던 process를 멈추고, 운영체제의 인터럽트 처리 루틴으로 이동하여, 인터럽트 처리를 수행한다.
- 인터럽트 처리를 마치면 인터럽트 발생 직전의 프로세스에게 CPU 제어권이 넘어간다.

<br>

## 5.2 Interrupt의 서로 다른 중요도

- 인터럽트 처리 중, 또 다른 인터럽트가 발생한 경우에는 어떻게 처리되는가???
  - 중요도를 비교한다.
  - 현재 처리 중인 인터럽트의 중요도가 상대적으로 낮으면, 처리 중인 인터럽트 코드의 수행 지점을 저장한다.
  - 그 다음, 중요도가 더 높은 인터럽트를 처리한다.
  - 인터럽트 처리가 끝나면 저장 주소로 복귀해 이전에 수행하던 인터럽트 처리 코드를 마저 수행한다.

<br>

---

# 6. 시스템 콜

- **system call 사용의 예**

  - process가 CPU에서 명령을 수행하던 중 I/O 작업이 필요한 경우, **sw interrupt인 system call**을 통해 kernel 함수를 호출한다.

    → kernel 함수는 사용자 프로그램이 수행할 수 없으므로, CPU 제어권을 OS에게 넘겨야 하는데,

    → OS에게 넘기기 위해서 인터럽트 라인을 세팅하는 명령을 실행하여, CPU에게 interrupt가 발생했다는 걸 알린다.

    → CPU는 program counter가 가리키는 명령을 하나씩 실행한 후, interrupt line을 체크하여 interrupt 발생을 확인한다.

    → interrupt를 확인한 CPU는 현재 실행 중인 process를 멈춘 후, process의 실행 상태를 PCB에 저장한다.

    → OS는 interrupt line을 통해서 어느 종류의 interrupt인지 확인한 후, interrupt vector가 가리키는 interrut service routine을 찾아 실행하여, 요청한 I/O에 해당하는 device controller에게 I/O 명령을 한다.

    → I/O 요청이 수행되는 동안, 해당 process는 데이터가 없어서 다음 명령을 수행할 수 없으므로, CPU를 다른 process에게 이양한다.

    → 다른 process의 작업을 CPU가 작업하는 도중에, I/O 작업이 완료되면 device controller가 CPU에게 interrupt를 발생시켜 I/O 작업 완료를 알린다. 이 때 발생한 interrupt는 HW interrupt다.

    → iterrupt 처리 내용으로 device controller가 device로부터 읽어와서 local buffer에 저장한 내용을 메모리로 복사해온다.

    → 복사 후, I/O 작업을 요청했던 process에게 다시 CPU를 얻을 수 있는 권한을 준다.

    → 그러면 I/O 작업을 이제 완료한 process는 CPU를 기다리는 큐에 삽입되고, CPU의 제어권은 iterrupt를 당한 process에게 넘어가서 하던 작업을 계속 수행한다.

<br>

- **process가 CPU를 빼앗기는 경우: 2가지**
  - **Timer의 CPU 할당 시간이 만료된 경우, interrupt가 발생**
    - time sharing system의 필수적인 요소
    - 한 process가 CPU를 독점하는 걸 방지
  - **process가 I/O 작업 같은 kernel code 수행이 필요한 경우 sw interrupt인 system call 하는 경우**
    - 시간이 오래 걸리는 I/O 작업이 수행하는 동안, CPU를 다른 process에게 할당한다.
      - 입출력 작업을 요청한 process에게 CPU를 할당해도 파일 데이터가 있어야 당장 다음 명령을 수행할 수 있는데,
      - I/O 연산 속도는 CPU 연산보다 매우 느려서 긴 기다리는 시간 동안 CPU가 일을 할 수 없기 때문에 비효율적이다.

<br>

---

# 7. 프로세스의 두 가지 실행 상태

- 프로세스의 실행 상태 두 가지: **_user mode running(사용자 모드에서의 실행 상태) 와 kernel mode running(커널 모드에서의 실행 상태)_**
- **프로그램 자신의 주소 공간에서 정의된 코드**를 실행 ↔ **_user mode running_**
  - ex) 사용자 정의 함수 와 라이브러리 함수를 호출
- kernel의 system call 함수 (**kernel 주소 공간에 정의된 함수**) 를 실행 ↔ **_kernel mode running_**
  - system call 실행이 끝나면 다시 user mode로 복귀
  - 또한, 프로그램 실행이 끝날 때에는 kernel mode로 진입해 프로그램을 종료한다.
- **_process 가 kernel mode에서 실행 중이란 의미는???_**
  - process A가 system call 을 통해 OS에게 대행 요청을 하여 kernel code를 실행 중이다 = process A가 kernel mode에서 실행 중
  - os가 kernel code를 수행하고 있을 지라도, os는 process A를 대신하여 수행 중이기 때문에, process A가 실행 상태인 걸로 간주한다.

<br>

---

# Reference

- [운영체제와 정보기술의 원리](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
