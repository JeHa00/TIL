# 0. Introduction

> 1. [Bound process](#1-bound-process)
> 2. [CPU 스케쥴러](#2-cpu-스케쥴러)
> 3. [Dispatcher](#3-dispatcher)
> 4. [스케쥴링의 성능 평가](#4-스케쥴링의-성능-평가)
> 5. [스케쥴링 알고리즘](#5-스케쥴링-알고리즘)
> 6. [스케쥴링 알고리즘의 평가](#6-스케쥴링-알고리즘의-평가)

<br>

- 해당 내용은 [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 와 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)를 보고 정리한 내용입니다.
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

- **즉, CPU burst 와 I/O burst의 반복된 조합으로 이뤄진다.**

  - CPU burst(버스트): user program이 CPU를 직접 가지고 바른 명령을 수행하는 일련의 단계 -> user mode
  - I/O burst(버스트): I/O 요청이 발생해 kernel에 의해 입출력 작업을 진행하는 비교적 느린 단계 -> kernel mode

- **위 2가지를 I/O 작업을 기준으로 분류해보자.**
  - CPU burst: program이 I/O를 한 번 완료한 후, 다음 번 I/O를 수행하기까지 직접 CPU를 가지고 명령을 수행하는 일련의 작업
  - I/O burst: I/O 작업이 요청된 후, 다시 CPU burst로 돌아가기까지 일어나는 일련의 작업

<br>

### 1.4 Bound process: CPU & I/O

- **각 program마다 CPU burst와 I/O burst의 비율이 균일하지 않다.**
- **그래서 CPU bound process와 I/O bound process로 나눠볼 수 있다.**
  - CPU bound process: I/O 작업 little + long CPU burst -> 소수의 긴 CPU burst로 구성
    - 입출력 작업 없이 CPU 작업에 소모하는 계산 위주의 프로그램이 해당된다.
  - I/O bound process: I/O 요청 many + short CPU burst -> 짧은 CPU burst로 구성
    - 사용자로부터 interaction을 계속 받아가며 수행시키는 대화형 프로그램(interactive prgram)에 해당된다.
    - 즉, 사용자에게 입력을 받아 CPU 연산을 수행하여 그 결과를 다시 출력하는 작업에 해당된다.

<br>

### 1.5 CPU sheduling이 필요한 이유

- **CPU를 사용하는 패턴이 다양한 process 들이 동일한 시스템 내부에서 함께 실행되기 때문에, CPU scheduling이 필요하다.**

- **특히, 이 CPU는 한 시스템 내에 하나 밖에 없으므로, 시분할 시스템에서 매우 효율적으로 관리해야 한다.**

- **대부분의 짧은 CPU burst + 극히 일부분의 긴 CPU burst**
  = 대부분 CPU를 오래 사용하기보다는 잠깐 사용하고, I/O 작업을 수행하는 process들이 많다.  
  = CPU busrt가 짧은 process는 대부분 대화형 작업이다.  
   = CPU 스케쥴링을 할 때, CPU burst가 짧은 process에게 우선적으로 CPU를 사용할 수 있도록 하는 스케쥴링이 필요

- **I/O bound process의 우선순위를 높이는 것이 바람직한다.**

<br>

---

## 2. CPU 스케쥴러

<br>

---

## 3. Dispatcher

<br>

---

## 4. 스케쥴링의 성능 평가

<br>

---

## 5. 스케쥴링 알고리즘

<br>

---

## 6. 스케쥴링 알고리즘의 평가

<br>

---

# Reference

- [운영체제와 정보기술의 원리](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
