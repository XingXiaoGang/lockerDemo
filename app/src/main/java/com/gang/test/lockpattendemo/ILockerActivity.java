package com.gang.test.lockpattendemo;

/**
 * Created by gang on 16-8-14.
 */
public interface ILockerActivity {

    enum LockEvent {

        SUCCESS("SUCCESS"), FAILED("FAILED"), ERRO("ERRO");

        public String message;

        LockEvent(String s) {
            this.message = s;
        }
    }

    enum Mode {
        MODE_SET_PASSWORD, MODE_SET_UNLOCK, MODE_USE
    }

    /**
     * 获取密码
     **/
    Object generatePassword();

    /**
     * 最多重试次数
     **/
    int generateMaxFailTimes();

    /**
     * 觖锁失败
     **/
    void onUnLockFial(LockEvent lockEvent);

}
