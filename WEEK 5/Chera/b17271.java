import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b17271 {
	static int[] time;
	static int N, M;
	static long cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// A : 1초, B : M초
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		time = new int[]{1, M};
		count(0);
		System.out.println(cnt%1000000007);
	}
	
	static void count(int value) {
		if(value>N) {
			return;
		}
		
		if(value == N) {
			cnt++;
			return;
		}
		
		
	}
}
