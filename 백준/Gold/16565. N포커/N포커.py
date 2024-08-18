import math
import sys
input = sys.stdin.readline

# N: 입력받는 카드의 수
N = int(input())

# 조합 계산 함수 (n개 중 k개를 선택하는 경우의 수)
def comb(n, k):
    # n < k 또는 k < 0이면 조합의 결과는 0
    if n < k or k < 0:
        return 0
    return math.comb(n, k)

# N포커의 경우의 수를 포함-배제 원리로 계산하는 함수
def count_poker(N):
    answer = 0
    # k는 1부터 13까지 반복 (포커의 수: 1개 ~ 13개)
    for k in range(1, 14):
        # 포함-배제 원리를 위해 부호를 번갈아가며 설정
        sign = (-1) ** (k+1)
        
        # k개의 포커를 선택하는 경우의 수 계산
        # comb(13, k): 13개의 숫자 중 k개를 선택하는 경우의 수
        # comb(52 - 4*k, N - 4*k): k개의 포커를 만든 후 남은 카드 중 N-4k장을 선택하는 경우의 수
        answer += sign * comb(13, k) * comb(52 - 4*k, N - 4*k)
    
    return answer

# N이 4보다 작은 경우 포커를 만들 수 없으므로 0을 출력
if N < 4:
    print(0)
else:
    # 포함-배제 원리로 계산된 결과를 10007로 나눈 나머지를 출력
    print(count_poker(N) % 10007)