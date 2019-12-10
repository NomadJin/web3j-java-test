package com.skcc.mydata.consentmanagement;

import com.skcc.mydata.consentmanagement.config.KaleidoConfig;
import com.skcc.mydata.consentmanagement.contracts.GetterSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import javax.annotation.PostConstruct;
import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GanacheTest {

    private static String CONTRACT_ADDRESS = "0x0f09DB9d9B30C4f1384093a3188fc26863D031d7";
    private static String ACCOUNT_ADDRESS = "0xe5fbce49e2f4b3109f4316e5df5529c0017f8562";
    private static String GANACHE_URL = "http://localhost:7545";

    private Web3j web3j;

    GanacheTest() {
        web3j = Web3j.build(new HttpService(GANACHE_URL));
    }

    // 실행
    public void test() {
        try {
            getOwner();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Address getOwner() throws Exception {

        // 1 ~ 3. 토픽 얻어오기
        String topic = getTopic2();

        // 4. 토픽을 통한 Log 얻어오기.
        List<EthLog.LogResult> filter = createFilterAndRead(topic);

        // 출력!

        //filter.stream().forEach((result) -> System.out.println("RESULT: " + ((EthLog.LogResult)result).get()));

        return null;
    }

    private String getTopic() throws Exception {
        // 1. Function제작
        Function function = new Function("getOwner",
                Collections.emptyList(),
                Arrays.asList(new TypeReference<Address>() {}));

        String txHash = ethSendTransaction(function);
        System.out.println("txHash = " + txHash);

        // 2. transaction 발생
        TransactionReceipt receipt = waitForReceipt(web3j, txHash);
        System.out.println("receipt = " + receipt);
        receipt.getLogs().forEach( (log) -> {
            System.out.println("[LOG] " + log);
            log.getTopics().forEach( topic -> System.out.println("[TOPIC] " + topic));

        });

        // 3. Receipt에서 topic확인
        String s = receipt.getLogs().get(0).getTopics().get(0);
        System.out.println("s = " + s);

        List<Log> logs = receipt.getLogs();

        Log log = logs.get(0);

        List<String> topics = log.getTopics();

        for (String topic : topics) {
            System.out.println("topic: " + topic);

        }

        return s;
    }

    private String getTopic2() throws Exception {
        // 1. Function제작
        Function function = new Function("setPot",
                Arrays.asList(new org.web3j.abi.datatypes.generated.Uint256(1)),
                Collections.emptyList());

        String txHash = ethSendTransaction(function);
        System.out.println("txHash = " + txHash);

        // 2. transaction 발생
        TransactionReceipt receipt = waitForReceipt(web3j, txHash);
        System.out.println("receipt = " + receipt);

        // 3. Receipt에서 topic확인
        //String s = receipt.getLogs().get(0).getTopics().get(0);
        //System.out.println("s = " + s);


        if("0x0".equals(receipt.getStatus())) {
            // NOW
            //Request<?, EthTransaction> req = web3j.ethGetTransactionByHash(receipt.getTransactionHash());
            //EthTransaction tx2 = web3j.ethGetTransactionByHash(receipt.getTransactionHash()).send();
            //org.web3j.protocol.core.methods.response.Transaction tx3 = tx2.getTransaction().get();
            //Transaction transaction = tx2.getTransaction().get();
            Thread.sleep(1000);
            System.out.println("1");
            Thread.sleep(10000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("3");

            Transaction transaction = Transaction.createEthCallTransaction(ACCOUNT_ADDRESS, CONTRACT_ADDRESS, FunctionEncoder.encode(function));

            EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.valueOf( receipt.getBlockNumber().toString() )).send();


            if( ethCall.reverts() ) {
                System.out.println( ethCall.getRevertReason() );

                return null;
            } else {

                if( true ) return null;
                System.out.println(ethCall.getValue());
                //4. 결과값 decode
                List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                        function.getOutputParameters());

                System.out.println("ethCall.getResult() = " + ethCall.getResult());
                //FunctionReturnDecoder.decode(  )
                System.out.println("getValue = " + decode.get(0).getValue());
                System.out.println("getType = " + decode.get(0).getTypeAsString());
                System.out.println("getValue = " + decode.get(1).getValue());
                System.out.println("getType = " + decode.get(1).getTypeAsString());



            }

        }

        //3. ethereum 호출후 결과 가져오기
        //EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
        //EthCall ethCall = web3j.ethCall(tx3, receipt.getBlockNumber()).send();


        return "s";
    }

    /*event Log 확인*/
    private List<EthLog.LogResult> createFilterAndRead(String topic) throws IOException {
        EthFilter ethFilter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                //DefaultBlockParameterName.LATEST,
                DefaultBlockParameterName.LATEST,
                CONTRACT_ADDRESS);

        String topic2 = "0x7fb9819a09a35786a1935798187608d12fb9f74418c5224871a8ca49181647c9";
        ethFilter.addSingleTopic(topic);
        //System.out.println("YYYYYYY");
        EthLog ethLog = web3j.ethGetLogs(ethFilter).send();
        //System.out.println("XXXXX");
        return ethLog.getLogs();
    }

    private String ethSendTransaction(Function function) throws Exception {
        BigInteger nonce = getNonce();

        String encodedFunction = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createFunctionCallTransaction(
                ACCOUNT_ADDRESS,
                nonce,
                BigInteger.ZERO,
                BigInteger.valueOf(4_500_000),
                CONTRACT_ADDRESS,
                encodedFunction);


        org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =
                web3j.ethSendTransaction(transaction).send();//Async().get();

        return transactionResponse.getTransactionHash();
    }


    /**
     * Waits for the receipt for the transaction specified by the provided tx hash.
     * Makes 40 attempts (waiting 1 sec. inbetween attempts) to get the receipt object.
     * In the happy case the tx receipt object is returned.
     * Otherwise, a runtime exception is thrown.
     */
    private static TransactionReceipt waitForReceipt(Web3j web3j, String transactionHash)
            throws Exception
    {

        int attempts = 40;//Web3jConstants.CONFIRMATION_ATTEMPTS;
        int sleep_millis = 1000;//Web3jConstants.SLEEP_DURATION;

        Optional<TransactionReceipt> receipt = getReceipt(web3j, transactionHash);

        while(attempts-- > 0 && !receipt.isPresent()) {
            Thread.sleep(sleep_millis);
            receipt = getReceipt(web3j, transactionHash);
        }

        if (attempts <= 0) {
            throw new RuntimeException("No Tx receipt received");
        }

        return receipt.get();
    }

    /**
     * Returns the TransactionRecipt for the specified tx hash as an optional.
     */
    private static Optional<TransactionReceipt> getReceipt(Web3j web3j, String transactionHash)
            throws Exception
    {
        EthGetTransactionReceipt receipt = web3j
                .ethGetTransactionReceipt(transactionHash)
                .sendAsync()
                .get();

        return receipt.getTransactionReceipt();
    }

    private BigInteger getNonce() throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                ACCOUNT_ADDRESS, DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        System.out.println( "NONCE:" + nonce);

        return nonce;
    }

}