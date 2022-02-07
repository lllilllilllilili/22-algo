import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static class MST implements Comparable<MST>{
		int start;
		int end; 
		int value;
		MST(int start, int end, int value){
			this.start = start;
			this.end = end;
			this.value = value;
		}
		@Override
		public int compareTo(MST o) {
			// TODO Auto-generated method stub
			return this.value - o.value;
		}
	}
	static int[] parents;
	static ArrayList<MST> al = new ArrayList<>();
	static int find(int n) {
		if(parents[n] == n )
			return n;
		else
			return parents[n] = find(parents[n]);
	}
	
	static void merge(int u, int v) {
		int f_u = find(u);
		int f_v = find(v);
		if(f_u != f_v) {
			parents[f_v] = f_u;
		}
	}
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parents = new int[n+1];
		for(int i=1; i<=n; i++) {
			parents[i] = i;
		}//초기값을  주고
		
		for(int i=0; i<costs.length; i++) {
			al.add(new MST(costs[i][0],costs[i][1],costs[i][2]));
		}//start, end, value 넘겨주고 
		
		int ans = 0;
		Collections.sort(al);
		for(int i=0; i<al.size(); i++) {
			int u = al.get(i).start;
			int v = al.get(i).end;
			int value = al.get(i).value;
			if(find(u)!=find(v)) {
				merge(u,v);
				ans += value;
			}
		}//end of for 
		
        return ans;
    }
}
