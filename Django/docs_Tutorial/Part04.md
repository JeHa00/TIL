# 0. Introduction

> 1. [Write a minimal form](#1-write-a-minimal-form)
> 2. [use generic views: Less code is better](#2-use-generic-views-less-code-is-better)

- django 공식 문서를 번역하는 작업을 통해 튜토리얼을 진행하여 이해해본다.

- 이번 tutorial을 통해서 기본적인 설문조사 애플리케이션을 만들 수 있다.

  - 이 애플리케이션은 다음 2가지로 구성된다.
    - 사람들이 설문조사를 보고 투표할 수 있는 'public site'
    - 설문조사를 더하고, 수정하고, 삭제하는 'admin site'

-

<br>

---

# 1. Write a minimal form

- Par03에서 만들었던 detail template을 업데이트해보자.
  - 업데이트할 detail template에는 HTML `form` 요소가 포함된다.

```yml
> <form action="{% url 'polls:vote' question.id %}" method="post">
>   {% csrf_token %}
>   <fieldset>
>       <legend><h1>{{ question.question_text }}</h1></legend>
>       {% if error_message %}<p><strong>{{ error_message }}</strong></p>{% endif %}
>       {% for choice in question.choice_set.all %}
>       <input
>            type="radio"
>            name="choice"
>            id="choice{{ forloop.counter }}"
>            value="{{ choice.id }}">
>       <label for="choice{{ forloop.counter }}">{{ choice.choice_text }}</label>
>       {% endfor %}
>   </fieldset>
>   <input type="submit" value="Vote">
> </form>
```

- 질문을 선택하기 위한 라디오 버튼을 보여준다. 각 라디오 버튼의 값은 question choice의 ID 값이다. 누군가 라디오 버튼의 하나를 클릭하여 이 form을 전송할 때, POST data choice = # 을 보낸다. 여기서 # 은 선택된 ID다. 이것인 HTML 형태의 기본 개념이다.

- 이 form의 action을 `{% url 'polls:vote' question.id %}` 으로 정하고, method는 `post`로 정했다. 'post'는 서버 쪽 데이터를 변경하려는 form을 생성하려고 할 때 사용해야 하는 method다. 이는 django에만 해당되는 게 아닌 일반적으로 웹 개발의 좋은 예시에서 찾을 수 있다.

- method 'POST'를 사용하는 만큼 _'Cross Site Request Forgeries'_ 에 대해 고려해야 한다.

  - CSRF(Cross Site Request Forgeries)란 사용자가 자신의 의지와는 무관하게 다른 사용자가 의도한 행위를 특정 웹사이트에 요청하게 하는 공격으로, 개인정보 유출이 일어날 수 있다.

- 하지만, django에는 이를 도와주는 보안 시스템이 있는데, `{% csrf_token %}` template tag를 사용하는 것이다. 내부 URLs를 대상으로 하는 모든 POST method는 이 tag를 사용해야 한다.

- 아래 코드에는 이번 tutorial에서 아직 다루지 않는 것들을 포함시킨다.

  ```yml
  # 경로: polls/views.py

  >  def vote(request, question_id):
  >     question = get_object_or_404(Question, pk = question_id)
  >     try:
  >         selected_choice = question.choice_set.get(pk = request.POST['choice'])
  >     except (KeyError, Choice.DoesNotExist):
  >         return render(request, 'polls/detail.html', {'question': question, 'error_message': "You didn't select a choice.", })
  >     else:
  >         selected_choice.votes += 1
  >         selected_choice.save()
  >     return HttpResponseRedirect(reverse('polls:results', args = (question.id, )))
  ```

  - `request.POST`: 이 `request.POST`의 값은 언제나 string이다. `request.post['choice']` 는 선택된 choice의 ID 값을 문자열로서 반환한다.
  - 하지만 선택된 choice가 존재하지 않는다면 `KeyError`를 발생시킨다.
  - 선택된 choice의 count가 증가된 후, 일반적인 `HttpResponse`가 아닌 `HttpResponseRedirect`를 반환한다. `HttpResponseRedirect` 는 user가 원래 정한 url이 아닌 다른 url로 가도록 끌어준다.
  - POST data를 성공적으로 처리한 후, 항상 `HttpResponseRedirect` 를 반환해야 한다.
    `HttpResponseRedirect`의 인자로 `reverse`를 사용하고 있는데 이 함수는 view function에서 hardcoding을 방지해준다.

- 이 vote view 코드의 경우 **_race condition_**에 대한 해결책이 나와있지 않는다. 둘 이상의 유저가 동시에 같은 quesition을 클릭했을 경우, +2가 되어야 하지만 +1이 증가될 수도 있다. 이 문제를 해결하는 걸 학습하고 싶으면 [Avoiding race conditions using F()](https://docs.djangoproject.com/en/4.0/ref/models/expressions/#avoiding-race-conditions-using-f) 를 읽어보자.

- question에 누군가 vote를 한 후, `vote()` view가 결과 page로 보여지도록 설계한다.

  ```yml
  def results(request, question_id):
    question_id = get_object_or_404(Question, pk = question_id)
    return render(request, 'polls/resul
  ```

- `polls/templates/polls/results.html` 를 만들어보자.

  ```yml
  <h1>{{ question.question_text }}</h1>
  <ul>
  {% for choice in question.choice_set.all %}
  <li>{{ choice.choice_text }} -- {{ choice.votes }} vote{{ choice.votes|pluralize }}</li>
  {% endfor %}
  </ul>
  <a href="{% url 'polls:detail' question.id %}">Vote again?</a>
  ```

<br>

---

# 2. Use generic views: Less code is better

- 위 코드들을 통해서 기본적인 웹 배포의 흔한 경우를 알 수 있다.

  - URL로 넘긴 매개변수에 따라 DB로부터 데이터를 얻고, template을 로딩하고, 구현된 template을 반환하는 흐름을 말한다.

- 그래서 django에서는 이에 대한 짧고 효과적인 방법인 `generic views` 를 제공한다.

- 그러면 polls app을 generic view로 전환하기 위해 몇 가진 단계를 거쳐보자.
  - URLconf를 전환하기 -> 오래되고 불필요한 view를 제거하기 -> Django의 generic views를 기반으로한 새로운 views를 도입하기
    - 여태 generic view를 사용하지 않은 이유는 django의 핵심 개념에 집중하기 위해서다.

<br>

## 2.1 Amend URLconf

- `polls/urls.py`의 urlpatterns에서 두 번째, 세 번째 url의 question_id를 pk로 바꿨다.

```yml
> app_name = 'polls'
> urlpatterns = [
>     path('', views.indexView.as_view(), name = 'index'),
>     path('<int:pk>/', views.DetailView.as_view(), name = 'detail'),
>     path('<int:pk>/results/', views.ResultsView.as_view(), name = 'results'),
>     path('<int:question_id>/vote/', views.vote, name = 'vote'),
> ]
```

<br>

## 2.2 Amend views

- 기존에 만들었던 `index`, `detail`, `results` 를 generic view로 대체할 것이다.

<br>

```yml
## 경로: polls/views.py
# def index -> class IndexView로 대체

> from django.views import generic

> class IndexView(generic.ListView):
>   template_name = 'polls/index.html'
>   context_object_name = 'latest_question_list'

>   def get_queryset(self):
>    """Return the last five published questions"""
>    return Question.objects.order_by('-pub_date')[:5]

## 경로: polls/views.py
# def detail -> class DetailView로 대체

> class DetailView(generic.DetailView):

# 밑에 model이 오타라든가 생성되지 않은 model을 할당하지 않으면 오류가 난다. 

>   model = Question  
>   template_name = 'polls/detail.html'


## 경로: polls/views.py
# def results -> class ResultsView로 대체

> class ResultsView(generic.DetailView):
>   model = Question
>   template_name = 'polls/results.html'
```

- 두 가지의 generic view: `ListView`, `DetailView` 를 사용했다.
  - 각 generic view는 어떤 model 위에 작동하는지 알아야 한다. 왜냐하면 `model` 속성을 사용하여 제공되기 때문이다.
  - 각 generic view는 `<app name>/<model name>_detail.html` 명칭의 템플릿을 사용하며, `template_name`을 사용하는 이유는 자동 생성되는 기본 템플릿 말고 이 이름을 가진 템플릿을 사용하라고 django에게 말해주는 것이다.

---

# Reference

- [Django at a glance](https://docs.djangoproject.com/en/4.0/intro/overview/)
