package lk.gsbp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StockDTO {
    private String StockId;
    private String ItemName;
    private String CatogaryName;
    private String QTY;
}
