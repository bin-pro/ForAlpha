![header](https://capsule-render.vercel.app/api?type=waving&color=0046ff&height=250&section=header&text=증권을%20더%20쉽게,%20ForAlpha&fontSize=48&animation=fadeIn&fontAlignY=32&desc=신한투자증권주관%20프로디지털아카데미%20최종%20프로젝트&descAlignY=51&descAlign=70&fontColor=ffffff)

# 프로디지털아카데미란?

```
  - 총 512시간의 금융개발 실무교육
  - 클라우드 환경 기반의 풀스택 개발 과정 학습
  - 신한투자증권 실무에 바로 활용 가능한 능력 배양/활용
  - 핀테크 산업에 필요한 전반적인 ICT 능력 배양
```

# **사용 기술**

<table>
<tr>
<td align="center">
  
### FrontEnd

<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=white">
</td>
<td align="center">
  
### BackEnd

[![Spring](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)](https://mariadb.org/)
[![Redis](https://img.shields.io/badge/Redis-DD0000?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Kafka](https://img.shields.io/badge/Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)](https://kafka.apache.org/)

</td>

<td align="center">

### AWS

[![Route53](https://img.shields.io/badge/Route53-0E0F37?style=for-the-badge&logo=amazon-route-53&logoColor=white)](https://aws.amazon.com/route53/)
[![AWS S3](https://img.shields.io/badge/S3-569A31?style=for-the-badge&logo=amazon-s3&logoColor=white)](https://aws.amazon.com/s3/)
[![ECR](https://img.shields.io/badge/ECR-F49403?style=for-the-badge&logo=amazon&logoColor=white)](https://aws.amazon.com/ecr/)
[![AWS RDS](https://img.shields.io/badge/RDS-4479A1?style=for-the-badge&logo=amazon-rds&logoColor=white)](https://aws.amazon.com/rds/)
[![AWS EKS](https://img.shields.io/badge/EKS-0046ff?style=for-the-badge&logo=amazon-eks&logoColor=white)](https://aws.amazon.com/eks/)
[![Auto Scaling Group](https://img.shields.io/badge/ASG-F49403?style=for-the-badge&logo=amazon&logoColor=white)](https://aws.amazon.com/autoscaling/)
[![AWS ALB](https://img.shields.io/badge/ALB-F49403?style=for-the-badge&logo=amazon&logoColor=white)](https://aws.amazon.com/elasticloadbalancing/)

<td>

### UX·UI

<img src="https://img.shields.io/badge/Figma-ae4dff?style=for-the-badge&logo=figma&logoColor=white">
</td>
</tr>
</table>

<hr/>

## 주식 초보자들을 위한 서비스, ForAlpha

- **개발 및 발표 준비 기간 : 10/7 - 10/20, 약 14일**
- **증권에 익숙치 않은 사용자 입장에서 기존 증권관련 모바일 앱 분석**
  ```
  - 실제 돈을 잃을 수도 있다는 두려움
  - 번거로운 계좌 개설
  - 어려워 보이는 많은 수치와 차트
  - 주식 관련 용어 학습 필요
  ```

<hr/>

- **제안 및 해결안 도출**

  > 1. 포인트를 활용한 모의투자 기능
  > 2. 소셜로그인 기능 제공
  > 3. 어려워보이지 않도록 직관적인 UI 도입
  > 4. 교육을 위한 금융 관련 OX 퀴즈 제공

- **왜, MSA?**

  > 1. 모의투자, OX 퀴즈 등 기존의 미니게임 외 빠른 확장 가능
  > 2. 우선적으로 User, Main으로 나누어 User 서비스 중단 시 Main 내 포인트 관련 로직 수행 가능
  > 3. EKS 상의 배포를 통해 Cloud Native 환경 학습

---

## 프로젝트 내 역할

![역할](./photos/1.png)

> 1. 이은지 : Open API 활용, 예측 서비스 개발, 캐싱 설계, 요구사항 변경에 따른 전체적인 수정
> 2. 이수빈 : ERD 설계, Spring Cloud 활용 MSA 구축, kompose 활용 k8s yml 작성, User, Gateway, Discovery Service 개발, Main 내 투자 내역 및 분석 API, CQRS Pattern 활용 DB 이중화
> 3. 장정은 : Cloud Architecture 설계 및 EKS 배포
> 4. 추예진 : Figma 활용 UI 설계 및 FrontEnd 전체
> 5. 홍보영 : CRUD API 설계 및 FrontEnd와 API 연동 디버깅

## FrontEnd

> ![UI](./photos/2.png)

## Backend

### 캐싱 설계

> ![Caching](./photos/5.png) ![Caching](./photos/6.png)

### Cloud Architecture

> ![Cloud](./photos/7.png)

### System Architecture

> ![Cloud](./photos/8.png)

### ERD

#### User Service

> ![User Service](./photos/user_service.png)

#### Main Service

> ![Main Service](./photos/main_service.png)

<hr/>

### 발표 자료

> https://drive.google.com/file/d/1OQykCvLUHL1hUwxhhqQmPhUNsuknxmmo/view?usp=drive_link
