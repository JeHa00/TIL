# HTTP Introduction 1: Internet Network

<br>

# 0.Introduction

> 1. [IP에 대해서](#1-ip에-대해서)
> 2. [IP 문제점의 해결책: TCP, TCP 문제점의 해결책: UDP](#2-ip-문제점의-해결책-tcp-tcp-문제점의-해결책-udp)
> 3. [Port와 DNS란 무엇인가??](#3-port와-dns란-무엇인가)

<br>

HTTP에 관한 학습내용의 기본 출처는 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC) 이다. 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.

<br>

이 강의는 HTTP에 대한 웹 기본지식을 설명하는 강의이므로, 내용이 간략할 수 있다.  
프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고 시작하고 싶어 HTTP 공부를 시작한다.  
이 강의에 대해 공부 후, 네트워크 전반에 대해 공부한다.

---

---

title: "[TIL] Network HTTP intro. 1: Internet network"
date: 2022-03-07T21:16:44+09:00
draft: false
summary: HTTP를 학습하기 위해 사전지식으로 IP,TCP/UDP, PORT, DNS를 알아본다.
tags: ["TIL", "Network", "IP", "TCP", "PORT", "DNS"]
categories: ["개발-dev Netwok"]

---

HTTP에 관한 학습내용의 기본 출처는 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC) 이다. 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.

<br>

이 강의는 HTTP에 대한 웹 기본지식으르 설명하는 강의이므로, 내용이 간략할 수 있다.  
프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고 시작하고 싶어 HTTP 공부를 시작한다. 이 강의에 대해 공부 후, 네트워크 전반에 대해 학습한다.

---

<br>

# 1. IP에 대해서

컴퓨터는 랜선 또는 인터넷 망을 통해서 통신한다. 그리고 인터넷 망은 수 많은 서버들로 구성되어 있으며, 이 서버들을 `node(노드)`라 한다.

그러면 수 많은 node들을 거쳐서 '어떻게' data를 보낼 수 있을까??

바로 `IP` 라는 규약을 통해서 보낸다.

<br>

## 1.1 IP란??

> **_Internet Protocol 약어로, 인터넷 데이터 통신을 원활히 하기 위해 필요한 통신 '규약'_**

<br>

IP의 역할은

- 지정한 IP 주소로,
- packet이라는 통신 단위로,
- 데이터를 전달하는 역할이다.

<br>

그래서, 원하는 서버와 클라이언트에 데이터가 도달하기 위해서는 컴퓨터를 구분하는 고유 IP 주소(IP address)를 부여받아야 한다. 이 IP 주소의 형식은 100.100.100.1 같이 각 부분을 점으로 구분하여 표현한다.

- IP 주소 형식에 대해서도 IPv4, IPv6 로 분류된다. IPv4는 점으로 구분된 부분이 4개의 part고, IPv6는 6개의 파트로 구분된다. IPv4로는 주소 수가 부족하여 IPv6가 등장했다.

<br>

---

## 1.2 Packet이란?

> **_수화물을 의미하는 Package와 덩어리를 의미하는 bucket의 합성어로, 통신 단위 packet에는 여러 데이터가 담겨져 있지만, 기본적으로 '주소지 정보(출발지의 IP 주소, 도착지의 IP 주소)' 그리고, 전달하려는 '전송 데이터'가 있다._**

<br>

인터넷 망을 구성하는 node들은 다 IP를 따르기 때문에, node들은 출발지가 어디고, 어디가 도착지인지 이해할 수 있다. 그래서 node들이 IP를 참고하여 서로 `packet`을 던지면서 원하는 목적지로 보내진다.

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FtptXC%2Fbtq1TM1JO1G%2Fiuuu8dAUsWdm9gRPQ02IA0%2Fimg.png)

위의 이미지는 클라이언트의 패킷을 전달하는 내용이다.  
서버의 패킷을 전달하는 것도 동일한 원리다.

---

# 2. IP 문제점의 해결책: TCP

<br>

이 IP는 3가지 문제점(한계)이 있다.

<br>

- 비연결성 문제

  - 상대방이 받을 수 없는 상태여도 패킷을 전송한다.
    - 패킷을 받는 대상이 없거나 (컴퓨터가 off된 경우), 서비스 불능 상태여도 패킷을 전송한다. 연결이 안되도 보내지는 문제점이 존재한다.
    - ex) 우편물을 A 주소로 보냈지만, 도착해서 보니 A 주소에 집이 없는 경우를 말한다.

- 비신뢰성 문제

  - 손실 문제: packet이 전송되는 과정에 중간에 사라지는 경우
    - ex) 노드 서버에 도착했는데, 갑자기 케이블이 끊어지는 경우
  - 순서 바뀜 문제: packet의 용량 문제로 나눠 보낼 때 순서에 문제가 발생한 경우

- 프로그램 구분 문제

  - 한 IP 주소에서 2개 이상의 application을 사용하고 있을 때, 무슨 application에 관한 정보인지 어떻게 구분하는가??

<br>

## 2.1 인터넷 프로토콜 스택의 4계층

<br>

이를 해결한 것이 바로 `TCP` 다.

TCP에 대해 알기에 앞서 `인터넷 프로토콜 스택의 4계층`에 대해 알아보자.

<br>

인터넷 프로토콜은 4계층으로, 순서는 애플리케이션(응용) 계층 > 전송 계층 > 인터넷 계층 > 네트워크 인터페이스 순으로 구성된다.

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F999DED345B7A26DF05)

1. 애플리케이션 계층(Application layer) - HTTP, FTP
2. 전송계층(Transport layer) - TCP, UDP
3. 인터넷 계층(Internet layer) - IP
4. 네트워크 인터페이스 계층(Network Access layer)

<br>

이 계층 순서로 어떻게 packet을 보내는지 알아보자.

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fuuas1%2FbtrnlmHave1%2F33bKdoh9rcPnEI97wtkgDK%2Fimg.png)

<br>

- resource 요청 시, Application layer에서 HTTP 메세지를 생성한다.  
  -> 3 way handshake를 통해 socket에 연결한다.  
  -> socket library를 통해 transport layer 계층으로 데이터를 전송한다.  
  -> transport layer에서 HTTP를 포함한 TCP 정보를 씌운다.  
  -> Internet layer에서 TCP 정보를 포함하는 IP 패킷을 생성한다.  
  -> IP 패킷 정보가 인터넷을 거쳐서 서버에 도착한다.  
  -> IP 패킷이 서버에 도착하면 IP 패킷과 TCP 세그먼트는 버리고, HTTP 메세지를 서버가 해석한다.  
  -> HTTP 응답 메시지를 동일한 방식으로 packet을 생성하여 응답 패킷을 전달한다.  
  -> 수 많은 노드들을 통해서 응답 패킷이 도착하면, 웹 브라우저가 HTML 렌더링하여 화면에 보여준다.

- socket이란??
  - application layer와 transport layer 사이에 위치하여, process가 메시지를 송신하고 수신할 수 있도록 API를 제공해주는 역할을 한다.

<br>

TCP 정보와 IP packet을 생성한 데이터 안에 담겨진 구체적인 내용은 다음과 같다.

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F3a8M7%2FbtrnjiljtYp%2FfPkwoHEeeJAkMGDOPIYn30%2Fimg.png)

<br>

<br>

---

## 2.2 IP 문제점의 해결책: TCP

`TCP (Transmisstion Control Protocol)`는 전송 제어 프로토콜로, IP의 3가지 문제점에 대한 해결책이다. TCP에 여러 특징들이 있지만, 위 문제점을 해결하는 3가지 특징에 대해 중점적으로 알아보자.

<br>

### 2.2.1 연결지향 - TCP 3 way handshake (가상 연결)

<br>

![image](https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F197da095-716c-4dc6-a60c-8c30521537c3%2FUntitled.png&blockId=3f99a4e7-2066-4106-beaa-f0124f44aaa6)

<br>

클라이언트가 서버에 데이터를 전송하기 전에 '연결과정'을 거친다. 이 과정으로 IP의 비연결성 문제를 해결한다.

- 첫 번째, 클라이언트가 서버에 접속 요청(SYN)한다. 접속 요청하는 걸 `SYN(Synchronization)` 이라 한다.
- 두 번째, 그 후 서버는 클라이언트의 요청을 수락(ACK)한다. 서버도 클라이언트에게 접속 요청(SYN)한다. 요청을 수락하는 걸 `ACK(Acknowledgement)` 라 한다.
- 세 번째, 클라이언트가 서버의 접속 요청에 수락(ACK)한다.

<br>

이 3가지 단계를 거친 후, 클라이언트가 서버에 데이터를 전송한다. 그래서 `SYN -> SYN + ACK -> ACK` 순서로 `3 way handshake`가 진행된 후, 데이터를 전송한다.

하지만, 때로는 세 번째 단계 ACK할 때 데이터를 함께 전송한다.

<br>

> **_3 way handshake 방식은 물리적으로 직접 연결된 상태가 아니라, 논리적으로 연결된 상태이다. 이 의미는 클라이언트와 서버 사이에 무수히 많은 노드들을 거쳐서 연결된 것을 의미한다. 물리적으로 직접 연결된 상태라는 건 클라이언트와 서버가 직섭 랜선으로 연결된 경우를 말한다._**

<br>

---

### 2.2.2 데이터 전달 보증

데이터를 전송하면 수신 확인 메세지를 클라이언트에게 보내준다. IP의 '비신뢰성' 문제를 해결한다.

<br>

![image](https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fea2caa3f-2dd6-45d6-8b61-5822af8d15f1%2FUntitled.png&blockId=087e1377-06f0-4590-b3a7-2d211bdeb82d)

---

### 2.2.3 순서 보장

생성한 HTTP data에 TCP 정보를 씌울 때, 순서 정보가 들어가기 때문에, 데이터를 받고 나서 의도한 순서대로 온 건지 판단할 수 있다. IP의 '순서 바뀜' 문제를 해결한다.

![image](https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F21919f79-8bac-49f3-b09f-7ee52b30b00b%2FUntitled.png&blockId=3c3cd24f-4f23-4f41-9cea-94c1cabe8cb0)

---

# 3. TCP 문제점의 해결책: UDP

IP의 여러 문제점을 해결하는 TCP이지만, 위에 TCP segment에 들어가는 정보들처럼 정보양이 많기 때문에 시간이 오래 걸리고, 최적화가 어렵다. 또한, 인터넷 자체도 이미 TCP 기반이라 다듬을 수 없다. 그래서 UDP를 최적화하여 속도를 증가시킬 수 있다. 최근에 이 UDP가 뜨고 있다. 웹 브라우저가 TCP handshake 과정을 줄일려고 하기 때문이다.

<br>

- TCP의 속도 문제를 해결할 수 있는게 `UDP(User Datagram Protocol)`이다.
- 데이터를 데이터그램 단위로 처리하는 프로토콜이란 의미다.
- 연결지향 X, 데이터 전달 보증과 순서보장 X 지만, 단순하고 빠르다.
- IP와 거의 같지만, 차이점은 PORT와 checksum 기능이 있다.

<br>

그리고 바로 이 `PORT`라는 기능이 IP의 세 번째 문제점을 해결해준다.

---

<br>

# 4. Port와 DNS란 무엇인가??

<br>

## 4.1 Port란?

한 IP에서 여러 Application을 사용하고 있을 때, 데이터를 원하는 Application으로 보내기 위해서 `PORT` 가 필요하다. 이 port 정보는 TCP 세그먼트에 포함되어 있다.

<br>

그래서 IP packet에 있는 IP 주소로 원하는 클라 또는 서버에 도달한다. 그리고, 클라 또는 서버 안에 원하는 Application에 데이터를 제공하기 위해서 PORT 정보를 활용한다.

<br>

Port number는

- 0 ~ 65535번까지 할당이 가능하다.
- 0 ~ 1023번은 잘 알려진 포트이기 때문에, 사용하지 않는 것이 낫다.
  - FTP - 20, 21
  - TELNET - 23
  - HTTP - 80
  - HTTPS - 443

![image](https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F81941bfb-5c61-42f1-a907-6dfcfcc766c9%2FUntitled.png&blockId=3f53ccd1-f694-40e5-891e-28677058ea77)

<br>

위 이미지를 예를 들어 서버 IP 200.200.200.3 에서 클라이언트의 웹 브라우저 요청에 응답하기를 원한다면 도착지 IP는 100.100.100.1 이고, PORT는 10010 이다.

<br>

---

## 4.2 DNS란?

`DNS`는 `Domain Name System`으로, 기억하기 어렵고 변경될 수 있는 IP address 대신에 Domain Name을 사용하면 DNS에서 이 Domain name에 해당되는 IP주소로 응답하여 접속하는 시스템을 말한다.

![image](https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F1a902ca4-a148-494f-9f35-487ac50bdba4%2FUntitled.png&blockId=f2c0a3ec-9692-4046-a177-16784339df70)

---

# Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [1. 인터넷 네트워크](https://catsbi.oopy.io/ce77a5c6-a2bd-4a87-a5dd-6c73293d8efb)
- [Application layer](https://heegyukim.medium.com/computer-network-2-application-layer-services-826603eda244)
- [Socket](https://blog.naver.com/PostView.naver?blogId=dbstnrrud93&logNo=221897635561&parentCategoryNo=&categoryNo=77&viewDate=&isShowPopularPosts=true&from=search)

---

## Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [1. 인터넷 네트워크](https://catsbi.oopy.io/ce77a5c6-a2bd-4a87-a5dd-6c73293d8efb)
- [Application layer](https://heegyukim.medium.com/computer-network-2-application-layer-services-826603eda244)
- [Socket](https://blog.naver.com/PostView.naver?blogId=dbstnrrud93&logNo=221897635561&parentCategoryNo=&categoryNo=77&viewDate=&isShowPopularPosts=true&from=search)
