package changmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 *  문제: 15683 감시
 *  링크: https://www.acmicpc.net/problem/15683
 */
public class B15683 {
	
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int[][][] aos = {
		    {},
		    {{0},{1},{2},{3}},                         // type 1
		    {{0,1},{2,3}},                             // type 2
		    {{0,2},{2,1},{1,3},{3,0}},                 // type 3
		    {{0,1,2},{0,2,3},{0,1,3},{1,2,3}},         // type 4
		    {{0,1,2,3}}                                // type 5
		};
	
	static int N, M; //가로세로
	static int[][] office; //사무실 상태
	static boolean[][] see; //감시구역
	static List<Cctv> cctvs; //cctv정보관리
	static int[] dirs; 
	
	
	static int min; //최소 사각지대
	
	static class Cctv {
		
		int type, r, c, dir; //종류 좌표 방향

		public Cctv(int type, int r, int c, int dir) {
			this.type = type; //cctv타입
			this.r = r; 
			this.c = c;
			this.dir = dir; //방
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		office = new int[N][M];
		see = new boolean[N][M];
		cctvs = new ArrayList<>();
		min = Integer.MAX_VALUE;
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if(office[i][j] > 0 && office[i][j] < 6) {
					Cctv cctv = new Cctv(office[i][j], i, j, 0);
					cctvs.add(cctv);
				}
			}
		}
		
		dirs = new int[cctvs.size()];
		bt(0);
		
		System.out.println(min);
	}
	
	public static void bt(int depth) { 
		if(depth == cctvs.size()) { //모든cctv의 상태를 정한후
			setDir(); //방향 설정
			goSee(); //바라보기
			int black = cal(); //세기
			min = Math.min(black, min); //비교하기
			return;
		}
		
		Cctv cctv = cctvs.get(depth);
		int type = cctv.type;
		
		for(int i = 0; i < aos[type].length; i++) {
			dirs[depth] = i;
			bt(depth+1);
		}
		
	}
	
	public static int cal() {
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(office[i][j] == 0 && !see[i][j]) count++;
				if(count > min) return count;
			}
		}
		return count;
	}
	
	public static void setDir() { //cctv들 방향설정
		for(int i = 0; i < cctvs.size(); i++) {
			cctvs.get(i).dir = dirs[i];	
		}
	}
	
	public static void goSee() {
	    see = new boolean[N][M];

	    for (Cctv cc : cctvs) {
	        int r = cc.r;
	        int c = cc.c;
	        int dir = cc.dir;
	        int type = cc.type;
	        see[r][c] = true;
	        for(int d : aos[type][dir]) {
	        	int nr = r;
	        	int nc = c;
	        	
	        	while(true) {
	        		nr += di[d];
	        		nc += dj[d];
	        		if(nr < 0 || nc < 0 || nr >= N || nc >= M) break;
	        		if(office[nr][nc]== 6) break;
	        		
	        		see[nr][nc] = true;
	        	}
	        }
	    }
	        
	}
}
