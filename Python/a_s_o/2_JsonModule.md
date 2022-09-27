# 0. Introduction 


- `async` 와 `bs4` module을 사용하여 한 특정 웹 사이트를 스크랩핑하는 코드를 작성하고 있다.

- 그 과정에서 Error가 발생하여 이에 대한 원인과 해결책을 기록두는 것뿐만 아니라, 모르는 것들에 대해 학습한 걸 기록한다. 

- 이번에는 **'Serialization(직렬화)'** 란 개념과 Flat data, Nested data 무엇인지 알아보자. 마지막으로, `json.loads()` 와 `json.dumps()`를 알아본다. 

- 이후 이 주제와 관련되어 새롭게 학습한 것이 있다면 이 포스팅에 정리한다.  

<br>

---

# 1. Serialization(직렬화)

[python docs-guide: Data Serialization](https://docs.python-guide.org/scenarios/serialization/)와 [stackoverflow - What is data serialization?](https://stackoverflow.com/questions/11817950/what-is-data-serialization)에 따르면 data serialiation의 정의를 다음과 같이 내린다. 

> **_'Data seirialization(직렬화)'란 구조화된 데이터를 저장되거나 공유될 수 있는 한 가지 형식으로 전화하는 것으로 즉, 저장되거나 네트워크를 통해 보내지기 위해서 다른 형식으로 객체를 인코딩하는 걸 말한다. 이 과정에서 데이터의 사이즈를 줄이는 효과도 가진다._**


<br>

### 직렬화의 전환되는 형식: 두 가지

> **_Flat vs Nested data_**

전환되는 형식에는 **두 가지 종류** 가 있는데, 바로 **_Flat vs Nested data_** 다. 


각 데이터 예시는 [Flat vs. Nested Data Layer](https://community.tealiumiq.com/t5/TLC-Blog/Flat-vs-Nested-Data-Layer/ba-p/31395)을 참고하자.


간단히 말하자면 이와 같다.

> **_Flat은 계층화되어 있지 않은 데이터 종류로 one level의 properties나 "key: value" 쌍으로 구성된 데이터를 의미하고, nested는 계층화되어 있어 몇 개의 level로 구성된다._**

각 데이터 타입에 대한 장단점도 위 링크에 있으니 참고하자. 


<br>

### Flat data

파이썬은 data에 flat data가 포함되어 있다면 직렬화하기 위해 `repr` method나 `csv` module을 제공한다.  

- [[TIL]Python basic 20: with open as](https://jeha00.github.io/post/python/python_basic_20_filewriteread_1/)
- [[TIL]Python basic 21: csv.read, write](https://jeha00.github.io/post/python/python_basic_21_filereadwrite_2/)
- [[TIL] Python basic 25: _ _str _ _ vs _ _repr _ _](https://jeha00.github.io/post/python/python_basic_25_str_repr/)



<br>

### Nested data 

Nested data의 종류에는 YAML, JSON, XML 등이 있다. 이 type들 모두 `import yaml, json, xml` 을 통해 nested data를 다룰 수 있다.  

여기서는 `import json`에 대한 것만 다음 챕터에서 다룰 것이다.  


<br>


---


# 2. json.loads 와 json.dumps


JSON이란 무엇인지 학습한 후, `json.loads()`와 `json.dump()`에 대해 알아본다. 

먼저 두 함수에 대해 그림으로 간략히 표현하면 이와 같다.


![image](https://user-images.githubusercontent.com/78094972/182030027-6d2756d3-0e0f-42ec-a36e-14376bff605b.PNG)

- 이미지 출처: [What is the difference between json.loads() and json.dumps() ?](https://www.educative.io/answers/what-is-the-difference-between-jsonloads-and-jsondumps) 



### JSON이란?

> JavaScript Object Notation의 약어로, `{ }` 중괄호(curly braces)로 둘러쌓여진 것으로, key와 value 쌍으로 쓰여진다.  

- python에서는 dictionary data type을 생각해보자.  

#### json.loads

> string을 json Object 즉, dictionary 형태로 바꾼다.

```python
BASE_DIR = Path(__file__).resolve().parent
secrets_path = str(BASE_DIR / "secrets.json")
with open(secrets_path) as f:
    secret = f.read()
    secret2 = json.loads(secret)
    print(f"secret type: {type(secret)} \nsecret2 type: {type(secret2)}")
```

출력 결과는 다음과 같다. 

```python
secret type: <class 'str'> 
secret2 type: <class 'dict'>
```

- 즉, `.read()` 는 json 파일은 string 문자열로 읽어오고, `json.loads()`는 이를 dictionry인 `json` object로 읽어온다.   


그러면 read()와 dumps() 의 차이는 무엇일까??? 
둘 다 string 을 반환하지만, dumps는 json 형태의 string으로 바꾸고, read()는 작성된 형태 그대로 읽어온다.

#### json.dumps

> json Object를 string으로 바꿔준다. 

- 위에 코드를 이어서 사용해보자. 

```python
dump = json.dumps(secret2)
print(type(dump))

# 결과
<class 'str'>
```

- 즉, `json` object로 넘겨주면 데이터를 추출하여 이를 string type으로 반환한다는 걸 알 수 있다.  

<br>

---
# 3. json.load 와 json.dump

python 공식 문서를 보면 load, loads, dump, dumps 의 인자에 대해 다음과 같이 설명한다.

### json.load(fp), json.loads(fp) -> Python object

- json.load(fp)에서의 fp
    - a .read()-supporting **text file or binary file** containing a JSON document
    - json 파일을 의미

- json.loads(fp)에서의 fp
    - a **str, bytes or bytearray instance** containing a JSON document
    - json 형식의 문자열을 의미 

![image](https://s1.md5.ltd/image/364d7e35ffaa0788c6f057031198bba7.png)


<br>


### json.dump(obj), json.dumps(obj) -> fp

- json.dump(obj)
    - obj as a JSON formatted stream to fp (a .write()-supporting file-like object)
    - json 파일로 변환

- json.dumps(obj)
    - Serialize obj to a JSON formatted str 
    - json 형식의 문자열로 변환


<br>

---


# Reference

- [Data Serialization](https://docs.python-guide.org/scenarios/serialization/)
- [Flat vs. Nested Data Layer](https://community.tealiumiq.com/t5/TLC-Blog/Flat-vs-Nested-Data-Layer/ba-p/31395)
- [json - JSON 인코더와 디코더](https://docs.python.org/ko/3/library/json.html)
- [stackoverflow - What is data serialization?](https://stackoverflow.com/questions/11817950/what-is-data-serialization)
- [[TIL]Python basic 20: with open as](https://jeha00.github.io/post/python/python_basic_20_filewriteread_1/)
- [[TIL]Python basic 21: csv.read, write](https://jeha00.github.io/post/python/python_basic_21_filereadwrite_2/)
- [[TIL] Python basic 25: _ _str _ _ vs _ _repr _ _](https://jeha00.github.io/post/python/python_basic_25_str_repr/)
- [What is the difference between json.loads() and json.dumps() ?](https://www.educative.io/answers/what-is-the-difference-between-jsonloads-and-jsondumps)