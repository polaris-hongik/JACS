// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * @dev Interface of the ERC20 standard as defined in the EIP.
 */
interface IERC20 {
    /**
     * @dev Returns the amount of tokens in existence.
     */
    function totalSupply() external view returns (uint256);

    /**
     * @dev Returns the amount of tokens owned by `account`.
     */
    function balanceOf(address account) external view returns (uint256);
    

    /**
     * @dev Moves `amount` tokens from the caller's account to `recipient`.
     *
     * Returns a boolean value indicating whether the operation succeeded.
     *
     * Emits a {Transfer} event.
     */
    function transfer(address recipient, uint256 amount) external returns (bool);

    /**
     * @dev Returns the remaining number of tokens that `spender` will be
     * allowed to spend on behalf of `owner` through {transferFrom}. This is
     * zero by default.
     *
     * This value changes when {approve} or {transferFrom} are called.
     */
    function allowance(address owner, address spender) external view returns (uint256);

    /**
     * @dev Sets `amount` as the allowance of `spender` over the caller's tokens.
     *
     * Returns a boolean value indicating whether the operation succeeded.
     *
     * IMPORTANT: Beware that changing an allowance with this method brings the risk
     * that someone may use both the old and the new allowance by unfortunate
     * transaction ordering. One possible solution to mitigate this race
     * condition is to first reduce the spender's allowance to 0 and set the
     * desired value afterwards:
     * https://github.com/ethereum/EIPs/issues/20#issuecomment-263524729
     *
     * Emits an {Approval} event.
     */
    function approve(address spender, uint256 amount) external returns (bool);

    /**
     * @dev Moves `amount` tokens from `sender` to `recipient` using the
     * allowance mechanism. `amount` is then deducted from the caller's
     * allowance.
     *
     * Returns a boolean value indicating whether the operation succeeded.
     *
     * Emits a {Transfer} event.
     */
    function transferFrom(
        address sender,
        address recipient,
        uint256 amount
    ) external returns (bool);

    /**
     * @dev Emitted when `value` tokens are moved from one account (`from`) to
     * another (`to`).
     *
     * Note that `value` may be zero.
     */
    event Transfer(address indexed from, address indexed to, uint256 value);

    /**
     * @dev Emitted when the allowance of a `spender` for an `owner` is set by
     * a call to {approve}. `value` is the new allowance.
     */
    event Approval(address indexed owner, address indexed spender, uint256 value);
}

/*
 * @dev Provides information about the current execution context, including the
 * sender of the transaction and its data. While these are generally available
 * via msg.sender and msg.data, they should not be accessed in such a direct
 * manner, since when dealing with meta-transactions the account sending and
 * paying for execution may not be the actual sender (as far as an application
 * is concerned).
 *
 * This contract is only required for intermediate, library-like contracts.
 */
abstract contract Context {
    function _msgSender() internal view virtual returns (address) {
        return msg.sender;
    }

    function _msgData() internal view virtual returns (bytes calldata) {
        return msg.data;
    }
}

// File: @openzeppelin/contracts/access/Ownable.sol

abstract contract Ownable is Context {
    address private _owner;

    event OwnershipTransferred(address indexed previousOwner, address indexed newOwner);

    /**
     * @dev Initializes the contract setting the deployer as the initial owner.
     */
    constructor() {
        _setOwner(_msgSender());
    }

    /**
     * @dev Returns the address of the current owner.
     */
    function owner() public view virtual returns (address) {
        return _owner;
    }

    /**
     * @dev Throws if called by any account other than the owner.
     */
    modifier onlyOwner() {
        require(owner() == _msgSender(), "Ownable: caller is not the owner");
        _;
    }

    /**
     * @dev Leaves the contract without owner. It will not be possible to call
     * `onlyOwner` functions anymore. Can only be called by the current owner.
     *
     * NOTE: Renouncing ownership will leave the contract without an owner,
     * thereby removing any functionality that is only available to the owner.
     */
    function renounceOwnership() public virtual onlyOwner {
        _setOwner(address(0));
    }

    /**
     * @dev Transfers ownership of the contract to a new account (`newOwner`).
     * Can only be called by the current owner.
     */
    function transferOwnership(address newOwner) public virtual onlyOwner {
        require(newOwner != address(0), "Ownable: new owner is the zero address");
        _setOwner(newOwner);
    }

    function _setOwner(address newOwner) private {
        address oldOwner = _owner;
        _owner = newOwner;
        emit OwnershipTransferred(oldOwner, newOwner);
    }
}

/**
 * @dev Contract module that helps prevent reentrant calls to a function.
 *
 * Inheriting from `ReentrancyGuard` will make the {nonReentrant} modifier
 * available, which can be applied to functions to make sure there are no nested
 * (reentrant) calls to them.
 *
 * Note that because there is a single `nonReentrant` guard, functions marked as
 * `nonReentrant` may not call one another. This can be worked around by making
 * those functions `private`, and then adding `external` `nonReentrant` entry
 * points to them.
 *
 * TIP: If you would like to learn more about reentrancy and alternative ways
 * to protect against it, check out our blog post
 * https://blog.openzeppelin.com/reentrancy-after-istanbul/[Reentrancy After Istanbul].
 */
abstract contract ReentrancyGuard {
    // Booleans are more expensive than uint256 or any type that takes up a full
    // word because each write operation emits an extra SLOAD to first read the
    // slot's contents, replace the bits taken up by the boolean, and then write
    // back. This is the compiler's defense against contract upgrades and
    // pointer aliasing, and it cannot be disabled.

    // The values being non-zero value makes deployment a bit more expensive,
    // but in exchange the refund on every call to nonReentrant will be lower in
    // amount. Since refunds are capped to a percentage of the total
    // transaction's gas, it is best to keep them low in cases like this one, to
    // increase the likelihood of the full refund coming into effect.
    uint256 private constant _NOT_ENTERED = 1;
    uint256 private constant _ENTERED = 2;

    uint256 private _status;

    constructor() {
        _status = _NOT_ENTERED;
    }

    /**
     * @dev Prevents a contract from calling itself, directly or indirectly.
     * Calling a `nonReentrant` function from another `nonReentrant`
     * function is not supported. It is possible to prevent this from happening
     * by making the `nonReentrant` function external, and make it call a
     * `private` function that does the actual work.
     */
    modifier nonReentrant() {
        // On the first call to nonReentrant, _notEntered will be true
        require(_status != _ENTERED, "ReentrancyGuard: reentrant call");

        // Any calls to nonReentrant after this point will fail
        _status = _ENTERED;

        _;

        // By storing the original value once again, a refund is triggered (see
        // https://eips.ethereum.org/EIPS/eip-2200)
        _status = _NOT_ENTERED;
    }
}


contract BelpGaming is Ownable, ReentrancyGuard {

    /* ========== STRUCT ========== */
    // gamer info
    struct GamerInfo {
        string nickName;
        string rewardCode;
        address to;
    }

    event SetRewardToken(address indexed owner, address _newRewardToken);
    event SetReward(address indexed owner,  uint8 _rewardType, uint256 _reward);
    event SetRewardCode(address indexed owner, uint8 _rewardType, string _nickName, string _rewardCode, address _to);
    event SetMaxRewardForSecondType(address indexed owner, uint256 _newMaxReward);

    /* ========== VARIABLES ========== */
    IERC20 public rewardTokenForFirstType = IERC20(0x4f46d39991a3DcaCf6DAB52492fe4f24fF78B688);
    IERC20 public rewardTokenForSecondType = IERC20(0x0cb4ed981dD280b87BF816E1074a37Ddf4ca6F9F);

    uint256 public rewardFirst = 10 ether;
    uint256 public rewardSecond = 20 ether;

    uint256 public maxRewardForSecondType = 300;

    GamerInfo[] public gamerInfoFirstType;
    GamerInfo[] public gamerInfoSecondType;

    /* ========== VIEWS ========== */

    /**
     * @dev Function to get number of gamer got reward code on first type
     */
    function numberOfFirstType() public view returns(uint256) {
        return gamerInfoFirstType.length;
    }

    /**
     * @dev Function to get number of gamer got reward code on second type
     */
    function numberOfSecondType() public view returns(uint256) {
        return gamerInfoSecondType.length;
    }

    /**
     * @dev Function to check valid reward code
     */
    function checkValidRewardCode(uint8 _rewardType, string memory _nickName, string memory _rewardCode) public view returns(bool) {
        if(_rewardType == 1) {
            for(uint256 i = 0; i < gamerInfoFirstType.length; i++) {
                if(compareStrings(gamerInfoFirstType[i].rewardCode, _rewardCode) || compareStrings(gamerInfoFirstType[i].nickName, _nickName)) {
                    return false;
                }
            }
            for(uint256 i = 0; i < gamerInfoSecondType.length; i++) {
                if(compareStrings(gamerInfoSecondType[i].rewardCode, _rewardCode)) {
                    return false;
                }
            }
        }
        if(_rewardType == 2) {
            if(numberOfSecondType() >= maxRewardForSecondType) {
                return false;
            }
            for(uint256 i = 0; i < gamerInfoFirstType.length; i++) {
                if(compareStrings(gamerInfoFirstType[i].rewardCode, _rewardCode)) {
                    return false;
                }
            }
            for(uint256 i = 0; i < gamerInfoSecondType.length; i++) {
                if(compareStrings(gamerInfoSecondType[i].rewardCode, _rewardCode) || compareStrings(gamerInfoFirstType[i].nickName, _nickName)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @dev Function to comapre 2 string
     */
    function compareStrings(string memory a, string memory b) public view returns (bool) {
        return (keccak256(abi.encodePacked((a))) == keccak256(abi.encodePacked((b))));
    }

    /* ========== RESTRICTED FUNCTIONS ========== */

    /**
     * @dev Function to set new reward token.
     * @param _rewardType is a new number of reward type.
     * @param _newRewardToken is a new reward token.
     */
    function setRewardToken(uint8 _rewardType, address _newRewardToken) external onlyOwner {
        if(_rewardType == 1) {
            rewardTokenForFirstType = IERC20(_newRewardToken);
        } else if(_rewardType == 2) {
            rewardTokenForSecondType = IERC20(_newRewardToken);
        }

        emit SetRewardToken(msg.sender, _newRewardToken);
    }

    /**
     * @dev Function to set the number of reward token.
     * @param _rewardType is a new number of reward type.
     * @param _reward is a new number of reward token.
     */
    function setReward(uint8 _rewardType, uint256 _reward) external onlyOwner {
        if(_rewardType == 1) {
            rewardFirst = _reward;
        } else if(_rewardType == 2) {
            rewardSecond = _reward;
        }

        emit SetReward(msg.sender, _rewardType, _reward);
    }

    /**
     * @dev Function to set reward code
     * @param _rewardType is a reward type (1 or 2).
     * @param _nickName is a nick name.
     * @param _rewardCode is a reward code.
     */
    function setRewardCode(uint8 _rewardType, string memory _nickName, string memory _rewardCode, address _to, uint256 _rewardSecond) external nonReentrant onlyOwner {
        require(checkValidRewardCode(_rewardType, _nickName, _rewardCode), "invalid reward code");

        GamerInfo memory gamerInfo;
        gamerInfo.nickName = _nickName;
        gamerInfo.rewardCode = _rewardCode;
        gamerInfo.to = _to;

        if(_rewardType == 1) {
            gamerInfoFirstType.push(gamerInfo);
            rewardTokenForFirstType.transfer(_to, rewardFirst);
        } else if(_rewardType == 2) {
            gamerInfoSecondType.push(gamerInfo);
            rewardTokenForSecondType.transfer(_to, _rewardSecond);
        }

        emit SetRewardCode(msg.sender, _rewardType, _nickName, _rewardCode, _to);
    }

    /**
     * @dev Function to set max reward for sencond type.
     * @param _newMaxReward is a new max reward for second type.
     */
    function setMaxRewardForSecondType(uint256 _newMaxReward) external onlyOwner {
        maxRewardForSecondType = _newMaxReward;

        emit SetMaxRewardForSecondType(msg.sender, _newMaxReward);
    }

}