# 📚 Algostudy_Q2

**기간**: 2024년 4월 1일 ~ 5월 31일 (총 8주)  
**목표**: 알고리즘 문제 풀이 및 코드 리뷰를 통해 함께 성장하기

---

## 📌 Rules

### 🔹 문제 선정
- **매주 수요일 밤 10시까지**, 각자 풀고 싶은 백준 문제 1개를 선정
- 난이도는 **실버 중상 ~ 골드 중하** 수준 권장

### 🔹 문제 풀이
- **매주 수요일 밤 10시까지**, 지난주에 선정된 총 3문제 풀이
- 풀이 시 **주석으로 간단한 설명**을 반드시 작성

### 🔹 코드 리뷰
- **매주 목요일 밤 8시 30분**, 오프라인 리뷰 진행

### 🔹 문제 선정 투표
- **목요일 밤 12시까지** Mattermost에서 투표 진행
- 선정된 문제는 각 `weekN/overview.md`에 공지

### 🔹 AI 사용 가이드
- 생성형 AI의 도움 없이 **직접 사고하고 풀이하는 것**을 권장

---

### 🔹 Convention Rule

#### ✅ Commit Message
```
[Wn] 자유기술  
ex) [W2] 0000 문제 풀이  
```

#### ✅ Pull Request
```
Wn / 이름  
ex) W3 / 홍길동  
```

---

## 🗂️ Directory Structure

```bash
📦 Algostudy_Q2
 ┣ 📂 week1
 ┃ ┣ 📂 honggildong
 ┃ ┃ ┣ 📄 BOJ_1234.java
 ┃ ┃ ┗ 📄 BOJ_5678.java
 ┣ 📂 week2
 ┃ ┣ 📂 honggildong
 ┃ ┃ ┗ 📄 BOJ_1357.py
 ┣ 📄 overview.md
 ┗ 📄 README.md
 ```

## 🧭 Git & GitHub Guide

### 1. ✅ 저장소 Fork 및 Clone
```
# 내 GitHub 계정으로 포크
# 이후 로컬에 복제
git clone https://github.com/본인계정/Algostudy_Q2.git
cd Algostudy_Q2
```

### 2. ✅ 원본 저장소 연결 (upstream 등록)
```
git remote add upstream https://github.com/kimdonguk822/Algostudy_Q2.git
```

### 3. ✅ 주차별 브랜치 생성 및 작업
```
git checkout -b username/week1

# 폴더 생성 및 코드 작성 후후

git add .
git commit -m "[W1] 홍길동"
git push origin username/week1
```

### 4. ✅ Pull Request 생성

- GitHub에서 원본 저장소(kimdonguk822/Algostudy_Q2)로 PR 생성

- 제목: W1 / 홍길동

- 내용: 문제 링크 + 풀이 요약


### 5. ✅ 최신 상태로 유지 (매주 Merge 이후)
```
# 메인 브랜치로 이동
git checkout main

# 원본 저장소에서 변경 내용 가져오기
git pull upstream main

# 내 GitHub에도 반영
git push origin main
```

### ✅ Pull Request 작성 예시 템플릿
```
## ✏️ 푼 문제
- [BOJ 1234 - 문제 제목](https://www.acmicpc.net/problem/1234)

## 💡 풀이 요약
- BFS를 이용하여 ...
- 시간 복잡도: O(N^2)

## ❗ 어려웠던 점
- 특정 조건에서 메모리 초과 발생 원인 찾기 어려웠음
```

