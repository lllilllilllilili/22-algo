package study.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최종제출
public class BOJ_15657_NnM8 {
    static int N, M;
    static int[] arr, sel;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sel = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt( st.nextToken() );
        }

        Arrays.sort(arr);
        permute(0, 0);

        System.out.println(sb);

        br.close();
    }

    public static void permute(int cnt, int depth) {
        if (cnt == M) {
            for (int i = 0; i < M; ++i) {
                sb.append(sel[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = depth; i < N; ++i) {
            sel[cnt] = arr[i];
            permute(cnt + 1, i);
        }
    }
}
