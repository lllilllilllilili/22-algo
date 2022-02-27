package study.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최종제출 6에서 중복체크 제외
public class BOJ_15656_NnM7 {
    static int N, M;
    static int[] arr, sel;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];   // input배열에 N개의 자연수를 입력받고 오름차순 정렬한다.
        sel = new int[M];

        st = new StringTokenizer( br.readLine() );
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt( st.nextToken() );
        }

        Arrays.sort(arr);
        dfs(0);

        System.out.println(sb);

        br.close();
    }

    public static void dfs(int depth) {
        if(depth == M) {
            for(int i = 0; i < M; i++){
                sb.append(sel[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for( int i = 1; i <= N; i++ ) {
            sel[depth] = arr[i - 1];
            dfs( depth + 1);
        }
    }


}
