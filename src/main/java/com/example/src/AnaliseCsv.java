package com.example.src;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class AnaliseCsv {

    public static Optional<List<Users>> getListOfUsers() throws IOException {

        List<Users> users;

        try (InputStream is = AnaliseCsv.class.getClassLoader()
                .getResourceAsStream("src/main/resources/Usuarios.csv")) {

            if (is == null) {

                return Optional.empty();
            }

            HeaderColumnNameMappingStrategy<Users> strategy
                    = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Users.class);

            try (var br = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))) {

                CsvToBean<Users> csvToBean = new CsvToBeanBuilder<Users>(br)
                        .withType(Users.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                users = csvToBean.parse();
            }
        }

        return Optional.of(users);
    }
}
