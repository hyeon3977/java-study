package Practice;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


class Transaction {
    String date, title, type;
    int amount;

    public Transaction(String date, String title, String type, int amount) {
        this.date = date;
        this.title = title;
        this.type = type;
        this.amount = amount;
    }

    private String getPaddedCenter(String target, int width) {
        int currentWidth = 0;
        for (char c : target.toCharArray()) {
            if (c >= '\uAC00' && c <= '\uD7A3') currentWidth += 2;
            else currentWidth += 1;
        }

        int totalPadding = width - currentWidth;
        if (totalPadding <= 0) return target;

        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < leftPadding; i++) sb.append(" ");
        sb.append(target);
        for (int i = 0; i < rightPadding; i++) sb.append(" ");

        return sb.toString();
    }

    @Override
    public String toString() {
        String pDate = getPaddedCenter(date, 10);
        String pTitle = getPaddedCenter(title, 20);
        String pType = getPaddedCenter(type, 12);

        return String.format("%s|%s|%s|%,12d원", pDate, pTitle, pType, amount);
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

                int amount;
                try {
                    amount = Integer.parseInt(data[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("파일 로드 중 손상된 데이터 발견(금액 오류). 해당 내역을 스킵합니다.");
                    continue;
                }

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
        if (!history.isEmpty()) {
            Collections.sort(history, new Comparator<Transaction>() {
                @Override
                public int compare(Transaction t1, Transaction t2) {
                    return t1.date.compareTo(t2.date);
                }
            });
        }
        System.out.println("\n==========================================================================");
        System.out.println("   번호   |    날짜    |         항목         |     구분     |      금액      ");
        System.out.println("--------------------------------------------------------------------------");
        if (history.isEmpty()) {
            System.out.println("                         기록된 내역이 없습니다.                        ");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.printf(" [%2d ]  |%s%n", (i + 1), history.get(i));
            }
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("   현재 최종 잔액: %,53d원%n", currentBalance);
        System.out.println("==========================================================================\n");
    }

    public void showRangeReport(String startDate, String endDate) {
        System.out.println("\n======================= 기간별 조회 (" + startDate + " ~ " + endDate + ") =======================");
        System.out.println("   번호   |    날짜    |         항목         |     구분     |      금액      ");
        System.out.println("--------------------------------------------------------------------------");

        Collections.sort(history, (t1,t2) -> t1.date.compareTo(t2.date));

        boolean found = false;
        int printIdx = 1;
        for (Transaction t : history) {
            if (t.date.compareTo(startDate) >= 0 && t.date.compareTo(endDate) <= 0) {
                System.out.printf(" [%2d ] |%s%n", printIdx++, t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("                         해당 기간의 내역이 없습니다.                    ");
        }
        System.out.println("==========================================================================\n");
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

        System.out.println("\n====================== 지출/수입 통계 ======================");
        System.out.printf("     총 입금액     : %,35d원%n",totalDeposit);
        System.out.printf("     고정 지출     : %,35d원%n",totalFixedWithdraw);
        System.out.printf("     유동 지출     : %,35d원%n",totalVariavleWithdraw);
        System.out.println("------------------------------------------------------------");
        System.out.printf("     총 지출 합계   : %,35d원%n",(totalFixedWithdraw + totalVariavleWithdraw));
        System.out.println("============================================================\n");
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

    public void searchTransactions(String keyword) {
        System.out.println("\n=============================== 키워드 검색 결과 ==============================");
        System.out.println("  검색어: " + keyword);
        System.out.println("----------------------------------------------------------------------------");
        boolean found = false;
        for (Transaction t : history) {
            if (t.title.contains(keyword)) {
                System.out.printf(" [ 결과 ]  |%s%n", t);
                found = true;
            }
        }
        if (!found) System.out.println("                         검색 결과가 없습니다.                         ");
        System.out.println("============================================================================\n");
    }

    private static boolean isValidDate(String date) {
        if (date.length() != 5 || date.charAt(2) != '-') return false;
        try {
            int month = Integer.parseInt(date.substring(0, 2));
            int day = Integer.parseInt(date.substring(3, 5));
            return (month >= 1 && month <= 12) && (day >= 1 && day <= 31);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String formatDate(String input) {
        if (input.length() == 4 && !input.contains("-")) {
            return input.substring(0,2) + "-" + input.substring(2);
        }
        return input;
    }

    public static void main(String[] args) {
        MyAccountManager myBank = new MyAccountManager();
        Scanner scanner = new Scanner(System.in);
        myBank.loadData();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n===============================   가계부 관리 시스템   ===============================");
            System.out.println("1. 입금 2. 지출 3. 내역조회 4. 통계보기 5. 종료 6. 내역 수정 7. 내역 삭제 8. 검색 9. 기간별조회");
            System.out.print("선택: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 메뉴 번호(숫자)만 입력해주세요.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("날짜(MMDD): ");
                    String dDate = formatDate(scanner.nextLine());
                    System.out.print("항목: ");
                    String dTitle = scanner.nextLine();
                    System.out.print("금액: ");
                    int dAmount = scanner.nextInt();
                    myBank.deposit(dDate, dTitle, dAmount);
                    break;
                case 2:
                    System.out.print("날짜(MMDD): ");
                    String wDate = formatDate(scanner.nextLine());
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
                    System.out.println("수정 번호: ");
                    int updateIdx = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (updateIdx >= 0 && updateIdx < myBank.getHistorySize()) {
                        System.out.print("새 날짜: ");
                        String nDate = formatDate(scanner.nextLine());
                        System.out.print("새 항목: ");
                        String nTitle = scanner.nextLine();
                        System.out.print("새 금액: ");
                        int nAmount = scanner.nextInt();
                        scanner.nextLine();

                        myBank.updateTransaction(updateIdx, nDate, nTitle, nAmount);
                    } else {
                        System.out.println("해당 번호를 찾을 수 없습니다.");
                    }
                    break;
                case 7:
                    myBank.showReport();
                    System.out.print("삭제 내역 번호: ");
                    int deleteIdx = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (deleteIdx >= 0 && deleteIdx < myBank.getHistorySize()) {
                        myBank.deleteTransaction(deleteIdx);
                    } else {
                        System.out.println("해당 번호를 찾을 수 없습니다.");
                    }
                    break;
                case 8:
                    System.out.println("검색할 키워드를 입력하세요.");
                    String keyword = scanner.nextLine();
                    myBank.searchTransactions(keyword);
                    break;
                case 9:
                    System.out.println("시작 날짜(MMDD): ");
                    String startDate = formatDate(scanner.nextLine());
                    if (!isValidDate(startDate)) {
                        System.out.println("날짜 형식이 올바르지 않습니다.");
                        break;
                    }
                    System.out.println("종료 날짜(MMDD): ");
                    String endDate = formatDate(scanner.nextLine());
                    if (!isValidDate(endDate)) {
                        System.out.println("날짜 형식이 올바르지 않습니다.");
                        break;
                    }
                    myBank.showRangeReport(startDate, endDate);
                    break;

                default:
                    System.out.println("잘못된 번호입니다.");
            }
        }
    }
}