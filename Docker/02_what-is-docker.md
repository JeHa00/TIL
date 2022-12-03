# 0. Introduction

> 1. [Docker란?](#1-docker란)  
> 2. [laaS, PaaS, SaaS의 개념](#2-laas-paas-saas의-개념)  
> 3. [가상화, 가상화의 종류 그리고 docker](#3-가상화-가상화의-종류-그리고-docker)  
> 4. [docker의 구조](#4-docker의-구조)  
> 5. [컨테이너는 운영체제일까?](#5-컨테이너는-운영체제일까)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.

- docker의 기본 개념에 대해 학습해보겠습니다. 

<br>

---

# 1. Docker란?

- **container(컨테이너)** 라고 부르는 패키지 형태  
- **OS-level 가상화** 기술을 사용한 **PaaS(Platform as a service)** 제품  
- 컨테이너를 관리하는 소프트웨어를 **도커 엔진(Docker Engine)** 이라 한다.  


<br>

---

# 2. laaS, PaaS, SaaS의 개념  

<img width="1722" alt="Screen Shot 2022-12-01 at 8 39 17 PM" src="https://user-images.githubusercontent.com/78094972/205043637-222e4ee6-971a-4f55-b4b2-68a20e1aebb5.png">

### On premises

- 사용자가 전부 다 관리해야하는 서비스
- 다나와에서 서버를 하나 사서, 처음부터 끝까지 사용자가 다 하는 것

### Iaas (Infrastructure as a service)
- 인프라를 서비스로 제공한다는 의미로서, Networking부터 가상화까지만 업체에서 제공하고, 나머지만 사용자가 관리하는 서비스
- Ex) AWS, Google Cloud, Microsoft Azure
    - HW 고장을 신경쓰지 않는다. 

### Paas (Platform as a service)
- applications과 data 제외한 모든 부분을 업체에서 제공하는 서비스
    - Ex) Docker, github, Kubernetes
- 배포할 수 있는 플랫폼을 제공하는 서비스

### Saas (Software as a service)

- 전부 다 업체가 관리하는 서비스
- 소프트웨어만 사용자가 신경쓴다.
- ex) Gmail, Google Docs, Office 365

### 그렇다면 개인 프로젝트들은 IaaS, PaaS, SaaS 중 무엇일까?

- 정답: **SaaS** 
- **사용자가 아닌 제공자의 관점에서 위 이미지 기준으로 어느 단계까지 제공하는 지가 분류 기준이다.**
    - IaaS X: EC2를 사용할 뿐, EC2를 제공하지 않는다.
    - PaaS X: Docker를 사용할 뿐, Docker service를 제공하지 않는다. 

<br>

---

# 3. 가상화, 가상화의 종류 그리고 docker

## 가상화(virtualization)이란?

> **_물리적 자원(H/W)을 논리적 자원으로 변환해서 사용하는 것_**

- 예를 들어서 하드 디스크는 하나지만, C 드라이브와 D 드라이브로 쪼개서 사용하는 것이 가상화의 개념이다. 


## 가상화의 종류 
- 호스트 가상화(Host virtualization)

- 하이퍼 바이저 가상화(Hypervisor virtualization)

- OS level 가상화, 컨테이너 가상화

<br>



### 첫 번째, Host virtualization(호스트 가상화)

> **_Host OS(기존에 깔려 있는 OS) 위에 Guest OS(설치할려는 OS)를 얹어서 사용하는 방식_**

- **layer 구성**
    - HW > **Host OS(ex: Window)** > Hypervisor (ex: VMware) > 가상 서버 #1, #2...#N

- 가상서버는 다음과 같이 밑에서부터 구성된다.
    - 가상 서버 #1: **guest OS(ex: linux)** > Bins/libs > application A 
    - 가상 서버 #2: **guest OS** > Bins/libs > application B

- 장점: Host OS가 무엇이든지 상관없이 제약이 없다.

- 단점: OS 위에 OS를 얹기 때문에 무거워서 느리다. 

<br>


### 두 번째, Hypervisor virtualization(하이퍼바이저 가상화)

> **_Host OS 없이 하드웨어 위에 바로 하이퍼바이저 설치_**

- layer 구성
    - HW > Hypervisor > 가상 서버 #1, #2, .., #N 
    - 가상 서버: guest OS > Bins/libs > application A

- 장점: Host OS가 존재하지 않으므로 가벼움

- 단점: 머신 전체를 관리하기 위한 또 다른 PC 필요

- 그래서 많이 사용하지 않는다.


<br>


### 세 번째, Container virtualization(컨테이너 가상화): Docker

> **_Host OS 위에 컨테이너 관리 프로그램 설치_**

- layer 구성
    - HW > **Host OS** > Docker > Container #1, #2, ... , # N
    - Container #1: Bins/libs > Application A

- application만 관리

- 장점: 가볍고 빠르다.

- 컨테이너가 없이 사용할 경우 문제점
    - OS 위에 바로 장고를 설치하여 app을 배포한다면 OS에 따라서 달라지는 문제가 생긴다. 
    - 컨테이너 없이 app 배포를 한다면 계속해서 서비스를 유지해야 하기 때문에, 다른 것을 하기 어렵다. 

- 하나의 서버에 여러 서비스를 돌리면 위험한 이유
    - 서로 다른 서비스에게 반드시 영향을 줄 수 밖에 없다. 
    - 이 서비스를 삭제한다고 해도 흔적이 남기 때문이다.

- 하지만 도커를 사용해서 다음 것들이 가능해졌다.
    - 1번 컨테이너에 문제가 생겼으면 해당 컨테이너를 끄기만 하면 된다.(격리 isolation)
    - 다른 container에 있는 것에 영향을 줄 수 없다.     
    - 옛날에는 하나의 EC2 마다 하나의 서비스를 배포했지만, 지금은 docker를 사용하면 하나의 EC2에서도 여러 개의 서비스를 배포할 수 있다.

<br>

## Docker는 Linux 환경에서만 동작한다.

<img width="1738" alt="Screen Shot 2022-12-01 at 9 33 03 PM" src="https://user-images.githubusercontent.com/78094972/205053860-488bd4ef-e191-4603-bd84-4dc6d611a987.png">

<br>

---
# 4. docker의 구조

## docker engine의 구조

> **_Persistent storage, Containerd, Networking_**

도커의 구조는 매우 복잡하지만 크게 보면 3가지로 구분된다.

- Persistent Storage: 데이터 저장 공간
    - volumes
    - Bind Mounts

- Containerd: 만든 프로그램을 컨테이너 안에 담는 역할로, 도커 엔진 밖에 존재한다.  
    - runC

- Networking: 컨테이너끼리의 통신 또는 컨테이너와 외부 간 통신하는 역할

<br>

## docker의 구조

<img width="1721" alt="Screen Shot 2022-12-01 at 10 11 25 PM" src="https://user-images.githubusercontent.com/78094972/205061439-69520c12-90bf-49d4-9fa2-f4108bff919b.png">

### Client, Registry
- client: 사용하는 노트북
- Registry: 도커 이미지 저장소
    - https://hub.docker.com/   
    - 기본적으로 도커 허브에 있는 이미지를 찾도록 설정되어 있다. 

### Docker hosts
docker가 설치된 서버로서, 나는 ec2를 사용할 것이므로 EC2가 된다.   

<br>

**Docker engine** : 클라이언트-서버 역할을 수행
- dockered와 같은 DAEMON process가 실행되는 서버
    - dockerd에서 d는 daemon을 말한다.    
- 도커 데몬과 의사소통하기 위한 API 역할    
- CLI client docker     
- 도커 명령어를 dockered가 받아서 실행하는데, 이는 API를 호출하는 것이다.

<br>

**docker daemon** : host에서 메모리에 상주(프로세스)하며 백그라운드에서 클라이언트의 요청에 따라 컨테이너를 관리하는 프로세스  
- 메모리에 상주하면서 클라이언트의 요청이 오면 즉시 대응할 수 있도록 대기 중인 프로세스  
- **docker 사용자는 이 daemon과 통신하는 것**    

<br>

**docker image** : 도커 컨테이너를 실행하기 위해 필요한 모든 걸 모아놓은 패키지  
- 어떻게 인스턴스화되어야 하는지에 대한 내용 포함    
- **실행 중인 도커 컨테이너의 특정 시점을 나타내는 가상 환경의 스냅샷**    
    - 특정 시점: 나의 tool들을 설치한 시점

<br>

**docker container** : 실행 가능한 도커 이미지의 인스턴스  
- 다른 프로세스들과 완전히 부리된 하나의 프로세스    
- 가상화된 런타임 환경    

<br>

### build, pull, run
- **build** : client로부터 직접 dockerfile를 image로 만드는 과정
    - 사람이란 개념(클래스)과 철수라는 구체적인 예시(인스턴스)   
    - image는 클래스 인스턴스의 개념에서 클래스  
    - Container는 instance  
    - 하지만 build는 image까지만 만들어낼 수 있다.   

- **pull** : Image를 만드는데, 내 컴퓨터에서 만드는 게 아닌 registry로부터 image를 만드는 과정


- **run** : image로부터 container를 만드는 과정 
    - Docker host에 image가 없다면 자동적으로 pull을 실행한다.  
    - **실행한 컨테이너는 IP 주소를 가지는 하나의 독립된 서버처럼 동작한다.**  
    - **각 컨테이너는 고유의 IP 주소와 포트 번호를 가진다.**    
    - **컨테이너는 실행할 때마다 새로운 IP 주소를 가진다.**    


<br>

---


# 5. 컨테이너는 운영체제일까?  

> **_컨테이너는 해당 image를 실행하기 위한 최소 파일들이지, 운영체제가 아니다._**  

위의 컨테이너 가상화 이미지를 보자. 

host OS는 있지만, 컨테이너 안에는 OS가 없다. 

그러면 운영체제가 아닌데, 어떻게 우분투를 실행할 수 있을까?

컨테이너의 최하단은 `Bins/libs`다. 

해당 image를 실행하기 위한 최소 파일들을 말한다.

그래서 운영체제와 헷갈릴 수 있지만, 엄밀히 얘기하면 운영체제가 아니다.



<br>


---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)