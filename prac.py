import time  

def perf_clock(func):
    def perf_clocked(*args):
        st = time.perf_counter()
        result = func(*args)
        et = time.perf_counter() - st 
        name = func.__name__  
        arg_str = ', '.join(repr(arg) for arg in args)
        print(type(arg_str), arg_str)
        print('[%0.5fs] %s(%s) -> %r' % (et, name, arg_str, result))
        return result 
    return perf_clocked 

@perf_clock 
def time_func(seconds):
    time.sleep(seconds)

@perf_clock 
def sum_func(*numbers):
    return sum(numbers)

time_func(1.5)


sum_func(100,200,300,400,500)