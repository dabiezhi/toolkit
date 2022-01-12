package com.bloom.bloompoi.writer;

import javax.servlet.http.HttpServletResponse;

import lombok.Data;

/**
 * 用于包装HttpServletResponse对象和下载文件名
 *
 * @author bloom
 * @date 2018/2/25
 */
@Data
public class ResponseWrapper {

    private final HttpServletResponse servletResponse;
    private final String fileName;

    public ResponseWrapper(HttpServletResponse servletResponse, String fileName) {
        this.servletResponse = servletResponse;
        this.fileName = fileName;
    }

    public static ResponseWrapper of(HttpServletResponse servletResponse, String fileName) {
        return new ResponseWrapper(servletResponse, fileName);
    }

}
