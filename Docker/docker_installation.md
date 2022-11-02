# 0. Introduction

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 장철원 강사님의 docker 강의를 학습한 내용입니다.


- 도커 설치 명령어를 정리해본다.  

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
# 2. Docker image 실행

-  `docker run hello-world`를 실행하여 docker image를 확인해본다. 
    
    ```yml
    Unable to find image 'hello-world:latest' localy
    latest: Pulling from library/hello-world
    ...
    ```
    
    - 처음이라면 `unable to find image 'hello-world:latest' locally` 가 뜰 것이다. 이 의미는 로컬에 hello-world docker image가 없다는 걸 말한다.
    
    - 그래서 그 다음 줄에 로컬에 없으니 pull한다는 의미.

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)