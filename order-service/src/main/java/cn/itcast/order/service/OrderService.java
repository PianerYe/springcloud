package cn.itcast.order.service;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.feign.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Resource
    private UserClient userClient;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.用Feign远程调用
        User user = userClient.findById(order.getUserId());
        //3.封装user到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
    /*@Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //url路径
//        String url = "http://localhost:8080/user/"+ order.getUserId();
        String url = "http://userservice/user/" + order.getUserId();
        //发送http请求，实现远程调用
        User user = restTemplate.getForObject(url, User.class);
        //封装user到Order
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
