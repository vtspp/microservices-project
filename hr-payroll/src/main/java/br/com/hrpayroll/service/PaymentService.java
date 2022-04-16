package br.com.hrpayroll.service;

import br.com.hrpayroll.clients.WorkerFeignClient;
import br.com.hrpayroll.entities.Payment;
import br.com.hrpayroll.entities.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public Payment getPayment (long workerId, int days) {
        Worker worker = this.workerFeignClient.findById(workerId).getBody();
        return Payment.builder()
                .name(worker.getName())
                .dailyIncome(worker.getDailyIncome())
                .days(days)
                .build();
    }
}