package chap_02;

public class _Quiz_02 {
    public static void main(String[] args) {
        // 키가 115cm 이므로 탑승 불가능합니다
        // 키가 121cm 이므로 탑승 가능합니다

//        int x = 115;
//        int y = 121;
//
//        String s = (x < y) ? "키가 " + x + "cm 이므로 탑승 불가능합니다" : "키가 " + y + "cm 이므로 탑승 가능합니다";
//        System.out.println(SSs);
//
//        String z = (x > y) ? "키가 " + x + "cm 이므로 탑승 불가능합니다" : "키가 " + y + "cm 이므로 탑승 가능합니다";
//        System.out.println(z);

        // 답
        int height = 121;
        String result1 = (height >= 120) ? "탑승 가능합니다" : "탑승 불가능합니다";
        System.out.println("키가 " + height + "cm 이므로 " + result1);

        // 제미나이 예제 문제 1
        int hour = 14;
        String result2 = (hour <= 12) ? "오전" : "오후";
        System.out.println(result2);

        // 제미나이 예제 문제 2
        int age = 15;
        String result3 = ((age <= 13) || (age >= 65)) ? "할인 대상입니다" : "일반 요금입니다";
        System.out.println(result3);

        // 제미나이 예제 문제 3
        int year = 2024;
        String result = (year % 4 == 0) ? "윤년 가능성 있음" : "평년";
        System.out.println(result);

        // == 0 연습
        int myNum = 2;
        String result5 = (myNum % 2 == 0) ? "사탕" : "사탕 안줌";
        System.out.println(result5);
    }
}
