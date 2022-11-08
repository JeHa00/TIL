# 0. Introduction

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 lukas 강사님의 AWS 강의를 정리한 내용입니다.


<br>

---
# 1. EC2 & Load Balancer 실습

# 1.6 EC2와 LoadBalancer 실습

## 이론 
### 전통적인 아키텍처
얼마나 빠르게 대체 투입 되는가?
hot은 둘 다 떠 있으나 하나만 트래픽을 받는 측면
warm은 
cold
cold/warm/hot standby : 가용성 측면, fault tolerance 관점
- standby server 차이가 있다.
- 분산 처리 관점은 아니다.
- 장애가 발생했을 때, standby를 작동시키는 방식
    - warm: 
    - hot: standby

후자
- 클라이언트와 서버 중간에 Load Balancer를 준다.
- 그래서 트래픽이 동시에 많이 들어오면 여러 서버에 분산시킨다. 
- 이론상 최대 3배의 트래픽을 감당할 수 있다. 
    - RoundRobin 방식 
- 가용성(HA: High Avality) 측면에서도 좋고, 
- server 한 대가 고장나도 클라이언트는 모른다. 왜냐하면 나머지 2대가 이를 감당할 수 있기 때문이다. 
- scale-out 관점에서도 미리 서버를 증설하여 load balancer를 붙이면 평소보다 이론상 3배를 감당할 수 있다. 

### LB 이론

- L4 LB
    - PORT 번호로 부하 분산

- L7 LB (Application LB)
    - application 계층으로 부하 분산  


<br>

---
# 2. Serverless 서비스, Lambda 실습


<br>

---
# Reference 

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)