# pda-foralpha-service
foralpha 메인 서비스

## 🖥프로제트 소개
ForAlpha 서비스의 메인 서비스입니다.

### 핵심 기능
- 상승 주식 예측하기
- 금융 OX 퀴즈
- 투자 내역 및 분석
- 주식 예측 결과 공유 피드

### Test Swagger
- http://test2.shinhan.site:8002/swagger-ui/index.html#/home-controller/tradingVolume

### 🧩개발 기간
- 23.10.07 ~ 23.10.20

### 👨‍👧‍👧개발 멤버 구성
- 이수빈: 프로젝트 전체 관리, 투자 내역 및 분석 페이지 API 추가 개발
- 이은지: Open Api 활용 및 주식 예측 서비스 로직 개발, 데이터 캐싱 처리 등의 성능 개선, 요구사항 변경에 따른 전체적인 수정
- 홍보영: 메인 페이지, 전체 CRUD API 개발

### ⚙ 개발 환경
- JAVA 17
- Springboot
- AWS RDS : mysql 8
- Gradle
- Postman
- Swagger
- KRX : 약 2600개의 종목 및 테마
- 한국투자증권 opan api : 주식 시세 정보, 주식 50일 차트 데이터, 거래량 순위
- S3
- Redis
- Kafka
- docker compose

### 사용 OPEN API 및 데이터
- KRX의 약 2600개의 데이터를 DB에 저장 후 오전 9시마다 OPEN API로 현재가를 호출 후 S3에 저장
- 리액트 요청 시 S3에서 데이터 사용
- 실시간 거래량 OPEN API 사용
- 주식 시세가 OPEN API로 주식 차트 그릴 데이터 사용

### 백엔드 성능 개선
![image](https://github.com/PDA-Project/pda-foralpha-service/assets/76419984/05df5679-5a96-4a11-94c3-e5253b1451f3)
![image](https://github.com/PDA-Project/pda-foralpha-service/assets/76419984/01b0a835-607d-4ddf-8a65-63e22eddd688)


### 협업툴
- Notion
- Slack
