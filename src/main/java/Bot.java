import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args){
       try{
           TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
           telegramBotsApi.registerBot(new Bot());

       }catch (TelegramApiException e) {
           e.printStackTrace();
       }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


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
                    sendMsg(message, "Я бот который создал человек по имени SeniorARA, но пока я незнаю для чего я предназначен.");
                default:
                    sendMsg(message,"Прости но я не понимаю тебя.");

            }
        }
    }






    @Override
    public String getBotToken() {
        return "1803336237:AAH9vdjwh04QBDxeorMCpVhfj-s8K8cZ8uo";
    }


    @Override
    public String getBotUsername() {
        return "arman_abobabot";
    }



}
