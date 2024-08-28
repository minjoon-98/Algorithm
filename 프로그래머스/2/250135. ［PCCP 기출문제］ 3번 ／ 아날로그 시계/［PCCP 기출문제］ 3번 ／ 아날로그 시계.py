def time_to_seconds(h, m, s):
    """시간, 분, 초를 초로 변환합니다."""
    return h * 3600 + m * 60 + s

def solution(h1, m1, s1, h2, m2, s2):
    # 시작 시각과 종료 시각을 초 단위로 변환
    start_time = time_to_seconds(h1, m1, s1)
    end_time = time_to_seconds(h2, m2, s2)
    
    alarms = 0  # 알람 카운트를 초기화
    
    # 시작 시각이 정확히 0시 0분 0초이거나 12시 0분 0초일 때는 교차점이 1개이므로 카운트
    if start_time == 0 or start_time == 12 * 3600:
        alarms += 1
    
    # 시작 시각부터 종료 시각까지 1초 단위로 반복
    while start_time < end_time:
        # 현재 시각의 각도를 계산
        # 시침은 1초에 1/120도 이동
        h = start_time / 120 % 360
        # 분침은 1초에 0.1도 이동
        m = start_time / 10 % 360
        # 초침은 1초에 6도 이동
        s = start_time * 6 % 360
        
        # 다음 시각의 각도를 계산 (1초 후)
        # 다음 시각이 360도일 때 0도와 같은 효과를 주기 위해 360으로 처리
        n_h = 360 if (start_time + 1) / 120 % 360 == 0 else (start_time + 1) / 120 % 360
        n_m = 360 if (start_time + 1) / 10 % 360 == 0 else (start_time + 1) / 10 % 360
        n_s = 360 if (start_time + 1) * 6 % 360 == 0 else (start_time + 1) * 6 % 360
        
        # 초침이 시침을 지나친 경우를 카운트
        # 초침의 현재 각도가 시침의 현재 각도보다 작고, 다음 초침의 각도가 시침의 다음 각도보다 작거나 같은 경우
        if s < h and n_h <= n_s:
            alarms += 1
        
        # 초침이 분침을 지나친 경우를 카운트
        # 초침의 현재 각도가 분침의 현재 각도보다 작고, 다음 초침의 각도가 분침의 다음 각도보다 작거나 같은 경우
        if s < m and n_m <= n_s:
            alarms += 1
        
        # 시침, 분침, 초침이 모두 같은 위치에 있을 때 중복 카운트를 방지
        # 이 경우 알람이 1번만 울리므로 중복 카운트를 하나 뺀다
        if n_s == n_m and n_s == n_h:
            alarms -= 1
        
        # 다음 초로 이동
        start_time += 1
    
    return alarms
