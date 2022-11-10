# 0. Introduction

> 1. [서버에 PostgreSQL 설치하기]() .     
> 2. [docker로 django 배포하기](#1-docker로-django-배포하기)   
> 3  [docker로 nginx 배포하기](#)   
> 4. [docker compose로 django와 Nginx 함께 배포하기]()     
 

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.


<br>

---
# 1. Docker로 django 배포하기

## 1.1 Django image pull 받아서 container 가동

### 1.1.1 장고 이미지 Pull 받기

> docker pull django

<br>

### 1.1.2 pull로 받은 장고 이미지 확인

> docker image ls

이미지는 클래스며는 컨테이너는 인스턴스다. 

이 이미지를 띄우기 위해서는 컨테이너를 가동해야 한다.

밖에서 들어오는 트래픽을 받을 수 있어야 한다.

그런데, 트래픽은 초기에는 80port 를 통과하고 8000Port로 오도록 해야한다. 

이미지 상에서 화살표 단계 설계를 하지 않으면 웹서버를 띄어도 연결되지 않는다.

80번 포트는 웹 애플리케이션의 기초 강의를 보기 

웹 서버를 만들 때도 포트 번호를 맞춰야 한다. 

80번 포트로 요청하고, 화면을 그대로 받아서 브라우저에 보여주면 된다. 

하지만, 우리는 컨테이너를 사용하기 때문에 바로 볼 수 없고 컨테이너가 8000번 포트를 가지고 있기 떄문에, 이를 실행시킬 수 있어야 한다. 여기서 8000은 임의로 정한 것이다. 

컨테이너 이름으로는 django-test로 입력했고, 8000포트로 열었고, 80번과 연결해야한다!

80번과 연결하는게 포인트다.

www.naver.com/ 을 그냥 입력하는 것은 www.naver.com/:80 을 입력하는 것과 동일하다. 


<br>

### 1.1.3 장고 컨테이너 가동

> docker run -d -p 80:8000 django bash -c "pip install django && django-admin startproject test01 && cd test01 &&python manage.py runserver 0.0.0.0:8000" 

- `-d`: 도커 컨테이너의 백그라운드 실행  

- `-p 80:8000`: 컨테이너 포트 설정 - 호스트 포트: 컨테이너 포트


- `bash`: 컨테이너 실행 후, bash 실행  
    - bash에 "" 안 내용을 실행한다. 

- `&&`: 연결지어 순차적으로 실행한다.

- `python manage.py runserver 0.0.0.0:8000` 에서 위에 `-p` 옵션으로 입력할 포트번호와 일치시켜야 한다.

- 만약 해당 포트가 이미 사용 중이라고 한다면 `ps -ef | grep 8000`을 입력해서 8000번에 무엇이 사용되는지 알 수 있다.


- 만약 `docker container ls`를 입력했는데 아무것도 안뜨면 보다 자세히 확인하기 위해서 `docker container ls -a` 를 입력하면 STATUS를 통해 확인하면 `Exited`를 확인할 수 있다. 그러면 로그를 확인해보자. 

- 위에 명령어를 입력했을 때 뜨는 container ID 를 입력해보자. 
- `docker logs 408e61963ab3` 그러면 다음과 같이 뜬다.

    ```yml
    bash: line 1: djang-admin: command not found
    ```

    - 오타임을 알 수 있다.


- docker에서 오류가 뜨면 위와 같은 방식으로 접근하여 원인을 알아보자. 

<br>

### 1.1.4 docker container 정지 

> docker container stop fdcf4d091478

<br>

---
## 1.2. 서버에 장고 코드 배포하기  





### 1.2.1 AWS 외부 접속을 위한 포트 설정

- 해당 EC2의 인바운드 규칙 편집에 사용자 지정 TCP로 '8000'을 추가한다. 


<br>

### 1.2.2 장고 프로젝트 생성

- `work`로 이동

- 가상환경 실행: `pyenv activate py3_10_4`
    - pyenv 말고, ec2에 설치된 게 있으면 그것으로 하면 된다. 

- `pyenv`는 아래 링크를 따라서 진행하여 설치 및 활성화한다.
    - https://losskatsu.github.io/programming/pyenv/#5-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EA%B0%80%EC%83%81%ED%99%98%EA%B2%BD-%EC%84%A4%EC%B9%98

sudo apt-get install python3.9
apt install python3-virtualenv

- `django-admin startproject practice`

<br>

### 1.2.3 장고 설정파일 수정

- DEBUG = TRUE

settings.py 에서 ALLOWED_HOSTS 를 `['*']`로 수정한다. 이것의 의미는 모든 외부 접속을 허용한다는 의미다.

수정할 때에만 `vim`을 사용하고, 그냥 볼 때는 `cat`를 사용한다. 


<br>

### 1.2.4 접속 확인

`python manage.py runserver 0.0.0.0:8000` 입력하여 접속하기  

<br>

---

### 파일 구조 확인하기

> sudo apt-get install tree

- 위 명령어 실행 후, `tree ./`로 원하는 경로의 directiory 구조를 tree로 확인한다.

### requirements.txt 파일 생성

- 이 파일을 추가하는 이유는 나만의 도커 이미지를 만들기 위해서, django 4.1.1 layer를 쌓겠다는 의미다. 
- 왜냐하면 도커는 여러 Layer가 쌓여진 것이기 때문이다. 

- `vim requirements.txt`를 실행하여 생성한다. 
    - `django==4.1.1`를 추가한다.


### Dockerfile이란?

- 도커 이미지에 대한 정보를 기술한 템플릿
- 패키지, 명령어, 환경 변수 설정등을 기록

### Dockerfile 생성하기

- 여태까지는 직접 하나하나 설치하고 실행했지만, 이는 도커 개념을 이해하기 위해서다. 

- requirements.txt만 실행하면 바로 설치되고 실행되도록 한다. 

- 이 때 생성해야하는 Dockerfile 이름은 다음과 같다.
    - `Dockerfile`

- `FROM`: 기본 layer를 무엇으로 할 것인가?
    - `python:3.10.4`: 기본 Layer를 이것으로 한다.

- `WOKDIR`: container 내부 경로를 의미한다. 

- `COPY . .`: 모든 것을 컨테이너 안으로 가져오겠다는 의미다.


- `RUN`: 옆에 명령어를 실행하겠다. 

- `WORKDIR`: 위에 RUN 명령어를 실행 후, 옆 경로로 이동한다. 

- `EXPOSE`: 컨테이너에 포트 몇 번을 열지를 알려주는 것 

<br>

내가 짠 코드를 사용할려면 빌드를 해야 한다.

`docker image ls`를 입력하면 내가 만든 docker image가 뜬다.

컨테이너는 운영체제가 아니기 때문에, root User 관련 경고는 문제가 아니다. 

### 도커 빌드한 이미지 컨테이너 실행

`docker run -d -p 80:8000 practice-test`

위 명령어 실행 후, `docker container ls`를 입력하면 가동되고 있는 container를 확인할 수 있다.

<br>

### image build 하기

`docker build . -t nignx-test`

- `-t`가 태그명을 의미하는데, 무엇에 대한 태그명을 의미하는가?

### container 실행

- `docker run -d nginx-test`: 컨테이너를 실행한다.

---

## Docker compose로 장고와 nginx 함께 배포하기  

nginx: 서버

django: 웹 서버

아직 서버와 웹서버를 연결하지 않았다. 이를 연결하기 위해서 gunicorn을 설치해야 한다. 

dockerfile과 dockercompose 차이도 이해하기

- 배포 시스템 구조 이해하기
    - 서버 외부에서 80포트로 들어오면 nginx로 이동하면 django-test 8000 port로 이동되도록 한다.

- 이를 위해서 docker compose를 사용해보자.    

컨테이너 안에 있는 default.conf를 삭제하고, 서버에 있는 것을 새롭게 컨테이너 안으로 복사한다. 


### Docker compose(도커 컴포즈)란?

도커 컴포즈는 여러 개의 컨테이너를 사용하는 도커 애플리케이션입니다. 

도커 컴포즈를 사용하기 위해서는 docker-compose.yml이라는 YAML 파일을 사용한다.


- YAML

# Section 4-4

### 새로운 디렉토리 생성 및 이동
2개를 디렉토리에 담은 이유는 통합해서 관리하기 위함이다.



### 
nginx와 django를 묶기 위해서 gunicorn을 사용한다.


### default_conf

### docker-compose.yml
build: build 위치
에서 version 3은 이 컴포즈의 버전을 의미.
중단될 때 재시작할 것인가?
expose: 포트 몇 번을 열 것인가?
depends_on의 의미: 선행 작업을 의미
- 원래라면 nginx가 받아서 Django에게 줄텐데 반대여야할 것 같은데, 이런 부분은 문법 만든 사람에 따라 다르기에 넘어가자. 

tab

docker compose up -d --build 는 docker-compose.yml이 있는 위치에서 실행한다.
- d: 백그라운드에서 실행
- build: 없으면 build해라.

compose file을 만들면 되므로 docker run을 할 필요가 없어졌다.

django만으로는 트래픽을 받을 수 없다. 

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)