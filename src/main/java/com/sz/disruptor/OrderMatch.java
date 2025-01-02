package com.sz.disruptor;

import com.sz.disruptor.engine.MatchEngineService;
import com.sz.disruptor.engine.Order;
import com.sz.disruptor.engine.OrderConstant;

/**
 * @Author
 * @Date 2025-01-02 22:56
 * @Version 1.0
 */
public class OrderMatch {

    public static void main(String[] args) {
        MatchEngineService matchEngineService = new MatchEngineService();
        // buy: 1000 990
        // sell : 995 1005
        Order buyOrder1 = new Order(200L, 1006L, "order1", OrderConstant.BUY);
        Order buyOrder2 = new Order(200L, 990L, "order2", OrderConstant.BUY);
        Order sellOrder1 = new Order(150L, 995L, "order3", OrderConstant.SELL);
        Order sellOrder2 = new Order(100L, 1005L, "order4", OrderConstant.SELL);

        matchEngineService.submitOrder(buyOrder1);
        matchEngineService.submitOrder(sellOrder1);
        matchEngineService.submitOrder(buyOrder2);
        matchEngineService.submitOrder(sellOrder2);


    }
}
