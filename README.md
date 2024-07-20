# 이커머스

* * *

## 📚목차
>
>###   - [프로젝트 개요](#프로젝트-개요)
>###   - [Stacks](#Stacks)
>###   - [ERD](#ERD)
>###   - [주요 기능](#주요-기능)

* * *
## 프로젝트 개요  

>내용 추가
>

>### 개발 기간 및 인원
>24.06.19 ~ 24.07.16<br>
>백엔드 1명
* * *
## Stacks
- Spring Boot
- Spring Security
- JPA / Hibernate
- MySQL
- Docker / Docker Compose

* * *

## ERD
!![image](https://github.com/user-attachments/assets/08b1ae09-752e-48bb-abc9-d0c012e95d20)



* * *

## 주요 기능


### 토스 페이먼츠 코어 API
>■ 상품 결제 기능 구현

### 최근 본 상품(쿠키)
>■ 불필요한 재검색 단계 생략
>- 상세페이지 접속시 상품 고유번호를 쿠키에 저장
>- 쿠키값으로 해당 상품 이미지 호출
>- 플로팅 사이드바 상품 이미지 출력 및 링크 제공

### 상품 검색 및 정렬 방식
>■ 상품 접근 단계 간소화
>- 검색 기능을 통해 직접 접근 가능
>- 정렬 방식을 변경으로 접근 간편화
>- 카테고리 분류로 사용 편의성 증대
  
### 권한 구분 및 관리자 페이지
>■ 사용자의 정보 및 상태 관리
>- 상품 관리
>- 문의 상태 확인 및 답변
>- 주문 현황 조회/관리
  
### 찜목록 및 장바구니 저장
>■ 관심 상품, 구매 예정 상품 저장 → 구매 단계 간소화

### 챗봇 시스템
>■ 문의사항 발생시 게시판 접속, 사례 검색 등의 단계 간소화
>- konlpy 패키지로 키워드 형태소 분석 및 질문 전처리
>- sklearn 패키지 코사인 유사도 계산 및 답변 추출
>- 모든 페이지에서 챗봇에게 질문시 답변 제공

### 회원
>■ 회원가입, 로그인, 아이디/비밀번호 찾기, 회원정보, 회원수정, 회원탈퇴

### 상품
>■ 상품 리스트 조회, 상세페이지, 상품 등록, 등록 상품 수정/삭제, 댓글 작성

### 구매
>■ 구매하기, 구매기록 조회, 포인트 누적


* * *
