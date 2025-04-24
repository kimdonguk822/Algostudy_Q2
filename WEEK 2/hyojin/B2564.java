// 문제: 백준 2564번 
// 문제제목 : 경비원
// 링크: https://www.acmicpc.net/problem/2564
// 메모리 : 14232KB
// 시간: 104ms
import java.util.Scanner;

//백준_2564_경비원 
public class B2564 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();// 가로 길이, 열 크기
        int r = sc.nextInt();// 세로 길이, 행 크기

        int store = sc.nextInt(); // 상점 갯수
        int[] dir = new int[store];
        int[] len = new int[store];

        for (int i = 0; i < store; i++) {
            dir[i] = sc.nextInt(); // 상점이 위치한 방향. 1_북 / 2_남 / 3_서 / 4_동
            len[i] = sc.nextInt(); // 경계로부터의 거리.
                                    // dir이 1,2면 왼쪽 기준
                                    // dir이 3,4aus 위쪽 기준
        }

        int dongDir = sc.nextInt(); // 동근 위치한 방향
        int dongLen = sc.nextInt(); // 동근 경계로부터의 거리


        int sum = 0; // 최단 거리의 합

        for (int i = 0; i < store; i++) {// 상점의 갯수만큼 반복
            // 동근이가 각 방향에 있는 경우를 나누어서 생각하기
            if (dongDir == 1) {// 동근이가 북쪽에 있는 경우

                if (dir[i] == 1) {// 같은 방향인 북쪽에 있는 경우
                    sum += Math.abs(dongLen - len[i]);
                }
                // 남쪽에 위치하는 경우
                else if (dir[i] == 2) {
                    sum += Math.min(r + dongLen + len[i], r + (c - dongLen) + (c - len[i]));
                }
                // 서쪽에 위치하는 경우
                else if (dir[i] == 3) {
                    sum += dongLen + len[i];
                }
                // 동쪽에 위치하는 경우
                else if (dir[i] == 4) {
                    sum += (r - dongLen) + len[i];
                }

            } // 동근이가 북쪽에 있는 경우
            
            // 동근이가 남쪽에 위치하는 경우
            else if (dongDir == 2) {

                // 북쪽에 위치하는 경우
                if (dir[i] == 1) {
                    sum += Math.min(r + dongLen + len[i], r + (c - dongLen) + (c - len[i]));
                }
                // 같은 방향인 남쪽에 위치하는 경우
                else if (dir[i] == 2) {
                    sum += Math.abs(dongLen - len[i]);
                }
                //서쪽에 위치하는 경우
                else if(dir[i]==3) {
                    sum += dongLen + (r - len[i]); //r이 세로길이
                }
                //동쪽에 위치하는 경우
                else if(dir[i]==4) {
                    sum += (c-dongLen) + (r - len[i]); //c가 가로길이
                }
                

            } // 동근이가 남쪽에 위치하는 경우
            
            //동근이가 서쪽에 위치하는 경우
            else if(dongDir == 3) {
                //북쪽에 위치하는 경우
                if(dir[i]==1) {
                    sum += dongLen + len[i];
                }
                
                //남쪽에 위치하는 경우
                else if(dir[i]==2) {
                    sum += (r-dongLen) + len[i];
                }
                
                //같은 위치인 서쪽에 위치하는 경우
                else if(dir[i]==3) {
                    sum += Math.abs(dongLen-len[i]);
                }
                
                //동쪽에 위치하는 경우
                else if(dir[i]==4) {
                    sum += Math.min(c+dongLen+len[i], c+(r-dongLen)+r-len[i]);
                }
                
            }//동근이가 서쪽에 위치하는 경우
            
            
            //동근이가 동쪽에 위치하는 경우
            else if(dongDir == 4) {
                //북쪽에 위치하는 경우
                if(dir[i]==1) {
                    sum += dongLen + (c - len[i]); //c가 가로
                }
                
                //남쪽에 위치하는 경우
                else if(dir[i]==2) {
                    sum += (c - len[i]) + (r - dongLen);
                }
                
                //서쪽에 위치하는 경우
                else if(dir[i]==3) {
                    sum += Math.min(c + len[i] + dongLen, c + (r-dongLen) + (r-len[i]));
                }
                
                //같은 위치인 동쪽에 위치하는 경우
                else if(dir[i]==4) {
                    sum += Math.abs(dongLen - len[i]);
                }
            }//동근이가 서쪽에 위치하는 경우

        } // 상점의 갯수만큼 반복
        
        System.out.println(sum);

    }// main

}
