import static java.util.stream.Collectors.toList;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BOJ_1647_도시분할계획 {

    static int n, m;
    static final Scanner SCANNER = new Scanner(System.in);
    static int[] parent;
    static class Info implements Comparable<Info>{
        int oldHouse;
        int newHouse;
        int cost;

        public Info(int oldHouse, int newHouse, int cost) {
            this.oldHouse = oldHouse;
            this.newHouse = newHouse;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return this.cost-o.cost;
        }
    }

    public static int find(int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y)
            parent[x]=y;
    }
    public static void main(String[] args) throws Exception{
        String[] input = SCANNER.nextLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        List<Info> infoInput = new ArrayList<>(IntStream.range(0, m)
            .mapToObj(idx -> {
                String[] input2 = SCANNER.nextLine().split(" ");
                return new Info(Integer.parseInt(input2[0]),
                    Integer.parseInt(input2[1]),
                    Integer.parseInt(input2[2]));
            }).sorted().collect(toList()));


        Collections.sort(infoInput);

        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }


        //일단 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다. 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다.
        int ans = 0;
        int cost = 0;
        for(int i=0; i<infoInput.size(); i++){
            Info info = infoInput.get(i);
            if(find(info.newHouse)!=find(info.oldHouse)){
                ans+=info.cost;
                union(info.oldHouse, info.newHouse);
                cost = info.cost;
            }
        }
        System.out.println(ans - cost);
    }
}
