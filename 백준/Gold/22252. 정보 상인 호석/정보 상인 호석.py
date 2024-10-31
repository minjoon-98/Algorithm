import sys
import heapq

input = sys.stdin.readline

Q = int(input())
gorillas = {}
total_value = 0

for _ in range(Q):
    query = input().strip().split()
    action = int(query[0])
    
    if action == 1:
        # 정보 획득
        name = query[1]
        k = int(query[2])
        values = list(map(int, query[3:3+k]))
        
        if name not in gorillas:
            gorillas[name] = []
        
        # 힙에 정보 가치 추가 (Max-Heap을 위해 부호 반전)
        for value in values:
            heapq.heappush(gorillas[name], -value)
    
    elif action == 2:
        # 정보 구매
        name = query[1]
        b = int(query[2])
        
        if name in gorillas:
            current_heap = gorillas[name]
            num_to_buy = min(b, len(current_heap))
            purchase_value = 0
            
            for _ in range(num_to_buy):
                # 가장 비싼 정보를 구매 (부호 반전)
                purchase_value += -heapq.heappop(current_heap)  # purchase_value -= heapq.heappop(current_heap)
            
            # 총 가치 갱신
            total_value += purchase_value
            
            # 만약 고릴라의 정보가 없어진 경우 (다 구매한 경우)
            if not current_heap:
                del gorillas[name]

print(total_value)