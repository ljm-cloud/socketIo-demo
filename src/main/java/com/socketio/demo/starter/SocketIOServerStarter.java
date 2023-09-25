package com.socketio.demo.starter;

import com.corundumstudio.socketio.SocketIOChannelInitializer;
import com.corundumstudio.socketio.SocketIOServer;
import com.socketio.demo.config.SocketIOConfig;
import com.socketio.demo.helper.ThreadPoolHelper;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SocketIOServerStarter {
    @Autowired
    SocketIOServer socketIOServer;

    @Autowired
    SocketIOConfig socketIOConfBean;

    @PostConstruct
    public void startServer() {
        if (!StringUtils.isNotBlank(socketIOConfBean.getHostname()) || socketIOConfBean.getPort() == null || socketIOConfBean.getPort()<1) {
            return;
        }
        socketIOServer.start();
    }

    public void closeServer() {
        if(socketIOServer!=null){
            socketIOServer.stop();
        }
    }

    public void socketIORestart(){
        socketIOServer.stop();
        SocketIOChannelInitializer pipelineFactory = new SocketIOChannelInitializer();
        socketIOServer.setPipelineFactory(pipelineFactory);
        socketIOServer.start();
    }

    /**
     * 断开所有连接,如果直接使用SocketIOClient的disconnect,则屏幕的socketIo不能进行重连
     * 需关闭socketIo服务器,再重新启动socketIo服务器
     *
     * @param restartFailRetryNum socketIOServer重启失败重试次数
     */
    public void socketIORestart(int restartFailRetryNum) {
        socketIOServer.stop();
        log.info("stop socketIoServer");
        ThreadPoolHelper.commonScheduledThreadPool.schedule(() -> {
            SocketIOChannelInitializer pipelineFactory = new SocketIOChannelInitializer();
            socketIOServer.setPipelineFactory(pipelineFactory);
            //直接start会报错：已经停止的服务不能再启动，报的错误正是在pipelineFactory.start里，所以需要初始化一个新的SocketIOChannelInitializer并传进去(socketIOServer.setPipelineFactory)
//            socketIOServer.start();
            Future<Void> socketIoServerStartFuture = socketIOServer.startAsync();
            socketIoServerStartFuture.addListener((FutureListener<Void>) future -> {
                if (future.isSuccess()) {
                    log.info("reStart socketIoServer success");
                } else {
                    if (restartFailRetryNum > 0) {
                        int reTryRemainNum = restartFailRetryNum - 1;//剩余重试次数
                        log.info("reStart socketIoServer error|reTryRemainNum:{}", reTryRemainNum);
                        socketIORestart(reTryRemainNum);
                    }
                }
            });
        }, 10, TimeUnit.SECONDS);//N秒后重新启动
    }

}
