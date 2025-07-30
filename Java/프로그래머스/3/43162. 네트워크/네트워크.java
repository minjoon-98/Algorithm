class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;

        // 모든 컴퓨터에 대해
        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않았다면, 새로운 네트워크의 시작
            if (!visited[i]) {
                dfs(i, computers, visited);
                answer++;  // 네트워크 개수 증가
            }
        }

        return answer;
    }

    // DFS 재귀 함수
    private void dfs(int node, int[][] computers, boolean[] visited) {
        visited[node] = true;

        for (int i = 0; i < computers.length; i++) {
            // 연결되어 있고 아직 방문하지 않았다면
            if (computers[node][i] == 1 && !visited[i]) {
                dfs(i, computers, visited);
            }
        }
    }
}
