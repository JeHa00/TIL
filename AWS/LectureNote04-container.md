- 신뢰성과 가용성: 하나의 서버가 망가지면 대신할 서버를 얼마나 쉽게 사용할 수 있는지

- AWS Solution Architect는 AWS 자격증을 공부하면서 알 수 있다. 

- 도커를 많이 쓰는 무엇보다 큰 이유: 런타임과 라이브러리와 같은 디펜던시를 하나의 패키지로 묶을 수 있다. 
    - 하나의 Image로 묶으면(=환경까지 포함해서, 컨테이너까지 포함해서) 재현성이 상승! 
    - 이동성: 이것들을 말아서 도커 허브에 올려서 어느 누구든지 이동할 수 있다.
    - 이식성: 도커가 OS위에 각자 디펜던시를 가지고 있으니 어디든 이식되서 돌아갈 수 있다.
    - 어느 환경에서나 똑같이 동작하는 것이 목표다!!!! 그래서 도커가 많이 사용되는 것 
     
- VM도 GuestOS까지 합쳐서 하나의 패키지화를 할 수 있지만, 용량이 기가 단위로 커진다. 
- 자원 효율성

VM이라면 서버가 꺼져서 새로 커지면, OS, 장고도 다 새로 깔아야 하지만 도커를 사용하면 Image 파일로 감싸서 바로 실행할 수 있다. 

모든 앱, 컴포넌트는 죽을 수 있으나, 컨테이너는 어디서든 바로 동일한 실행 환경을 띄울 수 있다.  

snowflake server란 눈송이가 얼 때 눈꽃 모양이 다 다르듯이, 실제 프로덕션 환경이 다 다르기 때문에 시간이 지날수록 여러 서버 환경들이 점점 달라진다. 이러면 유지 보수가 매우 힘들어진다. 

하지만 불변 인프라의 장점은 이미지를 다시 말아서 띄우기 때문에, 문제가 생기면 삭제했다가 다시 올리기 떄문에 큰 문제가 없다. 

Rollback이란 만든 것을 되돌리기 위해서는 물리적 서버에 설치하는 것은 종속성이 관리가 안되는데, 컨테이너가 되면서 이미지만 잘 관리하면 불변 인프라로 관리하면서 롤백도 언제든 된다. 

!! 롤백되기 쉽다. 

프로세스를 보면 

# CI/CD -> 초반 강의에 보기

1.3.1을 배포했는데, 사용자 불편함이 있으면 이 불편한 버전만 수정해서 바로 배포할 수 있는게 ci/cd다. 
이게 비지니스 민첩성이다. 

# Container orchestrator

한계: 하나의 머신에서만 가능하다. 

컨테이너들을 한 번에 띄워서 LB를 추가한다. 

태스크 정의(작업 정의): 메모리를 얼마나 사용하고, 띄울지 결정하는 설계도 
- 쿠버네티스에서는 이를 Pod라 한다.

- 이 태스크들을 묶어주는 서비스
- 이 서비스를 묶어주는 클러스터
- 태스크가 제일 작은 단위


# Fargate
- 컨테이너가 여러 개를 띄우는데, 내가 A라는 컨테이너를 띄우기 위해서 전용 공간을 만들어서 띄우는 녀석
- 아파트보단 원룸, 고시원 같은 느낌!!

# 실습

- 서비스 설정에서 원하는 작업 갯수란 Task 수를 말한다.

- EC2와 Fargate의 차이: 어플리케이션을 지속적으로 써야하는 부분은 EC2를, Fargate는 배치성으로 사용한다.
    - 배치성으로 사용한다는 것은 껐다가 일정 이상 들어오면 작업을 하는 걸 말한다. 

- 클러스터 생성 후, 서비스 업데이트 클릭하여 3개로 늘리자.


- ALB가 판단하기 쉽다.

- 대상값은 얼마나 서버가 견디는지를 계산해서 넣어야 하는데, 우리는 모르니 1만한다. 

- 컨테이너 기반이기 떄문에 오토스케일링을 쉽게 할 수 있다. 


- 다섯 개로 늘어났다. 
- 20000번 다 요청한 후, 서버가 다 늘어나면 축소 비활성화가 체크되어 있지 않기 때문에, 종료되었다. 
- 이제 점차 1개로 축소될 것이다. 
15.164.230.119

curl -O https://bootstrap.pypa.io/get-pip.py 
python3 get-pip.py --user
source .profile
pip install awsebcli --upgrade --user 
sudo apt update
sudo apt install -y awscli

aws
eb --version

AKIA37K46L3I2ZDAYTOT

sudo apt install -y python3.10-venv
git clone https://github.com/sangyun-han/django-images && cd django-images
git remote rm origin
python3 -m venv venv && source venv/bin/activate
pip install -r requirements.txt
python manage.py migrate
python manage.py runserver 0:8000


# terminal command - beanstalk 환경 생성

여러 서버 복제본은 만든다. 예를 들어 dev, cbt, prod 로 단계적으로 배포한다. 

실제 프로젝트에 적용할 수 잇는 기술: beanstalk, 

<br>

---

# PDF 4

## task 

- ECS에서의 최소 단위로 app을 실행하기 위한 컨테이너 집합

- 태스크는 하나 이상의 컨테이널


# CloudFormation > 스택 > ecs-demogo

19 page

라우팅에 의해서 내부 서브넷끼리 통신이 가능하다. 

ECS 사용 시, EC2 를 띄운다. 

ALB란 인터넷을 통해 접속할 수 있는 것 


# 20 page

# 21 page

