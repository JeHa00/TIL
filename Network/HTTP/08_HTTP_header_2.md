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

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005472-c2031cd5-6a0e-4d53-b971-66c600902097.PNG"></p>

- 처음과 동일하게, 총 1.1M의 크기를 다운받는다.

<br>

- **_캐시가 없기 때문에_**
  - 데이터가 변경되지 않아도, 계속 네트워크를 통해서 데이터를 다운받아야 한다.
  - 인터넷 네트워크는 비싸고 매우 느리다.
  - 브라우저 로딩 속도가 느리다.
  - 결국, 사용자는 느린 사용 경험을 겪는다.

<br>

## 1.2 캐시를 적용할 때

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005479-027ec26e-e0fb-426d-ad3b-df70e2e8a294.PNG"></p>

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005483-5bcff411-e444-4dfa-a8fd-7b9c298acf57.PNG"></p>

- 브라우저에 캐시 저장 공간을 사용한다.
- 클라이언트의 요청에 서버는 응답 메세지를 생성하며, 클라이언트에게 응답 메세지를 보내면서 결과를 캐시에 저장한다.

<br>

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005485-f165358b-54c4-4824-aa04-8bbf7b112923.PNG"></p>

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005488-55a8093f-0029-41a8-b1f8-2e6fc1e445d7.PNG"></p>

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

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005491-6f8f87a1-a6f2-4168-b86f-e053f88e84c7.PNG"></p>

- 캐시 유효 시간을 검증 요청한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005492-f81a1019-cc7c-4026-a50a-2ea7bacd1c4f.PNG"></p>

- 서버에서 다시 전송한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159005493-bd112193-ab53-4712-b6d4-32f07328d608.PNG"></p>

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

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159008398-034f1d5d-cffc-4d8a-8f11-4cdd0449491f.PNG"></p>

- 캐시 시간 초과로 서버에 재요청 시, `if-modified-since:` header를 메세지에 넣어 보낸다.
- 이 header가 `조건부 요청` header다.
- 이 header는 `캐시가 가지고 있는, 데이터 최종 수정일`을 말한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159008401-dee35854-fd61-48e9-9e6a-39885baa01ca.PNG"></p>

- 서버에서 클라이언트가 보낸 요청과 서버의 해당 data의 데이터 최종 수정일을 비교한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159008403-978b6dde-1bca-4aac-b0a1-b8d0781b844b.PNG"></p>

- 동일할 경우, `HTTP body`를 전송하지 않고 `HTTP header`만 전송한다.
- 데이터가 수정되지 않았기 때문에, `304 Not Modified` 상태이며,
- 검증헤더인 `Last-Modified` header를 추가한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159008405-8acd9936-8493-4cd9-95e4-95a5cac2534c.PNG"></p>

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

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159013587-a2616b67-f5f5-4b97-baf1-f373d40eed2e.PNG"></p>

<br>

---

# 3. 검증 헤더와 조건부 요청 2

> 검증 헤더: `Last-Modified`, `ETag`  
> 조건부 요청 헤더: `If-Modified-Since:`, `Last-Modified`, `If-None-Match:ETag`

- `Last-Modified` header 의 단점을 해결하는 header에 대해 알아보자.

- **검증 헤더 (Validator)**

  - `Last-Modified`, `ETag`

- **조건부 요청 헤더**

  - `If-Match`, `If-None-Match:ETag 값` 사용
  - `If-Modified-Since`, `If-Unmodified-Since: Last-Modified 값` 사용
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

## 3.1 `If-Modified-Since:`, `Last-Modified` 단점

- 1초 미만(0.x초) 단위로 캐시 조정이 불가능하다.
- 날짜 기반의 로직을 사용한다.
- 그래서, 데이터를 수정해서 날짜가 다르지만, 같은 데이터를 수정해서 데이터 결과가 똑같은 경우에도 다시 다운받아야 한다.
- 위의 문제점으로 서버에서 별도의 캐시 로직을 관리하고 싶은 경우, 다음에 소개되는 Header를 사용한다.
  - ex) 스페이스나 주석처럼 크게 영향이 없는 변경에서 캐시를 유지하고 싶은 경우

<br>

## 3.2 해결책: `ETag`, `IF-None-Match`

> 날짜 기반의 date가 기준이 아닌 데이터의 버전 이름이 기준

- `ETag`: Entity Tag
- 캐시용 데이터에 임의의 고유한 버전 이름을 달아둔다.
  - ex) ETag: "v1.0", ETag: `a2jiodwjekij3`
- 데이터가 변경되면 이 이름을 바꾸어서 변경한다. (Hash를 다시 생성한다.)
  - ex) ETag: 'aaaaa', -> ETag: 'bbbbbb'
- 진짜 단순하게 ETag만 보내서 같으면 유지, 다르면 다시 받기!!

<br>

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159034239-a629868d-4ac9-4874-88f1-81a18629e351.PNG"></p>

- `ETag: aaaaaaaaaa` header 로 서버가 응답했다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159034243-6e9994d3-00f8-4535-95a8-f6f0a13c1e20.PNG"></p>

- 그리고 위 Tag 로 응답 결과를 캐시에 저장했다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159034244-070ba99f-8ad9-442e-b0dc-91a960fb21e5.PNG"></p>

- 두 번째 요청을 했지만, 캐시 시간이 초과된 상황이다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159034248-cfb46db5-781e-4b24-b498-d55234bae455.PNG"></p>

- 서버에 재요청을 보낼 때, 캐시가 가지고 있는 `ETag`의 내용을 `If-None-Match:` header로, 요청 message의 header에 함께 보낸다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159034250-80cef4bf-4490-4b66-9a52-6212626d872c.PNG"></p>

- 서버에서 응답하는 `ETag`의 내용과 `If-None-Match:`의 내용을 비교한다.
- 동일하다는 건, 아직 데이터는 수정되지 않았음을 의미한다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159034254-235f224d-524f-48f4-b18e-d06a8951f8fc.PNG"></p>

- 데이터가 수정되지 않았기 때문에, `HTTP 헤더`만 보낸다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159034259-4649eb43-e181-4f33-9ac2-cf9bf8689444.PNG"></p>

- 응답 결과를 재사용하여, 캐쉬 데이터의 헤더 데이터를 갱신한다.

<br>

---

# 4. 캐시와 조건부 요청 헤더

- 캐쉬 제어 헤더의 종류에는 3가지가 있다.
  - `Cache-Control`: 캐시 제어
  - `Pragma`: 캐시 제어(하위 호환)
  - `Expires`: 캐시 유효 기간(하위 호환)

<br>

## 4.1 Cache-Control

> 캐시 지시어(directives)

- `Cache-Control: max-age`

  - 초 단위로, 캐시 유효 시간을 알려준다.

- `Cache-Control: no-cache`

  - 데이터는 캐시해도 되지만, 항상 원(origin) 서버에 검증하고 사용해야 한다.
    - Origin 서버라 하는 이유는 중간에 여러 proxy 서버가 있기 때문이다.

- `Cache-Contrl: no-store`

  - 데이터에 민감한 정보가 있으므로 저장하면 안된다.
  - (메모리에서 사용하고 최대한 빨리 삭제)

<br>

## 4.2 Pragma

> 캐시 제어(하위 호환)

- `Pragma:no-cache`
- HTTP 1.0 의 하위 호환
  - 하위 호환이라 지금은 대부분 사용하지 않는다.
  - 하지만, 구글에서는 여러 국가를 지원하기 때문에 사용하고 있다.

<br>

## 4.3 Expires

> 캐시 만료일 지정(하위 호환)  
> expires: Mon, 01 Jan 1990 00:00:00 GMT

- 캐시 만료일을 정확한 날짜로 지정한다.
- HTTP 1.0부터 사용한다.
- 지금은 더 유연한 방법인 `Cache-Control:max-age` 를 권장한다.
- `Cache-Control:max-age`와 함께 사용하면 `Expires`는 무시된다.

<br>

## 4.4 검증 헤더와 조건부 요청 헤더

- **검증 헤더 (Validator)**

  - ETag: "v1.0", ETag: "asid93jkrh2l"
  - Last-Modified: Thu, 04 Jun 2020 07:19:24 GMT

- **조건부 요청 헤더**

  - If-Match, If-None-Match: ETag 값 사용
  - If-Modified-Since, If-Unmodified-Since: Last-Modified 값 사용

<br>

---

# 5. 프록시 캐시

> Cache-Control  
> 캐시 지시어(directives) - 기타

- `Cache-Control: public`

  - 응답이 public 캐시에 저장되어도 된다.

- `Cache-Control: private`

  - 응답이 해당 사용자만을 위한 것이다.
  - private 캐시에 저장해야 한다. (기본값)

- `Cache-Control: s-maxage`

- 프록시 캐시에만 적용되는 max-age

- `Age: 60`(HTTP 헤더)

  - Origin 서버에서 응답 후, proxy 캐시 내에 머문 시간(단위:초)
  - 우리가 데이터를 받아야 알 수 있다.

<br>

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159041113-bd3c9900-b4f9-49fe-a6cd-b54f38cd722b.PNG"></p>

- 원 서버와 클라이언트 사이에 중간 서버 없이, Origin (원) 서버에 직접 접근하는 경우
- 데이터를 가져오는데 비교적 긴 시간이 걸린다.

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159041121-5909e892-5a06-4fcf-bb05-4fd820ac638f.PNG"></p>

- 하지만 이렇게 proxy 캐시 서버를 도입하면 한국에서 보다 빨리 데이터를 받을 수 있다.

<br>

---

# 6. 캐시 무효화

> 확실한 캐시 무효화 응답  
> `Cache-Control: no-cache`, `Cache-Control: no-store`, `Cache-Control: must-revalidate`  
> `Pragma: no-cache` : HTTP 1.0 하위호환

- 캐시 무효화가 필요한 이유:

  - 캐쉬를 적용하려고 하지 않아도, 웹 브라우저들이 임의로 적용한다.
  - 그래서, 이 페이지는 캐쉬를 넣으면 안된다면, 위 헤더들을 반드시 넣어야 한다.

- **Cache-Control directives(캐시 지시어) - 확실한 캐시 무효화**

  - `Cache-Control: no-cache`

    - 데이터는 캐시해도 되지만, 항상 원 서버에 검증하고 사용해야 한다.
    - Header 이름 혼동 주의!

  - `Cache-Control: no-store`

    - 데이터에 민감한 정보가 있으므로 저장하면 안된다.  
      (메모리에서 사용하고 최대한 빨리 삭제)

  - `Cache-Control: must-revalidate`

    - _캐시 만료 후, 최초 조회시_ 원 서버에 검증해야 한다.
    - _원 서버 접근 실패시 반드시 오류가 발생해야한다. - 504(Gateway Timeout)_ => `no-cache`와의 차이점
    - must-revalidate는 캐시 유효 시간이라면 캐시를 사용함

  - `Pragma: no-cache`
    - HTTP 1.0 하위 호환

<br>

## no-cache vs must-revalidate

- `no-cache`의 기본 동작 (데이터가 수정되지 않은 상황)

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159101257-73033a97-cefe-4e54-b410-1e95235151ad.PNG"></p>

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159101258-53f6d5be-2665-4d37-8a2d-dcf7a408f449.PNG"></p>

<br>

- `no-cache`를 사용한 상황에서 프록시 캐시와 원 서버 간 네트워크 단절이 순간 발생한 경우

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159101260-d3e0d59b-259a-4771-9da2-1f51aad9fe55.PNG"></p>

- `must=revalidate` 를 사용한 상황에서 프록시 캐시와 원 서버 간 네트워크 단절이 순간 발생한 경우

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/159101256-67c91b6b-3d64-4047-b629-f7f80f334958.PNG"></p>

<br>

- 정리

  - 프록시 캐시와 원 서버 간 네트워크 단절이 순간 발생한 경우
  - `no-cache`

    - 원 서버에 접근할 수 없는 경우, 서버 설정에 따라서 프록시 서버에서 응답할 수 있다.
    - 응답한 data가 오류보다 오래된 데이터라도 보여준다.
    - _단, 오류인지는 알려주지 않는다._

  - `must=revalidate`
    - 원 서버에 접근할 수 없는 경우, **항상 오류**가 발생해야 한다.
    - _504 Gateway Timeout_ 으로 응답한다.
    - _오류인지 알려준다_

<br>

---

# Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
