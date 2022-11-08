# 0. Introduction

> 1. [백엔드 개발의 업무 단위](#11-백엔드-개발의-업무-범위)
> 2. [왜 배우는지](#12-왜-배우는지)
> 3. [DevOps & AWS/Cloud 소개](#13-devops--awscloud-소개)
> 4. [AWS 주요 개념과 서비스 소개](#14-aws-주요-개념과-서비스-소개)
> 5. [Old vs New (with Server vs serverless)](#15-old-vs-new-with-server-vs-serverless)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 lukas 강사님의 AWS 강의를 정리한 내용입니다.

- 이번 챕터에서는 왜 DevOps가 중요한지, 클라우드와 AWS를 소개하면서 이에 대한 이론을 학습한다.

<br>

---
# 1.1 백엔드 개발의 업무 범위

> **_주 목적: 장기적으로 안정적으로 서비스를 배포하고 운영하기_**

backend 안에는 DevOps, Data 분야, 추천 서비스 AI 까지 파고들 수 있다. 

## 구체적인 범위들

보다 구체적으로 정리하자면 다음과 같다.

- 서버 개발(API, 비즈니스 로직, 라이브러리/프레임워크 개발)    
- DB 연동/관리/스키마 정의 등: 위에 서버 개발과 관련된 부분은 DBA가 아니어도 백엔드 개발자의 역할    
- 서버 및 기타 미들웨어 연동
    - ex) redis
- 테스팅, 통합(Integration), 배포(Deployment), 운영(Operation), 모니터링(Monitoring)     
- Architecture 설계: 확장성, 가용성, 비용효율성 고려   
    - 신규 프로젝트가 시작되면 이 업무를 시니어와 함께할 것 
- 등등..


<br>

## 과거와 달라진 업무 범위

과거에는 개발팀과 운영팀이 분리되었지만, 현재는 DevOps라는 문화로 향하고 있다. 

DevOps를 직군으로 많이 설명하지만, 사실상 '문화'다. 
- 아래 이미지에 적힌 대로 무한히 돌아가는 것을 말한다. 

![image](https://marvel-b1-cdn.bc0a.com/f00000000236551/dt-cdn.net/wp-content/uploads/2021/07/13429_ILL_DevOpsLoop.png)


<br>

## 협업 관계자들

주로 DevOps, Cloud Engineer, System Engineer와 함께 일하기 때문에 이 분야에 대해서도 알아야 한다. 


## 전체 프로세스

1. 설계 -> 2. 개발(구현) -> 3. 테스트 및 배포 -> 4. 운영 및 모니터링

- 설계: Application Design & System Design
    - System Design
        - 비용 최적화
        - 고가용성
        - 확장성

- 개발(구현)
    - 환경 구축 및 설정  
    - 프로그래밍  
    - 미들웨어 연동  
    - 테스트 코드 작성/유닛 테스트  

- 테스트 및 배포
    - 통합 테스트, 빌드, 배포
    - 이 단계를 통해서 서버를 몇 대 돌려야하는지 알 수 있다. 

- 운영 및 모니터링
    - 어느 시간대에 사용량이 많은지도 분석 등등
    - CPU 사용률이 떨어졌다. 

<br>

---

# 1.2 왜 배우는지

해야할 일이 매우 많기 때문에, 최대한 많은 것을 자동화하고, 외주화(있는 걸 잘 사용)해야 한다. 이를 위해서 Cloud를 배워야 한다. 
- 자동화는 DevOps의 핵심  

왜 클라우드를 배워야할까?
- 초기 투자 비용 없이 확장성 있는 설계 가능  
- 운영 비용 절감: 사용한 만큼만 지불  
- 확장 가능한 설계, 신뢰성 높은 설계
- 핵심 비즈니스에 집중  
- 빠른 시간 내에 글로벌 서비스 구현 가능  
- 레고 조립하듯 아키텍처 설계 가능  



<br>

---

# 1.3 DevOps & AWS/Cloud 소개

## DevOps란?

> 애플리케이션과 서비스를 빠른 속도로 제공할 수 있도록 조직의 역량을 향상시키는 **_문화 철학_**, **_방식 및 도구의 조합_**.  [from AWS]

- Develop + Operate의 합성어  
    - 설계-개발-테스트-배포-운영-유지보수를 옛날처럼 설계-개발-테스트 와 배포-운영-유지보수로 나누는 게 아닌 한 팀으로 하는 것  
    - 그래서 **_이 전체를 다 맡은 대신 서비스를 잘게 쪼갠다. 이게 바로 MSA다._**  


- 엔지니어 또는 해당되는 팀이 개발, 테스트, 배포, 운영에 이르기까지 전체 애플리케이션 수명 주기에 걸쳐 작업하는데, 이를 위해서 많은 것들을 자동화하고, 많은 도구나 플랫폼을 개발

- 문제를 빠르게 파악하고 수정 및 개선하기 위해서 logging & monitoring이 중요하다.  

<br>

## CI/CD

옛날에는 위에서 언급한 각 단계마다 팀을 쪼개서 커뮤니케이션 문제와 빠르게 확인할 수 없었다. 하지만 현재는 DevOps로 직군이 아닌 서비스로 쪼개기 때문에, **_빠르게 확인하고, 빠르게 반영해서, 빠르게 개선하자_** 는 방식으로 작업한다. 

이를 위해서 CI/CD 가 필요해졌다.

- CI(Continuous Integration): 자동 통합
    - test, build, dockerize
- CD(Continuous Deployment/Delivery): 자동 배포(전달)
    - remote push & run

아마존은 하루에 136,000번 배포되는데, 초당 1.6번 배포되고 있다.


## Monolithic VS MSA

> MSA는 단일 애플리케이션을 나누어 작은 서비스의 조합으로 구축하는 방법이며, 각 개별 서비스는 API로 연결된다.    
> - Martin Fowler -   

여러 코드 구조를 하나의 구조로 관리하는 게 Monolithic 이라 한다. 그래서 수십, 수백만줄 코드를 위에 아마존처럼 빌드/배포를 할 수 없다. 

그래서 코드를 서비스 기준으로 쪼개기 시작하면서, 전체 생명주기를 서로 긴밀하게 통합하기 시작했다. 즉, 이것이 DevOps이며, DevOps를 실현하기 위한 수단이 바로 MSA(Micro Service Architecture)다.  

서비스를 쪼개서 더 빠르게 문제 파악과 해결이 가능하고, 새로운 기술 도입이나 고가용성, 무중단 등에도 탁월하다.  

<br>

---

# 1.4 AWS 주요 개념과 서비스 소개  

## 왜 AWS인가?

다른 클라우드 서비스도 많은데 AWS(Amazon Web Service)를 많이 사용하는 이유는 압도적인 점유율 1위다. 

그러면 왜 점유율 1위인가? 

- 첫 번째, 제일 먼저 시작해서 점유율이 높고, 클라우드와 인프라는 한 번 결정되면 바꾸는데 비용이 매우 크다. 

- 두 번째, 지원 가능한 국가가 매우 다양하다. 

- 세 번째, 클라우드와 인프라는 규모의 경제 영향이 커서, 이 규모를 점차 늘리고 있어서 비용이 줄어들고 있다.

- 네 번째, cost를 점차 낮추고 있다.  
    - region이 많으면 적은 나라보다 더 싸다. 
    - 그래서 북미가 제일 싸다.  

하지만, Data Engineering의 관점에서 현재 GCP로 옮겨가고 있다.


<br>

## AWS 경험 우대

네카라에서는 private cloud(on-premise) 환경에서 회사별 클라우드 서비스를 사용하지만, 정작 채용할 때는 AWS 경험을 우대한다. 

요즘은 직군 구분치 않고 필수 또는 우대사항으로 AWS 경험을 언급한다.

<br>

## 주요 개념 설명

- Region
    - 전 세계의 데이터 센터를 뭉치는 물리적 위치  
    - 각 AWS 리전은 지리적 영역 내에서 격리되고, 물리적으로 분리된 다수의 AZ로 구성  
    - ex) US East, US West, Asia Pacific(Seoul) etc

- AZ
    - region을 구성하는 개별 데이터 센터
    - 단일로 사용하는 것보다 더 높은 가용성, 확장성, 안전성을 확보할 수 있다.  
    - 자연재해 등으로 안전하게 격리하기 위해서 모든 AZ는 서로 멀리 떨어져 있다.


    ![image](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.RegionsAndAvailabilityZones.html)

- Serverless
    - SaaS(Software-as-a-Service)형 서비스  
    - 인프라를 소유하거나 관리하지 않고, 서비스나 컴퓨팅 리소스를 사용할 수 있는 것  


- On-premise (private cloud)
    - 서버와 데이터 센터를 자체적으로 보유하고 관리하는 방식
    - Public cloud와 반대되는 개념  
    - 네이버, 카카오 등등

- PM(Physical Machine) <-> VM(Virtual Machine)
    - 물리 서버와 가상 서버의 차이  
    - VM: 하드웨어와 OS 상에서 하이버파이저(Hypervisor)라는 계층을 둬서 리소스를 공유하고 게스트 OS를 담을 수 있도록 만든 가상의 서버  


- VPC(Virtual Private Cloud)
    - 나만의 클라우드 네트워크 / 나만의 데이터센터  
    - VPC 내부에 서버, 게이트웨이, 가상 라우터, 가상 방화벽, VPN 등을 둘 수 있다.  
    - 네트워크 지식이 많이 필요

- IAM(Identity and Access Management)
    - 클라우드 리소스에 대한 접근을 안전하게 제어하고 관리하는 방식 또는 서비스  
    - Zero-trust를 기본 전제로 한 클라우드 환경에서 반드시 필요한 white list
        - 믿을 게 한 명도 없으니, 특정 사람만 허용하겠다. 


<br>

---


# 1.5 Old vs New (with Server vs serverless)

### 인스턴스 생성하기

- 지역: 서울 택하기
    - latency가 적어서 서울 선택한다.

- Ubuntu Server 22.04 LTS 프리 티어 사용 가능 선택
    - 22년 04 버전을 의미한다.  
    - 만약 프리 티어 외를 사용한다면 무엇을 선택하는가?
    - 이것보다는 '인스턴스 유형' 이 더 중요하다. 

- 인스턴스 유형: t2.small

- 키 페어 없이 계속 진행: 웹 브라우저로 편하게 진행하는 방법 사용

- 나머지는 default 유지

### 콘솔에 연결하기

생성된 인스턴스 선택 후, '연결' 클릭

'인스턴스 EC2 연결' 탭 클릭 후 '연결' 클릭

### 콘솔에서 가상환경 생성 후, 프로젝트 생성하기

아래 명령어를 순차적으로 입력

```yml
sudo apt update
sudo apt install -y python3.10-venv
mkdir nanodegree
cd nanodegree
python3 -m venv venv
source venv/bin/activate
pip install Django
django-admin startproject sample
cd sample
python manage.py runserver 0:8000
```

### 인바운드 보안 편집으로 8000번 포트 추가  

- 인바운드: 내 로컬에 들어올 수 있는 범위  
    - 소스: IPv4 anywhere  
    - 이 포트가 막혀있기 때문에, runserver PublicIPv4:8000으로 접속해도 데이터를 가져올 수 없었다.  


- 아웃바운드: 내 local에서 밖으로 갈 수 있는 영역

<br>

### 인스턴스의 PublicIPv4 복사하기

`python manage.py runserver 0:8000`을 입력한 후,  

`Public IPv4:8000` 을 입력하여 장고 서버가 돌아가는 걸 확인한다.


### 인스턴스 종료하기

서버가 올린 것을 확인했기 때문에, 생성했던 인스턴스를 종료한다. 이는 컴퓨터의 전원을 끈 것이다.  

<br>

---

---
# Reference 

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)