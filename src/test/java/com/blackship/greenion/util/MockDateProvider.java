package com.blackship.greenion.util;

import java.util.Date;

public class MockDateProvider extends DateProvider {
    public Date now_returnValue;

    @Override
    public Date now() {
        return now_returnValue;
    }
}
