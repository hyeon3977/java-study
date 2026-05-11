package Practice;

import java.util.ArrayList;
import java.util.List;

class Transaction {
    String date;
    String title;
    String type;
    int amount;

    public Transaction(String date, String title, String type, int amount) {
        this.date = date;
        this.title = title;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-10s | %-6s | %d원", date, title, type, amount);
    }
}

public class MyAccountManager {
        private int currentBalance = 0;
        private final List<Transaction> history = new ArrayList<>();

        public void deposit(String date, String title, int amount) {
            currentBalance += amount;
            history.add(new Transaction(date, title, "입금", amount));
            System.out.println("입금 완료: " + title + " (" + amount + "원)");
        }

        public void withdraw(String date, String title, int amount, boolean isFixed) {
            String type = isFixed ? "고정지출" : "유동지출";

            if (currentBalance >= amount) {
                currentBalance -= amount;
                history.add(new Transaction(date, title, type, amount));
                System.out.println("지출 완료: " + title + " [" + type + "] (" + amount + "원)");
            } else {
                System.out.println("잔고 부족으로 '" + title + "' 결제 실패 (현재 잔액: " + currentBalance + "원)");
            }
        }

        public void showReport() {
            System.out.println("\n=========== 가계부 최종 리포트 ===========");
            for (Transaction t : history) {
                System.out.println(t);
            }
            System.out.println("------------------------------------------");
            System.out.println("현재 계좌 최종 잔액: " + currentBalance + "원");
            System.out.println("==========================================\n");
        }

        public static void main(String[] args) {
            MyAccountManager myBank = new MyAccountManager();

            myBank.deposit("05-11", "월급", 220000);
            myBank.withdraw("05-11", "월세", 35000, true);
            myBank.withdraw("05-11", "교통비", 10000, true);
            myBank.withdraw("05-11", "식비", 1200, false);

            myBank.showReport();
        }
    }

