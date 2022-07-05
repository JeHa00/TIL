# 0. Introduction

> 1. [Creating a project](#1-creating-a-project)
> 2. [The development server](#2-the-development-server)
> 3. [Creating the Polls app](#3-creating-the-polls-app)
> 4. [Write your first view](#4-write-your-first-view)

- django 공식 문서를 번역하는 작업을 통해 튜토리얼을 진행하여 이해해본다.

- 이번 tutorial을 통해서 기본적인 설문조사 애플리케이션을 만들 수 있다.
  - 이 애플리케이션은 다음 2가지로 구성된다.
    - 사람들이 설문조사를 보고 투표할 수 있는 'public site'
    - 설문조사를 더하고, 수정하고, 삭제하는 'admin site'

<br>

---

# 1. Creating a project

Django가 설치되어 있어야 하며, 해당 튜토리얼은 Django 4.0 과 Python 3.8 이후로 진행된다.

Django가 설치되어 있다면 버전을 확인할 수 있다.

```yml
python -m django --version
```

django가 설치되었다면 새로운 하나의 Django project를 세우기 위한 코드를 자동 생성할 필요가 있다.

    - 이 코드는 Django의 인스턴스에 대한 세팅들, DB 배치, 애플리케이션의 구체적인 설정들까지 포함한다.

코드를 저장하기 원하는 directory로 이동하여 아래 명령어를 입력한다.

```yml
# django-admin startproject <project명>

django-admin startproject mysite
```

이 명령어로 생성된 파일 구성은 다음과 같다.

```yml
> mysite/
>     manage.py
>     mysite/
>         __init__.py
>         settings.py
>         urls.py
>         asgi.py
>         wsgi.py
```

- `mysite/`: 여기서 외곽 root directory인 `mysite`는 프로젝트의 컨테이너인데, 이 컨테이너의 이름은 장고에서 중요하지 않으므로 원하는 방식대로 이름을 바꿔도 된다.

- `manage.py`: 다양한 방식으로 생성된 이 django 프로젝트와 상호작용할 수 있도록 하는 커맨드라인 유틸리티다.

  - [django-admin and manage.py.](https://docs.djangoproject.com/en/4.0/ref/django-admin/) 에서 이 파일에 대한 모든 세부사항들을 읽을 수 있다.

- `/mysite`: 내부 `mysite`는 생성된 프로젝트의 실제 파이썬 패키지다. 그래서 이 패키지 name을 사용하여 import하므로 수정하면 안된다.

- `mysite/__init__.py`: Python에게 이 directory는 Python package라고 말해주는 file이다.

  - [\_\_init\_\_.py 가 존재하는 이유](https://jeha00.github.io/post/python_basic/python_basic_16_package/#3-__init__py-%EA%B0%80-%EC%A1%B4%EC%9E%AC%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0)

- `mysite/settings.py`: 생성된 django project에 대한 settings와 configuration을 의미한다.

  - [Django settings](https://docs.djangoproject.com/en/4.0/topics/settings/)가 이 settings 파일이 어떻게 작동되는지 설명되어 있다.

- `mysite/urls.py`: 생성된 프로젝트의 URL declarations로서, django에 의해 생성된 사이트의 콘텐츠 표이다.

  - [URL dispatcher](https://docs.djangoproject.com/en/4.0/topics/http/urls/)에서 URLs에 대해 더 읽을 수 있다.

- `mysite/asgi.py` 와 `mysite/wsgi.py`: [How to deploy with ASGI](https://docs.djangoproject.com/en/4.0/howto/deployment/asgi/) 와 [How to deploy with WSGI](https://docs.djangoproject.com/en/4.0/howto/deployment/wsgi/)를 나중에 참고해보자.

<br>

---

# 2. The development server

django 프로젝트가 작동하는지 보이기 위해 외부 `mysite` directory로 이동하여 아래 명령어를 입력한다.

```yml
python manage.py runserver
```

그러면 아래와 같은 command line이 뜬다.

```yml
Performing system checks...

System check identified no issues (0 silenced).

You have unapplied migrations; your app may not work properly until they are applied.
Run 'python manage.py migrate' to apply them.

July 02, 2022 - 15:50:53
Django version 4.0, using settings 'mysite.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CONTROL-C.
```

❗ 기본적으로 `runserver` 명령어는 port 8000번에서 서버가 시작하도록 하는 명령어다. 포트 번호를 바꾸고 싶다면 `python manage.py runserver <원하는 포트 번호>` 를 입력한다. IP 주소까지 바꾸고 싶으면 `python manage.py runserver 0:8000` 로 입력한다. 여기서 `0`은 `0.0.0.0`의 약어다.

이처럼 django가 자동적으로 한 가지 app의 기본적인 directory 구조를 생성하기 때문에, 디렉토리들을 어떻게 생성할지보다 코드를 작성하는 것에 집중할 수 있게 된다.

<br>

---

# 3. Creating the Polls app

> **_Projects vs. apps_**  
>  app이란 어떤 것을 수행하는 웹 애플리케이션을 의미한다. 그리고, 하나의 프로젝트에는 여러 개의 app들이 포함되어 있다. 또한, 한 가지 app은 다양한 프로젝트에 존재할 수 있다.

app을 만들기 위해서 `manage.py`가 존재하는 directory 경로로 이동한다.

```yml
# 경로: /mysite

# project 생성은 python manage.py startproject <project 명>
$ python manage.py startapp polls

$ cd polls

# 경로: mysite/polls

$ dir
__init__.py  admin.py  apps.py  migrations  models.py  tests.py  views.py

$ cd migrations
$ dir
__init__.py
```

즉, 다음과 같은 구조를 가진다.

```yml
> polls/
>     __init__.py
>     admin.py
>     apps.py
>     migrations/
>         __init__.py
>     models.py
>     tests.py
>     views.py
```

<br>

---

# 4. Write your first view

`polls/views.py` 를 열어 아래 코드를 입력하자.

```yml
> from django.http import HttpResponse

> def index(request):
>     return HttpResponse("Hello, world. You're at the polls index.")
```

이 view를 호출하기 위해서는 이 뷰를 URL에 mapping 해야 하며, 이를 위해서 `URLconf`가 필요하다.

polls directory에 `URLconf`를 만들기 위해서 polls에 `urls.py`를 생성한 후, 밑에 코드를 입력하자.

```yml
# 경로: polls/urls.py

> from django.urls import path

> from . import views

# path에 `index`란 이름으로 views.index를 추가한다.

> urlpatterns = [
>     path('', views.index, name='index'),
> ]
```

> **_URLconf란 URL configuration의 약어로서 python module의 이름으로, URL path 표현식들을 파이썬 함수 즉, view에 연결시킨다. from [Django 공식문서 - URL dispatcher](https://docs.djangoproject.com/en/4.0/topics/http/urls/)_**

다음 단계는 app의 urls module을 project 폴더의 urls에 추가하여, 다른 URLconfs도 인식되도록 하는 단계다.  
mysite/urls.py 에 추가하기 위해서, `django.urls.include` 를 import하고 `urlpatterns`에 `include()`를 삽입한다.

```yml
# 경로:  mysite/urls.py

> from django.contrib import admin
> from django.urls import include, path

> urlpatterns = [
>     path('polls/', include('polls.urls')),
>     path('admin/', admin.site.urls),
> ]
```

❗ `include()` 는 다른 경로에 있는 URL patterns를 포함시키고자할 때, 반드시 사용해야한다. 이 function을 통해서 URLs를 손쉽게 추가하고, 사용할 수 있다.

`index` view를 URLconf로 엮었기 때문에, 이제 아래 명령어로 확인해보자.

만약 page를 확인할 수 없다고 뜬다면 `http://localhost:8000/polls/` 또는 `http://localhost:8000/.` 을 입력해보자.

```yml
> python manage.py runserver
```

<br>

---

## path() argument: 4가지

> **_path(route, view, kwargs, name): route와 view는 필수이고, kwargs와 name은 선택이다._**

- **route**

  - 한 개의 URL pattern을 포함하고 있는 문자열
  - 요청이 처리될 때, django는 urlpatterns의 첫 번째 패턴을 시작하여 각 patten과 요청된 URL을 비교하면서 매칭되는 것을 찾을 때까지 밑으로 내려간다.
  - Patterns 은 GET, POST 또는 도매인 이름을 찾지 않는다.
    - `https://www.example.com/myapp/` 또는 ` https://www.example.com/myapp/?page=3` 으로 요청이 들어온다면 URLconf는 `myapp/`을 찾을 것이다.

- view

  - 매칭되는 패턴을 장고가 찾을 대, `HttpRequest` 객체와 함께 지정된 view fuction을 첫 번째 인자로서 호출하고, route로부터 포착한 값을 키워드 인자로서 호출한다.

- kwags

  - tutorial에서 사용되지 않기 때문에 건너뛴다.

- name
  - URL을 django 어디에서든 name으로 참조하도록 한다.

<br>

---

# Reference

- [Django at a glance](https://docs.djangoproject.com/en/4.0/intro/overview/)
