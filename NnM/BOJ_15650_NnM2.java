package study.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합기본
// 최종제출
public class BOJ_15650_NnM2 {
    static int N, M;
    static int arr[];
    static int sel[];   // output
    static boolean visited[];
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // init
        arr = new int[N];
        visited = new boolean[N];
        sel = new int[M];

        for (int i = 0; i < N; i++){
            arr[i] = i+1;
        }
        permute(0, 0);
        System.out.println(sb);

    }

    public static void permute(int idx, int depth) {
        // 깊이가 M이랑 같을경우 출력
        if (depth == M) {
            int x = 0;
            for (int j = 0; j < N; j++) {
                if(visited[j]) {
                    sb.append(sel[x++] + " ");
                }
            }
            sb.append('\n');
            return;
        }
        // i 는 at 부터 탐색하도록 한다.
        for (int i = idx; i < N; i++) {
            // 현재 깊이를 index로 하여 해당 위치에 i 값을 담는다
            // 중복을 허용하지 않으므로 사용하지 않은 자연수일 경우는
            if(!visited[i]) {
                visited[i] = true;
                sel[depth] = arr[i];
                // 위 자연수를 사용했으므로 idx + 1, depth + 1
                // 재귀호출 :  현재 i 값보다 다음 재귀에서 더 커야하므로
                // i + 1 의 값을 넘겨주고, 깊이 또한 1 증가시켜 재귀호출해준다
                permute(i + 1, depth+1);

                // 이 자연수를 사용하지 않을래.
                visited[i] = false;
            }

        }
    }
}
