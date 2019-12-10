pragma solidity 0.5.4;

contract ConsentContract {
    address owner;
    uint counter;

    struct Consent {
        address consentId;
        address userId;
        string serviceId;
        string serviceVersion;
        string dataSinkId;
        string groupInfos;  //ConsentGroup[] consentGroups; Setting array is not easy.. so change it to String
        uint startDtm;
        uint endDtm;
        uint registDtm;
        uint withdrawalDtm;

    }

    mapping (address => Consent[]) userIdContents;   // 1 to many mapping
    mapping (address => uint) consentIdOrder;  // 1 to 1 mapping
    //mapping (string => address[]) serviceIdConsentIds;   // 1 to many mapping // Disabled for many consentIds are appended to serviceId..

    constructor() public {
        owner = msg.sender;
    }

    function addConsent(address _userId, address _consentId,
                        string memory _serviceId, string memory _serviceVersion, string memory _dataSinkId,
                        string memory _groupInfos,
                        uint _startDtm, uint _endDtm, uint _registDtm ) public {

        if ( consentIdOrder[_consentId] != 0 ) revert("ERROR MSG");

        Consent memory consent; //= userContents[_consentId];

        consent.userId = _userId;
        consent.serviceId = _serviceId;
        consent.serviceVersion = _serviceVersion;
        consent.dataSinkId = _dataSinkId;
        consent.groupInfos = _groupInfos;
        consent.startDtm = _startDtm;
        consent.endDtm = _endDtm;
        consent.registDtm = _registDtm;

        uint order = userIdContents[_userId].push(consent);

        consentIdOrder[_consentId] = order;

    }

    function withdrawConsent(address _userId, address _consentId, uint _withdrawalDtm) public {
        uint order = consentIdOrder[_consentId];
        if( order == 0 ) revert();

        if( userIdContents[_userId][order-1].userId == address(0)) revert();

        userIdContents[_userId][order-1].withdrawalDtm = _withdrawalDtm;
    }

    function getConsent(address _userId, address _consentId) public view
                        returns(string memory, string memory, string memory,
                                string memory,
                                uint, uint, uint, uint) {

        uint order = consentIdOrder[_consentId];
        if( order == 0 ) revert();

        Consent memory consent = userIdContents[_userId][order-1];

        return (consent.serviceId, consent.serviceVersion, consent.dataSinkId,
                consent.groupInfos,
                consent.startDtm, consent.endDtm, consent.registDtm, consent.withdrawalDtm );
    }

    function updateConsent(address _userId, address _consentId,
                            //string memory _serviceId, string memory _serviceVersion, string memory _dataSinkId,
                            string memory _groupInfos,
                            uint _startDtm, uint _endDtm, uint _registDtm ) public {

        uint order = consentIdOrder[_consentId];
        if( order == 0 ) revert();

        if( userIdContents[_userId][order-1].userId == address(0)) revert();

        // below 3 are not changable..  => Need a decision for check the serviceId, serviceVersion, dataSinkId EQUALITY
        //userIdContents[_userId][order-1].serviceId = _serviceId;
        //userIdContents[_userId][order-1].serviceVersion = _serviceVersion;
        //userIdContents[_userId][order-1].dataSinkId = _dataSinkId;
        userIdContents[_userId][order-1].groupInfos = _groupInfos;
        userIdContents[_userId][order-1].startDtm = _startDtm;
        userIdContents[_userId][order-1].endDtm = _endDtm;
        userIdContents[_userId][order-1].registDtm = _registDtm;

    }

    string stringTest = "Before";
    uint uintTest = 999;

    function getTest() public view returns(string memory, uint) {
        return (stringTest, uintTest);
    }

    function setTest(string memory _stringTest, uint _uintTest) public {
        stringTest = _stringTest;
        uintTest = _uintTest;
    }
}
