
# 0. Introduction 

> 1. [urls 분기](#1-urls-분기)    
> 2. [MRO](#2-mro)  

- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- 그리고, [Django REST framework](https://www.django-rest-framework.org/tutorial/)를 함께 참고하여 학습했습니다.


- 이번에는 django의 외부 라이브러리인 DRF(Django Rest-Framework)에 대해 학습한 걸 정리했습니다. 
	- urls.py를 api_url / view_url 분기



<br>

---

# 1. urls 분기

## api_patterns 과 urlpatterns로 분기

> **_실제 API를 만들 때는 urls를 view_urls 와 api_urls 로 분기한다._**    


### app urls 나누기

- 해당 app의 urls.py가 존재하는데, 이를 `view_url.py`와 `api_url.py`로 나눈다. 이를 위해서 `urls` directory를 해당 app directory 안에 생성한다. 

- 그리고 Python 인터프리터가 이를 모듈로 인식하기 위해서 `__init__.py`를 만든다. 
	- `__init__.py` 안에는 `from .api_url import *` 와 `from .view_url import *` 를 입력한다. 

```yml
<app>
  ㄴ urls
	  ㄴ __init__.py
	  ㄴ api_url.py
	  ㄴ view_url.py 
```

- 이때 settings.py 를 나눌 때 `base.py`를 만든 이유는 공통사항이 있어서 그런 것이고, app urls를 나눌 때는 공통사항이 없기 때문에 `base.py`를 만들지 않는다.

### Master url에 포함시키기

- Master url이란?

	- config/urls.py 처럼 `ROOT_URLCONF`에 등록된 url를 `master url` 이라 한다.


- `config/urls.py`에 아래 내용으로 수정한다.
	- url 내용은 예시를 의미한다.

- api_patterns는 고유 변수명이 아니기 때문에, 원하는 name으로 사용하자. 중요한 것은 이를 urlpatterns에 포함시킨다는 것이다. 

```python
from django.urls import path, include

api_patterns = [
	path("user/", include('user.urls.api_url')),
	path("course/", include('course.urls.api_url')),
]

urlpatterns = [
	path("user/", include('user.urls.view_url')),
	path("course/", include('course.urls.view_url')),
	path('api/', include(api_patterns))
]
```

<br>

---

# 2. MRO

우리가 반드시 알아야하는 개념: MRO 

- 상속과 관련된 계층도를 의미

Registration → ModelViewSet

ListAPIVIEW: 여러 개만 읽어온다. 

RetrieveAPIVIEW: 한 개만 읽어온다.

Mixin은 파이썬 개념이므로, 나중에 찾아보기

CRUD를 제공하는게 ModelViewSet 

ModelViewSet에는 CRUD가 다 있다. 

GenericAPIView는 APIView를 상속받아 사용한다. 

그런데, 현업에서는 APIView만을 상속받아 별도로 만든다. 
- Lesson Model에 관한거라면 LessonAPIView로 만든다.
- 하지만 이런 경우, 내부는 비어있기 때문에 직접 작성해야 한다. 
- GenericAPIView 내부를 보면 queryset 과 serailizer_class 가 존재한다.  이에 대한 값을 새로 할당하기 위해서 ModelViewSet에 작성한다.


API 문서에는 규격이 정해져있다. 이를 간편하게 하기 위해서 안쓴다. 

DRF의 경우, 화면에 CRUD가 다 있다. 

GenericView를 그대로 쓸 것이냐 아니면 APIView를 쓸 것인지 택 1하는데, 더 자유롭기 때문에, 후자를 택한다.  

JsonResponse → Serializer → bytestring


class ListModelMixin 내부를 보면 다음과 같은 코드를 볼 수 있다. 

get_serializer(page, many=True)를 보면 many가 ListModelMixin에는 들어가지만,

	```python
	class ListModelMixin:
		"""
		List a queryset.
		"""
		def list(self, request, *args, **kwargs):
			queryset = self.filter_queryset(self.get_queryset())

			page = self.paginate_queryset(queryset)
			if page is not None:
				serializer = self.get_serializer(page, many=True)
				return self.get_paginated_response(serializer.data)

			serializer = self.get_serializer(queryset, many=True)
			return Response(serializer.data)
	```

RetrieveAPIView를 보면 many가 들어가지 않고, instance만 입력한다.

	```python
	class RetrieveModelMixin:
    """
    Retrieve a model instance.
    """
    def retrieve(self, request, *args, **kwargs):
        instance = self.get_object()
        serializer = self.get_serializer(instance)
        return Response(serializer.data)
	```


다시 ListAPIView를 보면 단지 get method만 있지만, 이는 ListModelMixin을 상속받기 때문에, get을 받을 때 동작이 바로 위에 ListModelMixin에서 일어나는 것이다.



<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)