// 문제: 백준 2564번 
// 문제제목 : 경비원
// 링크: https://www.acmicpc.net/problem/2564
// 메모리 : 14232KB
// 시간: 104ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Info{
		int dir, dis;

		public Info(int dir, int dis) {
			this.dir = dir;
			this.dis = dis;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		
		//방향, 거리 정보 저장된 클래스 배열 생성
		Info[] guard = new Info[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			guard[i] = new Info(dir, dis);
		}
		st = new StringTokenizer(br.readLine());
		int dongdir = Integer.parseInt(st.nextToken());
		int dongdis = Integer.parseInt(st.nextToken());
		
		
		int sum = 0;
		//일직선으로 펼친다고 생각하고 방향별로 최소거리 구하기!
		
		int res = 0;
		if (dongdir == 1) { //북쪽
			res = dongdis;
		}else if (dongdir == 2) { //남쪽
			res = 2*W+H-dongdis;
		}else if (dongdir == 3) { //서쪽
			res = 2*(W+H)-dongdis;
		}else {
			res = W+dongdis;
		}
		
		int diff = 0;
		int finres = 0;
		for (int i = 0; i < N; i++) {
			int comp = guard[i].dis;
			int cir = 2*(W+H);
			if (guard[i].dir == 1) { //북쪽
				sum = comp;
			}else if (guard[i].dir == 2) { //남쪽
				sum = 2*W + H-comp;
			}else if (guard[i].dir == 3) { //서쪽
				sum = 2*(W+H)-comp;
			}else { //동쪽
				sum = W+comp;
			}
			int temp = Math.abs(res-sum);
			diff = Math.min(temp, 2*(W+H)-temp);
			finres += diff;
		}
		
		System.out.println(finres);

	}

}
