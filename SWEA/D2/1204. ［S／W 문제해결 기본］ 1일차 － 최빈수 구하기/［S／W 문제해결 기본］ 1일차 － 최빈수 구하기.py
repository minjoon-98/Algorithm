#import sys
#sys.stdin = open("input.txt", "r")
from collections import defaultdict

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    test_case_number = int(input()) # test_case_number == test_case
    scores = list(map(int, input().split()))
    score_count = defaultdict(int)
    for score in scores:
        score_count[score] += 1
    
    max_count = max(score_count.values())
    mode_scores = [score for score, count in score_count.items() if count == max_count]
    answer = max(mode_scores)
    print(f'#{test_case} {answer}')
    # ///////////////////////////////////////////////////////////////////////////////////
# mode : 최빈값 (통계학에서 주어진 데이터 집합에서 가장 자주 나타나는 값)