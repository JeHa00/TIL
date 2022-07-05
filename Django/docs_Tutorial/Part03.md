# 0. Introduction

> 1. [Overview](#1-overview)
> 2. [Writing more views](#2-writing-more-views)
> 3. [Write views that actually do something](#3-write-views-that-actually-do-something)
> 4. [Raising a 404 error](#4-raising-a-404-error)
> 5. [Use the template system](#5-use-the-template-system)
> 6. [Removing hardcoded URLs in templates](#6-removing-hardcoded-urls-in-templates)
> 7. [Namespacing URL names](#7-namespacing-url-names)

- django 공식 문서를 번역하는 작업을 통해 튜토리얼을 진행하여 이해해본다.

- 이번 tutorial을 통해서 기본적인 설문조사 애플리케이션을 만들 수 있다.

  - 이 애플리케이션은 다음 2가지로 구성된다.
    - 사람들이 설문조사를 보고 투표할 수 있는 'public site'
    - 설문조사를 더하고, 수정하고, 삭제하는 'admin site'

- Tutorial Part 3에서는 public interface인 `view` 를 생성해보는 걸 목표로 둔다.

<br>

---

# 1. Overview

각 view는 함수로 구현되고, django는 요청된 URL에 따라서 view를 선택한다.

웹 페이지에서는 url은 `ME2/Sites/dirmod.htm?sid=&type=gen&mod=Core+Pages&gid=A6CD4967199A42D9B65B1B` 이처럼 알아보기 어렵다.

하지만, django는 URL의 일반적인 형태 `URL patterns`를 사용하여 `newsarchive/<year>/<month>/.`처럼 세련된 URL 주소를 사용하도록 한다.

django는 이 patterns를 사용하기 위해서 `URLconfs`를 사용하는데, 이 `URLconfs`란 URL patterns을 views와 mapping시킨 걸 말한다.

이번 tutorial을 통해 URLconfs의 기본적인 사용 방법을 배울 수 있다.

<br>

---

# 2. Writing more views

현재 `polls` application을 만들고 있으므로, `polls/views.py` 로 들어가 view를 추가하자.

```yml
# polls/views.py

def detail(request, question_id): return HttpResponse("You're looking at question {}".format(question_id))

def results(request, question_id):
  response = "You're looking at the results of quesion {}."
  return HttpResponse(response.format(question_id))

def vote(request, question_id): return HttpResponse("You're voting on question {}".format(question_id))
```

그리고 만든 view 갯수만큼 `mysite/urls.py`의 urlpatterns에 `path`를 추가해야한다.

```yml
# polls/urls.py

from django.urls import path
from . import views

urlpatterns = [
path('', views.index, name = 'index'),
path('<int:question_id>/', views.detail, name = 'detail'),
path('<int:question_id>/results', views.results, name = 'results'),
path('<int:question_id>/vote', views.vote, name = 'vote'),
]
```

- 예시를 통해서 django가 어떻게 작동되는지 알아보자.
  - ex) `/polls/34/.` page를 요청하는 상황
  - `mysite/settings.py`의 `ROOT_URLCONF` 변수에 저장된 경로로 먼저 이동한다.
    - 저장된 경로는 `mysite.urls`다.
  - `mysite.urls`에서 `urlpatterns` 변수를 찾은 후, 이 변수 안에 있는 '`path()`의 text'가 `polls/`에 해당되는 되는 걸 찾는다.
  - 그러면, `polls/`를 벗긴 후 남아있는 text `34/` 를 `polls/urls`에 보낸다.
    - `<int: question_id>/`에서 `int`는 converter이고, `question_id`는 pattern name이다. `:`은 이 두 가지를 구분하는 역할을 한다.
    - converter란 URL path에서 이 부분에 해당되는 pattern이 무엇인지 결정한다.

<br>

---

# 3. Write views that actually do something

- 각 view는 두 가지 중 하나를 할 책임이 있다.

  - 바로 요청된 page에 대한 콘텐츠를 포함하고 있는 `HttpsResponse` 객체를 반환하는 것 또는 `Http404` 같이 예외를 발생시키는 것 두 가지다.

- 그러면 새로운 view를 만들어보자.

```yml
# 경로: polls/views.py

from django.http import HttpResponse
from .models import Question

def index(request):
    latest_question_list = Question.objects.order_by('-pub_date')[:5]
    output = ', '.join([q.question_text for q in latest_question_list])
    return HttpResponse(output)
```

- 하지만, 이 방식의 경우 페이지의 디자인을 수정하려고 할 때, 파이썬 코드를 직접 수정해야한다.
- 그래서 view 사용하는 template을 만들어서 Python과 디자인을 분리시키고자, django의 `template system`을 사용해야 한다.
  - 첫 번째, `polls` directory 안에 `templates`라 불리는 directory를 생성하자.
  - 두 번째, `templates` 과 관련된 setting은 `mysite/settings.py`의 `TEMPLATES` 변수에 존재한다.
  - 세 번째, `polls/templates/polls/index.html` 경로로 폴더와 파일을 생성한다.
    - application 과 동일한 directory 생성한 후, 그 안에 template 파일을 생성한 이유는 다른 애플리케이션의 동일한 이름의 template 파일이 있을 경우 django는 이를 구분하지 못하기 때문에, 확실하게 구분하기 위해서 이와 같은 경로로 만들었다.

```yml
# 경로: polls/template/polls/index.html

> {% if latest_question_list %}
>     <ul>
>         {% for question in latest_question_list %}
>             <li><a href = "/polls/{{ question.id }}">{{ question.question_text }}</a></li>
>         {% endfor %}
>     </ul>
> {% else %}
>     <p>No polls are available.</p>
> {% endif %}
```

- views.py를 업데이트해보자.

```yml
> from django.http import HttpResponse
> from django.template import loader

> from .models import Question


> def index(request):
>     latest_question_list = Question.objects.order_by('-pub_date')[:5]
>     template = loader.get_template('polls/index.html')
>     context = {
>         'latest_question_list': latest_question_list,
>     }
>     return HttpResponse(template.render(context, request))
```

- 이 코드는 `polls/index.html` 이라 불리는 템플릿을 불러오고, 이를 `context`라는 변수에 전달한다.

<br>

---

# 4. Raising a 404 error

- 요청된 ID를 가진 질문이 존재하지 않는 경우, 404 예외가 발생하는 view를 만들어보겠다.

```yml
# 경로: polls/views.py

> from django.http import Http404
> from django.shortcut import render

> def detail(request, question_id):
>     try:
>         question = Question.objects.get(pk = question_id)
>     except Question.DoesNotExist:
>         raise Http404("Question does not exist")
>     return render(request, 'polls/detail.html', {'question' : question})
```

- quesiton_id에 해당되는 Question을 얻지 못하면 Http404를 일으킨다.

- `render` 은 요청 객체를 첫 번째 인수로, 템플릿 이름을 두 번째 인수로, 딕셔너리를 세 번째 인수로 받는다.

<br>

---

# 5. Use the template system

- html 인데도 python code를 사용하여 template을 만들 수 있다는 것까지 이해해보자.

```yml
# 경로: polls/templates/polls/detail.html
> <h1>{{ question.question_text }}</h1>
> <ul>
> {% for choice in question.choice_set.all %}
>   <li>{{ choice.choice_text }}</li>
> {% endfor %}
> </ul>
```

<br>

---

# 6. Removing hardcoded URLs in templates

- `polls/index.html` 에서 하드코딩된 부분(데이터의 소스 부분을 그대로 입력한 부분)을 제거해보자.

```yml
# 경로: polls/templates/polls/index.html

> <li><a href="/polls/{{ question.id }}/">{{ question.question_text }}</a></li>
```

- URL 입력 시 하드 코딩 방식이면 많은 템플렛에 사용된 URLs를 바꾸는 것이 힘든 일이다. 그러나, `polls/urls.py`에 `path()` function에 이름 인자를 사용하면 즉, {% url %} 템플릿을 사용하여 특정 URL path에 의존하지 않아도 된다. 그래서 아래와 같은 방식으로 수정한다.

```yml
> <li><a href = "{% url 'detail' %}">{{ question.question_text }}</li>
```

<br>

---

# 7. Namespacing URL names

- 하지만, 위와 같은 방식도 문제점이 있다.

- 지금 튜토리얼은 한 프로젝트에 한 가지 app이지만, 실제로는 훨씬 더 많은 app이 존재한다. 그런 경우 다른 app에 `detail` view가 존재한다면 어떻게 구분할 수 있을까???

- 해결책은 다음과 같다.

  - `application name/urls.py` 로 이동한다. ex) `polls/urls.py`로 이동한다.
  - `app_name` 변수를 생성하고, `polls`를 할당한다.

  ```yml
  app_name = 'polls'
  ```

  - 그리고 `polls/index.html`에서 `{% url 'detail' question.id %}` 부분을 `{% url 'polls: detail' question.id %}`로 수정하여 다른 application을 만들 때 django가 바로 인식할 수 있도록 namespacing을 사용한다.

<br>

---

# Reference

- [Django at a glance](https://docs.djangoproject.com/en/4.0/intro/overview/)

