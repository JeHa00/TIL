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

## 1. 데이터 저장과 연산 순서

- storage로부터 data를 가져와 연산한 후, 다시 storage에 저장한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150665-b6be593e-349a-45ac-b456-ca7012b1c258.PNG"/></p>

- 각 box 예시
  | E-box | S-box |
  |----|----|
  |CPU|Memory|
  |컴퓨터 내부| 디스크|
  |프로세스| 그 프로세스의 주소 공간|

<br>

---

# 2. Process Synchronization의 개요

<br>

## Race condition이란??

> **한 연산자가 stroage에서 가져와 작업 중인데, 작업 중인 data를 다른 연산자가 가져가서 작업하여 동기화되지 않는 현상**

<br>

## Race condition 발생 배경

- **공유 데이터(shared data)의 동시 접근(concurrent access) -> 데이터의 불일치(inconsistency) 발생**

- **그래서 concurrent process는 동기화가 필요하다.**

  - 일관성(consistency) 유지를 위해서는 협력 프로세스(cooperating process) 간의 실행 순서(oderly execution)을 정해주는 메커니즘이 필요하다.

- **데이터 불일치가 발생하는 상황들**

  - Multiprocessor system (Memory ~ CPU)
  - shared memory를 사용하는 process들 (address space ~ process)
  - kernel 내부 data를 접근하는 routine들 간 발생

<br>

---

# 3. OS에서의 race condition 3가지

<br>

## 3.1 Interrupt handler vs kernel

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150669-f384805c-84d2-47ff-988d-2d7f1c78fc13.PNG"/></p>

<br>

## 3.2 Preempt a process running in kernel

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150673-e2cc077e-d9c6-4e5f-aa27-a5f5c9d9cb76.PNG"/></p>

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150674-b4f63a05-e17a-4a1c-9696-5a94efd2da9f.PNG"/></p>

<br>

## 3.3 Multi-processor

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150667-a0483412-2fc6-488c-a463-98f43ea5c353.PNG"/></p>

<br>

---

# 4. Critical section

## 4.1 Critical section이란??

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/166150661-dec4d572-8875-4297-ab31-3a4d3f25cb4f.PNG"/></p>

<br>

## SW solution 1

<br>

## SW solution 2

<br>

## SW solution 3

<br>

## HW solution 1

<br>

<br>

---

# 5.

<br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
