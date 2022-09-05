# 0. Introduction

> 1. [Process](#1-process)
> 2. [Multitasking](#2-multitasking)
> 3. [Linux의 Process](#3-linux의-process)
> 4. [Process 종류](#4-process-종류)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 지난 챕터에는 특수 권한에 대해 학습했다.

- 이번 챕터에서는 Process에 대해 아래 내용 중 첫 번째를 학습한다.
    - Process 관리
    - Process 명령어
    
<br>

---
# 1. Process 

> **_실행 중인 모든 프로그램, 컴퓨터의 CPU에서 실행되는 모든 프로그램_**

<br>

---
# 2. Multitasking

> **_실같은 시간(동시)에 여러 개의 프로그램을 실행_**

<br>

---
# 3. Linux의 Process

기본 규칙

1) 각각의 프로세스마다 고유의 프로세스 ID(PID)를 하나씩 증가시키면서 부여

2) 더 이상 할당할 PID가 없으면, 가장 낮은 미사용 숫자로 되돌아가서 PID 할당

3) 파일의 소유권과 유사한 방식의 소유권

4) 프로세스를 실행하는 사용자의 UID가 실제 사용자 UID로 할당

5) SetUID와 같은 특수한 경우, 실행하는 사용자 ID가 아닌 실제 소유자가 실행한 것처럼 실행  

<br>

---
# 4. Process 종류

## 4.1 대화형 프로세스: 대부분의 프로세스

> **_터미널과 세션을 통해 사용자와 정보를 주고받으며 동작하는 프로세스_**

## 4.2 데몬 프로세스: 서비스 관리자

> **_특정 서비스(프로그램) 실행을 위해서 백그라운드 상태에서 동작하는 서버 프로세스_**


## 4.3 좀비 프로세스

> **_자식 프로세스가 종료되었으나, 그 신호가 부모 프로세스에게 전달되지 않아 마치 살아있는 것처럼 보이는 프로세스_**


## 4.4 배치 프로세스: 예약 프로세스

> **_특정한 작업을 특정 시각에 실행해주는 프로세스_**

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)