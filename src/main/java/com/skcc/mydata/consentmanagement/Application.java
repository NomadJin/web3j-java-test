package com.skcc.mydata.consentmanagement;

import com.skcc.mydata.consentmanagement.model.Consent;
import com.skcc.mydata.consentmanagement.service.ConsentService;
import com.skcc.mydata.consentmanagement.util.TimeUtil;
import com.skcc.mydata.consentmanagement.util.WalletUtil;
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
import java.time.*;
import java.util.*;

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

    //@Autowired
    //GanacheTest ganacheTest;

    @Autowired
    ConsentService consentService;

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

        String consentId = "0xcfe1b029bf174b4a713f540e19044536c7f36261";
        consentId = (String)WalletUtil.generateWallet().get("address");
        String userId = "0xe5fbce49e2f4b3109f4316e5df5529c0017f8562";

        System.out.println("[WalletUtil] " +consentId);

        Instant now = Instant.now();
        BigInteger startDtm = BigInteger.valueOf(now.getEpochSecond());
        BigInteger endDtm = BigInteger.valueOf(now.getEpochSecond() + 365*24*60*60);
        BigInteger registDtm = BigInteger.valueOf(now.getEpochSecond());
        BigInteger withdrawalDtm = BigInteger.valueOf(0);


        Consent consent1 = new Consent(consentId, userId, "S1", "V1", "SINK01", "{'g1','y','g2','y'}",
                startDtm, endDtm, registDtm);
        List<Consent> consentList = new ArrayList<Consent>();
        consentList.add(consent1);

        consentService.addConsents(consentList);

        Consent c2 = consentService.getConsent(consentId);
        System.out.println("[c2-1]" + c2) ;

        Thread.sleep(1000);
        Instant now2 = Instant.now();
        consent1.setGroupInfos("{'g1','n','g2','n");
        consent1.setStartDtm(BigInteger.ONE);
        consent1.setEndDtm(BigInteger.TEN);
        consent1.setRegistDtm(BigInteger.ZERO);

        List<Consent> consentList2 = new ArrayList<Consent>();
        consentList2.add(consent1);
        consentService.updateConsents(consentList2);

        c2 = consentService.getConsent(consentId);
        System.out.println("[c2-2]" + c2) ;
        //ganacheTest.test();
        //consentContractTest.getTest();

        consentService.withdrawConsent(consentId, BigInteger.valueOf(1000));
        c2 = consentService.getConsent(consentId);
        System.out.println("[c2-3]" + c2) ;

    }



}
