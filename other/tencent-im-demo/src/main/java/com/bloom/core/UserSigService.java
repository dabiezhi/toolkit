package com.bloom.core;

import com.tencentyun.TLSSigAPIv2;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author taosy
 * Created by on 2022-03-17 上午11:37
 */
@NoArgsConstructor
@Component
public class UserSigService {

    @Value("${IMConfig.sdkAppId}")
    public long   sdkAppId;
    @Value("${IMConfig.secretKey}")
    public String secretKey;

    private long  expire = 60 * 60 * 24 * 7;

    public String generateUserSig(String userId) {
        TLSSigAPIv2 apIv2 = new TLSSigAPIv2(sdkAppId, secretKey);
        return apIv2.genUserSig(userId, expire);
    }

}