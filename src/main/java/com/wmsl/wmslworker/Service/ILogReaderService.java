/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmsl.wmslworker.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
/**
 *
 * @author LuciferPM
 */
public interface ILogReaderService {
    void ReceiveLogs(String EXCHANGE_NAME,String FILEPATH, String SET_HOST);
    List<String> SReceiveLogs(String EXCHANGE_NAME, String SET_HOST) throws IOException, TimeoutException, Exception;
    void ReceiveSuccess(String message);
}
