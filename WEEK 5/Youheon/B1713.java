package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 후보 추천하기 
// https://www.acmicpc.net/problem/1713 
// 메모리 21388kb, 시간 240ms  
public class B1713 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int frames = sc.nextInt(); // 사진틀 개수
        int total = sc.nextInt();  // 총 추천 횟수

        int[][] students = new int[101][2]; // [추천 수, 게시 시간]
        List<Integer> frameList = new ArrayList<>(); // 현재 사진틀에 올라온 학생 번호

        for (int time = 0; time < total; time++) {
            int num = sc.nextInt();

            // 이미 사진틀에 있는 경우: 추천 수 증가
            if (students[num][0] > 0) {
                students[num][0]++;
            } else {
                // 사진틀이 가득 찼으면 제거할 학생을 찾는다
                if (frameList.size() == frames) {
                    int minVote = Integer.MAX_VALUE;
                    int earliestTime = Integer.MAX_VALUE;
                    int studentToRemove = -1;

                    for (int stu : frameList) {
                        int vote = students[stu][0];
                        int postTime = students[stu][1];
                        if (vote < minVote || (vote == minVote && postTime < earliestTime)) {
                            minVote = vote;
                            earliestTime = postTime;
                            studentToRemove = stu;
                        }
                    }

                    // 제거
                    students[studentToRemove][0] = 0;
                    students[studentToRemove][1] = 0;
                    frameList.remove(Integer.valueOf(studentToRemove));
                }

                // 새 학생 등록
                students[num][0] = 1;
                students[num][1] = time;
                frameList.add(num);
            }
        }

        // 사진틀에 있는 학생 번호 오름차순 정렬
        Collections.sort(frameList);
        for (int stu : frameList) {
            System.out.print(stu + " ");
        }
    }
}
