# 0. Introduction

> 5. [서로 다른 터미널에 접속 및 종료 시키기](#5-서로-다른-터미널에-접속-및-종료-시키기)     
> 6. [컨테이너와 docker host 간 파일 전송](#6-컨테이너와-docker-host-간-파일-전송)     


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.


- 도커 명령어를 정리해봅니다.

- 먼저 ec2 접속하기 위해 `ssh <root 계정 이름>@<IPv4>`를 입력하여 시작합니다.

<br>

---


# 5. 서로 다른 터미널에 접속 및 종료 시키기

그렇다면 docker host 안에 container가 있는 그림을 생각하자.

## 5.1 (터미널 1)container 내부에서 bash shell 실행  

docker host에서 만든 container 내부에서 bash shell을 실행한다는 의미다. container는 하나의 서버이기 때문이다. 

- 아래 명령어 입력 시, 바로 bash shell에 접속한다.
- `docker run -it <image name>`
    - i: interactive 
    - t: 터미널을 통해 대화형 조작이 가능하게 한다. 
    

### 5.2 (터미널 2)새로운 터미널을 열고 컨테이너 리스트 확인 

터미널 2는 docker host로 접속된 상태

컨테이너의 리스트를 확인하기
- `docker container ps`

### 5.3 서로 다른 터미널에 접속 및 종료 시키기

- 현재 터미널 상황: Docker host가 동작 중인 터미널 1, 2에서 터미널 1만 container 실행 중

- 터미널 1은 `docker run -it <image name>`으로 컨테이너 실행 중인 상황  

- 터미널 2에서 터미널 1에 실행 중인 컨테이너에 접속하기: `docker exec -it <container id> bash`
    - 여기서 `container id`란 터미널 1에서 실행 중인 컨테이너다.
    - 터미널 2에서 `docker container ls` 명령어로 실행 중인 컨테이너 id를 확인 후, 이를 입력하여 접속한다.  
    - 두 터미널을 비교해보면 동일한 container id를 나타내는 걸 알 수 있다.

- 터미널 2에서 터미널 1에 실행 중인 컨테이너 접속 후, 나갈 때: `exit`

- 터미널 2에서 터미널 1에 실행 중인 컨테이너 종료 시키기  
    - `docker stop <container id>` 


<br>

---

# 6. 컨테이너와 docker host 간 파일 전송

2개의 터미널 A, B를 띄어서 실행한다.

터미널 A에 임의의 경로 C에 파일 하나를 생성한다. 

생성한 파일을 D라고 하자.  

<br>

## 6.1 Docker host -> container로 파일 전송

### terminal A에서 terminal B의 container로 파일 전송

1. 터미널 A: `cd <경로 C>` 로 이동하여 `ls` 로 D가 있는지 확인하자.

2. 터미널 B: `docker run -it ubuntu bash` 

3. 터미널 A: `docker container ls` 로 터미널 B에서 실행시킨 컨테이너 ID를 확인하여 실제로 실행되었는지 확인

4. 터미널 A: `docker cp <파일 D의 경로> <B에서 실행한 컨테이너 ID>:<복사할 경로>` 
    - ex)
        - 파일 D의 경로: ./while_loop.py
        - 컨테이너 ID: 26fcb671e9ef
        - 복사할 경로: /home 

5. 터미널 B: 위에 복사할 경로에 입력한 경로로 이동해서 `ls`로 확인

그러면 위 터미널 B에서 복사한 파일을 확인할 수 있다.

<br>

## 6.2 container -> Docker host로 파일 전송

1. 터미널 A: `docker cp <container id>:<파일 경로> <복사한 파일이 위치할 경로>`
    - ex) docker cp 26fcb671e9ef:/home/while_loop.py ./

<br>

---


# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)