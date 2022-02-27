import java.util.*;
import java.io.*;
public class Main {

	static int[] arr;
	static boolean[] visit;
	static int n,m;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		arr = new int[m];
		visit = new boolean[n+1];
		DFS(0);
	}
	static void DFS(int node) {
		if(node==m) {
			for(int i=0; i<arr.length; i++)
				System.out.print(arr[i]+" ");
			System.out.println();
			return ;
		}
		for(int i=1; i<=n; i++) {
			if(visit[i]) continue;
			visit[i]=true;
			arr[node]=i;
			DFS(node+1);
			visit[i]=false;
		}
	}
}
