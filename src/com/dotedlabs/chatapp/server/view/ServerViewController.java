package com.dotedlabs.chatapp.server.view;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.dotedlabs.chatapp.server.MainApp;
import com.dotedlabs.chatapp.server.util.IPAddressValidator;
import com.dotedlabs.chatapp.server.util.MessageUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerViewController {
	// Default server socket port
	private final int DEFAULT_SOCKET_PORT = 2000;

	// Reference to the main application.
	private MainApp mainApp;

	// Reference the server log text area
	@FXML
	private TextArea serverLog;

	// Reference the server start button
	@FXML
	private Button serverStartBtn;

	// Reference the server stop button
	@FXML
	private Button serverStopBtn;

	// Reference the server IP address
	@FXML
	private TextField ipAddress;

	// Reference the server port
	@FXML
	private TextField serverPort;

	static ServerSocket server;
	static Socket conn;

	private IPAddressValidator ipAddressValidator;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		serverStopBtn.setDisable(true);
		ipAddressValidator = new IPAddressValidator();
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * This will trigger the starting of the server
	 */
	public void handleServerStart() {

		try {
			// Validate port number
			int serverPortToUse = 0;
			if (!serverPort.getText().equals("")) {
				serverPortToUse = Integer.parseInt(serverPort.getText());
			} else {
				MessageUtils.error("Invalid Port Number, Please check your Port Number entered");
				return;
			}

			// Validate the IP address
			if (ipAddressValidator.validate(ipAddress.getText())) {

				server = new ServerSocket(serverPortToUse, 1, InetAddress.getByName(ipAddress.getText()));

				serverStartBtn.setDisable(true);
				serverStartBtn.setText("Server Started, Waiting for clients");
				serverStopBtn.setDisable(false);

				conn = server.accept();


			} else {
				MessageUtils.error("Invalid IP Address, Please check your IP address entered");
				return;
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		serverLog.setText("[Server started...]");
	}

	/**
	 * This will trigger the stopping of the server
	 */
	public void handleServerStop() {
		serverStartBtn.setDisable(false);
		serverStartBtn.setText("Start Server");
		serverStopBtn.setDisable(true);

		serverLog.setText("[Server stopped...]");
	}
}
