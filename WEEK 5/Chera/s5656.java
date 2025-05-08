import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class s5656 {
	static int N, H, W;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 슈팅 수
			W = Integer.parseInt(st.nextToken()); // 너비 (열)
			H = Integer.parseInt(st.nextToken()); // 높이 (행)
			
			int[][] arr = new int[H][W];
			for(int i = 0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
		}
	}

	static void brick(int[][] arr, int cnt) {
		int[] dr = {0, 0, -1, 1};
		int[] dc = {1, -1, 0 , 0};
		
		if(cnt==N) {
			return;
		}
		int[][] copy = new int[H][W];
		for(int i = 0; i<H; i++) {
			copy[i]=Arrays.copyOf(arr[i], arr[i].length);
		}
		for(int i = 0; i<H; i++) {
			for(int j = 0; j<W; j++) {
				if(copy[i][j]==0) continue;
				
				int n = copy[i][j];
				
				for(int k = n; k>0; k--) {
					for(int d = 0; d<dr.length; d++) {
						
					}
				}
				
			}
		}
	}
}
