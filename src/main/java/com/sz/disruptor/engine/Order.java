package com.sz.disruptor.engine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author
 * @Date 2024-12-30 21:30
 * @Version 1.0
 * 委托单：限价委托单、市价委托单、止损委托单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long amount;

    private Long price;

    private String id;

    /**
     * 0代表卖sell
     * 1代表买buy
     */
    private Integer side;
}
