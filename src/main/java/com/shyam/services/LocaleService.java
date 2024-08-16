package com.shyam.services;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocaleService {

    public String getLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        return language;
    }

}
