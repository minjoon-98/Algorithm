import sys
input = sys.stdin.readline

N = int(input())
RB_balls = list(input().strip())

last_color = RB_balls[-1]

for i in range(N-1, -1, -1):
    if RB_balls[i] == last_color:
        RB_balls.pop()
    else:
        break

count_R = RB_balls.count('R')
count_B = RB_balls.count('B')

answer = min(count_R, count_B)

print(answer)
