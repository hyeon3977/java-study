package Review_Note;

public class _Review_01 {
    public static void main(String[] args) {

    // 제미나이 학습 점검 문제 1
    // 문제 1. [변수와 자료형]
    // 자신의 이름(String), 나이(int), 그리고 키(double)를 변수에 담고,
    // 이를 아래와 같이 한 줄로 출력하는 코드를 작성하세요.
    // 출력 예시: 제 이름은 홍길동이고, 나이는 25살, 키는 180.5cm입니다.

        String name = "홍길동";
        int age = 25;
        double height = 180.5;

        System.out.println("제 이름은 " + name + "이고, 나이는 " + age + "살, 키는 " + height + "cm입니다.");


    // 문제 2. [연산자]
    // 정수형 변수 score가 있습니다.
    // 이 점수가 90점 이상이면 "A", 80점 이상이면 "B", 그 외에는 "C"를 출력하는
    // 코드를 삼항 연산자를 사용하여 작성하세요.

        int score = 95;
        String a = (score >= 90) ? "A" : (score >= 80) ? "B" : "C";
        System.out.println(a);


    //문제 3. [조건문 - Switch]
    // 사용자의 등급(int grade)이 1일 때는 "관리자", 2일 때는 "운영자", 3일 때는
    // "일반 유저"라고 출력하고, 그 외의 숫자일 때는 "손님"이라고 출력하는 코드를
    // switch 문으로 작성하세요. (단, break를 적절히 활용하세요.)

        int grade = 1;
        switch (grade) {
            case 1:
                System.out.println("관리자");
                break;
            case 2:
                System.out.println("운영자");
                break;
            case 3:
                System.out.println("일반 유저");
                break;
            default:
                System.out.println("손님");
        }


    // 문제 4. [반복문 - For]
    // for 문을 사용하여 1부터 20 사이의 숫자 중
    // 3의 배수만 한 줄로 출력하는 코드를 작성하세요. (힌트: % 나머지 연산자 활용)

        for (int i = 1; i < 20 % 3 == 0; i++) {
            System.out.print(i);
        }

    /* ------------------ 답 ------------------ */

        for (int i = 1; i < 20; i++) {
            if (i % 3 == 0) { // i를 3으로 나눈 나머지가 0일 때 (3의 배수)
                System.out.println(i + " ");
            }
        }

    /* ----------------------------------------- */

    // 문제 5. [이중 반복문 - Nested Loop]
    // 이중 반복문을 사용하여 아래와 같은 모양의 별(*)을 출력하는 코드를 작성하세요.
    // *****
    // ****
    // ***
    // **
    // *

        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j < 5 - i; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }

    /* ------------------ 답 ------------------ */

        for (int i = 5; i >= 1; i--) { // 5줄부터 1줄까지
            for (int j = 1; j <= i; j++) { // 각 줄마다 i의 개수만큼 별 출력
                System.out.println("*");
            }
            System.out.println(); // 줄바꿈
        }

    /* ----------------------------------------- */

    }
}
