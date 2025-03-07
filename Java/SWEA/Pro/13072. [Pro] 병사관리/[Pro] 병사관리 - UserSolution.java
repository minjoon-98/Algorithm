/// 정답 코드
//author: 류호석(rhs0266)

/**
 * ============================================================================
 * [병사관리 문제 정답 코드 분석]
 * ============================================================================
 * 이 코드는 병사 관리 문제를 해결하기 위해 구현된 정답 코드입니다.
 * 
 * <주요 아이디어 및 접근 방식>
 * - 각 병사는 고유번호(mID), 팀 번호(mTeam), 평판 점수(mScore)를 갖습니다.
 * - 평판 점수의 범위가 1~5로 매우 작으므로, 각 팀별로 평판 점수별(1~5) 연결리스트(버킷)를 구성하여 병사를 관리합니다.
 * - 연결리스트를 사용하면, 병사 고용(hire) 시 O(1)로 추가할 수 있고,
 *   팀 전체 업데이트(updateTeam) 시 각 평판 버킷을 단순히 이동시킴으로써 O(1) 시간에 처리할 수 있습니다.
 * - 병사 정보가 변경될 때(평판 변경, 해고 등) 기존 노드를 무효화하고 새 노드를 추가하는 방식으로 처리하는데,
 *   이를 위해 각 병사의 최신 버전을 관리하는 version 배열을 사용합니다.
 * - 해고(fire)는 실제 노드를 삭제하지 않고, version 값을 -1로 설정하여 해당 병사가 무효함을 표시하는 Lazy Deletion 기법을 사용합니다.
 *
 * <사용된 자료구조>
 * - Node 클래스: 병사 정보를 담는 연결리스트의 노드로, 고유번호(id), 버전(v), 다음 노드(nxt)를 저장.
 * - Node[] node: 미리 할당된 노드 풀(pool)로, 최악의 경우 100,000번의 고용 및 평판 변경 호출로 인해 최대 약 200,000개 이상의 노드가 생성될 수 있으므로,
 *   여유 공간을 포함하여 200055 크기를 사용합니다.
 *   (여기서 200055는 안전하게 모든 노드를 저장하기 위해 선택됨)
 * - int[] version: 각 병사의 최신 버전 번호를 저장하는 배열입니다.
 *   병사의 고유번호(mID)는 1부터 최대 100,000까지 주어지므로, 인덱스 범위를 안전하게 처리하기 위해 100055 크기를 사용합니다.
 * - int[] num: 각 병사의 소속 팀 번호를 저장하는 배열입니다.
 * - Team 클래스: 각 팀(1~5) 별로 평판 점수(1~5)마다 연결리스트의 head와 tail 포인터를 저장합니다.
 * - Team[] t: 팀 번호 1번부터 5번까지의 팀 정보를 저장합니다.
 *
 * <연산별 시간 복잡도>
 * - hire, fire, updateSoldier, updateTeam 연산은 O(1) 시간에 처리됩니다.
 * - bestSoldier 연산은 해당 팀의 연결리스트를 순회하므로 최악의 경우 O(N)이지만,
 *   호출 횟수가 적어 전체 성능에 큰 영향을 주지 않습니다.
 * ============================================================================
 */

class UserSolution
{
    /**
     * 병사 정보를 담는 연결리스트의 노드 클래스
     */
    public class Node {
        int id;   // 병사의 고유번호 (mID)
        int v;    // 노드의 버전 (해당 병사의 최신 정보 판별용)
        Node nxt; // 다음 노드를 가리키는 포인터

        Node() {}

        Node(int id, int v) {
            this.id = id;
            this.v = v;
            this.nxt = null;
        }

        Node(int id, int v, Node nxt) {
            this.id = id;
            this.v = v;
            this.nxt = nxt;
        }
    }

    // 미리 할당된 노드 풀 (최대 200055개 노드)
    // 200055는 최악의 경우(고용, 평판 업데이트 호출)에도 안전하게 모든 노드를 저장할 수 있도록 여유를 둔 크기임.
    public Node[] node = new Node[200055];

    // 노드 풀에서 현재까지 사용한 노드의 개수
    public int cnt = 0;

    // 각 병사(mID)의 최신 버전을 저장하는 배열
    // mID 범위가 1~100000 이므로, 안전하게 사용하기 위해 여유분을 두어 100055 크기를 사용함.
    // version[i] := ID가 i인 병사의 최신 버전 번호
    public int[] version = new int[100055];

    // 각 병사의 소속 팀 번호를 저장하는 배열
    // num[i] := ID가 i인 병사의 소속 팀 번호
    public int[] num = new int[100055];

    /**
     * @brief 새 노드를 생성(재활용)하는 함수
     * 
     * 새로운 노드를 노드 풀에서 가져와서, 해당 병사의 고유번호와 다음 노드 포인터를 설정하고,
     * 버전을 최신화하여 저장한다.
     * 
     * @param id 병사의 고유번호
     * @param nxt 다음 노드 포인터
     * @return Node 생성된 노드 반환
     */
    public Node getNewNode(int id, Node nxt) {
        Node ret = node[cnt++];  // 미리 할당된 노드 배열에서 새 노드를 가져옴
        ret.id = id;             // 병사 고유번호 설정
        ret.nxt = nxt;           // 다음 노드 포인터 설정
        ret.v = ++version[id];   // 해당 병사의 버전을 1 증가시킨 후 최신 버전 저장
        return ret;
    }

    /**
     * 팀 정보를 관리하는 클래스
     * 각 팀은 평판 점수 1~5에 대해 연결리스트(버킷)를 가지고 있으며,
     * head와 tail 포인터로 해당 리스트를 관리한다.
     */
    public class Team {
        // 평판 점수 1~5에 대한 연결리스트의 head 포인터 (인덱스 0은 사용하지 않음)
        Node[] head = new Node[6];
        // 평판 점수 1~5에 대한 연결리스트의 tail 포인터
        Node[] tail = new Node[6];
    }

    // 팀 정보를 저장하는 배열 (인덱스 1~5 사용)
    public Team[] t = new Team[6];

    /**
     * @brief 초기화 함수
     * 
     * 각 테스트 케이스 시작 시 호출되며, 
     * - 노드 풀, 팀 정보, 버전 배열(version), 소속 팀 배열(num) 모두를 초기화한다.
     */
    public void init() {
        cnt = 0;
        // 노드 풀 초기화 : 모든 인덱스에 대해 Node 객체 생성 (null이면 새로 생성)
        for (int i = 0; i < 200055; i++){
            if (node[i] == null) node[i] = new Node();
        }
        // 팀별 초기화: 팀 번호 1부터 5까지 각 팀에 대해 평판 점수 1~5의 연결리스트를 초기화
        for (int i = 1; i <= 5; i++) {
            t[i] = new Team();
            // 각 평판 점수 버킷에 대해 더미 노드(dummy node)를 head와 tail에 설정
            for (int j = 1; j <= 5; j++) {
                t[i].tail[j] = t[i].head[j] = getNewNode(0, null);
                // 여기서 id가 0인 노드를 더미 노드로 사용함
            }
        }
        // version 및 num 배열 초기화: 모든 병사의 버전과 소속 팀 정보를 0으로 초기화
        for (int i = 0; i <= 100000; i++) {
            version[i] = 0;
            num[i] = 0;
        }
    }

    /**
     * @brief 병사 고용 함수
     * 
     * 주어진 mID, mTeam, mScore 값을 가진 병사를 고용하며,
     * 해당 팀의 평판 점수 버킷에 새 노드를 추가한다.
     * 
     * @param mID 병사의 고유번호 (1 ≤ mID ≤ 100,000)
     * @param mTeam 병사의 소속 팀 (1 ≤ mTeam ≤ 5)
     * @param mScore 병사의 평판 점수 (1 ≤ mScore ≤ 5)
     */
    public void hire(int mID, int mTeam, int mScore) {  // O(1)
        // 새 노드 생성 후, 해당 팀의 평판 mScore 버킷의 연결리스트 끝에 추가
        Node newNode = getNewNode(mID, null);
        t[mTeam].tail[mScore].nxt = newNode;  // 현재 tail의 다음 노드로 새 노드 연결
        t[mTeam].tail[mScore] = newNode;        // tail 포인터 갱신
        num[mID] = mTeam;  // 병사의 소속 팀 정보 저장
    }

    /**
     * @brief 병사 해고 함수
     * 
     * 주어진 mID를 가진 병사를 해고하며, 
     * 실제 연결리스트에서 삭제하지 않고, 버전 정보를 -1로 설정하여 
     * 해당 병사의 노드가 무효함을 표시한다.
     * 
     * @param mID 해고할 병사의 고유번호
     */
    public void fire(int mID) {  // O(1)
        // 버전 값을 -1로 설정하여 이후 bestSoldier 호출 시 무효한 노드로 처리됨
        version[mID] = -1;
    }

    /**
     * @brief 병사의 평판 점수 개별 변경 함수
     * 
     * 주어진 mID 병사의 평판 점수를 mScore로 변경한다.
     * 실제로는 해당 병사의 새 노드를 생성(hire 함수 호출)하여 최신 정보를 갱신한다.
     * 
     * @param mID 평판 점수를 변경할 병사의 고유번호
     * @param mScore 새 평판 점수 (1 ≤ mScore ≤ 5)
     */
    public void updateSoldier(int mID, int mScore) {  // O(1)
        // 기존 병사의 팀 정보를 num 배열에서 조회한 후, hire()를 호출하여 평판 점수 업데이트
        hire(mID, num[mID], mScore);
    }

    /**
     * @brief 팀 전체 병사 평판 일괄 변경 함수
     * 
     * 특정 팀(mTeam)의 모든 병사의 평판 점수를 mChangeScore만큼 변경한다.
     * 변경 시, 평판 점수는 1과 5 사이로 클램핑(clamp)된다.
     * 평판 점수 별 연결리스트(버킷)를 이동시키는 방식으로 처리하여 O(1) 시간에 실행된다.
     * 
     * @param mTeam 평판 점수를 변경할 팀 번호 (1 ≤ mTeam ≤ 5)
     * @param mChangeScore 평판 점수의 변화량 (-4 ≤ mChangeScore ≤ 4)
     */
    public void updateTeam(int mTeam, int mChangeScore) {  // O(1)
        // mChangeScore가 음수인 경우: 낮은 평점부터 처리하여 버킷 이동
        if (mChangeScore < 0) {
            for (int j = 1; j <= 5; j++) {
                int k = j + mChangeScore;       // 변경 후 평점 계산
                // 평점 k를 1과 5 사이로 클램핑
                k = k < 1 ? 1 : (k > 5 ? 5 : k);
                if (j == k) continue;           // 평점이 변하지 않으면 건너뜀
                if (t[mTeam].head[j].nxt == null) continue;  // 해당 버킷에 병사가 없으면 건너뜀
                // 평점 j 버킷의 노드들을 평점 k 버킷으로 이동
                t[mTeam].tail[k].nxt = t[mTeam].head[j].nxt;
                t[mTeam].tail[k] = t[mTeam].tail[j];
                // 평점 j 버킷을 비움 (head와 tail을 더미 노드로 재설정)
                t[mTeam].head[j].nxt = null;
                t[mTeam].tail[j] = t[mTeam].head[j];
            }
        }
        // mChangeScore가 양수인 경우: 높은 평점부터 처리하여 버킷 이동
        if (mChangeScore > 0) {
            for (int j = 5; j >= 1; j--) {
                int k = j + mChangeScore;       // 변경 후 평점 계산
                // 평점 k를 1과 5 사이로 클램핑
                k = k < 1 ? 1 : (k > 5 ? 5 : k);
                if (j == k) continue;           // 평점이 변하지 않으면 건너뜀
                if (t[mTeam].head[j].nxt == null) continue;  // 해당 버킷에 병사가 없으면 건너뜀
                // 평점 j 버킷의 노드들을 평점 k 버킷으로 이동
                t[mTeam].tail[k].nxt = t[mTeam].head[j].nxt;
                t[mTeam].tail[k] = t[mTeam].tail[j];
                // 평점 j 버킷을 비움 (head와 tail을 더미 노드로 재설정)
                t[mTeam].head[j].nxt = null;
                t[mTeam].tail[j] = t[mTeam].head[j];
            }
        }
    }

    /**
     * @brief 팀 내 최고 평판 병사 반환 함수
     * 
     * 특정 팀(mTeam)에서 평판 점수가 가장 높은 병사를 반환한다.
     * 평판 점수가 동일한 경우에는 고유번호(mID)가 가장 큰 병사를 선택한다.
     * 최신 노드(버전 관리)를 고려하여 해고되거나 업데이트된 병사의 정보는 무시한다.
     * 
     * @param mTeam 검색할 팀 번호 (1 ≤ mTeam ≤ 5)
     * @return int 최고 평판 병사의 고유번호, 병사가 없으면 0 반환
     */
    public int bestSoldier(int mTeam) {  // O(N)
        // 평점 5부터 1까지 내림차순으로 확인
        for (int j = 5; j >= 1; j--) {
            // 평점 j 버킷의 첫 번째 유효 노드부터 탐색 시작
            Node node = t[mTeam].head[j].nxt;
            if (node == null) continue;   // 해당 평점에 병사가 없으면 건너뜀

            int ans = 0;
            // 연결리스트를 순회하면서 최신 버전의 노드(유효한 병사)만 고려
            while (node != null) {
                // node.v와 version[node.id]가 일치하면 최신 정보임
                if (node.v == version[node.id]) {
                    // mID가 큰 병사를 선택 (최대값 갱신)
                    ans = ans < node.id ? node.id : ans;
                }
                node = node.nxt;
            }
            if (ans != 0) return ans;   // 유효한 병사를 찾으면 반환
        }
        return 0;  // 조건 상 항상 병사가 존재하므로 도달하지 않음
    }
}


///**
// * ============================================================================
// * [세그먼트 트리 기반 병사관리 코드 상세 분석 및 시간 초과 원인]
// * ============================================================================
// * 이 코드는 병사들의 평판 점수를 관리하기 위해 세그먼트 트리와 lazy propagation 기법을
// * 이용하여, 각 팀(1~5)별로 병사들의 평판 정보를 관리하는 솔루션입니다.
// *
// * <전체 구조 및 아이디어>
// * - 각 병사는 고유번호(mID)와 평판 점수(1~5)를 가지고 있으며, 각 팀에 대해 전용 세그먼트 트리를 구축합니다.
// * - 세그먼트 트리는 1부터 MAX_MID(100,000)까지의 구간을 담당하며, 각 노드(NodeData)는 해당 구간에
// *   포함된 병사들의 평판별(0~5) 개수와, 각 평판을 가진 병사들 중 최대 mID를 저장합니다.
// * - lazy propagation 기법을 사용하여, 팀 전체 평판 일괄 변경(updateTeam) 시 구간 업데이트를 효율적으로
// *   처리하고자 합니다. lazy 함수는 길이 6의 배열로 표현되며, 각 평판을 변경하는 함수를 나타냅니다.
// * - 각 연산(hire, fire, updateSoldier, updateTeam, bestSoldier)은 세그먼트 트리의 점 업데이트, 구간 업데이트,
// *   구간 질의 등을 이용하여 처리됩니다.
// *
// * <사용된 자료구조 및 변수>
// * - MAX_MID: 최대 mID 값 (100,000). 세그먼트 트리의 리프 노드 개수를 결정함.
// * - soldierTeam: 각 mID에 대해 소속 팀 정보를 저장하는 배열 (인덱스 1~100,000).
// * - trees: 팀별로 1~5 인덱스를 사용하는 세그먼트 트리 배열.
// *
// * - 내부 클래스 NodeData:
// *   - count: 평판 i (i=0은 미고용 상태를 의미)의 병사 수
// *   - maxID: 평판 i를 가진 병사들 중 최대 mID
// *
// * - 내부 클래스 SegmentTree:
// *   - tree: 세그먼트 트리 노드 배열 (1-indexed)로, 각 노드에 NodeData를 저장함.
// *   - lazy: 각 노드에 적용될 lazy 함수를 저장하는 2차원 배열 (길이 6 배열). lazy 함수는 항등함수 f(x)=x로
// *     초기화되며, 업데이트에 따라 합성(composition)됨.
// *
// * <시간 복잡도 및 시간 초과 원인 분석>
// * - 각 점 업데이트(updatePoint)와 구간 업데이트(rangeUpdate)는 일반적으로 O(log(MAX_MID))의
// *   시간 복잡도를 가집니다.
// * - 하지만 lazy propagation 구현에서, 각 노드마다 길이 6의 배열에 대해 반복문(상수 6번)을 수행하며,
// *   함수 합성(compose), 함수 적용(applyFunction) 등 추가적인 상수 연산이 발생합니다.
// * - 최악의 경우, 다수의 updateTeam 호출이 전체 구간(1~MAX_MID)에 대해 수행되면, 
// *   세그먼트 트리의 많은 노드에서 lazy 함수를 갱신하고 pushDown을 수행해야 하므로, 
// *   재귀 호출과 배열 복사 등 상수 연산이 누적되어 시간 초과(TLE)가 발생할 수 있습니다.
// * - 또한, Java의 재귀 호출 및 객체 생성, 배열 접근 등에서의 오버헤드가 누적되어 제한 시간 내에
// *   처리하지 못하는 경우가 발생합니다.
// *
// * ============================================================================
// */
//
//package B형특강.no5_병사관리;
//
//import java.io.*;
//import java.util.*;
//
//public class UserSolution {
//
//    static final int MAX_MID = 100000;  // mID의 최대값 (1 ~ 100,000)
//
//    /**
//     * soldierTeam 배열은 각 mID에 대해 소속된 팀 번호를 저장한다.
//     * mID가 고용되지 않은 경우 0으로 저장됨.
//     */
//    static int[] soldierTeam = new int[MAX_MID + 1];
//
//    /**
//     * 각 팀(1~5)마다 전용 세그먼트 트리를 저장하는 배열.
//     * 인덱스 1부터 5까지 사용한다.
//     */
//    static SegmentTree[] trees = new SegmentTree[6];
//
//    /********** 내부 자료구조 – 세그먼트 트리 및 관련 클래스 **********/
//
//    /**
//     * @brief NodeData 클래스
//     * 한 구간 내에서 평판 점수(0~5)별 병사 수와 해당 평판을 가진 병사들 중 최대 mID를 저장한다.
//     * 평판 0은 미고용 상태를 의미한다.
//     */
//    static class NodeData {
//        int[] count;   // count[i] : 평판 i인 병사 수
//        int[] maxID;   // maxID[i] : 평판 i인 병사들 중 최대 mID
//
//        public NodeData() {
//            count = new int[6];  // 0~5, 총 6개 버킷
//            maxID = new int[6];  // 0~5, 총 6개 버킷
//        }
//    }
//    
//    /**
//     * @brief SegmentTree 클래스
//     * lazy propagation 기법을 이용한 세그먼트 트리 구현.
//     * 각 리프는 개별 mID 위치의 평판 정보를 저장하며, 내부 노드는 자식 노드의 정보를 합친다.
//     * lazy 배열은 각 노드에 적용될 lazy 함수를 저장하며, 길이 6의 배열로 표현된다.
//     */
//    static class SegmentTree {
//        int n;                   // 리프의 개수 (여기서는 MAX_MID)
//        NodeData[] tree;         // 세그먼트 트리 노드 데이터 (1-indexed)
//        int[][] lazy;            // 각 노드에 적용될 lazy 함수 배열, 길이 6의 배열
//
//        /**
//         * @brief 생성자
//         * 주어진 n에 대해 세그먼트 트리를 초기화한다.
//         * lazy 함수는 항등함수 f(x)=x로 초기화된다.
//         * 
//         * @param n 리프 노드 개수 (여기서는 MAX_MID)
//         */
//        public SegmentTree(int n) {
//            this.n = n;
//            int size = 1;
//            while(size < n) size *= 2;
//            tree = new NodeData[size * 2];
//            lazy = new int[size * 2][6];
//            // 모든 노드를 초기화
//            for (int i = 0; i < tree.length; i++) {
//                tree[i] = new NodeData();
//                // lazy 함수 초기값은 항등함수: f(x)=x
//                for (int j = 0; j < 6; j++) {
//                    lazy[i][j] = j;
//                }
//            }
//            // 내부 노드 병합 초기화: 리프 노드들의 정보를 합쳐서 부모 노드를 구성
//            for (int i = size - 1; i > 0; i--) {
//                tree[i] = merge(tree[i * 2], tree[i * 2 + 1]);
//            }
//        }
//
//        /**
//         * @brief 점 업데이트 함수 (updatePoint)
//         * 주어진 pos 위치의 값을 value(0 또는 1~5)로 업데이트한다.
//         * 
//         * @param pos 업데이트할 위치 (mID)
//         * @param value 업데이트할 평판 점수 (0은 미고용 상태)
//         */
//        public void updatePoint(int pos, int value) {
//            updatePoint(1, 1, n, pos, value);
//        }
//        
//        private void updatePoint(int idx, int l, int r, int pos, int value) {
//            if(l == r) {
//                // 리프 노드에서는 모든 평판 bucket을 초기화한 후,
//                // value가 유효하면 해당 bucket에 값을 기록
//                for (int i = 0; i < 6; i++) {
//                    tree[idx].count[i] = 0;
//                    tree[idx].maxID[i] = 0;
//                }
//                if(value >= 1 && value <= 5) {
//                    tree[idx].count[value] = 1;
//                    tree[idx].maxID[value] = pos;
//                }
//                return;
//            }
//            pushDown(idx, l, r);
//            int mid = (l + r) / 2;
//            if(pos <= mid)
//                updatePoint(idx * 2, l, mid, pos, value);
//            else
//                updatePoint(idx * 2 + 1, mid + 1, r, pos, value);
//            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
//        }
//        
//        /**
//         * @brief 구간 업데이트 함수 (rangeUpdate)
//         * 구간 [L, R]에 대해 lazy 함수 f (길이 6 배열)을 적용한다.
//         * 
//         * @param L 업데이트 시작 구간
//         * @param R 업데이트 종료 구간
//         * @param f 적용할 lazy 함수 (길이 6 배열)
//         */
//        public void rangeUpdate(int L, int R, int[] f) {
//            rangeUpdate(1, 1, n, L, R, f);
//        }
//        
//        private void rangeUpdate(int idx, int l, int r, int L, int R, int[] f) {
//            if(R < l || r < L) return;  // 구간 밖이면 리턴
//            if(L <= l && r <= R) {
//                applyLazy(idx, f);
//                return;
//            }
//            pushDown(idx, l, r);
//            int mid = (l + r) / 2;
//            rangeUpdate(idx * 2, l, mid, L, R, f);
//            rangeUpdate(idx * 2 + 1, mid + 1, r, L, R, f);
//            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
//        }
//        
//        /**
//         * @brief 구간 질의 함수 (query)
//         * 구간 [L, R]에 대한 NodeData를 반환한다.
//         * 
//         * @param L 질의 시작 구간
//         * @param R 질의 종료 구간
//         * @return NodeData 구간의 병사 평판 정보 (count 및 maxID)
//         */
//        public NodeData query(int L, int R) {
//            return query(1, 1, n, L, R);
//        }
//        
//        private NodeData query(int idx, int l, int r, int L, int R) {
//            if(R < l || r < L) return new NodeData();  // 구간에 해당하는 데이터가 없으면 빈 NodeData 반환
//            if(L <= l && r <= R)
//                return tree[idx];
//            pushDown(idx, l, r);
//            int mid = (l + r) / 2;
//            NodeData leftData = query(idx * 2, l, mid, L, R);
//            NodeData rightData = query(idx * 2 + 1, mid + 1, r, L, R);
//            return merge(leftData, rightData);
//        }
//        
//        /**
//         * @brief lazy 값을 자식 노드로 전파하는 함수 (pushDown)
//         * 현재 노드의 lazy 함수가 항등함수가 아니라면, 자식 노드에 적용하고 자신의 lazy 값을 항등함수로 재설정한다.
//         * 
//         * @param idx 현재 노드의 인덱스
//         * @param l 현재 구간의 시작
//         * @param r 현재 구간의 종료
//         */
//        private void pushDown(int idx, int l, int r) {
//            if(isIdentity(lazy[idx])) return;
//            int mid = (l + r) / 2;
//            applyLazy(idx * 2, lazy[idx]);
//            applyLazy(idx * 2 + 1, lazy[idx]);
//            // 현재 노드의 lazy 함수를 항등함수로 재설정
//            for (int j = 0; j < 6; j++) {
//                lazy[idx][j] = j;
//            }
//        }
//        
//        /**
//         * @brief 노드에 lazy 함수 f를 적용하는 함수 (applyLazy)
//         * 노드의 NodeData에 lazy 함수 f를 적용하고, 기존 lazy 함수와 합성한다.
//         * 
//         * @param idx 적용 대상 노드의 인덱스
//         * @param f 적용할 lazy 함수 (길이 6 배열)
//         */
//        private void applyLazy(int idx, int[] f) {
//            tree[idx] = applyFunction(tree[idx], f);
//            lazy[idx] = compose(f, lazy[idx]);
//        }
//        
//        /**
//         * @brief lazy 함수가 항등함수(identity)인지 확인하는 함수
//         * 
//         * @param f lazy 함수 배열 (길이 6)
//         * @return boolean 항등함수이면 true, 아니면 false
//         */
//        private boolean isIdentity(int[] f) {
//            for (int i = 0; i < 6; i++) {
//                if(f[i] != i) return false;
//            }
//            return true;
//        }
//        
//        /**
//         * @brief 두 lazy 함수 f와 g의 합성을 수행하는 함수
//         * 합성 결과: result(x) = f(g(x))
//         * 
//         * @param f 첫 번째 함수
//         * @param g 두 번째 함수
//         * @return int[] 합성된 함수 (길이 6 배열)
//         */
//        private int[] compose(int[] f, int[] g) {
//            int[] res = new int[6];
//            for (int i = 0; i < 6; i++) {
//                res[i] = f[g[i]];
//            }
//            return res;
//        }
//        
//        /**
//         * @brief NodeData에 lazy 함수 f를 적용하는 함수 (applyFunction)
//         * 각 평판 버킷을 lazy 함수 f에 따라 재배치한다.
//         * 
//         * @param data 기존 NodeData
//         * @param f 적용할 lazy 함수 (길이 6 배열)
//         * @return NodeData lazy 함수 적용 후의 새로운 NodeData
//         */
//        private NodeData applyFunction(NodeData data, int[] f) {
//            NodeData res = new NodeData();
//            for (int i = 0; i < 6; i++) {
//                int newScore = f[i];
//                res.count[newScore] += data.count[i];
//                res.maxID[newScore] = Math.max(res.maxID[newScore], data.maxID[i]);
//            }
//            return res;
//        }
//        
//        /**
//         * @brief 두 NodeData를 병합하는 함수 (merge)
//         * 두 자식 노드의 정보를 합쳐 부모 노드의 NodeData를 구성한다.
//         * 
//         * @param left 왼쪽 자식의 NodeData
//         * @param right 오른쪽 자식의 NodeData
//         * @return NodeData 병합된 결과
//         */
//        private NodeData merge(NodeData left, NodeData right) {
//            NodeData res = new NodeData();
//            for (int i = 0; i < 6; i++) {
//                res.count[i] = left.count[i] + right.count[i];
//                res.maxID[i] = Math.max(left.maxID[i], right.maxID[i]);
//            }
//            return res;
//        }
//    }
//
//    /**
//     * @brief 평판 점수를 1~5 범위로 제한하는 함수 (clamp)
//     * 
//     * @param x 입력값
//     * @return int 1과 5 사이로 클램핑된 값
//     */
//    private int clamp(int x) {
//        if(x < 1) return 1;
//        if(x > 5) return 5;
//        return x;
//    }
//    
//    /**
//     * @brief 초기화 함수
//     * 각 테스트케이스 시작 시 호출되며, soldierTeam 배열과 팀별 세그먼트 트리를 초기화한다.
//     */
//    public void init() {
//        // soldierTeam 배열 초기화: 모든 병사의 소속 팀을 0으로 설정 (미고용 상태)
//        Arrays.fill(soldierTeam, 0);
//        // 팀 번호 1부터 5까지 각 팀에 대해 세그먼트 트리 초기화 (범위는 1 ~ MAX_MID)
//        for (int team = 1; team <= 5; team++) {
//            trees[team] = new SegmentTree(MAX_MID);
//        }
//    }
//
//    /**
//     * @brief 병사 고용 함수 (hire)
//     * 주어진 mID, 소속팀, 평판 점수를 설정하여 병사를 고용한다.
//     * 
//     * @param mID 병사의 고유번호 (1 ≤ mID ≤ 100,000)
//     * @param mTeam 병사의 소속팀 (1 ≤ mTeam ≤ 5)
//     * @param mScore 병사의 평판 점수 (1 ≤ mScore ≤ 5)
//     */
//    public void hire(int mID, int mTeam, int mScore) {
//        soldierTeam[mID] = mTeam;
//        trees[mTeam].updatePoint(mID, mScore);
//    }
//
//    /**
//     * @brief 병사 해고 함수 (fire)
//     * 주어진 mID에 해당하는 병사를 해고하며, 세그먼트 트리 상의 값을 0으로 업데이트한다.
//     * 
//     * @param mID 해고할 병사의 고유번호
//     */
//    public void fire(int mID) {
//        int team = soldierTeam[mID];
//        if(team != 0) {
//            soldierTeam[mID] = 0;
//            trees[team].updatePoint(mID, 0);
//        }
//    }
//
//    /**
//     * @brief 병사 평판 점수 개별 업데이트 함수 (updateSoldier)
//     * 주어진 mID 병사의 평판 점수를 mScore로 변경한다.
//     * 
//     * @param mID 업데이트할 병사의 고유번호
//     * @param mScore 새 평판 점수 (1 ≤ mScore ≤ 5)
//     */
//    public void updateSoldier(int mID, int mScore) {
//        int team = soldierTeam[mID];
//        if(team != 0) {
//            trees[team].updatePoint(mID, mScore);
//        }
//    }
//
//    /**
//     * @brief 팀 전체 병사 평판 일괄 업데이트 함수 (updateTeam)
//     * 특정 팀에 속한 모든 병사의 평판을 mChangeScore만큼 변경한다.
//     * 변경 시 평판 점수는 1과 5 사이로 클램핑된다.
//     * 
//     * @param mTeam 업데이트할 팀 번호 (1 ≤ mTeam ≤ 5)
//     * @param mChangeScore 평판 점수 변화량 (-4 ≤ mChangeScore ≤ 4)
//     */
//    public void updateTeam(int mTeam, int mChangeScore) {
//        // lazy 함수 f 생성: f(0)=0, 그리고 x(1~5)에 대해 f(x)= clamp(x + mChangeScore)
//        int[] f = new int[6];
//        f[0] = 0;
//        for (int i = 1; i <= 5; i++) {
//            f[i] = clamp(i + mChangeScore);
//        }
//        // 전체 구간(1 ~ MAX_MID)에 대해 lazy 함수 f 적용 (rangeUpdate)
//        trees[mTeam].rangeUpdate(1, MAX_MID, f);
//    }
//
//    /**
//     * @brief 최고 평판 병사 검색 함수 (bestSoldier)
//     * 특정 팀에서 평판 점수가 가장 높은 병사의 mID를 반환한다.
//     * 평판 점수가 같은 경우 mID가 가장 큰 병사를 선택한다.
//     * 
//     * @param mTeam 검색할 팀 번호 (1 ≤ mTeam ≤ 5)
//     * @return int 최고 평판 병사의 고유번호, 병사가 없으면 0 반환
//     */
//    public int bestSoldier(int mTeam) {
//        NodeData res = trees[mTeam].query(1, MAX_MID);
//        // 평판 5부터 1까지 내림차순으로 확인하며, 해당 평판을 가진 병사 중 최대 mID 반환
//        for (int score = 5; score >= 1; score--) {
//            if (res.count[score] > 0) {
//                return res.maxID[score];
//            }
//        }
//        return 0;  // 문제 조건 상 항상 병사가 존재함
//    }
//}
