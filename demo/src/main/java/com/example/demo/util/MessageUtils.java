package com.example.demo.util;

import com.example.demo.exception.vm.ErrorField;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {

    private final MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Get Error property value from error.properties
     *
     * @param messageKey Message Key
     * @return Error Property Value
     */
    public String getPropertyValue(String messageKey) {
        return messageSource.getMessage(messageKey, null, Locale.getDefault());
    }

    /**
     * Get Error Message with Error Code from error.properties
     *
     * @param messageKey Message Key
     * @return Error Field
     */
    public ErrorField getErrorField(String messageKey) {
        String messageValue = messageSource.getMessage(messageKey, null, Locale.getDefault());
        System.out.println(messageValue);
        String[] values = messageValue.split(":");
        return ErrorField.of(values[0], null, values[1]);
    }

}