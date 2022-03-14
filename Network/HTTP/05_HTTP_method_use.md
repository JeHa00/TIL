# Intro

> 1. [클라이언트에서 서버로 데이터 전송](#1-클라이언트에서-서버로-데이터-전송)
> 2. [HTTP API 설계](#2-http-api-설계)
> 3. [참고하면 좋은 URI 설계 개념](#3-참고하면-좋은-uri-설계-개념)

<br>

- HTTP 학습내용의 기본 출처: 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.
- 이 강의는 HTTP에 대한 웹 기본지식을 설명하는 강의이므로, 내용이 간략할 수 있다.

<br>

- 학습 이유: 프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고자 HTTP 공부를 시작한다. 이 강의에 대해 공부 후, 네트워크 전반에 대해 공부한다.

<br>

# 1. 클라이언트에서 서버로 데이터 전송

> 1.1 [정적 데이터 조회](#11-정적-데이터-조회)
> 1.2 [동적 데이터 조회](#12-동적-데이터-조회)
> 1.3 [HTML Form을 통한 데이터 전송](#13-html-form을-통한-데이터-전송)
> 1.3 [HTML API를 통한 데이터 전송](#14-http-api를-통한-데이터-전송)

<br>

- 클라이언트에서 서버로 데이터를 전달하는 방식은 크게 2가지가 있다.
  - **쿼리 파라미터를 통한 데이터 전송**
    - GET을 많이 사용한다.
    - 예) 주로 정렬 필터나 검색어를 사용할 때 쿼리 파라미터를 많이 사용한다.
  - **메시지 바디를 통한 데이터 전송**
    - POST, PUT, PATCH를 사용한다.
    - 예) 회원 가입, 상품 주문, 리소스 등록, 리소스 변경하는데 사용한다.

<br>

- 그리고, 클라이언트가 서버로 데이터를 전송하는 4가지 상황이 있다. 각 상황에서 어떻게 클라이언트가 서버에 데이터를 전달하는지 알아보자.

---

# 1.1 정적 데이터 조회

- 예) 이미지, 정적 텍스트 문서
- 정적 데이터는 일반적으로 쿼리 파라미터 없이 `GET` method로 `resource path만` 적어, 단순하게 조회가 가능하다.

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F2a0386de-df5f-430f-b267-3902b833591c%2FUntitled.png&blockId=f016c60e-0e24-4cf4-a24a-4643ab1ab4ab"/></p>

<br>

---

# 1.2 동적 데이터 조회

- 예) 주로 검색,게시판 목록에서 검색어를 사용하여 정렬 필터한다. (검색어)
- 조회 조건을 줄여주는 필터, 조회 결과를 정렬하는 정렬 조건에 주로 사용한다.
- 조회이기 때문에 `GET` 사용하지만, 정적 데이터가 아닌 동적 데이터이므로, `쿼리 파라미터를 사용`해서 데이터를 클라이언트가 서버에게 넘겨줘야 한다.
- GET도 메세지 바디를 사용할 수 있지만, 지원하지 않는 경우가 많아서 사용하지 않는다.

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0082a8b8-b24d-41ed-81b9-ac485f67cfe0%2FUntitled.png&blockId=82858c8a-47aa-4adf-a1db-4ee739577065"/></p>

<br>

---

# 1.3 HTML Form을 통한 데이터 전송

<br>

- **HTML Form 전송은 GET, POST만 지원한다.**
- 예) 회원 가입, 상품 주문, 데이터 변경

<br>

## POST 전송 - 저장

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fa35eb384-0cd3-4fad-982c-1cd3f058d7a1%2FUntitled.png&blockId=9d2c7b8f-bde7-4bd4-b438-fe3924180f36"/></p>

- 예) 회원 가입, 상품 주문, 데이터 변경 상황
- 전송 버튼을 누르면 웹 브라우저가 form 태그 정보를 읽어서, HTTP message를 생성한다. 그리고, action에 작성된 경로로 해당 method 요청을 보낸다.
- 쿼리 파라미터랑 동일한 형식으로 HTTP message body에 넣어 서버에 전송된다.
  - `username=kim&age=20`

<br>

## GET 전송 - 저장 (오류)

<p align="center"> <image src ="https://media.vlpt.us/images/dnstlr2933/post/d7518bef-bada-4ad9-9702-1ad70ab6a883/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-01-07%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%2012.18.02.png"/></p>

- GET이라 메세지 바디를 안쓰기 때문에, url 경로에다가 data를 넣는다. 즉, Query parameter 형식으로 넣어준다.
- 또한, GET은 조회용으로만 사용가능하므로 저장하는 곳에 사용하면 안된다.
- GET은 조회이므로, `/members` 등으로 조회가 가능한 곳으로 보내야 정상적으로 처리된다.

> [결론]
> FORM에서 GET / POST의 사용에 맞춰서 웹 브라우저가 알아서 `HTTP 요청 메세지`의 구성을 Query 또는 Body 등으로 맞춰서 생성한다.

<br>

## multipart/form-data

<p align="center"> <image src ="https://media.vlpt.us/images/dnstlr2933/post/296861ed-bce7-431f-8e3c-13a0b0f73d18/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-01-09%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2010.16.57.png"/></p>

- 다른 종류의 여러 파일과 폼의 내용을 함께 전송이 가능하다. 그래서 이름이 mulitpart다.
- 주로 binary data를 전송할 때 사용한다.
- 웹브라우저가 생성한 요청 HTTP 메시지의 content-type에 boundary 가 명시되어 있는데, form data 간 구분을 지어준다.

<br>

---

# 1.4. HTTP API를 통한 데이터 전송

<br>

- HTML Form을 쓰지 않는 모든 상황을 말한다.
- 예) 회원 가입, 상품 주문, 데이터 변경에 사용
- 그러면 언제 사용하는가???
  - 서버 to 서버: 백엔드 시스템 통신
  - 앱 클라이언트에서 전송 시 (아이폰,안드로이드)
  - 웹 클라이언트에서 HTML Form 전송 대신 자바 스크립트를 통한 통신에 사용 (AJAX)
    - 예)React, Vue.JS 같은 웹 클라이언트와 API 통신
- POST, PUT,PATCH: 사용하며, 메시지 바디를 통해 데이터를 전송
- GET: 조회, 쿼리, 파라미터로 데이터를 전달
- Content-type: application/json을 주로 사용 (사실상 표준)
  - TEXT, XML, JSON 등등이 있지만, XML이 읽기 어렵고, 복잡해서 지금은 JSON을 사용한다. 데이터 크기도 상대적으로 XML보다 작아서, 사실상 JSON이 표준이다.

<br>

---

# 2. HTTP API 설계

- HTTP API 설계에는 **3가지 종류**가 있다.
  - HTTP API - **collection**
    - **POST 기반** 등록
    - **서버가 리소스 URI 결정**
  - HTTP API - **store**
    - **PUT 기반** 등록
    - **클라이언트가 리소스 URI 결정**
  - HTML FORM 사용
    - 순수 HTML + HTML form 사용
    - GET, POST만 지원

<br>

- 이 3가지에 대해 각각 알아보자.

<br>

## 2.1 HTTP API - collection

> **서버가 새로 등록된 리소스 URI를 생성하고 관리하는 구조: Collection**

<br>

- 회원 관리 시스템: API 설계 - POST 기반 등록
  - 회원 목록 /members -> GET
  - 회원 등록 /members -> POST
  - 회원 조회 /members/{id} -> GET (회원 단권 조회)
  - 회원 수정 /members/{id} -> PATCH, PUT, POST
  - 회원 삭제 /members/{id} -> DELETE

> 실제로는 위의 경우처럼 명확하게 구분되지 않기 때문에, `컨트롤 URI` 를 사용할 수 밖에 없다.

<br>

- 클라이언트는 등록될 리소소의 URI를 모른다.
  - 회원 등록 /members -> POST
  - POST /members
- _서버가 새로 등록된 리소스 URI를 생성한다._
  - HTTP/1.1 201 Created  
    Location: /members/100
- _Collection_
  - 서버가 관리하는 리소스 디렉토리
  - 서버가 리소스의 URI를 생성하고 관리
  - 여기서 collection은 /members

---

<br>

## 2.2 HTTP API - store

> **클라이언트가 직접 resource uri를 지정하고, 관리하는 구조: Store**

<br>

- 파일 관리 시스템: API 설계 - PUT 기반 등록
  - 파일 목록 /files -> GET
  - 파일 조회 /files/{filename} -> GET
  - 파일 등록 /files/{filename} -> PUT
  - 파일 삭제 /files/{filename} -> DELETE
  - 파일 대량 등록 /files -> POST
    - 파일 등록에 PUT을 썼기 때문에, 대량 등록에는 POST를 썼다.
    - 왜냐하면 POST는 임의로 의미를 만들 수 있다.

<br>

- 클라이언트가 리소스 URI를 알고 있어야 한다.
  - 파일 등록/files/{filename} -> PUT
  - PUT/files/star.jpg
- _클라이언트가 직접 리소스의 URI를 지정한다._
- _Store_
  - 클라이언트가 관리하는 리소스 디렉토리
  - 클라이언트가 리소스의 URI를 생성하고 관리
  - 여기서 store는 /files

---

<br>

## 2.2 HTML FORM 사용

- 회원 목록 /members -> GET
- 회원 등록 폼 /members/new -> GET
- 회원 등록 /members/new, /members -> POST
  - 위에 등록 폼과 같은 url을 쓰는 것을 추천한다.
- 회원 조회 /members/{id} -> GET
- 회원 수정 폼 /members/{id}/edit -> GET
  - 실제로 수정일 일어나는 게 아니기 때문에, GET 을 사용한다.
- 회원 수정 /members/{id}/edit, /members/{id} -> POST
  - 위에 수정 폼과 같은 url을 쓰는 것을 추천한다.
- 회원 삭제 /members/{id}/delete -> POST

<br>

- HTML FORM은 _GET, POST만 지원_
- GET, POST만 지원하므로 제약이 있다.
- AJAX 같은 기술을 사용해서 해결 가능하다 -> 회원 API 참고
- 여기서는 순수 HTML, HTML FORM 이야기다.

<br>

---

# 3. 참고하면 좋은 URI 설계 개념

- **문서(document)**
  - 단일 개념(파일 하나, 객체 인스턴스, 데이터베이스 row)
  - 예) /members/100, /files/star.jpg
- **컬렉션(collection)**
  - 서버가 관리하는 리소스 디렉터리
  - 서버가 리소스의 URI를 생성하고 관리
  - 예) /members
- **스토어(store)**
  - 클라이언트가 관리하는 자원 저장소
  - 클라이언트가 리소스의 URI를 알고 관리
  - 예) /files
- **컨트롤러(controller), 컨트롤 URI**

  - 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행
  - 동사를 직접 사용
  - 예) /members/{id}/delete

- https://restfulapi.net/resource-naming 참고하기
  - 여러 사람들이 HTTP API를 하다보니, 좋은 practice가 있다.

<br>

---

# Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [HTTP 메서드 활용](https://catsbi.oopy.io/e43388ef-bc41-4f44-986c-010c12b596dd)
