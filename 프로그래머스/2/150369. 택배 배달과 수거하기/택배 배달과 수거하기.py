def solution(cap, n, deliveries, pickups):
    answer = 0
    delivery = 0    # 배달할 재활용 택배 상자의 개수 초기화
    pickup = 0  # 수거할 빈 재활용 택배 상자의 개수 초기화
    
    for i in range(n-1, -1, -1):    # 뒤에서 부터 배달, 수거 함
        delivery += deliveries[i]   # 해당 집의 배달 갯수
        pickup += pickups[i]    # 해당 집의 수거 갯수
        
        while delivery > 0 or pickup > 0:   # 배달 카운트와 수거 카운트 중 하나라도 0보다 크면 왕복 거리 계산 (현재 집에 배달, 수거해야 할 택배가 남아 있어 추가 방문 필요)
            delivery -= cap # 배달 카운트 - 트럭이 한번에 움직일 수 있는 양
            pickup -= cap   # 수거 카운트 - 트럭이 한번에 움직일 수 있는 양
            answer += (i + 1) * 2 # 거리 계산
    
    return answer