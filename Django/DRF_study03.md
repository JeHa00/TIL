
# 0. Introduction 

> 3. [url 설계: api_url, view_url 분기](#3-url-설계-apiurl-viewurl-분기)    



- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- 그리고, [Django REST framework](https://www.django-rest-framework.org/tutorial/)를 함께 참고하여 학습했습니다.


- 이번에는 django의 외부 라이브러리인 DRF(Django Rest-Framework)에 대해 학습한 걸 정리했습니다. 
	- Serializer 설계  



1. DRF 설치 및 api_url / view_url 분기
2. DRF의 serializer 등록 후, ModelSerializer 바라보기



<br>

---

# 3. url 설계: api_url, view_url 분기

### api_patterns 과 urlpatterns로 분기

> **_실제 API를 만들 때는 View를 view_urls 와 api_urls 로 분기한다._**    

config/urls.py 처럼 `ROOT_URLCONF`에 등록된 url를 `master url` 이라 한다.

master url에 다음 내용을 추가한다. 

```python
## config/urls.py
# 추가할 내용
api_patterns = [
	path("course/", include("course.urls.api_url")),
]

urlpatterns =[
	...
	path("api/", include(api_patterns)),

]

# 변경한 내용
urlpatterns = [
	path("course/", include("course.urls.view_url")), 
]
```

위 내용을 아래 내용으로 분기하자. 위에 api_patterns는 임의로 만든 변수다. 

### course/urls.py → course/urls/view_url.py 로 이름 변경
### course/urls/api_url.py

위에 api_patterns에서 include(”course.urls.api_url”)에 언급했듯이

`course/urls/api_url.py` 와 `course/urls/view_url.py`로 분리하기

그리고 각 directory 안에다가 `__init__.py` 만들어서 프로젝트 인식하도록 하기 

또한, 공통적으로 상속받는게 없기 때문에, settings 처럼 base는 만들지 않는다. 

```python
# config/urls/api_url.py

urlpatterns =[]

# config/urls/view_url.py

urlpatterns =[
		...
		path("api/", include(api_patterns)),

]
```

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)