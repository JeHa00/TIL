# 0. Introduction

> 1. [Process 및 Process 종류](#1-process-및-process-종류)
> 2. [Process 명령어](#2-process-명령어)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 특수 권한에 대해 학습했다.

- 이번 챕터에서는 **프로세스 관리** 에 대해 학습해보자.


<br>

---

# 1. Process 및 Process 종류

## 1.1 Process

> **_실행 중인 모든 프로그램, 컴퓨터의 CPU에서 실해오디는 모든 프로그램_**


## 1.2 Multitasking

> **_같은 시간(동시)에 여러 개의 프로그램을 실행_**

## 1.3 Linux의 Process

- 기본 규칙
    - 각각의 프로세스마다 고유의 PID를 하나씩 증가시키면서 부여
    - 더 이상 할당할 PID가 없으면, 가장 낮은 미사용 숫자로 되돌아가서 PID 할당
    - 파일의 소유권과 유사한 방식의 소유권
    - 프로세스를 실행하는 사용자의 UID가 실제 사용자 UID로 할당
    - SetUID와 같은 특수한 경우, 실행하는 사용자 ID가 아닌 실제 소유자가 실행한 것처럼 실행

## 1.4 Process 종류

### Foreground process: ex) 게임

- 화면에 보이는 상태에서 동작하는 프로세스

- 화면에 보이면서 동작하며 그 와중에 다른 작업은 불가한 프로세스 

### Background process: ex) 음악

- 화면에 보이지 않는 상태에서 동작하는 프로세스

- 다른 작업 가능

### 대화형 프로세스: 대부분의 프로세스

- 터미널과 세션을 통해 사용자와 정보를 주고 받으며 동작하는 프로세스

### 데몬 프로세스: 서비스 관리자

- 특정 서비스(프로그램) 실행을 위해서 백그라운드 상태에서 동작하는 서버 프로세스


### 좀비 프로세스

- 자식 프로세스가 종료되었으나, 그 신호가 부모 프로세스에게 전달되지 않아 마치 살아있는 것처럼 보이는 프로세스

### 배치 Process: 예약 프로세스

- 특정한 작업을 특정 시각에 실행해주는 프로세스

<br>

---
# 2. Process 명령어

## 2.1 Foreground / Background Process

### Foreground

> **_기본 기능: 화면 실행되는 것이 보이는 프로세스_**


### Background

> **_기본 기능: 사용자에게 직접적으로 보이지 않고, 뒤에서 돌아가고 있는 프로세스_**

<br>

## 2.2 ps(Process State)

> **_기본 기능 첫 번째: 현재 실행 중인 프로세스와 상태를 출력. Proc 파일 시스템에 있는 stat의 내용_**  
> **_기본 기능 두 번째: 시스템 사용량을 실시간으로 확인 (window의 작업관리자와 유사)_**  
> ps [옵션]

| 옵션 | 내용 | 
| ---- | ---- |
|-a|현재 실행 중인 전체 사용자의 모든 프로세스 출력|
|-e|커널 프로세스를 제외한 모든 프로세스를 출력|
|-f|Full format으로 표기 (UID, PID, PPID 등이 함께 표시)|
|-l|출력을 long forma으로 표기 (PRI, NI 값 등)|
|-u|Process의 소유자(실행한)를 기준으로 출력|
|-x|데몬 프로세스처럼 터미널에 종속되지 않은 프로세스를 출력|
|USER|Process 소유자 이름, PID|
|PID|Process의 식별 번호|
|PPID|Parent Process의 PID|
|%CPU|최근 1분간 Process가 CPU 사용한 비율의 추정치(백분율)|
|%MEM|최근 1분간 Process가 Memory 사용 비율의 추정치(백분율)|
|TTY|Process와 연결된 터미널|
|STAT|현재 Process의 상태 코드|
|STIME|Process가 시작된 시간 혹은 날짜|


<br>


## 2.3 pstree

> **_기본 기능: 현재 실행 중인 프로세스들을 '트리 구조'로 출력_**  
> pstree [옵션]


<br>


## 2.4 kill

> **_기본 기능: 프로세스나 프로세스 그룹에게 시그널을 보내는 명령어_**  
> kill [옵션] [PID]

|시그널|번호|내용|
| ---- | ---- | ---- | 
|SIGHUP|1|로그아웃과 같은 세션이 종료될 때 시스템이 보내는 시그널|
|SIGINT|2|키보드로부터 오는 인터럽트 시그널로 실행을 중지 시그널(Ctrl + C)|
|**SIGKILL**|9|무조건 종료, 강제로 종료시키는 시그널|
|SIGEGV|11|메모리 침범이 일어날 대, 시스템이 보내는 시그널|
|SIGTERM|15|기본 값, 정상 종료시키는 시그널|
|SIGHLD||17|자식 프로세스가 stop되거나 종료되었을 때, 부모에게 전달하는 신호|
|SIGSTOP|19|터미널에 입력된 정지 시그널, SIGCONT로 재실행|
|SIGTSTP|20|실행 정지 후, 다시 실행을 계속하기 위해 대기시키는 시그널(ctrl + z)|

<br>


## 2.5 killall

> **_기본 기능: 프로세스 이름으로 프로세스 종료_**  
> killall [옵션] [프로세스명]


<br>



## 2.6 skill

> **_기본 기능: 시스템에 접속해 있는 사용자 혹은 특정 터미널을 종료시키는데 사용되는 명령어_**  
> skill -KILL [사용자 or 터미널]




<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)