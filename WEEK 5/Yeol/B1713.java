// 문제: 백준 1713번
// 문제제목 : 후보추천하기
// 링크: https://www.acmicpc.net/problem/1713
// 메모리 : 14332KB
// 시간: 112ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1713_후보추천하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[] students = new int[101];// 추천 수 저장. 100명. 1부터 사용
		int N = Integer.parseInt(br.readLine());// 사진 틀 개수(1~20 사이)
		int recommends = Integer.parseInt(br.readLine());// 총 추천수(1000 이하)
		Queue<Integer> q = new ArrayDeque<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < recommends; i++) {
			int curr = Integer.parseInt(st.nextToken());// 하나씩 읽어옴

			// 이미 추천 받았던 사람이면
			if (students[curr] > 0) {
				students[curr]++;// 추천 수 증가
			}

			//액자에 자리가 남았다면
			else if(q.size() < N) {
				students[curr]++;// 추천 수 증가
				q.add(curr);// 큐에 넣기
			}
			
			//액자가 꽉 찼다면
			else {
				// 지금까지 추천 받은 사람들 중에서 추천 수가 가장 적은 사람 찾기
				int min = Integer.MAX_VALUE;// 가장 적은 추천 수
				int minIdx = 0;// 가장 적은 추천을 받은 사람의 위치
				int minCnt = 0;// 가장 적은 추천 수의 개수

				for (int j = 1; j <= 100; j++) {
					// 최소가 1이겠지
					if (students[j] > 0) {
						// 현재 최솟값보다 작다면
						if (students[j] < min) {
							min = students[j];// 최솟값 갱신
							minIdx = j;// 추천 수가 가장 적은 사람의 위치 갱신
							minCnt = 1;// 최솟값의 개수를 1로 초기화
						}
						// 현재 최솟값과 같다면
						else if (students[j] == min) {
							minCnt++;// 최솟값의 개수 1 증가
						}
					}
				}
				// 찾았으면 그 사람의 사진 삭제
				// 추천 수가 가장 적은 사람이 한명이면 그 사람의 사진 삭제
				if (minCnt == 1) {
					q.remove(minIdx);// 큐에서 삭제
					students[minIdx] = 0;//배열의 값 0으로 초기화
				}
				// 추천 수가 가장 적은 사람이 여러명이면 가장 오래 전 게시된 사람의 사진 삭제
				else {
					for(int student : q) {
						if(students[student] == min) {
							q.remove(student);
							students[student] = 0;
							break;
						}
					}
				}
				//새로운 학생 추가
				students[curr] = 1;
				q.add(curr);
			}
		}
		
		//액자에 남은 학생들을 오름차순으로 정렬하고 출력
		List<Integer> result = new ArrayList<>(q);
		Collections.sort(result);
		for(int student : result) {
			sb.append(student).append(" ");
		}
		System.out.println(sb);
		
	}// main
}// class
