import sys
input = sys.stdin.readline

N = int(input())
func = list(input().strip())
answer = -sys.maxsize

def calc(a, oper, b):
    if oper == '+':
        num = a + b
    elif oper == '*':
        num = a * b
    elif oper  == '-':
        num = a - b
    return num

def dfs(idx, val):
    global answer
    if idx == N - 1:
        answer = max(answer, val)
        return

    if idx + 2 < N:
        # 괄호가 쳐져있지 않을 때
        next_val = calc(val, func[idx + 1], int(func[idx + 2])) 
        dfs(idx + 2, next_val)

    if idx + 4 < N:
        # 괄호가 쳐져있을 때
        next_next_val = calc(int(func[idx+2]), func[idx+3], int(func[idx+4])) # 괄호 처리(다음번 수 (+, *, -) 다다음번 수)
        next_val = calc(val, func[idx + 1], next_next_val) # 다음번에 나오는 수
        dfs(idx + 4, next_val)

dfs(0, int(func[0]))
print(answer)