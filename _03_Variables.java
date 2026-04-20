package chap_01;

import com.sun.org.apache.xpath.internal.objects.XString;

public class _03_Variables {
    public static void main(String[] args) {
        String name = "박정현";
        int hour = 15;

        System.out.println(name + "님, 배송이 시작됩니다. " + hour + "시에 방문 예정입니다.");
        System.out.println(name + "님, 배송이 완료되었습니다.");

        double score = 90.5;
        char grade = 'A';
        name = "강백호";
        System.out.println(name + "님의 평균 점수는 " + score + "점입니다.");
        System.out.println("학점은 " + grade + "입니다.");

        boolean pass = true;
        System.out.println("이번 시험에 합격했을까요? " + pass);

        double d = 3.14123456789; // 정밀한 실수
        float f = 3.14123456789F;
        System.out.println(d);
        System.out.println(f);

        int i = 1000000000;
        long l = 1000000000000L;
        System.out.println(l);
        l = 1_000_000_000_000L;

        // int, long  정수 사용시
        // float, double  실수 사용시
        // char, String  하나, 여러문자 사용시
        // boolean  참과 거짓 값
    }
}
