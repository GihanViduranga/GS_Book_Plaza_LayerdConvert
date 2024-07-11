package lk.gsbp.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Stock {
    private String StockId;
    private String ItemName;
    private String CatogaryName;
    private String QTY;
}
