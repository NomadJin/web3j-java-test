pragma solidity 0.5.4;

contract ConsentContract {
    address owner;

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

    mapping (address => Consent) consents;   // 1(consentId) to many mapping
    mapping (address => address[]) userIdConsentId;  // 1(userId) to many(consentIds) mapping
    //mapping (string => address[]) serviceIdConsentIds;   // 1 to many mapping // Disabled for many consentIds are appended to serviceId..

    constructor() public {
        owner = msg.sender;
    }

    function addConsent(address _consentId, address _userId,
                        string memory _serviceId, string memory _serviceVersion, string memory _dataSinkId,
                        string memory _groupInfos,
                        uint _startDtm, uint _endDtm, uint _registDtm ) public {

        if ( consents[_consentId].consentId !=  address(0) ) revert("Same consentId is existed");

        Consent memory consent; //= userContents[_consentId];

        consent.consentId = _consentId;
        consent.userId = _userId;
        consent.serviceId = _serviceId;
        consent.serviceVersion = _serviceVersion;
        consent.dataSinkId = _dataSinkId;
        consent.groupInfos = _groupInfos;
        consent.startDtm = _startDtm;
        consent.endDtm = _endDtm;
        consent.registDtm = _registDtm;

        consents[_consentId] = consent;

        userIdConsentId[_userId].push(_consentId);



    }

    function toString(address x) private returns (string memory) {
        bytes memory b = new bytes(20);
        for (uint i = 0; i < 20; i++)
            b[i] = byte(uint8(uint(x) / (2**(8*(19 - i)))));
        return string(b);
    }

    function withdrawConsent(address _consentId, uint _withdrawalDtm) public {
        if( consents[_consentId].consentId == address(0) ) revert("No consent"); // + toString(_consentId));
        if( consents[_consentId].withdrawalDtm != 0 ) revert("Withdrawed consent");

        consents[_consentId].withdrawalDtm = _withdrawalDtm;
    }

    function getConsent(address _consentId) public view
                        returns(address, address,
                                string memory, string memory, string memory,
                                string memory,
                                uint, uint, uint, uint) {

        Consent memory consent = consents[_consentId];

        return (consent.consentId, consent.userId,
                consent.serviceId, consent.serviceVersion, consent.dataSinkId,
                consent.groupInfos,
                consent.startDtm, consent.endDtm, consent.registDtm, consent.withdrawalDtm );
    }


    function getUserConsentIds(address _userId) public view returns(address[] memory) {
        return userIdConsentId[_userId];
    }


    function updateConsent(address _consentId,
                            //string memory _serviceId, string memory _serviceVersion, string memory _dataSinkId,
                            string memory _groupInfos,
                            uint _startDtm, uint _endDtm, uint _registDtm ) public {

       if( consents[_consentId].consentId == address(0) ) revert("No consent"); // for " + toString(_consentId));
       if( consents[_consentId].withdrawalDtm != 0 ) revert("Withdrawed consent");

        consents[_consentId].groupInfos = _groupInfos;
        consents[_consentId].startDtm = _startDtm;
        consents[_consentId].endDtm = _endDtm;
        consents[_consentId].registDtm = _registDtm;

    }

    string stringTest = "Before";
    uint uintTest = 999;

    function getTest() public view returns(string memory, uint) {
        if( uintTest == 9 ) revert("XXXXX");

        return (stringTest, uintTest);
    }

    function setTest(string memory _stringTest, uint _uintTest) public {
        require(_uintTest == 101, "ERROR_REQUIRE");
        if( _uintTest == 100 ) revert("ERROR_REVERT");

        stringTest = _stringTest;
        uintTest = _uintTest;
    }
}
