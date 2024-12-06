import sys
input = sys.stdin.readline

def calculate(operation, operand, number):
    if operation == '+':
        return operand + number
    elif operation == '-':
        return operand - number
    elif operation == '*':
        return operand * number
    else:  # operation == '/'
        if operand < 0:
            return -(abs(operand) // number)  # 음수 나눗셈 처리
        return operand // number

N = int(input())
dp = [1, -float('inf')]  # dp[0]: 스킵하지 않은 최대값, dp[1]: 스킵한 최대값

for _ in range(N):
    a, b = input().rstrip().split()
    op1, num1 = a[0], int(a[1:])
    op2, num2 = b[0], int(b[1:])

    # 현재 상태에서 가능한 모든 경우의 수 계산
    if dp[0] > 0:
        next_no_skip = max(calculate(op1, dp[0], num1), calculate(op2, dp[0], num2))
    else:
        next_no_skip = -float('inf')

    next_skip = max(dp[0],  # 이전까지 스킵하지 않은 상태를 스킵한 상태로 변경
                    max(calculate(op1, dp[1], num1), calculate(op2, dp[1], num2)))  # 이미 스킵한 상태에서 계산

    dp = [next_no_skip, next_skip]

    if max(dp) <= 0:  # 게임 종료 조건
        print("ddong game")
        break
else:
    print(max(dp))



# # 오답
# import sys
# input = sys.stdin.readline

# def calculate(operand, operation, number):
#     if operation == '+':
#         return int(operand) + int(number)
#     elif operation == '-':
#         return int(operand) - int(number)
#     elif operation == '*':
#         return int(operand) * int(number)
#     elif operation == '/':
#         return int(operand) // int(number)

# N = int(input())
# people = 1

# dp = [[-float('inf'), -float('inf')] for _ in range(N + 1)]

# dp[0] = [1, 1]

# for i in range(1, N+1):
#     c1, c2 = map(str, input().split())
#     op1, n1 = c1.strip()
#     op2, n2 = c2.strip()

#     option1 = calculate(dp[i-1][0], op1, n1)
#     option2 = calculate(dp[i-1][0], op2, n2)
#     option3 = max(dp[i-1][0], calculate(dp[i-1][1], op1, n1), calculate(dp[i-1][1], op2, n2))
    
#     dp[i] = [max(option1, option2), option3]

#     if max(dp[i]) <= 0:
#         print("ddong game")
#         exit()

# print(max(dp[N]))