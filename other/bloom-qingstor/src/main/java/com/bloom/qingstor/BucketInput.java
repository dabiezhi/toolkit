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
        putObjectFetch(bucket, "", "curry/5.wav");
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