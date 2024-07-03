package app.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderEvent {
    private String product;
    private Integer quantity;
}
