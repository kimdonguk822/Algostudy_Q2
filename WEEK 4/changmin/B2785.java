package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * B2785 체인
 * https://www.acmicpc.net/problem/2785
 */

public class B2785 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //체인의 개수
		
		int[] chain = new int[N]; //체인길이
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			chain[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(chain); //정렬
		
		long sum = 0; 
		int answer = 0; 
		
		for(int i = 0; i < N; i++) {
			sum += chain[i];
			answer = N - i - 1;
			if(sum >= answer) {
				break;
			}
		}
		
		System.out.println(answer);
	}
}
