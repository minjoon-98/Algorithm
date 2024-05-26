import sys
input = sys.stdin.readline

N, K, Q, M = map(int, input().split())

doze = [False] * (N + 3)
check = [False] * (N + 3)

# 자는 학생 정보
for i in map(int, input().split()):
    doze[i] = True

# 출석 정보를 체크
for i in map(int, input().split()):
    if doze[i]:
        continue
    for j in range(i, N + 3, i):
        if not doze[j]:
            check[j] = True

# 결석 여부를 누적 합으로 저장
prefix_absent = [0] * (N + 3)
for i in range(3, N + 3):
    prefix_absent[i] = prefix_absent[i - 1] + (not check[i])

# 질의 처리
for _ in range(M):
    S, E = map(int, input().split())
    print(prefix_absent[E] - prefix_absent[S - 1])