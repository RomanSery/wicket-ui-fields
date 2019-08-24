package org.coderdreams;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.List;

import org.coderdreams.wicketfields.model.CheckBoxGroupModel;
import org.coderdreams.wicketfields.model.LocalDateRangeModel;
import org.coderdreams.wicketfields.model.YesNoUnknownChoice;

public class DemoFormObj implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ajaxTextAreaWithCounterValue;
    private String ajaxTxtValue;
    private String textAreaValue;
    private String textAreaWithCounterValue;
    private String txtValue;

    private LocalDate ajaxLocalDateValue;
    private LocalTime ajaxTimeValue;
    private LocalDate localDateValue;
    private LocalDateRangeModel localDateRangeValue;
    private LocalDateTime localDateTimeValue;
    private MonthDay monthDayValue;
    private LocalTime timeValue;

    private BigDecimal ajaxMoneyValue;
    private Double ajaxNumberSpinnerValue;
    private BigDecimal moneyValue;
    private Integer numberSpinnerValue;

    private Boolean ajaxCheckBoxValue;
    private Boolean ajaxCheckBoxReverseValue;
    private Boolean ajaxRadioValue;
    private Boolean ajaxSwitchValue;
    private YesNoUnknownChoice ajaxYesNoUnknownValue;
    private Boolean checkBoxValue;
    private Boolean checkBoxReverseValue;
    private Boolean radioValue;
    private YesNoUnknownChoice yesNoUnknownValue;
    private Boolean group1Choice;
    private Boolean group2Choice;
    private Boolean group3Choice;

    private Boolean group4Choice;
    private Boolean group5Choice;
    private Boolean group6Choice;

    private State ajaxDropdownValue;
    private String ajaxGroupedDropdownValue;
    private String groupedDropdownValue;
    private List<State> ajaxMultiDropdownValue;
    private State dropdownValue;
    private List<State> multiDropdownValue;

    public String getAjaxTextAreaWithCounterValue() {
        return ajaxTextAreaWithCounterValue;
    }

    public void setAjaxTextAreaWithCounterValue(String ajaxTextAreaWithCounterValue) {
        this.ajaxTextAreaWithCounterValue = ajaxTextAreaWithCounterValue;
    }

    public String getAjaxTxtValue() {
        return ajaxTxtValue;
    }

    public void setAjaxTxtValue(String ajaxTxtValue) {
        this.ajaxTxtValue = ajaxTxtValue;
    }

    public String getTextAreaValue() {
        return textAreaValue;
    }

    public void setTextAreaValue(String textAreaValue) {
        this.textAreaValue = textAreaValue;
    }

    public String getTextAreaWithCounterValue() {
        return textAreaWithCounterValue;
    }

    public void setTextAreaWithCounterValue(String textAreaWithCounterValue) {
        this.textAreaWithCounterValue = textAreaWithCounterValue;
    }

    public String getTxtValue() {
        return txtValue;
    }

    public void setTxtValue(String txtValue) {
        this.txtValue = txtValue;
    }

    public LocalDate getAjaxLocalDateValue() {
        return ajaxLocalDateValue;
    }

    public void setAjaxLocalDateValue(LocalDate ajaxLocalDateValue) {
        this.ajaxLocalDateValue = ajaxLocalDateValue;
    }

    public LocalTime getAjaxTimeValue() {
        return ajaxTimeValue;
    }

    public void setAjaxTimeValue(LocalTime ajaxTimeValue) {
        this.ajaxTimeValue = ajaxTimeValue;
    }

    public LocalDate getLocalDateValue() {
        return localDateValue;
    }

    public void setLocalDateValue(LocalDate localDateValue) {
        this.localDateValue = localDateValue;
    }

    public LocalDateRangeModel getLocalDateRangeValue() {
        if(localDateRangeValue == null) {
            localDateRangeValue = new LocalDateRangeModel(null, null);
        }
        return localDateRangeValue;
    }

    public void setLocalDateRangeValue(LocalDateRangeModel localDateRangeValue) {
        this.localDateRangeValue = localDateRangeValue;
    }

    public LocalDateTime getLocalDateTimeValue() {
        return localDateTimeValue;
    }

    public void setLocalDateTimeValue(LocalDateTime localDateTimeValue) {
        this.localDateTimeValue = localDateTimeValue;
    }

    public MonthDay getMonthDayValue() {
        return monthDayValue;
    }

    public void setMonthDayValue(MonthDay monthDayValue) {
        this.monthDayValue = monthDayValue;
    }

    public LocalTime getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(LocalTime timeValue) {
        this.timeValue = timeValue;
    }

    public BigDecimal getAjaxMoneyValue() {
        return ajaxMoneyValue;
    }

    public void setAjaxMoneyValue(BigDecimal ajaxMoneyValue) {
        this.ajaxMoneyValue = ajaxMoneyValue;
    }

    public Double getAjaxNumberSpinnerValue() {
        return ajaxNumberSpinnerValue;
    }

    public void setAjaxNumberSpinnerValue(Double ajaxNumberSpinnerValue) {
        this.ajaxNumberSpinnerValue = ajaxNumberSpinnerValue;
    }

    public BigDecimal getMoneyValue() {
        return moneyValue;
    }

    public void setMoneyValue(BigDecimal moneyValue) {
        this.moneyValue = moneyValue;
    }

    public Integer getNumberSpinnerValue() {
        return numberSpinnerValue;
    }

    public void setNumberSpinnerValue(Integer numberSpinnerValue) {
        this.numberSpinnerValue = numberSpinnerValue;
    }

    public Boolean getAjaxCheckBoxValue() {
        return ajaxCheckBoxValue;
    }

    public void setAjaxCheckBoxValue(Boolean ajaxCheckBoxValue) {
        this.ajaxCheckBoxValue = ajaxCheckBoxValue;
    }

    public Boolean getAjaxCheckBoxReverseValue() {
        return ajaxCheckBoxReverseValue;
    }

    public void setAjaxCheckBoxReverseValue(Boolean ajaxCheckBoxReverseValue) {
        this.ajaxCheckBoxReverseValue = ajaxCheckBoxReverseValue;
    }

    public Boolean getAjaxRadioValue() {
        return ajaxRadioValue;
    }

    public void setAjaxRadioValue(Boolean ajaxRadioValue) {
        this.ajaxRadioValue = ajaxRadioValue;
    }

    public Boolean getAjaxSwitchValue() {
        return ajaxSwitchValue;
    }

    public void setAjaxSwitchValue(Boolean ajaxSwitchValue) {
        this.ajaxSwitchValue = ajaxSwitchValue;
    }

    public YesNoUnknownChoice getAjaxYesNoUnknownValue() {
        return ajaxYesNoUnknownValue;
    }

    public void setAjaxYesNoUnknownValue(YesNoUnknownChoice ajaxYesNoUnknownValue) {
        this.ajaxYesNoUnknownValue = ajaxYesNoUnknownValue;
    }

    public Boolean getCheckBoxValue() {
        return checkBoxValue;
    }

    public void setCheckBoxValue(Boolean checkBoxValue) {
        this.checkBoxValue = checkBoxValue;
    }

    public Boolean getCheckBoxReverseValue() {
        return checkBoxReverseValue;
    }

    public void setCheckBoxReverseValue(Boolean checkBoxReverseValue) {
        this.checkBoxReverseValue = checkBoxReverseValue;
    }

    public Boolean getRadioValue() {
        return radioValue;
    }

    public void setRadioValue(Boolean radioValue) {
        this.radioValue = radioValue;
    }

    public YesNoUnknownChoice getYesNoUnknownValue() {
        return yesNoUnknownValue;
    }

    public void setYesNoUnknownValue(YesNoUnknownChoice yesNoUnknownValue) {
        this.yesNoUnknownValue = yesNoUnknownValue;
    }

    public State getAjaxDropdownValue() {
        return ajaxDropdownValue;
    }

    public void setAjaxDropdownValue(State ajaxDropdownValue) {
        this.ajaxDropdownValue = ajaxDropdownValue;
    }

    public String getAjaxGroupedDropdownValue() {
        return ajaxGroupedDropdownValue;
    }

    public void setAjaxGroupedDropdownValue(String ajaxGroupedDropdownValue) {
        this.ajaxGroupedDropdownValue = ajaxGroupedDropdownValue;
    }

    public List<State> getAjaxMultiDropdownValue() {
        return ajaxMultiDropdownValue;
    }

    public void setAjaxMultiDropdownValue(List<State> ajaxMultiDropdownValue) {
        this.ajaxMultiDropdownValue = ajaxMultiDropdownValue;
    }

    public State getDropdownValue() {
        return dropdownValue;
    }

    public void setDropdownValue(State dropdownValue) {
        this.dropdownValue = dropdownValue;
    }

    public String getGroupedDropdownValue() {
        return groupedDropdownValue;
    }

    public void setGroupedDropdownValue(String groupedDropdownValue) {
        this.groupedDropdownValue = groupedDropdownValue;
    }

    public List<State> getMultiDropdownValue() {
        return multiDropdownValue;
    }

    public void setMultiDropdownValue(List<State> multiDropdownValue) {
        this.multiDropdownValue = multiDropdownValue;
    }

    public Boolean getGroup1Choice() {
        return group1Choice;
    }

    public void setGroup1Choice(Boolean group1Choice) {
        this.group1Choice = group1Choice;
    }

    public Boolean getGroup2Choice() {
        return group2Choice;
    }

    public void setGroup2Choice(Boolean group2Choice) {
        this.group2Choice = group2Choice;
    }

    public Boolean getGroup3Choice() {
        return group3Choice;
    }

    public void setGroup3Choice(Boolean group3Choice) {
        this.group3Choice = group3Choice;
    }

    public Boolean getGroup4Choice() {
        return group4Choice;
    }

    public void setGroup4Choice(Boolean group4Choice) {
        this.group4Choice = group4Choice;
    }

    public Boolean getGroup5Choice() {
        return group5Choice;
    }

    public void setGroup5Choice(Boolean group5Choice) {
        this.group5Choice = group5Choice;
    }

    public Boolean getGroup6Choice() {
        return group6Choice;
    }

    public void setGroup6Choice(Boolean group6Choice) {
        this.group6Choice = group6Choice;
    }
}
