package com.skcc.mydata.consentmanagement.config;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class BlockchainConfig {

    private static String TYPE_KALEIDO = "kaleido";
    @Value("${blockchain.type}")
    private String BLOCKCHAIN_TYPE;

    @Value("${blockchain.network.rpcEndpoint}")
    private String RPC_ENDPOINT;

    public String getRPC_ENDPOINT() {
        return RPC_ENDPOINT;
    }

    @Value("${blockchain.network.user}")
    private String USER;

    public String getUSER() {
        return USER;
    }


    @Value("${blockchain.network.password}")
    private String PWD;

    public String getPWD() {
        return PWD;
    }

    @Value("${blockchain.contract.address}")
    private String contractAddress;

    public String getContractAddress() {
        return contractAddress;
    }

    @Value("${blockchain.network.accountAddress}")
    private String accountAddress;

    public String getAccountAddress() {
        return accountAddress;
    }


    @Bean
    public Web3j web3j() {
        System.out.println( "getTYPE:" + BLOCKCHAIN_TYPE);
        System.out.println( "getRPC_ENDPOINT:" + RPC_ENDPOINT);
        System.out.println( "getUSER:" + USER);
        System.out.println( "getPWD:" + PWD);
        System.out.println( "getAccountAddress:" + accountAddress);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if( TYPE_KALEIDO.equals(BLOCKCHAIN_TYPE) ) {
            clientBuilder.authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic(USER, PWD);
                    return response.request().newBuilder().header("Authorization", credential).build();
                }
            });
        }
        // Create a Service object for web3 to connect to
        HttpService service = new HttpService(RPC_ENDPOINT, clientBuilder.build(), false);

        return Web3j.build(service);  ////Web3j web3j = Web3j.build(service);
    }

}
