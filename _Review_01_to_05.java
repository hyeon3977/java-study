package Review_Note;

public class _Review_01_to_05 {
    public static void main(String[] args) {
        // [문제 1] 자료형 및 연산자: 정가 100,000원 물건 20% 할인 계산
        int price = 100000;
        double discount = 0.2;
        int result = (int)(price * (1 - discount)); // 명시적 형변환
        System.out.println("할인된 가격은 " + result + "원입니다.");

        // [문제 2] 조건문: 점수별 등급 출력
        int score = 85;
        if (score >= 90) {
            System.out.println("A");
        } else if (score >= 80) { // 다중 조건문
            System.out.println("B");
        } else {
            System.out.println("C");
        }

        // [문제 3] 반복문: 1~10 사이의 짝수만 출력
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) { // 나머지 연산자로 짝수 판별
                System.out.println(i);
            }
        }

        // [문제 4] 배열: 크기 5 배열 선언 및 10으로 초기화
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) { // length 속성 사용
            arr[i] = 10;
        }

        // [문제 5] 이중 반복문: 구구단 5단 출력
        int dan = 5;
        for (int i = 1; i <= 9; i++) {
            System.out.println(dan + " x " + i + " = " + (dan * i));
        }

        // [문제 6] Break/Continue: 4의 배수 건너뛰기
        for (int i = 1; i <= 10; i++) {
            if (i % 4 == 0) {
                continue; // 4의 배수일 때 아래 코드를 무시하고 다음 반복으로 이동
            }
            System.out.println(i);
        }

        // [문제 7] 다차원 배열: 2x2 배열 생성 및 "Java" 채우기
        String[][] box = {
                {"Java", "Java"},
                {"Java", "Java"}
        }; // 중괄호를 이용한 직접 선언

        // [문제 8] 아스키코드: 'C' 다음 문자 'D' 출력
        char ch = 'C';
        ch++; // 증감 연산자를 통한 문자 연산
        System.out.println(ch); // D 출력

        // [문제 9] 향상된 for문: ages 배열 출력
        int[] ages = {20, 25, 30};
        for (int age : ages) { // for-each 문
            System.out.println(age);
        }

        // [문제 10] 복합 응용: 신발 사이즈 250부터 10씩 증가 (5개)
        int[] sizes = new int[5];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 250 + (10 * i); // 인덱스 활용 로직
            System.out.println("신발 사이즈 " + sizes[i]);
        }
    }
}

