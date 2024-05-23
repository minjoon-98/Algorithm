import sys
input = sys.stdin.readline

# 주어진 숫자 n에서 특정 소수 factor가 몇 번 곱해졌는지 세는 함수
def count_factors(n, factor):
    count = 0
    while n > 0:
        n //= factor
        count += n
    return count

n, m = map(int, input().split())

# nCr에서 2와 5의 개수를 각각 구함
two_count = count_factors(n, 2) - count_factors(n - m, 2) - count_factors(m, 2)
five_count = count_factors(n, 5) - count_factors(n - m, 5) - count_factors(m, 5)

# 끝자리 0의 개수는 두 값 중 최소값
print(min(two_count, five_count))