package lk.gsbp.entity;

import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class Supplier {
        private String SupplierId;
        private String SuppName;
        private String Contact;
        private String Product;
        private String ItemName;
        private String Qty;

        public Supplier(String supplierId, String suppName, String contact, String product, String qty) {
        }
}
