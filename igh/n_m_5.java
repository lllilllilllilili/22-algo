import java.util.*;
import java.io.*;
public class Main {

	static int[] arr;
	static int[] temp_arr;
	static boolean[] visit;
	static StringBuilder sb;
	static int n,m;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		temp_arr = new int[n+1];
		visit = new boolean[n+1];
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			temp_arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(temp_arr);
		DFS(0);
		System.out.println(sb.toString());
	}
	static void DFS(int node) {
		if(node==m) {
			for(int i=0; i<arr.length; i++)
				sb.append(arr[i]+" ");
			sb.append("\n");
			
		}else {
			for(int i=1; i<=n; i++) {
				if(visit[i]) continue;
				visit[i]=true;
				arr[node]=temp_arr[i];
				DFS(node+1);
				visit[i]=false;
			}
		}
	}
}
