'''
n개의 원소로 합이 s인 집합을 만들 때, 각 원소의 값이 가능한 한 비슷하도록 만드는 것이 곱을 최대화하는 방법임
따라서 각 원소는 가능한 한 같게 나누고, 나머지 만큼 큰 값으로 채워 합이 정확하게 s가 되도록 하며 곱이 최대가 되도록 만든다.
'''

def solution(n, s):
    # 합이 n보다 작은 경우, 자연수로 n개의 원소를 만들 수 없음
    if s < n:
        return [-1]
    
    # 몫과 나머지 계산
    quotient = s // n  # 각 원소의 기본 값
    remainder = s % n  # 나머지 값 (더해야 할 값의 개수)
    
    # 결과 집합을 생성
    result = [quotient] * (n - remainder) + [quotient + 1] * remainder
    
    return result