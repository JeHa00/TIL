
# 0. Introduction 

> 3. [url 설계: api_url, view_url 분기](#3-url-설계-apiurl-viewurl-분기)    
> 4. [view 설계: ModelViewSet]()    
> 5. [view 설계: @api_view]()   
> 6. [view 설계: ViewSet]()   
> 7. [Serializer 설계]()    



- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- 그리고, [Django REST framework](https://www.django-rest-framework.org/tutorial/)를 함께 참고하여 학습했습니다.


- 이번에는 django의 외부 라이브러리인 DRF(Django Rest-Framework)에 대해 학습한 걸 정리했습니다. 
	- DRF와 DRF의 핵심인 직렬화가 무엇인지
	- 어떤 흐름으로 설계를 진행하는지
	- ModelViewSet, @api_view, ViewSet 각각으로 view를 만드는 방법
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

# API 만드는 방법 첫 번째

### course/urls/api_url.py

router는 nginx처럼 여러 군데로 보내는 역할을 수행한다.
 
router를 만들어서 여기에 `.register`를 사용하여 등록했다. 

그리고, 굳이 ViewSet로 해야하는 이유는 없고, View로 만들어도 된다. 



```python
from django.urls import path

from rest_framework.routers import DefaultRouter
from ..views import CourseViewSet, GroupViewSet, RegistrationViewSet

router = DefaultRouter() # instance 형태로, 객체 형태로 넣어준다.

# 존재하는 Model의 갯수만큼 입력한다.
# <모델명>ViewSet 으로 작성하여 상속받아서 만든다는 의미다. 
router.register("course", CourseViewSet)
router.register("group", GroupViewSet)
router.register("registration", RegistrationViewSet)

urlpatterns = [
		path("", include(router.urls))
]
```

<br>

### course/urls/api_url.py 에 작성한 ViewSet 만들기

- ModelViewSet을 받아서 ViewSet을 만든다.
- 이 때 2가지 옵션을 단다.
	- `queryset`, `serializer_class` 를 만든다. 
- queryset 은 `<모델명>.objects.all()` 을 만들어 할당한다. 
- serializer_class에 해당하는 건 `<모델명>Serailizer`로 작성한다.

```python
# course/views.py 

from rest_framework import viewsets
from .serialization import CourseSerializer, GroupSerializer, RegistrationSerializer

class CourseViewSet(viewsets.ModelViewSet):
	queryset = Course.objects.all() 
	serializer_class = CourseSerializer 

class GroupViewSet(viewsets.ModelViewSet):
	queryset = Group.objects.all() 
	serializer_class = GroupSerializer 

class RegistrationViewSet(viewsets.ModelViewSet):
	queryset = Registration.objects.all() 
	serializer_class = RegistrationSerializer 

```

<br>

### course/views.py 에 작성한 Serializer 작성하기

- Serializer의 class name은 `<Model명>Serializer` 로 작성한다.

- fields의 역할은 가져오는 정보 종류를 의미한다. 
	- `"__all__"` 이면 모든 정보를 다 가져온다. 
	- 하지만 `"code"` 를 하면 code만 가져온다. 

- drf의 view인 `APIView` 라는 걸 이용해서 하나 하나 api를 구현할 수 있지만, drf는 설정한대로 하면 금방한다. 


```python
# course/serialization.py

from rest_framework import serializers 
from course.models import Course, Group, Registration

class CourseSerializer(serializers.ModelSerializer):
	class Meta:
		model = Course
		fields = "__all__"
		# fields = ["code"] 
		# 만약 이렇게 하면 code만 가져온다. 
			
class GroupSerializer(serializers.ModelSerializer):
	class Meta:
		model = Group
		fields = "__all__"

class RegistrationSerializer(serializers.ModelSerializer):
	class Meta:
		model = Registration
		fields = "__all__"
```

A serializer class is very similar to a Django Form class, and includes similar validation flags on the various fields, such as required, max_length and default.

<br>

---

### DRF 화면 확인하기 

`python manage.py runserver --settings=config.settings.develop`를 실행 후,  url에 `api/course/`를 입력한다.

그러면 DRF 화면이 뜬다. 

### APIView와 ViewSet 의 사용 빈도 

ViewSet보다 APIView를 훨씬 많이 사용한다.

FormView를 잘 사용하지 않는 이유가 요구사항이 바껴서 적용하기 힘들 때, 폼에 포스트를 전달하는 것 대신에 Ajax로 API를 체크한다. 

ModelViewSet으로는 API를 자세하게 다룰 수 없어서, APIView를 주로 사용한다. 


<br>

---


API_VIEW를 좀 더 많이 사용한다. 

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

---

# API View 만드는 두 번째

### course/urls/api_url.py

```python

urlpatterns = [
	# path("", include(router.urls)),
	path("course", CourseAPIView.as_view()),

]

```

### course/views.py

```python
class CourseAPIView(APIView):

	def get():
		lesson_qs = Lesson.objects.all() 


		# serialize 는 모두 json으로 바꿔주지만, 사용하지 않으면 수동으로 다 해야 한다. 
		data = dict() 
		data["lesson_list"] = lesson_qs.values_list("code", flat=True)

		return response 
		# 또는
		return JsonResponse(data, status)

	def post()

```


DRF의 GenericView를 상속받아서 사용할 수도 있지만, 이렇게 APIView를 커스텀해서 사용하냐로 택일이다. 

API는 외부에서 쐈을 때, json 형태로 받아지는 것인데, 이 값에 대한 규격이 무엇을 사용하냐에 따라서 달라지는 것이다. 




<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)