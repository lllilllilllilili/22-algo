import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	private static int N,L,R;
	private static int[][] map;
	private static int[][] visit;
	private static int cnt;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int abs(int a, int b) {
		if(a>b)
			return a-b;
		else
			return b-a;
	}
	private static boolean BFS() {
		
		Stack<int[]> s = new Stack<>();
		int sum = 0;
		visit = new int[N][N];
		boolean test = false;
		
		for(int i=0; i<N; i++) {	
			for(int j=0; j<N; j++) {
				if(visit[i][j] == 0) {
					visit[i][j] = 1;
					Queue<int[]> q = new LinkedList<>();
					
					q.add(new int[] {i,j});
					s.add(new int[] {i,j});
					sum=map[i][j];
					while(!q.isEmpty()) {
					
					int[] temp = q.remove();
						
					for(int ii=0; ii<4; ii++) {
						int ddx = temp[0]+dx[ii];
						int ddy = temp[1]+dy[ii];
						
						if(ddx<0 || ddx>=N || ddy<0 || ddy>=N) continue;
						
						if(visit[ddx][ddy] == 0 && (abs(map[temp[0]][temp[1]],map[ddx][ddy])>=L && abs(map[temp[0]][temp[1]],map[ddx][ddy])<=R )) {
							visit[ddx][ddy] = 1;
							s.add(new int[] {ddx,ddy});
							q.add(new int[] {ddx,ddy});
							sum+=map[ddx][ddy];
							test= true;
							
							}
						}
					}
					if(s.size()!=0) {
						int value = sum/s.size();
						int len = s.size();
						for(int z=0; z<len; z++) {
							int[] index = s.pop();
							map[index[0]][index[1]] = value;
						}
					}
					
				}
			}
			
		}
		
		return test;
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end of for loop
		
		
		
		while(true) {
			if(BFS()) {
				cnt+=1;
			}
			else
				break;
		}
		System.out.println(cnt);
	}

}
