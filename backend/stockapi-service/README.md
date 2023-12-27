# pda-foralpha-openapi
한국투자증권 open api 호출 서비스

## 🖥프로제트 소개
ForAlpha 서비스의 open api 호출 서비스입니다.

### 핵심 기능
- KRX에서 받아온 약 2600개의 주식 종목과 테마가 저장
- 매일 오전 9시, 약 2600개의 현재가를 조회하는 한국투자증권 OPEN API 호출
- 반환 데이터를 S3에 저장

![image](https://github.com/PDA-Project/pda-foralpha-openapi/assets/76419984/4e431cdb-c535-48fe-87b1-ee7c2cf31fb1)
![image](https://github.com/PDA-Project/pda-foralpha-openapi/assets/76419984/636b158f-71bc-459f-82fb-ab0b6227352e)



### 🧩개발 기간
- 23.10.07 ~ 23.10.20

### 👨‍👧‍👧개발 멤버 구성
- 이은지: Open Api 활용 및 주식 예측 서비스 로직 개발

### ⚙ 개발 환경
- JAVA 17
- Springboot
- Gradle
- KRX : 약 2600개의 종목 및 테마
- 한국투자증권 opan api : 주식 시세 정보, 주식 50일 차트 데이터, 거래량 순위
- S3

