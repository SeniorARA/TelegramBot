import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args){

        /*Инициализация Бота,Регистрация*/
       try{
           TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
           telegramBotsApi.registerBot(new Bot());

       }catch (TelegramApiException e) {
           e.printStackTrace();
       }
    }

    /*Метод вывода кнопки*/
    public void mybtn(SendMessage sendMessage){
       ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
       sendMessage.setReplyMarkup(replyKeyboardMarkup);

       //Параметры Бота
       replyKeyboardMarkup.setSelective(true);
       replyKeyboardMarkup.setResizeKeyboard(true);
       replyKeyboardMarkup.setOneTimeKeyboard(true);

       //Список строки Клавиатуры
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow firstkeyboardrow = new KeyboardRow();
        KeyboardRow secondkeyboardrow = new KeyboardRow();
        firstkeyboardrow.add(new KeyboardButton("Hello"));
        firstkeyboardrow.add(new KeyboardButton("bye"));
        secondkeyboardrow.add(new KeyboardButton("Photo"));

        // enable ListKeyboard
        keyboardRowList.add(firstkeyboardrow);
        keyboardRowList.add(secondkeyboardrow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    //Метод отправки Фото
    public void sendPh(Message message,String image){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.enableNotification();
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setReplyToMessageId(message.getMessageId());
        sendPhoto.setPhoto(new InputFile(new File(image)));
        try{
           execute(sendPhoto);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /*Метод отправки сообщения*/
    public void sendMsg(Message message,String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            mybtn(sendMessage);
            execute(sendMessage);


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


/*Метод получения сообщений,обновлений*/
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message !=null && message.hasText()){
            switch (message.getText()){
                case "Hello","hello","Привет","привет","Здраствуй":
                    sendMsg(message,"Hello, My Friend");
                    break;
                case "GoodBye","bye","пока","Пока":
                    sendMsg(message,"Bye, Bye My Friend");
                    break;
                case "Что ты":
                    sendMsg(message,"Я бот который создал человек по имени SeniorARA, но пока я незнаю для чего я предназначен.");
                    break;
                case "Photo","photo":
                    sendPh(message, "C:\\Users\\77475\\IdeaProjects\\TelegramBot\\src\\main\\resources\\04.png");
                    break;
                    default:
                    sendMsg(message,"Прости но я не понимаю тебя.");
                    break;
            }

        }
    }






    /*Токен Бота*/
    @Override
    public String getBotToken() {
        return "1803336237:AAH9vdjwh04QBDxeorMCpVhfj-s8K8cZ8uo";
    }

    /*Юзернейм Бота*/
    @Override
    public String getBotUsername() {
        return "arman_abobabot";
    }



}
