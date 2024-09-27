def solution(players, callings):
    # 선수의 현재 위치를 저장하는 딕셔너리 생성
    position = {player: i for i, player in enumerate(players)}

    for called in callings:
        idx = position[called]  # 호출된 선수의 현재 인덱스 가져오기
        
        # 호출된 선수가 첫 번째가 아닐 경우에만 교환
        if idx > 0:
            # 앞선 선수
            front_player = players[idx - 1]
            
            # 순위를 교환
            players[idx], players[idx - 1] = players[idx - 1], players[idx]
            
            # 딕셔너리에서 인덱스 업데이트
            position[called] = idx - 1
            position[front_player] = idx

    return players