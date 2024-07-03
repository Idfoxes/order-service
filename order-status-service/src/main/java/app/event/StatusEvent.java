package app.event;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StatusEvent {
    private String status;
    private Instant date;

    public StatusEvent(String status, Instant date) {
        this.status = status;
        this.date = date;
    }
}
