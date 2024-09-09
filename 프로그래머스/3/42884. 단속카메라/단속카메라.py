def solution(routes):
    # 차량의 경로를 진출 지점 기준으로 정렬 (차량의 경로는 [진입 지점, 진출 지점]으로 주어짐)
    routes.sort(key=lambda x: x[1])
    
    # 카메라의 개수를 저장할 변수와 마지막으로 설치한 카메라의 위치를 초기화
    camera_count = 0
    last_camera = -30001  # 문제의 제약 사항에 따라 진입 지점과 진출 지점은 -30000 이상이므로 그 이전 값을 설정
    
    # 차량의 경로를 순회합니다.
    for route in routes:
        # 차량의 진출 지점이 마지막 카메라의 위치보다 크다면, 새로운 카메라를 설치
        if route[0] > last_camera:
            camera_count += 1
            last_camera = route[1]
    
    return camera_count
