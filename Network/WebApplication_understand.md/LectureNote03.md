# 0. Introduction: 웹 애플리케이션 기초

> 3. [네트워크 기초](#3-네트워크-기초)
> 4. [네트워크 실습](#4-네트워크-실습)

<br>

### 학습 목적

> **_기반 지식을 다지기_**

사용자가 눈에 보이는 부분을 개발하는 부분을 **프론트엔드** ,

사용자 눈에 보이지 않는 부분을 개발하는 부분을 **백엔드** 라 한다.

프론트엔드는 코드 실행 결과를 눈으로 직접 바로바로 확인이 가능하다.  

하지만, **백엔드는 코드의 결과를 눈으로 확인하기 어렵다. 그래서 오류의 원인을 찾는 디버깅이 어렵다**. 

**이 디버깅을 하기 위해서는 기반지식이 필요하다.**  

입문자가 생각하는 개발은 파이썬 코드라고 생각하지만, **실제 개발에 필요한 내용은 기반지식이다.**   

그래서 이번에 기반 지식 아래 내용을 학습해본다.

- 클라이언트 - 서버 구조
- 웹 서버 구조 설계
- 네트워크


<br>

---
# 3. 네트워크 기초

- 여러 개의 계층을 통해 데이터를 송수신한다.  

### OSI 7 layer

- OSI 7 layer의 구성은 다음과 같다.
    | OSI 7 layer |
    | ----| 
    |응용 계층|
    |표현 계층|
    |세션 계층|
    |전송 계층|
    |네트워크 계층|
    |데이터링크 계층|
    |물리계층|


- 그래서 송신할 때는 위에 응용계층부터 물리계층방향으로 진행되어, 수신할 때는 물리계층부터 응용 계층 방향으로 진행되어 데이터를 받는다.  

- 이 **OSI 7 layer** 를 4개의 계층으로 표현한 것을 **TCP/IP 4 layer** 라 한다. 설명은 OSI 7 layer를  기준으로 한다.

    | OSI 7 layer | TCP/IP 4 layer |
    | ---- | ---- |
    |응용 계층| 4. 응용 계층|
    |표현 계층| 4. 응용 계층|
    |세션 계층| 4. 응용 계층|
    |전송 계층| 3. 전송 계층| 
    |네트워크 계층| 2. 인터넷 계층 |
    |데이터링크 계층| 1. 네트워크 접속 계층 | 
    |물리계층| 1. 네트워크 접속 계층 |

- 우리가 우편을 보낼 때, 보낼 물건을 바로 보내는 게 아니라 우편 봉투에 넣고, 주소 태그를 붙이는 것처럼 데이터가 각 계층을 통해 보내질 때, 계층을 한 번 이동할 때마다 계층에 맞는 header(헤더)가 붙여진다.  

- 그리고 데이터 수신 시, 각 계층을 지나갈 때마다 그 계층에 맞는 헤더를 제거해서 응용계층에서 원하는 데이터를 본다.  

![image](http://wiki.hash.kr/images/7/71/OSI_7_%EA%B3%84%EC%B8%B5.jpg)


## 3.4 4계층: 전송 계층

> **_네트워크 계층(3계층)이 데이터를 전달할 때, 전송 계층(4 계층)은 데이터가 제대로 전달되었는지를 확인하며, 데이터가 최종적으로 도착할 애플리케이션이 무엇인지 식별하는 기능_**


### 통신방법: 연결형 통신과 비연결형 통신

- **연결형 통신: TCP (Transmission Control Protocol)**
    - 데이터를 신뢰성 있고 정확하게 전달
    - 이를 위해 상대방과 계속 연락하면서 통신
    - Ex) 등기 우편

- **비연결형 통신: UDP(User Datagram Protocol)**
    - 데이터를 효율적으로 전달
    - 상대방 반응을 확인하지 않고 일방적으로 전달
    - Ex) 일반 우편

### TCP header

> **_segment = TCP header + data_**

- 특성
    - TCP header의 URG, ACK, PSH, RST, SYN, FIN이 중요하다.

| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 0 | 0 | 0 | 0 | 0 |

<br>

### TCP header의 특성 1: 신뢰성

- **3 way hand shake: 3 단계를 걸쳐서 연결 유무를 확인하는 걸 의미한다.**  


연결 요청 응답 관련 흐름이 다음과 같다.

1. 연결 요청 (클라이언트 -> 서버)


| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 0 | 0 | 0 | 1 | 0 |

2. 연결 응답 + 연결 요청 (서버 -> 클라이언트)


| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 1 | 0 | 0 | 1 | 0 |


3. 연결 응답 (클라이언트 -> 서버)


| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 1 | 0 | 0 | 0 | 0 |


연결 종료 응답 관련 흐름도 이와 비슷하다.

1. 연결 종료 요청

| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 0 | 0 | 0 | 0 | 1 |


2. 연결 종료 응답

| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 1 | 0 | 0 | 0 | 0 |


3. 연결 종료 요청

| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 0 | 0 | 0 | 0 | 1 |

4. 연결 종료 응답

| URG | ACK | PSH | RST | SYN | FIN |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 0 | 1 | 0 | 0 | 0 | 0 |

<br>

### TCP header의 특성: 식별성

- 각 애플리케이션에 해당되는 포트를 사용한다.  

| 애플리케이션 | 포트 | 
| SSH | 22 |
| SMTP | 25 |
| DNS | 53 |
| HTTP | 80 |
| POP3 | 110|
| HTTPS | 443 |

<br>

### UDP header

> UDP datagram = UDP header + data

- UDP Header는 TCP와 달리 매우 단순하여, 가볍다. 

- 그래서 TCP는 확인해야할 것들이 많아서 느리지만, UDP는 빠르다.  

<br>

---

## 3.5 5,6,7 계층: 응용계층

> **_응용계층의 역할: 클라이언트의 요청을 전달하기 위해 서버가 이해할 수 있는 메시지(데이터)로 변환하고 전송 계층으로 전달하는 역할을 한다. _**

🔆 클라이언트와 서버가 무엇을 하려고 하는지에 따라서 프로토콜의 종류가 달라지고, 클라이언트와 서버의 프로토콜이 일치해야 한다. 

| 사용 목적 | 응용 계층 프로토콜 |
| ---- | ---- |
| 웹 사이트 열람 | HTTP | 
| 파일 전송 | FTP |
| 메일 송신 | SMTP |
| 메일 수신 | POP 3|
| 이름 해석 | DNS | 

<br>

## Summary 

![image](https://user-images.githubusercontent.com/78094972/187599188-35a39439-9e90-4636-a090-7e2750f89e3d.png)



<br>

---

# 4. 네트워크 실습

## ifconfig

> **_IP 주소를 확인하는 명령어_**

- terminal에 `ifconfig`를 입력해보고, 뜨는 것을 분석해보자. 
- 이것이 네트워크의 전부다.  

- `lo0`: loop back을 의미하는데, 자기 자신에게 보내는 데이터를 처리하기 위한 가상 인터페이스 장치 이름
    - `inet 127.0.0.1`: localhost로 자신의 컴퓨터를 의미한다.
    - `netmask 0xff000000`: 255.0.0.0 /8
        - 11111111 00000000 000000000 00000000

- `en0`: ethernet을 의미한다
    - `inet6 fe80::1872:9ed:4de7:e8c`: IPv6 주소
    - `inet 172.30.1.1`: IPv4 주소로 내부 IP
    - `broadcast 172.30.1.255`: broadcast address
    - `netmask 0xffffff00`: 255.255.255.192 /26
        - 11111111 11111111 1111111 11000000

<br>

## DNS server

> **_DNS(Domain Name System)이란? 웹 사이트의 IP 주소와 도메인 주소를 이어주는 시스템_**

- 터미널을 열어서 `nslookup www.naver.com`을 입력해보자.  
    - `Non-authoritative answer:` 아래 항목들 중에서 `Address`를 보면 `www.naver.com`의 IP 주소를 확인할 수 있다.
    - `Address: 223.130.200.104` 를 입력하면 네이버로 이동할 것이다.

- url을 입력하면 DNS server에서 이 url에 해당되는 IP 주소를 보내주고, 이 IP 주소로 입력되어 해당 서버에 요청하여 화면에 보여지는 것

### 맥에서 DNS 확인

> `cat /etc/hosts`을 입력

- 위 명령어를 입력하면 아래창을 확인할 수 있다.  

```yml
127.0.0.1	localhost
255.255.255.255	broadcasthost
::1             localhost
```

- `127.0.0.1`은 localhost다.  
- `broadcasthost` 는 `255.255.255.255` 다. 
- DNS에다가 추가하고 싶은 IP 주소를 추가할 수 있다는 것


<br>

## ping

> **_Ping(Packet INternet Groper)의 약자로, 네트워크 상태를 점검 및 진단하는 명령어_**

- 나중에 서버를 구출할 때, 그 서버에 ping을 날려보면 데이터를 보내고 받을 수 있는지 확인할 수 있다.

- 터미널 실행 후,  `ping google.co.kr`를 입력해보자.  

```yml
> ping google.co.kr
PING google.co.kr (172.217.26.227): 56 data bytes
64 bytes from 172.217.26.227: icmp_seq=0 ttl=114 time=45.149 ms
64 bytes from 172.217.26.227: icmp_seq=1 ttl=114 time=36.573 ms
64 bytes from 172.217.26.227: icmp_seq=2 ttl=114 time=44.751 ms
64 bytes from 172.217.26.227: icmp_seq=3 ttl=114 time=44.796 ms
64 bytes from 172.217.26.227: icmp_seq=4 ttl=114 time=40.381 ms
64 bytes from 172.217.26.227: icmp_seq=5 ttl=114 time=45.104 ms
64 bytes from 172.217.26.227: icmp_seq=6 ttl=114 time=37.562 ms
64 bytes from 172.217.26.227: icmp_seq=7 ttl=114 time=36.680 ms
^C
--- google.co.kr ping statistics ---
8 packets transmitted, 8 packets received, 0.0% packet loss
round-trip min/avg/max/stddev = 36.573/41.375/45.149/3.740 ms
```

- 총 8개의 packet을 보내고, 받았다는 걸 알 수 있다.  
- 이렇게 Ping을 보내어 해당 서버와 통신 유무를 확인할 수 있다.  

<br>

---

# Reference

- [나노디그리 러닝스푼즈: Python & Django backend course](https://learningspoons.com/course/detail/django-backend/)
- [OSI 7 layer](http://wiki.hash.kr/index.php?title=OSI_7_%EA%B3%84%EC%B8%B5&mobileaction=toggle_view_desktop)