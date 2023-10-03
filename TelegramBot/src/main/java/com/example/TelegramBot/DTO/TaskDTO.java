package com.example.TelegramBot.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDTO {
    private String taskName;
    private String sectionId;
    private String descriptionText;
    private String priority;
}
