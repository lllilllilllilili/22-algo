import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Position{
    float x;
    float y;
    public Position(String x, String y) {
        this.x = Float.parseFloat(x);
        this.y = Float.parseFloat(y);
    }
}

class Edge implements Comparable<Edge>{
    int node;
    float wasteTime;
    public Edge(int node, float wasteTime) {
        this.node = node;
        this.wasteTime = wasteTime;
    }
    @Override
    public int compareTo(Edge o) {
        return (int) (this.wasteTime - o.wasteTime);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node=" + node +
                ", wasteTime=" + wasteTime +
                '}';
    }
}

class Main {
    static List<Position> positions = new ArrayList<Position>(102);
    static List<List<Edge>> edges = new ArrayList<>();
    static float[] timeArr;
    static Position start , end;
    static final float WALK = 5f;  // 5 m/s
    static final float FLY_WASTE_TIME = 2f; // 2초
    static final float FLY = 50f; // 대포에 올라타고 발사하고 착륙까지 2초 , 50m
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        start = new Position(line[0] , line[1]);
        line = br.readLine().split(" ");
        end = new Position(line[0], line[1]);

        int count = Integer.parseInt(br.readLine());

        // 시간 배열
        timeArr = new float[count + 2];

        // 시작점
        edges.add(new ArrayList<Edge>());
        timeArr[0] = Float.MAX_VALUE;
        positions.add(start);

        // 대포
        for(int i = 0 ; i < count ; i++) {
            line = br.readLine().split(" ");
            edges.add(new ArrayList<Edge>());
            timeArr[i + 1] = Float.MAX_VALUE;
            positions.add(new Position(line[0] , line[1]));
        }

        //끝점
        edges.add(new ArrayList<Edge>());
        timeArr[count + 1] = Float.MAX_VALUE;
        positions.add(end);

        // 그래프를 어떻게 형성할지??
        // 1. 시작점에서 각 대포와 끝점 까지 걸어가는 가중치
        for(int i = 1 ; i < count + 2 ; i++){
            edges.get(0).add(new Edge(i , (float) getDistance(positions.get(0) , positions.get(i)) / WALK));
        }

        // 2. 각 대포끼리와 끝점 까지의 가중치
        for(int i = 1 ; i < count + 2 ; i++){
            for(int j = 1 ; j < count + 2 ; j++){
                Position startPosition = positions.get(i);
                Position endPosition = positions.get(j);
                float distance = getDistance(startPosition , endPosition);
                float walk = distance / WALK;
                float fly = Math.abs(distance - FLY) / WALK + FLY_WASTE_TIME;
                float wasteTime = Math.min(walk , fly);
                edges.get(i).add(new Edge(j , wasteTime));
            }
        }

        dijkstra(0);
        System.out.println(timeArr[count + 1]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start , 0));
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            int nowNode = now.node;
            float nowWasteTime = now.wasteTime;
            for(Edge nearEdge : edges.get(nowNode)){
                float sumWasteTime = nearEdge.wasteTime + nowWasteTime;
                if(timeArr[nearEdge.node] > sumWasteTime){
                    timeArr[nearEdge.node] = sumWasteTime;
                    pq.offer(new Edge(nearEdge.node , sumWasteTime));
                }
            }
        }
    }

    public static float getDistance(Position obj1 , Position obj2) {
        float diffX = obj1.x - obj2.x;
        float diffY = obj1.y - obj2.y;
        return (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    }
}