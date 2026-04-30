package Review_Note;

public class _Review_06 {
    /*public static String getHiddenData(String data, int index) {
        String s = data.substring(0, index);

        for (int i = 0; i < data.length() - index; i++) {
            s += "*";
        }
        return s;
    }

    public static void main(String[] args) {
        String name = "나코딩";
        String phone = "010-1234-5678";

        System.out.println("이름 : " + getHiddenData(name, 1));
        System.out.println("전화번호 : " + getHiddenData(phone, 9));
    }*/

    // 문제 2
    // 출력 예시
    // 이메일 : java****@example.com

    public static String getHiddenEmail(String email, int length) {

        String id = email.substring(0, length);

        int atIndex = email.indexOf("@");

        for (int i = 0; i < atIndex - length; i++) {
            id += "*";
        }

        id += email.substring(atIndex);

        // 5. 완성된 주소 반환
        return id;
    }

    public static void main(String[] args) {
        String email = "java_love@example.com";

        // 메소드를 호출하고 결과를 출력합니다
        System.out.println("이메일 : " + getHiddenEmail(email, 4));
    }
}
