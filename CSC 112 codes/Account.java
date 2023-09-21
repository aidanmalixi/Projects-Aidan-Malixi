import java.util.Date;

public class Account

{

private int id;

private double balance;

private double annualInterestRate;

private Date dateCreated;

public Account()

{

this.id = 0;

this.balance = 0.0;

this.annualInterestRate = 0.0;

this.dateCreated = new Date();

}

public Account(int id, double balance)

{

this.id = id;

this.balance = balance;

this.annualInterestRate = 0.0;

this.dateCreated = new Date();

}

public int getID()

{

return this.id;

}

public double getBalance()

{

return this.balance;

}

public double getAnnualInterestRate()

{

return this.annualInterestRate;

}

public Date getDateCreated()

{

return this.dateCreated;

}


public void setID(int id)

{

this.id = id;

}

public void setBalance(double balance)

{

this.balance = balance;

}

public void setAnnualInterestRate(double annualInterestRate)

{

this.annualInterestRate = annualInterestRate;

}

public double getMonthlyInterestRate()

{

return this.annualInterestRate / 12.0;

}

public double getMonthlyInterest()

{

return this.getBalance() * this.getMonthlyInterestRate();

}

public void withdraw(double amount)

{

if( amount > this.getBalance() )

System.out.println("Insufficient Balance");

else

this.balance = this.balance - amount;

}

public void deposit(double amount)

{

this.balance = this.balance + amount;


}


public static void main(String[] args)

{

Account bank = new Account(1122, 20000);

bank.setAnnualInterestRate(.045);

bank.withdraw(2500);

bank.deposit(3000);

System.out.println("ID: " + bank.getID());

System.out.println("Balance: " + bank.getBalance());

System.out.println("Monthly Interest: " + (bank.getMonthlyInterest()));

System.out.println("Date Created: " + bank.getDateCreated());

}

}