/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmsl.wmslworker.Service;

import java.util.List;
/**
 *
 * @author LuciferPM
 */
public interface ILogReaderService {
    void ReceiveLogs(String EXCHANGE_NAME,String FILEPATH, String SET_HOST);
    List<String> SReceiveLogs(String EXCHANGE_NAME, String SET_HOST);
    void ReceiveSuccess(String message);
}
