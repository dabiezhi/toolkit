package com.bloom.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author taosy
 * Created by on 2022-03-18 下午1:54
 */
@Data
public class Msg {

    @JsonProperty(value = "CallbackCommand")
    private String        callbackCommand;
    @JsonProperty(value = "From_Account")
    private String        fromAccount;
    @JsonProperty(value = "To_Account")
    private String        toAccount;
    @JsonProperty(value = "MsgSeq")
    private String        msgSeq;
    @JsonProperty(value = "MsgRandom")
    private String        msgRandom;
    @JsonProperty(value = "msgTime")
    private String        msgTime;
    @JsonProperty(value = "MsgKey")
    private String        msgKey;
    @JsonProperty(value = "OnlineOnlyFlag")
    private Integer       onlineOnlyFlag;
    @JsonProperty(value = "cloudCustomData")
    private String        cloudCustomData;
    @JsonProperty(value = "MsgBody")
    private List<MsgBody> msgBody;

    @Data
    static class MsgBody {
        @JsonProperty(value = "MsgType")
        private String              msgType;
        @JsonProperty(value = "MsgContent")
        private Map<String, String> msgContent;
    }

}