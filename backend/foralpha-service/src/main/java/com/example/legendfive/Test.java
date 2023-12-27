package com.example.legendfive;

public class Test {

    public static void main(String[] args) {
        // 기본 설정
        int basePoint = 100;
        double earningRate=1;
        int totalPoint = 0;

        for (int investmentPeriod = 1; investmentPeriod <= 100; investmentPeriod++) {
            double additionalPercentage = 0;

            // 수익률을 양수와 음수로 구분
            if (investmentPeriod <= 1) {
                // 단타(1일)의 경우
                additionalPercentage = 200;
            } else if (investmentPeriod <= 90) {
                int maxK = 200;
                int minK = 5;
                int daysAfter1 = investmentPeriod - 1;
                double K = maxK - (daysAfter1 * (maxK - minK) / 89);

                additionalPercentage = K;
            }

            // 수익률이 양수인 경우
            if (earningRate > 0) {
                totalPoint = basePoint + (int)(earningRate * additionalPercentage);
            }
            // 수익률이 음수인 경우
            else if (earningRate < 0) {
                totalPoint = -basePoint + (int)(earningRate * additionalPercentage);
            }

//            System.out.println("Investment Period: " + investmentPeriod + " days");
//            System.out.println("Total Point: " + totalPoint);
        }
    }
}
