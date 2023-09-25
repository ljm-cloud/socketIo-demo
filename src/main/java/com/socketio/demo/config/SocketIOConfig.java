package com.socketio.demo.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author ljm
 * @date 2023/9/25 11:30
 */
@org.springframework.context.annotation.Configuration
@Data
public class SocketIOConfig {
    @Value("${socketio.hostname:0.0.0.0}")//默认为0.0.0.0，服务器将监听来自所有网络接口的连接。这意味着它可以通过任何可用的网络接口接受来自不同主机或计算机的连接请求
    private String hostname = "0.0.0.0";
    @Value("${ssocketio.port:9092}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer(){
        Configuration config = new Configuration();
        config.setAllowCustomRequests(true);
        config.setHostname(hostname);
        config.setPort(port);
        SocketIOServer server = new SocketIOServer(config);
//        server.addListeners(eventListennter);
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        // 创建 SpringAnnotationScanner 实例，该实例将扫描并注册 Socket.IO 事件处理器等组件。
        return new SpringAnnotationScanner(socketIOServer);
    }
}
