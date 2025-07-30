import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        for (String op : operations) {
            String[] parts = op.split(" ");
            String command = parts[0];
            int number = Integer.parseInt(parts[1]);

            if (command.equals("I")) {
                minPQ.offer(number);
                maxPQ.offer(number);
            } else if (command.equals("D")) {
                if (minPQ.isEmpty()) continue;  // 삭제 명령은 비어있으면 무시

                if (number == 1) {
                    int max = maxPQ.poll();
                    minPQ.remove(max);
                } else if (number == -1) {
                    int min = minPQ.poll();
                    maxPQ.remove(min);
                }
            }
        }

        if (minPQ.isEmpty()) return new int[]{0, 0};
        return new int[]{maxPQ.peek(), minPQ.peek()};
    }
}
