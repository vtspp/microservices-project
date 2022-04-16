package br.com.hrpayroll.service;

import br.com.hrpayroll.entities.Payment;
import br.com.hrpayroll.entities.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class PaymentService {

    private final RestTemplate restTemplate;

    @Value("${hr-worker.host}")
    private String webserviceHrWorker;

    public Payment getPayment (long workerId, int days) {
        Worker worker = this.restTemplate.getForObject (
                this.webserviceHrWorker.concat("/v1/workers/{id}"), Worker.class, new Object[] { String.valueOf(workerId) }
        );
        return Payment.builder()
                .name(worker.getName())
                .dailyIncome(worker.getDailyIncome())
                .days(days)
                .build();
    }
}