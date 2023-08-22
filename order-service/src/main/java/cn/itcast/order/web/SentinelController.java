package cn.itcast.order.web;

import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流控模式有哪些？
 * •直接：对当前资源限流
 * •关联：高优先级资源触发阈值，对低优先级资源限流。
 * •链路：阈值统计时，只统计从指定资源进入当前资源的请求，是对请求来源的限流
 * 流控效果有哪些？
 * - 快速失败：QPS超过阈值时，拒绝新的请求
 * - warm up： QPS超过阈值时，拒绝新的请求；QPS阈值是逐渐提升的，可以避免冷启动时高并发导致服务宕机。
 * - 排队等待：请求会进入队列，按照阈值允许的时间间隔依次执行请求；如果请求预期等待时长大于超时时间，直接拒绝
 */
@RestController
@RequestMapping("order")
public class SentinelController {

    @Autowired
    private OrderService orderService;

    /**
     * 模拟订单查询
     */
    @GetMapping("/query")
    public String queryOrder() {
        // 查询商品
        orderService.queryGoods();
        // 查询订单
        System.out.println("查询订单");
        return "查询订单成功";
    }

    /**
     * 模拟订单更新
     */
    @GetMapping("/update")
    public String updateOrder() {
        return "更新订单成功";
    }

    /**
     * 模拟订单新增
     */
    @GetMapping("/save")
    public String save() {
        // 查询商品
        orderService.queryGoods();
        // 查询订单
        System.err.println("新增订单");
        return "新增订单成功";
    }
}
