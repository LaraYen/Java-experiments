import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankTests extends TestCase {

    Map<String, Account> accounts = new HashMap<>();
    List<Thread>threads = new ArrayList<>();
    Bank bank = new Bank(accounts);

    public void setUp() {
        for(int i = 0; i < 100; i++){
            double money = Math.random() * 100000;
            accounts.put(Integer.toString(i), new Account((long) money, Integer.toString(i), false));
        }
    }

    public void testGetBalance(){
        accounts.put("200", new Account(300, "200", false));
        long expected = 300;
        long actual = bank.getBalance("200");
        assertEquals(expected, actual);
    }

    public void testTransfer(){
        long expected = bank.getSumAllAccounts();
        for(int i = 0; i < 100; i = i+2){
            String fromAccount = Integer.toString(i);
            String toAccount = Integer.toString(i + 1);
            new Thread(() -> bank.transfer(fromAccount, toAccount, bank.getBalance(fromAccount))).start();
            threads.add(new Thread(() -> bank.transfer(fromAccount, toAccount, bank.getBalance(fromAccount))));
        }

        threads.forEach(Thread::start);

        for (int i = 0; i < threads.size(); i++){
            while (threads.get(i).isAlive()){
            }
        }

        long actual = bank.getSumAllAccounts();
        assertEquals(expected, actual);
    }


}
