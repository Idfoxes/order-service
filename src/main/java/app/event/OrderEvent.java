package app.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderEvent {
    private String product;
    private Integer quantity;

    public OrderEvent(String product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
