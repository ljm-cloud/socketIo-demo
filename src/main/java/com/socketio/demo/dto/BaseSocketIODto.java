package com.socketio.demo.dto;

import lombok.Data;

/**
 * @author ljm
 * @date 2023/9/25 14:33
 */
@Data
public class BaseSocketIODto {
    protected String wcName;
    protected String siteName;
    protected String roomType;
}
