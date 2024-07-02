package lk.gsbp.repository;

import lk.gsbp.model.OrderDTO;
import lk.gsbp.model.orderDetailsDTO;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PlaceOrder {
    private OrderDTO orderDTO;
    private List<orderDetailsDTO> odList;


}
