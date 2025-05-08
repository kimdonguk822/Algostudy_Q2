/**
 * 문제 : 백준 1713번 후보 추천하기
 * 메모리 : 14872KB
 * 시간 : 120ms
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class b1713 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int N = Integer.parseInt(br.readLine());
		int total = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[101];
		
		List<Integer> photo = new LinkedList<>();
		
		for(int i = 0; i<total; i++) {
			int idx = Integer.parseInt(st.nextToken());
			if(photo.size()==N && arr[idx]==0) {
				int min = Integer.MAX_VALUE;
				int delStudent = 0;
				int delIdx = 0;
				for(int j = 0; j<photo.size(); j++) {
					if(arr[photo.get(j)]<min) {
						delStudent = photo.get(j);
						min = arr[delStudent];
						delIdx = j;
					}
				}
				photo.remove(delIdx);
				arr[delStudent] = 0;
			}
			if(arr[idx]==0) {
				photo.add(idx);
			}
			arr[idx]++;
		}
		
		Collections.sort(photo);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<photo.size(); i++) {
			sb.append(photo.get(i)+" ");
		}
		
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
	}
}
