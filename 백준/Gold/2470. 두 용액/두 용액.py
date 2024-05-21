import sys
input = sys.stdin.readline

N = int(input())
solutions = sorted(list(map(int, input().split())))

start = 0
end = len(solutions) - 1
result = sys.maxsize

while start < end:
    if solutions[start] + solutions[end] == 0:
        print(str(solutions[start])+' '+str(solutions[end]))
        exit()
    if abs(solutions[start] + solutions[end]) < result:
        answer_1 = solutions[start]
        answer_2 = solutions[end]
        result = abs(solutions[start] + solutions[end])
    if 0 < solutions[start] + solutions[end]:
        end -= 1
    if 0 > solutions[start] + solutions[end]:
        start += 1
        
print(str(answer_1)+' '+str(answer_2))