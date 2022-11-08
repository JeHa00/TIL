
# 0. Introduction 

> 1. [Throttle Scope](#1-throttle-scope)    
> 2. [pagination](#2-pagination_class)    
> 3. [authentication & permission](#3-authentication--permission)  


- 해당 강의는 [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)의 김형종 강사님의 django 강의를 학습한 내용입니다.

- 그리고, [Django REST framework](https://www.django-rest-framework.org/tutorial/)를 함께 참고하여 학습했습니다.


- 이번 챕터에서는 지난 챕터에서 언급한 DRF의 Policy에 대해 학습해봅니다. 
	- throttle_scope
	- pagination_class
	- authentication_classes & permission_classes

<br>

---
# 1. Throttle Scope


## settings.py에 환경 변수 추가

호출 제한하기 위해서는 config/base.py 또는 config/settings.py에서 환경 변수 하나를 추가한다. 

[Throttling](https://www.django-rest-framework.org/api-guide/throttling/#scopedratethrottle)을 참고한다.  

```python
REST_FRAMEWORK = {
	"DEFAULT_THROTTLE_CLASSES": [
		"rest_framework.throttling.ScopedRateThrottle"
	],
	"DEFAULT_THROTTLE_RATES": {
		"basic": "3/day",
		"premium": "100/day",
	}
}
```

해당 위치에다가 작성한 이유는 api 호출 전체 횟수를 여기서 관리하기 위함이다.  

- 4번 새로고침하면 끊긴다. 왜냐하면 'basic'으로 하루 3번으로 설정했기 때문이다.

- 원래는 DB에 호출횟수를 입력해야 한다. 

```python
from rest_framework.viewsets import ModelViewSet

class EmployeeViewSet(ModelViewSet):
	queryset = Employee.objects.all() 
	serializer_class = EmployeeSerializer 

	throttle_scope = "basic"
```

<br>

---

# 2. pagination_class

[Pagination](https://www.django-rest-framework.org/api-guide/pagination/)도 함께 참고했다. 

## ViewSet에 pagination_class 추가

```python
from rest_framework.pagination import PageNumberPagination
from rest_framework.viewsets import ModelViewSet

class CustomPagination(PageNumberPagination):
	page_size = 1 

class EmployeeViewSet(ModelViewSet):
	queryset = Employee.objects.all() 
	serializer_class = EmployeeSerializer 

	pagination_class = CustomPagination
	throttle_scope = "basic"
```

하지만, pagination_class도 throttle_scope 처럼 전역적으로 관리하기 위해서는 settings.py에 아래와 같이 추가할 수 있다. 

```python
REST_FRAMEWORK = {
    'DEFAULT_PAGINATION_CLASS': 'rest_framework.pagination.LimitOffsetPagination',
    'PAGE_SIZE': 100
}
```


<br>

---
# 3. authentication & permission


- `authentication_classes = []` 과 `permission_classes = [IsAuthenticated]`를 추가한다.

```python
from rest_framework.pagination import PageNumberPagination
from rest_framework.viewsets import ModelViewSet
from rest_framework.permissions import IsAuthenticated

class CustomPagination(PageNumberPagination):
	page_size = 1 

class EmployeeViewSet(ModelViewSet):
	queryset = Employee.objects.all() 
	serializer_class = EmployeeSerializer 

	authentication_classes = []
	permission_classes = [IsAuthenticated]
	pagination_class = CustomPagination
	throttle_scope = "basic"
```

## 3.1 Custom permission 만들기

[공식문서: Custom Permissions](https://www.django-rest-framework.org/api-guide/permissions/#custom-permissions)도 함께 참고했다. 

drf에서 기본적으로 제공하는 Permission이 아니라, 이를 상속받아 custom permission을 만들어본다. 

```python
from rest_framework import permissions

class CustomPermission(permissions.BasePermission): 
	# list를 받을 때
    def has_permission(self, request, view):
		print(f"has_permission")
        return True

    # queryset이 all이냐 아니면 last냐
    def has_object_permission(self, request, view, obj):
        print("has_object_permission")
		return True
```

그러면 이를 `class EmployeeViewSet` 의 permission_classes의 list data type에 넣는다. 


```python
from .permissions import CustomPermission

class EmployeeViewSet(ModelViewSet):
	...
	permission_classes = [IsAuthenticated, CustomPermission]
	...
```

만약 `has_permission` 부분을 False로 바꾸면 다음과 같은 결과가 뜬다. 

```python
{
    "detail": "자격 인증데이터(authentication credentials)가 제공되지 않았습니다."
}
```

True로 바꾸면 다음과 같다. 

```python
has_permission
```

또는 `return True` 대신에 `return request.user.is_staff`을 추가하여 staff 여부를 확인할 수 있다.

<br>

## 3.2 authentification


인증 방식에는 4가지가 있는데, 이번 학습에는 TokenAuthentication에 대해 알아본다.  
- BasicAuthentication 
- TokenAuthentication 
- SessionAuthentication  
- JSON Web Token(JWT)  


### TokenAuthentication 추가하기

- `from rest_framework.authentication import TokenAuthentication`을 추가하여 `authentication_classes`에 추가하여 token 방식으로 인증을 진행하겠다는 걸 의미한다.

```python
from rest_framework.pagination import PageNumberPagination
from rest_framework.viewsets import ModelViewSet
from .permissions import CustomPermission
from rest_framework.authentication import TokenAuthentication


class EmployeeViewSet(ModelViewSet):
	queryset = Employee.objects.all() 
	serializer_class = EmployeeSerializer 

	# Policy
	authentication_classes = [TokenAuthentication]
	permission_classes = [IsAuthenticated, CustomPermission]
	pagination_class = CustomPagination
	throttle_scope = "basic"

```

### token 발행 경로 추가하기

master url에 아래 코드를 추가하여 token 발행 경로를 추가해보자. 

```python
from rest_framework.authtoken.views import obtain_auth_token

api_patterns = [
	...
	path("auth/token/", obtain_auth_token)
]
```

### settings.py 또는 settings/base.py 에 app 추가하기

```python
INSTALLED_APPS = [
	...
	"rest_framework.authtoken"
]
```


### 실행해보기

그러면 해당 url를 입력해보면(GET) 다음과 같은 결과가 뜨면 올바르게 작동된 것이다.

```python
{
"detail": "메소드(Method) \"GET\"는 허용되지 않습니다."
}
```

그러면 POSTMAN 을 사용해서 POST로 하여 다시 보내보자. 다음과 같은 결과가 뜨면 올바르게 작동된 것이다.

```python
{
    "username": [
        "이 필드는 필수 항목입니다."
    ],
    "password": [
        "이 필드는 필수 항목입니다."
    ]
}
```

그러면 Body tab을 포스트맨에서 선택 후, `form-data`를 선택하여 데이터를 입력해보자. 

superuser로 만들었던 아이디와 비밀번호를 입력하면 아래와 같이 토큰이 발급된다.

```python
{
    "token": "626ccf92~~~~~"
}
```

그러면 이 Token을 http message header에 추가하여 GET으로 함께 해당 api_url를 보내면 user 정보를 받을 수 있다.  


<br>


### Refresh token

실제로 구글에는 2가지 토큰이 있다. 
- refresh token 과 일반 토큰

refresh token이 이 일반 토큰을 계속해서 갱신시킨다.

<br>

---


# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/)