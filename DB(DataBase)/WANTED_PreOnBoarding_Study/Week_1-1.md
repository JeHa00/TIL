# 0. Introduction

> 1. [데이터베이스의 원칙](#1-데이터베이스의-원칙)    
> 2. [다양한 데이터베이스 종류와 CAP Theorem](#2-다양한-데이터베이스-종류와-cap-theorem)    
> 3. [예제로 보는 서비스에 적합한 데이터베이스 선택법](#3-예제로-보는-서비스에-적합한-데이터베이스-선택법)    
> 4. [RDBMS와 NoSQL 비교](#4-rdbms와-nosql-비교)    

- [원티드 백엔드 챌린지 2월: MySQL '잘' 사용하기](https://www.wanted.co.kr/events/pre_challenge_be_4) 를 듣고 제 언어로 정리한 포스팅입니다.  

- 해당 챌린지의 목표는 MySQL의 특징을 이해하여 실무에 적용하고, MySQL 기본 개념들을 학습하여 기술 면접에 대비하는 것입니다.

### 해당 포스팅의 주제와 키워드

- 이번 포스팅의 주제는 **[다양한 데이터베이스의 특징과 장단점]** 입니다.

- Keyword: 데이터 베이스의 원칙, CAP Theorem, RDBMS, NoSQL

<br>

---

# 1. 데이터베이스의 원칙

> **_무결성, 안전성, 확장성_**

아래 3가지를 고려하여 DB를 정해야 한다.  

## 무결성(data integrity)

데이터가 전송, 저장, 처리되는 과정에서 변경되거나 손상되지 않는 원칙을 말한다.

이 특징으로 인해서 데이터의 **완전성, 정확성, 일관성** 을 유지한다.  

## 안전성(data reliability)

- 데이터를 보호할 수 있는 방법
- 인증/인가되지 않은 사용자로부터 데이터를 보호함
- 고장이 안나야됨

## 확장성(scalability)

데이터 양이나 사용자가 늘어날 때 확장하는 원칙을 말한다.  


<br>

---

# 2. 다양한 데이터베이스 종류

> **- _DB의 종류: RDBMS(MySQL, PostgreSQL..), NoSQL(redis, MongoDB)_**  
> **- _서비스에 적합한 DB 선택법: CAP Theorem_**    

## 2.1 RDBMS 

> **_Ex: MySQL, Oracle, PostgreSQL..._**

- 데이터를 row와 column으로 이뤄진 **table의 형태** 로 저장한다.  

- 그리고, 이 저장된 table로부터 **SQL(Structured Query Language)** 를 사용해서 데이터를 읽어오거나 조작한다.  

<br>


## 2.2 NoSQL

> **_Ex: key-value, graph, document_**

데이터가 저장되는 형태인 key-value, graph, document 에 따라 NoSQL 여러 종류로 나눠진다.

### 2.2.1 key-value

- key-value 쌍으로 데이터가 저장된다.  
- 여기서 Key는 unique identifier로도 사용된다. (식별자)
- redis 등이 여기에 해당된다.  
    - cache
    - message broker → pub/sub
    - not that scalable → limitation on the size of RAM
    - Dynamo DB
        - partiton key - primary.
        - sort key - secondary, optional
        - advantage
            - scalable →AWS offers, HA, serverless


### 2.2.2 graph

- 데이터를 graph 형태로 저장한다.  
- 각 항목이 node로 이뤄져있고, node 간의 관계는 edge를 사용해서 나타낸다. 
- SNS 등 서로 관계가 복잡한 상황에서 자주 사용된다.
    - SNS, linkedin
- 종류: Neo4j, OrientDB 등

### 2.2.3 document  

- 데이터가 document 형태로 저장되서 구조가 자유롭다.  
    - JSON 또는 XML 형태
- DB 별로 데이터를 조작할 수 있는 언어가 따로 있다.  
- 종류: MongoDB, CassandraDB, Couchbase 등이 해당  

<br>

## 2.2 row-oriented vs column-oriented  

- row - oriented: MySQL, PostgreSQL, hbase
- column - oriented: CassandraDB, hbase, Bigquery
- example: Netflix


CAP Theorem 에 따라서 DB를 선택한다.
- Consistency는 일관성 (금융 데이터에 중요)
- Avail ~ 가용성 (이커머스 분야에서 중요)
- Partition-Tolerance: 분산저장이 가능 (데이터량이 매우 많을 때)

위 3가지는 다 가질 수 없다. 

서비스에 적합한 데이터베이스 선택
- 넷플릭스: MySQL 
- 인스타그램: avail ~ 를 중요하게 생각한다. 즉, write가 중요하다. 

<br>

---

# 3. 서비스에 적합한 데이터베이스 선택법

> **_CAP Theorem_**

## 3.1 CAP Theorem

![image](https://user-images.githubusercontent.com/78094972/217833930-899a11e5-dcf0-463a-aeaf-8fc12f6fecb6.png)

### consistency(일관성)
- 데이터베이스 안의 모든 node들이 같은 값을 가지고 있음
- request를 보내면 해당 request가 delay 될 수 있음
- 금융 쪽에서 중요하게 생각함
- 송금 데이터가 데이터베이스 노드당 align되지 않으면? 송금 안된줄 알고 또보내고 또보내고….


### availability(가용성)
- 데이터베이스에 request를 보내면 항상 response를 받음
    - consistencty는 response바로 안옴
- 하지만 해당 response가 가장 최근 데이터라는 것을 보장받을 수 없음
- 접근하는 node에 따라 값이 다르다


### partition-tolerance(분산처리)
- node간 소통이 불가능 하더라도 정상적으로 작동함

<br>

## 3.2 위 3가지를 다 가질 수 없는 이유  

<Br>

## 3.3 예시

- RDBMS - Netflix
    - 영상에 대한 정보를 저장하는 방식
    - 평점 데이터를 가져와서 인공지능과 연결 아마도??  → 사용자가 좋아할 것 같은 영상을 추천한다
- NoSQL - 인스타그램
    - [Cassandra DB 블로그](https://jasonkang14.github.io/database/cassandra-db)↗️
    - 종종 데이터 날아가는데 몇시간 뒤에 복구함 어떻게 이게 가능한지?
    - 데이터를 저장하는 방식 덕분에/때문에 가능하다


<br>

---

# 4. RDBMS와 NoSQL 비교

| |RDBMS | NoSQL|
| ---- | ---- | ---- |
|Data modeling | | |
| Scalability|Scale up | Scale out |
| Query Language | SQL(Structured Query Language) | 문법이 조금 다름 |
| Consistency | Strong |  |
| flexibility | Not really | very flexible |

🔆 Scale up VS. Scale out

![image](https://user-images.githubusercontent.com/78094972/217833521-8484166b-990a-4e8b-b3f9-7db94d40baec.png)

DB는

RDB는 스키마가 확실한 부분에서 좋다. 사용자 정보는 변하지 않기 떄문에 RDB에
블로그 글의 경우에서는 NoSQL에 저장한다.   

https://www.mysqltutorial.org/mysql-sample-database.aspx 

스키마가 정해져있는데, 데이터가 중간에 넣어야 하기 떄문에 insert는 느리다. 

read를 빨리하기 위해서 Column Oriented Database를 사용

시간복잡도는 알고리즘에서만 도입.

redis는 환자가 접속하고 있는지를 볼 때 사용했다. 이는 disk에 저장할 필요가 없기 때문이다.  
Mongo DB는 log 남길 때 사용했다.  


<br>

---

# Tip. 회사를 선택하는 나만의 기준

<br>

---

# Reference
- [원티드 백엔드 챌린지 2월: MySQL '잘' 사용하기](https://www.wanted.co.kr/events/pre_challenge_be_4)
- [Understanding NoSQL Databases by the CAP Theorem](https://data-science-blog.com/blog/2021/10/14/cap-theorem/)