# Intro

> 1. [2xx (Successful)](#1-2xx-successful)
> 2. [3xx (Redirection)](#2-3xx-redirection)
> 3. [4xx (Client Error)](#3-4xx-client-error)
> 4. [5xx (Server Error)](#4-5xx-serve-error)

<br>

- HTTP 학습내용의 기본 출처: 김영한님의 [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- 강의를 듣고 정리한 내용과 모르는 부분에 대한 추가 내용을 합쳐 올린다.
- 이 강의는 HTTP에 대한 웹 기본지식을 설명하는 강의이므로, 내용이 간략할 수 있다.

<br>

- 학습 이유: 프레임워크를 사용하여 웹 개발을 배우기 전에, HTTP에 대해 기본적인 지식을 알고자 HTTP 공부를 시작한다. 이 강의에 대해 공부 후, 네트워크 전반에 대해 공부한다.

### 이번 시간에는 상태 코드에 대해 알아본다.

> 상태 코드란??  
> 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능

### 상태 코드의 종류에는 다음과 같다.

- 1xx (Informational): 요청이 수신되어 처리중
- 2xx (Successful): 요청 정상 처리
- 3xx (Redirection): 요청을 완료하려면 추가 행동이 필요
- 4xx (Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- 5xx (Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함

<br>

- 만약 모르는 상태 코드가 나타나면??

  - 클라이언트가 인식할 수 없는 상태코드를 서버가 반환하면??
  - **클라이언트는 상위 상태 코드로 해석해서 처리한다.**
  - 미래에 새로운 상태 코드가 추가되어도 클라이언트를 변경하지 않아도 된다.
  - 예)
    - 299 ??? -> 2xx (Successful)
    - 451 ??? -> 4xx (Client Error)
    - 599 ??? -> 5xx (Server Error)

- 그러면 각 상태 코드에 대해 알아보자.
  (1xx는 거의 사용하지 않으므로 생략한다. )

<br>

---

# 1. 2xx (Successful)

> 클라이언트의 요청을 성공적으로 처리한 상태

<br>

- ### 200대 state code 종류

  - 200 OK
  - 201 Created
  - 202 Accepted
  - 204 No Content

### 200 OK

> 요청이 성공한 상태

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/158292322-01527b4e-2ca0-4e5e-933d-17a6e1b4e50a.PNG"/></p>

- 200번대 모든 코드들을 사용하지 않고, 200과 201만 사용하는 경우가 많다.
- 그래서, 팀 내에서 코드를 어디까지 사용할지 결정한다.

<br>

### 201 Created

> 요청이 성공하여 새로운 리소스가 생성된 상태

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/158292339-12e77ed7-ebe3-454e-970b-d343bc65c8b9.PNG"/></p>

<br>

### 202 Accepted

> 요청이 접수되었으나 처리가 완료되지 않은 상태

- 배치 처리(batch processing) 같은 곳에서 사용한다.
- 예) 요청 접수 후, 1시간 뒤에 배치 프로세스가 요청을 처리한다.

> 배치 처리(batch processing)란??  
> 일괄 처리라 하며, 데이터를 일괄적으로 모아서 처리하는 작업을 말한다.

<br>

### 204 No Content

> 서버가 요청을 성공적으로 수행했으나, 응답 페이로드 본문에 보낼 데이터가 없는 상태

- 예) 웹 문서 편집기에서 save 버튼
- save 버튼의 겨로가로 아무 내용이 없어도 된다.
- save 버튼을 눌러도 같은 화면을 유지해야 한다.
- 결과 내용이 없어도, 204 메시지(2xx)만으로 성공을 인식할 수 있다.

<br>

---

# 2. 3xx (Redirection)

> 요청을 완료하기 위해 유저 에이전트(웹 브라우저)의 추가 조치가 필요한 상태

### 3xx status code 종류

- 300 Multiple Choices
- 301 Moved Permanently
- 302 Found
- 303 See Other
- 304 Not Modified
- 307 Temporary Redirect
- 308 Permanent Redirect

- 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동한다. 이를 `리다이렉트`라 한다.

- /event path를 더 사용하지 않기 때문에, /new-event로 바꼈다.

<br>

### 리다이렉션의 종류

- 영구 리다이렉션:

  - 특정 리소스의 URI가 _영구적으로_ 이동
  - 예) /members -> /users
  - 예) /event -> /new-event

- 일시 리다이렉션:

  - _일시적인_ 변경
  - 주문 완료 후 주문 내역 화면으로 이동한다.
  - 자주 쓰이는 패턴: PRG (Post/Redirect/Get)

- 특수 리다이렉션:
  - 결과 대신 캐시를 사용한다.
  - 클라이언트가 캐시 사용 시간을 확인하기 위해 서버에게 보내어 서버가 캐시 생성일자로 응답하는 것을 말한다.

<br>

### 영구 리다이렉션 (301, 308)

- 리소스의 URI가 영구적으로 이동
- 원래의 URL를 사용X, 검색 엔진 등에서도 변경 인지

<br>

- 301 Moved Permanently

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/158309427-31702d0b-1c21-4110-9ef2-2b36903152ee.PNG"/></p>

- 리다이렉트 시, 요청 메서드가 POST에서 **GET으로 변하고**, 본문이 제거될 수 있음(MAY)
- 본문이 제거될 수 있다는 문제점을 308이 해결할 수 있으나, 대부분 301을 사용한다.
- 왜냐하면 경로가 `/new-event`로 바뀌면 내부적으로 전달하는 데이터가 다 바뀌는 것이기 때문에, POST로 와도 GET으로 되돌리는 게 맞다.

- 308 Permanent Redirect

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/158309420-34739160-055b-4f71-b94c-79370c7e6f32.PNG"/></p>

- 301과 기능은 같음
- 리다이렉트시 요청 메서드와 본문 유지
  (처음 POST를 보내면 리다이렉트도 **POST 유지**)

- 스펙에 나와 있어 설명한다. 실무에서는 거의 이렇게 사용하지 않는다.

<br>

### 일시적인 리다이렉션 (302, 307, 303)

- 리소스의 URI가 일시적으로 변경
- 따라서 검색 엔진 등에서 URL을 변경하면 안됨
- **302 Found**
  - **리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음(MAY)**
- **307 Temporary Redirect**
  - 302와 기능은 같음
  - **리다이렉트시 요청 메서드와 본문 유지(요청 메서드를 변경하면 안된다. MUST NOT)**
- **303 See Other**
  - 302와 기능은 같음
  - **리다이렉트시 요청 메서드가 GET으로 변경**

<br>

- 그러면 예를 들어서 설명해보자.

- POST로 주문 후, 웹 브라우저를 새로고침하면??
  - 새로 고침은 다시 요청하는 것이기 때문에, 중복 주문이 될 수 있다.
- 이를 해결하기 위해 자주 사용하는 패턴이 `PRG`다.
  - PRG: POST/Redirect/Get

<br>

- PRG 사용 전

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/158310955-13fedf73-f6f3-4ba2-94f0-2ef7cd012fa3.PNG"/></p>

<br>

- PRG 사용 후

<p align="center"> <image src ="https://user-images.githubusercontent.com/78094972/158310948-77dbaf09-aa61-4871-8a28-28f2a5cffb31.PNG"/></p>

- 302 또는 303 사용한다.
- POST로 주문 후에도 새로 고침으로 인한 중복 주문을 방지한다.
- POST로 주문 후에도 주문 결과 화면을 GET method로 리다이렉트하여, 새로 고침해도 결과 화면을 GET 으로 조회한다.
- 즉, 중복 주문 대신에 결과 화면만 GET으로 다시 요청한다.
- PRG를 사용해서 경고창이 안뜨고, 서버 입장에서는 오류가 줄어든다.

<br>

- [Summary]

  - 302 Found -> GET으로 변할 수 있다.
  - 307 Temporary Redirect -> 메서드가 변하면 안된다.
  - 303 See Other -> 메서드가 GET으로 변경한다.

- [History]

  - 처음 302 스펙의 의도는 HTTP 메서드를 유지하는 것이다.
  - 그런데 웹 브라우저들이 대부분 GET으로 바꿨다.(일부는 다르게 동작)
  - 그래서 모호한 302를 대신하는 명확한 307, 303이 등장했다.(301 대응으로 308도 등장)

- [Now]
  - 307, 303을 권장하지만 현실적으로 이미 많은 애플리케이션 라이브러리들이 302를 기본값으로 사용
  - 자동 리다이렉션시에 GET으로 변해도 되면 그냥 302를 사용해도 큰 문제 없음

<br>

### 기타 리다이렉션 (300, 304)

- 300 Multiple Choices: 안쓴다.
- **_304 Not Modified_**
  - 매우 많이 사용한다.
  - 캐시를 목적으로 사용
  - 클라이언트에게 리소스가 수정되지 않았음을 알려준다. 따라서 클라이언트는 로컬PC에 저장된 캐시를 재사용한다. (캐시로 리다이렉트 한다.)
  - 304 응답은 응답에 메시지 바디를 포함하면 안된다. (로컬 캐시를 사용해야 하므로)
  - 조건부 GET, HEAD 요청시 사용

<br>

---

# 3. 4xx (Client Error)

> 오류의 원인이 '클라이언트'에게 있어서, 발생하는 클라이언트 오류

- 클라이언트의 요청에 잘못된 문법 등으로 서버가 요청을 수행할 수 없다.
- **_클라이언트가 이미 잘못된 요청, 데이터를 보내고 있기 때문에, 똑같은 재시도는 실패한다._**

<br>

### 3.1 400 Bad Request

> 클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없다.

- 요청 구문, 메시지 등등으로 인한 오류가 발생한 상태다.
- 클라이언트는 요청 내용을 다시 검토하고, 보내야 한다.
- 예) 요청 파라미터가 잘못되거나, API 스펙이 맞지 않을 때

<br>

### 3.2 401 Unauthorized

> 클라이언트가 해당 리소스에 대한 인증이 필요하다.

- 인증(Authentication) 되지 않은 상태를 말한다.
  - 401 오류 발생시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명한다.

<br>

> [참고]
>
> - 인증(Authentication): 본인이 누구인지 확인, (로그인)
> - 인가(Authorization): 권한부여 (ADMIN 권한처럼 특정 리소스에 접근할 수 있는 권한, 인증이 있어야 인가가 있음)
> - 오류 메시지가 Unauthorized 이지만 인증 되지 않음 (이름이 아쉬움)

<br>

### 3.3 403 Forbidden

> 서버가 요청을 이해했지만, 승인을 거부함

- 주로 인증 자격 증명은 있지만, 접근 권한이 부충분한 경우
  - 예) Admin 등급이 아닌 사용자가 로그인 했지만, admin 등급의 resource에 접근하는 경우

<br>

### 3.4 404 Not Found

> 요청 리소스를 찾을 수 없다.

- 요청 리소스가 서버에 없다.
- 클라이언트가 권한이 부족한 리소스에 접근할 때
- 해당 리소스를 숨기고 싶을 때

<br>

---

# 4. 5xx (Serve Error)

> 오류의 원인이 '서버'에게 있어서, 발생하는 서버 오류

- Client Error와 달리 서버에 문제가 있기 때문에, 재시도하면 성공할 수 있다.
- 500대 에러는 서버에 심각한 문제가 터졌을 때를 의미한다.
- 고객의 잔고가 부족할 경우, 20세 이상만 이용 가능한데 15세가 들어왔을 경우 등등은 500번대 에러가 아니다.

<br>

### 4.1 500 Internal Server Error

> 서버 문제로 오류 발생, 애매하면 500 오류

- 서버 내부 문제로 오류가 발생한 상황
- 애매하면 500 오류를 사용

<br>

### 4.2 503 Service Unavailable

> 서비스 이용 불가

- 서버가 일시적인 과부하 또는 예정된 작업으로 잠시 요청을 처리할 수 없는 상황
- Retry-After 헤더 필드로 얼마 뒤에 복구되는지 보낼 수 있다.
- **_대부분의 서비스 에러는 예측 불가하기 때문에 500번이다._**

<br>

---

# Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [HTTP 메서드 활용](https://catsbi.oopy.io/e43388ef-bc41-4f44-986c-010c12b596dd)
