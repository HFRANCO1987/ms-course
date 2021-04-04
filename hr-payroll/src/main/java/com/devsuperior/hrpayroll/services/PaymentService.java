package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${hr-worker.host}")
    private String workerHost;

    public Payment getPayment(long workedId, int days){
        Map<String, String> uriVariable = new HashMap<>();
        uriVariable.put("id", String.valueOf(workedId));
        Worker worker = restTemplate.getForObject(this.workerHost + "/workers/{id}", Worker.class, uriVariable);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}
