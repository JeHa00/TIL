a = [i for i in range(1, 10)]

import os
import psutil

tmp = psutil.cpu_percent(percpu=False)  # 앞에서 한번 호출해야 다음에 제대로 계산?

mem = dict(psutil.virtual_memory()._asdict())

total_mem = round(mem['total']/(1024*1024))
used_mem = round(mem['used']/(1024*1024))
free_mem = round(mem['free']/(1024*1024))
free_percent = mem['percent']

print(f'Total Mem. : {total_mem} MB')
print(f'Used Mem.  : {used_mem} MB ({free_percent} %)')
print(f'Free Mem.  : {free_mem} MB ({100-free_percent} %)')

pid = os.getpid()             # 현재 파이썬 프로그램 pid
ps  = psutil.Process(pid)   # pid 프로세스 정보 
memoryUse = round(ps.memory_info()[0]/(1024*1024))  # 프로세스 사용 메모리(MB)
print(f'Program use: {memoryUse} MB')
print(f'Used CPU   : {psutil.cpu_percent(interval=1.0, percpu=False)} %')  # CPU 이용률 