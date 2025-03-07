package B형특강.swea_Pro_14613_리스트복사;

import java.util.*;

class UserSolution {

    // 각 리스트의 이름을 key로, Container(리스트 상태)를 value로 관리
    private HashMap<String, Container> lists = new HashMap<>();

    // Container: 리스트의 persistent 트리 루트와 길이를 보관
    static class Container {
        Node root;
        int n;
        Container(Node root, int n) {
            this.root = root;
            this.n = n;
        }
    }

    // persistent 세그먼트 트리의 노드 (리프에는 실제 값, 내부 노드는 자식 포인터만 사용)
    static class Node {
        int val;
        Node left, right;
        // 리프 노드용 생성자
        Node(int val) {
            this.val = val;
        }
        // 내부 노드용 생성자
        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }

    // char 배열 (마지막에 '\0' 포함)을 String으로 변환 (문자열 길이는 최대 20)
    private String charToString(char[] arr) {
        int len = 0;
        while (len < arr.length && arr[len] != '\0') {
            len++;
        }
        return new String(arr, 0, len);
    }

    // [l, r] 구간에 대해 persistent 트리 빌드 (리프에 원소 값 저장)
    private Node build(int[] arr, int l, int r) {
        if (l == r) {
            return new Node(arr[l]);
        }
        int mid = (l + r) / 2;
        Node left = build(arr, l, mid);
        Node right = build(arr, mid + 1, r);
        return new Node(left, right);
    }

    // persistent 트리 update: 인덱스 index의 값을 value로 업데이트하여 새로운 노드 반환
    private Node update(Node node, int l, int r, int index, int value) {
        if (l == r) {
            return new Node(value);
        }
        int mid = (l + r) / 2;
        if (index <= mid) {
            Node newLeft = update(node.left, l, mid, index, value);
            return new Node(newLeft, node.right);
        } else {
            Node newRight = update(node.right, mid + 1, r, index, value);
            return new Node(node.left, newRight);
        }
    }

    // persistent 트리 query: 인덱스 index의 값을 반환
    private int query(Node node, int l, int r, int index) {
        if (l == r) {
            return node.val;
        }
        int mid = (l + r) / 2;
        if (index <= mid) {
            return query(node.left, l, mid, index);
        } else {
            return query(node.right, mid + 1, r, index);
        }
    }

    // 테스트케이스 시작 시 호출: 기존에 저장된 리스트 모두 삭제
    public void init() {
        lists.clear();
    }

    // 새로운 리스트 생성: mListValue 배열의 처음 mLength 원소를 이용하여 persistent 트리 빌드
    public void makeList(char mName[], int mLength, int mListValue[]) {
        String name = charToString(mName);
        Node root = build(mListValue, 0, mLength - 1);
        Container container = new Container(root, mLength);
        lists.put(name, container);
    }

    // copyList: mCopy가 true이면 deep copy, false이면 shallow copy
    public void copyList(char mDest[], char mSrc[], boolean mCopy) {
        String destName = charToString(mDest);
        String srcName = charToString(mSrc);
        Container srcContainer = lists.get(srcName);
        if (mCopy) {
            // deep copy: 새 Container 객체를 생성하여 독립적인 상태로 만듦
            Container newContainer = new Container(srcContainer.root, srcContainer.n);
            lists.put(destName, newContainer);
        } else {
            // shallow copy: 같은 Container 객체의 참조를 할당
            lists.put(destName, srcContainer);
        }
    }

    // updateElement: mName 리스트의 mIndex 원소를 mValue로 업데이트 (업데이트 시 새 persistent 노드들만 생성)
    public void updateElement(char mName[], int mIndex, int mValue) {
        String name = charToString(mName);
        Container container = lists.get(name);
        container.root = update(container.root, 0, container.n - 1, mIndex, mValue);
    }

    // element: mName 리스트의 mIndex 원소를 반환 (persistent 트리에서 점 쿼리 수행)
    public int element(char mName[], int mIndex) {
        String name = charToString(mName);
        Container container = lists.get(name);
        return query(container.root, 0, container.n - 1, mIndex);
    }
}


/// 오답
/// 채점용 input 파일로 채점한 결과 fail 입니다.
/// (오답 : 25개의 테스트케이스 중 4개가 맞았습니다.)
/// 런타임 에러가 발생하였습니다. 런타임 에러로 전체 혹은 일부 테스트 케이스는 채점이 되지 않을 수 있습니다.
/// Error Message : Runtime Error!

//class UserSolution {
//
//    // 리스트 이름과 해당 리스트 객체를 매핑하는 HashMap
//    private HashMap<String, MyList> lists = new HashMap<>();
//
//    // 내부 클래스: 리스트 데이터를 저장하는 객체
//    static class MyList {
//        int[] arr;
//        MyList(int[] arr) {
//            this.arr = arr;
//        }
//    }
//    
//    // char 배열(문자열 끝에 '\0' 포함)을 Java의 String으로 변환하는 헬퍼 함수
//    private String charToString(char[] arr) {
//        int len = 0;
//        // 배열의 끝 혹은 '\0'이 나올 때까지 읽음
//        while (len < arr.length && arr[len] != '\0') {
//            len++;
//        }
//        return new String(arr, 0, len);
//    }
//    
//    // 각 테스트케이스 시작 시 호출: 기존에 저장된 리스트들을 모두 초기화
//    public void init() {
//        lists.clear();
//    }
//    
//    // 새로운 리스트 생성
//    // mName : 리스트 이름, mLength : 길이, mListValue : 원소 값 배열
//    public void makeList(char mName[], int mLength, int mListValue[]) {
//        String name = charToString(mName);
//        // mLength 크기의 새로운 배열 할당 후, 원소 복사
//        int[] arr = new int[mLength];
//        // System.arraycopy를 사용하여 빠르게 복사
//        System.arraycopy(mListValue, 0, arr, 0, mLength);
//        // 새로운 MyList 객체 생성 후, 해시맵에 저장
//        lists.put(name, new MyList(arr));
//    }
//    
//    // 리스트 복사 함수
//    // mDest : 복사하여 생성될 리스트 이름, mSrc : 원본 리스트 이름, mCopy : 깊은 복사 여부
//    public void copyList(char mDest[], char mSrc[], boolean mCopy) {
//        String destName = charToString(mDest);
//        String srcName = charToString(mSrc);
//        MyList srcList = lists.get(srcName);
//        
//        if (mCopy) {
//            // 깊은 복사: 원본 배열을 새 배열에 복사하여 새로운 MyList 생성
//            int len = srcList.arr.length;
//            int[] newArr = new int[len];
//            System.arraycopy(srcList.arr, 0, newArr, 0, len);
//            lists.put(destName, new MyList(newArr));
//        } else {
//            // 얕은 복사: 원본 MyList 객체의 참조를 그대로 할당
//            lists.put(destName, srcList);
//        }
//    }
//    
//    // 리스트의 특정 인덱스 값을 수정
//    public void updateElement(char mName[], int mIndex, int mValue) {
//        String name = charToString(mName);
//        MyList list = lists.get(name);
//        list.arr[mIndex] = mValue;
//    }
//    
//    // 리스트의 특정 인덱스 값을 반환
//    public int element(char mName[], int mIndex) {
//        String name = charToString(mName);
//        MyList list = lists.get(name);
//        return list.arr[mIndex];
//    }
//}
