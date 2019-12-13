package com.skcc.mydata.consentmanagement.service;

import com.skcc.mydata.consentmanagement.config.BlockchainConfig;
import com.skcc.mydata.consentmanagement.model.Consent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConsentServiceImpl implements ConsentService {
    private static String STATUS_ERROR = "0x0";

    @Autowired
    Web3j web3j;

    @Autowired
    BlockchainConfig blockchainConfig;

    @Override
    public void addConsents(List<Consent> consents) {
        consents.forEach(consent -> addConsent(consent));
    }




    private Function makeAddConsentFunction(Consent consent) {
        return new Function(
                "addConsent",
                Arrays.asList(
                        new org.web3j.abi.datatypes.Address(consent.getConsentId()),
                        new org.web3j.abi.datatypes.Address(consent.getUserId()),
                        new org.web3j.abi.datatypes.Utf8String(consent.getServiceId()),
                        new org.web3j.abi.datatypes.Utf8String(consent.getServiceVersion()),
                        new org.web3j.abi.datatypes.Utf8String(consent.getDataSinkId()),
                        new org.web3j.abi.datatypes.Utf8String(consent.getGroupInfos()),
                        new org.web3j.abi.datatypes.generated.Uint256(consent.getStartDtm()),
                        new org.web3j.abi.datatypes.generated.Uint256(consent.getEndDtm()),
                        new org.web3j.abi.datatypes.generated.Uint256(consent.getRegistDtm())),
                Collections.emptyList());
    }

    private void addConsent(Consent consent) {

        try {

            System.out.println("[Consent Info]" + consent.toString());

            Function function = makeAddConsentFunction(consent);



            TransactionReceipt receipt = waitForReceipt(web3j, ethSendTransaction(function));

            if(STATUS_ERROR.equals(receipt.getStatus())) {
                Transaction transaction = Transaction.createEthCallTransaction(blockchainConfig.getAccountAddress(),
                        blockchainConfig.getContractAddress(),
                        FunctionEncoder.encode(function));

                System.out.println("receipt.getBlockNumber().toString()" + receipt.getBlockNumber().toString());
                EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();// valueOf( receipt.getBlockNumber().toString() )).send();


                if( ethCall.reverts() ) {
                    System.out.println( ethCall.getRevertReason() );
                    // throws Exception later
                }
                //else { }
            }

        } catch (Exception TBD) {
            TBD.printStackTrace();
        }
    }

    private String ethSendTransaction(Function function) throws Exception {
        BigInteger nonce = getNonce();

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

        return transactionResponse.getTransactionHash();
    }

    private Function makeUpdateConsentFunction(Consent consent) {
        return new Function(
                "updateConsent",
                Arrays.asList(
                        new org.web3j.abi.datatypes.Address(consent.getConsentId()),
                        new org.web3j.abi.datatypes.Utf8String(consent.getGroupInfos()),
                        new org.web3j.abi.datatypes.generated.Uint256(consent.getStartDtm()),
                        new org.web3j.abi.datatypes.generated.Uint256(consent.getEndDtm()),
                        new org.web3j.abi.datatypes.generated.Uint256(consent.getRegistDtm())
                ),
                Collections.emptyList());
    }

    @Override
    public void updateConsents(List<Consent> consents) {
            consents.forEach(consent -> updateConsent(consent));
    }

    private void updateConsent(Consent consent) {
        try {

            //System.out.println("consentId=" + consentId);
            //System.out.println("withdrawalDtm=" + withdrawalDtm);

            Function function = makeUpdateConsentFunction(consent);

            TransactionReceipt receipt = waitForReceipt(web3j, ethSendTransaction(function));

            if(STATUS_ERROR.equals(receipt.getStatus())) {
                Transaction transaction = Transaction.createEthCallTransaction(blockchainConfig.getAccountAddress(),
                        blockchainConfig.getContractAddress(),
                        FunctionEncoder.encode(function));

                EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

                if( ethCall.reverts() ) {
                    System.out.println( "[RECEIPT ERROR]" + ethCall.getRevertReason() );
                    // throws Exception later
                }
                //else { }
            }
        } catch (Exception TBD) {
            TBD.printStackTrace();
        }
    }

    private Function makeWithdrawConsentFunction(String consentId, BigInteger withdrawalDtm) {
        return new Function(
                "withdrawConsent",
                Arrays.asList(
                        new org.web3j.abi.datatypes.Address(consentId),
                        new org.web3j.abi.datatypes.generated.Uint256(withdrawalDtm)),
                Collections.emptyList());
    }

    @Override
    public void withdrawConsent(String consentId, BigInteger withdrawalDtm) {
        try {

            System.out.println("consentId=" + consentId);
            System.out.println("withdrawalDtm=" + withdrawalDtm);

            Function function = makeWithdrawConsentFunction(consentId, withdrawalDtm);

            TransactionReceipt receipt = waitForReceipt(web3j, ethSendTransaction(function));

            if(STATUS_ERROR.equals(receipt.getStatus())) {
                Transaction transaction = Transaction.createEthCallTransaction(blockchainConfig.getAccountAddress(),
                        blockchainConfig.getContractAddress(),
                        FunctionEncoder.encode(function));

                EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

                if( ethCall.reverts() ) {
                    System.out.println( ethCall.getRevertReason() );
                    // throws Exception later
                }
                //else { }
            }

        } catch (Exception TBD) {
            TBD.printStackTrace();
        }
    }


    private Function makeGetConsentFunction(String consentId) {
        return new Function("getConsent",
                Arrays.asList(
                        new org.web3j.abi.datatypes.Address(consentId)),
                Arrays.asList(
                        new TypeReference<Address>() {
                        },   // consentId
                        new TypeReference<Address>() {
                        },   // userId
                        new TypeReference<Utf8String>() {
                        },  // serviceId
                        new TypeReference<Utf8String>() {
                        },  // serviceVersion
                        new TypeReference<Utf8String>() {
                        },  // dataSinkId
                        new TypeReference<Utf8String>() {
                        },  // groupInfos
                        new TypeReference<Uint256>() {
                        }, // startDtm
                        new TypeReference<Uint256>() {
                        }, // endDtm
                        new TypeReference<Uint256>() {
                        }, // registDtm
                        new TypeReference<Uint256>() {
                        }) // withdrawlDtm
        );
    }

    @Override
    public Consent getConsent(String consentId) {
        Consent consent = new Consent();
        try {
            // 1. make a function
            Function function = makeGetConsentFunction(consentId);

            // 2. ethereum을 function 변수로 통해 호출
            Transaction transaction = Transaction.createEthCallTransaction(blockchainConfig.getAccountAddress(),
                    blockchainConfig.getContractAddress(),
                    FunctionEncoder.encode(function));

            //3. ethereum 호출후 결과 가져오기
            EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();

            //System.out.println("ethCall.getValue():" + ethCall.getValue());

            if (ethCall.reverts()) {
                // 4.1. Throw revert exception.
                // right now show the revert message
                System.out.println(ethCall.getRevertReason());

                return consent;
            } else {

                //4.2. 결과값 decode
                List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                        function.getOutputParameters());

                //System.out.println("ethCall.getResult() = " + ethCall.getResult());
                //FunctionReturnDecoder.decode(  )
                consent.setConsentId((String)decode.get(0).getValue());
                consent.setUserId((String)decode.get(1).getValue());

                consent.setServiceId((String) decode.get(2).getValue());
                consent.setServiceVersion((String) decode.get(3).getValue());
                consent.setDataSinkId((String) decode.get(4).getValue());
                consent.setGroupInfos((String) decode.get(5).getValue());

                consent.setStartDtm((BigInteger) decode.get(6).getValue());
                consent.setEndDtm((BigInteger) decode.get(7).getValue());
                consent.setRegistDtm((BigInteger) decode.get(8).getValue());
                consent.setWithdrawalDtm((BigInteger) decode.get(9).getValue());

                System.out.println("consent:" + consent);
                return consent;

            }
        } catch (Exception TBD) {
            TBD.printStackTrace();
        }

        return consent;

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
                blockchainConfig.getAccountAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        return nonce;
    }


}
