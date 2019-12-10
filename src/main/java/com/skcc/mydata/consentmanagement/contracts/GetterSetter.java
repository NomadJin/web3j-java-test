package com.skcc.mydata.consentmanagement.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class GetterSetter extends Contract {
    private static final String BINARY = "600060015560c0604052600560808190527f68656c6c6f00000000000000000000000000000000000000000000000000000060a09081526100439160029190610068565b5034801561005057600080fd5b5060008054600160a060020a03191633179055610103565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100a957805160ff19168380011785556100d6565b828001600101855582156100d6579182015b828111156100d65782518255916020019190600101906100bb565b506100e29291506100e6565b5090565b61010091905b808211156100e257600081556001016100ec565b90565b6103ca806101126000396000f3fe608060405234801561001057600080fd5b506004361061007e577c010000000000000000000000000000000000000000000000000000000060003504633fb5c1cb81146100835780637fcaf666146100a2578063893d20e81461014857806389ea642f146101795780638da5cb5b146101f6578063f2c9ecd8146101fe575b600080fd5b6100a06004803603602081101561009957600080fd5b5035610218565b005b6100a0600480360360208110156100b857600080fd5b8101906020810181356401000000008111156100d357600080fd5b8201836020820111156100e557600080fd5b8035906020019184600183028401116401000000008311171561010757600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061021d945050505050565b610150610234565b6040805173ffffffffffffffffffffffffffffffffffffffff9092168252519081900360200190f35b610181610251565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101bb5781810151838201526020016101a3565b50505050905090810190601f1680156101e85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101506102e4565b610206610300565b60408051918252519081900360200190f35b600155565b8051610230906002906020840190610306565b5050565b60005473ffffffffffffffffffffffffffffffffffffffff165b90565b60028054604080516020601f60001961010060018716150201909416859004938401819004810282018101909252828152606093909290918301828280156102da5780601f106102af576101008083540402835291602001916102da565b820191906000526020600020905b8154815290600101906020018083116102bd57829003601f168201915b5050505050905090565b60005473ffffffffffffffffffffffffffffffffffffffff1681565b60015490565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061034757805160ff1916838001178555610374565b82800160010185558215610374579182015b82811115610374578251825591602001919060010190610359565b50610380929150610384565b5090565b61024e91905b80821115610380576000815560010161038a56fea165627a7a72305820e84e6faf582faa071e59c41cc57bc4f8d6fc62688e4ed6a42e472381750dedb90029";

    public static final String FUNC_SETNUMBER = "setNumber";

    public static final String FUNC_SETSTRING = "setString";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETSTRING = "getString";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETNUMBER = "getNumber";

    @Deprecated
    protected GetterSetter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected GetterSetter(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected GetterSetter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected GetterSetter(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> setNumber(BigInteger num) {
        final Function function = new Function(
                FUNC_SETNUMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(num)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setString(String str) {
        final Function function = new Function(
                FUNC_SETSTRING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(str)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOwner() {
        final Function function = new Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getString() {
        final Function function = new Function(FUNC_GETSTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getNumber() {
        final Function function = new Function(FUNC_GETNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static GetterSetter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new GetterSetter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static GetterSetter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new GetterSetter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static GetterSetter load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new GetterSetter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static GetterSetter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new GetterSetter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<GetterSetter> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GetterSetter.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<GetterSetter> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(GetterSetter.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<GetterSetter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GetterSetter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<GetterSetter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(GetterSetter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
