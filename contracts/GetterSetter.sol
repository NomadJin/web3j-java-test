pragma solidity >=0.4.21 <0.6.0;

 contract GetterSetter {
     address payable public owner;
     uint256 private _num = 0;
     string _str = "hello";

     constructor() public {
         owner = msg.sender;
     }

     function getNumber() public view returns(uint256) {
         return _num;
     }

     function setNumber(uint256 num) public {
         _num = num;
     }

     function setString(string memory str) public {
         _str = str;
     }

     function getString() public view returns(string memory) {
         return _str;
     }

     function getOwner() public view returns(address) {
         return owner;
     }
 }