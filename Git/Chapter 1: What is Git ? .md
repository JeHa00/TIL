# Chapter 1: What is Git?? 


# Intro
> 1. [What is Version Control System ???](https://github.com/JeHa00/TIL/edit/master/Git/Chapter%201:%20What%20is%20Git%3F%20.md#1-what-is-version-control-system-)
> 2. [Histroy summaries of Git](https://github.com/JeHa00/TIL/edit/master/Git/Chapter%201:%20What%20is%20Git%3F%20.md#2-histroy-summaries-of-git)
> 3. [Key point of Git : 데이터를 다루는 방식의 차이](https://github.com/JeHa00/TIL/edit/master/Git/Chapter%201:%20What%20is%20Git%3F%20.md#3-key-point-of-git--%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-%EB%8B%A4%EB%A3%A8%EB%8A%94-%EB%B0%A9%EC%8B%9D%EC%9D%98-%EC%B0%A8%EC%9D%B4)
> 4. [CLI](https://github.com/JeHa00/TIL/edit/master/Git/Chapter%201:%20What%20is%20Git%3F%20.md#4-cli)
> 5. [Git initail setup](https://github.com/JeHa00/TIL/edit/master/Git/Chapter%201:%20What%20is%20Git%3F%20.md#5-git-initail-setup)


<br>


- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291) 자체 스터디 내용  
- 이번 장은 Git을 처음 접하는 분들에게 필요한 내용이다.  
- 이 챕터를 통해서 Git 탄생 배경, 사용 이유를 알 수 있다.
- 지속적인 복습을 위해 만든다.


<br>


# 1. What is Version Control System ???

Version Control System이란 무엇이고, 왜 필요할까?? 
 
> **버전 관리 시스템(VCS) : 파일의 변화를 시간에 따라 기록했다가, 나중에 특정 시점의 버전을 다시 꺼내올 수 있는 시스템**  

VCS를 통해서 큰 노력 없이

1. 각 파일을 이전 상태로 되돌릴 수 있다.
2. 프로젝트를 통째로 이전 상태로 되돌릴 수 있다.
3. 시간에 따라 수정 내용을 비교할 수 있다. 
4. 누가 문제를 일으켰는지 추적할 수 있다. 
5. 누가 언제 만들어낸 이슈인지 알 수 있다. 
6. 파일을 잃어버리거나 잘못 고쳤을 때도 쉽게 복구할 수 있다. 


> **VSC의 종류에는 LVCS, CVCS, DVCS가 있다.** 

각 종류에 대해 알아보자.

<br>

## 1.1 Local Version Control System (LVCS, 로컬 버전 관리)

![image]()

[LVCS 탄생 계기]  
- 많은 사람은 버전을 관리하기 위해 directory로 파일을 복사하는 방법을 사용한다.
- 하지만 이러한 방식은 잘못되기 쉽기 때문에,  **Local Version Control System**을 만들었다.  
- 간단한 DB를 사용해서 파일의 변경 정보를 관리했다.   

<br>

[LVCS의 한 예: RCV]
- 많이 사용하는 VCS 중 RCS (Revision Control System)가 오늘날까지도 많은 회사에서 사용하고 있다.  
- RCS는 Mac OS에서도 개발 도구를 설치하면 함께 설치되며, 기본적으로 Patch Set(: 파일에서 변경되는 부분)을 관리한다.  
- Patch Set을 통해 모든 파일을 특정 시점으로 되돌릴 수 있다.



<br>


## 1.2 Central Version Control System (CVCS, 중앙 집중식 버전 관리)

![iamge]()

[CVCS 탄생 계기]
- 위의 LVCS의 장점에도 불구하고, LVCS는 다른 개발자와 협업 시에 사용할 때에는 적절한 tool이 아니었다. 
- 그래서 중앙 집중식 버전 관리 (CVCS, Central Version Control System) 를(을) 개발했다.

<br>

[CVCS 특징과 장점]
- CVCS는 LVCS에 비해 장점이 많다.
- 파일을 관리하는 서버가 별도로 있고, 클라이언트가 이 중앙 서버에 파일을 받아서 사용(checkout)하기 때문에,
  - 누가 무엇을 하고 있는지 알 수 있다.   
  - 관리자의 입장에서는 누가 무엇을 할지 꼼꼼히 관리할 수 있다. 
  - 모든 client의 local DB를 관리하는 것보다 VCS 하나를 관리하기가 훨씬 쉽다.

<br>

[CVCS 단점]
- 파일을 관리하는 중앙 서버가 별도로 있고, client가 checkout 방식으로 사용하고, 저장소를 전부 복제하는 게 아니기 떄문에,
  - 서버가 다운되는 동안, 다른 사람과 협업할 수 없고, 백업할 방법도 없다.
  - 중앙 DB가 있는 하드디스크에 문제가 생기면 project의 모든 history를 잃는다. (client가 가진 snapshot 제외)


> - **checkout: git checkout으로 사용되는 것처럼 사용할 branch를 지정하여 연결하는 걸 의미한다.**  
> - **snapshot: 변경된 파일 전체를 저장하는 것이 아닌 수정된 부분만을 저장하는 방식으로, 사진 찍는 것과 같아 snapshot이라 한다.**
 


<br>


## 1.3 Distributed Version Control System (DVCS, 분산 버전 관리 시스템)

![image]()

[DVCS 장점]
- 첫 번째, Git과 같은 **DVCS는 단순히 파일의 마지막 스냅샷을 checkout하지 않고, 저장소를 전부 복제한다.**    
  - 그래서 서버에 문제가 생기면 작업을 할 수 없었던 CVCS와는 달리, 이 복제물로 다시 작업을 시작할 수 있다. 
  - Client 중에서 아무거나 골라도 서버를 복원할 수 있다. 
  - 모든 checkout이 모든 데이터를 가진 백업이라는 의미다.
- 두 번째, 대부분의 DVCS 환경은 remote repository가 존재한다. 
  - 그래서, 사람들은 동시에 다양한 그룹과 방법으로 협업이 가능하다.
  - 계층 모델 같은 CVCS으로는 할 수 없는 workflow를 다양하게 사용할 수 있다.

<br>


# 2. Histroy summaries of Git

- 리눅스(Linux) kernel은 굉장히 규모가 큰 open source project였다.
- 1991~2002년 단순 압축 파일로만 관리하다가 2002년 BitKeeper라 불리는 DVCS를 사용하기 시작했다.
- 그러다 2005년에 BitKeepr가 유료화가 되었다. 
- Linux는 커뮤니티이고, BitKeeper는 이익을 추구하는 회사이기 때문에 이해관계가 달랐다.
- 이 계기로 Linux 개발 커뮤니티가 자체 도구를 만들었다. 그 도구가 Git이다.  
- Git은 아래와 같은 목표로 세워져서 지금도 유지하고 있다.
  - 빠른 속도
  - 단순한 구조
  - 비선형적인 개발 (동시다발적인 branch)
  - 완벽한 분산
  - 속도나 데이터 크기 면에서 대형 proejct에도 유용할 것


<br>


# 3. Key point of Git : 데이터를 다루는 방식의 차이


## 3.1 파일의 변화가 아니라 스냅샵의 스트림

VCS들과 Git의 가장 큰 차이점은 데이터를 다루는 방식에 있다.
  - VCS는 `각 파일의 변화`를 시간 순으로 관리하면서 파일들의 집합을 관리한다.
  - Git은 데이터를 `파일 시스템 스냅샷`으로 취급하여, `각 파일의 변화`가 아닌 `파일 전체를` 스냅샷으로 찍어낸다.
    - 그래서 Git은 데이터를 **snapshot stream**처럼 취급한다.
    - 그래서 Git은 프로젝트의 상태를 저장할 때마다, 파일이 존재하는 그 순간을 중요하게 여긴다. 
    - 그래서 Git은 파일이 달라지지 않으면 성능을 위해 새로 저장하지 않는다. 

<br>

## 3.2 거의 모든 명령을 로컬에서 실행

Git은 거의 모든 명령이 저장소 전체를 복사해서 `로컬 파일과 데이터만` 사용하는 방식이기 때문에,
  - 네트워크의 속도에 영향을 받는 다른 CVCS보다 매우 빠른 속도를 가진다. 
  - project의 history를 조회할 때, 서버 없이 local DB에서 조회하기 때문에 매우 빠르다. 
  - 어떤 파일의 현재 버전과 한 달 전의 상태를 비교하고 싶을 때도 이 두 상태를 로컬에서 찾는다.
  - 오프라인 상태거나 네트워크와 연결할 수 없어도 막힘 없이 일할 수 있다. 


<br>


## 3.3 Git의 무결성: checksum 방식


체크섬(checksum)이란
  - 40자 길이의 16진수 문자열인 SHA-1 해시를 사용하여 만든다.
  - Git에서 사용하는 가장 기본적인(atomic) 데이터 단위이다.
  - Git의 기본 철학이다.

> **SHA-1: 24b9da6552252987aa493b52f8696cd6d3b00373 같은 40자 길이의 16진수 문자열이다.


그래서 Git은 checksum을 사용하여 (중심으로)
  - 데이터를 저장하고 관리한다.
  - 그래서 체크섬 없이는 어떠한 파일이나 디렉터리도 변경 할 수 없다.
  - 모든 것을 식별한다. 
  - 파일을 저장하고, 파일 이름으로 저장하지 않는다.

<br>


## 3.4 Git은 데이터를 추가할 뿐


Git은 무엇을 하든 Git database에 데이터를 추가한다.   
    - 그래서 데이터를 되돌리거나 삭제할 방법이 없다.   
    - 또한, 다른 VCS처럼 commit하지 않으면 변경사항을 잃어버릴 수 있다.  
    - 하지만, commit 하면 데이터를 잃기 어렵다.  


<br>


## 3.5 Git의 3가지 상태(: Commited, Modified, Staged) 와 3가지 단계(: Working directory, Staging Area, .git Directory(Repository)

> 이 부분에 대한 자세한 내용은 Chapter 2를 참고한다.

- Git 파일은 **세 가지 상태(: Commited, Modified, Staged)** 로 관리한다.  
    - Commited: 데이터가 local database에 안전하게 저장된 상태  
    - Modified: 수정한 파일을 아직 local database에 커밋하지 않은 상태  
    - Staged: 현재 수정한 파일을 곧 커밋할 것이라고 표시한 상태   
    
![image]()
    
    
- 이 3가지 상태는 **Git project의 3가지 단계(: Working directory, Staging Area, .git Directory(Repository)**와 연결된다.    

- Git directory는
    - Git의 핵심이다.
    - Git이 project의 meta data와 객체 datebase를 저장하는 곳이다.
    - 다른 컴퓨터에 있는 저장소를 clone할 때 만들어지는 곳이다.
    - 파일들이 commited된 상태다.

- Working directory는 
    - project의 특정 버전을 checkout한 것이다.
    - Git directory 안에 압축된 database에서 파일을 가져와서 만든 것이다.
    - 파일을 수정하는 공간이므로, checkout하고 나서 수정했지만, 아직 staging area에 추가하지 않은 상태다.
 
- Staging Area는
    - Git directroy에 있다.
    - 단순한 파일이고 곧 커밋할 파일에 대한 정보를 저장한다.
    - 파일들이 Staged 상태다.


- 위 개념들을 토대로 Git으로 하는 기본적인 일은 아래와 같다.
    - working directory에서 파일을 수정한다.
    - Staging area에 파일을 stag해서 커밋할 snapshot을 만든다.
    - Staging Area에 있는 파일들을 커밋해서 Git directroy에 영구적인 snapshot으로 저장한다. 

<br>


# 4. CLI
- Git은 CLI (Command Line Interface)와 GUI (Grapic User Interface)로 사용할 수 있다.  
- 하지만, **Git의 모든 기능을 지원하는 것은 CLI 뿐이다.**
- CLI를 사용할 줄 알면 GUI를 사용할 수 있지만, 반대는 성립되지 않는다.  



<br>



# 5. Git initail setup

- `사용자 정보 등록`은 다음 명령어로 한다.  
     - `--global` 은 `전역 설정`하는 명령어로, 딱 한 번만 하면 된다.
       `git config --global user.name "user-name"`
       `git config --global user.email <user-email>` 
    - 만약, 프로젝트마다 다른 이름과 이메일 주소를 사용하고 싶으면 `--global`전역 명령어를 빼고 입력한다.

- `설정 확인` 방법은 `git config --list` 명령을 실행한다. 
    - Git은 같은 키를 여러 파일에서 읽기 때문에 같은 키가 여러 개 있을 수 있다. 
    - 그러면 Git은 나중값을 사용한다. 
    - `git config <key>` 명령으로 특정 Key에 대해 어떤 값을 사용하는지 확인할 수 있다.
    - `git config user.name`

- `명령어 도움말` 이 필요할 때는 방법은 3가지다.
    - `git help <verb>`
    - `git <verb> --help`
    - `main git-<verb>`
    - 예를 들어 `git help config`를 실행하면 `config` 명령에 대한 도움말만 볼 수 있다.

---


<br>


# Reference 

- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291) 
- [더북(TheBook:Git 교과서)](https://thebook.io/080212/ch04/04/02/)
