package br.com.hrpayroll.resources;

import br.com.hrpayroll.entities.Payment;
import br.com.hrpayroll.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
@RequestMapping (value = "/v1/payments")
public class PaymentResource {

    private final PaymentService service;

    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment (@PathVariable Long workerId, @PathVariable int days) {
        return ResponseEntity.ok(this.service.getPayment(workerId, days));
    }
}