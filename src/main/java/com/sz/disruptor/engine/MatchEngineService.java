package com.sz.disruptor.engine;

import com.sz.disruptor.barrier.SequenceBarrier;
import com.sz.disruptor.buffer.RingBuffer;
import com.sz.disruptor.event.OrderEventHandler;
import com.sz.disruptor.model.OrderEventModel;
import com.sz.disruptor.model.factory.OrderEventModelFactory;
import com.sz.disruptor.processor.BatchEventProcessor;
import com.sz.disruptor.sequence.Sequence;
import com.sz.disruptor.strategy.BlockingWaitStrategy;

/**
 * @Author
 * @Date 2025-01-02 22:45
 * @Version 1.0
 */
public class MatchEngineService {

    private final RingBuffer<OrderEventModel> ringBuffer;

    //一个match service处理一个订单簿
    public MatchEngineService() {
        OrderBook orderBook = new OrderBook();
        int ringBufferSize = 1024;
        ringBuffer = RingBuffer.createSingleProducer(new OrderEventModelFactory(), ringBufferSize, new BlockingWaitStrategy());

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        BatchEventProcessor<OrderEventModel> eventProcessorA = new BatchEventProcessor<>(ringBuffer, new OrderEventHandler(orderBook), sequenceBarrier);

        Sequence consumerSequenceA = eventProcessorA.getCurrentConsumerSequence();

        ringBuffer.addGatingConsumerSequence(consumerSequenceA);

        new Thread(eventProcessorA).start();
    }

    public void submitOrder(Order order){
        long sequence = ringBuffer.next();
        try {
            OrderEventModel orderEventModel = ringBuffer.get(sequence);
            orderEventModel.setOrder(order);
            orderEventModel.setProcessed(false);
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
