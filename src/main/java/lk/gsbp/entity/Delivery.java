package lk.gsbp.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Delivery {
    private String DeliveryId;
    private String DeliverName;
    private String Date;
    private String Address;
    private String Stetus;
}
