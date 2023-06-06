package org.hechuans.demo.security.indb.model.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 11:22
 * @description :
 * @since : version-1.0
 */
@Data
@Builder
public class ReturnData {

    private Integer code;

    private String msg;

    private Object data;

}
