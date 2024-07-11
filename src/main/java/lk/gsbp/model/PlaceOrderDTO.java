package lk.gsbp.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PlaceOrderDTO {
    private OrderDTO orderDTO;
    private List<orderDetailsDTO> odList;
}
