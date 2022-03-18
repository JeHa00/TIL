# Intro

> 1. [캐시 기본 동작](#1-캐시-기본-동작)
> 2. [검증 헤더와 조건부 요청 1](#2-검증-헤더와-조건부-요청-1)
> 3. [검증 헤더와 조건부 요청 2](#3-검증-헤더와-조건부-요청-2)
> 4. [캐시와 조건부 요청 헤더](#4-캐시와-조건부-요청-헤더)
> 5. [프록시 캐시](#5-프록시-캐시)
> 6. [캐시 무효화](#6-캐시-무효화)

<br>

- HTTP 학습내용의 기본 출처: 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.
- 이 강의는 HTTP에 대한 웹 기본지식을 설명하는 강의이므로, 내용이 간략할 수 있다.

<br>

- 학습 이유: 프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고자 HTTP 공부를 시작한다. 이 강의에 대해 공부 후, 네트워크 전반에 대해 공부한다.

<br>

- 이번 chapter에서는 지난 HTTP header [[TIL] Network HTTP Header 1](https://jeha00.github.io/post/network/network_http_7/)에 이어서 `캐시와 조건부 요청` 관련된 header에 대해 집중적으로 알아본다.

- HTTP header의 용도는 [[TIL] Network HTTP basic](https://jeha00.github.io/post/network/network_http_3/#52-http-header)을 참고한다.

<br>

---

# 1. 캐시 기본 동작

<br>

## 1.1 캐시가 없을 때

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005459-4793f519-f8e2-4af3-894f-875b45664987.PNG"></p>

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005470-e710e815-a0c0-4c41-9d41-69c3ba901a59.PNG"></p>

- 클라이언트가 GET 메소드를 사용하여 /star.jpg 를 조회요청을 서버에 보낸다.
- 서버는 이에 응답하여 HTTP 메세지를 생성하고, HTTP body에 data를 담는다.
- data의 크기는 다음 같이 가정한다.
  - HTTP header의 data 크기: 0.1 Megabye
  - HTTP body의 data 크기: 1.0 Megabyte
- 서버의 응답에 클라이언트는 star.jpg를 받는다.

<br>

- 그러면 캐시가 없는 상황에서 서버에 동일한 요청을 또 보내면 어떻게 될까??

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005472-c2031cd5-6a0e-4d53-b971-66c600902097.PNG"></p>

- 처음과 동일하게, 총 1.1M의 크기를 다운받는다.

<br>

- **_캐시가 없기 때문에_**
  - 데이터가 변경되지 않아도, 계속 네트워크를 통해서 데이터를 다운받아야 한다.
  - 인터넷 네트워크는 비싸고 매우 느리다.
  - 브라우저 로딩 속도가 느리다.
  - 결국, 사용자는 느린 사용 경험을 겪는다.

<br>

## 1.2 캐시를 적용할 때

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005479-027ec26e-e0fb-426d-ad3b-df70e2e8a294.PNG"></p>

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005483-5bcff411-e444-4dfa-a8fd-7b9c298acf57.PNG"></p>

- 브라우저에 캐시 저장 공간을 사용한다.
- 클라이언트의 요청에 서버는 응답 메세지를 생성하며, 클라이언트에게 응답 메세지를 보내면서 결과를 캐시에 저장한다.

<br>

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005485-f165358b-54c4-4824-aa04-8bbf7b112923.PNG"></p>

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005488-55a8093f-0029-41a8-b1f8-2e6fc1e445d7.PNG"></p>

- 그러면 클라이언트의 두 번째 요청 시, 클라이언트는 서버에 요청을 바로 보내는 것이 아니라, 브라우저 캐시에서 캐시 유효 시간을 먼저 검증한다.
- 유효 시간이 일치하면 캐시에서 조회하여 원하는 데이터를 사용한다.

<br>

- **_캐시가 존재하기 때문에_**
  - 캐시 가능 시간 동안 네트워크를 사용하지 않아도 된다.
  - 비싼 네트워크 사용량을 줄일 수 있다.
  - 브라우저 로딩 속도가 매우 빠르다.
  - 사용자는 빠른 네트워크 경험을 할 수 있다.

<br>

## 1.3 캐시 시간 초과했을 때

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005491-6f8f87a1-a6f2-4168-b86f-e053f88e84c7.PNG"></p>

- 캐시 유효 시간을 검증 요청한다.

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005492-f81a1019-cc7c-4026-a50a-2ea7bacd1c4f.PNG"></p>

- 서버에서 다시 전송한다.

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159005493-bd112193-ab53-4712-b6d4-32f07328d608.PNG"></p>

- 응답 결과를 다시 캐시에 저장한다.

- 캐시 유효 시간이 초과하면, 서버를 통해 데이터를 다시 조회하고, 캐시를 갱신한다.
- 이때 다시 네트워크 다운로드가 발생한다.

---

# 2. 검증 헤더와 조건부 요청 1

> 검증 헤더: 캐시 데이터와 서버 데이터가 같은지 검증하는 데이터. ex) `Last-Modified` header  
> 조건부 요청 헤더: 검증 헤더로 조건에 따른 분기. ex) `if-modified-since:` header

- 캐시 유효 시간이 초과해서 서버에 다시 요청하면 다음 두 가지 상황이 나타난다.

  - 서버에서 기존 데이터를 변경하거나, 변경하지 않는 상황

- 캐시 만료 후에도 서버에서 데이터를 변경하지 않은 상황으로 가정하자.
  - 데이터를 전송하는 대신에 저장해 두었던 캐시를 재사용할 수 있다.
  - 단, 클라이언트의 데이터와 서버의 데이터가 같다는 사실을 확인할 수 있는 방법이 필요하다.

<br>

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159008398-034f1d5d-cffc-4d8a-8f11-4cdd0449491f.PNG"></p>

- 캐시 시간 초과로 서버에 재요청 시, `if-modified-since:` header를 메세지에 넣어 보낸다.
- 이 header가 `조건부 요청` header다.
- 이 header는 `캐시가 가지고 있는, 데이터 최종 수정일`을 말한다.

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159008401-dee35854-fd61-48e9-9e6a-39885baa01ca.PNG"></p>

- 서버에서 클라이언트가 보낸 요청과 서버의 해당 data의 데이터 최종 수정일을 비교한다.

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159008403-978b6dde-1bca-4aac-b0a1-b8d0781b844b.PNG"></p>

- 동일할 경우, `HTTP body`를 전송하지 않고 `HTTP header`만 전송한다.
- 데이터가 수정되지 않았기 때문에, `304 Not Modified` 상태이며,
- 검증헤더인 `Last-Modified` header를 추가한다.

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159008405-8acd9936-8493-4cd9-95e4-95a5cac2534c.PNG"></p>

- 그러면 응답 결과를 재사용하여, header data를 갱신한다.

- 정리

  - 캐시 유효 시간이 초과해도, 서버의 데이터가 갱신되지 않으면
    - `304 Not Modified` + `Header Meta data`만 응답하면 된다.
    - 여기서 `Header Meta data`란 검증헤더를 말한다.
    - 이 때, `HTTP Body`는 없어도 된다.
    - -> 클라이언트는 서버가 보낸 응답 헤더정보로 `캐시의 메타 정보`를 갱신한다.
    - -> 클라이언트는 캐시에 저장되어 있는 데이터를 재활용한다.
    - 결과적으로, 네트워크 다운로드가 발생하지만 용량이 적은 헤더 정보만 다운로드한다.

- 실제 웹 브라우저에서 다음 경로를 통해서 조건부 요청 헤더를 볼 수 있다.
  - 검사(F12) -> Network tab을 클릭 -> Status 란에 글씨가 연한 것이 Cache에서 불러온 것
  - 다시 이미지 더블클릭 -> 검사 -> Network -> 새로고침(F5) -> 이미지 클릭 -> headers tab -> Request header -> if-modified-since 보기

<p align="center"> <image src =https://user-images.githubusercontent.com/78094972/159013587-a2616b67-f5f5-4b97-baf1-f373d40eed2e.PNG"></p>

<br>

---

# 3. 검증 헤더와 조건부 요청 2

> 검증 헤더: `Last-Modified`, `ETag`  
> 조건부 요청 헤더: `If-Modified-Since: Last-Modified`, `If-None-Match:ETag`

- `Last-Modified` header 의 단점을 해결하는 header에 대해 알아보자.

- **검증 헤더**

  - `Last-Modified`, `ETag`

- **조건부 요청 헤더**

  - `If-Modified-Since: Last-Modified` 사용
  - `If-None-Match:ETag` 사용
  - 조건이 만족하면 200 OK
  - 조건이 만족하지 않으면 304 Not Modified

- 예시
  - `If-Modified-Since:` 이후에 데이터가 수정되었다면???
    - **데이터 미변경 예시**
      - 캐시: 2020년 11월 10일 10:00:00 vs 서버:2020년 11월 10일 10:00:00
      - **304 Not Modified**, 헤더 데이터만 전송(BODY 미포함)
      - 전송 용량 0.1M (헤더 0.1M, 바디 1.0M)
    - **데이터 변경 예시**
      - 캐시: 2020년 11월 10일 10:00:00 vs 서버:2020년 11월 10일 _11:00:00_
      - **200 OK**, 모든 데이터 전송(BODY 포함)
      - 전송 용량 1.1M (헤더 0.1M, 바디 1.0M)

<br>

---

# 4. 캐시와 조건부 요청 헤더

<br>

---

# 5. 프록시 캐시

<br>

---

# 6. 캐시 무효화

<br>

---

# Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
