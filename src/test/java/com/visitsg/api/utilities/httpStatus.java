package com.visitsg.api.utilities;

public class httpStatus {
    public enum HttpStatusCodes {
        OK(200, "OK");
        private final int code;
        private final String reason;

        HttpStatusCodes(int code, String reason) {
            this.code = code;
            this.reason = reason;
        }

        public int getCode() {
            return code;
        }

        public String getReason() {
            return reason;
        }

    }
}
