package com.sz.disruptor.event;

import com.sz.disruptor.engine.Order;
import com.sz.disruptor.engine.OrderBook;
import com.sz.disruptor.engine.Trade;
import com.sz.disruptor.model.OrderEventModel;

import java.util.List;

/**
 * @Author
 * @Date 2024-12-08 15:32
 * @Version 1.0
 * 订单事件处理器
 */
public class OrderEventHandler implements EventHandler<OrderEventModel>{

    private final OrderBook orderBook;

    public OrderEventHandler(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    public void consume(OrderEventModel event, long sequence, boolean endOfBatch) {
        if(!event.isProcessed()){
            //未处理
            Order order = event.getOrder();
            List<Trade> trades = orderBook.process(order);
            if(!trades.isEmpty()){
                processTrade(trades);
            }
            event.setProcessed(true);
        }
    }

    private void processTrade(List<Trade> trades) {
        for (Trade trade : trades) {
            System.out.println("Trade executed: " + trade);
        }
    }
}
