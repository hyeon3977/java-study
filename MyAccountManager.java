package Practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;


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
            if (history.isEmpty()) {
                System.out.println("기록된 내역이 없습니다.");
            } else {

                for (int i = 0; i < history.size(); i++) {
                    System.out.println("[" + (i + 1) + "]");
                    System.out.println(history.get(i));
                }
            }
            System.out.println("------------------------------------------");
            System.out.println("현재 계좌 최종 잔액: " + currentBalance + "원");
            System.out.println("==========================================\n");
        }

        public void showStatistics() {
            int totalDeposit = 0;
            int totalFixedWithdraw = 0;
            int totalVariavleWithdraw = 0;

            for (Transaction t : history) {
                if (t.type.equals("입금")) {
                    totalDeposit += t.amount;
                } else if (t.type.equals("고정지출")) {
                    totalFixedWithdraw += t.amount;
                } else if (t.type.equals("유동지출")) {
                    totalVariavleWithdraw += t.amount;
                }
            }

            System.out.println("\n======== 지출/수입 통계 ========");
            System.out.println("총 입금액: " + totalDeposit + "원");
            System.out.println("총 고정지출: " + totalFixedWithdraw + "원");
            System.out.println("총 유동지출: " + totalVariavleWithdraw + "원");
            System.out.println("---------------------------------");
            System.out.println("순수 지출 합계: " + (totalFixedWithdraw + totalVariavleWithdraw) + "원");
            System.out.println("==================================\n");
        }

        public void deleteTransaction(int index) {
            Transaction removed = history.remove(index);
            System.out.println("삭제 완료: " + removed.title);
            recalculateBalance();
        }

        public void updateTransaction(int index, String date, String title, int amount) {
            if (index >= 0 && index < history.size()) {
                Transaction t = history.get(index);
                t.date = date;
                t.title = title;
                t.amount = amount;
                recalculateBalance();
            }
        }

        public int getHistorySize() {
            return history.size();
        }

        public void recalculateBalance() {
            currentBalance = 0;
            for (Transaction t : history) {
                if (t.type.equals("입금")) {
                    currentBalance += t.amount;
                } else {
                    currentBalance -= t.amount;
                }
            }
        }

        public static void main(String[] args) {
            MyAccountManager myBank = new MyAccountManager();
            Scanner scanner = new Scanner(System.in);
            myBank.loadData();

            boolean isRunning = true;
            while (isRunning) {
                System.out.println("\n--- 가계부 관리 시스템 ---");
                System.out.println("1. 입금 2. 지출 3. 내역조회 4. 통계보기 5. 종료 6. 내역 수정 7. 내역 삭제");
                System.out.print("선택: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("날짜(MM-DD): ");
                        String dDate = scanner.nextLine();
                        System.out.print("항목: ");
                        String dTitle = scanner.nextLine();
                        System.out.print("금액: ");
                        int dAmount = scanner.nextInt();
                        myBank.deposit(dDate, dTitle, dAmount);
                        break;
                    case 2:
                        System.out.print("날짜(MM-DD): ");
                        String wDate = scanner.nextLine();
                        System.out.print("항목: ");
                        String wTitle = scanner.nextLine();
                        System.out.print("금액: ");
                        int wAmount = scanner.nextInt();
                        System.out.print("고정지출인가요? (true/false): ");
                        boolean isFixed = scanner.nextBoolean();
                        myBank.withdraw(wDate, wTitle, wAmount, isFixed);
                        break;
                    case 3:
                        myBank.showReport();
                        break;
                    case 4:
                        myBank.showStatistics();
                        break;
                    case 5:
                        myBank.saveData();
                        isRunning = false;
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    case 6:
                        myBank.showReport();
                        System.out.println("수정할 내역의 번호를 입력하세요: ");
                        int updateIdx = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (updateIdx >= 0 && updateIdx < myBank.getHistorySize()) {
                            System.out.print("새로운 날짜(MM-DD): ");
                            String nDate = scanner.nextLine();
                            System.out.print("새로운 항목: ");
                            String nTitle = scanner.nextLine();
                            System.out.print("새로운 금액: ");
                            int nAmount = scanner.nextInt();
                            scanner.nextLine();

                            myBank.updateTransaction(updateIdx, nDate, nTitle, nAmount);
                        } else {
                            System.out.println("해당 번호를 찾을 수 없습니다.");
                        }
                        break;
                    case 7:
                        myBank.showReport();
                        System.out.print("삭제할 내역의 번호를 입력하세요.");
                        int deleteIdx = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (deleteIdx >= 0 && deleteIdx < myBank.getHistorySize()) {
                            myBank.deleteTransaction(deleteIdx);
                        } else {
                            System.out.println("삭제되었습니다.");
                        }
                        break;
                    default:
                        System.out.println("잘못된 번호입니다.");
                }
            }
        }
    }
