package com.lucien.utils;

import com.lucien.main.AppUIMain;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * <pre>
 *     author : Lucien Z
 *     e-mail : 825038797@qq.com
 *     time   : 2019/05/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AlertUtils {

    public static final String CONST_STRING_CANCEL = "取消";
    public static final String CONST_STRING_CONFIRM = "确定";

    public static boolean popConfirmCancelDialog(String header, String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                msg,
                new ButtonType(CONST_STRING_CANCEL, ButtonBar.ButtonData.NO),
                new ButtonType(CONST_STRING_CONFIRM, ButtonBar.ButtonData.YES));
        alert.setTitle("Please confirm");
        alert.setHeaderText(header);
        alert.initOwner(getMainStage());
        Optional<ButtonType> _buttonType = alert.showAndWait();
        return _buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES);
    }

    public static void popMessage(String p_header, String p_message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tip");
        alert.setHeaderText(p_header);
        alert.setContentText(p_message);
        alert.initOwner(getMainStage());
        alert.show();
    }

    public static Stage getMainStage(){
        return AppUIMain.getMainStage();
    }

}
