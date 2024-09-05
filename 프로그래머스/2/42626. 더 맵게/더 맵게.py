import heapq

def solution(scoville, K):
    # 힙으로 변환
    heapq.heapify(scoville)
    answer = 0
    
    while scoville and scoville[0] < K:
        if len(scoville) < 2:
            return -1  # 불가능한 경우
        
        # 두 개의 가장 낮은 스코빌 지수를 꺼내기
        first = heapq.heappop(scoville)
        second = heapq.heappop(scoville)
        
        # 새로운 스코빌 지수 계산
        new_scoville = first + second * 2
        heapq.heappush(scoville, new_scoville)
        answer += 1

    # 결과 반환
    return answer if scoville and scoville[0] >= K else -1