import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
   int startNode;
   int endNode;
   int cost;

   public Edge(int startNode, int endNode, int cost) {
       this.startNode = startNode;
       this.endNode = endNode;
       this.cost = cost;
   }

   @Override
   public int compareTo(Edge o) {
       return this.cost - o.cost;
   }

   @Override
   public String toString() {
       return "Edge{" +
               "startNode=" + startNode +
               ", endNode=" + endNode +
               ", cost=" + cost +
               '}';
   }
}

class Solution {
    int[] relation;
    public int solution(int n, int[][] costs) {
        List<Edge> list = new ArrayList<>();
        relation = new int[n];

        for(int i = 0 ; i < n ; i++) relation[i] = i;
        for(int i = 0 ; i < costs.length ; i++) {
            int[] line = costs[i];
//            Arrays.stream(line).forEach(value -> System.out.print(value + " "));
//            System.out.println();
            list.add(new Edge(line[0] , line[1] , line[2]));
        }

        // Edge 객체 비용 오름차순 정렬
        Collections.sort(list);

//        System.out.println(list);

        int answer = 0;

        for(Edge edge : list){
            answer += Union(edge);
        }

        return answer;
    }

    public int Union(Edge edge){
        int startNode = Find(edge.startNode);
        int endNode = Find(edge.endNode);
        if(startNode != endNode){
            relation[startNode] = endNode;
            return edge.cost;
        }
        return 0;
    }

    public int Find(int node){
        if(node == relation[node])
            return node;
        return relation[node] = Find(relation[node]);
    }
}