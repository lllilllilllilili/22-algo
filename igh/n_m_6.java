import java.util.*;
import java.io.*;
public class Main {

	static int n,m;
	static int[] temp_arr;
	static int[] arr;
	static StringBuilder sb;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		temp_arr = new int[n];
		arr = new int[m];
		visit = new boolean[n];
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			temp_arr[i]=Integer.parseInt(st.nextToken());
		}//end of for loop
		
		Arrays.sort(temp_arr);
		DFS(0,0);
		System.out.println(sb.toString());
	}
	static void DFS(int node, int next) {
		if(node==m) {
			for(int i=0; i<arr.length; i++)
				sb.append(arr[i]+" ");
			sb.append("\n");
			return ; 
		}
		for(int i=next; i<n; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			arr[node] = temp_arr[i];
			DFS(node+1, i+1);
			visit[i] = false; 
		}
	}
}
