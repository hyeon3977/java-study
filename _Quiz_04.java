package chap_04;

public class _Quiz_04 {


    // 주차 요금은 시간당 4000원 (일일 최대 요금은 30000원)
    // 경차 또는 장애인 차량은 최종 요금에서 50% 할인

    // 일반 차량 5시간 주차 시 20000 원
    // 경차 5시간 주차 시 10000 원
    // 장애인 차량 10시간 주차 시 15000 원

    // 주차 요금은 xx 원입니다.

    public static void main(String[] args) {
        int hour = 10; // 주차 시간
        boolean g = false; // 경차
        boolean j = true; // 장애인

        int fee = hour * 4000;

        if (fee > 30000) {
            fee = 30000;
        }

        if (g || j) {
            fee /= 2;
        }
        System.out.println("주차 요금은 " + fee + " 원입니다.");
    }
}
