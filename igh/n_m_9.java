import java.util.*;
import java.io.*;
public class Main {

	static int n,m;
	static int[] arr;
	static boolean[] visit;
	static int[] temp_arr;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		temp_arr = new int[n];
		visit = new boolean[n];
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			temp_arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(temp_arr);
		DFS(0);
		System.out.println(sb.toString());
	}
	static void DFS(int node) {
		if(node==m) {
			for(int i=0; i<arr.length; i++) {
				sb.append(arr[i]+" ");
			}
			sb.append("\n");
			return ;
		}
		int num=0;
		for(int i=0; i<n; i++) {
			if(!visit[i] && num!=temp_arr[i]) {
				visit[i] =true;
				num = temp_arr[i];
				arr[node]=temp_arr[i];
				DFS(node+1);
				visit[i]=false; 
			}
		}
	}
}
