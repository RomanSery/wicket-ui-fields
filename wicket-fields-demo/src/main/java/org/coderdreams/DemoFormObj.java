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
    private Integer ajaxNumberSpinnerValue;
    private BigDecimal moneyValue;
    private Integer numberSpinnerValue;

    private Boolean ajaxCheckBoxValue;
    private List<CheckBoxGroupModel> ajaxCheckBoxGroupValue;
    private Boolean ajaxCheckBoxReverseValue;
    private Boolean ajaxRadioValue;
    private Boolean ajaxSwitchValue;
    private Boolean ajaxYesNoUnknownValue;
    private Boolean checkBoxValue;
    private List<CheckBoxGroupModel> checkBoxGroupValue;
    private Boolean checkBoxReverseValue;
    private Boolean radioValue;
    private Boolean yesNoUnknownValue;

    private String ajaxDropdownValue;
    private List<String> ajaxGroupedDropdownValue;
    private List<String> ajaxMultiDropdownValue;
    private String dropdownValue;
    private List<String> groupedDropdownValue;
    private List<String> multiDropdownValue;

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

    public Integer getAjaxNumberSpinnerValue() {
        return ajaxNumberSpinnerValue;
    }

    public void setAjaxNumberSpinnerValue(Integer ajaxNumberSpinnerValue) {
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

    public List<CheckBoxGroupModel> getAjaxCheckBoxGroupValue() {
        return ajaxCheckBoxGroupValue;
    }

    public void setAjaxCheckBoxGroupValue(List<CheckBoxGroupModel> ajaxCheckBoxGroupValue) {
        this.ajaxCheckBoxGroupValue = ajaxCheckBoxGroupValue;
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

    public Boolean getAjaxYesNoUnknownValue() {
        return ajaxYesNoUnknownValue;
    }

    public void setAjaxYesNoUnknownValue(Boolean ajaxYesNoUnknownValue) {
        this.ajaxYesNoUnknownValue = ajaxYesNoUnknownValue;
    }

    public Boolean getCheckBoxValue() {
        return checkBoxValue;
    }

    public void setCheckBoxValue(Boolean checkBoxValue) {
        this.checkBoxValue = checkBoxValue;
    }

    public List<CheckBoxGroupModel> getCheckBoxGroupValue() {
        return checkBoxGroupValue;
    }

    public void setCheckBoxGroupValue(List<CheckBoxGroupModel> checkBoxGroupValue) {
        this.checkBoxGroupValue = checkBoxGroupValue;
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

    public Boolean getYesNoUnknownValue() {
        return yesNoUnknownValue;
    }

    public void setYesNoUnknownValue(Boolean yesNoUnknownValue) {
        this.yesNoUnknownValue = yesNoUnknownValue;
    }

    public String getAjaxDropdownValue() {
        return ajaxDropdownValue;
    }

    public void setAjaxDropdownValue(String ajaxDropdownValue) {
        this.ajaxDropdownValue = ajaxDropdownValue;
    }

    public List<String> getAjaxGroupedDropdownValue() {
        return ajaxGroupedDropdownValue;
    }

    public void setAjaxGroupedDropdownValue(List<String> ajaxGroupedDropdownValue) {
        this.ajaxGroupedDropdownValue = ajaxGroupedDropdownValue;
    }

    public List<String> getAjaxMultiDropdownValue() {
        return ajaxMultiDropdownValue;
    }

    public void setAjaxMultiDropdownValue(List<String> ajaxMultiDropdownValue) {
        this.ajaxMultiDropdownValue = ajaxMultiDropdownValue;
    }

    public String getDropdownValue() {
        return dropdownValue;
    }

    public void setDropdownValue(String dropdownValue) {
        this.dropdownValue = dropdownValue;
    }

    public List<String> getGroupedDropdownValue() {
        return groupedDropdownValue;
    }

    public void setGroupedDropdownValue(List<String> groupedDropdownValue) {
        this.groupedDropdownValue = groupedDropdownValue;
    }

    public List<String> getMultiDropdownValue() {
        return multiDropdownValue;
    }

    public void setMultiDropdownValue(List<String> multiDropdownValue) {
        this.multiDropdownValue = multiDropdownValue;
    }
}
