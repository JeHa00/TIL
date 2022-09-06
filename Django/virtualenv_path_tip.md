# 0. Introduction 

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- django 프로젝트를 만들고, 가상환경을 사용할 때 directory 구조의 좋은 방식을 알게 되어 이를 정리해놓는다. 

- 또한, 실제 현업에서 사용하는 방식이라고 하니 알아두자.

<br>


---
# 기존에 사용한 방식



<br>

---

# 새롭게 배운 방식

1. `mkdir <project명>` 으로 원하는 경로에 직접 directory를 만든다.  
    - ex) `mkdir pjt1`

2. `cd <project명>` 으로 생성한 directory로 이동한다.

3. 그리고 나서 아래 명령어로 가상환경을 생성한다.
    - `virtualenv <가상환경 이름> --python=<python version 명>`  
        - ex) `virtualenv env-after --python=3.8.9`

4. 가상환경을 실행: `source ./<가상환경 이름>/bin/activate`
    - ex) `source ./env-after/bin/activate`

5. 가상환경 실행 후, django 설치: `pip install django==<version>`  
    - ex) `pip install django==3.2.13`

6. 프로젝트 생성: `django-admin startproject config .`
    - 현재 경로에 프로젝트명으로 config를 생성하여 그 안에 프로젝트 파일들을 담는다. 

이런 방식으로 만들면 위에 프로젝트 경로로 들어오면 바로 가상환경이 실행된다! 


<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)
