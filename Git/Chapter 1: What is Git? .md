# Chapter 1: What is Git?? 

> [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291) 자체 스터디 내용  
> 이번 장은 Git을 처음 접하는 분들에게 필요한 내용이다.  
> 이 챕터를 통해서 Git 탄생 배경, 사용 이유를 알 수 있다.

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

그리고,  

> **VSC의 종류에는 LVCS, CVCS, DVCS가 있다.** 

각 종류에 대해 알아보자.

 
## 1.1 Local Version Control System (LVCS, 로컬 버전 관리)

![image]()

[LVCS 탄생 계기]  
- 많은 사람은 버전을 관리하기 위해 directory로 파일을 복사하는 방법을 사용한다.
- 하지만 이러한 방식은 잘못되기 쉽기 때문에,  **Local Version Control System**을 만들었다.  
- 간단한 DB를 사용해서 파일의 변경 정보를 관리했다.   

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

[CVCS 특징과 장점]
- CVCS는 LVCS에 비해 장점이 많다.
- 파일을 관리하는 서버가 별도로 있고, 클라이언트가 이 중앙 서버에 파일을 받아서 사용(checkout)하기 때문에,
  - 누가 무엇을 하고 있는지 알 수 있다.   
  - 관리자의 입장에서는 누가 무엇을 할지 꼼꼼히 관리할 수 있다. 
  - 모든 client의 local DB를 관리하는 것보다 VCS 하나를 관리하기가 훨씬 쉽다.

[CVCS 단점]
- 파일을 관리하는 중앙 서버가 별도로 있고, client가 checkout 방식으로 사용하고, 저장소를 전부 족제하는 게 아니기 떄문에,
  - 서버가 다운되는 동안, 다른 사람과 협업할 수 없고, 백업할 방법도 없다.
  - 중앙 DB가 있는 하드디스크에 문제가 생기면 project의 모든 history를 잃는다 (client가 가진 snapshot 제외)


> - **checkout: git checkout으로 사용되는 것처럼 사용할 branch를 지정하여 연결하는 걸 의미한다.**  
> - **snapshot: 변경된 파일 전체를 저장하는 것이 아닌 수정된 부분만을 저장하는 방식으로, 사진 찍는 것과 같아 snapshot이라 한다.**
 


<br>


## 1.3 Distributed Version Control System (DVCS, 분산 버전 관리 시스템)

![image]()

[CVCS와의 차이점]
- 첫 번째, **DVCS는 단순히 파일의 마지막 스냅샷을 checkout하지 않고, 저장소를 전부 복제한다.**    
  - 그래서 서버에 문제가 생기면 작업을 할 수 없었던 CVCS와는 달리, 이 복제물로 다시 작업을 시작할 수 있다. 
  - Client 중에서 아무거나 골라도 서버를 복원할 수 있다. 
  - 모든 checkout이 모든 데이터를 가진 백업이라는 의미다.

<br>


# 2. Short histroy of Git

<br>


# 3. Git baisc

<br>




# 4. CLI

<br>



# 5. Git initail setup



---


<br>


# Reference 

- [Pro git : Second editions](https://book.naver.com/bookdb/book_detail.nhn?bid=7187291) 
- [더북(TheBook:Git 교과서)](https://thebook.io/080212/ch04/04/02/)
