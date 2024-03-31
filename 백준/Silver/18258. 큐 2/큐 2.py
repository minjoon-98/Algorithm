import sys
from collections import deque

que = deque([])
N = int(sys.stdin.readline())
for _ in range(N):
    order = sys.stdin.readline().split()
    if order[0] == 'push':
        que.append(order[1])
    if order[0] == 'pop':
        if len(que) == 0:
            print(-1)
        else:
            print(que.popleft())
    if order[0] == 'size':
        print(len(que))
    if order[0] == 'empty':
        if len(que) == 0:
            print(1)
        else:
            print(0)
    if order[0] == 'front':
        if len(que) == 0:
            print(-1)
        else:
            print(que[0])
    if order[0] == 'back':
        if len(que) == 0:
            print(-1)
        else:
            print(que[-1])