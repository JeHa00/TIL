# 0. Introduction

> 1. [Foreground / Backgound Process](#1-foreground--background-process)
> 2. [ps(Process State)](#2-psprocess-state)
> 3. [pstree](#3-pstree)
> 4. [kill](#4-kill)
> 5. [killall](#5-killall)
> 6. [skill](#6-skill)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 특수 권한에 대해 학습했다.

- 이번 챕터에서는 Process에 대해 아래 내용 중 첫 번째를 학습한다.
    - Process 관리
    - Process 명령어
    
<br>

---
# 1. Foreground / Background Process

## 1.1 Foreground Process

> **_기본 기능: 화면에 실행되는 것이 보이는 프로세스_**  

- 포그라운드 프로세스: ex) 게임
    - 화면에 보이는 상태에서 동작하는 프로세스
    - 화면에 보이면서 동작하며 그 와중에 다른 작업은 불가한 프로세스


<br>

## 1.2 Background Process

> **_사용자에게 직접적으로 보이지 않고 뒤에서 돌아가고 있는 프로세스_**

- 백그라운드 프로세스: ex) 음악
    - 화면에 보이지 않는 상태에서 동작하는 프로세스
    - 다른 작업 가능  

<br>

---
# 2. ps(Process State)

> **_기본기능: 현재 실행 중인 프로세스와 상태를 출력_**
> ps [옵션]

| 옵션 | 내용 |
| ---- | ---- |
|-a |현재 실행 중인 전체 사용자의 모든 프로세스 출력|
|**_-e_**|커널 프로세스를 제외한 모든 프로세스를 출력|
|**_-f_**|Full format으로 표기 (UID, PID, PPID 등이 함께 표시)|
|**_l_**|출력을 Long forma으로 표기 (PRI, NI 값 등)|
|-u|Process의 소유자(실행한)를 기준으로 출력|
|-x|데몬 프로세스처럼 터미널에 종속되지 않은 프로세스를 출력|
|USER|Process 소유자 이름, UID|
|PID|Process의 식별 번호|
|PPID|Parent Process의 PID|
|%CPU|최근 1분간 Process가 CPU 사용한 비율의 추정치(백분율)|
|%MEM|최근 1분간 Process가 Memory 사용 비율의 추정치(백분율)|
|TTY|Process와 연결된 터미널|
|STAT|현재 Process의 상태 코드|
|STIME|Process가 시작된 시간 혹은 날짜|
|-a|Process들이 실행될 때 사용되었던 인자값, 옵션 등이 모두 출력|
|-h|현재 Process와 Parent Process를 하이라이트 형태로 출력|
|-n|Process의 PID를 기준으로 정렬하여 출력|
|-p|Process명과 PID도 출력|

<br>

---
# 3. pstree

> **_기본 기능: 현재 실행 중인 프로세스들을 '트리 구조'로 출력_**
> pstree [옵션]

<br>

---
# 4. kill

> **_기본 기능: 프로세스나 프로세스 그룹에게 시그널을 보내는 명령어_**
> kill [옵션] [PID]

| 시그널 | 번호 | 내용 |
| ---- | ---- | ---- |
| SIGHUP | 1 | 로그아웃과 같은 세션이 종료될 때 시스템이 보내는 시그널 |
| SIGINT | 2 | 키보드로부터 오는 인터럽트 시그널로 실행을 중지 시그널(Ctrl + c) |
| **SIGKILL** | **9** | 무조건 종료, 강제로 종료시키는 시그널 |
| SIGEGV | 11 | 메모리 침범이 일어날 때 시스템이 보내는 시그널 |
| SIGTERM | 15 | 기본 값, 정상 종료시키는 시그널 |
| SIGCHLD | 17 | 자식 프로세스가 stop 되거나 종료되었을 때, 부모에게 전달하는 신호 |
| SIGTSTOP | 19 | 터미널에 입력된 정지 시그널, SIGCONT로 재실행 |
| SIGTSTP | 20 | 실행 정지 후, 다시 실행을 계속하기 위해 대기시키는 시그널(Ctrl +z) |


<br>

---
# 5. killall

> **_기본 기능: 프로세스 이름으로 프로세스 종료_**
> killall [옵션] [프로세스명]

<br>

---
# 6. skill

> **_기본 기능: 시스템에 접속해 있는 사용자 혹은 특정 터미널을 종료시키는데 사용되는 명령어_**
> skill -KILL [사용자 or 터미널]

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)