T = int(input())  # 테스트 케이스 수

# 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N, M = map(int, input().split())  # N과 M 입력 받기
    Ai = list(map(int, input().split()))  # Ai 배열 입력 받기
    Bj = list(map(int, input().split()))  # Bj 배열 입력 받기

    # 더 긴 배열을 기준으로 배치하면서 곱의 합을 계산
    max_sum = -float('inf')  # 최댓값 초기화 (가능한 가장 작은 값으로 시작)

    # 더 긴 배열을 기준으로 이동하면서 계산
    if N >= M:
        for offset in range(N - M + 1):  # Ai 배열을 기준으로 Bj 배열을 이동
            current_sum = 0
            for i in range(M):
                current_sum += Ai[offset + i] * Bj[i]
            max_sum = max(max_sum, current_sum)
    else:
        for offset in range(M - N + 1):  # Bj 배열을 기준으로 Ai 배열을 이동
            current_sum = 0
            for i in range(N):
                current_sum += Ai[i] * Bj[offset + i]
            max_sum = max(max_sum, current_sum)

    print(f"#{test_case} {max_sum}")  # 결과 출력
