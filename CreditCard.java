public class CreditCard {
    //instance variables
    private String customer;
    private String bank;
    private String account;
    private int limit;
    protected double balance;

    //constructors
    public CreditCard(String cust, String bk, String acnt, int lim, double initialBal){
        customer = cust;
        bank = bk;
        account = acnt;
        limit = lim;
        balance = initialBal;
    }
    public CreditCard(String cust, String bk, String acnt, int lim){
        this(cust,bk,acnt,lim,0.0);
    }

    //accessor methods
    public String getCustomer(){return customer;}
    public String getBank(){return bank;}
    public String getAccount(){return account;}
    public int getLimit(){return limit;}
    public double getBalance(){return balance;}

    //update methods
    public boolean charge(double price){
        if (price + balance > limit){
            return false;
        }
        balance += price;
        return true;
    }
    public void makePayment(double amount){
        balance -= amount;
    }
    //utility method 
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Customer = " + this.getCustomer());
        sb.append("\n\rBank = " + this.getBank());
        sb.append("\n\rAccount = "+ this.getAccount());
        sb.append("\n\rBalance = " + this.getBalance());
        sb.append("\n\rLimit = " + this.getLimit());
        return sb.toString();
    }

    public static void main (String[] args){
        CreditCard[] wallet = new CreditCard[3];
        wallet[0] = new CreditCard("John Bowan", "California Savings",
            "5391 4456 6845 6578", 5000);
        wallet[1] = new CreditCard("John Bowan", "California Federal",
            "5564 4584 7788 4454", 3500);
        wallet[2] = new CreditCard("John Bowan", "California Finance",
            "5864 4334 7748 4454", 2500,300);

        for (int val = 1; val <=16; val++){
            wallet[0].charge(3*val);
            wallet[1].charge(2*val);
            wallet[2].charge(val);
        }

        for(CreditCard card : wallet){
            System.out.println(card.toString());
            while(card.getBalance() > 200.0){
                card.makePayment(200);
                System.out.println("New Balance = " + card.getBalance());
            }
        }
    }


}