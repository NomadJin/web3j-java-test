package com.skcc.mydata.consentmanagement;

import com.skcc.mydata.consentmanagement.config.BlockchainConfig;
import com.skcc.mydata.consentmanagement.contracts.ConsentContract;
import com.skcc.mydata.consentmanagement.model.Consent;
import com.skcc.mydata.consentmanagement.service.ConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.*;

import org.web3j.abi.datatypes.Function;

import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.DefaultBlockParameterName;


@Component
public class ConsentContractTest {
    @Autowired
    private BlockchainConfig blockchainConfig;

    @Autowired
    private Web3j web3j;

    private ConsentContract consentContract;

    @Autowired
    private ConsentService consentService;

    //@PostConstruct
    private void init() {

        boolean usingCredentials = false;
        String contractAddress = blockchainConfig.getContractAddress();

        if( usingCredentials ) {
            System.out.println("using org.web3j.crypto.Credentials (using privarte key) ...");
            org.web3j.crypto.Credentials creds = org.web3j.crypto.Credentials.create("0x3BC743663E454FB4FEB768B06D3FAC8952B3E18364AF5611AE4F3F6E1895B570");
            consentContract = ConsentContract.load(
                    contractAddress,
                    web3j,
                    creds,
                    new StaticGasProvider(BigInteger.ZERO, BigInteger.valueOf(4_500_000))
            );

        } else {
            System.out.println("using ClientTransactionManager in ConsentContractTest ");
            consentContract = ConsentContract.load(
                    contractAddress,
                    web3j,
                    new ClientTransactionManager(web3j, blockchainConfig.getAccountAddress()),  // address in Wallet
                    new StaticGasProvider(BigInteger.ZERO, BigInteger.valueOf(4_500_000))
            );
        }
    }

    public void addConsent() throws Exception {
        //try {
        String userId = "0xcfe1b029bf174b4a713f540e19044536c7f36261";
        String consentId = "0xcfe1b029bf174b4a713f540e19044536c7f36261";
        String serviceId = "1";
        String serviceVersion = "2";
        String dataSinkId = "3";
        String groupInfos = "4";
        BigInteger startDtm = new BigInteger("11");
        BigInteger endDtm = new BigInteger("12");
        BigInteger registDtm = new BigInteger("13");


        TransactionReceipt tr = consentContract.addConsent(userId, consentId, serviceId, serviceVersion, dataSinkId, groupInfos, startDtm, endDtm, registDtm).send();
        System.out.println( tr.getTransactionHash() );
        //addConsent(String _userId, String _consentId, String _serviceId, String _serviceVersion, String _dataSinkId, String _groupInfos, BigInteger _startDtm, BigInteger _endDtm, BigInteger _registDtm) {

    }

    public void getContract() throws Exception {
        try {
            String userId = "0xb0347193bc56c7ed6793eee454e83d52bfc546e7";
            String consentId = "0xb0347193bc56c7ed6793eee454e83d52bfc546e7";
            Tuple8<String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger> send = consentContract.getConsent(userId, consentId).send();
            System.out.println(send.component1());
            System.out.println(send.component5());
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("################################################");
            throw new Exception("HEELO");
        }
    }

    public void setTest() throws Exception {
        TransactionReceipt tr = consentContract.setTest("XYZ", new BigInteger("100")).send();
        System.out.println( tr.getTransactionHash() );
    }

    public void getTest() throws Exception {
        Tuple2<String, BigInteger> send = consentContract.getTest().send();
        System.out.println( send.component1());
        System.out.println( send.component2());

    }

    public BigInteger getNonce() throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                blockchainConfig.getAccountAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        System.out.println( "NONCE:" + nonce);

        return nonce;
    }

    public void test() throws Exception {
        //consentService.getConsent("0xb249d8F059f17F4D9A94762C2685eC2ea52e5781", "0xb249d8F059f17F4D9A94762C2685eC2ea52e5781");

        setTestUsingFunction();

        if(true) return;

        Consent consent = new Consent("0x18446A8Ca1d8e1dd75aBdB4cC1FB6a9D0431cce4", "0x18446A8Ca1d8e1dd75aBdB4cC1FB6a9D0431cce4", "1", "2", "3", "4",
                BigInteger.valueOf(1000000), BigInteger.valueOf(2000000), BigInteger.valueOf(30000000));
        consent.setUserId("0x18446A8Ca1d8e1dd75aBdB4cC1FB6a9D0431cce4");
        consent.setConsentId("0x18446A8Ca1d8e1dd75aBdB4cC1FB6a9D0431cce4");
        consent.setServiceId("1");
        consent.setServiceVersion("2");
        consent.setDataSinkId("3");
        consent.setGroupInfos("GroupInfos4");
        consent.setStartDtm(BigInteger.valueOf(10000000));
        consent.setEndDtm(BigInteger.valueOf(20000000));
        consent.setRegistDtm(BigInteger.valueOf(30000000));

        List<Consent> myList = new ArrayList<Consent>();
        myList.add(consent);

        consentService.addConsents(myList);
    }

    public void setTestUsingFunction() throws Exception {
        // 테스트용 파라미터
        String _stringTest = "aaa";
        BigInteger _uintTest = new BigInteger("101");

        BigInteger nonce = getNonce();

        Function function = new Function(
                "setTest",
                Arrays.asList(new org.web3j.abi.datatypes.Utf8String(_stringTest),
                        new org.web3j.abi.datatypes.generated.Uint256(_uintTest)),
                Collections.emptyList());

        String encodedFunction = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createFunctionCallTransaction(
                blockchainConfig.getAccountAddress(),
                nonce,
                BigInteger.ZERO,
                BigInteger.valueOf(4_500_000),
                blockchainConfig.getContractAddress(),
                encodedFunction);


        org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =
                web3j.ethSendTransaction(transaction).send();//Async().get();


        if (transactionResponse.hasError()) {
            System.out.println("ERROR:" + transactionResponse.getError().getMessage());
        } else {
            System.out.println("SUCCESS");
        }

        System.out.println("TransactionHash:" + transactionResponse.getTransactionHash());

        while (true) {
            EthGetTransactionReceipt send =
                    web3j.ethGetTransactionReceipt(transactionResponse.getTransactionHash()).send();
            if (send.getResult() != null) {
                String status = send.getResult().getStatus();
                System.out.println("status : " + status); //트랜잭션 성공or실패 반환
                for (Log log : send.getResult().getLogs()) {
                    System.out.println(log.getData());
                }
                break;
            }
            Thread.sleep(1000);
        }

        System.out.println("END of ethSendTransaction");



        TransactionReceipt tr = waitForReceipt(web3j, transactionResponse.getTransactionHash());

        System.out.println( "tr.getStatus():" + tr.getStatus() );

        System.out.println( "tr.getBlockNumber():" + tr.getBlockNumber());

        tr.getLogs().forEach( (log) -> System.out.println( log.getData() ));
        System.out.println( "tr.toString():" + tr.toString() );
        System.out.println("END222");

    }

    private void testTransactionReceipt() throws Exception {
        String transactionHash = "0xe62c00d99b06a75d7cddb5bd78e2c460f2251409448b489b26328db32c729dab";

        TransactionReceipt tr = waitForReceipt(web3j, transactionHash);

        System.out.println( "tr.getStatus():" + tr.getStatus() );
        System.out.println( "tr.getBlockNumber():" + tr.getBlockNumber());

        tr.getLogs().forEach( (log) -> System.out.println( log.getData() ));
    }



    public void getTestUsingFunction() throws Exception {

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("getTest",
                Collections.emptyList(),
                Arrays.asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        //org.web3j.abi.datatypes.generated.
        // 2. ethereum을 function 변수로 통해 호출

        String encodedFunc = FunctionEncoder.encode(function);
        System.out.println("encodedFunc:" + encodedFunc);

        //return 1;
        ethCall(function);
    }

    public Object ethCall(Function function) throws Exception {

        Transaction transaction = Transaction.createEthCallTransaction(blockchainConfig.getAccountAddress(), blockchainConfig.getContractAddress(), FunctionEncoder.encode(function));

        //3. ethereum 호출후 결과 가져오기
        EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();


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

           return decode.get(0).getValue();

       }


    }

    /**
     * Waits for the receipt for the transaction specified by the provided tx hash.
     * Makes 40 attempts (waiting 1 sec. inbetween attempts) to get the receipt object.
     * In the happy case the tx receipt object is returned.
     * Otherwise, a runtime exception is thrown.
     */
    public static TransactionReceipt waitForReceipt(Web3j web3j, String transactionHash)
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
    public static Optional<TransactionReceipt> getReceipt(Web3j web3j, String transactionHash)
            throws Exception
    {
        EthGetTransactionReceipt receipt = web3j
                .ethGetTransactionReceipt(transactionHash)
                .sendAsync()
                .get();

        return receipt.getTransactionReceipt();
    }

}
