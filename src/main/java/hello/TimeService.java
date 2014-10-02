package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class GreetingController.
 *
 * @author Boris Pronin (<a href="mailto:bpronin@bttprime.com">bpronin@bttprime.com</a>)
 */
@Service
public class TimeService implements ApplicationListener<BrokerAvailabilityEvent> {

    private final MessageSendingOperations<String> messagingTemplate;
    private final AtomicBoolean brokerAvailable = new AtomicBoolean();

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        this.brokerAvailable.set(event.isBrokerAvailable());
    }

    @Autowired
    public TimeService(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void send() {
        if (this.brokerAvailable.get()) {
            this.messagingTemplate.convertAndSend("/topic/time", new Date().toString());
        }
    }
}