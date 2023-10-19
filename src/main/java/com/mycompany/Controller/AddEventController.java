/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.Controller;

import com.mycompany.Application.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class AddEventController {
    @FXML private TextField titleField;

    @FXML private ToggleButton MONButton;
    @FXML private ToggleButton TUEButton;
    @FXML private ToggleButton WEDButton;
    @FXML private ToggleButton THUButton;
    @FXML private ToggleButton FRIButton;
    @FXML private ToggleButton SATButton;
    @FXML private ToggleButton SUNButton;

    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;

    @FXML private DatePicker startDayField;
    @FXML private DatePicker endDayField;

    @FXML private ColorPicker ColorButton;
    @FXML private RadioButton loopButton;


    public Event setEvent(){
        Event event = new Event();
        event.setTitle(titleField.getText());
        event.setStartTime(startTimeField.getText());
        event.setEndTime(endTimeField.getText());
        event.setStartDay(startDayField.getValue().toString());
        event.setEndDay(endDayField.getValue().toString());
        event.setLoop(loopButton.isSelected());

        Color color = ColorButton.getValue();
        String colorAsString = String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
        event.setColor(colorAsString);

        event.setDayOfWeeks(setWeeks());
        return event;
    }

    @FXML
    void saveButtonPress(ActionEvent event) {
        MainScheduleController schedule =MainScheduleController.getInstance();
        schedule.addEventToSchedule(setEvent());
    }
    //remove delete ButtonPress,now it is being handle in the MainSheduleController

    // set which weeks is select
    private String setWeeks(){
        StringBuilder selectedDays = new StringBuilder();
        if(SUNButton.isSelected()){
            selectedDays.append("SUN ");
        }
        if(MONButton.isSelected()){
            selectedDays.append("MON ");
        }
        if(TUEButton.isSelected()){
            selectedDays.append("TUE ");
        }
        if(WEDButton.isSelected()){
            selectedDays.append("WED ");
        }
        if(THUButton.isSelected()){
            selectedDays.append("THU ");
        }
        if(FRIButton.isSelected()){
            selectedDays.append("FRI ");
        }
        if(SATButton.isSelected()){
            selectedDays.append("SAT ");
        }

        return selectedDays.toString().trim();
    }

}



