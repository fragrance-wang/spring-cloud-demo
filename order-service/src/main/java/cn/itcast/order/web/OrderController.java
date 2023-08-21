package cn.itcast.order.web;

import cn.hutool.json.JSONUtil;
import cn.itcast.feign.pojo.Order;
import cn.itcast.order.config.WangshanConfiguration;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope//方式一：实现nacos配置热更新（无需重启微服务，使修改配置自动生效），添加 @RefreshScope注解
@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

    @Value("${logging.pattern.dateformat}")
    private String dateformat;

    /**
     * 方式二：使用ConfigurationProperties注解类，自动配置更新
     */
    @Autowired
    private WangshanConfiguration wangshanConfiguration;


    @GetMapping("/{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }

    @GetMapping("/format")
    public String getLoggerFormat() {
        return dateformat;
    }

    @GetMapping("/ws-config")
    public String getWsConfig() {
        return JSONUtil.toJsonStr(wangshanConfiguration);
    }

}
