package com.example.TelegramBot.comp;

import com.example.TelegramBot.DTO.TaskDTO;
import com.example.TelegramBot.config.BotConfig;
import com.example.TelegramBot.model.Section;
import com.example.TelegramBot.service.TodoistService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class MessangerTelegramBot extends TelegramLongPollingBot {
    final BotConfig config;
    private final TodoistService todoistService;
    private LocalDate date = LocalDate.now();

    public MessangerTelegramBot(BotConfig config, TodoistService todoistService) {
        this.config = config;
        this.todoistService = todoistService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    public void onUpdateReceived(@NotNull Update update) {
        long chatId = 0;
        String userName = null;
        String receivedMessage;
        ResponseEntity<String> responseEntity = todoistService.getAllSections();
        List<Section> sections = null;

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                sections = objectMapper.readValue(responseBody, new TypeReference<List<Section>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();
            TaskDTO taskDTO = new TaskDTO();

            if(update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                String sectionId = "0";
                String[] lines = receivedMessage.split("\n");

                if ("/help".equals(receivedMessage)) {
                    startBot(chatId, userName);
                } else if(lines[0].equals("#Task") && lines.length > 2) {
                    for (Section section : sections) {
                        if (section.getName().equals(lines[2])) {
                            sectionId = section.getId();
                        }
                    }
                    if (sectionId != "0") {
                        switch (lines.length) {
                            case 6:
                                taskDTO.setTaskName(lines[1]);
                                taskDTO.setSectionId(sectionId);
                                taskDTO.setDescriptionText(lines[2]);
                                taskDTO.setPriority(lines[4]);
                                todoistService.createTask(taskDTO);
                                switch (lines[5]) {
                                    case "Сегодня":
                                        todoistService.createTimeForTask("сегодня", lines[1]);
                                        break;
                                    case "Завтра":
                                        todoistService.createTimeForTask("завтра", lines[1]);
                                        break;
                                    case "Следующая неделя":
                                        todoistService.createTimeForTask("следующая неделя", lines[1]);
                                        break;
                                    case "Следующий месяц":
                                        todoistService.createTimeForTask("следующий месяц", lines[1]);
                                        break;
                                    case "На выходных":
                                        todoistService.createTimeForTask("на выходных", lines[1]);
                                        break;
                                    default:
                                        break;
                                }
                                botAnswerUtils(receivedMessage, chatId, userName);
                                break;
                            case 3:
                                taskDTO.setTaskName(lines[1]);
                                taskDTO.setSectionId(sectionId);
                                taskDTO.setDescriptionText("");
                                taskDTO.setPriority("1");
                                todoistService.createTask(taskDTO);
                                todoistService.createTimeForTask("сегодня", lines[1]);
                                break;
                            default:
                                break;
                        }
                    } else {
                        notFoundSection(chatId, lines[2]);
                    }
                }
            }
        }
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        switch (receivedMessage) {
            case "/help":
                startBot(chatId, userName);
                break;
            default:
                break;
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Здраствуйте, " + userName + "! Я Sirius bot.\n"
                + "Для созданий задачи напишите:\n"
                + "#Task\n"
                + "1. Напишите название задачи\n"
                + "2. Для кого задача\n"
                + "3. Напишите описание\n"
                + "4. Приоритет (от 1 до 4)\n"
                + "5. Дедлайн таска (Сегодня, Завтра, Следующая неделя, Следующий месяц, На выходных)");

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void notFoundSection(long chatId, String sectionName){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Пользователя под названим " + sectionName + " не существует");

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}