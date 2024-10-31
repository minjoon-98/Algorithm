# O(N^2 + M)
# O(N^2)로 팰린드롬 여부를 확인하는 DP 테이블을 만들어 두고, 각 질문에 대해서는 O(1)로 답변
# N이 최대 2,000일 경우, O(N^2)는 4,000,000 (400만)번의 연산
# M에 대해 O(M)을 더하면, 전체적으로 O(4,000,000+1,000,000) = O(5,000,000)

import sys
input = sys.stdin.readline

N = int(input())
numbers = list(map(int, input().split()))
M = int(input())

# 다이나믹 프로그래밍 배열 초기화
dp = [[False] * N for _ in range(N)]

# 길이가 1인 부분 문자열
for i in range(N):
    dp[i][i] = True

# 길이가 2인 부분 문자열
for i in range(N - 1):
    dp[i][i + 1] = (numbers[i] == numbers[i + 1])

# 길이가 3 이상인 부분 문자열
for length in range(3, N + 1):  # 길이를 3부터 N까지
    for start in range(N - length + 1):
        end = start + length - 1
        dp[start][end] = (numbers[start] == numbers[end]) and dp[start + 1][end - 1]
        # 팰린드롬은 시작과 끝 요소가 같고, start와 end 사이의 내부 요소들이 팰린드롬이여야함

# 질문 처리
for _ in range(M):
    S, E = map(int, input().split())
    print(1 if dp[S - 1][E - 1] else 0)



# # 시간 초과
# O(N * M)
# 각 질문마다 최대 N 길이의 부분 배열을 슬라이싱하고 확인
# M이 최대 1,000,000일 때, N이 2,000이라면 이 경우는 최대 2,000,000,000 (20억)번의 연산
# 따라서 시간초과 발생

# import sys
# input = sys.stdin.readline

# def isPalindrome(number):
#     if number == number[::-1]:
#         return True
#     else:
#         return False
    
# N = int(input())
# numbers = list(map(int, input().split()))
# M = int(input())
# sections = [tuple(map(int, input().split())) for _ in range(M)]

# for S, E in sections:
#     if isPalindrome(numbers[S-1 : E]):
#         answer = 1
#     else:
#         answer = 0
#     print(answer)