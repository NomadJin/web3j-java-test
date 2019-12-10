package com.skcc.mydata.consentmanagement;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.web3j.crypto.*;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.UUID;


@Component
public class KeyUtil {

    public JSONObject generateWallet() throws Exception{

        String seed = UUID.randomUUID().toString();
        //JSONObject result = process(seed); // get a json containing private key and address
        org.springframework.boot.configurationprocessor.json.JSONObject processJson = new JSONObject();

        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

            String sPrivatekeyInHex = privateKeyInDec.toString(16);

            WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);
            String sAddress = aWallet.getAddress();

            //System.out.println("address=0x" + sAddress);
            //System.out.println("privatekey=" + sPrivatekeyInHex);

            processJson.put("address", "0x" + sAddress);
            processJson.put("privatekey", sPrivatekeyInHex);


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        //System.out.println( processJson.toString() );
        return processJson;
    }

}
