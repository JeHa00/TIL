## 0. Introduction



- `async` 와 `bs4` module을 사용하여 한 특정 웹 사이트를 스크랩핑하는 코드를 작성하고 있다.

- 그 과정에서 다음과 같은 Error다 발생하여 이에 대한 원인과 해결책을 기록해둔다.

```yml
Exception ignored in: <function _ProactorBasePipeTransport.__del__ at 0x0000028AAAD631F0>
Traceback (most recent call last):
  File "C:\Users\rudtl\.conda\envs\FASTAPI\lib\asyncio\proactor_events.py", line 116, in __del__
    self.close()
  File "C:\Users\rudtl\.conda\envs\FASTAPI\lib\asyncio\proactor_events.py", line 108, in close
    self._loop.call_soon(self._call_connection_lost, None)
  File "C:\Users\rudtl\.conda\envs\FASTAPI\lib\asyncio\base_events.py", line 751, in call_soon
    self._check_closed()
  File "C:\Users\rudtl\.conda\envs\FASTAPI\lib\asyncio\base_events.py", line 515, in _check_closed
    raise RuntimeError('Event loop is closed')
RuntimeError: Event loop is closed

```


<br>


---

## RuntimeError: Event loop is closed 

결론부터 바로 말하자면 다음과 같다. 

### 원인

- 파이썬 버전 3.8 부터는 윈도우에서 기본적으로 비동기 모듈에서 [ProactorEventLoop](https://docs.python.org/ko/3/library/asyncio-eventloop.html#asyncio.ProactorEventLoop)가 사용되어 발생되는 원인

### 해결책

- `asyncio.run()` 코드 줄 위에 `asyncio.set_event_loop_policy(asyncio.WindowsSelectorEventLoopPolicy())` 를 추가한다.  


### ProactorEventLoop

- ayncio를 사용한 Coroutine을 실행하기 위해서는 `asyncio.run()`을 사용해야 한다.  


-  이 때 asyncio에는 두 가지 이벤트 루프가 제공되는데, `SelectEventLoop` 와 `ProactorEventLoop` 다.  
    - 전자는 유닉스에서, 후자는 윈도우에서 사용되도록 구성된다.  

- 개발 OS로 윈도우를 사용하기 때문에, 후자 이벤트 루프로 인한 Error가 발생했다.  


<br>

---

## Reference 

- [공식문서: Python 정책 - Set_event_loop_policy](https://docs.python.org/ko/3/library/asyncio-policy.html#asyncio.DefaultEventLoopPolicy)
- [공식문서: ProactorEventLoop](https://docs.python.org/ko/3/library/asyncio-eventloop.html#asyncio.ProactorEventLoop)