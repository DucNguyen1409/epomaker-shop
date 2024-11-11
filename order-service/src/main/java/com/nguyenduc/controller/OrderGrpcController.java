package com.nguyenduc.controller;

import com.nguyenduc.mapper.OrderMapper;
import com.nguyenduc.model.Order;
import com.nguyenduc.publisher.OrderPublisher;
import com.nguyenduc.service.OrderService;
import com.nguyenduc.shared.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Objects;

@GrpcService
@RequiredArgsConstructor
public class OrderGrpcController extends OrderServiceGrpc.OrderServiceImplBase {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final OrderPublisher orderPublisher;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        //convert to Model
        Order order = orderMapper.convert(request);

        //Persist to DB
        orderService.save(order);

        //Publish to ActiveMQ
        orderPublisher.publish(order);

        responseObserver.onNext(orderMapper.convert(order));
        responseObserver.onCompleted();
    }

    @Override
    public void getOrder(GetOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        Order orderById = orderService.getOrderById(request.getOrderId());

        if (Objects.nonNull(orderById.getOrderId())) {
            responseObserver.onNext(orderMapper.convert(orderById));
            responseObserver.onCompleted();
        }

        responseObserver.onNext(null);
        responseObserver.onCompleted();
    }
}
