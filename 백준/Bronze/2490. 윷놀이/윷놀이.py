import sys
input = sys.stdin.readline

def determine_yut(yut_state):
    count_0 = yut_state.count(0)  # 배(0)의 개수
    count_1 = yut_state.count(1)  # 등(1)의 개수

    # 결과에 따라 반환할 문자열 결정
    if count_0 == 1:
        return 'A'  # 도
    elif count_0 == 2:
        return 'B'  # 개
    elif count_0 == 3:
        return 'C'  # 걸
    elif count_0 == 4:
        return 'D'  # 윷
    elif count_0 == 0:
        return 'E'  # 모

results = []
for _ in range(3):
    yut_state = list(map(int, input().split()))
    results.append(determine_yut(yut_state))

for result in results:
    print(result)
