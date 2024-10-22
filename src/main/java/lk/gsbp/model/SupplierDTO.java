package lk.gsbp.model;

import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class SupplierDTO {
        private String SupplierId;
        private String SuppName;
        private String Contact;
        private String Product;
        private String ItemName;
        private String Qty;
}
