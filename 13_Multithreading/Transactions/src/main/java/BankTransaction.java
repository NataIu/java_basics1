import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {
    private String fromAccountNum;
    private String toAccountNum;
    private long value;
}
