# 0. Introduction

> 1. [PostgreSQL 컨테이너로 배포하기](#1-postgresql-컨테이너로-배포하기)      
> 2. [도커 볼륨(docker volume)](#2-도커-볼륨docker-volume)   
> 3. [docker로 django 배포하기](#3-docker로-django-배포하기)   
> 4. [서버에 장고 코드 배포하기](#4-서버에-장고-코드-배포하기)  
> 5. [직접 짠 코드 docker container로 배포하기](#5-직접-짠-코드-docker-container로-배포하기)   
> 6. [docker로 nginx 배포하기](#6-docker로-nginx-배포하기)
> 7. [docker compose로 django와 nginx 함께 배포하기](#7-docker-compose로-장고와-nginx-함께-배포하기)     
> 8. [도커로 django, nginx, postgreSQL 함께 배포하기](#8-도커로-장고-nginx-postgresql-함께-배포하기)    

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.

- 인스턴스는 우분투를 사용한다.

<br>

---
# 1. PostgreSQL 컨테이너로 배포하기

### DB 관리

db를 컨테이너에 띄어서 관리하는가, 아니면 쌩 서버에 관리하는가 는 호불호가 갈린다. 

왜냐하면 컨테이너의 기본이 지워져도 상관없다는 전제이기 때문에, db는 지워지면 안되기 때문에 컨테이너에 사용되지 않는다. 

 ㅜ 
### 컨테이너 내부 접속하기
컨테이너 내부로 들어가기: `docker exec -it <Container ID> /bin/bash`
- 그래서 이 내부에서 명령어를 잘못 입력하면 컨테이너에서 나갔다가 Kill하면 된다. 

superuser로 로그인하기: `psql -U postgres` 를 의미한다. 

- `\dt`: 테이블 조회

postgresql 종료 명령어: `\q`

### Dockerfile이란?

- 도커 이미지에 대한 정보를 기술한 템플릿
- 패키지, 명령어, 환경 변수 설정등을 기록

<br>


### 도커 파일을 통해 postgreSQL container 띄우기

PostgreSQL에 대한 Dockerfile을 담을 directory를 생성한다.

해당 경로에 `vim Dockerfile`를 실행하여 `FROM postgres`를 입력한다.


<br>

---

# 2. 도커 볼륨(docker volume)

> **_도커 볼륨이란? 도커 컨테이너에 있는 데이터를 보관하기 위해서 사용하는 툴_**

도커 볼륨을 사용하는 방식에는 2가지 방식이 있다. 

- EC2 서버 내부의 file system 경로에 연결하는 것
- volume에 연결하는 것

위 방식들을 사용하면 file system과 계속해서 연결되어 있기 때문에, DB가 꺼져도 file system까지 다 저장되어 있어서 데이터를 보존할 수 있다. 

도커에서는 볼륨을 생성하여 사용하기를 추천한다. 

## 2.1 로컬 경로와 연결

![image](https://user-images.githubusercontent.com/78094972/206185981-93ac908c-1cae-4e9d-ba82-898ee0cb11b0.png)

- **이 방식의 문제점**  : 필요한 경로라 생각하여 사용자가 무심코 경로를 삭제할 수 있다.

<br>

### 연결할 호스트 경로 확인

> docker run -e POSTGRES_PASSWORD=mysecretpassword -v ~/test:/test:rw -d postgres

- `-v`: volume을 의미
- local 상의 `~/work/test`와 container 상의 `~/work/test`를 연결

- `rw`: read와 write 권한을 주겠다는 걸 의미  

- `-d`: background으로 작동한다는 걸 의미  



### container ls로 확인 후, 접속하기

- `docker container ls`  
- `docker exec -it <container id> /bin/bash`
- 접속하면 `/test`가 있는 걸 확인할 수 있다. 그러면 /test 경로로 이동한다(`cd test`).
- `ls`로 확인하면 postgresql에 접속하기 전과 동일하다는 걸 알 수 있다.

### 접속한 container에서 생성 후, docker host에서도 확인하기

- `mkdir inside` 후, exit하여 나온다.
- 동일한 경로로 이동하여 `ls`로 확인하면 `inside` directory가 있는 걸 알 수 있다.

- 생성한 directory를 docker host에서 삭제한 후, container에 접속하여 확인하면 똑같이 삭제된 걸 확인할 수 있다.

<br>

## 2. 도커 볼륨으로 연결하기

![image](https://user-images.githubusercontent.com/78094972/206185086-a0071191-96ea-4b05-a7a6-0b9f740107a9.png)

### 2.1 생성 후, 내용 확인하기 


첫 번째, 명령어 `docker volume create <볼륨명>`을 사용하여 볼륨을 ec2 내에서 생성한다.

생성 후, 볼륨 목록을 확인하고 싶으면 `docker volume ls`를 입력한다. 

생성한 볼륨에 대한 정보를 확인하고 싶으면 `docker volume inspect <생성한 볼륨명>`을 입력한다.
- 확인된 정보들에서 `Mountpoint`는 생성한 볼륨의 경로를 의미한다.


### 2.2 docker host의 경로와 연결하기

> docker run -e POSTGRES_PASSWORD=mysecretpassword -v myvolume:/var/lib/postgresql/data -d postgres

myvolume을 postgresql의 데이터 경로와 연결한다. 

❗️/var/lib/postgresql/data 는 내가 임의로 정하는 게 아닌 RDBMS마다 정해져있으므로 다른 것을 사용한다면 추가로 확인해야한다.

### 2.3 postgresql 데이터 생성 및 container 삭제 후, 다시 실행하여 데이터 확인하기

- container 접속: `docker exec -it <postgresql에 해당되는 container id>`
- postgresql 접속: `psql -U postgres`
- 사용자 추가: `CREATE USER jeha PASSWORD '1234' SUPERUSER;`
- `\du`: 모든 사용자 보여주기 
- `\q`: 나오기
- container 중단: `docker container stop <container id>`
- container 삭제: `docker container rm <container id>`
- 다시 postgresql 실행하여 접속한다.
- `\du`로 등록된 사용자가 아직 존재하는지 확인 후, exit

<br>


----

# 3. Docker로 django 배포하기

## 3.1 Django image pull 받고 확인하기

- django image pull 받기: `docker pull django`   
- django pull 한 image 확인: `docker image ls`   



<br>

## 3.2 장고 컨테이너 가동

### 배포시스템 구조 이해하기

> **_서버 포트와 컨테이너 포트 연결하기_**

- 트래픽은 컨테이너로 바로 들어오는 게 아닌, 서버를 거쳐서 컨테이너로 들어온다. 총 2단계가 필요하다.

- 우리가 브라우저의 url 창에 도메인을 입력하고 나서 입력하는 포트 번호는 컨테이너의 포트 번호가 아니라 '서버의 포트 번호'를 말한다. 

- Port 번호 
    - 내가 사용하는 프레임워크는 django 이므로, port 번호는 8000을 사용하기로 한다.
    - HTTP port는 80으로 정해져있다. 
    - `www.naver.com` 을 입력하는 것은 `www.naver.com/:80` 을 입력하는 것과 동일하다. 

- 그래서 80 port를 지난 후, 8000 port로 오도록 연결해야 한다. 이를 설계하지 않으면 컨테이너를 띄어도 외부에서 접속할 수 없다.


<br>

### 장고 컨테이너 가동하기

> docker run -d -p 80:8000 django bash -c "pip install django && django-admin startproject <project name> && cd <project name> && python manage.py runserver 0.0.0.0:8000" 

- `-d`: 도커 컨테이너의 백그라운드 실행  

- `-p 80:8000`: 컨테이너 포트 설정 - 호스트 포트: 컨테이너 포트
    - 두 포트를 연결하겠다는 의미

- `bash`: 컨테이너 실행 후, bash 실행  
    - bash에 "" 안 내용을 실행한다. 
    - bash를 사용해서 장고 프로젝트를 생성한다.

- `&&`: 연결지어 순차적으로 실행한다.

- `python manage.py runserver 0.0.0.0:8000` 에서 위에 `-p` 옵션으로 입력할 포트번호와 일치시켜야 한다.

- 만약 해당 포트가 이미 사용 중이라고 한다면 `ps -ef | grep 8000`을 입력해서 8000번에 무엇이 사용되는지 알 수 있다.


- 만약 `docker container ls`를 입력했는데 아무것도 안뜨면 보다 자세히 확인하기 위해서 `docker container ls -a` 를 입력하면 STATUS를 통해 확인하면 `Exited`를 확인할 수 있다. 그러면 아래 명령어를 사용하여 로그를 확인해보면 에러 원인을 보다 자세히 알 수 있으므로 활용해보자.

- 로그 확인: `docker logs <container ID>`  

❗️ Error starting userland proxy: listen tcp4 0.0.0.0:80: bind: address already in use.

`sudo netstat -nlptu | grep 80`을 입력해보자. 만약 `command not found`가 뜬다면 `apt-get install net-tools`를 입력하여 설치 후, 다시 실행해보자.

그러면 80 port를 누가 사용 중인지 확인할 수 있다.

만약 그 포트를 사용하는게 nginx, apache라면 `sudo systemctl stop nginx` 또는 `sudo systemctl stop apache` 을 입력하여 중단하자.

그후 다시 장고 컨테이너 가동하기 위한 명령어 `docker run -d -p 80:8000 ...`을 입력한다. 

nginx, apache가 아니라면서 `sudo kill -9 <PID>` 를 입력한다.



<br>

### 3.3 docker container 정지 및 재가동

- 정지: `docker container stop <container ID>`

- 재가동
    - `docker container ls -a`로 정지했었던 container id를 확인
    - 멈춰있는 도커를 재시작한다는 의미이므로, `docker start <container id>`를 입력

<br>

---


# 4. 서버에 장고 코드 배포하기  

### 4.1 AWS 외부 접속을 위한 포트 설정

- 해당 EC2의 인바운드 규칙 편집에 사용자 지정 TCP로 '8000'을 추가한다. 

<br>

### 4.2 장고 프로젝트 생성 및 가동

- root 계정으로 이동: `sudo -i`

- `work`로 이동

- `pyenv`는 아래 링크를 따라서 진행하여 설치 및 활성화한다.
    - https://losskatsu.github.io/programming/pyenv/#5-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EA%B0%80%EC%83%81%ED%99%98%EA%B2%BD-%EC%84%A4%EC%B9%98

- 가상환경 실행: `pyenv activate <가상환경명>`
    - pyenv 말고, ec2에 설치된 게 있으면 그것으로 하면 된다. 


- 프로젝트 명칭: `django-admin startproject <프로젝트 명>`

<br>

### 4.3 장고 설정파일 수정 및 접속 확인

- settings.py 에서 ALLOWED_HOSTS 를 `['*']`로 수정한다. 이것의 의미는 모든 외부 접속을 허용한다는 의미다.

- 수정할 때에만 `vim`을 사용하고, 그냥 볼 때는 `cat`를 사용한다. 

- `python manage.py runserver 0:8000` 를 입력해야 ec2 ip 주소를 사용해서 `<IP v4>:8000` 로 접속할 수 있다.


## ❗️ 발생한 Error

> ModuleNotFoundError: No module named '_sqlite3'

sqlite3는 파이썬 설치 시, 포함되는데 위와 같은 에러는 파이썬 설치가 제대로 안된 것이기 때문에, 

`pyenv install <python version>`을 입력하여 설치를 다시 한다.


<br>

---

# 5. 직접 짠 코드 docker container로 배포하기

## 5.1 디렉토리 정리, tree 설치하여 파일 구조 확인하기

> sudo apt-get install tree

- 위 명령어 실행 후, `tree ./`로 원하는 경로의 directiory 구조를 tree로 확인한다.

<br>

## 5.2 requirements.txt 파일 생성

- 이 파일을 추가하는 이유는 나만의 도커 이미지를 만들기 위해서, django 4.1.1 layer를 쌓겠다는 의미다. 
- 왜냐하면 도커는 여러 Layer가 쌓여진 것이기 때문이다. 

- `vim requirements.txt`를 실행하여 생성한다. 
    - `django==4.1.1`를 추가한다.

<br>

## 5.3 Dockerfile 생성하기

### Dockerfile 생성하기

- 위 장고 과정에서 직접 하나하나 설치하고 실행한 건 도커 개념을 이해하기 위해서다.

- requirements.txt만 실행하면 바로 설치된다.

```yml
FROM python:3.10.4

WORKDIR /usr/src/app

COPY . . 

RUN python -m pip install -upgrade pip
RUN pip install -r requirements.txt 

WORKDIR ./practice

CMD python manage.py runserver 0:8000

EXPOSE 8000
```

- 이 때 생성해야하는 Dockerfile 이름은 다음과 같다.
    - `Dockerfile`
    - 주의사항: 첫 글자가 대문자이고, 확장자가 존재하지 않는다.

- `FROM`: 기본 layer를 무엇으로 할 것인가?
    - `python:3.10.4`: 기본 Layer를 이것으로 한다.

- 첫 번째 `WOKDIR`: root부터 시작하는 container 내부 경로를 의미한다. 

- `COPY . .`: 모든 것을 컨테이너 안으로 가져오겠다는 의미다.

- `RUN`: 오른쪽에 입력한 명령어를 실행하겠다. 

- 두 번째 `WORKDIR`: 위에 RUN 명령어를 실행 후, 오른쪽에 입력한 경로로 이동한다. 

- `EXPOSE`: 컨테이너에 포트 몇 번을 열지를 알려주는 것 

<br>

### Dockerfile 의 file level

- Dockerfile과 requirements.txt는 동일한 파일 level에 있어야 한다.

![image](https://user-images.githubusercontent.com/78094972/205901668-9b0a42b2-a803-4037-9d54-cbd8be15ac37.png)

<br>


## 5.4 image build 하기

`docker build . -t <만들 image 이름>`

Dockerfile이 있는 위치에서 실행한다.

- `.`은 위 명령어를 실행하는 경로 안에 있는 모든 걸 의미한다.

- `-t`: 태그를 의미

- `docker image ls`를 입력하면 목록에 위에 입력한 image 이름으로 존재하는 걸 확인할 수 있다.

❗️ 컨테이너는 운영체제가 아니기 때문에, root User 관련 경고는 문제가 아니다. 
❗️ 만약 image를 빌드 후, Dockerfile을 수정하면 다시 image를 만들어야 한다.

<br>

### 빌드한 이미지 실행하여 컨테이너 가동

`docker run -d -p 80:8000 <만든 image 이름>`

위 명령어 실행 후, `docker container ls`를 입력하면 가동되고 있는 container를 확인할 수 있다.


❗️ 위 명령어로 container id가 떴음에도 불구하고, `docker container ls -a`로 해당 container id의 status가 `exited` 이면 `docker logs <container id>`로 원인을 파악한다.

<br>

---

# 6. docker로 nginx 배포하기

django만으로는 traffic을 받을 수 없기 때문에, nginx를 설치해야한다.

## 6.1 nginx 디렉토리 생성

`mkdir nginxtest` 생성

<br>

## 6.2 Dockerfile 생성

- 위에 생성한 디렉토리로 이동 후, `vim Dockerfile`로 생성하여 아래 내용을 입력한다.

❗️ 입력 시, 홀따옴표가 아닌 쌍따옴표를 사용한다.

```yml
FROM nginx
CMD ["nginx", "-g", "daemon off;"]
```

- `daemon off`의 의미
    - nginx를 foreground로 돌리기 위함  
    - container가 background로 실행되므로, nginx를 foreground로 돌리지 않으면 nginx가 exited된다. 


### file level

![image](https://user-images.githubusercontent.com/78094972/205932391-12e25d2c-19cc-4a0e-8c49-299f69e035f8.png)

<br>

## 6.3 image build 및 확인

### image build
Dockerfile이 있는 경로에서 아래 명령어를 실행한다.

`docker build . -t nginx-test`

### image 확인

`docker image ls`를 실행하여 목록을 확인한다. 이 때 경로는 상관없다.


<br>

## 6.4 nginx container 가동

### container 가동

`docker run -d nginx-test`

### 목록 확인

`docker container ls`


<br>

---

# 7. Docker compose로 장고와 nginx 함께 배포하기  


![image](https://user-images.githubusercontent.com/78094972/206096399-d5734708-c12b-499e-a8a4-f83905ea371c.png) 

위 이미지처럼 컨테이너 두 개를 연결하려고 한다.
- 서버 외부에서 80포트로 들어오면 nginx로 이동하면 django-test 8000 port로 이동되도록 한다.

nginx: 서버 와 django: 웹 서버를 연결하기 위해서 gunicorn을 사용해볼 예정이다.

이를 위해서 docker compose를 사용해보자.    

<br>

## 7.1 Docker compose 개념 & 설치

### Docker compose concept

django과 nginx를 연결해야하는데, 많은 컨테이너를 하나 하나 직접 연결하는 건 매우 시간이 많이 걸린다.

도커 파일은 '하나의 컨테이너' 를 관리하기 위한 파일이다.

하지만, **도커 컴포즈는 '여러 개의 컨테이너'를 관리하는 도커 애플리케이션**이다.

- 도커 컴포즈를 사용하기 위해서는 docker-compose.yml이라는 YAML 파일을 사용한다.

<br>

### YAML

- YAML은 마크업 언어가 아니다.

- 기존 JSON의 불편함을 해소하기 위해 만들어진 언어

- 주로 설정 파일(configuration file)에 사용  

- 확장자: *.yml

<br>


## 7.2 Docker compose installation

[docs.docker.com - install the plugin manually](https://docs.docker.com/compose/install/linux/#install-the-plugin-manually)를 보고 아래 명령어를 순차적으로 입력한다.

```yml
mkdir -p ~/.docker/cli-plugins

curl -SL https://github.com/docker/compose/releases/download/v2.14.0/docker-compose-linux-x86_64 -o ~/.docker/cli-plugins/docker-compose

chmod +x ~/.docker/cli-plugins/docker-compose

docker compose version
```

### ❗️ Error

> docker: 'compose' is not a docker command.

curl -o 뒤에 경로와 mkdir의 경로가 일치한지 확인하기



<br>

## 7.3 directory 정리

다음과 같이 경로를 수정한다.

```yml
./
├── django
│   ├── Devket
│   │   ├── config
│   │   │   ├── __init__.py
│   │   │   ├── __pycache__
│   │   │   │   ├── __init__.cpython-310.pyc
│   │   │   │   ├── settings.cpython-310.pyc
│   │   │   │   ├── urls.cpython-310.pyc
│   │   │   │   └── wsgi.cpython-310.pyc
│   │   │   ├── asgi.py
│   │   │   ├── settings.py
│   │   │   ├── urls.py
│   │   │   ├── wsgi.py
│   │   │   └── ~
│   │   ├── db.sqlite3
│   │   └── manage.py
│   ├── Dockerfile
│   └── requirements.txt
└── nginx
    └── Dockerfile
```

<br>



## 7.4 Dockerfile 수정


### django directory 안에 있는 dockerfile 수정
첫 번째 `CMD` 뒤에 다른 `CMD`를 추가한다.

`CMD gunicorn config.wsgi:application --bind 0.0.0.0:8000`


### nginx directory 안에 있는 dockerfile 수정

다음과 같이 수정한다.

```yml
FROM nginx
RUN rm /etc/nginx/conf.d/default.conf
COPY default.conf /etc/nginx/conf.d
CMD ["nginx", "-g", "daemon off;"]
```

- 기존 컨테이너에 있던 default.conf를 삭제하고, 새로운 default.conf를 기존에 있던 경로에 만들겠다는 의미다.


<br>

## 7.5 nginx에 default.conf 추가

```yml
server {
	listen 80;
	server_name localhost;

	location /{
		proxy_pass http://<nginx에 연결할 image 이름>:8000;
	}
}
```

- `listen 80 `: 서버로부터 들어오는 것을 80 port로 받겠다는 의미
- `proxy_pass`: 80 port로 들어온 것을 8000 port로 돌리겠다는 의미

<br>


## 7.6 docker-compose.yml

```yml
# docker-compose.yml이 있는 경로: ~/test

version: "3"
services:
    <첫 번째 service name>:
        build: ./<django Dockerfile이 있는 directory name>
        container_name: <생성될 image name>
        restart: always
        expose: 
            - "8000"

    <두 번째 service name>:
        build: ./<nginx Dockerfile이 있는 directory>
        container_name: <생성될 image name>
        restart: always 
        ports: 
          - "80:80" 
        depends_on: 
          - <첫 번째 service name>
```

- `version`: version에 따라 참고하는 규격이 달라진다.
    - [About versions and upgrading](https://docs.docker.com/compose/compose-file/compose-versioning/#version-3)
- `services` 밑에 바로 오는 것은 여러 서비스 명칭들을 의미
- `expose`: container 상에 몇 번 포트를 열지 지정
- `ports`: 서버에서 들어와 바로 받기 때문에 입력해야 한다.
- `depends_on`: service 간 관계성을 지정하는 키워드로서, 실행 순서를 정하는 키워드로 depends_on에 있는 service를 실행한 후, 해당 service를 실행
- 생성될 image name은 현재 `test` 경로에서 실행되었기 때 문에, `test-<생성될 image name>`으로 image가 생성된다.


<br>


## 7.7 docker compose 실행 및 내리기

### docker compose 실행하기

> docker compose up -d --build


### docker compose 내리기

> docker compose down


<br>

---
# 8. 도커로 장고, nginx, PostgreSQL 함께 배포하기

전체적인 서버 구성은 다음과 같다.

![image](https://user-images.githubusercontent.com/78094972/206230297-44fc1f1a-226e-4173-8e4d-3bbaf88c1764.png)

그리고, 컨테이너의 관점에서 바라보면 전체적인 구성은 다음과 같다.

![image](https://user-images.githubusercontent.com/78094972/206230121-50f10a96-878f-4fc1-8f32-acdd353b61ba.png)

<br>

### gunicorn을 선택한 이유

gunicorn을 사용한 이유는 현재 과정에서는 빠른 속도가 불필요하기 때문이다. 빠른 속도가 빠른 성능이 필요하면 wsgi를 쓴다. 

<br>

### ❗️현 단계에서 Error가 발생되면

 컨테이너가 잘못되었는지 File system이 잘못되었는지 구분해야 한다.

<br>

## 8.1 file directory 구조

- django, nginx, psql이 docker-compose.yml과 동일한 file level이어야 한다. 

- 그리고 django, nginx, psql에는 각 해당되는 Dockerfile이 존재해야한다. 

- django에는 requirements.txt가, nginx에는 default.conf가 추가로 존재해야 한다. 

```yml
./
├── django
│   ├── Devket
│   │   ├── config
│   │   │   ├── __init__.py
│   │   │   ├── __pycache__
│   │   │   │   ├── __init__.cpython-310.pyc
│   │   │   │   ├── settings.cpython-310.pyc
│   │   │   │   ├── urls.cpython-310.pyc
│   │   │   │   └── wsgi.cpython-310.pyc
│   │   │   ├── asgi.py
│   │   │   ├── settings.py
│   │   │   ├── urls.py
│   │   │   ├── wsgi.py
│   │   │   └── ~
│   │   ├── db.sqlite3
│   │   └── manage.py
│   ├── Dockerfile
│   └── requirements.txt
├── docker-compose.yml
├── nginx
│   ├── Dockerfile
│   └── default.conf
└── psql
    └── Dockerfile
```

<br>

## 8.2 docker-compose.yml 수정하기

DB 추가에 따른 docker-compose.yml은 다음 내용이 추가된다.

```yml
version: "3"
services:
    
    ...
    
    psql:
        build: ./psql
        container_name: psql
        restart: always
        volumes:
          - myvolume:var/lib/postgresql/data
        environment:
          - POSTGRES_USER=postgres
          - POSTGRES_PASSWORD=postgres
          - POSTGRES_DB=postgres

volumes:
    myvolume:
```

- psql-test에는 db이기 때문에 volume이 추가된 걸 알 수 있다. 


- 만약 용량이 부족하다는 error가 뜰 경우 `docker system prune --volumes` 명령어를 상용해보자. 

<br>

## 8.3 django settings.py의 DB 설정 변경하기

- 그리고 django application의 settings.py의 DATEBASES 환경 변수를 수정한다. 

```python
# before
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': BASE_DIR / 'db.sqlite3',
    }
}

# after
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': 'postgres',
        'USER': 'postgres',
        'PASSWORD': 'postgres',
        'HOST': 'db-test',
        'PORT': 5432,
    }
}
```

<br>

## 8.4 requirements 변경 후, compose 실행하기

- requirements.txt에 `psycopg2`를 추가 
- compose 실행: `docker compose up -d --build`


❗️ invalid mount config for type "volume": invalid mount path: 'var/lib/postgresql/data' mount path must be absolute

'var/lib/postgresql/data' 앞에 `/`가 없어서 생긴 문제다.

❗️strconv.Atoi: parsing "": invalid syntax

`sudo docker system prune`을 실행한다.

<br>

---


# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)