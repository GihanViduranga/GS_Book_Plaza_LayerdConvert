package lk.gsbp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
    private String PaymentId;
    private String PaymentMethod;
    private String Date;
    private String Payment;
}
