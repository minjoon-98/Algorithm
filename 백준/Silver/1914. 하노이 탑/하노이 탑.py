import sys
sys.setrecursionlimit(10 ** 6)

a = 1
b = 3
movehanoi = []

N = int(sys.stdin.readline())
def count(N) :
    if N == 1 :
        return 1
    else :
        return 2 * count(N - 1) + 1
def move(N, a, b) :

    if N > 1 :
        move(N - 1, a, 6 - a - b)

    movehanoi.append(str(a) + ' ' + str(b) + '\n')

    if N > 1 :
        move(N - 1, 6 - a - b, b)

if N > 20:
    print(count(N))
else:
    move(N, a, b)
    print(count(N))
    print(''.join(movehanoi))