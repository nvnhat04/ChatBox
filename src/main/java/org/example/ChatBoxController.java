package org.example;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.security.Key;
import java.util.Timer;

public class ChatBoxController {
    @FXML
    public VBox layout = new VBox();
    public VBox chatPane = new VBox();
    public TextArea chatArea = new TextArea();
    public TextField messageField = new TextField();
    public Button sendButton = new Button();
    public ScrollPane scrollPane = new ScrollPane();

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
            return "I'm sorry, I don't have access to real-time weather information.";
        } else if (message.contains("bye")) {
            return "Goodbye! Have a great day!";
        } else if (message.contains("joke")) {
            return "Why don't scientists trust atoms? Because they make up everything!";
        } else if (message.contains("help")) {
            return "You can ask about my name, how I'm doing, the weather, or just say hello!";
        }
        return "I didn't understand that. Ask me something else!";
    }

    private void sendMessage(TextField messageField) {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            TextArea userTextArea = createChatTextArea(message,2);
            TextArea chatBoxTextArea = createChatTextArea(handleChat(message),2);
            chatBoxTextArea.getStyleClass().add("chatbox");
            userTextArea.getStyleClass().add("user");
            HBox userChatHBox = createChat("You:", userTextArea);
            HBox chatBoxAutoHBox = createChatAuto(" :ChatBox", chatBoxTextArea);

            layout.getChildren().addAll(userChatHBox, chatBoxAutoHBox);

            // Clear the message field
            messageField.clear();

            // Scroll to the bottom of the VBox (if you want to always show the latest messages)
            chatPane.layout();
            chatPane.setPrefHeight(chatPane.getHeight() + userChatHBox.getHeight() + chatBoxAutoHBox.getHeight());
            scrollPane.setVvalue(1.0);
        }
    }
    private TextArea createChatTextArea(String text, int numberOfLines) {
        TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(numberOfLines);
        textArea.setMaxWidth(layout.getPrefWidth() - 100);
        textArea.setMinHeight(Region.USE_PREF_SIZE);  // This allows the TextArea to shrink if the content is smaller
        return textArea;
    }


    private HBox createChat(String nameChat, TextArea chat) {
        HBox hBox = new HBox();
        Label name = new Label(nameChat);
        name.setMaxWidth(50);
        name.getStyleClass().add("name");
        hBox.getChildren().addAll(name, chat);
        hBox.setAlignment(Pos.CENTER_LEFT);  // Align to the left
        hBox.setHgrow(chat, Priority.ALWAYS); // Allow the HBox to grow horizontally
        hBox.setSpacing(10);
        return hBox;
    }

    private HBox createChatAuto(String nameChat, TextArea chat) {
        HBox hBox = new HBox();
        Label name = new Label(nameChat);
        name.setMaxWidth(100);
        name.getStyleClass().add("name");
        hBox.setAlignment(Pos.CENTER_RIGHT);  // Align to the right
        hBox.getChildren().addAll(chat, name);
        hBox.setHgrow(chat, Priority.ALWAYS); // Allow the HBox to grow horizontally
        hBox.setSpacing(10);
        hBox.setVisible(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            hBox.setVisible(true);

            // Scroll to the bottom of the VBox (if you want to always show the latest messages)
            chatPane.layout();
            chatPane.setPrefHeight(chatPane.getHeight() + hBox.getHeight());
        });
        pause.play();
        return hBox;
    }



}
