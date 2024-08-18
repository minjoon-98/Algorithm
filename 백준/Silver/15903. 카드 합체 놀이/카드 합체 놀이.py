import heapq
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
# cards = heapq.heapify(list(map(int, input().split()))) # heapq.heapify() 함수가 리스트를 힙 구조로 변환하는 작업만 하고, 그 결과를 반환하지 않음 따라서 cards 변수에는 None이 저장됨
cards = list(map(int, input().split()))  # 여기서 리스트를 먼저 만듭니다
heapq.heapify(cards)  # 리스트를 heapify 합니다

for _ in range(m):
    current_sum = heapq.heappop(cards) + heapq.heappop(cards)
    heapq.heappush(cards, current_sum)
    heapq.heappush(cards, current_sum)

print(sum(cards))