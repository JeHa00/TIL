# 0. Introduction

> 1. [Database setup](#1-database-setup)
> 2. [Creating models](#2-creating-models)
> 3. [Activating models](#3-activating-models)
> 4. [Playing with the API](#4-playing-with-the-api)
> 5. [Introducing the Django Admin](#5-introducing-the-django-admin)

- django 공식 문서를 번역하는 작업을 통해 튜토리얼을 진행하여 이해해본다.

- 이번 tutorial을 통해서 기본적인 설문조사 애플리케이션을 만들 수 있다.

  - 이 애플리케이션은 다음 2가지로 구성된다.
    - 사람들이 설문조사를 보고 투표할 수 있는 'public site'
    - 설문조사를 더하고, 수정하고, 삭제하는 'admin site'

- 이번 tutorial에서는 database를 설치하고, 첫 번째 model을 만들고, 장고가 자동적으로 생성되는 'admin site'에 대해 quick introduction을 얻는다. 

<br>

---

# 1. Database setup

- `mysite/setting.py` 를 열어보자. 이는 Django settings 내용이 있는 Python module이다.

- 기본적으로 SQLite를 사용하는데, SQLite는 파이썬에 포함되어 있기 때문에, 다른 db를 설치할 필요는 없다.

  - 하지만, 또 다른 database를 사용하고자 한다면 `mysite/setting.py`의 DATABASE 변수에서 key가 `'default'`인 dictionary value에 있는 `django.db.backends.<사용하려는 DB>` 로 수정한다. 그리고, sqlite 이외의 DB를 사용하고자 한다면 **USER**, **PASSWORD**, **HOST** 같이 세팅을 추가해야 한다.

    - `mysite/settings.py` 에서 제공되는 db user는 db를 생성할 권리를 가지고 있고, test database를 자동적으로 생성할 수 있다.

- `INSTALLED_APPS` 에는 모든 Django applications들의 이름을 가지고 있다. 이 애플리케이션들로 다양한 프로젝트에서 사용될 수 있다. 아래에 있는 application 들은 기본적으로 포함되어 있다.

  - `django.contrib.admin`: admin site
  - `django.contrib.auth`: 권한 부여 시스템
  - `django.contrib.contenttypes`: content type을 위한 framework
  - `django.contrib.sessions`: session framework
  - `django.contrib.messages`: messaging framework
  - `django.contrib.staticfiles`: static 파일들을 관리하기 위한 framework

- 위 application들은 최소 1개 이상의 database를 사용하기 때문에, db 안에 table을 더 생성할 필요가 있으니 아래 명령어를 실행해보자.

```yml
$ python manage.py migrate
```

- migrate는 `mysite/settings` 안에 db settings에 따라 필요한 db table을 생성한다.

<br>

---

# 2. Creating models

> 한 개의 model은 data에 대한 정보의 단 하나의 확정적인 출처다. 이 model에는 저장된 데이터의 필수적인 field와 행동들을 포함한다. Django는 DRY(Don't Repeat Yourself) 법칙을 따르기 때문에, Django의 목표는 데이터 모델을 **한 장소에 저장**하고, **이 한 장소로부터** 자동적으로 많은 것들을 시작되도록 하는 게 목표다.

- poll app에서는 2개의 model을 만들 것이다. **Question** 과 **Choice** 다.

  - 전자는 question과 public data를 가진다.
  - 후자는 2개의 필드를 가지는데, choice_text 와 votes 다.
  - 후자는 전자와 연관되어 있다.

- 이 개념들은 **Python classes** 에 의해서 나타낸다.

- 그러면 `polls/models.py` file을 아래와 같이 수정해보자.

```yml
> from django.db import models

# model: Question
> class Question(models.Model):
>   question_text = models.CharField(max_length = 200)
>   pub_data = models.DataTimeField('data published')

# model: Choice
> class Choice(models.Model):
>   question = models.foreignKey(Question, on_delete = models.CASCADE)
>   choice_text = models.CharField(max_length = 200)
>   votes = models.integerField(default = 0)
```

- **각 model은 클래스로 구현된다.**

  - 각 클래스는 `django.db.models.Model`의 서브클래스다.

  - 각 model은 그래서 클래스 변수를 가지고 있고, 각 클래스 변수는 db에서 db field로 표현된다.

- `Field` class

  - `question` class 변수를 제외한 클래스 변수들은 `Field` 클래스의 인스턴스로서, `CharField`는 문자열 data type을, `DataTimeField`는 datetime에 대한 field다. 즉, 이 2개의 field는 django에게 각 filed가 보유하고 있는 데이터 타입이 무엇인지를 알려준다.

  - 또한, 각 Field의 인스턴스인 클래스 변수의 이름은 databse의 field의 이름이며, 파이썬 코드 안에서 이 값을 사용할 것이고, db에서는 이 이름들을 column name으로 사용할 것이다.

  - `CharField` class: `max_length` 값을 요구한다. 이는 db schema에서뿐만 아니라, validation(확인, 검증)에서도 사용한다.

  - 또한 `Choice.votes` 처럼 `Field` class는 다양한 값을 인자로 받는다.

  - ForeignKey (외래키)를 사용하여 관계를 정의할 수 있다. 각 `Choice` class는 `Question` class 와 관련 있다. django는 모든 DB 관계들을 지원한다.

<br>

---

# 3. Activating models

> Django apps은 'pluggable' 하다. 이 말의 의미는 django app은 하나의 app으로 plug 처럼 다양하나 프로젝트에 사용될 수 있다는 의미로서, 하나의 프로젝트에 묶여있지 않다는 말이다.

- 위 코드는 django에게 많은 정보를 제공한다.

  - 이 앱을 위한 db schema를 생산하라.
  - 객체 Question과 Choice에 접근하기 위한 파이썬 코드로 된 API를 생산하라.

- 이를 위해서 첫 번째, polls app을 설치해야 한다.

  - `INSTALLED_APPS` setting에 poll app의 class에 대한 참조를 추가해야 한다.
  - 그 참조는 `polls/apps.py`에 있는 class `PollsConfig`를 dot notation으로 입력한다.

    ```yml
    INSTALLED_APPS = [
    'polls.apps.PollsConfig',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    ]
    ```

- 다음으로 `python manage.py makemigrations polls`를 실행한다.

  ```yml
  $ python manage.py makemigrations polls
  Migrations for 'polls':
      polls\migrations\0001_initial.py
      - Create model Question
      - Create model choice
  ```

  - `makemigrations`를 실행하면 model에 변화를 줬으니 _migration_ 으로서 저장하겠다는 의미다.

- `migrations` 는 django가 model의 변화를 저장하는 방법으로, database에 model table을 생성하는 방법이다.

  - migration 한 것을 보고 싶다면 `polls/migrations/0001_inital.py` 에서 읽을 수 있다.
  - migration이 사용하는 SQL이 무엇인지를 보고 싶다면 `python manage.py sqlmigrate polls 0001`을 입력하자.

    ```yml
    $ python manage.py sqlmigrate polls 0001
    BEGIN;
    --
    -- Create model Question
    --
    CREATE TABLE "polls_question" ("id" integer NOT NULL PRIMARY KEY AUTOINCREMENT, "question_text" varchar(200) NOT NULL, "pub_date" datetime NOT NULL);
    --
    -- Create model Choice
    --
    CREATE TABLE "polls_choice" ("id" integer NOT NULL PRIMARY KEY AUTOINCREMENT, "choice_text" varchar(200) NOT NULL, "votes" integer NOT NULL, "question_id" bigint NOT NULL REFERENCES "polls_question" ("id") DEFERRABLE INITIALLY DEFERRED);
    CREATE INDEX "polls_choice_question_id_c5b4b260" ON "polls_choice" ("question_id");
    COMMIT;
    ```

- 그러면 migrations을 실행하여 생성되는 table에 대해 알아보자.

  - table name은 `polls`와 model name의 소문자 이름 `question`, `choice`를 조합하여 자동적으로 생성된다.
  - Primary kiey는 자동적으로 더해진다.
  - foreign key field name에는 `_id`가 더해진다.

- `sqlmigrate` 명령어는 실제로 migrate를 하는 게 아닌, 단지 django가 생각하기에 요구되는 SQL 문이 무엇인지 볼 수 있도록 화면에 출력하는 용도다. 그래서 django가 무엇을 할 것인지 확인하고 싶으면 이 명령어를 사용한다.

<br>

---

# 4. Playing with the API

1. `python manage.py shell` 입력

2. `from polls.models import Choice, Question` 입력

3. `Question.objects.all()` : system에 아직 question이 없기 때문에, 새로운 question을 생성한다.
   - `<QuerySet []>` 생성

```yml
# 내가 작성한 model classes를 import
>  from poll.models import Choice, Question

# 시스템에는 아직 question이 존재하지 않는다.
> Question.objects.all()
 <QuerySet []>

# 새로운 Question을 생성한다.

> from django.utils import timezone
> q = Question(question_text = "What's new?", pub_date = timezone.now())

# 객체 q를 db에 저장한다.
> q.save()

# 저장해서 q는 ID를 가진다.
> q.id
1

# 파이썬 속성들을 통해서 model field 값에 접근한다.
> q.question_text
"What's new?"

> q.pub_date
 datetime.datetime(2022, 7, 5, 2, 54, 11, 538368, tzinfo=datetime.timezone.utc)

# 속성 값을 변경한다.
> q.question_text = "What's up?"
> q.save()

# object.all() 은 db에 있는 모든 question 을 보여준다.
> Question.objects.all()
<QuerySet [<Question: Question object (1)>]>
```

- 그런데 `<QuerySet [<Question: Question object (1)>]>`은 이 객체를 드러내는데 도움이 되지 않기 때문에, 보다 효과적으로 수정해보자.
  - magic method `__str__`를 model class에 추가하자.

```yml
# polls/models.py
> class Question(models.Model):

    ...
>   def __str__(self) ->str:
>       return self.question_text


> class Choice(models.Model):

    ...
>   def __str__(self) ->str:
>       return self.choice_text
```

- 또한 class Question에서 변수 pub_date를 알아보기 쉽게 바꿔보자.

```yml
# polls/models.py

> import datetime

> from django.db import models
> from django.utils import timezone

> class Question(models.Model):
    #...
>    def was_publiced_recently(self):
>       return self.pub_date >= timezone.now() - datetime.timedelta(days = 1)
```

- 그러면 어떻게 변했을지 shell을 다시 열어서 실행해보자.

```yml
> from polls.model import Choice, Question

> Question.object.all()
<QuerySet [<Question: What's up?>]>

> q = Question.objects.get(id = 1)
> q.was_publisehd_recently()
True

> q.choice_set.create(choice_text = 'Not much', votes = 0)
<Choice : Not much>

> q.choice_set.objects.all()
<QuerySet [<Choice: Not much>]>

> q.choice_set.count()
1
```

<br>

---

# 5. Introducing the Django Admin

### admin user 만들기

- `python manage.py createsuperuser` 를 실행하여 뜨는 입력란에 정보를 다 입력하는데, 이 정보들은 admin user의 ID와 Password가 된다.

<br>

### 배포 서버 실행하기

- `python manage.py runserver`
  - local server 주소는 http://127.0.0.1:8000/ 이지만, admin은 http://127.0.0.1:8000/admin/ 다.

<br>

### Question 등록하기

- admin index page에는 poll app이 보이지 않아서 아래 명령어를 입력해줘야 한다.
  - `admin.site.register(Question)`

<br>

---

# Reference

- [Django at a glance](https://docs.djangoproject.com/en/4.0/intro/overview/)
