package com.bloom.qingstor;

import com.qingstor.sdk.config.EnvContext;
import com.qingstor.sdk.service.Bucket;
import com.qingstor.sdk.service.QingStor;

/**
 * @author taosy
 * Created by on 2022-07-15 下午4:16
 */
public class MyBucket {

    public static final String url = "";

    public static Bucket of() {
        EnvContext env = new EnvContext("VYMXFLGEMEZDHGMZKIXW",
            "ddOCoJNUYij6DAQo1gcbZdW12QfflP5bKcybXuMh");
        QingStor stor = new QingStor(env);
        String zone = "pek3b";
        String bucketName = "curry123";
        return stor.getBucket(bucketName, zone);
    }
}