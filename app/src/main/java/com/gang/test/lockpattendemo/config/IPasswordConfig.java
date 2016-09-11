package com.gang.test.lockpattendemo.config;

/**
 * Created by gang on 16-9-11.
 */
public interface IPasswordConfig {

    public final static String KEY_NUM = "key_num";
    public final static String KEY_TEXT = "key_text";
    public final static String KEY_PATTERN = "key_pattern";

    void setPwd(String key, Object value);

    Object getPwd(String key);

}
