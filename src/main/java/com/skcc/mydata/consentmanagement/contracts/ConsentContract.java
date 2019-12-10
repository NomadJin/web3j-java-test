package com.skcc.mydata.consentmanagement.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class ConsentContract extends Contract {
    private static final String BINARY = "0x60806040526040805190810160405280600681526020017f4265666f726500000000000000000000000000000000000000000000000000008152506004908051906020019062000051929190620000ac565b506103e76005553480156200006557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506200015b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000ef57805160ff191683800117855562000120565b8280016001018555821562000120579182015b828111156200011f57825182559160200191906001019062000102565b5b5090506200012f919062000133565b5090565b6200015891905b80821115620001545760008160009055506001016200013a565b5090565b90565b6116f1806200016b6000396000f3fe608060405234801561001057600080fd5b506004361061007f576000357c010000000000000000000000000000000000000000000000000000000090048063333445181461008457806337fcc3e0146101495780634061efeb146102625780636f1ceb43146105415780639051aad2146105af578063a8cd0a80146107ec575b600080fd5b6101476004803603604081101561009a57600080fd5b81019080803590602001906401000000008111156100b757600080fd5b8201836020820111156100c957600080fd5b803590602001918460018302840111640100000000831117156100eb57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929080359060200190929190505050610876565b005b610260600480360360c081101561015f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001906401000000008111156101bc57600080fd5b8201836020820111156101ce57600080fd5b803590602001918460018302840111640100000000831117156101f057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290803590602001909291908035906020019092919080359060200190929190505050610898565b005b61053f600480360361012081101561027957600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001906401000000008111156102d657600080fd5b8201836020820111156102e857600080fd5b8035906020019184600183028401116401000000008311171561030a57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561036d57600080fd5b82018360208201111561037f57600080fd5b803590602001918460018302840111640100000000831117156103a157600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561040457600080fd5b82018360208201111561041657600080fd5b8035906020019184600183028401116401000000008311171561043857600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561049b57600080fd5b8201836020820111156104ad57600080fd5b803590602001918460018302840111640100000000831117156104cf57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290803590602001909291908035906020019092919080359060200190929190505050610b51565b005b6105ad6004803603606081101561055757600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610e79565b005b610611600480360360408110156105c557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610ff0565b604051808060200180602001806020018060200189815260200188815260200187815260200186815260200185810385528d818151815260200191508051906020019080838360005b8381101561067557808201518184015260208101905061065a565b50505050905090810190601f1680156106a25780820380516001836020036101000a031916815260200191505b5085810384528c818151815260200191508051906020019080838360005b838110156106db5780820151818401526020810190506106c0565b50505050905090810190601f1680156107085780820380516001836020036101000a031916815260200191505b5085810383528b818151815260200191508051906020019080838360005b83811015610741578082015181840152602081019050610726565b50505050905090810190601f16801561076e5780820380516001836020036101000a031916815260200191505b5085810382528a818151815260200191508051906020019080838360005b838110156107a757808201518184015260208101905061078c565b50505050905090810190601f1680156107d45780820380516001836020036101000a031916815260200191505b509c5050505050505050505050505060405180910390f35b6107f4611473565b6040518080602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561083a57808201518184015260208101905061081f565b50505050905090810190601f1680156108675780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b816004908051906020019061088c929190611520565b50806005819055505050565b6000600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905060008114156108ea57600080fd5b600073ffffffffffffffffffffffffffffffffffffffff16600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001830381548110151561095157fe5b90600052602060002090600a020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156109a457600080fd5b84600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600183038154811015156109f457fe5b90600052602060002090600a02016005019080519060200190610a18929190611520565b5083600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060018303815481101515610a6957fe5b90600052602060002090600a02016006018190555082600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060018303815481101515610ace57fe5b90600052602060002090600a02016007018190555081600260008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060018303815481101515610b3357fe5b90600052602060002090600a02016008018190555050505050505050565b6000600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054141515610c08576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260098152602001807f4552524f52204d5347000000000000000000000000000000000000000000000081525060200191505060405180910390fd5b610c106115a0565b89816020019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff1681525050878160400181905250868160600181905250858160800181905250848160a00181905250838160c0018181525050828160e001818152505081816101000181815250506000600260008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020829080600181540180825580915050906001820390600052602060002090600a02016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190610da2929190611620565b506060820151816003019080519060200190610dbf929190611620565b506080820151816004019080519060200190610ddc929190611620565b5060a0820151816005019080519060200190610df9929190611620565b5060c0820151816006015560e08201518160070155610100820151816008015561012082015181600901555050905080600360008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050505050505050505050565b6000600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490506000811415610ecb57600080fd5b600073ffffffffffffffffffffffffffffffffffffffff16600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060018303815481101515610f3257fe5b90600052602060002090600a020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610f8557600080fd5b81600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060018303815481101515610fd557fe5b90600052602060002090600a02016009018190555050505050565b6060806060806000806000806000600360008b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050600081141561104e57600080fd5b6110566115a0565b600260008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600183038154811015156110a557fe5b90600052602060002090600a020161014060405190810160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112055780601f106111da57610100808354040283529160200191611205565b820191906000526020600020905b8154815290600101906020018083116111e857829003601f168201915b50505050508152602001600382018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156112a75780601f1061127c576101008083540402835291602001916112a7565b820191906000526020600020905b81548152906001019060200180831161128a57829003601f168201915b50505050508152602001600482018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113495780601f1061131e57610100808354040283529160200191611349565b820191906000526020600020905b81548152906001019060200180831161132c57829003601f168201915b50505050508152602001600582018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113eb5780601f106113c0576101008083540402835291602001916113eb565b820191906000526020600020905b8154815290600101906020018083116113ce57829003601f168201915b5050505050815260200160068201548152602001600782015481526020016008820154815260200160098201548152505090508060400151816060015182608001518360a001518460c001518560e001518661010001518761012001518797508696508595508494509950995099509950995099509950995050509295985092959890939650565b606060006004600554818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156115115780601f106114e657610100808354040283529160200191611511565b820191906000526020600020905b8154815290600101906020018083116114f457829003601f168201915b50505050509150915091509091565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061156157805160ff191683800117855561158f565b8280016001018555821561158f579182015b8281111561158e578251825591602001919060010190611573565b5b50905061159c91906116a0565b5090565b61014060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff16815260200160608152602001606081526020016060815260200160608152602001600081526020016000815260200160008152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061166157805160ff191683800117855561168f565b8280016001018555821561168f579182015b8281111561168e578251825591602001919060010190611673565b5b50905061169c91906116a0565b5090565b6116c291905b808211156116be5760008160009055506001016116a6565b5090565b9056fea165627a7a72305820d32b5aad63e1ead866b4c753c0f6ecf4b6d580ee9d84912ed7eb52ddfe58bb960029";

    public static final String FUNC_ADDCONSENT = "addConsent";

    public static final String FUNC_WITHDRAWCONSENT = "withdrawConsent";

    public static final String FUNC_GETCONSENT = "getConsent";

    public static final String FUNC_UPDATECONSENT = "updateConsent";

    public static final String FUNC_GETTEST = "getTest";

    public static final String FUNC_SETTEST = "setTest";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x2479827b2c3CD3a01131FB71771ac4cac89c0538");
        _addresses.put("2071083193", "0x6F4FbD241d0282f1B45722C7aE6DeCB94e2eA333");
    }

    @Deprecated
    protected ConsentContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ConsentContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ConsentContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ConsentContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addConsent(String _userId, String _consentId, String _serviceId, String _serviceVersion, String _dataSinkId, String _groupInfos, BigInteger _startDtm, BigInteger _endDtm, BigInteger _registDtm) {
        final Function function = new Function(
                FUNC_ADDCONSENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId), 
                new org.web3j.abi.datatypes.Address(_consentId), 
                new org.web3j.abi.datatypes.Utf8String(_serviceId), 
                new org.web3j.abi.datatypes.Utf8String(_serviceVersion), 
                new org.web3j.abi.datatypes.Utf8String(_dataSinkId), 
                new org.web3j.abi.datatypes.Utf8String(_groupInfos), 
                new org.web3j.abi.datatypes.generated.Uint256(_startDtm), 
                new org.web3j.abi.datatypes.generated.Uint256(_endDtm), 
                new org.web3j.abi.datatypes.generated.Uint256(_registDtm)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawConsent(String _userId, String _consentId, BigInteger _withdrawalDtm) {
        final Function function = new Function(
                FUNC_WITHDRAWCONSENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId), 
                new org.web3j.abi.datatypes.Address(_consentId), 
                new org.web3j.abi.datatypes.generated.Uint256(_withdrawalDtm)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple8<String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger>> getConsent(String _userId, String _consentId) {
        final Function function = new Function(FUNC_GETCONSENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId), 
                new org.web3j.abi.datatypes.Address(_consentId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple8<String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple8<String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple8<String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<String, String, String, String, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateConsent(String _userId, String _consentId, String _groupInfos, BigInteger _startDtm, BigInteger _endDtm, BigInteger _registDtm) {
        final Function function = new Function(
                FUNC_UPDATECONSENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userId), 
                new org.web3j.abi.datatypes.Address(_consentId), 
                new org.web3j.abi.datatypes.Utf8String(_groupInfos), 
                new org.web3j.abi.datatypes.generated.Uint256(_startDtm), 
                new org.web3j.abi.datatypes.generated.Uint256(_endDtm), 
                new org.web3j.abi.datatypes.generated.Uint256(_registDtm)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> getTest() {
        final Function function = new Function(FUNC_GETTEST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> setTest(String _stringTest, BigInteger _uintTest) {
        final Function function = new Function(
                FUNC_SETTEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_stringTest), 
                new org.web3j.abi.datatypes.generated.Uint256(_uintTest)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ConsentContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ConsentContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ConsentContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ConsentContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ConsentContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ConsentContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ConsentContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ConsentContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ConsentContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ConsentContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<ConsentContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ConsentContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ConsentContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ConsentContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ConsentContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ConsentContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
