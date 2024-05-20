import sys
input = sys.stdin.readline

N = int(input())

num = [[0,1,2,3,4,5,6,7,8,9]]

for k in range(1, 11):
    decrease_num = []
    for i in range(k, 10):
        for j in (num[k-1]):
            if  j < i*pow(10,k-1):
                decrease_num.append(i*pow(10,k) + j)
    num.append(decrease_num)

for i in range(10):
    if len(num[i]) <= N:
        N -= len(num[i])
    else:
        print(num[i][N])
        exit()
print(-1)