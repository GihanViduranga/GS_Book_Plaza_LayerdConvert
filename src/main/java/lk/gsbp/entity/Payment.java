package lk.gsbp.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Payment {
    private String PaymentId;
    private String PaymentMethod;
    private String Date;
    private String Payment;
}
