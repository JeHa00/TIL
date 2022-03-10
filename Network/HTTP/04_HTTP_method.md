---
title: "[TIL] Network HTTP method"
date: 2022-03-10T20:32:24+09:00
draft: true
summary: HTTP method인 GET, POST, PUT, PATCH, DELETE 그리고 속성에 대해 알아본다.
tags: ["TIL", "Network", "HTTP method"]
categories: ["개발-dev"]
---

# Intro

> 1. [HTTP API 설계해보기(Http method intro.)](#1-http-api-설계해보기-http-method-intro)
> 2. [HTTP method - GET, POST](#2-http-method---get-post)
> 3. [HTTP method - PUT, PATCH, DELETE](#3-http-method---put-patch-delete)
> 4. [HTTP method의 속성](#4-http-method의-속성)

<br>

- HTTP 학습내용의 기본 출처: 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC) 이다.
- 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.
- 이 강의는 HTTP에 대한 웹 기본지식을 설명하는 강의이므로, 내용이 간략할 수 있다.

<br>

- 학습 이유: 프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고자 HTTP 공부를 시작한다. 이 강의에 대해 공부 후, 네트워크 전반에 대해 공부한다.

<br>

# 1. HTTP API 설계해보기 (HTTP method intro.)

- API 설계에서 가장 중요한 것은 _리소스를 식별하는 게 중요하다._
- 그 다음으로 해야할 것이 *리소스와 행위를 분리하는 것*이다.

- 회원 정보 관리 API를 만든다고 가정하자.
  - 회원 목록 조회
  - 회원 조회
  - 회원 등록
  - 회원 수정
  - 회원 삭제

<br>

- 그러면 초보 개발자인 경우, 다음과 같이 API URI 설계할 수 있다.

  - 회원 목록 조회 /read-member-list
  - 회원 조회 /read-member-by-id
  - 회원 등록 /create-member
  - 회원 수정 /update-member
  - 회원 삭제 /delete-member

- 위 방식의 문제점은 URI의 정의를 정확히 살리지 못하는 방법이다.
  - URI = Uniform Resource Identifier
- 가장 중요한 것은 *리소스 식별*이다.
- 리소스의 의미는
  - 회원을 등록하고, 수정하고 조회하는 게 리소스가 아니다.
    - 예) 미네랄을 캐라 -> 미네랄이 리소스
  - _회원이라는 개념 자체가 바로 리소스다._
- 그러면 리소스를 어떻게 식별하는게 좋을까??
  - 회원을 등록하고, 수정하고, 조회하는 것을 모두 배제한 다음
  - _회원이라는 리소스만 식별하면 된다 -> 회원 리소스를 URI에 매핑한다._

<br>

- 위 사항들을 반영하면 다음과 같다. (리소스 식별, URI 계층 구조 활용)
- 참고: 계층 구조상 상위를 컬렉션으로 보고 복수단어 사용 권장(member -> members)

  - 회원 목록 조회 /members
  - 회원 조회 /members/{id}
  - 회원 등록 /members/{id}
  - 회원 수정 /members/{id}
  - 회원 삭제 /members/{id}

- 그러면 회원 조회부터 삭제는 어떻게 구분할 수 있을까???
  - _리소스와 행위를 분리_ 하여 구분한다.
  - 리소스: 회원
  - 행위: 조회, 등록,삭제, 변경
  - 리소스는 명사, 행위는 동사

<br>

- 행위는 어떻게 구분할 수 있을까?? **HTTP method**에 대해 알아보자.

---

<br>

# 2. HTTP method - GET, POST

- HTTP 주요 method

  - GET: 리소스 조회
  - POST: 요청 데이터 처리, 주로 등록에 사용
  - PUT: 리소스를 대체, 해당 리소스가 없으면 생성
  - PATCH: 리소스 부분 변경
  - DELETE: 리소스 삭제

- HTTP 기타 method
  - HEAT: GET과 동일하지만 메시지 부분을 제외하고, 상태 줄과 헤더만 반환
  - OPTIONS: 대상 리소스에 대한 통신 가능 옵션(메서드)을 설명(주로 CORS에서 사용)
  - CONNECT: 대상 자원으로 식별되는 서버에 대한 터널을 설정
  - TRACE: 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행

<BR>

- 이번 단원에서는 GET과 POST에 대해 자세히 알아보자.

## 2.1 HTTP method - GET

```yml
GET /search?q=hello&hl=ko HTTP/1.1
Host: www.google.com
```

- GET

1.  **리소스 조회** (/search?q=hello&hl=ko)
2.  서버에 전달하고 싶은 데이터는 query (쿼리 파라미터, 쿼리 스트링)를 통해서 전달
3.  메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아서 권장하지 않는다.

- 리소스 조회 예시

![image](https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F2ddf8c73-307f-4f5e-96f8-7f3a67a89eaf%2FUntitled.png&blockId=66457d66-3f42-46f0-ac0e-9715e547f825)

1. 클라이언트에서 /members/100 으로 100번 유저를 조회해서 정보를 달라고 GET 요청을 보낸다.
2. 서버에서는 받은 메세지를 분석해 내부의 유저 정보를 조회한 후, 결과 Response를 만든다.
3. 응답 메세지를 받았고, 정상적으로 받았기에 200 OK status를 가진다. 또한, 회원 정보다 담겨있다.
   - 위 에시에는 JSON이지만, 실제로는 HTML일수도 있고, 다양한다.

<br>

## 2.2 HTTP method - POST

```yml
POST /members HTTP/1.1
Content-Type: application/json
{
"username": "hello",
"age": 20
}
```

- POST

1. 요청 데이터 처리
2. 메시지 바디를 통해 서버로 요청 데이터 전달
3. 서버는 요청 데이터를 처리
   - 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행한다.
4. 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용

---

<br>

# 3. HTTP method - PUT, PATCH, DELETE

---

<br>

# 4. HTTP method의 속성

---

<br>

# Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [HTTP 메서드](https://catsbi.oopy.io/1b703a8f-9b02-4443-a28f-8ef3e2223f13)
