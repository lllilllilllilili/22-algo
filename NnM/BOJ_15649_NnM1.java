package study.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열기본
// 브루트포스와 재귀 카테고리 , 최종제출
// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
// 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
// 수열은 사전 순으로 증가하는 순서로 출력
public class BOJ_15649_NnM1 {
    static int N, M;	// 정적변수
    static int arr[];
    static boolean[] visit;  // 체크 여부
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정적변수 N과 M을 초기화해준다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visit = new boolean[N];

        // 정적변수를 쓰면 되기 때문에 N과 M을 넘겨줄 필요 없지만 사용, 사용하지 않으면 코드는 클린해진다.
        permute(N, M, 0);
        System.out.println(sb);

        br.close();

    }

    // DFS
    public static void permute(int N, int M, int depth) {
        // 2번째 index의 숫자 결정, 재귀 사용 깊이가 M이랑 같을경우 출력
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        // false 라면 true ,, 재귀하면서 백트래킹 할 arr 배열 i값 재귀 반복
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i + 1;
                permute(N, M, depth + 1);
                visit[i] = false;
            }
        }
    }
}
