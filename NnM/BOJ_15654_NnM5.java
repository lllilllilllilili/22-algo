package study.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최종제출
public class BOJ_15654_NnM5 {
    static int N, M;
    static int[] arr, sel, visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sel = new int[M];
        visited = new int[N];

        st = new StringTokenizer( br.readLine() );
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt( st.nextToken() );
        }
        Arrays.sort(arr);

        permute(0);

        System.out.println(sb);

    }

    public static void permute(int depth) {
        if(depth == M) {
            for( int val : sel ){
                sb.append(val).append( " " );
            }
            sb.append("\n");
            return;
        }

        for( int i = 0; i < N; i++ ) {
            if( visited[i] != 1 ) {
                sel[depth] = arr[i];
                visited[i] = 1;
                permute(depth + 1);
                visited[i] = 0;
            }
        }
    }

}
