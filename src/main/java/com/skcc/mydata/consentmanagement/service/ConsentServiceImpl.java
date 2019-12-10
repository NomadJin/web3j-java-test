package com.skcc.mydata.consentmanagement.service;

import com.skcc.mydata.consentmanagement.config.KaleidoConfig;
import com.skcc.mydata.consentmanagement.model.Consent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
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

    @Autowired
    Web3j web3j;

    @Autowired
    KaleidoConfig kaleidoConfig;

    @Override
    public void addConsents(List<Consent> consents) {
        consents.forEach(consent -> addConsent(consent));
    }

    public BigInteger getNonce() throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                kaleidoConfig.getAccountAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        //System.out.println( "NONCE:" + nonce);

        return nonce;
    }

    private void addConsent(Consent consent) {

        try {

            System.out.println(consent.toString());


            Function function = new Function(
                    "addConsent",
                    Arrays.asList(
                            new org.web3j.abi.datatypes.Address(consent.getUserId()),
                            new org.web3j.abi.datatypes.Address(consent.getConsentId()),
                            new org.web3j.abi.datatypes.Utf8String(consent.getServiceId()),
                            new org.web3j.abi.datatypes.Utf8String(consent.getServiceVersion()),
                            new org.web3j.abi.datatypes.Utf8String(consent.getDataSinkId()),
                            new org.web3j.abi.datatypes.Utf8String(consent.getGroupInfos()),
                            new org.web3j.abi.datatypes.generated.Uint256(consent.getStartDtm()),
                            new org.web3j.abi.datatypes.generated.Uint256(consent.getEndDtm()),
                            new org.web3j.abi.datatypes.generated.Uint256(consent.getRegistDtm())),
                    Collections.emptyList());

            Transaction transaction = Transaction.createFunctionCallTransaction(
                    kaleidoConfig.getAccountAddress(),
                    getNonce(),
                    BigInteger.ZERO,
                    BigInteger.valueOf(4_500_000),
                    kaleidoConfig.getContractAddress(),
                    FunctionEncoder.encode(function));


            //org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =
            //        web3j.ethSendTransaction(transaction).send();

            org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =
                    web3j.ethSendTransaction(transaction).send();//Async().get();

            // just for printing...
            String transactionHash = transactionResponse.getTransactionHash();

            System.out.println("TransactionHash:" + transactionHash);
            System.out.println("transactionResponse.getResult():" + transactionResponse.getResult());
            System.out.println(transactionResponse.getError());

            if (transactionResponse.hasError()) {
                System.out.println(transactionResponse.getError().getMessage() );
            }




            //transactionResponse.get
        } catch (Exception TBD) {
            TBD.printStackTrace();
        }
    }

    @Override
    public void updateConsents() {

    }

    @Override
    public void withdrawConsents() {

    }

    @Override
    public Consent getConsent(String userId, String consentId) {
        try {

            Function function = new Function("getConsent",
                    Arrays.asList(
                            new org.web3j.abi.datatypes.Address(userId),
                            new org.web3j.abi.datatypes.Address(consentId)),
                    Arrays.asList(
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

            // 2. ethereum을 function 변수로 통해 호출
            Transaction transaction = Transaction.createEthCallTransaction(kaleidoConfig.getAccountAddress(),
                    kaleidoConfig.getContractAddress(),
                    FunctionEncoder.encode(function));

            //3. ethereum 호출후 결과 가져오기
            EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();

            System.out.println("ethCall.getValue():" + ethCall.getValue());

            if (ethCall.reverts()) {
                // 4.1. Throw revert exception.
                // right now show the revert message
                System.out.println(ethCall.getRevertReason());

                return null;
            } else {

                //4.2. 결과값 decode
                List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                        function.getOutputParameters());

                System.out.println("ethCall.getResult() = " + ethCall.getResult());
                //FunctionReturnDecoder.decode(  )
                Consent consent = new Consent();
                consent.setUserId(userId);
                consent.setConsentId(consentId);
                consent.setServiceId((String) decode.get(0).getValue());
                consent.setServiceVersion((String) decode.get(1).getValue());
                consent.setDataSinkId((String) decode.get(2).getValue());
                consent.setGroupInfos((String) decode.get(3).getValue());

                consent.setStartDtm((BigInteger) decode.get(4).getValue());
                consent.setEndDtm((BigInteger) decode.get(5).getValue());
                consent.setRegistDtm((BigInteger) decode.get(6).getValue());
                consent.setWithdrawalDtm((BigInteger) decode.get(7).getValue());

//                System.out.println("getValue = " + decode.get(0).getValue());
//                System.out.println("getType = " + decode.get(0).getTypeAsString());
//                System.out.println("getValue = " + decode.get(1).getValue());
//                System.out.println("getType = " + decode.get(1).getTypeAsString());
                System.out.println("consent:" + consent);
                return null;

            }
        } catch (Exception TBD) {
            TBD.printStackTrace();
        }

        return null;
    }



}
