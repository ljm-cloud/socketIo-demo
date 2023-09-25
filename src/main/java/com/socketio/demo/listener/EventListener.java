package com.socketio.demo.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.socketio.demo.dto.AqCurrentDto;
import com.socketio.demo.dto.AqEventDataDto;
import com.socketio.demo.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.socketio.demo.helper.SocketIOHelper.AQ_CURRENT;

/**
 * @author ljm
 * @date 2023/9/25 14:24
 */
@Service
@Slf4j
public class EventListener {

    @OnConnect
    public void onConnect(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        String deviceId = client.getHandshakeData().getSingleUrlParam("deviceId");
        log.info("connect.onConnect:{}|{}|{}", sessionId, deviceId, client.getRemoteAddress().toString());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("socket.client.disconnect:{}", client.getSessionId().toString());
    }

    @OnEvent(AQ_CURRENT)
    public void onAqCurrent(SocketIOClient client, AqCurrentDto aqCurrentDto){
        //接收客户端监听空气质量数据的请求，这里直接返回固定数据给客户端
        log.info("connect.AQ_CURRENT:{}|{}",  client.getSessionId().toString(), JSONUtils.beanToJson(aqCurrentDto));
        client.sendEvent(AQ_CURRENT,new AqEventDataDto(2,1,1f,1f,1f,1f,23.6f,1000,10f));
    }
}
