package study.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최종제출
public class BOJ_15655_NnM6 {
    static int N, M;
    static int[] arr, sel;
    static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];   // input배열에 N개의 자연수를 입력받고 오름차순 정렬한다.
        sel = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer( br.readLine() );
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt( st.nextToken() );
        }

        Arrays.sort(arr);
        dfs(0, 0);

        System.out.println(sb);

        br.close();
    }

    public static void dfs(int idx, int depth) {
        if(depth == M) {
            for(int i = 0; i < M; i++){
                sb.append(sel[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for( int i = idx; i < N; i++ ) {
            if(visited[i] ) {
                continue;
            }
            visited[i] = true;
            sel[depth] = arr[i];
            dfs(i, depth + 1);
            visited[i] = false;
        }
    }


}
