package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQuery;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQuery orderSimpleQuery;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order o : all) {
            o.getMember().getUsername();
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public ResultOrderDto orderV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> orderList = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new ResultOrderDto(orderList.size(), orderList);
    }

    @GetMapping("/api/v3/simple-orders")
    public ResultOrderDto orderV3() {
        List<Order> orders = orderRepository.findAllByMemberDelivery();
        List<SimpleOrderDto> orderList = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new ResultOrderDto(orderList.size(), orderList);
    }

    @GetMapping("/api/v4/simple-orders")
    public ResultOrderDto orderV4() {
        List<OrderSimpleQueryDto> orderDtos = orderSimpleQuery.findOrderDtos();
        return new ResultOrderDto(orderDtos.size(), orderDtos);
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getUsername(); //LAZY 초기화
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }

    @Data
    @AllArgsConstructor
    static class ResultOrderDto<T> {
        private int count;
        private T data;
    }
}
