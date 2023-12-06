package com.mycompany.Application;

import java.time.LocalDate;

public interface DataChangeListener {
    void onDataChanged(LocalDate date);
}
