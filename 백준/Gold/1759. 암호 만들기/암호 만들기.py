import sys
input = sys.stdin.readline

# L: 암호의 길이, C: 주어진 문자들의 개수
L, C = map(int, input().split())

# 주어진 문자들을 사전순으로 정렬
character = sorted(input().split())

# 암호를 저장할 리스트
answer = []

# 현재 만든 암호가 조건을 만족하는지 확인하는 함수
def is_valid(password):
    # 모음 집합
    vowels = {'a', 'e', 'i', 'o', 'u'}
    # 암호에서 모음의 개수를 센다
    num_vowels = sum(1 for char in password if char in vowels)
    # 자음의 개수는 전체 길이에서 모음의 개수를 뺀 값
    num_consonants = len(password) - num_vowels
    # 모음이 최소 한 개 이상, 자음이 최소 두 개 이상인지 확인
    return num_vowels >= 1 and num_consonants >= 2

# DFS를 이용해 암호를 생성하는 함수
def dfs(depth, start):
    # 목표 길이에 도달했을 때
    if len(answer) == L:
        # 조건을 만족하는 경우 출력
        if is_valid(answer):
            print("".join(answer))
        return
    # 주어진 문자들 중 하나를 선택
    for i in range(start, C):
        answer.append(character[i])  # 선택한 문자를 추가
        dfs(depth + 1, i + 1)         # 다음 깊이로 재귀 호출
        answer.pop()                  # 되돌아와서 다음 문자를 선택하기 위해 제거
    
# DFS 탐색 시작
dfs(0, 0)
