import java.util.Scanner;

//백준_2785
//체인
public class practice02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 체인 갯수
        int[] chain = new int[N]; // 체인의 길이
        int sum = 0; // 전체 고리 수

        for (int i = 0; i < N; i++) {
            chain[i] = sc.nextInt();
            sum += chain[i];
        }

        // 최소 연결 횟수는 N-1임
        // 연결할 수 있는 최대 횟수 N-1, sum-1 비교
        System.out.println(Math.min(N-1, sum-1));
    }
}
