# Intro

> 1. [Synchronous VS Asynchronous](#1-synchronous-vs-asynchronous)
> 2. [Blocking VS Non-Blocking](#2-blocking-vs-non-blocking)
> 3. [GIL](#3-gilglobal-interface-lock)
> 4. [current.futures](#4-concurrentfutures)
> 5. [Exercise - PoolExecutor](#5-exercise---poolexecutor)
> 6. [Exercise - wait, as_completed](#6-exercise---wait-ascompleted)

<br>

- 이번 내용은 파이썬 고급 과정 [고수가 되는 파이썬 : 동시성과 병렬성 문법 배우기 Feat. 멀티스레딩 vs 멀티프로세싱 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%99%84%EC%84%B1-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90/dashboard) 에서 자세히 배운다고 하여 깊이 있게 들어가진 않았다.
- 이런 게 있구나 정도로만 파악하자.

<br>

---

# 1. Synchronous VS Asynchronous

- 소제목에 있는 두 영단어는 접두어와 어원으로 분석하여 접근하면 쉽다.
- syn는 'together'를, chrono는 'time'을 의미한다.
- 하지만 Asynchronous 는 접두어로 'a'가 붙었는데, 이는 부정의 의미를 가진다.
- 시간을 함께 맞추는 것 vs 시간을 함께 맞추지 않는 것의 의미로 해석할 수 있다.

- `Synchronous`는 단어적 의미를 기반으로 바라보면 2가지 이상의 대상이 서로 시간을 맞춰 해동하는 걸 의미한다.

  - A 작업이 끝난 후에야 B 작업이 시작되는 걸 말한다.
  - 끝나고 나서 시작되는 것처럼 시간을 맞춘다.

- `Asynchronous`는 서로 시간을 맞추지 않는 걸 의미한다.

  - A에 작업을 조금 걸어넣고, B에도 조금 시작하여 `동시성`이 실행되는 걸 의미한다.

<br>

---

# 2. Blocking vs Non-Blocking

- Blocking이란 직접 제어할 수 없는 대상의 작업이 끝날 때까지 제어권을 넘겨주지 않는 걸 말한다.

  - 예) 호출 함수가 I/O 을 요청했을 때, 이 요청이 완료될까지 아무 일도 하지 못한 채 기다리는 걸 말한다.

- Non-blocking이란 non이 붙여졌듯이 blocking의 반대 의미다.
- 요청이 완료될 때까지 기다릴 필요 없이 바로 자신의 작업을 할 수 있는 상태를 말한다.

- blocking 현상으로 CPU 및 resource 낭비를 하고 있을 때, `동시성` 활용 작업으로 낭비를 방지한다.
- 파이썬은 단일 쓰레드로 해도 충분히 빠르다.
- 특히, 비동기 작업과 적합한 프로그램일 경우, 압도적으로 성능이 향상된다.

<br>

---

# 3. GIL(Global Interpreter Lock)

- GIL은 Python에만 존재하는 것으로, Global Interface Lock in CPython을 의미한다.
- GIL은 두 개 이상의 thread가 동시에 실행될 때(context switching 상황) 하나의 자원에 액세스하는 경우, 문제점을 방지하기 위해 GIL을 실행하여 resource 전체에 lock을 건다.
- 그래서 multi-thread할 때, GIL 때문에 single-thread보다 느린 경우가 많다.

- GIL을 우회하기 위해서는 반드시 multi-processing을 사용하거나 CPython을 사용하면 GIL이 걸리지 않는다.
- 하지만 이 때는 동기화를 직접해야 한다.

<br>

---

# 4. Concurrent.futures

<br>

## 4.1 Thread Pool이란??

- server 요청 model에 `Thread per request model` 이다.
  - 즉, 요청마다 새로운 thread를 할당하여 처리한다면, 요청이 들어올 때마다 thread를 생성하고 요청이 완료되면 thread를 삭제해야 한다.
- 이 방식의 문제는 요청마다 스레드 생성에 소요되는 시간 때문에, 요청 처리가 더 오래 걸린다.

- 만약 처리 속도 < 요청 속도이면??

  - **thread가 계속 생성되어, thread 수는 증가한다.**
  - 이에 따라 context switching이 더 자주 발생한다.
  - CPU overhead 증가로 CPU time이 낭비된다.
  - 결국, 어느 순간 _서버 전체가 응답 불가능_ 한 상태에 빠진다.
  - 또한, _메모리가 점점 고갈_ 된다.

- 그래서 미리 최대 몇 개의 thread 묶음을 만들어놓는다. 이를 `thread pool`이라 한다.

  - API 요청이 들어오면 thread pool 안에 일이 없는 thread를 할당한다.
  - 요청이 완료되면 thread는 삭제하지 않고, 이 pool에 다시 들어온다.
  - **미리 thread를 여러 개 만들어 놓고 재사용하여 thread 생성 시간을 절약한다.**
  - **또한, 제한된 개수의 thread를 응용** 하기 때문에, 무제한으로 생성되는 걸 방지한다.

- Thread pool 사례: **여러 작업을 동시 처리해야할 때**

  - task를 subtask로 나뉘어서 동시에 처리할 때
  - 순서 상관없이 동시실행이 가능한 task 처리할 때

- Thread pool 사용 시, **Tip**

  1. thread pool에 몇 개의 thread를 만들어 두는 게 적절한가???
     - CPU의 core 개수와 task의 성향에 따라 다르다.
     - CPU-bound task라면 코어 개수 만큼 혹은 그보다 몇 개 더 많은 정도
     - I/O-bound task라면 경험적으로 찾아야 한다.
  2. thread pool에서 실행될 task 개수에 제한이 없다면, **thread pool의 큐가 사이즈 제한이 있는지 반드시 확인할 것**

  - 큐에 요청이 무한정 쌓인다면???
  - **잠재적으로 메모리를 고갈시킬 수 있는 원인이 된다.**
  - 그래서 **_큐에 제한이 없다면 제한을 걸어야 한다._**

- process pool 또한, process를 미리 만들어두는 방식을 말한다.
- python에서는 `GIL` 때문에 CPU-bound task를 실행할려면, 동시에 CPU에서 여러 프로세스가 실행될 수 없으므로 thread pool을 사용해야 한다.

<br>

## 4.2 concurrent.futures package

- concurrent.futures package

  - 비동기 실행을 위한 API를 고수준으로 작성하고, '사용하기 쉽도록 개선'하여 나온 것
    - 전에는 'import processing', 'import thread' 으로 따로 호출해서 사용했었지만, 지금은 multi-threading / multi-processing API가 통일되어 매우 사용하기 쉽다.
    - 또한, low level로 하지 않아도 가능하다.
  - 실행 중인 작업 취소, 완료 여부 체크, 타임아웃 옵션, 콜백 추가, 동기화 코드를 매우 쉽게 작성할 수 있다.

- `concurrent.futures.ProcessPoolExecutor` 는 `multiprocessing.Process`와 동일한 역할을 한다.
- 하지만, `concurrent.futures.ProcessPoolExecutor`는 max_workers를 지정하여 fork할 process의 최대 갯수를 지정하는 게 가능하다.
- process pool을 사용하여 호출을 비동기적으로 실행하는 method
- `ThreadPoolExecutor`는 process pool이 아닌, thread pool을 사용한다.

  - thread pool이란 미리 만들어 놓은 thread 집단을 말한다.

<br>

---

# 5. Exercise - PoolExecutor

- `concurrent.futures` 사용법
  - ThreadPoolExecutor의 max_work 기본 값은 min(32, os.cpu_count() + 4)다.
    - 이 기본값은 I/O 병목 작업을 위해 최소 5개의 작업자를 유지한다.
  - ProcessPoolExecutor의 max_work 기본 값은 기계의 프로세서 수를 기본값으로 사용한다.
  - 아래 코드의 pattern을 외우자.
  - `map`은 작업 순서를 유지하고, 즉시 실행한다.
    - 4개를 동시에 시작해서 그 결과값을 받아온다.
    - 그런데 4개 중 한 작업이 1시간이 걸린다면 이 한 시간을 계속 기다린다.

```yml
> import os
> import time

# threading, multi-processing이 저수준으로 만들어져 있는 걸 사용한다.
> from concurrent import futures

# 1부터 각 성분값까지 합을 계산할 것이기 때문에, 4가지 작업을 동시에 실행한다.
# 실무에서는 각 값에 자신이 만든 함수를 넣어 사용해도 된다.
> WORK_LIST = [100000, 1000000, 10000000, 100000000]

# 인자 n에 위 WORK_LIST의 성분 값들이 하나씩 넘어와서 합이 계산된다.
# 그래서 총 동시에 4개가 실행된다.
# 누적 합계 함수(generator)
> def sum_generator(n):
>     return sum(n for n in range(1, n+1))

# worker의 수는 두 인수 중 작은 수로 한다.
# worker를 몇 개 할지 모르면 운영체제한테 맡긴다.
> def main1():
>    worker= min(10, len(WORK_LIST))

## 시작시간
# time.time은 현재 시간을 나타낸다.
>     start_tm = time.time()

## 결과 건수
# with문으로 해야 열고 닫는 게 자동적으로 이뤄진다.
# def main1 과의 차이점 (ThreadPoolExecutor)
>     with futures.ThreadPoolExecutor() as excutor:
>        result = excutor.map(sum_generator, WORK_LIST)

## 종료시간
# 현재 시간을 나타낸 거에, 시작 시간을 빼서 총 걸린 시간을 나타낸다.
>    end_tm = time.time() - start_tm

## 출력 포맷
# float 형 데이터 출력에 소수점 둘째짜리까지 출력한다.
>    msg = '\n Result -> {} Time : {:.2f}s'

>    print(msg.format(list(result), end_tm))


> def main2():
>     worker= min(10, len(WORK_LIST))

>     start_tm = time.time()

# def main2 와의 차이점 (ProcessPoolExcutor)
>     with futures.ProcessPoolExecutor() as excutor:
>        result = excutor.map(sum_generator, WORK_LIST)

>     end_tm = time.time() - start_tm

>     msg = '\n Result -> {} Time : {:.2f}s'
>     print(msg.format(list(result), end_tm))

## 실행
# 진입점을 알려줘야, multi-processing이 실행될 때 작업을 할 수 있다.
> if __name__ == '__main__':
>     main1()
>     main2()

 Result -> [5000050000, 500000500000, 50000005000000, 5000000050000000] Time : 7.61s

 Result -> [5000050000, 500000500000, 50000005000000, 5000000050000000] Time : 7.14s
```

- ProcessPool로 실행했을 때의 결과가 더 빠르다는 걸 확인했다.

<br>

- 그러면 동기적으로 진행했을 때의 시간을 측정해보자.

```yml
> WORK_LIST = [100000, 1000000, 10000000, 100000000]

# 시작 시간
> stm = time.time()
> for i in WORK_LIST:
>     sum(n for n in range(1, i+1))

# 종료 시간
> etm = time.time() - stm

> print('total time - {:0.2f}s'.format(etm))
8.43s
```

- thread pool과 process pool을 사용하는 방법이 더 빠르다는 걸 알 수 있다.

<br>

---

# 6. Exercise - wait, as_completed

- [Exercise - PoolExecutor](#5-exercise---poolexecutor)에서 한 예제는 총 4가지에 대한 합계를 구하는 동시성을 구현해보는 예제였다.
- 그러면 동시적으로 수행되는 각 작업이 성공했는지, 확인할 수도 있어야 하지 않을까??
- 이를 확인하기 위해, `wait`과 `as_completed` module에 대해 먼저 알아보자.

<br>

## 6.1 wait

> 작업의 양이 작아서 금방 끝나서, 한 번에 다 끝난 다음에 작업을 하기 원할 때 wait을 사용한다.

- 각 작업이 정해진 시간(timeout)을 벗어나면 실패로 간주하고 중단시킨다.
- 완료된 작업들만 추출하고 싶으면 `.done` method를 사용한다.
- 완료되지 않은 작업들, 실패한 작업들을만 추출하고 싶으면 `.not_done` method를 사용한다.
- 결과값을 원할 때는 `.result`를 사용한다.

<br>

## 6.2 as_completed

> 하나라도 끝난 다음에 바로 일을 시작할려고 할 때, 즉 real time 작업을 하려고 할 때, as_completed를 사용한다.

- 먼저 끝나는 대로 순서를 반환한다. (yield)
- 결과값만 추출하기 원하면 `.result`를 사용한다.
- 완료된 작업들만 추출하고 싶으면 `.done` method를 사용한다.
- 완료되지 않은 작업들, 실패한 작업들을만 추출하고 싶으면 `.cancelled` method를 사용한다.

<br>

- 그러면 예시 코드로 적용해보자.

```yml
> import os
> import time

# 이번에는 쓸만큼만 호출한다.
> from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor, wait, as_completed

> WORK_LIST = [100000, 1000000, 10000000, 100000000]

> def sum_generator(n):
>     return sum(n for n in range(1, n+1))


> def main():
>    worker= min(10, len(WORK_LIST))

## 시작시간
>     start_tm = time.time()

>     future_list = []

## 결과 건수
# ThreadPoolExecutor로도 해보자.
> with ProcessPoolExecutor() as excutor:
>       for work in WORK_LIST:
>           furture = excutor.submit(sum_generator, work)
>           futures_list.append(future)
>           print('Scheduled for {} : {}'.format(work, future))
>           print()

## wait 결과 출력
# 스케쥴된 list를 인자로 받는다.
# timeout으로 정한 시간만큼 list를 기다린다.
# 이 정해진 시간 안에 일을 못 끝내면 실패로 간주하고 중단시킨다.
# sum_generator로 인한 연산 작업으로 future_list에 append될 때까지 7초를 넘으면 실패로 간주한다.
>       result = wait(futures_list, timeout = 7)

## 성공한 것들
>       print('Completed Tasks : ' + str(result.done))
>       print()

## 실패한 것들
>       print('Pending ones after waiting for 7seconds : ' + str(result.not_done))
>       print()

## 결과 값 출력
>       print([future.result() for future in result.done])
>       print()


## as_completed 결과 출력
>       for future in as_completed(futures_list):
>           result = future.result()
>           done = future.done()
>           cancelled = future.cancelled

## future 결과 확인
>         print('Future Result : {}, Done: {}'.format(result, done))
>         print('Future Cancelled : {}'.format(cancelled))

## 종료시간
>    end_tm = time.time() - start_tm

## 출력 포맷
>    msg = '\n Time : {:.2f}s'

>    print(msg.format(end_tm))


## 실행
> if __name__ == '__main__':
>     main()

## 결과 건수에서 예약된 작업들
Scheduled for 100000 : <Future at 0x204190e5490 state=running>

Scheduled for 1000000 : <Future at 0x20419161130 state=pending>

Scheduled for 10000000 : <Future at 0x20419161370 state=pending>

Scheduled for 100000000 : <Future at 0x204191610d0 state=pending>

## 성공한 것들
Completed Tasks : {<Future at 0x204190e5490 state=finished returned int>, <Future at 0x20419161130 state=finished returned int>, <Future at 0x20419161370 state=finished returned int>}

## 실패한 것들
Pending ones after waiting for 7seconds : {<Future at 0x204191610d0 state=running>}

## 결과값 출력
[5000050000, 500000500000, 50000005000000]

## 종료시간
 Time : 8.47s
```

- `submit(func, *args)`는 func에 \*args 를 넘겨주어 그 결과값을 return하는 함수다.

- `Futures` chapter는 더 공부를 해야할 필요를 다른 단원보다 많이 느낀다.  
- 파이썬에 대해 더 공부한 후, 파이썬 과정 level 4인 [고수가 되는 파이썬 : 동시성과 병렬성 문법 배우기 Feat. 멀티스레딩 vs 멀티프로세싱 (Inflearn Original)](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%99%84%EC%84%B1-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90/dashboard) 을 공부하면서 깊이 있게 학습해야겠다.

<br>

---

# Reference

- [인프런 파이썬 중급](https://www.inflearn.com/course/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%A4%91%EA%B8%89-%EC%9D%B8%ED%94%84%EB%9F%B0-%EC%98%A4%EB%A6%AC%EC%A7%80%EB%84%90)
- [Sync VS Async, Blocking VS Non-Blocking](https://velog.io/@codemcd/Sync-VS-Async-Blocking-VS-Non-Blocking-sak6d01fhx)
- [thread pool](https://www.youtube.com/watch?v=B4Of4UgLfWc)
