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
> 예시: https://www.google.com:443/search?q=hello&hl=ko

<br>

**- scheme**

> **scheme:**//[userinfo@]host[:port][/path][?query][#fragment]  
> **https:**//www.google.com:443/search?q=hello&hl=ko

**- userinfo**

> scheme://**[userinfo@]**host[:port][/path][?query][#fragment]  
> https://www.google.com:443/search?q=hello&hl=ko

**- host**

> scheme://[userinfo@]**host**[:port][/path][?query][#fragment]
> https://**www.google.com**:443/search?q=hello&hl=ko

**- PORT**

> scheme://[userinfo@]host **[:port]**[/path][?query][#fragment]  
> https://www.google.com **:443**/search?q=hello&hl=ko

**- path**

> scheme://[userinfo@]host[:port]**[/path]**[?query][#fragment]  
> https://www.google.com:443 **/search**?q=hello&hl=ko

**- query**

> scheme://[userinfo@]host[:port][/path]**[?query]**[#fragment]  
> https://www.google.com:443/search?**q=hello&hl=ko**

**- fragment**

> scheme://[userinfo@]host[:port][/path][?query]**[#fragment]**  
> https://docs.spring.io/spring-boot/docs/current/reference/html/gettingstarted.html#**getting-started-introducing-spring-boot**

---

<br>

## 2. 웹 브라우저 요청 흐름

---

<br>

## Reference

- [모든 개발자를 위한 HTTP 웹 기본지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)
- [URI와 웹 브라우저 요청 흐름](https://catsbi.oopy.io/6befbf82-ce78-4fb9-bb87-805ec1048855)
