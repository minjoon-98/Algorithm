from collections import deque
import sys
input = sys.stdin.readline

N = int(input())
RB_balls = list(input().strip())
RB_balls_1 = deque()

for i in range(N):
    RB_balls_1.append(RB_balls[i])

last_color = RB_balls[-1]
first_color = RB_balls[0]

for i in range(N-1, -1, -1):
    if RB_balls[i] == last_color:
        RB_balls.pop()
    else:
        break

while RB_balls_1:
    if RB_balls_1[0] == first_color:
        RB_balls_1.popleft()
    else:
        break

count_R = RB_balls.count('R')
count_B = RB_balls.count('B')

count_R_1 = RB_balls_1.count('R')
count_B_1 = RB_balls_1.count('B')


answer = min(count_R, count_B, count_R_1, count_B_1)

print(answer)