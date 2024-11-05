import math
import sys
input = sys.stdin.readline

# 소수 판별 함수
def is_prime(num):
    if num <= 1:
        return False
    if num == 2:
        return True
    if num % 2 == 0:
        return False
    for i in range(3, int(math.sqrt(num)) + 1, 2):
        if num % i == 0:
            return False
    return True

# 백트래킹으로 신기한 소수 찾기
def find_special_prime(curr_num, depth, N, result):
    if depth == N:
        result.append(curr_num)
        return
    
    # 다음 자리를 1부터 9까지 추가하면서 재귀 호출
    for digit in range(1, 10, 2):  # 1, 3, 5, 7, 9 만 추가
        new_num = curr_num * 10 + digit
        if is_prime(new_num):  # 현재까지 자른 숫자가 소수라면
            find_special_prime(new_num, depth + 1, N, result)


N = int(input())
result = []
    
# 1자리 숫자부터 시작 (소수만 시작)
for digit in [2, 3, 5, 7]:  # 처음에 1자리 소수만 시작
    find_special_prime(digit, 1, N, result)
    
# 결과 오름차순 정렬 후 출력
result.sort()
for num in result:
    print(num) 