# Intro

> 1. [HTTP API 설계해보기(Http method intro.)](#1-http-api-설계해보기-http-method-intro)
> 2. [HTTP method - GET, POST](#2-http-method---get-post)
> 3. [HTTP method - PUT, PATCH, DELETE](#3-http-method---put-patch-delete)
> 4. [HTTP method의 속성](#4-http-method의-속성)

<br>

- HTTP 학습내용의 기본 출처: 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.
- 이 강의는 HTTP에 대한 웹 기본지식을 설명하는 강의이므로, 내용이 간략할 수 있다.

<br>

- 학습 이유: 프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고자 HTTP 공부를 시작한다. 이 강의에 대해 공부 후, 네트워크 전반에 대해 공부한다.

<br>

---

# 1. HTTP API 설계해보기

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
- 가장 중요한 것은 `리소스 식별` 이다.
- 리소스의 의미는
  - 회원을 등록하고, 수정하고 조회하는 게 리소스가 아니다.
    - 예) 미네랄을 캐라 -> 미네랄이 리소스
  - **_회원이라는 개념 자체가 바로 리소스다._**
- 그러면 리소스를 어떻게 식별하는게 좋을까??
  - 회원을 등록하고, 수정하고, 조회하는 것을 모두 배제한 다음
  - _회원이라는 리소스만 식별하면 된다 -> 회원 리소스를 URI에 매핑한다._

<br>

---

- 위 사항들을 반영하면 다음과 같다. (리소스 식별, URI 계층 구조 활용)
- 참고: 계층 구조상 상위를 컬렉션으로 보고 복수단어 사용 권장(member -> members)

  - 회원 목록 조회 /members
  - 회원 조회 /members/{id}
  - 회원 등록 /members/{id}
  - 회원 수정 /members/{id}
  - 회원 삭제 /members/{id}

- 그러면 회원 조회부터 삭제는 어떻게 구분할 수 있을까???
  - **_리소스와 행위를 분리_** 하여 구분한다.
  - 리소스: 회원
  - 행위: 조회, 등록,삭제, 변경
  - 리소스는 명사, 행위는 동사

<br>

- 행위는 어떻게 구분할 수 있을까??
  - **HTTP method**를 사용하여 구분한다.
  - HTTP method에 대해 알아보자.

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
  - HEAT: GET과 동일하지만 메시지 바디 부분을 제외하고, 상태 줄과 헤더만 반환
  - OPTIONS: 대상 리소스에 대한 통신 가능 옵션(메서드)을 설명(주로 CORS에서 사용)
  - CONNECT: 대상 자원으로 식별되는 서버에 대한 터널을 설정
  - TRACE: 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행
    - CONNECT 와 TRACE는 거의 사용안한다.

<BR>

- 이번 단원에서는 GET과 POST에 대해 자세히 알아보자.

---

## 2.1 HTTP method - GET

```yml
GET /search?q=hello&hl=ko HTTP/1.1
Host: www.google.com
```

1.  **_리소스 조회_** (/search?q=hello&hl=ko에 있는 자원을 가져와라.)
2.  서버에 전달하고 싶은 데이터는 query (쿼리 파라미터, 쿼리 스트링)를 통해서 전달
3.  메시지 바디를 사용해서 데이터를 전달할 수 있다.
4.  하지만, 지원하지 않는 곳이 많아서 권장하지 않는다.
    - 실무에서는 GET에 메시지 바디를 안넣는다.

- 리소스 조회 예시

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F2ddf8c73-307f-4f5e-96f8-7f3a67a89eaf%2FUntitled.png&blockId=66457d66-3f42-46f0-ac0e-9715e547f825"/></p>

1. 클라이언트에서 /members/100 으로 100번 유저를 조회한다.
2. 그 후, 정보를 달라고 GET 요청을 보낸다.
3. 서버에서는 받은 메세지를 분석하여 내부의 유저 정보를 조회한다.
4. 그 후, JSON data 형태로 결과 Response를 만든다.
5. 응답 메세지를 받았고, 정상적으로 받았기에 200 OK status를 가진다.
6. 또한, 회원 정보도 담겨있다.
   - 위 에시에는 JSON이지만, 실제로는 HTML일수도 있고, 다양한다.

<br>

---

## 2.2 HTTP method - POST

<br>

### 2.2.1 POST란?

```yml
POST /members HTTP/1.1
Content-Type: application/json
{
"username": "hello",
"age": 20
}
```

1. 요청 데이터 처리하는 method
2. **_메시지 바디를 통해 서버로 요청 데이터를 전달한다._**
   - GET과의 차이점
3. 서버는 요청 데이터를 **처리**
   - 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행한다.
4. 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용

<br>

### 2.2.2 POST를 사용한 리소스 등록 과정

- 첫 번째: 메세지 전달

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F68d1a191-f78c-41bb-81b2-b2841b0bfca3%2FUntitled.png&blockId=210ed0fb-8405-4fbd-bd2a-b0ea0b037b67"/></p>

1. 클라이언트는 메세지 바디에 등록할 회원 정보를 JSON형태로 만들어 담는다.
2. 그리고 해당 정보를 서버로 전송한다.  
   ( 정보를 전달하기 전에, 사전에 서버가 무엇을 할지 미리 약속이 되어 있어야 한다.)

<br>

- 두 번째: 신규 리소스 생성

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F454628f3-768f-4854-a389-5cbee3a65e31%2FUntitled.png&blockId=50efcd8c-30b1-4b1d-af2b-c35c57e767be"/></p>

1. 서버에서 받는 메세지를 분석해 데이터베이스에 등록한다. 이 때 신규 아이디도 생성.

<br>

- 세 번째: 응답 데이터

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fce94e163-cda9-4de9-98ff-35eb74e68e0b%2FUntitled.png&blockId=3076f1aa-a3b7-4e85-90c7-841c3748338b"/></p>

1. Location이라는 헤더 정보로 회원이 생성된 경로를 첨부한다.
2. 신규회원에 대한 데이터를 바디에 담아서 보내준다.
3. 만들어졌기 때문에 Created라 뜬다. 그리고, 자원의 신규 생성된 URL을 보내준다.

<br>

### 2.2.3 요청 데이터를 어떻게 처리한다는 뜻일까??

> **_리소스 URI에 POST 요청이 오면, 요청 데이터를 어떻게 처리할지 리소스마다 따로 정해야한다  
>  -> 정해진 것이 없다._**

- 예를 들어 POST는 다음과 같은 기능에 사용된다.
  - HTML 양식에 입력된 필드와 같은 데이터 블록을 데이터 처리 프로세스에 제공
    - 예) HTML, FORM에 입력한 정보로 회원 가입, 주문 등에서 사용
  - 게시판, 뉴스 그룹, 메일링 리스트, 블로그 또는 유사한 기사 그룹에 메시지 게시
    - 예) 게시판 글쓰기, 댓글 달기
  - 서버가 아직 식별하지 않은 새 리소스 생성
    - 예) 신규 주문 생성
  - 기존 자원에 데이터 추가
    - 예) 한 문서 끝에 내용 추가하기

<br>

### 2.2.4 POST method 정리

**1. 새 리소스 생성(등록)**

- 서버가 아직 식별하지 않은 새 리소스 생성

**2. 중요! 요청 데이터 처리**

- 단순히 데이터를 생성하거나, 변경하는 것을 넘어서 `프로세스를 처리`해야 하는 경우
- 상태가 변하기 위해서 POST를 사용하기 때문에, 새로운 리소스가 생성되지 않을 수 있다. 그렇다 할지라도, `서버에 큰 변화를 일으킬 때`는 POST를 생성해야 한다.
  - 예) 주문에서 결제완료 -> 배달시작 -> 배달완료처럼 단순히 값 변경을 넘어 프로세스의 상태가 변경되는 경우
- POST의 결과로 새로운 리소스가 생성되지 않을 수도 있음
  - 예) POST/orders/{orderld}/start-delivery (**`컨트롤 URI`**)
    - URI를 설계할 때는 resource 단위로 설계해야 하지만, 어쩔 수 없이 행동으로 할 때가 있다. 이 때 동사의 URI가 나올 수 있다. 이 URI를 `컨트롤 URI` 라 한다.

**3. 다른 메서드로 처리하기 애매한 경우**

- 예) JSON으로 조회 데이터를 넘겨야 하는데, GET 메서드를 사용하기 어려운 경우
- **애매하면 POST**

---

<br>

# 3. HTTP method - PUT,PATCH,DELETE

## 3.1 HTTP method - PUT

```yml
PUT /members/100 HTTP/1.1
Content-Type: application/json
{
"username": "hello",
"age": 20
}
```

- **_리소스를 대체_**

  - 리소스가 있으면 대체하고, 없으면 생성한다. (Overwirte)

- **_POST와의 차이점: 클라이언트가 리소스를 식별한다._**

  - 클라이언트가 리소스 위치를 알고 URI 지정한다.

<br>

- 예) 리소스가 없는 경우
  - /members/100 이라는 신규 리소스를 생성한다.
  - 신규 리소스의 내용은 다음과 같다.

```yml
{ "username": "hello", "age": 20 }
```

- 예) 리소스가 있는 경우
  - /members/100 경로에 아래 내용으로 리소스가 있다면

```yml
{ "username": "young", "age": 50 }
```

- PUT method로 보내면 다음과 같이 대체된다.

```yml
{ "username": "hello", "age": 20 }
```

- 하지만 아래 내용으로 리소스를 보낸다면 아래 내용으로 완전히 대체된다.
- username 필드가 삭제된다.

```yml
{ "age": 50 }
```

<br>

---

## 3.2 HTTP method - PATCH

```yml
PUT /members/100 HTTP/1.1
Content-Type: application/json
{
"age": 50
}
```

- 그러면 기존 리소스를 갈아치우는 게 아니라, **_수정하고 싶으면_** 어떻게 해야할까??
- **_PATCH method_** 를 사용한다.
  - 만약 PATCH가 지원되지 않은 서버라면 POST를 사용한다.
- PUT과 양식은 비슷하지만, 서버에서 PATCH로 전송된 경우 **_필요한 부분만_** 업데이트된다.

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F0f50ed38-cf8a-4b89-b26d-cc6f008587ab%2FUntitled.png&blockId=304d1aae-0187-4285-a5b0-d1e03114deaf"/></p>

- 그 결과, PUT과는 다르게 회원 정보에서 age만 변경된다.

```yml
{ "age": 50 }
```

---

## 3.3 HTTP method - DELETE

```yml
DELETE /members/100 HTTP/1.1
Host: localhost:8080
```

- 클라이언트가 보내면 위 리소스 url에 해당되는 회원 정보를 서버에서 삭제한다.

---

<br>

# 4. HTTP method의 속성

- HTTP 메서드별 속성

<p align="center"> <image src ="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F320931bf-777b-4adf-ba2a-8245a7e8690b%2FUntitled.png&blockId=49eba0b3-a6e3-4ae0-9d32-39a1529034bd"/></p>

- 메서드의 속성
  - 안전(Safe Methods)
  - 멱등(idempotent Methods)
  - 캐시가능(Cacheable Methods)

<br>

---

## 4.1 안전(Safe)

> 호출해도 리소스를 변경하지 않는 속성

- GET은 단지 조회만 하기 때문에 안전하지만, 나머지는 아니다.
  - Q. 그래도 변경을 요청하면 변경되진 않아도, 로그에 계속 남게되어 터지지 않을까??
  - A: 안전은 해당 리소스만 고려한다. 그런 부분까지 고려하지 않는다.

<br>

---

## 4.2 멱등(Idempotent Methods)

> - f(f(x)) = f(x)
> - 몇 번을 호출하든 결과가 똑같은 속성

- 멱등 메서드
  - **GET: 몇 번을 조회하든 같은 결과가 조회된다.**
  - **PUT: 결과를 대체한다. 따라서 같은 요청을 여러번 해도 최종 결과는 동일.**
  - **DELETE: 결과를 삭제한다. 같은 요청을 여러번 해도 결과는 동일.**
  - **_POST_ : 멱등이 아니다! 두 번 호출하면 같은 결제가 중복해서 발생할 수 있다.**

<br>

- 활용
  - 자동 복구 메커니즘
  - 서버가 TIMEOUT 등으로 정상 응답을 못 주었을 때, 클라이언트가 같은 요청을 다시 해도 되는가?? 판단근거

<br>

- 멱등은 외부 요인으로 인해 리소스가 변경되는 건 고려하지 않는다.
  - 내가 호출하는 것에 한정한다.
  - 예시:
    - 사용자1: GET -> username:A, age:20
    - 사용자2: PUT -> username:A, age:30
    - 사용자1: GET -> username:A, age:30 -> 사용자2의 영향으로 바뀐 데이터 조회
  - 이런 부분은 멱등하지 않다고 생각하자.

<br>

---

## 4.3 캐시가능(Cacheable Methods)

- 응답 결과 리소스를 캐시해서 사용해도 될까?
- GET, HEAD, POST, PATCH 캐시가 가능하지만,
- 실제로는 GET, HEAD 정도만 캐시로 사용한다.
  - POST, PATCH는 본문 내용까지 캐시 키로 고려해야 하는데, 구현이 쉽지 않다.
  - GET은 URL만 캐시 키로 관리하면서 구현이 쉽기에 사용이 편하다.

---

<br>

# Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [HTTP 메서드](https://catsbi.oopy.io/1b703a8f-9b02-4443-a28f-8ef3e2223f13)
