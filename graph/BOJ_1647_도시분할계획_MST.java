package study.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도시 분할 게획 최종제출
public class BOJ_1647_도시분할계획_MST {

    static class Node implements Comparable<Node>{
        int sr, eg, ve;
        Node(int sr, int eg, int ve){
            this.sr = sr;
            this.eg = eg;
            this.ve = ve;
        }

        @Override
        public int compareTo(Node o) {
            return this.ve - o.ve;
        }
    }

    static int n, m, min;   // 간선
    static int parents[];
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            //sr번 집과 eg번 집을 연결하는 길의 유지비가 ve
            int sr = Integer.parseInt(st.nextToken());
            int eg = Integer.parseInt(st.nextToken());
            int ve = Integer.parseInt(st.nextToken());

            // 우선순위 큐는 자동으로 간선 비용순(오름차순)으로 정렬된다.
            pq.add(new Node(sr - 1, eg - 1, ve));
        }

        // N개의 집에서 최소비용으로 N-1개의 간선을 이용해 모든 집을 연결할 수 있다.
        parents = new int[n];
        Arrays.fill(parents, -1);

        // int mstKruskal = mstKruskal();
        mstKruskal();

        System.out.println(min);

    }

    private static void mstKruskal() {
        int num = 0;
        // 사이클이 발생하지 않는 경우에만 집합에 포함
        while (!pq.isEmpty()){
            Node temp = pq.poll();

            if (union(temp.sr, temp.eg)){   // 연결
                min += temp.ve;             // 정점 갱신
                num++;                      // 가장 마지막으로 연결된 집 사이의 유지비가 가장 큼
            }
            if (num == n - 2){
                break;
            }
        }
    }

    // union 노드 집합을 합치는 합집합
    private static boolean union(int sr, int eg) {
        int aRoad = findRoad(sr);
        int bRoad = findRoad(eg);

        // 두 집이 연결되어 있지 않다면
        if (aRoad != bRoad){
            parents[bRoad] = aRoad;
            return true;
        }
        return false;
    }

    // find
    // 노드가 속한 집합의 루트 노드 찾는
    private static int findRoad(int sr){
        if (parents[sr] < 0){
            return sr;
        }

        return parents[sr] = findRoad(parents[sr]);
    }

}
