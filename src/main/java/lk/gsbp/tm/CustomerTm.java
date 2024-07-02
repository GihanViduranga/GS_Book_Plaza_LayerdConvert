package lk.gsbp.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CustomerTm {
    private String CustomerId;
    private String Name;
    private String Address;
    private String Contact;
    private String Email;
}
