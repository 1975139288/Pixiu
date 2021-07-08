package com.poker.common.network.exception

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/7 14:42
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class ServerException(errCode: Int, errMsg: String) : BaseHttpException(errCode, errMsg)