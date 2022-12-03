# 0. Introduction  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.

- 이번 챕터에서는 docker 기본 image를 바탕으로 자신만의 image를 만드는 과정을 학습해보겠습니다.

<br>

---


# Docker image 변경 후 저장

> **_기본 Layer를 기반으로 여러 layer를 쌓아나가는 것_**

예를 들어서 파이썬이란 image는 우분투라는 image를 받아서 그 위에 파이썬을 깔아서 만든 image다. 

이처럼 여러 layer를 깔아서 만든 게 image다. 

자신만의 docker image를 만들어보자.  

### 현재 상황

- terminal 2개 A, B를 실해애 중인 상황

- terminal B에만 container 실행 중

### terminal A
- 기존 ubuntu image 실행하기
    - `docker run -it ubuntu bash`
    
- 그러면 원하는 기능이 ubuntu에 있는지 명령어를 입력하여 확인해보자.
    - 예를 들어서 `ifconfig`를 실행해본다.
        - `ifconfig`: 네트워크 IP 정보 보는 명령어
    -  그 결과: `bash: ifconfig: command not found` 

- 기본 ubuntu에는 이게 설치되어 있지 않기 때문이다. 
- 그래서 별도의 layer를 쌓아야 한다. 

- 아래 명령어를 통해서 설치해보자. 
    - `apt-get update && apt-get install net-tools`

- `ifconfig`를 입력하면 `inet <ip 주소>`를 확인할 수 있다. 

<br>

### terminal B

- `docker container ls`
    - terminal A에 올린 container를 확인할 수 있다.

- `docker commit <container id> <만들려는 image의 이름: 임의 지정>:<tag 번호: 임의 지정>`
    - ex) `docker commit <container id> my-ubuntu:0.1`

    - 0.1은 태그 번호라서 자신의 마음대로 입력하면 된다. 

    - 기본 우분투 위에 지금 `net-tools` 라는 걸 설치하여 한 layer를 쌓았다. 이를 저장하기 위해서는 위 명령어를 사용한다. 

<br>


### 만든 image 실행

일반 image 실행과 동일하다.

- `docker run -it <image name:image tag>`



🔆 `docker build`는 내 local 상에 있는 걸 저장할 때 사용하고, 위 명령어는 이미 떠 있는 컨테이너를 이미지로 뜨는 것이다. 


<br>

---


# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)