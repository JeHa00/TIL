# 0. Introduction

> 1. [Docker Installation](#1-docker-installation)  
> 2. [Docker image 설치 및 실행](#2-docker-image-실행)  
> 3. [Docker container 목록 확인](#3-docker-container-목록-확인)
> 4. [Docker pull](#4-docker-pull)    


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.


- 도커 설치 명령어, docker image를 가져와서 container로 실행하는 명령어에 대해 알아본다.

- 먼저 ec2 접속하기 위해 `ssh <root 계정 이름>@<IPv4>`를 입력하여 시작합니다.

<br>

---
 
# 1. Docker Installation

1. ec2에 로그인하기  

2. `sudo apt update`: sudo 명령어 사용하여 apt 업데이트하기 

3. docker 설치 확인 `docker`를 지금 단계에서 입력하면 `Command 'docker' not found` 를 확인할 수 있다. 

4. docker 설치하기: `sudo apt install docker.io`

5. docker 설치 확인: `docker`를 입력했을 때, 여러 옵션들이 뜨면 성공

6. docker version 확인: `docker --version`

7. docker 명령어를 유저 모드에서도 사용할 수 있게 변경 
- `sudo usermod -aG docker <사용자이름>`
- `exec $SHELL`
- 쉘 재가동

❗️ [root 계정이 아닌 사용자 추가하여 사용하기](https://losskatsu.github.io/os-kernel/aws-add-user/#aws-%EC%95%84%EB%A7%88%EC%A1%B4-%EB%A6%AC%EB%88%85%EC%8A%A4-%EC%9C%A0%EC%A0%80-%EC%B6%94%EA%B0%80%ED%95%98%EA%B3%A0-password-%EC%A0%91%EC%86%8D-%ED%97%88%EC%9A%A9)

<br>

---
# 2. Docker image 설치 및 실행

-  `docker run hello-world`를 실행하여 docker image를 확인해본다. 
    
    ```yml
    Unable to find image 'hello-world:latest' locally
    latest: Pulling from library/hello-world
    ...
    ```
    
    - 처음이라면 `unable to find image 'hello-world:latest' locally` 가 뜰 것이다. 이 의미는 로컬에 hello-world docker image가 없다는 걸 말한다. 

    - 그러면 자동적으로 pull을 하여 registry 에서 image를 가져온다.
        - 아래 registry에서 hello-world image를 확인할 수 있다.
        - https://hub.docker.com/_/hello-world 
    
    - 그래서 그 다음 줄에 로컬에 없으니 pull한다는 의미.

<br>

---

## docker image 목록 확인

- `docker image ls`

<br>

---
# 3. docker container 목록 확인

- 작동 중인 컨테이너 확인: `docker container ls` 

- 모든 컨테이너 확인: `docker container ls -a` 

- `status`에서 Extied는 종료되었다는 의미

- 직접 경로 이동하여 확인하기
    - 도커 실행 후, `cd /run/docker` 로 이동
    - `ls`를 입력하여 확인하면 실행 중인 container id 확인가능


🔆 참고: runtime-runc directory에 'moby'는 도커의 옛날 프로젝트 명이다.

<br>

---

## Containerd 확인

1. 관리자 계정으로 로그인: `sudo -i` . 

2. `cd /run/docker`

3. `ll` 을 사용하여 목록 확인
    - `ls -a` 와 동일한 기능  


<br>

---
# 4. Docker pull

### docker pull
- `docker pull <댕겨올 image>`
    - Ex) docker pull ubuntu

## pull된 docker image 저장 경로
- `cd /var/docker/image/overlay2/imagedb/content/sha256`


### pull 받은 image를 컨테이너로 실행

- 먼저 루트 계정으로 로그인: `sudo -i`
- `docker image ls` 로 해당 image id를 확인한다. 그 후, `docker run <image id>`를 입력하여 실행한다.

- 동일한 종류의 만들어놓은 image가 많을 경우 id를 사용하는 걸 권장

### 컨테이너 목록 확인
- 실행 확인: `docker container ls -a` 

<br>

---


# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)