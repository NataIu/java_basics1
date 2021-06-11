import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private volatile long money; //! не кешируем, чтобы корректно проверять остаток на счете
    private String accNumber;
    private volatile boolean  isBlocked;  //! не кешируем, чтобы знать актуальнок состояние счета

    public void increaseMoney(long sum) throws BankOperationException {
//        checkBlockingBeforeBankOperation();
        money = money + sum;
    }

    public void decreaseMoney(long sum) throws BankOperationException {
//        checkBlockingBeforeBankOperation();
        //считаю, что остаток на счете может быть отрицательным
        money = money - sum;
    }

    private void checkBlockingBeforeBankOperation() throws BankOperationException {
        if (isBlocked) {
            throw new BankOperationException("Account "+accNumber+" is blocked");
        }
    }

}
