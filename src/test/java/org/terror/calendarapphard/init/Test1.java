package org.terror.calendarapphard.init;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.parameters.P;
import org.terror.calendarapphard.enums.PrivateKey;

@SpringBootApplication
public class Test1 {
    @Test
    void test1(){
        System.out.println(PrivateKey.JWT_SECRET_KEY);
    }
}
