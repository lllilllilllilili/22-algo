import java.io.*;
import java.util.*;

class Main {
    static int[][] map;
    static int[][] dp;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());

        map = new int[size][size];
        dp = new int[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    dp[i][j] = 1;
            }
        }

        int max = Integer.MIN_VALUE;

        // 0행 초기화
        int prev1 = 0;
        for (int i = 0; i < size; i++) {
            if (prev1 == getNumber(map[0][i])) {
                if (i - 1 >= 0) {
                    dp[0][i] = dp[0][i - 1] + 1;
                    prev1 = map[0][i];
                    max = Math.max(max, dp[0][i]);
                }
            }
        }

        // 0열 초기화
        int prev2 = 0;
        for (int i = 0; i < size; i++) {
            if (prev2 == getNumber(map[i][0])) {
                if (i - 1 >= 0) {
                    dp[i][0] = dp[i - 1][0] + 1;
                    prev2 = map[i][0];
                    max = Math.max(max, dp[i][0]);
                }
            }
        }

        // 초기화 해놓은 0행과 0열을 참고하여 나아간다.
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                int rowPrev = map[i][j - 1];
                int colPrev = map[i - 1][j];
                int now = map[i][j];
                int expect = getNumber(now);
                if (rowPrev == expect && colPrev == expect)
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + 1;
                else if (rowPrev == expect)
                    dp[i][j] = dp[i][j - 1] + 1;
                else if (colPrev == expect)
                    dp[i][j] = dp[i - 1][j] + 1;
                else if (rowPrev == now)
                    dp[i][j] = dp[i][j - 1];
                else if (colPrev == now)
                    dp[i][j] = dp[i - 1][j];
                max = Math.max(max, dp[i][j]);
            }
        }

        // for(int i = 0 ; i < size ; i++){
        // System.out.println();
        // for(int j = 0 ; j < size ; j++) System.out.print(dp[i][j]);
        // }

        bw.append(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getNumber(int now) {
        if (now == 0)
            return 2;
        else if (now == 1)
            return 0;
        return 1;
    }

}