package com.eatclub.ken.eatclubdeals.config;

import com.eatclub.ken.eatclubdeals.EatClubContants;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JavaTimeModuleConfig {

    @Bean
    public Module javaTimeModule() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(new LocalTimeSerializer(DateTimeFormatter.ofPattern(EatClubContants.LOCAL_TIME_FORMAT)));
        module.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(EatClubContants.LOCAL_TIME_FORMAT)));
        return module;
    }
}
