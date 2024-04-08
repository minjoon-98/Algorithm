import sys

N = int(sys.stdin.readline())
time = [tuple(map(int, sys.stdin.readline().split())) for _ in range(N)]
time.sort(key=lambda x: (x[1], x[0]))  # 회의가 끝나는 시간을 기준으로 오름차순 정렬합니다.

count = 0
end_time = 0  # 초기 끝나는 시간을 0으로 설정합니다.

for start, finish in time:
    if start >= end_time:  # 회의가 끝나는 시간보다 시작 시간이 더 늦거나 같으면
        count += 1
        end_time = finish  # 끝나는 시간을 업데이트합니다.

print(count)