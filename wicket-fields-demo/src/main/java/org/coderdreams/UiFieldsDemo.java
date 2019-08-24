package org.coderdreams;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.model.util.MapModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.event.InitPanelFieldsEvent;
import org.coderdreams.wicketfields.fields.bool.AjaxCheckBoxField;
import org.coderdreams.wicketfields.fields.bool.AjaxCheckBoxGroupField;
import org.coderdreams.wicketfields.fields.bool.AjaxRadioField;
import org.coderdreams.wicketfields.fields.bool.AjaxSwitchField;
import org.coderdreams.wicketfields.fields.bool.AjaxYesNoUnknownField;
import org.coderdreams.wicketfields.fields.bool.CheckBoxField;
import org.coderdreams.wicketfields.fields.bool.CheckBoxGroupField;
import org.coderdreams.wicketfields.fields.bool.RadioField;
import org.coderdreams.wicketfields.fields.bool.YesNoUnknownField;
import org.coderdreams.wicketfields.fields.dates.AjaxLocalDateField;
import org.coderdreams.wicketfields.fields.dates.AjaxTimeField;
import org.coderdreams.wicketfields.fields.dates.LocalDateField;
import org.coderdreams.wicketfields.fields.dates.LocalDateTimeField;
import org.coderdreams.wicketfields.fields.dates.MonthDayField;
import org.coderdreams.wicketfields.fields.dates.TimeField;
import org.coderdreams.wicketfields.fields.dropdown.AjaxDropdownField;
import org.coderdreams.wicketfields.fields.dropdown.AjaxGroupedDropdownField;
import org.coderdreams.wicketfields.fields.dropdown.AjaxMultiDropdownField;
import org.coderdreams.wicketfields.fields.dropdown.DropdownField;
import org.coderdreams.wicketfields.fields.dropdown.GroupedDropdownField;
import org.coderdreams.wicketfields.fields.dropdown.MultiDropdownField;
import org.coderdreams.wicketfields.fields.numeric.AjaxMoneyField;
import org.coderdreams.wicketfields.fields.numeric.AjaxNumberSpinnerField;
import org.coderdreams.wicketfields.fields.numeric.MoneyField;
import org.coderdreams.wicketfields.fields.numeric.NumberSpinnerField;
import org.coderdreams.wicketfields.fields.text.AjaxTextAreaWithCounterField;
import org.coderdreams.wicketfields.fields.text.AjaxTxtField;
import org.coderdreams.wicketfields.fields.text.LabelField;
import org.coderdreams.wicketfields.fields.text.LinkField;
import org.coderdreams.wicketfields.fields.text.TextAreaField;
import org.coderdreams.wicketfields.fields.text.TextAreaWithCounterField;
import org.coderdreams.wicketfields.fields.text.TxtField;
import org.coderdreams.wicketfields.form.SingleClickIndicatingAjaxButton;
import org.coderdreams.wicketfields.model.CheckBoxGroupModel;
import org.coderdreams.wicketfields.resources.UiFieldsBehavior;
import org.coderdreams.wicketfields.util.DateUtils;

public class UiFieldsDemo extends WebPage {
	private static final long serialVersionUID = 1L;

	private Form<Void> fieldsForm;
	private DemoFormObj formData;

	private WebMarkupContainer txtCont, datesCont, boolCont, numericCont, dropdownCont;

	private Boolean txtFieldsEnabled = true;
    private Boolean dateFieldsEnabled = true;
    private Boolean dropdownFieldsEnabled = true;
    private Boolean boolFieldsEnabled = true;
    private Boolean numericFieldsEnabled = true;

    private IModel<Boolean> txtFieldsEnabledModel;
    private IModel<Boolean> dateFieldsEnabledModel;
    private IModel<Boolean> dropdownFieldsEnabledModel;
    private IModel<Boolean> boolFieldsEnabledModel;
    private IModel<Boolean> numericFieldsEnabledModel;

    public UiFieldsDemo(final PageParameters parameters) {
        super();
        formData = new DemoFormObj();

        txtFieldsEnabledModel = LambdaModel.of(this::getTxtFieldsEnabled, this::setTxtFieldsEnabled);
        dateFieldsEnabledModel = LambdaModel.of(this::getDateFieldsEnabled, this::setDateFieldsEnabled);
        dropdownFieldsEnabledModel = LambdaModel.of(this::getDropdownFieldsEnabled, this::setDropdownFieldsEnabled);
        boolFieldsEnabledModel = LambdaModel.of(this::getBoolFieldsEnabled, this::setBoolFieldsEnabled);
        numericFieldsEnabledModel = LambdaModel.of(this::getNumericFieldsEnabled, this::setNumericFieldsEnabled);


        fieldsForm = new Form<Void>("fieldsForm");
        addOrReplace(fieldsForm);

        fieldsForm.addOrReplace(txtCont = (WebMarkupContainer) new WebMarkupContainer("txtCont").setOutputMarkupId(true));
        fieldsForm.addOrReplace(datesCont = (WebMarkupContainer) new WebMarkupContainer("datesCont").setOutputMarkupId(true));
        fieldsForm.addOrReplace(boolCont = (WebMarkupContainer) new WebMarkupContainer("boolCont").setOutputMarkupId(true));
        fieldsForm.addOrReplace(numericCont = (WebMarkupContainer) new WebMarkupContainer("numericCont").setOutputMarkupId(true));
        fieldsForm.addOrReplace(dropdownCont = (WebMarkupContainer) new WebMarkupContainer("dropdownCont").setOutputMarkupId(true));

        addBoolFields();
        addDateFields();
        addDropdownFields();
        addNumericFields();
        addTxtFields();

        fieldsForm.add(new SingleClickIndicatingAjaxButton("saveButton", fieldsForm, true, null) {
            private static final long serialVersionUID = 1L;
            @Override protected void onSubmit(AjaxRequestTarget target) {
                //System.out.println(formData.getTxtField());
            }
        });

        add(new UiFieldsBehavior());

        send(this, Broadcast.BREADTH, new InitPanelFieldsEvent(null));
    }

    private void addToggleBtn(String id, WebMarkupContainer cont, Runnable runnable) {
        fieldsForm.add(new SingleClickIndicatingAjaxButton(id, fieldsForm, true, null) {
            private static final long serialVersionUID = 1L;
            @Override protected void onSubmit(AjaxRequestTarget target) {
                runnable.run();
                send(cont, Broadcast.BREADTH, new InitPanelFieldsEvent(null));
                target.add(cont);
                UiFieldsBehavior.clientScripts(target);
            }
        });
    }

    private void addBoolFields() {
        addToggleBtn("toggleBoolFields", boolCont, () -> boolFieldsEnabledModel.setObject(!boolFieldsEnabled));

        boolCont.addOrReplace(new CheckBoxField(FieldArgs.Builder.of(
                "CheckBoxField", "CheckBoxField", LambdaModel.of(formData::getCheckBoxValue, formData::setCheckBoxValue)).propertiesId("CheckBoxField").isEnabledModel(boolFieldsEnabledModel).build()));
        boolCont.addOrReplace(new AjaxCheckBoxField(FieldArgs.Builder.of(
                "AjaxCheckBoxField", "AjaxCheckBoxField", LambdaModel.of(formData::getAjaxCheckBoxValue, formData::setAjaxCheckBoxValue))
                .propertiesId("AjaxCheckBoxField").isEnabledModel(boolFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxCheckBoxField onFieldChanged", BooleanUtils.toStringOnOff(formData.getAjaxCheckBoxValue()));
            }
        });

        boolCont.addOrReplace(new CheckBoxGroupField(FieldArgs.Builder.of(
                "CheckBoxGroupField", "CheckBoxGroupField", new ListModel<CheckBoxGroupModel>(getGroupChoices1())).propertiesId("CheckBoxGroupField").isEnabledModel(boolFieldsEnabledModel).build()));
        boolCont.addOrReplace(new AjaxCheckBoxGroupField(FieldArgs.Builder.of(
                "AjaxCheckBoxGroupField", "AjaxCheckBoxGroupField", new ListModel<CheckBoxGroupModel>(getGroupChoices2())).propertiesId("AjaxCheckBoxGroupField").isEnabledModel(boolFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                List<String> selected = new ArrayList<>();
                if(BooleanUtils.isTrue(formData.getGroup4Choice())) { selected.add("Choice 1"); }
                if(BooleanUtils.isTrue(formData.getGroup5Choice())) { selected.add("Choice 2"); }
                if(BooleanUtils.isTrue(formData.getGroup6Choice())) { selected.add("Choice 3"); }
                showToast(target, "AjaxCheckBoxGroupField onFieldChanged", StringUtils.join(selected, ", "));
            }
        });

        boolCont.addOrReplace(new RadioField(FieldArgs.Builder.of(
                "RadioField", "RadioField", LambdaModel.of(formData::getRadioValue, formData::setRadioValue)).propertiesId("RadioField").isEnabledModel(boolFieldsEnabledModel).build()));
        boolCont.addOrReplace(new AjaxRadioField(FieldArgs.Builder.of(
                "AjaxRadioField", "AjaxRadioField", LambdaModel.of(formData::getAjaxRadioValue, formData::setAjaxRadioValue)).propertiesId("AjaxRadioField")
                .isEnabledModel(boolFieldsEnabledModel).yesText("Public").noText("Private").build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxRadioField onFieldChanged", BooleanUtils.toStringOnOff(formData.getAjaxRadioValue()));
            }
        });

        boolCont.addOrReplace(new AjaxSwitchField(FieldArgs.Builder.of(
                "AjaxSwitchField", "AjaxSwitchField", LambdaModel.of(formData::getAjaxSwitchValue, formData::setAjaxSwitchValue)).propertiesId("AjaxSwitchField").isEnabledModel(boolFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxSwitchField onFieldChanged", BooleanUtils.toStringOnOff(formData.getAjaxSwitchValue()));
            }
        });

        boolCont.addOrReplace(new YesNoUnknownField(FieldArgs.Builder.of(
                "YesNoUnknownField", "YesNoUnknownField", LambdaModel.of(formData::getYesNoUnknownValue, formData::setYesNoUnknownValue)).propertiesId("YesNoUnknownField").isEnabledModel(boolFieldsEnabledModel).build()));
        boolCont.addOrReplace(new AjaxYesNoUnknownField(FieldArgs.Builder.of(
                "AjaxYesNoUnknownField", "AjaxYesNoUnknownField", LambdaModel.of(formData::getAjaxYesNoUnknownValue, formData::setAjaxYesNoUnknownValue)).propertiesId("AjaxYesNoUnknownField").isEnabledModel(boolFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxYesNoUnknownField onFieldChanged", formData.getAjaxYesNoUnknownValue() != null ? formData.getAjaxYesNoUnknownValue().getDescription() : "");
            }
        });

    }

    private void addDateFields() {
        addToggleBtn("toggleDateFields", datesCont, () -> dateFieldsEnabledModel.setObject(!dateFieldsEnabled));

        datesCont.addOrReplace(new AjaxLocalDateField(FieldArgs.Builder.of(
                "AjaxLocalDateField", "AjaxLocalDateField", LambdaModel.of(formData::getAjaxLocalDateValue, formData::setAjaxLocalDateValue)).propertiesId("AjaxLocalDateField").isEnabledModel(dateFieldsEnabledModel).r(2).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxLocalDateField onFieldChanged", DateUtils.format(formData.getAjaxLocalDateValue()));
            }
        });

        datesCont.addOrReplace(new AjaxTimeField(FieldArgs.Builder.of(
                "AjaxTimeField", "AjaxTimeField", LambdaModel.of(formData::getAjaxTimeValue, formData::setAjaxTimeValue)).propertiesId("AjaxTimeField").isEnabledModel(dateFieldsEnabledModel).r(2).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxTimeField onFieldChanged", DateUtils.format(formData.getAjaxTimeValue()));
            }
        });

        datesCont.addOrReplace(new LocalDateField(FieldArgs.Builder.of(
                "LocalDateField", "LocalDateField", LambdaModel.of(formData::getLocalDateValue, formData::setLocalDateValue)).isEnabledModel(dateFieldsEnabledModel).propertiesId("LocalDateField").r(2).build()));

        datesCont.addOrReplace(new LocalDateTimeField(FieldArgs.Builder.of(
                "LocalDateTimeField", "LocalDateTimeField", LambdaModel.of(formData::getLocalDateTimeValue, formData::setLocalDateTimeValue)).isEnabledModel(dateFieldsEnabledModel).propertiesId("LocalDateTimeField").r(3).build()));

        datesCont.addOrReplace(new MonthDayField(FieldArgs.Builder.of(
                "MonthDayField", "MonthDayField", LambdaModel.of(formData::getMonthDayValue, formData::setMonthDayValue)).isEnabledModel(dateFieldsEnabledModel).propertiesId("MonthDayField").r(2).build()));

        datesCont.addOrReplace(new TimeField(FieldArgs.Builder.of(
                "TimeField", "TimeField", LambdaModel.of(formData::getTimeValue, formData::setTimeValue)).isEnabledModel(dateFieldsEnabledModel).propertiesId("TimeField").r(2).build()));
    }

    private void addDropdownFields() {
        addToggleBtn("toggleDropdownFields", dropdownCont, () -> dropdownFieldsEnabledModel.setObject(!dropdownFieldsEnabled));

        dropdownCont.addOrReplace(new DropdownField<State>(FieldArgs.Builder.of(
                "DropdownField", "DropdownField", LambdaModel.of(formData::getDropdownValue, formData::setDropdownValue)).choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer())
                .propertiesId("DropdownField").isEnabledModel(dropdownFieldsEnabledModel).build()));

        dropdownCont.addOrReplace(new AjaxDropdownField<State>(FieldArgs.Builder.of(
                "AjaxDropdownField", "AjaxDropdownField", LambdaModel.of(formData::getAjaxDropdownValue, formData::setAjaxDropdownValue))
                .choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer()).isEnabledModel(dropdownFieldsEnabledModel).propertiesId("AjaxDropdownField").build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxDropdownField onFieldChanged", formData.getAjaxDropdownValue() != null ? formData.getAjaxDropdownValue().getAbbreviation() : "");
            }
        });

        dropdownCont.addOrReplace(new MultiDropdownField<State>(FieldArgs.Builder.of(
                "MultiDropdownField", "MultiDropdownField", LambdaModel.of(formData::getMultiDropdownValue, formData::setMultiDropdownValue)).choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer())
                .propertiesId("MultiDropdownField").isEnabledModel(dropdownFieldsEnabledModel).build()));

        dropdownCont.addOrReplace(new AjaxMultiDropdownField<State>(FieldArgs.Builder.of(
                "AjaxMultiDropdownField", "AjaxMultiDropdownField", LambdaModel.of(formData::getAjaxMultiDropdownValue, formData::setAjaxMultiDropdownValue)).choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer())
                .propertiesId("AjaxMultiDropdownField").isEnabledModel(dropdownFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxMultiDropdownField onFieldChanged", StringUtils.join(formData.getAjaxMultiDropdownValue(), ","));
            }
        });



        dropdownCont.addOrReplace(new GroupedDropdownField(FieldArgs.Builder.of(
                "GroupedDropdownField", "GroupedDropdownField", LambdaModel.of(formData::getGroupedDropdownValue, formData::setGroupedDropdownValue)).choiceList(getGroupedChoices()).r(5).isEnabledModel(dropdownFieldsEnabledModel)
                .propertiesId("GroupedDropdownField").build()));

        dropdownCont.addOrReplace(new AjaxGroupedDropdownField(FieldArgs.Builder.of(
                "AjaxGroupedDropdownField", "AjaxGroupedDropdownField", LambdaModel.of(formData::getAjaxGroupedDropdownValue, formData::setAjaxGroupedDropdownValue)).choiceList(getGroupedChoices()).r(5)
                .propertiesId("AjaxGroupedDropdownField").isEnabledModel(dropdownFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxGroupedDropdownField onFieldChanged", formData.getAjaxGroupedDropdownValue());
            }
        });

    }

    private void addNumericFields() {
        addToggleBtn("toggleNumericFields", numericCont, () -> numericFieldsEnabledModel.setObject(!numericFieldsEnabled));

        numericCont.addOrReplace(new MoneyField(FieldArgs.Builder.of(
                "MoneyField", "MoneyField", LambdaModel.of(formData::getMoneyValue, formData::setMoneyValue)).isEnabledModel(numericFieldsEnabledModel).propertiesId("MoneyField").build()));

        numericCont.addOrReplace(new AjaxMoneyField(FieldArgs.Builder.of(
                "AjaxMoneyField", "AjaxMoneyField", LambdaModel.of(formData::getAjaxMoneyValue, formData::setAjaxMoneyValue)).isEnabledModel(numericFieldsEnabledModel).propertiesId("AjaxMoneyField").build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxMoneyField onFieldChanged", formData.getAjaxMoneyValue() != null ? formData.getAjaxMoneyValue().toString() : "");
            }
        });

        numericCont.addOrReplace(new NumberSpinnerField<Integer>(FieldArgs.Builder.of(
                "NumberSpinnerField", "NumberSpinnerField", LambdaModel.of(formData::getNumberSpinnerValue, formData::setNumberSpinnerValue)).propertiesId("NumberSpinnerField").inputType(Integer.class).max(10).isEnabledModel(numericFieldsEnabledModel).build()));

        numericCont.addOrReplace(new AjaxNumberSpinnerField<Double>(FieldArgs.Builder.of(
                "AjaxNumberSpinnerField", "AjaxNumberSpinnerField", LambdaModel.of(formData::getAjaxNumberSpinnerValue, formData::setAjaxNumberSpinnerValue))
                .inputType(Double.class).step(0.5).max(5.0).isEnabledModel(numericFieldsEnabledModel).propertiesId("AjaxNumberSpinnerField").build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxNumberSpinnerField onFieldChanged", formData.getAjaxNumberSpinnerValue() != null ? formData.getAjaxNumberSpinnerValue().toString() : "");
            }
        });
    }

    private void addTxtFields() {
        addToggleBtn("toggleTxtFields", txtCont, () -> txtFieldsEnabledModel.setObject(!txtFieldsEnabled));

        txtCont.addOrReplace(new AjaxTextAreaWithCounterField(FieldArgs.Builder.of(
                "AjaxTextAreaWithCounterField", "AjaxTextAreaWithCounterField", LambdaModel.of(formData::getAjaxTextAreaWithCounterValue, formData::setAjaxTextAreaWithCounterValue))
                .maxLength(500).rows(5).propertiesId("AjaxTextAreaWithCounterField").isEnabledModel(txtFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxTextAreaWithCounterField onFieldChanged", formData.getAjaxTextAreaWithCounterValue());
            }
        });

        txtCont.addOrReplace(new AjaxTxtField<String>(FieldArgs.Builder.of(
                "AjaxTxtField", "AjaxTxtField", LambdaModel.of(formData::getAjaxTxtValue, formData::setAjaxTxtValue)).propertiesId("AjaxTxtField").isEnabledModel(txtFieldsEnabledModel).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxTxtField onFieldChanged", formData.getAjaxTxtValue());
            }
        });

        txtCont.addOrReplace(new LabelField<String>(FieldArgs.Builder.of(
                "LabelField", "LabelField", LambdaModel.of(() -> DateUtils.format(LocalDateTime.now()))).propertiesId("LabelField").isEnabledModel(txtFieldsEnabledModel).build()));

        txtCont.addOrReplace(new LinkField<UiFieldsDemo>(FieldArgs.Builder.of(
                "LinkField", "LinkField", null).pageClass(UiFieldsDemo.class).txtModel(Model.of("Demo page")).propertiesId("LinkField").isEnabledModel(txtFieldsEnabledModel).build()));

        txtCont.addOrReplace(new TextAreaField(FieldArgs.Builder.of(
                "TextAreaField", "TextAreaField", LambdaModel.of(formData::getTextAreaValue, formData::setTextAreaValue)).propertiesId("TextAreaField").isEnabledModel(txtFieldsEnabledModel).build()));

        txtCont.addOrReplace(new TextAreaWithCounterField(FieldArgs.Builder.of(
                "TextAreaWithCounterField", "TextAreaWithCounterField", LambdaModel.of(formData::getTextAreaWithCounterValue, formData::setTextAreaWithCounterValue))
                .maxLength(200).propertiesId("TextAreaWithCounterField").isEnabledModel(txtFieldsEnabledModel).build()));

        txtCont.addOrReplace(new TxtField<String>(FieldArgs.Builder.of(
                "TxtField", "TxtField", LambdaModel.of(formData::getTxtValue, formData::setTxtValue)).propertiesId("TxtField").isEnabledModel(txtFieldsEnabledModel).build()));

    }


    private void showToast(AjaxRequestTarget target, String heading, String txt) {
        target.appendJavaScript("showToastMsg('"+ StringEscapeUtils.escapeEcmaScript(heading)+"', '"+StringEscapeUtils.escapeEcmaScript(txt)+"');");
    }

    private MapModel<String, List<String>> getGroupedChoices() {
        Map<String, List<String>> choices = new HashMap();
        choices.put("Group A", List.of("choice 1", "choice 2", "choice 3", "choice 4"));
        choices.put("Group B", List.of("choice 5", "choice 6"));
        choices.put("Group C", List.of("choice 7", "choice 8", "choice 9"));
        return new MapModel<String, List<String>>(choices);
    }

    private List<CheckBoxGroupModel> getGroupChoices1() {
        List<CheckBoxGroupModel> choices = new ArrayList<>();
        choices.add(new CheckBoxGroupModel(LambdaModel.of(formData::getGroup1Choice, formData::setGroup1Choice), "Choice 1"));
        choices.add(new CheckBoxGroupModel(LambdaModel.of(formData::getGroup2Choice, formData::setGroup2Choice), "Choice 2"));
        choices.add(new CheckBoxGroupModel(LambdaModel.of(formData::getGroup3Choice, formData::setGroup3Choice), "Choice 3"));
        return choices;
    }
    private List<CheckBoxGroupModel> getGroupChoices2() {
        List<CheckBoxGroupModel> choices = new ArrayList<>();
        choices.add(new CheckBoxGroupModel(LambdaModel.of(formData::getGroup4Choice, formData::setGroup4Choice), "Choice 1"));
        choices.add(new CheckBoxGroupModel(LambdaModel.of(formData::getGroup5Choice, formData::setGroup5Choice), "Choice 2"));
        choices.add(new CheckBoxGroupModel(LambdaModel.of(formData::getGroup6Choice, formData::setGroup6Choice), "Choice 3"));
        return choices;
    }

    public Boolean getTxtFieldsEnabled() {
        return txtFieldsEnabled;
    }

    public void setTxtFieldsEnabled(Boolean txtFieldsEnabled) {
        this.txtFieldsEnabled = txtFieldsEnabled;
    }

    public Boolean getDateFieldsEnabled() {
        return dateFieldsEnabled;
    }

    public void setDateFieldsEnabled(Boolean dateFieldsEnabled) {
        this.dateFieldsEnabled = dateFieldsEnabled;
    }

    public Boolean getDropdownFieldsEnabled() {
        return dropdownFieldsEnabled;
    }

    public void setDropdownFieldsEnabled(Boolean dropdownFieldsEnabled) {
        this.dropdownFieldsEnabled = dropdownFieldsEnabled;
    }

    public Boolean getBoolFieldsEnabled() {
        return boolFieldsEnabled;
    }

    public void setBoolFieldsEnabled(Boolean boolFieldsEnabled) {
        this.boolFieldsEnabled = boolFieldsEnabled;
    }

    public Boolean getNumericFieldsEnabled() {
        return numericFieldsEnabled;
    }

    public void setNumericFieldsEnabled(Boolean numericFieldsEnabled) {
        this.numericFieldsEnabled = numericFieldsEnabled;
    }
}
