def solution(bandage, health, attacks):
    t, x, y = bandage  # 붕대 기술의 시전 시간, 초당 회복량, 추가 회복량
    current_health = health
    current_time = 0
    attack_index = 0
    total_attacks = len(attacks)
    healing_success_count = 0  # 회복 성공 카운트 초기화

    # 공격 시간을 딕셔너리로 저장 (빠른 접근을 위해)
    attack_dict = {attack[0]: attack[1] for attack in attacks}

    while attack_index < total_attacks or current_time <= (attacks[-1][0] if attacks else 0):
        # 현재 시간이 공격 시간인 경우
        if current_time in attack_dict:
            damage = attack_dict[current_time]  # 해당 시간의 피해량
            current_health -= damage
            # 연속 회복 카운트 초기화
            healing_success_count = 0

            # 체력이 0 이하가 되면 -1 반환
            if current_health <= 0:
                return -1
            
            # 공격 처리 후 다음 공격으로 이동
            attack_index += 1

        else:
            # 회복 처리
            healing_success_count += 1
            
            # 회복량 계산
            if healing_success_count < t:
                current_health += x  # 초당 회복량 적용
            else:
                current_health += x + y  # 추가 회복량 포함
                healing_success_count = 0  # 연속 성공 카운트 초기화

            # 최대 체력 초과 방지
            current_health = min(current_health, health)

        current_time += 1  # 다음 초로 이동

    return current_health  # 모든 공격 후 남은 체력 반환