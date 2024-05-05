import sys
input = sys.stdin.readline

N = int(input())
planet = list(map(int, input().split()))
speed = planet[N-1]

for i in range(N-2, -1, -1):
    if speed < planet[i]: # speed보다 행성 속력이 크다면
        speed = planet[i] # speed를 현재 행성 속력으로 업데이트
    else: # speed가 더 크다면
        if speed % planet[i] != 0: # 정수배가 되지 않는다면 
            speed = ((speed//planet[i])+1) * planet[i] # 배수이면서 최소값으로 업데이트

print(speed)