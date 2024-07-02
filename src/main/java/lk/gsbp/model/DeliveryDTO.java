package lk.gsbp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeliveryDTO {
    private String DeliveryId;
    private String DeliverName;
    private String Date;
    private String Address;
    private String Stetus;
}
