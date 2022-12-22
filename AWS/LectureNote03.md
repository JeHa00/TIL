# 0. Introduction

> 1. []()

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 lukas 강사님의 AWS 강의를 정리한 내용입니다.


<br>

---
# 1. Cloud Storage(데이터 저장소)

labmda는 stateless 하다. 하지만, 모든 것이 stateless일 수가 없기 때문에, cloud storage를 사용한다.  


## File Storage

> ex) Mac Finder, Window 탐색기

- 가장 쉽게 접할 수 있는 일반적인 형태의 스토리지

- 파일마다 메타데이터를 기반으로, 용량, 위치, 시간 등을 기록

- OS 내 파일 시스템과 연동  
    - ex) Finder, 탐색기, NAS

<br>


## Object Storage -> Cloud 개념

- 데이터를 객체(오브젝트) 단위로 저장
    - ex) AWS S3, Dropbox, google drive, naver mybox가 이런 종류

- 폴더 계층 구조 x 
    - 사용자 편의를 위해서 이름이 붙여 있고, 내부적으로는 x

- 평면 주소 공간에 오브젝트 별로 주소가 할당되어 바로 접근이 가능  
    - 오브젝트란 .pdf, .csv 같은 파일들을 의미 
    - HTTP API 접근 가능
    - 별도 라이브러리나 에이전트 없이 HTTP로 손쉽게 접근 가능하도록 열어준다.

- 객체 접근이 쉽고 빠르다.  


<br>

## Block Storage

- 파일을 쪼갠 데이터의 단위인 블록으로 처리하기 때문에, 촘촘하게 분산시켜서 저장한다. => 인접해서 저장할 필요가 없다.    

- 그리고 분산된 데이터를 모아서 읽을 수 있다.  

- 시스템적으로 많이 사용한다.  

<br>

## Summary

누군가 파일을 접근하면 다른 사람은 접근할 수 없다. : Strong Consistency
- RDB의 트랜젝션가 비슷

Eventual Consistency: 일관성 대신 가용성을 선택한 케이스
- 일관성이 깨져도 좋으니, 언제든 사용자들이 가져가서 사용할 수 있도록 하는 것 

<br>

## Amazon Lightsail

한 번에 다 가능하다.  

### 실습

웹 사이트를 위해 패키징한 것이다. 

apps + os 클릭 -> 워드프레스 선택 -> 인스턴스는 3개를 생성


- Session persistence is inactive: statafull 하게 연결

<br>

---

# 2. AWS Elastic Beanstalk 

### ppt 35
프로비저닝 즉, PaaS 서비스 제공


### ppt 38

RDS는 정말 많이 사용한다. 

### Ppt 39

동기적이라는 건 기본에서 저장하면 standby에 바로 반영되기 때문에 데이터 손실, consistency 문제 없다. 

데이터를 쓰는 작업보다 조회하는 작업이 매우 많이 비중을 차지한다. 즉 select 작업이 훨씬 많다.  

그래서 읽기 전용 load를 만든다.  

### Ppt 41

최종적으로 `eb --version`을 입력해서 EB CLI 3.20.3을 입력하면 완료


### 48

사용자를 생성해서 그룹를 부여 또는 그룹을 생성해서 사용자를 추가할 수 있다. 

### 52부터 Beanstalk에 올려서 서버에 배포하기

49 ~51은 혹시 여러 예외 상황이 발생할 경우를 대비해서 작성한 것
52번 명령어를 통해서 beanstalk 에 서버를 올려서 실행해보는 걸 실습해본다.

- `eb init` 입력하기
    - 이는 AWS console이 아닌 이번에는 cli 명령어를 통해서 실행한다.
    - 중간에 `aws-access-id`와 `aws-secret-key`를 입력할 때는 그룹 생성 시 복사한 것들을 입력한다.
    - 그 후에 브랜치 기본명칭에서 Username과 password는 커밋 시에 사용할 것이므로 등록한다.
        - 이미 등록했으면 안뜰 것이다.

- beanstalk와 CodeCommit 간의 관계: eb init을 사용해서 Launch Environment 를 구축했다. 내가 배포할 애플리케이션은 elastic에 올린 것이고, 이를 CodeCommit으로 관리할 것이다.  


<br>

---
# Reference 

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)