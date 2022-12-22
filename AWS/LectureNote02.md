# 0. Introduction

> 1. [EC2 & Load Balancer 실습](#1-ec2--load-balancer-실습)  
> 2. [Serverless 서비스, Lambda 실습](#2-serverless-서비스-lambda-실습)

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 lukas 강사님의 AWS 강의를 정리한 내용입니다.


<br>

---
# 1. EC2 & Load Balancer 실습

## 1.1 전통적인 아키텍처

![image](https://user-images.githubusercontent.com/78094972/201865826-25d1692b-03a1-4e38-8e63-6a628ed01393.png)

### single server & cold/warm/hot standby

얼마나 빠르게 대체 투입 되는가??" 로서 가용성과 fault tolerance의 관점이다.

- Hot: 서버가 2개 다 떠 있으나, 하나만 트래픽을 받는다. 
- 장애가 발생했을 때, standby를 작동시키는 방식
- 분산 처리 관점에서 나눈 게 아니다.  

<br>

###  Multi server & load balancer

- 클라이언트와 서버 중간에 Load Balancer를 준다.
- 다양한 알고리즘에 의해서 트래픽을 여러 서버로 분산시켜주는 소프트웨어 또는 장비 
- 확장성 있는 설계에서 가장 중요한 것 중 하나인 scale-out을 가능케 한다.
    - 그래서 미리 서버를 증설하여 load balancer를 붙이면 평소보다 이론상 3배를 감당할 수 있다. 
- scheduling 방식: RoundRobin 방식 
- 가용성(HA: High Avality) 측면에서도 좋은 이유가 server 한 대가 고장나도 나머지 2대가 이를 감당할 수 있기 때문이다. 

<br>

### LB의 OSI 계층에서 부하 분산

- L3, L4 LB 
    - 3,4 계층을 기반으로 IP, TCP/UDP, PORT 번호로 부하 분산
    - 패킷을 까서 application 레벨까지 볼 수 없다. 

- L7 LB (Application LB)
    - application 계층으로 부하 분산 
    - API URL까지 봐서 routing을 시킬 수 있다.
        - API URL 경로 기반 분산    
    - MSA, kubernetes에서 사용하는 부하 분산

<br>

---

## 1.2 전통적인 아키텍처 실습

### 1.2.1 생성하기

- 인스턴스 이름: nano-degree 
- 종류: Ubuntu: 22.04 
- 인스턴스 유형: t2.micro 
- 생성할 인스턴스 갯수: 3개
    - 3개 모두 동일한 보안 그룹을 가지기 위해서

### 1.2.2 서버에 연결하기

LB에 최종적으로 연결하기 서버에 연결하기

위 전통적인 아키텍처 이미지처럼 서버 3개를 생성했다. 

1. 생성한 3개의 인스턴스를 각각 연결하여 3개의 터미널을 띄우기
2. 각 터미널에 `touch <number>`를 입력하기
    - 그러면 각 터미널에 `ls`를 입력하면 목록이 뜬다.

3. 기본적인 Python3 은 python 3.10이다. 
4. `python3 -m http.server 8000`: python으로 간단한 서버를 띄우는 것이다.
    - 각 서버에 띄우기

### 1.2.3 로드 밸런싱 -> 대상 그룹 택

1. 대상그룹 -> Instances 선택 

2. Target group name 자유롭게 입력

3. Protocol Port: TCP 8000

4. Next 선택 

5. 생성한 인스턴스 3개 클릭하고, 포트 8000번 입력하기

6. `Create target group` 선택


🔆 Health checks는 LB의 부하 분산 기능 뿐만 아니라 fault tolerance 기능을 위해 존재하는 것 

### 1.2.4 로드 밸런싱 -> 로드 밸랜서 택

1. `Network Load Balancer`create

- Gateway LB는 글로벌 서비스에서 사용한다.

2. LB name 입력

3. Network mapping 4개 선택 

4. Listners and routing TCP: 8000번 입력

5. 생성 후, 상태가 프로비저닝 중에서 '활성'이 될 떄까지 기다린다.

6. '활성'으로 변하면 생성한 LB를 선택한 후, 이에 해당되는 `DNS 이름` 을 복사하여 `DNS 이름:8000` 번을 입력하면 이 사이트로 이동된다.

7. 새로고침을 계속하면 제일 하단에 접속되는 서버가 바뀌는지 확인할 수 있다.

8. 그러면 1.2.2 에서 만든 터미널에서 접속되는 것을 확인할 수 있다.  

9. 1.2.2 중 하나의 서버를 끄면 `로드 밸런싱 > 대상그룹 > 대상 그룹 클릭 후, 하단의 Targets` 에서 Health status가 Unhealthy로 바뀐다.


🔆 **LB에 연결해서 가지는 효과**

서버 3개를 띄어서 LB에 연결했다. 

이런 상황에서 클라이언트는 어느 서버에 분산됬는지 모른다. 

만약 하나의 서버가 다운되면 신규 사용자가 들어오면 어떤 일이 일어날까?

이 다운된 서버에 접속되지 않도록 LB 목록에는 다운된 서버를 지우고 나머지 2개의 서버로 분산시킨다.


### 실습 완료되면 리소스 삭제하기 

- 보안그룹, 로드밸런서를 다른 실습을 위해 삭제한다.


<br>

---
# 2. Serverless 서비스, Lambda 실습

- 인프라 없이 일부 서비스 운영이 가능하다. 

- Serverless/FaaS형 서비스와 API Gateway와 함께 간편하고, 안정적이고, 확장성 있는 설계가 가능하다.

- MSA에서 일부 차용

## AWS Lambda 와 S3

### AWS Lambda

- FaaS(Function as a Service): function 단위로 실행되고 꺼지는 서비스  
    - 또는 Serverless computing이라고도 한다. 

- 인프라 관리 없이 코드만으로 실행 가능

- 코드는 브라우저에서 업로드하거나, S3에서 불러올 수 있다.  

- 함수 단위로 실행되므로 초당 수십만 개의 요청까지 처리 가능하다.

- ms 단위로 사용하는 컴퓨팅 시간에 대해서만 비용을 지불한다.  

- 함수가 한 번 실행하고 꺼지는 구조이기 때문에 stateless한 구조다.
    - 요청이 오면 켜지고, 끝나면 꺼지고를 반복한다.
    - 만약 statefull 구조를 원한다면 별도의 storage가 필요

- 무언가 함수를 호출하면 실행되는 구조 (triggering의 예: API GW, S3, EventBridge 등) 

<br>

### AWS S3: Simple Storage Service

> AWS의 수 많은 서비스를 엮어주는 허브 역할  

- cloud storage 중 하나인 object storage

- 정형/비정형 데이터를 모두 저장할 수 있는 저장 공간 역할

- 수많은 서비스와 쉽게 연동하는 anchor point 역할을 수행  

- 다양한 스토리지 클래스 / 관리 기능 / 로깅 및 모니터링 / 보안 / 규정 준수

- 무한대의 확장성

- 데이터 내구성은 eleven nine 이다.
    - 99.999999999%: AWS S3로 망가지는 경우 x 

- bucket: 저장소 
- 데이터 보관 정책, 버저닝 등을 통해 데이터 거버넌스에 큰 도움을 준다.
    - 그래서 데이터 엔지니어링에 많이 사용 

- 스토리지 클래스가 있어서, 클래스 별로 가격을 나눈다.


<br>

---

## AWS Lambda 실습  

### 함수 생성

- 새로 작성 -> 이름 작성 -> 런타임: Python 3.9 선택 -> `함수 생성`

- Deploy를 클릭하여 저장되어 손실되지 않는다. 저장 후, 이 버튼이 활성화되지 않아야 한다. 

<br>

## AWS S3 실습

- 버킷 생성하기  

- 버킷 클릭하여 들어가면 `관리` 탭에 들어가보자.
    - 복제 규칙은 일정 기간이 지나면 데이터를 복사하는 기능을 한다. 
        - 로그 복사를 수행한다.


## Lambda에서 bucket name 입력하여 로그 함수 실행

Test 버튼 클릭 -> 이벤트 이름 입력 -> 테스트 이벤트 구성: hello-world - input parameter를 의미하는데, 이를 클릭 후 빈 값을 입력한다.

실행하면 Error가 뜬다. 

왜냐하면 IAM 때문이다. 만들어 놓은 람다 함수는 S3에 접근하는 구너한이 없기 때문에, IAM에 등록해야 한다. 

권한 -> 구성 -> 역할이름: 자동적으로 생성된다. 

이를 클릭해서 들어가보자. 

??? Lambda에서 S3에 접근 권한이 없기 때문에 권한 부여한다.
- 보안/인증/인가 등으로 실무에서 개발자를 가장 괴롭히는 것 중 하나: IAM

- 권한 추가 -> 정책 연결 -> s3 검색 -> AmazonS3FullAccess를 클릭 -> 정책 연결 클릭

- 다시 Lambda로 와서 test를 실행 -> Success -> S3에서 로그 파일이 생성된 걸 확인할 수 있다. 즉, 람다가 S3에 접근하여 작성한다는 걸 알 수 있다.  

<br>

---
# Reference 

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)