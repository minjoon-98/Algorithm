import sys
sys.setrecursionlimit(10**6)    # 런타임 에러 오류 수정

preorder_arr = []

# 공백을 엔터할 때까지 입력받음
while True:
    try:
        preorder_arr.append(int(sys.stdin.readline()))
    except:
        break

def postorder(root_index, end_index):
    if root_index > end_index:
        return
    global preorder_arr

    # 만약 root보다 큰 값 없는 경우 전부 왼쪽 서브트리로 취급
    right_start = end_index + 1
    for i in range(root_index + 1, end_index + 1):
        if preorder_arr[root_index] < preorder_arr[i]:
            right_start = i
            break

    # root 다음부터 왼쪽 서브트리 탐색
    postorder(root_index + 1, right_start - 1)

    # 왼쪽 서브트리 탐색 끝나면 오른쪽 서브트리 탐색
    postorder(right_start, end_index)

    # 왼쪽, 오른쪽 서브트리 탐색 끝나면 root 출력
    print(preorder_arr[root_index])

postorder(0, len(preorder_arr) - 1)