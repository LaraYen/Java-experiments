public class Account {

    private long money;
    private String accNumber;
    private boolean blocked;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        //Добавила синхронайзд блок, чтобы не было попыток осуществить манипуляцию со счетом из разным потоков
        synchronized (this){
            this.money = money;
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        //Добавила синхронайзд блок, чтобы не было попыток осуществить манипуляцию со счетом из разным потоков
        synchronized (this){
            this.accNumber = accNumber;
        }
    }

    public boolean isBlocked(){
        return blocked;
    }

    public Account(long money, String accNumber, boolean blocked) {
        this.money = money;
        this.accNumber = accNumber;
        this.blocked = blocked;
    }

    public void setBlock(){
        //Добавила синхронайзд блок, чтобы не было попыток осуществить манипуляцию со счетом из разным потоков
        synchronized (this){
            this.blocked = true;
        }
    }
}
