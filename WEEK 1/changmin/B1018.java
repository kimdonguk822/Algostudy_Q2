import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*	B7576 토마토
 * 	https://www.acmicpc.net/problem/7576
 */
public class B7576 {
	//탐색용
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static int M,N; //박스 사이즈
	static int[][] box; //토마토가 들어있는 박스
	static int day = 0; //몇일째인지, 백준이여서 맞았지만 SWEA에서는 여기서 초기화 하면 틀려욧
	static Queue<int[]> queue = new LinkedList<>(); //이것도 마찬가지 BFS처음할때 풀었던 코드 긁어와서ㅎ..
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); //가로칸수
		N = Integer.parseInt(st.nextToken()); //세로칸수
		
		box = new int[N][M]; //지만 반대로 넣어야한다..
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) { //처음부터 익은 토마토면 큐에 삽입
					queue.offer(new int[]{i,j});
				}
			}
		}
		
		bfs(); //bfs를 돌리고 ( 토마토를 익혀보고 )
		
		boolean ok = true; //일단 true
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(box[i][j] == 0) { //아직 익지 않은 토마토면
					ok = false; //false로 바꿔주고
					break; //중지
				}
			}
			if(!ok) { //ok가 false면 탈출 (라벨로 하면 이거 안해도)
				break;
			}
		}
		
		if(ok) { //다 익었으면 날짜 출력
			bw.write(day+"");
		} else { //실패했으면 -1 출력
			bw.write(-1 + "");
		}
		bw.flush();
		bw.close();
		br.close();
		
		
		
	}
	
	public static void bfs() {
		while(!queue.isEmpty()) { //큐가 빌때까지
			int size = queue.size(); //같은 날 넣은 토마토만큼은 돌아야함
			boolean ok = false; //일단 false로
			
			for(int i = 0; i < size; i++) {
				int[] findXY = queue.poll(); //방금익은 토마토가 있던 좌표
				int x = findXY[0];
				int y = findXY[1];
				for(int j = 0; j < 4; j++) { //의 주변 4방향을 탐색
					int dx = x + di[j];
					int dy = y + dj[j];
					if(dx >= 0 && dx < N && dy >= 0 && dy < M && box[dx][dy] == 0) { //상자 안쪽이고, 안익은 토마토면
						box[dx][dy] = 1; //익혀주고
						queue.offer(new int[] {dx, dy}); //큐에 넣음
						ok = true; //한번이라도 익었으면 true
					}
				}
				
			}
			if(ok) { //만약 아무 토마토도 익지 않은 날이이면 날짜를 넘기지 않음
				day++;
			}
			
		}
	}
	
}
