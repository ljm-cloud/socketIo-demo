package com.socketio.demo.task;

import com.socketio.demo.starter.SocketIOServerStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ljm
 * @date 2023/9/25 15:16
 */
@Component
public class SocketIoTask {

    @Autowired
    private SocketIOServerStarter socketIOServerStarter;
    @Scheduled(cron = "0/5 * * * * ? ")
    public void restartSocketIOServer(){
        socketIOServerStarter.closeServer();
        socketIOServerStarter.startServer();
    }
}
