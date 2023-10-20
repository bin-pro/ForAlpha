# pda-user-service
user service

## 유저 서비스와 메인 서비스의 통신 구조

### 1. 안정성을 위해 유저 서비스와 메인 서비스를 분리하여 개발
주식 예측 정보를 보유한 Main에서 service가 다운되더라도, 유저들의 포인트는 유지됩니다.

</br>

### 2. 카프카를 이용하여 유저 서비스에 생성된 UUID를 메인 서비스에 전달
![image](https://github.com/PDA-Project/pda-user-service/assets/76419984/e09ead24-7b1e-4f2e-844f-fd75bcfb70a8)


</br>

### 3. 전체적인 통신 구조
![image](https://github.com/PDA-Project/pda-user-service/assets/76419984/08bbe448-9545-4192-8d02-5ef635024552)
</br>




