
# 0. Introduction 

> 4. [DRF의 이점](#3-drf로-도움받을-것들)  
> 5. [DRF에 사용할 models.py](#4-drf에-사용할-modelspy)  
> 6. [view 설계: APIView](#5-view-설계-apiview)
> 7. [view 설계: @api_view](#6-view-설계-api_view)   
> 8. [view 설계: ModelViewSet](#7-view-설계-viewset)    
> 9. [자주 사용되는 view 설계](#9-자주-사용되는-view-설계)


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- 그리고, [Django REST framework](https://www.django-rest-framework.org/tutorial/)를 함께 참고하여 학습했습니다.


- 이번에는 django의 외부 라이브러리인 DRF(Django Rest-Framework)에 대해 학습한 걸 정리했습니다. 
	- DRF와 DRF의 핵심인 직렬화가 무엇인지
	- 어떤 흐름으로 설계를 진행하는지
	- ModelViewSet, @api_view, ViewSet 각각으로 view를 만드는 방법
	- Serializer 설계  



<br>

---

# 4. DRF의 이점

모든 것을 DRF로 구현하는 게 아닌, DRF 기능 중 선별적으로 필요한 것만 골라서 사용한다.

최종적으로는 API를 만드는 것이지만, 구체적으로는 다음과 같다. 

- 첫 번째, Serializer
- 두 번째, Response
- 세 번째, APIView
- 네 번째, Policy

## DRF - Serializer

json library는 json으로 직렬화가 가능하지만, Django object까지는 처리하지 못한다.


## DRF - Response

- DRF의 모든 기능을 사용하여 API를 만드는 게 아니라, DRF의 기능을 선별적으로 사용한다.

```python
## django에서는 다음 모듈을 사용
from django.http import JsonResponse

## DRF에서는 다음 모듈을 사용
from rest_framework.response import Response
```


## DRF - APIView

DRF로 API를 만들 때 사용하는 방법들 3가지: APIView, @api_view, ModelViewSet

### APIView

```python
# CBV
# TemplateView와 유사
class LessonAPIView(APIView):
	def get(self, request):
		return
```

<br>

### @api_view(["GET"]])

```python
# FBV

@api_view(["GET"])
def get_lesson(request):
	return 
```

<br>

### ModelViewSet 상속

```python
# DRF에서 제공하는 ModelViewSet
# FormView 와 유사
class LessonViewSet(ModelViewSet):
	queryset = Lesson.objects.all() 
	serializer_class = LessonSerializer
```

<br>

### DRF - Policy

DRF에서 제공하는 유용한 기능 'Policy'

- authentication_classes & permission_classes
	- api 서버에 접근하여 가져올 때, 권한 부여

- pagination_class
	- pagination 만들기

- throttle_scope
	- api를 다룰 때 횟수 제한이 있다.
	- 이 횟수제한을 다루는 기능


<br>

---

# 5. DRF에 사용할 models.py

```python
from django.db import models
from django.contrib.auth.models import User


class Company(models.Model):
    SCALES = [(0, "10명미만"), (1, "50명미만"), (2, "100명미만"), (3, "300명미만"), (4, "500명미만")]

    name = models.CharField(verbose_name="회사명", max_length=30)
    scale = models.IntegerField(verbose_name="규모", choices=SCALES, null=True)

    def __str__(self):
        return f"{self.name} ({self.get_scale_display()})"

    class Meta:
        verbose_name = "회사"
        verbose_name_plural = "회사 목록"


class Employee(models.Model):
    company = models.ForeignKey(Company, on_delete=models.CASCADE, verbose_name="소속(회사)")
    user = models.OneToOneField(User, on_delete=models.CASCADE, verbose_name="유저")

    name = models.CharField(verbose_name="이름", max_length=10)
    phone = models.CharField(verbose_name="연락처", max_length=11)
    team = models.CharField(verbose_name="소속(팀)", max_length=30)

    is_agreed = models.BooleanField(verbose_name="이용약관 동의", default=False)

    def __str__(self):
        return f"{self.name} ({self.company})"

    class Meta:
        verbose_name = "임직원"
        verbose_name_plural = "임직원 목록"
```

<br>

---

# 6. View 설계: APIView

APIView를 사용하여 View를 설계해보고, django에서 제공하는 JsonResponse와 DRF에서 제공하는 Response를 비교해본다.

- 6.1: `from django.http import JsonResponse`  
- 6.2: `from rest_framework.response import Response`    
- 6.3: 직렬화 추가  


🔆 url에 매핑하는 방식은 기존 CBV 방식과 동일하다.


## 6.1 JsonResponse
### TypeError: Object of type Queryset is not JSON serializable

- 위 모델들이 바로 직렬화가 안되면 이 모델들을 dictionary 형태로 바꾸고 나서 직렬화를 진행하면 가능하다. 이를 아래 view 코드를 통해서 확인해보자. 
- 아래 result의 결과는 Json 직렬화를 할 수 없다는 Error가 발생한다. 
	- `TypeError: Object of type QuerySet is not JSON serializable`
- 왜냐하면 queryset은 jsonresponse로 던질 수 있는 적합한 타입이 아니다. 
	- queryset은 파이썬이 아닌 장고에서 만든 것이다.
- 그래서 파이썬에서 만들 수 있는 자료형으로 형변환 후, 이를 JsonResponse에 전달한다.

```python
# user/views.py
from rest_framework.views import APIView
from django.http import JsonResponse 

class EmployeeListAPIView(APIView):
	def get(self, request): 
		employee_qs = Employee.objects.all() 

		result = {"employee_list": employee_qs}

		return JsonResponse(result)

```

<br>


### queryset을 다른 data type으로 변환 후, 직렬화하기

- 그래서 위 내용을 다음과 같이 python의 다른 형태로 형변환하면 화면 상에서 볼 수 있다.

```python
# user/views.py
from rest_framework.views import APIView
from django.http import JsonResponse 

class EmployeeListAPIView(APIView):
	def get(self, request): 
		employee_qs = Employee.objects.all() 
        
		# 아래처럼 입력하면 employee_list 라는 key 값에 value가 list형태인 것으로 직렬화되어 출력된다.
		result = {"employee_list": list(employee_qs.values_list('name', flat=True))}
		
		return JsonResponse(result)
```

<br>

## 6.2 Response

- 아래 코드를 기반으로 화면을 보면 위에 JsonResponse와 달리 Http message 형태로 볼 수 있다. 

```python
from rest_framework.views import APIView
from rest_framework.response import Response

class EmployeeListAPIView(APIView):
	def get(self, request): 
		employee_qs = Employee.objects.all() 
        
		# 아래처럼 입력하면 employee_list 라는 key 값에 value가 list형태인 것으로 직렬화되어 출력된다.
		result = {"employee_list": list(employee_qs.values_list('name', flat=True))}
		
		return Response(result)
```

<br>

## 6.3 Serializers 추가하기

먼저 `./serializers.py`에서 `EmployeeSerializer` 를 만든다. 

```python
from rest_framework.serializers import ModelSerializer
from user.models import Employee


class EmployeeSerializer(ModelSerializer):
    class Meta: 
        model = Employee
        fields = "__all__"
```

그리고 이를 APIView에 추가한다.


```python
from rest_framework.views import APIView
from rest_framework.response import Response

class EmployeeListAPIView(APIView):
	def get(self, request): 
		employee_qs = Employee.objects.all() 
        
		serializer = EmployeeSerializer(employee_qs, many=True)
		
		return Response(serializer)
```

결과는 [5.2 Response](#52-response) 방식과 동일하다. 

하지만, 코드는 보다 간결해진 걸 알 수 있다. 


만약 모든 필드가 아닌 원하는 필드면 입력하여 뽑아낼 수 있다. 

- `fields = '__all__'`이 아닌 `fields = ['name']`으로 입력하면 name 필드 관련된 것만 가져온다.


<br>

---

# 7. View 설계: @api_view([''])

`@api_view([''])`를 사용하여 위에 CBV로 만든 것과 내용을 동일하게 하면서 FBV로 만들어보겠다.

데코레이터의 인자로는 리스트 데이터 타입으로 입력해야 한다.


```python
from rest_framework.serializers import ModelSerializer
from rest_framework.decorators import api_view
from user.models import Employee

@api_view(['GET'])
def employee_list(request):
	employee_qs = Employee.objects.all() 
	serializer = EmployeeSerializer(employee_qs, many=True)
	return Response(serializer.data)
```

🔆 url에 매핑하는 방식은 기존 FBV 방식과 동일하다.


<br>

---


# 8. View 설계: ViewSet

CBV 방식으로서, naming은 `class <Model name>ViewSet(ModelViewSet):`로 한다.


- ModelViewSet을 받아서 ViewSet을 만든다.
- 이 때 2가지 옵션을 단다.
	- `queryset`, `serializer_class` 를 만든다. 
- queryset 은 `<모델명>.objects.all()` 을 만들어 할당한다. 
- serializer_class에 해당하는 건 `<모델명>Serailizer`로 작성한다.

```python
# course/views.py 

from rest_framework.viewsets import ModelViewSet
from .serialization import EmployeeSerializer

class EmployeeViewSet(ModelViewSet):
	queryset = Course.objects.all() 
	serializer_class = EmployeeSerializer 
```

## url에 mapping 하기

> **_일반적인 cbv 방식과 달리 다음과 같이 DefaultRouter를 가져온다._**

### Router의 역할

- router는 nginx처럼 여러 군데로 보내는 역할을 수행한다.

- router를 만들어서 여기에 `.register`를 사용하여 등록했다. 

- router가 ModelViewSet을 사용하여 url를 여러개 만들어주는 역할을 한다.  

	- url 뒤에 pk 입력하는 url 설계는 하지 않았음에도 불구하고, 뒤에 pk를 입력하면 해당되는 데이터가 뜬다.

- router.register의 수는 ModelViewSet으로 만들고 싶은 Model의 갯수만큼 입력한다.

```python
# users/views.py
from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import EmployeeViewSet

router = DefaultRouter() 
router.register('employee', EmployeeViewSet)

urlpatterns = [
	path("", include(router.urls))
]
```

<br>

---


# 9. 자주 사용되는 view 설계

> **_ViewSet보다 APIView를 훨씬 많이 사용한다._**

- FormView를 잘 사용하지 않는 이유
	- 요구사항이 바껴서 적용하기 힘들 때, form에 post를 전달하는 것 대신에 Ajax로 API를 체크한다. 

- ModelViewSet을 잘 사용하지 않는 이유 
	- ModelViewSet으로는 API를 자세하게 다룰 수 없어서, APIView를 주로 사용한다. 


<br>

---




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

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)