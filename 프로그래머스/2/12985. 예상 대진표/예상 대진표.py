# 백준 1057 토너먼트

import sys
import math

def solution(n,a,b):
    answer = 0

    while n > 1:
        answer = math.ceil(math.log2(n)) # N강 토너먼트에서 나오는 최대 판수
        n = pow(2, answer) # N이 2의 제곱수가 아닌 경우, N을 2의 제곱수로 올림
        half_n = n // 2 # 절반을 나눈 값

        if a <= half_n and b <= half_n: # a와 b 둘 다 절반을 나눈 값보다 작은 구역에 있다면
            n = half_n # N의 절반값의 토너먼트로 간주
        elif a > half_n and b > half_n: # a와 b 둘 다 절반을 나눈 값보다 큰 구역에 있다면
            n -= half_n # N의 절반값의 토너먼트로 간주
            a -= half_n # N의 절반값을 뺀 값으로 생각한다 
            b -= half_n # N의 절반값을 뺀 값으로 생각한다
        else:
            break # 둘이 절반 기준으로 다른 곳에 위치한다면, 최대 판수를 그대로 이행하게 된다

    return answer