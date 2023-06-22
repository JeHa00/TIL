from collections import deque

def main():
    n = int(input())
    a = list(map(int, input().split(" ")))
    ret = [-1 for _ in range(n)]
    s = deque([])
    for i in range(n):
        while len(s) != 0 and a[s[-1]] < a[i]:
            ret[s[-1]] = a[i]
            s.pop()
        s.append(i)

    for i in range(n):
        print(ret[i], end=" ")

if __name__ == "__main__":
    main()
