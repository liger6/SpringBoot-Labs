package cn.iocoder.springboot.lab46.sentineldemo.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebTotalInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcTotalConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add Sentinel interceptor
        addSentinelWebTotalInterceptor(registry);
        addSentinelWebInterceptor(registry);
    }

    private void addSentinelWebInterceptor(InterceptorRegistry registry) {
        // 创建 SentinelWebMvcConfig 对象
        SentinelWebMvcConfig config = new SentinelWebMvcConfig();
        config.setHttpMethodSpecify(true); // 是否包含请求方法。即基于 URL 创建的资源，是否包含 Method。
        // config.setBlockExceptionHandler(new DefaultBlockExceptionHandler()); // 设置 BlockException 处理器。

        // 添加 SentinelWebInterceptor 拦截器
        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");
    }

    private void addSentinelWebTotalInterceptor(InterceptorRegistry registry) {
        // 创建 SentinelWebMvcTotalConfig 对象
        SentinelWebMvcTotalConfig config = new SentinelWebMvcTotalConfig();

        // 添加 SentinelWebTotalInterceptor 拦截器
        registry.addInterceptor(new SentinelWebTotalInterceptor(config)).addPathPatterns("/**");
    }

}
