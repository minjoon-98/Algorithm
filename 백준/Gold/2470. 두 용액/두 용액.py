import sys
input = sys.stdin.readline

N = int(input())
solutions = list(map(int, input().split()))
solutions = sorted(solutions, key = lambda x : abs(x))
pt1 = 0
pt2 = 1
sum = 2000000000
while pt1 < len(solutions) and pt2 < len(solutions):
    if abs(solutions[pt1] + solutions[pt2]) < sum:
        answer = []
        answer.append(solutions[pt1])
        answer.append(solutions[pt2])
        sum = abs(solutions[pt1] + solutions[pt2])
    pt1 = pt2
    pt2 += 1
answer.sort()
print(*answer)