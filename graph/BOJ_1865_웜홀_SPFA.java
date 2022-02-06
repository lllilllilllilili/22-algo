import java.io.*;
import java.util.*;

class Edge {
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

class Main {
    static int nodeCount, edgeCount, wormholeCount;
    static int[] costArr, cycleCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCaseCount = Integer.parseInt(br.readLine());
        // 테스트 케이스 loop
        mainLoop: for (int test = 0; test < testCaseCount; test++) {
            List<List<Edge>> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodeCount = Integer.parseInt(st.nextToken());
            edgeCount = Integer.parseInt(st.nextToken());
            wormholeCount = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= nodeCount; i++)
                list.add(new ArrayList<>());

            // 도시 인접리스트 생성
            for (int i = 0; i < edgeCount; i++) {
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list.get(startNode).add(new Edge(endNode, cost));
                list.get(endNode).add(new Edge(startNode, cost));
            }
            // 웜홀을 인접리스트에 추가
            for (int i = 0; i < wormholeCount; i++) {
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list.get(startNode).add(new Edge(endNode, cost * -1));
            }

            costArr = new int[nodeCount + 1];
            cycleCount = new int[nodeCount + 1];
            Arrays.fill(costArr, Integer.MAX_VALUE);
            for (int i = 0; i <= nodeCount; i++) {
                if (cycleCount[i] == 0 && SPFA(list, i)) {
                    bw.write("YES\n");
                    break mainLoop;
                }
            }
            bw.write("NO\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean SPFA(List<List<Edge>> list, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);

        costArr[startNode] = 0;
        cycleCount[startNode]++;

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            for (Edge nearEdge : list.get(nowNode)) {
                int targetNode = nearEdge.node;
                int cost = nearEdge.cost;
                if (costArr[targetNode] > costArr[nowNode] + cost) {
                    costArr[targetNode] = costArr[nowNode] + cost;
                    if (++cycleCount[targetNode] >= nodeCount) {
                        return true;
                    }
                    queue.offer(targetNode);
                }
            }
        }
        return false;
    }
}