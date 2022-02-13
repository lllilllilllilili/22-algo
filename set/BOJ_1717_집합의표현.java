import java.util.*;
import java.io.*;
public class Main {

	static int[] arr;
	static int find(int number) {
		//DFS로 타고 타고 들어가서 if 문까지 오게 되면 return number 로 해당 집합의 값을 타고 타고 간다. 
		//트리의 하나의 부분을 DFS로 타고타고 들어간다고 생각하면 됩니다. ^__^
		if(arr[number]==number)
			return number;
		else
			return arr[number]=find(arr[number]);
	}
	static void Union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		//노드의 압축은 고려하지 않음, 문제가 복잡해지면 노드의 경로를 압축해야 함 
		arr[fa]=fb;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1];
		//1. arr 값을 i로 초기화 해야 한다. = i번 원소가 속하는 집합의 번호, i번이 서로 다른 집합이 됩니다.
		for(int i=1; i<=n; i++)
			arr[i]=i;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int judge = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//2. find 함수로 집합이 서로 같은지 확인합니다. 
			if(judge==1) {
				if(find(a)!=find(b))
					System.out.println("NO");
				else
					System.out.println("YES");
			}else {
				//3. a,b 트리의 루트를 찾은 뒤에 다른 한쪽의 자손으로 넣어 트리를 합치게 됩니다.
				Union(a,b);
			}
		}
	}
}
