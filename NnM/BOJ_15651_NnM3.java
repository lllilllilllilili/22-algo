package study.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최종제출 중복포함
// 이전의 문제는 1을 탐색하고, 방문했을경우 2를탐색하는 식으로 dfs를 돌렸으나,
// 이 문제는 1을 탐색하고 다시 1을 탐색한다.(cnt가 주어진 M과 같을때까지) - 완전탐색.
public class BOJ_15651_NnM3 {
    static int N, M;
    static int arr[];
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        dfs(0);

        System.out.println(sb);
        br.close();

    }

    // DFS
    public static void dfs(int cnt) {
        if (cnt == M) {
            for (int i : arr){
                sb.append(i + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++){
            arr[cnt] = i;
            dfs(cnt + 1);
        }
    }

}
