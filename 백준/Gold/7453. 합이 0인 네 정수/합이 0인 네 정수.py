import sys
input = sys.stdin.readline

def count_zero_sum_pairs(n, A, B, C, D):
    from collections import defaultdict
    
    # 배열 A와 B의 모든 쌍의 합을 저장할 딕셔너리 생성
    sum_ab = defaultdict(int)
    
    # 배열 A와 B의 모든 쌍의 합을 계산하고 sum_ab 딕셔너리에 저장 (a ∈ A, b ∈ B)
    for a in A:
        for b in B:
            sum_ab[a + b] += 1
    
    count = 0
    
    # 배열 C와 D의 모든 쌍에 대해 해당 쌍의 합의 음수가 sum_ab 딕셔너리에 존재하는지 확인 (c ∈ C, d ∈ D)
    for c in C:
        for d in D:
            target = -(c + d)
            if target in sum_ab:
                count += sum_ab[target]
    
    return count

n = int(input().strip())
A = []
B = []
C = []
D = []

# 배열 A, B, C, D를 입력받음
for _ in range(n):
    a, b, c, d = map(int, input().strip().split())
    A.append(a)
    B.append(b)
    C.append(c)
    D.append(d)

# 합이 0인 쌍의 개수를 계산
result = count_zero_sum_pairs(n, A, B, C, D)
print(result)