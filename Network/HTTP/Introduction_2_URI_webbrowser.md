# HTTP Introduction 2: URI 와 Web browser의 흐름

<br>

## Intro

> 1. [URI](#1-uri)
> 2. [웹 브라우저 요청 흐름](#2-웹-브라우저-요청-흐름)

- HTTP에 관한 학습내용의 기본 출처는 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC) 이다.
- 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.
- 이 강의는 HTTP에 대한 웹 기본지식을 설명하는 강의이므로, 내용이 간략할 수 있다.

<br>

- 학습 이유: 프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고 시작하고 싶어 HTTP 공부를 시작한다. 이 강의에 대해 공부 후, 네트워크 전반에 대해 공부한다.

---

<br>

## 1. URI

- URI (Uniform Resource Identifier)란??
  - 로케이터(locater), 이름(name) 또는 둘 다 추가로 분류될 수 있다.
    - from https://www.ietf.org/rfc/rfc3986.txt - 1.1.3. URI, URL, and URN

<p align="center"><image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F55603624-57bc-422d-a423-1a2f3bc1d490%2FUntitled.png&blockId=7544083d-0ca3-48c5-a59a-1a24553ad18d" width = '400' height ='200'/></p>

<br>

### 1.1 URI, URL, URN의 각 의미

- URI의 단어 뜻
  - **U**niform: 리소스를 식별하는 통일된 방식
  - **R**esource: URI로 식별하는 수 있는 모든 자원으로, 제한 없다.
  - **I**dentifier: 다른 항목과 구분하는데 필요한 정보 (식별자)
- URL의 단어 뜻
  - Locator: resource가 있는 위치를 지정한다.
- URN의 단어 뜻
  - Name: resource에 이름을 부여한다.
- 위치는 변할 수 있지만, 이름은 변하지 않는다.
- urn:isbn:8960777331 (어떤 책의 isbn URN)
- URN 이름만으로 실제 리소스를 찾을 수 있는 방법이 보편화 되지 않았다.
- 그래서 **앞으로 URI를 URL과 같은 의미로 이야기하겠다**

<p align="center"><image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fd0c5b0b1-845e-4751-8994-16c6f24fce7e%2FUntitled.png&blockId=9a57f9d6-8a0d-4b02-bd36-20477512065d" width = '400' height ='200'/></p>

<br>

### 1.2 URL 분석

> **URL 전체 문법 구조**  
> **scheme://[userinfo@]host[:port][/path][?query][#fragment]**

- 예시: https://www.google.com:443/search?q=hello&hl=ko
- 프로토콜: `https`
- 호스트명: `google.com`
- 포트번호: `443`
- path: `\search`
- query parameter: `?q=hello&hl=ko`

<br>

**- scheme (ex: https)**

> **scheme:**//[userinfo@]host[:port][/path][?query][#fragment]  
> **https:**//www.google.com:443/search?q=hello&hl=ko

- 주로 프로토콜을 사용한다.
  - 프로토콜이란 어떤 방식으로 자원에 접근할건지 약속된 규칙이다.
  - 예: http, https, ftp 등등
- http는 80포트, https는 443포트를 주로 사용하며 포트는 생략 가능하다.
- https는 http에 보안 사용을 추가한 것이다. (HTTP Secure)

<br>

**- userinfo**

> scheme://**[userinfo@]**host[:port][/path][?query][#fragment]  
> https://www.google.com:443/search?q=hello&hl=ko

- URL에 사용자 정보를 포함해서 인증할 때 사용한다.
- 하지만 거의 사용하지 않는다.

<br>

**- host (ex: www.google.com)**

> scheme://[userinfo@]**host**[:port][/path][?query][#fragment]  
> https://www.google.com:443/search?q=hello&hl=ko

- 호스트명이다.
- domain 명 또는 IP 주소를 직접 입력한다.

<br>

**- PORT (ex: 443)**

> scheme://[userinfo@]host **[:port]**[/path][?query][#fragment]  
> https://www.google.com:443/search?q=hello&hl=ko

- 접속 포트
- 일반적으로 생략한다. 생략시 http는 80, https는 443이다.

<br>

**- path (ex: /search)**

> scheme://[userinfo@]host[:port]**[/path]**[?query][#fragment]  
> https://www.google.com:443/search?q=hello&hl=ko

- 리소스의 경로다.
- 계층적 구조로 되어있다.
  - /home/file1.jpg
  - /members
  - /members/100, /item/iphone12

<br>

**- query (ex: q=hello&hl=ko)**

> scheme://[userinfo@]host[:port][/path]**[?query]**[#fragment]  
> https://www.google.com:443/search?q=hello&hl=ko

- key = value 형태로 되어 있다.
- ?로 시작하며 &로 추가 가능하다.
  - ex) ?keyA=valueA&keyB=valueB
- query parameer, query string 등으로 불린다. 웹서버에 제공하는 파라미터, 문자형태다.  
  <br>

**- fragment (ex: getting-started-introducing-spring-boot)**

> scheme://[userinfo@]host[:port][/path][?query]**[#fragment]**  
> https://docs.spring.io/spring-boot/docs/current/reference/html/gettingstarted.html#getting-started-introducing-spring-boot

- html 내부 북마크 등에 사용한다.
- 서버에 전송하는 정보가 아니다.

---

<br>

## 2. 웹 브라우저 요청 흐름

다음 URL을 가지고 `https://www.google.com:443/search?q=hello&hl=ko` 웹 브라우저가 어떻게 요청해서 진행되는지 흐름을 파악해보자.

<p align="center"><image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0d4da4fb-b09a-40e9-ab1f-999c50f6e373%2FUntitled.png&blockId=af1f546e-4ecf-4089-9af4-504777ae78da" width = '400' height ='200'/></p>

1. DNS 조회: google.comd을 DNS에서 조회하여 해당 IP 주소를 찾는다.
2. HTTPS PORT는 생략한다. `443`
3. HTTP 요청 메시지를 클라이언트가 생성한다. HTTP 요청 메시지는 다음과 같다.

<p align="left"><image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F92d6ece1-5c3b-4bd5-8820-3d787ea97123%2FUntitled.png&blockId=852f034f-65a1-4e63-bcc9-581c399aecb6" width = '400' height ='200'/></p>

그러면 'Itroduction 1: Internet Network' 에서 학습한 과정이 진행된다.

<p align="center"><image src ="https://user-images.githubusercontent.com/78094972/156995792-e9d1254f-2424-4b55-81a5-ac5dfba9ef30.PNG" width = '400' height ='200'/></p>

<br>

### HTTP 메시지 전송

1. resource 요청 시, Application layer에서 HTTP 메세지를 생성한다.
2. 3 way handshake를 통해 socket에 연결한다.
3. socket library를 통해 transmission layer 계층으로 데이터를 전송한다.
4. transmission layer 계층에서 HTTP를 포함한 TCP 정보를 씌운다.
5. TCP 정보를 포함하는 IP 패킷을 생성한다.

<p align="center"><image src ="https://user-images.githubusercontent.com/78094972/157241361-b1b91484-f73b-4c2a-86b5-5dbf066b85a6.PNG" 
" width = '400' height ='200'/></p>

1. 서버는 패킷이 도착하면 패킷의 내부 HTTP method를 해석해서 정보에 맞는 동작을 한다.
2. 서버에서 HTTP 응답 메세지를 생성한다.

<p align="center"><image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5488679c-ffc0-4e0f-bc6b-7a0b7ad4b812%2FUntitled.png&blockId=d9d14474-2768-4145-b2e8-ffd62f37750d" width = '400' height ='200'/></p>

8. 클라이언트에서는 응답 메세지를 받아 HTML 렌더링을 한다.

<p align="center"><image src ="https://user-images.githubusercontent.com/78094972/157242597-f69fba96-1411-4b06-a930-9c423dcefb3a.PNG" width = '400' height ='200'/></p>

---

<br>

## Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [URI와 웹 브라우저 요청 흐름](https://catsbi.oopy.io/6befbf82-ce78-4fb9-bb87-805ec1048855)
