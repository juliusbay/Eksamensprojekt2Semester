package org.example.eksamensprojekt2semester.Service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BookingService {

    public Date dateFormatter(Date input) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String format = formatter.format(input);
        try {
            Date tempdate = formatter.parse(format);

            if (input.equals(tempdate)) {
                return input;
            } else {
                return tempdate;
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}