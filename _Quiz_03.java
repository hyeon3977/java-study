package chap_03;
// "901231-1234567" 인 경우 901231-1 까지 출력
// "030708-4567890" 인 경우 030708-4 까지 출력
public class _Quiz_03 {
    public static void main(String[] args) {
        String s1 = "901231-1234567";
        String s2 = "030708-4567890";

        System.out.println(s1.substring(0, 8));
        System.out.println(s2.substring(0, 8));

        // 해설 문제 추가 방법
        System.out.println(s1.substring(0, s1.indexOf("-") + 2));
        System.out.println(s2.substring(0, s2.indexOf("-") + 2));

        // indexOf("-")는 하이픈의 위치(6)를 찾음
        // + 2를 해줘야 그 다음 글자(성별)까지 포함해서 자를 수 있음 (끝 지점은 미포함이므로)
        System.out.println(s2.substring(0, s2.indexOf("-") + 2));

        // 제미나이 예제 문제 1
        String email = "hyeon3977@gmail.com";

        System.out.println(email.substring(0, email.indexOf("@")));

        // 제미나이 예제 문제 2
        String phone = "080-1234-5678";

        System.out.println(phone.substring(phone.indexOf("-") + 1));

/*        // 궁금한 점 : "080-1234-5678" 에서 두번째 하이픈 찾을 때

// 1. 첫 번째 하이픈의 위치를 찾음 (결과: 3)
        int firstDash = phone.indexOf("-");

// 2. 첫 번째 하이픈 다음 칸(4번 인덱스)부터 다시 하이픈을 찾음
        int secondDash = phone.indexOf("-", firstDash + 1);

        System.out.println("두 번째 하이픈 위치: " + secondDash); // 결과: 8
        System.out.println("마지막 번호만 추출: " + phone.substring(secondDash + 1)); // 결과: 5678
// 3. 마지막 하이픈의 위치를 찾음
        int lastDash = phone.lastIndexOf("-");

        System.out.println("마지막 하이픈 위치: " + lastDash); // 결과: 8
        */

        // 제미나이 예제 문제 3
        String productCode = "PRO-98765";

        System.out.println(productCode.substring(0, 3));
        System.out.println(productCode.startsWith("PRO"));
    }
}
