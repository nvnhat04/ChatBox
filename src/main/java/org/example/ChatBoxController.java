package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.security.Key;
import java.util.Timer;

public class ChatBoxController {
    @FXML
    public VBox layout = new VBox();
    public VBox chatPane = new VBox();
    public TextArea chatArea = new TextArea();
    public TextField messageField = new TextField();
    public Button sendButton = new Button();

    public void initialize(){


        messageField.setPromptText("Type your message...");



    }
    public void sendAction(MouseEvent event){
        sendMessage( messageField);
    }
    public void sendActionKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            sendMessage( messageField);
        }
    }

    private String handleChat(String message) {
        message = message.toLowerCase();
        if (message.contains("hello") || message.contains("hi")) {
            return "Welcome to ChatBox!";
        } else if (message.contains("name")) {
            return "My name is ChatBox. I'm created by Nhat.";
        } else if (message.contains("how are you")) {
            return "I'm good.";
        } else if (message.contains("weather")) {
            return "I'm sorry, \nI don't have access to real-time weather information.";
        } else if (message.contains("bye")) {
            return "Goodbye! Have a great day!";
        } else if (message.contains("joke")) {
            return "Why don't scientists trust atoms?\n Because they make up everything!";
        } else if (message.contains("help")) {
            return "You can ask about my name,\n how I'm doing, the weather, or just say hello!";
        }
        return "I didn't understand that.\n Ask me something else!";
    }

    private void sendMessage(TextField messageField) {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            Label userLabel = new Label(message);

            Label chatBoxLabel = new Label( handleChat(message));
            userLabel.getStyleClass().add("user");
            chatBoxLabel.getStyleClass().add("chatbox");
            layout.getChildren().add(createChat("You:", userLabel));

            layout.getChildren().add(createChat("ChatBox:", chatBoxLabel));
            messageField.clear();
        }
    }
    private HBox createChat(String nameChat, Label chat){

        HBox hBox = new HBox();
        Label name = new Label(nameChat);
        name.getStyleClass().add("name");
        hBox.getChildren().addAll(name, chat);
        hBox.setSpacing(10);
        return hBox;
    }

}
