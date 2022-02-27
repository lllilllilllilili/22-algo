import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[] arr;
	static StringBuilder sb;
	//중복조합
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		arr = new int[m];
		DFS(0,1);
		System.out.println(sb.toString());
	}
	static void DFS(int node, int next) {
		if(node==m) {
			for(int i=0; i<arr.length; i++)
				sb.append(arr[i]+" ");
			sb.append("\n");
			return ;
		}
		for(int i=next; i<=n; i++) {
			arr[node]=i;
			DFS(node+1, i);
		}
	}
}
