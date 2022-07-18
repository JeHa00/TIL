# 0. Introduction


- HTML과 CSS에 대한 기본적인 내용을 정리해보는 글이다.  


- 크롬, 익스플로어, 파이어폭스 같은 브라우저들은 서버에서 보내는 HTML, CSS 그리고 javascript 파일을 분석하여 구현한 후, 클라이언트들에게 보여준다.

- 그러면 이 3가지 중 HTML과 CSS가 무엇인지에 대해 알아보자.  



<br>

---

# 1. What is HTML and CSS ?

- 브라우저는 사람의 언어를 이해하지 못 하기 때문에, 만든 웹 사이트를 이해하기 위해서는 브라우저가 이해하는 언어로 전달해야 하기 때문에, 브라우저의 구성 요소인 3가지로 전달한다.    

- 그러면 웹 사이트의 구성 요소인 HTML, CSS, Javascript란 무엇일까??  

- 흔히들 이 3가지를 신체와 비교하여 다음과 같이 설명한다. 

    - HTML: 사람의 신체에 비유하자면 뼈대로서, Markup language 종류로 브라우저에게 `tag` 를 사용하여 이 컨텐츠의 종류가 무엇인지를 알려주는 역할  
        - 
    - CSS: 사람의 신체에 비유하자면 근육으로서, 이 콘텐츠를 디자인하는 역할  

    - Javascript: 사람의 신체에 비유하자면 brain으로서, web site가 동적으로 움직여서 interactivity 하기 위해 필요하다.  

❗ 브라우저는 오류가 있어도 오류를 알려주지 않는다.  

<br>

---

# 2. HTML

> 브라우저에게 'tag'를 사용하여 컨텐츠의 종류를 알려주는 마크업 언어의 종류  

<br>

## 2.1 HTML의 tag


### tag란?

- 'tag' 에는 아래 코드에서 보이듯이 괄호로 갇혀진 부분이 태그다.  
    - `self-closing tag` 와 그렇지 않은 태그 두 종류로 나눠진다.  
    - 그렇지 않은 태그는 여는 태그와 닫는 태그로 구성된다.  
    - 여는 태그와 닫는 태그 사이에 있는게 contents 내용이며, 태그로 이 컨텐츠의 종류를 결정한다.  

    ```yml
    # self-closing tag
    < input />

    # non self-closing tag
    # 태그 사이에 있는 Home - My website가 content  
    <title>Home - My website</title>
    ```

<br>

### tag 작성과 찾는 방법  

- HTML 'tag`는 매우 많은 종류가 있기 때문에, 암기하려고 하지않는다. 다만, 태그를 작성하는 방법과 찾는 방법을 알면 된다.  
    - 태그를 찾는 방법: 'html tag mdn'을 구글링하여 [mozilla](https://developer.mozilla.org/ko/docs/Web/HTML/Element) 에서 알아본다.   


<br>

### HTML tag의 기본 구성

- Visual Studio Code로 `doc`만 입력하면 바로 아래의 코드가 펼쳐진다.  

- html에는 반드시 따라야만 하는 틀이 있다.  

    - `<!DOCTYPE html>`로 html 문서는 시작한다. 

    - `<html>` tag 안에 `<head>`와 `<body>`로 크게 나눠진다.  
        - `<head>` : 브라우저에게 사이트의 정보를 알려주는 단계로 웹 사이트의 보이지 않는 부분인 환경을 설정한다.  
        - `<body>` : 브라우저 화면 상에 보여질 내용들인, 사용자가 볼 수 있는 content를 보여준다.  

    - `<html lang = "ko">`: 웹 사이트에서 사용되는 언어를 이 웹 사이트의 검색 엔진에게 알려주는 속성  


```yml
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <body>
        
    </body>
</html>
```

<br>

### semantic tag와 non-semantic tag

- tag의 다른 나눠지는 기준으로 `semantic tag`와 `non-semantic tag`가 있다.  
    - `tag` 자체 명칭에 의미가 있는 tag를 `semantic tag`라 하고, `tag` 자체 명칭에 의미가 없는 tag를 `non-semantic tag`라 한다.   

- `non-semantic tag`의 예로는 `<div>  </div>` 가 있다. division이란 단어에서 나온 것으로, 박스나 경계선이라 생각하면 된다.  
    - 기능은 가지고 있지만, 의미론적으로는 아무런 값이 없는 box다.  

- `semantic tag`의 예로는 `<header> </header>`가 있다.  `<head>` 와는 다른 것이며 `<body> </body>`에 포함된다. 이 예시를 읽었을 때 그 의미를 짐작할 수 있기 때문에 `semantic tag`로 분류된다.  
    - 코드를 짤 때는 최대한 `semantic tag`로만 코드를 짜는 거를 추천하는 이유가 코드만 보고도 무엇인지 빠르게 파악이 가능하기 때문이다.  

<br>

### tag의 attribute

- 각 'tag'는 attribute(속성)을 가지는데, 태그의 부가적인 정보를 말한다.    
    - attribute를 입력할 때는 `''` 이 아닌 `""`를 사용해야 한다.  
    - attribute에는 아무거나 작성핻 되지만, 각 tag에 정해진 attribute를 작성하지 않으면 브라우저는 인식하지 않는다.  
    - 자주 사용되는 tag는 암기하는 것을 추천한다.  
    - tag에 사용되는 attribute 중 `id`는 어느 태그에서든 사용할 수 있다.  

<br>

---

# Reference

- []()