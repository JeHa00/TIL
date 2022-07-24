# 0. Introduction

> 1. [What is CSS ?](#1-what-is-css)
> 2. [CSS를 추가하는 방식: 두 가지](#2-css를-추가하는-방식-두-가지)
> 3. [CSS를 입력하는 방식](#3-css를-입력하는-방식)


- HTML과 CSS에 대한 기본적인 내용을 정리해보는 글이다.  


- 크롬, 익스플로어, 파이어폭스 같은 브라우저들은 서버에서 보내는 HTML, CSS 그리고 javascript 파일을 분석하여 구현한 후, 클라이언트들에게 보여준다.

- 지난 'Study01.md'에서 이 3가지 중 HTML과 CSS가 무엇인지에 알아보았고, 더 나아가 HTML tag에 대해 알아보았다.  이번에는 CSS에 대해 더 알아보자.    

<br>

---
# 1. What is CSS ?


지난 study에서 css란 HTML의 tag를 꾸며주는 역할로, 클라이언트에게 보여주는 스타일을 담당한다는 걸 알았다.

그렇다면 CSS라는 명칭은 무슨 의미일까??

CSS란 'Cascading Style Sheets' 의 약어로, 여기서 Cascading이란 'one after the other' 순서대로 위에서부터 차례대로 흘러간다는 의미다.  

무슨 말인가?? 

**브라우저가 CSS를 읽은 방법이 위에서부터 아래로 읽는다는 것이다.** 

즉, 동일한 부분에 대해 다른 방식으로 CSS가 코드 위에서와 아래에서 스타일을 적용했다면, 맨 마지막에 있는 아래의 것이 최종적으로 적용된다는 것이다.    

> CSS란 Cascading Style Sheets의 약어로, 브라우저가 css를 읽는 방식을 나타내는데 브라우저가 CSS를 읽을 때 상대적으로 아래의 있는 css를 최종적으로 적용한다. 그리고, 이 CSS는 HTML tag를 꾸며주는 역할을 수행한다.    


❗ CSS의 속성들 또한 html의 tag처럼 매우 종류가 다양하므로 외우지 않고, 어떻게 동작하는지만 기억하자. 

<br>

---

# 2. CSS를 추가하는 방식: 두 가지 

> CSS를 추가하는 방식에는 inline 방식와 External 방식이 있다.

CSS를 추가하는 방식인 inline 방식와 external 방식에 대해 알아보자.  

첫 번째, 'Inline' 방식은 방식의 명칭대로 '코드 라인 내부에' CSS를 넣는 방식으로,  **_HTML 파일에 HTML 코드와 CSS 코드를 다 작성하는 방식_** 이다. 

두 번째, 'External' 방식은 첫 번째와 반대로 CSS와 HTML code를 분리하는 것으로, **_CSS를 별도의 파일로 만들어서 HTML 파일에서 불러와 사용하는 방식_** 이다.

**대부분 권장하는 방식은 'External' 방식이 있는데, 2가지 이유가 있다.**
- 첫 번째, 별도의 파일로 만들기 때문에 다른 더 많은 html page에서 사용할 수 있다.  
- 두 번째, 분리된 파일을 가지고 있는 방식이 보기 깔끔하다.  

## 2.1 Inline 방식

그러면 코드 라인 내부에 CSS를 입력한다면 html tag의 어디에 입력할까?  

html tag의 기본 템플렛이 다음과 같을 때, `<head> </head>` tag 안에 `<style> </style>`를 넣어서, 이 `<style> </style>` tag 안에 입력한다.  


    ```html
    <!DOCTYPE html>
    <html lang="ko">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <style>

                {css}

            </style>
        </head>
        <body>
            
        </body>
    </html>
    ```

<br>

## 2.2 External 방식 

외부에서 CSS 파일을 가져오는 방식은 `<style>` tag 사이에 입력하는 게 아닌 `<head> </head>` tag 사이에 `<link href ='css file 이름.css' rel = "stylesheet" >`를 입력한다.  

    ```html
    <!DOCTYPE html>
    <html lang="kr">
    <head>
        <meta charset="utf-8" />
        # External 방식  
        <link href="css file 이름.css" rel="stylesheet" />
        <style></style>
    </head>
    <body>
        <div class="sub">
        <div class="subsub"></div>
        <div class="subsub subsubelement"></div>
        <div class="subsub"></div>
        </div>
    </body>
    </html>
    ```


<br>

---

# 3. CSS를 입력하는 방식


### 3.1 css의 일반적인 입력 방식과 사용하면 안되는 문자

꾸미고자하는 HTML tag를 'selector'라고 한다.

그리고, 이 **'selector'를 입력한 후 중괄호를 열어 원하는 css 속성 이름과 속성값을 입력한다.**  

또한, css를 입력할 때는 띄어쓰기, 언더바(_). 슬래쉬(/)를 사용하면 안되며, css의 속성들은 각 줄이 세미클론(;)으로 끝나야 한다.  

    ```html
    # <h1> </h1> tag에 대해 CSS를 적용한다고 하자. 
    <html>
        <head>
            <style>
                h1 {
                    /* # 속성 이름: 속성값; */
                    color: yellowgreen; 
                    font-size: 50px;
                }
            </style>
        </head>
    </html>
    ```

<br>


### 3.2 tag의 id 이용하여 css 입력하기

> id라는 tag attribute를 사용하여 동일한 종류의 태그들이어도, 각각 지정하여 css를 적용할 수 있다.  

한 종류의 태그가 여러 개 있을 때, 이 태그들 각각에 서로 다른 css style을 적용하고 싶다면 어떻게 해야할까???

바로 아래 코드와 같이 `id` 속성을 사용한다. 

    ```html
    <!DOCTYPE html>
    <html lang="ko">
        <head>
            <meta charset="UTF-8">
            <title>Document</title>
        </head>
        <body>
            <div id = "red">
                <div id = "yellow">
                    <div id = "green">
                        <div id = "blue">
            
                        </div>
                    </div>
                </div>
            </div>
        </body>
    </html>
    ```

그러면 이 각 div에 서로 다른 css를 적용해보기 위해서, `id` 값을 css에 입력하는 방법을 밑에 코드로 확인해보자.  

    ```html
    <style>
        #id_name {
            css attibute name: attribute value;
        }
    </style>
    ```


어떻게 `id` 를 사용하여 css를 입력할지 알았으니, 각각의 `<div>` 에 적용해보자. 

    ```html
    <!DOCTYPE html>
    <html lang="ko">
        <head>
            <meta charset="UTF-8">
            <title>Document</title>
            <style>

                div {
                    height: 200px;
                    width: 200px; 
                }
                #red {
                    background-color: red  
                }
                #yellow {
                    background-color: yellow  
                }
                #green {
                    background-color: green
                }
                #blue {
                    background-color: blue  
                }

            </style>
        </head>
        <body>
            <div id = "red">
                <div id = "yellow">
                    <div id = "green">
                        <div id = "blue">
            
                        </div>
                    </div>
                </div>
            </div>
        </body>
    </html>
    ```

`<div>` tag에만 관련하여 브라우저에게 이와 같이 말하는 것이다. 
- `<div>` tag의 height와 width는 모두 200px로 해주고, 
- `<div>` tag 중 `id` 값이 'red'인 것의 배경색은 red color로, 
    - `id` 값이 'yellow'인 것의 배경색은 yellow color로,   
    - `id` 값이 'green'인 것의 배경색은 green color로,   
    - `id` 값이 'blue'인 것의 배경색은 blue color로 정한다. 



> **_기존 tag name을 사용하여 입력하는 방식에는 '공통사항'을 입력하고, id 값을 사용하여 각각의 tag에 id 값을 사용하여 구별해서 적용할 수 있다._** 


<br>

### 3.3 tag의 class를 이용하여 css 입력하기

> tag의 속성 class는 속성 id처럼 각 tag element들을 구별해서 가리킬 수도 있고, id와는 다르게 겹쳐서 가리킬 수 있는 attribute다.  

`class` 속성을 css에 입력하는 방법을 밑에 코드로 확인해보자. 

`id` 속성과는 달리 `#`아니라 `.`을 사용한다.    

    ```html
    <style>
        .class값 {
            css attibute name: attribute value;
        }
    </style>
    ```

그러면 'id' 속성으로 구현한 css 코드를 'class' 속성을 사용해서 구현해보겠다.  

    ```html
    <html>
        <head>
            <style>
                .btn {
                    height: 200px;
                    width: 200px; 
                }
                .red {
                    background-color: red;
                }
                .yellow {
                    background-color: yellow;
                }
                .green {
                    background-color: green;
                }
                .blue {
                    background-color: blue;
                }
            </style>
        </head>
        <body>
            <div class = "btn red">
                <div class = "btn yellow">
                    <div class = "btn green">
                        <div class = "btn blue">
            
                        </div>
                    </div>
                </div>
            </div>
        </body>
    </html>
    ```

> class는 id 속성과는 달리 속성값을 공백을 기준으로 여러 개를 입력할 수 있다. 


<br>

---
# Reference

- [노마드코더 - 코코아톡 클론 코딩](https://nomadcoders.co/kokoa-clone) 