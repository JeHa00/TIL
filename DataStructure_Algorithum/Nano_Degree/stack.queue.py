import sys

number = int(sys.stdin.readline())

stack = []

for i in range(number):
    text = sys.stdin.readline().strip()

    if text.split()[0] == 'push':