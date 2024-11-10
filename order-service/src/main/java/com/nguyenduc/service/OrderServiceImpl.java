package com.nguyenduc.service;

import com.nguyenduc.mapper.OrderMapper;
import com.nguyenduc.model.Order;
import com.nguyenduc.shared.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    private OrderMapper orderMapper;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        //convert to Entity
        //Persist to DB
        //Publish to ActiveMQ

        Order order = orderMapper.convert(request);
    }

    @Override
    public void getOrder(GetOrderRequest request, StreamObserver<OrderResponse> responseObserver) {

    }
}
