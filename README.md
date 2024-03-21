# PETKING 펫킹 🐶


## 📍 프로젝트 소개
코로나 19 이후로 늘어난 캠핑장과 캠핑러를 타겟팅하여 캠핑장 사이트를 주제로 정했습니다.
또한 늘어나는 반려가구를 위해 반려인 캠핑러로 범위를 한정하여
캠핑 예약, 캠핑장 정보 제공, 커뮤니티 기능 구현을 목표로 하였습니다.

## 📍 주요 기능
1. 캠핑장 정보 제공 및 예약 기능
   - 메인화면
     1. 검색 옵션 선택 → 키워드 입력
     2. 검색 태그 선택 → 검색
     3. 추천 캠핑장 슬라이드
     4. 신규 등록 캠핑장 슬라이드
   - 캠핑장 찾기
      1. 메인화면의 검색 기능 포함
      2. 캠핑장 정보 목록조회 
         (리뷰수, 찜수, 찜하기, 캠핑장별 태그)
      3. 캠핑장 정보 상세페이지
         (캠핑장 기본정보, 캠핑존 정보, 캠핑장 전경이미지, 제공하는 부가서비스, 지도api, 리뷰내역)
      4. 캠핑장 예약하기
         (해당 캠핑장에 등록된 캠핑존 정보, 
         날짜 선택시 예약 가능한 캠핑존 정보 조회,
         예약시 예약정보 확인 및 인원 설정)
2. 사이트 이용자들을 위한 소통창구 형성
   - 커뮤니티
     1. 동아리 모집글 게시판
          (동아리장에 한하여 게시글 작성 가능)
     2. 질문 게시판
     3. 자유 게시판
   - 동아리(회원당 1개의 동아리 가입 가능)
     1. 동아리 생성 - 동아리장으로 배정
     2. 동아리 가입 - 동아리 모집글 게시판에서 가입 가능
     3. 동아리 조회 - 내가 가입한 동아리 정보 및 동아리회원 조회
   - 캠핑로그
     1. 캠핑장 이용객들의 리뷰 조회 
3. 회원 정보 관리를 위한 관리페이지 구성
   - 마이페이지
     1. 내 정보 조회 및 수정 (사용자 정보, 펫정보)
     2. 포인트 적립내역
     3. 내 예약내역(완료 - 리뷰작성 | 진행 - 문의작성)
     4. 내 문의내역
     5. 내 리뷰내역
     6. 회원 탈퇴
   - 캠핑장 관리
     1. 내 캠핑장 정보수정 (캠핑장 정보, 캠핑존 정보)
     2. 내 캠핑장 리뷰조회
     3. 내 캠핑장 삭제요청
   - 홈페이지 관리
     1. 전체 캠핑장 관리 (조회, 삭제승인, 등록승인/반려)
     2. 전체 사용자 관리 (권한 변경)
     3. 전체 리뷰 관리 (삭제)

## 📍 Team 소개
- 팀장 | 이민정
- 팀원 | 고혜진, 정승범, 김정효, 성민준

[팀원별 업무 소개]
- 이민정
1. 메인화면 검색 및 디자인
2. 캠핑장 찾기 메뉴 구현
3. 캠핑장 찜하기
4. 캠핑장(기본/부가) 정보 수정
5. 캠핑로그 메뉴 구현
6. 캠핑로그 작성 시, 포인트 적립
7. 마이페이지 프로필 수정
8. 캠핑장 회원가입 / 로그인

- 고혜진
1. 캠핑장-사용자
   1:1 문의 구현
2. 캠핑장 캠핑존
   정보 등록 및 수정
3. 예약 가능여부 조회
4. 캠핑존 예약 구현

- 정승범
1. 커뮤니티 메뉴 구현
   → 게시글 타입별 구분
   → 코멘트 작성
2. 동아리 메뉴 구현
   → 로그인/비로그인
   → 동아리장/동아리회원
   → 동아리생성/가입

- 김정효
1. 사용자 회원 가입
   → 중복 및 유효성 검사
2. 사용자 회원정보 수정
3. 사용자 펫정보 수정
4. 사용자 회원탈퇴

- 성민준
1. 전체 사용자 조회
2. 전체 캠핑장 조회
   (승인/반려/삭제)
3. 사용자 권한 관리
3. 관리자 권한 리뷰 삭제
4. 메인화면 캠핑장 슬라이더
5. 조회수 처리

## 📍 기술 스택
<!-- 
정적아이콘 및 뱃지생성 사이트
https://simpleicons.org/?q=intellij
https://shields.io/badges/static-badge

simpleicons의 로고명, 컬러를 참조해서 shields.io문법에 따라 뱃지를 만든다.

shields.io 기본문법
https://img.shields.io/badge/<텍스트>-<배경색>?logo=<로고>&logoColor=<로그컬러> 
-->

###### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white)
![Javascript](https://img.shields.io/badge/Javascript-F7DF1E?style=flat&logo=JavaScript&logoColor=white)
![jQuery](https://img.shields.io/badge/jQuery-white?style=flat&logo=jquery&logoColor=0769AD)
![TailwindCSS](https://img.shields.io/badge/TailwindCSS-white?style=flat&logo=tailwindcss&logoColor=0769AD)


###### Backend
![Servlet & JSP](https://img.shields.io/badge/Servlet_&_JSP-lightgray?style=flat&logo=data:image/png;base64,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)
![Oracle](https://img.shields.io/badge/Oracle-4479A1?style=flat&logo=oracle&logoColor=red)
![MyBatis](https://img.shields.io/badge/MyBatis-ffffff?style=flat&logo=data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB3aWR0aD0iMzBweCIgaGVpZ2h0PSIzMHB4IiB2aWV3Qm94PSIwIDAgMzAgMzAiIHZlcnNpb249IjEuMSI+CjxkZWZzPgo8aW1hZ2UgaWQ9ImltYWdlOSIgd2lkdGg9IjMwIiBoZWlnaHQ9IjMwIiB4bGluazpocmVmPSJkYXRhOmltYWdlL3BuZztiYXNlNjQsaVZCT1J3MEtHZ29BQUFBTlNVaEVVZ0FBQUI0QUFBQWVDQVlBQUFBN01LNmlBQUFBQm1KTFIwUUEvd0QvQVArZ3ZhZVRBQUFFdTBsRVFWUklpZTJWVzRoVlZSakhmMnZ0Njlreng3azJuY1FiU3BNS0RVTkd6a09XRXRudG9ZZWVFaUtJQ3JRSG9TaXF4NFNJbm53cDU2R0xTUXFOcU1SZ0lLV0VraVExVFRrcWFVck5TYzh3ell6anpMbk0yWmUxVnc5enpuUThzODFCb2lmL3NQbSsvUzMyOTEvZmRjTnRKT0FENkQ0Q2cvK2xUelBKK0NGMGJZUFQxWGNKWTNmQVBZZmhlQUhPbFNGYmdzKzJ3cFZiSlpiMWhqNUk5Y0NwWG5pOGFuTmhZaENlRzRmK01seFlETTh1Z1QyM1Nnb2drb3dIb1M4REcvNkFQUWEwbXBBV0lCcGdaUWZjbTRWdlRFZy9CUTlYdjFtellrV1BiVmxQV29heFJrb1phSzEvSlFoMi8zRHAwcDhMSnU2RFZBbTJ6Y0RBVnZpMjltdy9mTndHR3pYNGo4RGFWY3VXcld1dzdWN1BkZGNaVWdvcEpZYVVHSWFCRUNJcWxzdDdwNVY2K2V6WnM4Rk5pV3RnQXczQVpOWHdNVHh6UCt6VG9IY3VXdlRlY0Z2YjZ3WFg5VVNWVUVwa25mU0Q0UHVDbEE4TkRBeUVDeVhPMkthWmRSem5uRzJhaDZWdDd4NGJHL3Z0Q1B6Y0FXdE5zQVFRQ3NHRVpYSE5zcGkwYmZLV0JVSVFDY0dNWmVFS3dVUVlmdjMreU1qbWhSSUwyelNuVTY3Ym1ISWNVbzZqYmRQTU5zZnhtWjBqSTAra2xaclhuTFg0YS9WcWRoc0duYmtjbS9ONS9VbFQwOUxlaVlrckFFWkNhdCt5RGFPOTBYRjhQNG9tcFJDYkxNdGFaWmttbG1rSzI3S2F5MEowbnZBODBlWDdOQ3VWU0dvdFc4YnlFeWY0Y21BQXU2Y0gvK0pGTVNQbG91OUtwWDZZUDA0QnNGM0JnVmpLUzNlMnRQaVo5dmJ1MXFZbTNaUk9rL1k4cEpTRVNqRW1KVzlrTXV4dmJxWXM1d2V1ZlovUlhJNlRKMDl5K3Z4NU9ydTdTY1h4ZzU3bkxVNktHT0J1cmZXNlNtY2FsbWsyT0pZbFBNZkJjMTFhMG1reXJhMDR0azJoWE9hTTYzSzBxWWtaMDZSTktkS1ZETVRGSWcxRFEyellzb1dWaGtGbmZ6K0RsbVVNU3BrUlFnd2sxYmdkdUdDYlprdktkZkZjbDFTRjFITWNValZTQXhleVdXS2w1anA0U1JUUk5UUERjdDhuRXdRNFdoTUxRZDZ5T09TNnYzeFZMTzRvRkFvSGtsYm1PUEJpcEZSZnBKUVJLWVZTaXFqdVVVcVJjbDJXZG5Ud2V5NEhXaU8wNXJKbE1lSTRpV04xclZoOHJUQTZlalNweGxVY2pMVitWU21sa2dpak9KN1QwNmtVY1J6UFBicEdyN1VwcFVJcm5UNWVKVWlxY1JXbkVDSXlwZHhrU0NsTXc1amJTTFg2WkQ3UHRVSUJJUVFJa1NpRkVQaGhPUGpUMEZCdjFYbmkzd25BTUl5WHBKUnZSMHBKUDRxd0UxSStWU3pPcHBsL0ZrS3RGRUJja1lIdjk5WDZUeUxPQUoraTlXTTZqa1VNaEVIQVZCd1RSaEZoSEJNcXhkWHBhYVpMSlF3aGtGSWl0SjZyczlDYXVDTFJHcTFVWG52ZXJoc1J0d0E3Z09lQnhyak9FVXBSS3BjcEI4RjFPMWxVWnJnK3d0cm95MEh3K2ZuaDRVSTljUmZ3RHZBbzRGVVB0TmF6alpLVVBpR3VpL0M2QzliSldLbHhsYysvV1o5V0V4Z0IxdFNTVmxFZjlWejY0dmlHdGF5enhhVWcyRDU4OWVwMHZXOEpqQUVQQUQvV0h3THpSaU91WENEV0dsMmpKOWxLNWZLdTRWeHVYNUxmNmpqNXdFZUFCYXduWWN6bXhvUGtEcTZORkNDTW9yN3M2T2dMU2FTMXhGVWNBdzRCSzRFVjFDK1ltcmxNbXRXS0hnUmgrRzV1ZlB5Vkc1RW1FY05zNnZkV0x1QXhPMTZOK2lZUm9uV2c0dmhZRklaUGowMU43ZjgzMGxvZk44TjZZS09VOGo0aHhGMVNpSlNVMHBKU2prdklZaGpIVzRyRkx5N0R6QUw5M2NiL2g3OEJPbXBvSDV3RHBDNEFBQUFBU1VWT1JLNUNZSUk9Ii8+CjwvZGVmcz4KPGcgaWQ9InN1cmZhY2UxIj4KPHVzZSB4bGluazpocmVmPSIjaW1hZ2U5Ii8+CjwvZz4KPC9zdmc+Cg==)
![Apache Tomcat](https://img.shields.io/badge/Apache_Tomcat-000000?logo=apachetomcat&logoColor=F8DC75)
![JUnit5](https://img.shields.io/badge/JUnit5-dc524a?logo=junit5&logoColor=25A162)
![Apache Maven](https://img.shields.io/badge/Apache_Maven-8a3578?logo=apachemaven&logoColor=C71A36)



###### Infra
<!-- 동적 아이콘 생성 사이트 https://techstack-generator.vercel.app/ -->

<div style="display:flex; align-items:flext-start;">
  <img src="https://techstack-generator.vercel.app/github-icon.svg" alt="icon" width="20" height="20" />
  <img src="https://img.shields.io/badge/Github_Actions-f7f7f7?logo=githubactions&logoColor=2088FF"/>
</div>
<div style="display:flex; align-items:flext-start;">
  <img src="https://img.shields.io/badge/Oracle_Cloud-ATP-4479A1?style=squre&logo=oracle&logoColor=red"/>
</div>

###### Tools
![Intellij IDEA](https://img.shields.io/badge/Intellij_IDEA-red?logo=intellijidea)
![Source Tree](https://img.shields.io/badge/Source_Tree-ffffff?style=flat&logo=sourcetree&logoColor=0052CC)
![Discord](https://img.shields.io/badge/discord-5865F2?style=flat&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/notion-000000?style=flat&logo=notion&logoColor=white)
