package algo;

import java.util.Arrays;
import java.util.Scanner;

public class B2785 {
	// 체인 https://www.acmicpc.net/problem/2785

	// 하나의 긴 체인으로 연결하기 위해 열고 닫아야 하는 최소한의 고리 수

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] chains = new int[N];

		for(int i = 0; i < N; i++) {
			chains[i] = sc.nextInt();
		}
		
		Arrays.sort(chains); //정렬
		
		long sum = 0; 
		int answer = 0; 
		
		for(int i = 0; i < N; i++) {
			sum += chains[i];
			answer = N - i - 1;
			if(sum >= answer) {
				break;
			}
		}
		
		System.out.println(answer);
	}
}
