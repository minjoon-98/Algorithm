import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
S = list(map(str, input().strip()))

pattern = ['I']
for i in range(N):
    pattern.append('O')
    pattern.append('I')

# KMP
pS, pp = 1, 0
skip = [0] * (len(pattern) + 1)  # 건너뛰기 표

# 건너뛰기 표 만들기
skip[pS] = 0
while pS != len(pattern):
    if pattern[pS] == pattern[pp]:
        pS += 1
        pp += 1
        skip[pS] = pp
    elif pp == 0:
        pS += 1
        skip[pS] = pp
    else:
        pp = skip[pp]

# 검색하기
pS = pp = 0
count = 0
while pS != len(S):
    if S[pS] == pattern[pp]:
        pS += 1
        pp += 1
    elif pp == 0:
        pS += 1
    else:
        pp = skip[pp]
    if pp == len(pattern):
        count += 1
        pS -= (pp-2)
        pp = 0
        
print(count)