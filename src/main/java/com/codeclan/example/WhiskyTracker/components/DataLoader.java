package com.codeclan.example.WhiskyTracker.components;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    DistilleryRepository distilleryRepository;

    @Autowired
    WhiskyRepository whiskyRepository;

    public void run(ApplicationArguments args) {
        Distillery glendronach = new Distillery("Glendronach", "Highland");
        distilleryRepository.save(glendronach);

        Distillery rosebank = new Distillery("Rosebank", "Lowland");
        distilleryRepository.save(rosebank);

        Distillery macallan = new Distillery("Macallan", "Speyside");
        distilleryRepository.save(macallan);

        Distillery balvenie = new Distillery("Balvenie", "Speyside");
        distilleryRepository.save(balvenie);

        Whisky whisky1 = new Whisky("The Glendronach Revival", 15, 2018, glendronach);
        whiskyRepository.save(whisky1);

        Whisky whisky2 = new Whisky("The Rosebank 12 - Flora and Fona", 12, 1991, rosebank);
        whiskyRepository.save(whisky2);

        Whisky whisky3 = new Whisky("The Macallan Anniversary Malt", 25, 1995, macallan);
        whiskyRepository.save(whisky3);

        Whisky whisky4 = new Whisky("The Glendronach Original", 12, 2018, glendronach);
        whiskyRepository.save(whisky4);

        Whisky whisky5 = new Whisky("The Balvenie Single Malt", 17, 2004, balvenie);
        whiskyRepository.save(whisky5);
    }
}
