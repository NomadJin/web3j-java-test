package com.skcc.mydata.consentmanagement;

import com.skcc.mydata.consentmanagement.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.web3j.crypto.*;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;


@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {

        String profile = System.getProperty("spring.profiles.active");
        if(profile == null) {
            System.setProperty("spring.profiles.active", "dev");
        }

        SpringApplication.run(Application.class, args);
    }

    //@Autowired
    //KeyUtil keyUtil;

    //@Autowired
    //GetterSetterTest getterSetterTest;

    //@Autowired
    //ConsentContractTest consentContractTest;

    @Autowired
    GanacheTest ganacheTest;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < args.length; ++i) {
            System.out.println("args[{}]: {}" + i + args[i]);
        }

        //test.printAccounts();
        //test.getWeb3ClientVersion();
        //test.testSend();

        //keyUtil.generateWallet();
        test();

    }

    private void test() throws Exception {
        ganacheTest.test();
        //consentContractTest.getTest();
    }



}
