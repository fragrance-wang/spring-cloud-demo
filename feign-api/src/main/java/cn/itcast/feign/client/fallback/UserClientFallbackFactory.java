package cn.itcast.feign.client.fallback;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务失败后，不能直接报错，而应该返回用户一个友好提示或者默认结果，这个就是失败降级逻辑。
 * 给FeignClient编写失败后的降级逻辑
 * ①方式一：FallbackClass，无法对远程调用的异常做处理
 * ②方式二：FallbackFactory，可以对远程调用的异常做处理，我们选择这种
 */
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public User findById(Long id) {
                log.error("查询用户异常", throwable);
                return new User();
            }
        };
    }
}
