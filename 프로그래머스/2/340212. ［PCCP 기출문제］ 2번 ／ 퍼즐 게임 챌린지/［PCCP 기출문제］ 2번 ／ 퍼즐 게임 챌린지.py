def solution(diffs, times, limit):
    def can_solve_within_time(level):
        total_time = 0
        prev_time = 0
        
        for i in range(len(diffs)):
            diff = diffs[i]
            time_cur = times[i]
            
            if diff <= level:
                total_time += time_cur
            else:
                # 실패 횟수 계산
                fails = diff - level
                total_time += (fails * (time_cur + prev_time)) + time_cur
            
            # 다음 퍼즐을 위해 prev_time 업데이트
            prev_time = time_cur
            
            if total_time > limit:  # 제한 시간을 초과하면 조기 종료
                return False
        
        return total_time <= limit

    low, high = 1, max(diffs)
    
    while low < high:
        mid = (low + high) // 2
        if can_solve_within_time(mid):
            high = mid  # mid가 정답일 수 있으므로 high를 mid로 설정
        else:
            low = mid + 1

    return low  # low가 최소 숙련도