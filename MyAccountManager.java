package Practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


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

    public String toFileString() {
        return String.format("%s,%s,%s,%d", date, title, type, amount);
        }
    }


public class MyAccountManager {
        private int currentBalance = 0;
        private final List<Transaction> history = new ArrayList<>();
        private final String FILE_NAME = "account_data.txt";

        public void loadData() {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length < 4) continue;

                    String date = data[0];
                    String title = data[1];
                    String type = data[2];
                    int amount = Integer.parseInt(data[3]);

                    history.add(new Transaction(date, title, type, amount));
                    if (type.equals("입금")) currentBalance += amount;
                    else currentBalance -= amount;
                }
                System.out.println("이전 데이터를 성공적으로 불러왔습니다.");
            } catch (IOException e) {
                System.out.println("로드 중 오류 발생: " + e.getMessage());
            }
        }

        public void saveData() {
            try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
                for (Transaction t : history) {
                    pw.println(t.toFileString());
                }
                System.out.println("데이터가 '" + FILE_NAME + "'에 저장되었습니다.");
            } catch (IOException e) {
                System.out.println("저장 중 오류 발생: " + e.getMessage());
            }
        }

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

            myBank.loadData();

            myBank.deposit("05-11", "월급", 220000);
            myBank.withdraw("05-11", "월세", 35000, true);
            myBank.withdraw("05-11", "교통비", 10000, true);
            myBank.withdraw("05-11", "식비", 1200, false);

            myBank.showReport();

            myBank.saveData();
        }
    }

