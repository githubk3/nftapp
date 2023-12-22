package com.application.scraper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Main {
    public static void main(String[] args) {
    	  String inputDateTime = "Dec 18, 2023 · 2:16 AM UTC";
          DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy '·' h:mm a z",  Locale.ENGLISH);
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

          LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);
          String outputDate = dateTime.format(outputFormatter);
          
          System.out.println(outputDate);
    }
}
