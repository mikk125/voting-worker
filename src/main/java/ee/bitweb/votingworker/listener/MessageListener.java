package ee.bitweb.votingworker.listener;

import ee.bitweb.votingworker.config.MQConfig;
import ee.bitweb.votingworker.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageListener {

    @Value("${DB_APP_PORT:8080}")
    private String dbAppPort;

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(Message message) {
        this.restTemplate.postForEntity(String.format("http://localhost:%s/voting/new", dbAppPort), message, Void.class);
    }

}
