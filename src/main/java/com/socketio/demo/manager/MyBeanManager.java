package com.socketio.demo.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyBeanManager {

    private final ApplicationContext applicationContext;

    @Autowired
    public MyBeanManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void removeBean(Object bean) {
        // 获取可配置的Bean工厂
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory());
        beanFactory.destroyBean(bean);
        // 销毁并移除指定bean
    }
}