package com.dotedlabs.chatapp.server.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageUtils {

	/**
	 * Alert dialog showing error
	 *
	 * @param msg
	 */
	public static void error(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error");
		alert.setContentText(msg);

		alert.showAndWait();
	}
}
