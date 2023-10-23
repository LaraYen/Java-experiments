import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Account> accounts = new HashMap<>();
        List<Thread> threads = new ArrayList<>();
        Bank bank = new Bank(accounts);

        //Создаем тестовые счета
        for(int i = 0; i < 100; i++){
            double money = Math.random() * 10000;
            accounts.put(Integer.toString(i), new Account((long) money, Integer.toString(i), false));
        }

/*        for(int i = 0; i < 100; i = i+2){
            String fromAccount = Integer.toString((int)(Math.random() * 100));
            String toAccount = Integer.toString((int)(Math.random() * 100));
            System.out.println("Перевод денег со счета " + fromAccount + " на счет " + toAccount + ". Сумма " + bank.getBalance(fromAccount));
            new Thread(() -> bank.transfer(fromAccount, toAccount, bank.getBalance(fromAccount))).start();
            threads.add(new Thread(() -> bank.transfer(fromAccount, toAccount, bank.getBalance(fromAccount))));
        }*/

        //Создаем тестовые транзакции
        for(int i = 0; i < 100; i = i+2){
            String fromAccount = Integer.toString(i);
            String toAccount = Integer.toString(i + 1);
            System.out.println("Перевод денег со счета " + fromAccount + " на счет " + toAccount + ". Сумма " + bank.getBalance(fromAccount));
            new Thread(() -> bank.transfer(fromAccount, toAccount, bank.getBalance(fromAccount))).start();
            threads.add(new Thread(() -> bank.transfer(fromAccount, toAccount, bank.getBalance(fromAccount))));
        }

        //Проверяем начальное значение суммы всех счетов
        System.out.println(bank.getSumAllAccounts());

        threads.forEach(Thread::start);

        //Ждем, когда все потоки выполнятся
        for (Thread thread : threads) {
            while (thread.isAlive()) {
            }
        }


        //Проверяем конечное значение суммы всех счетов
        System.out.println(bank.getSumAllAccounts());
        System.out.println(bank.getBalance("0"));
        System.out.println(bank.getBalance("4"));
    }
}
