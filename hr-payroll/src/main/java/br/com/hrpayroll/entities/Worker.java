package br.com.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    private Long id;

    private String name;

    private Double dailyIncome;
}