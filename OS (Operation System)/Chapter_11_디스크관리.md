# 0. Introduction

> 1. [디스크의 구조](#1-디스크의-구조)
> 2. [디스크 스케쥴링](#2-디스크-스케쥴링)
> 3. [다중 디스크 환경에서의 스케쥴링](#3-다중-디스크-환경에서의-스케쥴링)
> 4. [디스크의 저전력 관리](#4-디스크의-저전력-관리)

<br>

- 해당 내용은 [운영체제와 정보기술의 원리 -반효경 지음-](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=) 와 [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)를 보고 정리한 내용이다.
- 정확하지 않은 내용이 있다면 말씀해주시면 감사하겠습니다.

<br>

---

# 1. 디스크의 구조

- 디스크(disk): 컴퓨터 시스템의 대표적인 2차 저장장치
  - memory는 volatile(휘발성) 저장장치이지만, disk는 영구 보관할 수 있는 2차 저장장치
- 디스크의 효율적인 관리를 위해 `디스크 스케쥴링 기법` 과 `저전력 디스크 관리 기법` 에 대해 보자.
- 또한, 엘리베이터 scheduling 문제를 통해 쉽게 이해해보자.

<br>

## 1.1 논리 블록(logical block) 단위

![image](https://user-images.githubusercontent.com/78094972/170026928-a4779945-46b9-4770-8058-08e7a5e00854.PNG)

- 디스크 외부(=컴퓨터 host의 내부)에서 보는 디스크의 단위 정보 저장 공간들
  - 내부에서는 sector로 바라본다.
- 주소를 가진 1차원 배열처럼 취급
  - host에서 disk의 정보를 읽을 때 , sector 몇 번지가 아니라 1차원 배열에서 몇 번째 있는 정보를 달라고 요청한다.
- 정보를 전송하는 최소 단위
- disk에 데이터 저장과 disk 외부로의 입출력은 `논리 블록 단위`로 저장 및 전송이 이뤄진다.
- disk 내의 물리적인 위치 = `섹터(Sector)`
  - 논리 블록 하나가 섹터 하나와 1대1 매핑되어 저장
  - sector 0: 최외곽 실린더의 첫 트랙에 있는 첫 번째 섹터
- 어떻게 논리 블록의 저장된 데이터에 접근하는가??

  - 해당 블록의 index 번호를 disk controller에 전달 → disk controller가 대응되는 sector를 찾아 요청 데이터에 대한 입출력 작업을 수행

- Partitioning
  - disk를 하나 이상의 실린더 그룹을 나누는 과정
  - OS는 이것을 _독립적 disk_ 로 취급(logical disk)
- Logical formatting
  - 파일 시스템을 만드는 것
    - 컴퓨터 전원을 키면 이 파일 시스템에 있는 운영체제가 메모리에 올라와서 booting
  - FAT, inode, free space 등의 구조 포함
- **Booting 의 간결한 절차 순서**
  - disk에 CPU가 직접 접근하지 못 하는데 어떻게 접근하는가???
  - ROM에 있는 small bootstrap loader의 실행
    - ROM: 전원이 나가도 기억하는 소량의 memory
      - 이 ROM에 booting을 위한 loader인 small bootstrap loader가 저장되어 있다.
    - 전원을 키면 CPU 제어권이 이 ROM 주소를 가리키고, ROM에 이 loader가 실행된다.
    - disk에서 sector 0에 있는 것을 memory에 올리고, 실행하라고 bootstrap loader가 지시한다.
    - 이 sector 0은 어떤 파일 시스템이든 공통이기 때문에, 이 boot block을 메모리에 올린다.
    - 이 boot block이 file system에서 OS의 kernel 위치를 찾아서 memory에 올려서 실행하라고 말한다.
  - sector 0 (boot block)을 load하여 실행
  - sector 0 은 “full Bootstrap loader program”
  - OS를 disk에서 load하여 실행


<br>


## 1.2 디스크의 물리적 구조

- 디스크의 물리적인 구조: 마그네틱의 `원판` 으로 구성
- 원판 = `트랙(track)` 으로 구성
  - 트랙(track) = `섹터(sector)` 로 구성
- 실린더(Cylinder) = 상대적 위치가 동일한 트랙들의 집합
  - track과 거의 유사하게 사용
- 암(Arm) = disk의 data를 읽고 쓰기 위해서, 해당 섹터가 위치한 실린더로 이동하는 장치
- 헤드(head) = 암에 붙어있는 data를 읽고 쓰는 장치


<br>

---


# 2. 디스크 스케쥴링

<br>

## 2.1 접근시간(Access time)의 구성

- 탐색시간(seek time), 회전지연시간(rotational latency), 전송시간(transfer time)으로 구분
  - 탐색시간(seek time): disk head를 해당 실린더 위치로 이동시키는데 걸리는 시간
  - 회전지연시간(rotational latency): 디스크가 회전해서 읽고 쓰려는 섹터가 헤드 위치에 도달하기까지 걸리는 시간
  - 전송시간(transfer time): 해당 sector가 head 위치에 도달한 후, data를 실제로 sector에 읽고 쓰는 데 소요되는 시간
- disk I/O에 소요되는 접근시간 최소화 ⇒ disk I/O 효율 상승
  - 회전지연시간, 전송시간은 OS가 통제 힘듬
  - 그래서, `탐색시간` 을 줄이기 위해 헤드의 움직임을 최소화하는 스케쥴링 작업 실행

<br>

## 2.2 Disk scheduling 작업

- 효율적인 disk I/O를 위해 여러 sector들에 대한 I/O 요청이 들어왔을 때, 이들을 어떤 순서로 처리할지 결정하는 mechanism
- Disk bandwidth: 단위 시간 당 전송된 바이트의 수
- 목표: disk head의 이동거리를 줄이는 것
  - seek time을 최소화하는 것과 유사
- 엘리베이터의 스케쥴링 문제와 매우 유사

<br>

### 2.2.1 FCFS(First Come First Served) scheduling

![image](https://user-images.githubusercontent.com/78094972/170041063-a6dd7181-743f-4a42-9140-d2ae321fb4d2.PNG)

- 디스크에 먼저 들어온 요청을 먼저 처리하는 방식
- 은행창구처럼 고정된 장소에서 이뤄지는 게 아니라, 데스크 헤드가 움직이면서 서비스를 하기 때문에, 비효율적
- 그룹화를 하지 않고 진행하기 때문

<br>

### 2.2.2 SSTF(Shortest Seek Time First) scheduling

![image](https://user-images.githubusercontent.com/78094972/170041289-f24abf40-75e1-4fa6-8c7d-3f110024caf3.PNG)

- head의 현재 위치로부터 가장 가까운 위치에 있는 요청을 제일 먼저 처리하는 방식
- 문제점: 기아 현상(starvation)
  - 헤드 위치에서 멀리 떨어진 곳의 요청은 무한히 기다려야 하는 문제가 발생
- 이동거리 측면에서 가장 우수한 알고리즘 X

<br>

### 2.2.3 SCAN algorithum

![image](https://user-images.githubusercontent.com/78094972/170043962-f0bd1cbb-019c-4aab-8497-e14f982d4ac6.PNG)

![image](https://user-images.githubusercontent.com/78094972/170045075-c5cab1a9-8778-4424-bfc3-fd850e04eb56.PNG)

- head가 disk 원판의 안쪽 끝과 바깥쪽 끝을 오가며, 그 경로에 존재하는 모든 요청을 처리
- 엘리베이터에서 사용하는 스케쥴링 알고리즘과 유사
  - elevator scheduling algorithum 이라 불리기도 한다.
- 효율성과 형평성을 모두 만족하는 알고리즘
  - FCFS처럼 불필요한 헤드의 이동 발생 X
  - SSTF처럼 starvation 현상 발생 X
- 문제점
  - 이동 거리 측면에서 효율적이나, 모든 실린더 위치의 기다리는 시간이 공평한 것 X
  - 제일 안쪽과 바깥쪽 위치보다 가운데 위치가 기다리는 평균시간이 더 짧기 때문

<br>

### 2.2.4 C-SCAN algorithum

![image](https://user-images.githubusercontent.com/78094972/170045357-04be1f12-503c-439f-9474-22c4db297f0f.PNG)

![image](https://user-images.githubusercontent.com/78094972/170046609-2d0f296a-d228-4eae-b75b-0e79dc89e2b6.PNG)

- SCAN algorithum을 개선한 것으로, circular-SCAN의 줄임말이다.
- 출발점에서 다른 쪽 끝으로 이동하며 가는 길목에 있는 모든 요청을 처리하는 것까지 SCAN과 동일하나, 다른 쪽 끝에 도달해 원래 출발점 방향으로 이동할 때, 요청을 처리하지 않고, 바로 이동만 한다.
- 장점
  - 이동거리는 조금 길어지지만, 균일한 탐색시간을 제공

<br>

### 2.2.5 LOOK and C-LOOK algorithum

![image](https://user-images.githubusercontent.com/78094972/170052143-fff630d0-c783-492b-90bb-97135babe2c8.PNG)

- head가 한쪽 방향으로 이동하다가 그 방향에 더 이상 대기 중인 요청이 없으면 head의 이동 방향을 즉시 반대로 바꾸는 scheduling 방식
  - SCAN과 C-SCAN을 개선한 방식
- 진행 방향에 요청이 있는지 살핀 후, 이동하기 때문에 `LOOK` 이라 한다.
- 지금까지 살펴본 디스크 스케쥴링 기법들 중 LOOK 과 C-LOOK 등의 알고리즘이 많은 시스템에서 FCFS 와 SSTF에 비해 더 효율적인 것으로 알려져있다.



<br>

---


# 3. 다중 디스크 환경에서의 스케쥴링

<br>

## 3.1 어디서 다중 디스크를 사용하는가?

- 동시 사용자가 많은 서버에서는 다수의 disk를 함께 사용한다.

<br>

## 3.2 다중 디스크의 장점은???

- 다중 디스크 → 시스템의 성능과 신뢰성 동시 향상
  - 동일한 정보를 여러 디스크에 중복 저장 → 인기 데이터를 여러 디스크로부터 동시 서비스가 가능
  - 오류가 발생해도, 지속적인 서비스가 가능 + 정보의 유실을 방지 가능

<br>

## 3.3 다중 디스크의 문제와 스케쥴링 목적

- 다중 디스크 환경에서는 어느 디스크에서 요청을 처리할지 결정하는 스케쥴링 문제까지 포함
- 다중 디스크 환경에서의 스케쥴링 목적: 부하 균형(load balancing)을 이루면서 확장성 있는 서비스가 목표
  - 일부 디스크가 과부하에 걸리지 않도록, 모든 디스크에 골고루 분배하도록 스케쥴링

<br>

## 3.4 다중 디스크 스케쥴링의 또 다른 목표: 전력 소모 감소

- 하나의 디스크로부터 요청을 충분히 감당할 수 있을 때, 다른 디스크들의 회전은 정지하는 게 효율적
- 유사한 예시: 그룹 엘리베이터
  - 이웃한 여러 대의 엘리베이터가 독립적으로 운영되는 게 아니라, 동일한 제어 시스템에 의해서 공동으로 운영된다.
  - 목표: 다수의 승객이 오래 기다리지 않고, 빠른 서비스를 받는 시스템의 확장성
  - 사용자가 적을 경우, 한 대의 엘리베이터로 운행하는 게 효율적


<br>

---


# 4. 디스크의 저전력 관리

<br>

## 4.1 비활성화 기법

![image]()

- 디스크의 상태는 전력소모를 기준으로 4가지 상태로 나뉜다.
  - 활동(active) 상태: 현재 head가 data를 읽거나 쓰고 있는 상태
  - 공회전(idle) 상태: disk가 회전 중이지만, data를 읽거나 쓰지 않는 상태
  - 준비(standby) : disk 회전하지 않지만, interface가 활성화된 상태
  - 휴면(sleep) 상태: disk 회전 X, interface 비활성화
  - 활성 상태 = active + idle
  - 비활성 상태 = standby + sleep
  - 전력 소모가 비활성 상태일 때, 더 적다.

<그림 9-10>

- 각 상태로 전환 시, 부가적인 전력 및 시간 소모된다.
- 후속 요청까지의 시간 간격이 일정 시간 이상이어야만, 디스크의 회전을 정지시키는 것이 전력 소모 절감에 효과적
  - 비활성화 시점을 결정하는 게 중요하다는 것
- 디스크 비활성화 시점 결정 방법
  - 시간기반(timeout based): 일정 시간 동안 디스크가 공회전 상태이면 장치를 정지 → 요청이 들어오면 디스크 활성화
  - 예측기반(prediction based): 과거 요청을 관찰하여, 다음 공회전 구간의 길이를 예측 후, 비활성화 시점 결정
  - 확률기반(stochastic based): 확률분포를 사용

<br>

## 4.2 회전속도 조절 기법

- 디스크의 회전속도(RPM)를 가변적으로 조절하는 기법
- OS는 시스템 자원과 부하를 포괄적으로 볼 수 있기 때문에, 하드웨어 혼자보다 더 많은 전력 절감 효과 얻음
- 멀티미디어 환경에서는 미래의 참조 예측이 비교적 정확해서 전력 소모를 줄일 수 있다.

<br>

## 4.3 디스크의 데이터 배치 기법

- 데이터의 복제본을 많이 만들어, 헤드 위치에서 가까운 복제본에 접근하여 빠른 응답시간과 전력 소모량 절감을 얻는 FS2 file 시스템(free space file system)

<br>

## 4.4 버퍼캐싱 및 사전인출 기법

- 전제:미래에 요청할 데이터를 미리 알거나, 어느 정도 예측할 수 있다면
  - 활성 상태일 때 헤드 위치로부터 가까운 데이터를 사전 인출하여, 디스크의 비활성화 가능성을 높여 전력 소모를 줄임

<br>

## 4.5 쓰기전략을 통한 저전력 디스크 기법

- 디스크가 비활성화 상태일 때는 기다리다가, 활성 상태일 때 쓰는 방식으로 전력 소모를 줄이는 방안

<br>

---

# Reference

- [kocw 이화여자대학교 운영체제 - 반효경 교수 -](http://www.kocw.net/home/cview.do?lid=3dd1117c48123b8e)
- [운영체제와 정보기술의 원리 - 반효경 지음 -](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9791158903589&orderClick=LAG&Kc=)
