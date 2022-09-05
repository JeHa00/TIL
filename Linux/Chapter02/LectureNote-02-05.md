# 0. Introduction

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 Jason.Kim 강사님의 Linux 강의로부터 학습한 자료입니다.

- 지난 챕터에는 AWS를 사용한 Linux 명령어 학습 환경 조성을 하는데 목적을 두었다면, 이번에는 Linux 기본 명령어를 실습해보겠다.

- Linux의 기본 명령어는 모두 중요하므로, 다 학습해야 한다.

- 이번 소챕터에서는 리눅스 명령어를 사용하여 시스템 종료 및 재시작하는 것을 실습해보고, 어떤 과정을 거쳐서 진행되는지 알아본다.

<br>

---


# 5. 시스템 종료 및 재시작

<br>

## 5.1. Shutdown

> **_시스템을 안전하게 종료하는 시스템 관리 명령어_**

- **시스템을 종료하거나 재부팅**

- 현재 수행중인 프로세스(Program)들은 모두 종료하며 **Sync** 를 수행하여 아직 저장되어 있지 않은 데이터를 디스크에 저장한 후, 모든 파일 시스템을 Unmount 후 시스템을 종료  
    - unmount: 모든 장비를 해제시킨다. 

<br>

## 5.2. 🔆 Shutdown과 시스템 종료 절차 

1) shutdown 하기 전에 **sync 작업(= 동기화)을 수행** 한다. [sync]

2) 접속해 있는 사용자들에게 시스템이 종료된다는 **메시지를 전달** 한다.  

3) 새로운 사용자의 **로그인을 금지** 한다.   

4) 지정된 시간 내에 종료되지 않은 **프로세스를 강제 종료** 한다. [KILL]

5) 지정된 시간 내에 로그아웃하지 않은 **사용자(계정)를 강제 종료** 한다. [logout]

6) 메모리에 남아있는 데이터를 **디스크에 저장** 한다. [sync]

7) 시스템 종료와 관련된 정보를 시스템 **로그 파일에 기록** 한다. [wtmp, utrmp]

8) 마운트 되어 있는 **디바이스들을 언마운트** 한다. [unmount]

9) 시스템을 **종료** 한다. [system halt]

<br>

## 5.3 Shutdown 명령어

> **_명령어 사용법: shutdown [옵션] [시간] "전달메시지"_**

| 옵션 | 의미 |
| ----| ---- |
| -k | 시스템에 접속된 모든 사용자에게 경고 메시지만 전달 | 
| **-h** | 시스템 셧다운(shutdown) 후, 시스템 종료 ex) shoutdown -h now |
|**-r** |시스템 셧다운(shutdown) 후, 시스템 재시작 ex) shutdown -r now|
|-f|빠른 재부팅(fsck 수행을 하지 않음)|
|-c|실행 중인 셧다운(shutdown) 취소. 종료 예약 작업 시 종료 작업을 취소(ctrl +c) |
| +m | 현재 시간으로부터 종료시점 시간 지정(분)|
| hh:mm | 절대 시간으로 종료시점 시간 지정 (시간 : 분) |
| now | 명령어를 수행하는 순간 바로 종료 |



<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)