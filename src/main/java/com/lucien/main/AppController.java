package com.lucien.main;

import com.lucien.utils.AlertUtils;

import javafx.event.ActionEvent;

/**
 * <pre>
 *     author : Lucien Z
 *     e-mail : 825038797@qq.com
 *     time   : 2019/05/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AppController {
    public void onClick(ActionEvent actionEvent) {
        AlertUtils.popMessage("Hello","I am great!!");
    }
}
