package com.sz.disruptor.model;

import com.sz.disruptor.engine.Order;
import com.sz.disruptor.engine.OrderBook;
import lombok.Data;

/**
 * @Author
 * @Date 2024-12-08 15:29
 * @Version 1.0
 */
@Data
public class OrderEventModel {

  private Order order;

  private boolean processed;
}
