package br.com.everton.backend.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Utils {

    // DATAS

    public static String formataData(LocalDate data) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatador).toString();
    }

    public static String formataData(LocalDateTime data) {
        DateTimeFormatter formatador = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(new Locale("pt", "br"));
        return data.format(formatador).toString();
    }

}
