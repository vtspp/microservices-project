package br.com.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String name;

    private Double dailyIncome;

    private int days;

    public double getTotal () {
        return this.days * this.dailyIncome;
    }
}