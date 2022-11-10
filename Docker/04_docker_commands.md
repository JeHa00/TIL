# 0. Introduction


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.

- docker의 기본 명령어들에 대해 학습합니다.  

<br>

---

## docker image 확인

- docker image 확인: `docker image ls` 

<br>

---

## docker container 확인

- 작동 중인 컨테이너 확인: `docker container ls` 

- 모든 컨테이너 확인: `docker container ls -a` 

<br>

---

## Containerd 확인

1. 관리자 계정으로 로그인: `sudo -i` . 

2. `cd /run/docker`

3. `ll` 을 사용하여 목록 확인
    - `ls -a` 와 동일한 기능  

<br>

---

## docker image pull 하기

- 예를 들어 ubuntu image를 설치한다고 한다면 다음 명령어를 사용한다.
    - `docker pull ubuntu`

- pull된 도커 이미지 확인
    - 루트 계정으로 로그인: `sudo -i`
    - 특정 directory로 이동: `cd  /var/lib/docker/image/overlay2/imagedb/content/sha256`
    - 목록 확인: `docker image ls`

<br>

---

## docker image를 container로 실행하기  

- container
    - 위 명령어를 실행하면 image는 container로 변화되어 하나의 인스턴스가 된다.   
    - 실행 상태의 컨테이너는 IP 주소를 가지는 하나의 독립된 서버처럼 동작한다.  
    - 각 컨테이너는 고유의 IP 주소와 포트 번호를 가지고 있다.    
    - 컨테이너는 이전 이력과 관계없이 실행할 때마다 새로운 IP 주소를 가지게 된다.    

<br>

---
## 컨테이너 네트워크: 2개 이상의 터미널을 킨 상황


### (터미널 1)컨테이너 내부에서 bash shell 실행하기: 

- `docker run -it ubuntu`
- i(interactive) 옵션: 키보드 입력을 컨테이너 입력에 연결해 컨테이너 shell에 보낸다.  
- t(tty) 옵션: 터미널을 통해 대화형 조작이 가능하게 한다.  


### (터미널 2)새로운 터미널을 열고 컨테이너 ps 확인: 
- `docker container ps`
- STATUS를 보면 exited가 아니다. 왜?


### 서로 다른 터미널에 접속 및 종료 시키기

- 현재 터미널 상황: Docker host가 동작 중인 터미널 1, 2에서 터미널 1만 container 실행 중

- 터미널 2에서 터미널 1에 실행 중인 컨테이너 접속하기: `docker exec -it <container id> bash`
    - 여기서 `container id`란 터미널 1에서 실행 중인 컨테이너를 의미한다.  
    - 터미널 2에서 `docker container ls` 명령어로 실행 중인 컨테이너 id를 확인 후, 이를 입력하여 접속한다.  

- 터미널 2에서 터미널 1에 실행 중인 컨테이너 종료 시키기  
    - `docker stop <container id>` 


<br>

### 호스트 <-> 컨테이너 간 파일 전송 -> 영상 보기

- 현재 터미널 상황: Docker host가 동작 중인 터미널 1, 2에서 터미널 2만 container 실행 중

- 도커 이미지 변경 후 저장하기  
    - (터미널 1) `docker run -it ubuntu bash`
    - (터미널 2) `docker run -it ubuntu bash` -> 교재랑 설명이 안맞음
    - (터미널 1) `ifconfig`
    - (터미널 1) `apt-get update && apt-get install net-tools`
    - (터미널 1) `ifconfig`
    - (터미널 2) `docker container ls`
    - (터미널 2) `docker commit <container id> my-ubuntu:0.1`
    - (터미널 2) `docker image ls`


- Docker host -> container로 파일 전송

- container -> Docker host로 파일 전송

<br>

---

<br>

---

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)