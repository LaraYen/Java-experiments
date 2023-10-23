import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Bank {

    private final Map<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public Bank(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        //Если аккаунты уже заблокированы, перенос средств не происходит.
        if (accounts.get(fromAccountNum).isBlocked() || accounts.get(toAccountNum).isBlocked()){
            System.out.println("Один или оба указанных счета заблокированы.");
        } else {
            //Если сумма перевода больше 50000 отправляем счета на проверку
            if (amount > 50000){
                try {
                    if (isFraud(fromAccountNum, toAccountNum, amount)){
                        accounts.get(fromAccountNum).setBlock();
                        accounts.get(toAccountNum).setBlock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //Иначе зачсиляем средства
                accounts.get(toAccountNum).setMoney(getBalance(toAccountNum) + amount);
                accounts.get(fromAccountNum).setMoney(getBalance(fromAccountNum) - amount);
            }
        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;
        for (Account value: accounts.values()){
            sum = sum + value.getMoney();
        }
        return sum;
    }
}
