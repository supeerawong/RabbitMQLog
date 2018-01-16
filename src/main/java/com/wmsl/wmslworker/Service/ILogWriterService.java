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
public interface ILogWriterService {
    void CleaneFile(String PATH,String FILENAME);
    void WriteLogWithPath(String PATH,String FILENAME,String message);
    void WriteLogWithURL(String URL, String TYPE, String message);
}