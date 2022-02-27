import java.util.*;
import java.io.*;
public class Main {

	static int[] arr;
	static int[] k_arr;
	static int N,M;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		k_arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		DFS(0,0);
	}
	static void DFS(int node, int next) {
		if(node==M) {
			for(int i=0; i<k_arr.length; i++)
				System.out.print(k_arr[i]+" ");
			System.out.println();
			return ;
		}else {
			int temp = 0;
			for(int i=next; i<arr.length; i++) {
				if(temp!=arr[i]) {
					temp=arr[i];
					k_arr[node]=arr[i];
					DFS(node+1,i+1);
				}
			}
		}
	}
}
