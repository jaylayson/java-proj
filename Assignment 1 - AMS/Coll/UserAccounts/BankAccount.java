package coll.UserAccounts;

public class BankAccount {

	/**
	 * Constructs a bank object with $0.0 funds and assigns the given User as the
	 * owner.
	 * @param owner
	 */
	double funds;
	User owner;
	public BankAccount(User owner) {
		this.funds = 0;
		this.owner = owner;
	}

	/**
	 * Gets the total funds in the account.
	 * @return
	 */
	public double getFunds() {
		return this.funds;
	}

	/**
	 * Deposits money into the account.
	 * @param deposit. The sum of money in $ to be deposited.
	 */
	public void deposit(double deposit) {
		this.funds += deposit;
	}

	/**
	 * Withdraws money from the account. Only the owner can withdraw funds.
	 * @param user. The user attempting to withdraw funds.
	 * @param withdrawal. The amount to be withdrawn.
	 * @throws UserException if anyone but the owner is attempting to withdraw fundss.
	 * @throws FundsException if there are insufficient funds in the account.
	 */
	public void withdraw(User user, double withdrawal) throws UserException, FundsException {
		if(user != this.owner) {
            throw new UserException();
        } else if(withdrawal > funds) {
            throw new FundsException();
        }

        if(user == this.owner && withdrawal <= funds) {
            this.funds = funds - withdrawal;
        }

		/*try{
			if(user != this.owner) {

			throw new UserException();
			}
		}catch(UserException e) {
			System.out.println("Unauthorized!");
		}

		try{
			if(withdrawal > funds) {

			throw new FundsException();
			}
		}catch(FundsException e) {
			System.out.println("Insufficient!");
		}

		if(user == this.owner && withdrawal <= funds) {
			this.funds = funds - withdrawal;
		}*/
	}
}
