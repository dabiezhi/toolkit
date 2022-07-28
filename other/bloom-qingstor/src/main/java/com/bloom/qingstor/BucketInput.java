package com.bloom.qingstor;

import com.qingstor.sdk.exception.QSException;
import com.qingstor.sdk.service.Bucket;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author taosy
 * Created by on 2022-07-15 下午3:14
 */
public class BucketInput {

    public static void main(String[] args) {
        Bucket bucket = MyBucket.of();
        putObjectFetch(bucket,
            "https://rtcstor-7.huaweirtc.cn:9443/sp-h81xt9hoksxkmhkn5j85qc2gzc3u/22072619095812008532781.wav?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220727T015127Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=E30B0F4EE944341FC4C9%2F20220727%2F%2Fs3%2Faws4_request&X-Amz-Signature=bfa57b267683d40bee1f4853406726a43641f98d625275973b99eca8d2a2b70d",
            "curry/5.wav");
    }

    private static void putObjectFetch(Bucket bucket, String fetchSource, String newObjectKey) {
        InputStream is = HttpUtil.me(fetchSource);
        try {

            Bucket.PutObjectInput input = new Bucket.PutObjectInput();
            input.setBodyInputStream(is);
            Bucket.PutObjectOutput output = bucket.putObject(newObjectKey, input);
            if (output.getStatueCode() == 201) {
                // Success
                System.out.println("Put Object - Fetch success.");
            } else {
                // Failed
                System.out.println("Put Object - Fetch failed.");
                System.out.println("StatueCode = " + output.getStatueCode());
                System.out.println("Message = " + output.getMessage());
                System.out.println("RequestId = " + output.getRequestId());
                System.out.println("Code = " + output.getCode());
                System.out.println("Url = " + output.getUrl());
            }
        } catch (QSException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

}