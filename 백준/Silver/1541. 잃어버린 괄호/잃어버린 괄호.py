import sys

arr = sys.stdin.readline().split('-')
answer = 0

for i in arr[0].split('+'):
    answer += int(i)
for i in arr[1:]:
    for j in i.split('+'):
        answer -= int(j)

print(answer)