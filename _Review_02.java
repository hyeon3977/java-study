package Review_Note;

public class _Review_02 {
    public static void main(String[] args) {

        // 제미나이 학습 점검 문제 1
        // 문제 1.[놀이공원 입장료 계산기]
        //놀이공원 입장료를 계산하는 프로그램을 작성하세요.
        //기본 조건: 입장료는 시간당 10,000원입니다.
        //최대 요금: 아무리 오래 있어도 일일 최대 요금은 40,000원을 넘지 않습니다.
        //할인 혜택: '다자녀 가구'이거나 '국가유공자'인 경우 최종 요금에서 50% 할인을 적용합니다.

        int hour = 5;
        boolean d = true;
        boolean g = true;

        int fee = hour * 10000;
        if (fee > 40000) {
            fee = 40000;
        }

        if (d || g) {
            fee /= 2;
        }
        System.out.println("입장료는 " + fee + " 원입니다.");

        System.out.println("---------------------------------------");

        //문제 2. [헬스장 이용료 계산기]
        //헬스장 일일 이용권을 계산하는 프로그램을 작성하세요.
        //기본 조건: 이용료는 30분당 2,000원입니다. (1시간이면 4,000원)
        //최대 요금: 일일 최대 요금은 15,000원입니다.
        //할인 혜택: '학생'인 경우 최종 요금에서 20% 할인을 적용합니다.

        int minute = 30;
        boolean isStudent = true;
        int fee2 = minute * 2000;

        if (fee2 > 15000) {
            fee2 = 15000;
        }

        if (isStudent) {
            fee2 = (int) (fee2 * 0.8f);
        }
        System.out.println("이용료는 " + fee2 + " 원입니다.");


        System.out.println("---------------------------------------");

    }
}
