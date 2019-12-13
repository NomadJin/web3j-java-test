package com.skcc.mydata.consentmanagement;

import com.skcc.mydata.consentmanagement.config.BlockchainConfig;
import com.skcc.mydata.consentmanagement.contracts.GetterSetter;
import com.skcc.mydata.consentmanagement.test.App;
import okhttp3.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class KaleidoTest {

    private BlockchainConfig blockchainConfig;

    // Fill these in to test, ex. remove @RPC_ENDPOINT@
    private static final String USER = "k0h6brqo7d";
    private static final String PASS = "x39gQGJj_vv1Dxv8pixtZOWNmALi9tYzypBZqNGzZp8";
    private static final String RPC_ENDPOINT = "https://k0y4s2dcjl-k0wh7uhynx-rpc.kr0-aws.kaleido.io/";

    private static Web3j web3j;
    static {
        try {
            web3j = buildWeb3J();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {

        KaleidoTest t = new KaleidoTest();

        //t.printAccounts();
        t.testGetterSetter();

        System.out.println("END OF main()");

    }



    public void testSimple() {
        try {
            // Build an Authorization header using your app credentials
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.authenticator(new Authenticator() {
                @Override public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic(USER, PASS);
                   // System.out.println("credential: " + credential);
                    return response.request().newBuilder().header("Authorization", credential).build();
                }
            });
            // Create a Service object for web3 to connect to
            HttpService service = new HttpService(RPC_ENDPOINT, clientBuilder.build(), false);
            Web3j web3 = Web3j.build(service);

            // Get the latest block in the chain
            EthBlock.Block latestBlock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();

            // Print out the number of the latest block
            System.out.println("Latest Block: #" + latestBlock.getNumber());

            EthCoinbase coinbase = web3.ethCoinbase().sendAsync().get();
            System.out.println("Coinbase address: " + coinbase.getAddress());
            EthAccounts accounts = web3.ethAccounts().sendAsync().get();
            String toAddress = accounts.getResult().get(0);
            int addressCount = accounts.getResult().size();
            System.out.println("accounts count: " + addressCount);

            //EthAccounts accounts2 = web3.ethAccounts().send();


            // use the first account as the main account for the tests
            String mainAccountAddress = web3.ethAccounts().send().getAccounts().get(0);
            System.out.println("mainAccountAddress: " + mainAccountAddress);


        } catch (Exception ex) {
            // Catch any Connection errors and print them out
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printAccounts() {
        try {
            EthAccounts accounts = web3j.ethAccounts().sendAsync().get();
            // accounts.getAccounts();
            for( int i = 0; i < accounts.getAccounts().size(); i++ ) {
                System.out.println("account[" + i + "]: " + accounts.getAccounts().get(i));
            }

        } catch (Exception ex) {
            // Catch any Connection errors and print them out
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //private static final String RPC_ENDPOINT = = "http://127.0.0.1:7545";


    public static Web3j buildWeb3J() throws Exception {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(USER, PASS);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        });
        // Create a Service object for web3 to connect to
        HttpService service = new HttpService(RPC_ENDPOINT, clientBuilder.build(), false);
        Web3j web3j = Web3j.build(service);

        return web3j;
    }

    public void testGetterSetter() throws Exception {

        GetterSetter getterSetter;
        boolean usingCredentials = false;
        String contractAddress = "0x411c87Dc8D54E61c996e3a24568839b0484C5dAc";

        if( usingCredentials ) {
            System.out.println("using org.web3j.crypto.Credentials");
            org.web3j.crypto.Credentials creds = org.web3j.crypto.Credentials.create("0x3BC743663E454FB4FEB768B06D3FAC8952B3E18364AF5611AE4F3F6E1895B570");
            getterSetter = GetterSetter.load(
                    contractAddress,
                    web3j,
                    creds,
                    new StaticGasProvider(BigInteger.ZERO, BigInteger.valueOf(4_500_000))
            );

        } else {
            System.out.println("using ClientTransactionManager");
            getterSetter = GetterSetter.load(
                    contractAddress,
                    web3j,
                    new ClientTransactionManager(web3j, "0x23Ee0Ea812DfE2f85F28AE37a018d102A4ebc770"),  // address generated by metamask
                    //new ClientTransactionManager(web3j, "0xb999ad96541f17e9496f2411b71e8248b6834621"),  // address in Wallet
                    new StaticGasProvider(BigInteger.ZERO, BigInteger.valueOf(4_500_000))
            );
        }
        // generated address in metamask: 0x23Ee0Ea812DfE2f85F28AE37a018d102A4ebc770
        // private key: 3BC743663E454FB4FEB768B06D3FAC8952B3E18364AF5611AE4F3F6E1895B570

        try {
            /*
            for( int i = 0; i < 10; i++ ) {
                getterSetter.setNumber(BigInteger.ZERO).send();
                System.out.println("[" + i + "] ZERO");
                //lottery.setPot(BigInteger.ONE).send();
                //System.out.println("[" + i + "] ONE");

            }
            */
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();

            web3j.web3ClientVersion().sendAsync().thenAccept(transactionReceipt -> {

                // then accept gets transaction receipt only if the transaction is successful

            }).exceptionally(transactionReceipt  -> {
                return null;
            });
            System.out.println("[Web3ClientVersion]" + web3ClientVersion.getWeb3ClientVersion());;
            System.out.println("[Check 1]" + new Date());
            getterSetter.setNumber(BigInteger.ZERO).send();
            //System.out.println("txHash: " + tr.getTransactionHash());
            //BigInteger value = BigInteger.ZERO;
            System.out.println("[Check 2]" + new Date());
            String str = getterSetter.getString().send();
            System.out.println("getString(): " + str + " " + new Date());
            //assertEquals(BigInteger.ZERO, value);
            //assertEquals(new String(BigInteger.ZERO.toByteArray()), new String(value.toByteArray()));
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("END OF testGetterSetter()");
    }
}