import sys
input = sys.stdin.readline

def find_minimum_rooms(N):
    # N이 1인 경우는 중앙의 1번 방이므로 바로 1을 반환
    if N == 1:
        return 1
    
    # 이진 탐색 범위 초기화
    left, right = 1, N
    
    while left <= right:
        # 중간값 계산
        mid = (left + right) // 2
        
        # mid 레벨의 최대 방 번호 계산
        # max_room = [1]
        # for i in range(1, mid+1):
        #     max_room.append(max_room[i-1] * 6*i)
        max_room = 1 + 3 * mid * (mid + 1)
        if N <= max_room:
            # N이 max_room보다 작거나 같다면, 더 작은 범위 탐색
            right = mid - 1
        else:
            # N이 max_room보다 크다면, 더 큰 범위 탐색
            left = mid + 1
    
    # left는 최종적으로 N이 속하는 레벨을 가리킴
    # 1번 방을 포함하여 레벨을 계산하므로 1을 더함
    return left + 1

# 입력 값을 읽고 함수 호출 후 결과 출력
N = int(input())
print(find_minimum_rooms(N))
