
# 0. Introduction 

> 1. [DRF(Django RestFramework)란?](#1-drfdjango-restframework란)    
> 2. [직렬화와 역직렬화](#2-직렬화와-역직렬화)  


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

# 1. DRF(Django RestFramework)란? 

> **_REST 규격에 맞는 api 설계를 간편하게 해주는 django library_**    


### CRUD: HTTP method POST, GET, UPDATE & PATCH, DELETE

위 HTTP 메서드에 해당되는 CRUD 수행을 간편하게 해주는 외부 라이브러리다.

장고의 기본 모듈이 아닌 외부 라이브러리지만, 잘 만들어져서 다들 다양하게 사용한다.

- DRF는 장고 공식문서에서는 내용을 확인할 수 없고 별도의 사이트가 존재한다.
	- [django-rest-framework.org/](http://django-rest-framework.org/) 


<br>

### DRF installation

- 설치 명령어

	```python
	pip install djangorestframework
	```

- 그리고, `settings/base.py` 의 `INSTALLED_APPS`에 추가한다.  

	```python
	INSTALLED_APPS = [
		...
		"rest_framework",
	]
	```

<br>

---

# 2. 직렬화와 역직렬화

> **_- 직렬화(Serialization): instance -> dict(json) -> bystring_**  
> **_- 역직렬화(Deserialization): bystring -> dict(json) -> instance_**    

### 직렬화(Serialization)
데이터를 네트워크를 통해서 전달한다는 의미는 **API로 전달하는 것** 이라 생각하면 된다.  

그런데 이 데이터를 웹 애플케이션은 https, http 프로토콜을 사용하여 전달하며, 이는 HTTP message의 body 부분에 담아서 보낸다는 걸 의미한다.  

이때 담기 위해서는 json 형태여야하고, 이를 네트워크 기계를 통해서 전달해야하므로 최종적으로 bystring 형태로 보내야 한다.  

이 네트워크를 통해 전달하기 위해서 수행되는 **instance -> json: dictionary -> bystring**  과정을 직렬화라고 한다.  


### 역직렬화(Deserialization)

api를 통해서 전달받은 데이터를 원하는 객체 타입으로 전환하는 과정을 말한다.

**bystring -> json:dictionary -> instance**


### serialization.py

직렬화를 수행하기 위해서는 views.py, urls.py, models.py, forms.py 처럼 `serialization.py` 가 존재해야 한다.


- settings.py 가 분기되어 있을 때라면 다음과 같이 실행한다.

	```yaml
	python mange.py serialization --settings=config.settings.develop
	```

## 코드를 통해 이해해보기 

그러면 코드를 통해 직렬화를 간단히 이해해보자. 

- **Serializer 작명법**
	- 아래 코드에서 LessonSerializer는 `<model 명>Serializer`로 작성하는 작성법에 맞춰서 작성한 것이다. 

- 아래 내용은 DRF 튜토리얼 내용을 학습하면서 정리한 내용이다. 

### 직렬화


- instance 생성하기

	```python
	from snippets.models import Snippet
	from snippets.serializers import SnippetSerializer
	from rest_framework.renderers import JSONRenderer
	from rest_framework.parsers import JSONParser

	## instance 생성 
	>>> snippet = Snippet(code='foo = "bar"\n')
	>>> snippet.save()

	>>> snippet = Snippet(code='print("hello, world")\n')
	>>> snippet.save()
	```

- instance를 dictionary json으로 전환: Serializer

	```python
	>>> serializer = SnippetSerializer(snippet)
	>>> print(type(serializer))
	<class '<app 이름>.serializer.SnippetSerializer'>

	>>> serializer.data
	{'id': 2, 'title': '', 'code': 'print("hello, world")\n', 'linenos': False, 'language': 'python', 'style': 'friendly'}

	>>> print(type(serializer.data))
	<class 'rest_framework.utils.serializer_helpers.ReturnDict'>
	```

- dictionary json을 bystring으로 전환: JSONRender
	- 네크워크를 통해서 전달될 때는 JSON 형태로 전달되므로 JSONRenderer를 사용한다. 

	```python
	>>> content = JSONRenderer().render(serializer.data)

	>>> print(type(content))
	<class 'bytes'>

	>>> print(content)
	b'{"id":null,"title":"","code":"foo=\\"bar\\"\\n","linenos":false,"language":"python","style":"friendly"}'
	```

<br>

### 역직렬화

- bystring을 dictionary json으로 전환: JSONParser

	```python
	>>> import io
	>>> stream = io.BytesIO(content)
	>>> stream
	<_io.BytesIO object at 0x7fcc89a27f40>

	>>> data = JSONParser().parse(stream)
	>>> data
	{'id': None, 'title': '', 'code': 'foo="bar"\n', 'linenos': False, 'language': 'python', 'style': 'friendly'}

	>>> print(type(data))
	<class 'dict'>
	```

- dictionary json을 다시 instance로 전환: Serializer

	```python
	>>> serializer = SnippetSerializer(data=data)
	>>> print(type(serializer))
	<class 'quickstart.serializer.SnippetSerializer'>

	>>> serializer.is_valid()
	True
	
	>>> serializer.validated_data
	OrderedDict([('title', ''), ('code', 'foo="bar"'), ('linenos', False), ('language', 'python'), ('style', 'friendly')])

	>>> print(type(serializer.validated_data))
	<class 'collections.OrderedDict'>
	```

- formView에서 항상 valid 체크를 했었다. 이것이 통과되면 값을 뽑아낸다.
- 딕셔너리로 받은 것을 유효성 체크 후, 모델로 받은 것이다.


## instance가 아닌 querysets을 직렬화하기  

> **_쿼리셋을 직렬화할 때는 옵션으로 'many=True'를 사용한다._** 

```python
>>> serializer = SnippetSerializer(Snippet.objects.all(), many=True)
>>> serializer.data

```

<br>

---

# Reference

- [러닝스푼즈 - 나노디그리 Python & Django backed course](https://learningspoons.com/course/detail/django-backend/) 
- [Django REST framework](https://www.django-rest-framework.org/tutorial/)