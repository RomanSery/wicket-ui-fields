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
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
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
import org.coderdreams.wicketfields.fields.dates.LocalDateRangeField;
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

    public UiFieldsDemo(final PageParameters parameters) {
        super();
        formData = new DemoFormObj();

        fieldsForm = new Form<Void>("fieldsForm");
        addOrReplace(fieldsForm);

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


    private void addBoolFields() {
        fieldsForm.addOrReplace(new CheckBoxField(FieldArgs.Builder.of(
                "CheckBoxField", "CheckBoxField", LambdaModel.of(formData::getCheckBoxValue, formData::setCheckBoxValue)).build()));
        fieldsForm.addOrReplace(new AjaxCheckBoxField(FieldArgs.Builder.of(
                "AjaxCheckBoxField", "AjaxCheckBoxField", LambdaModel.of(formData::getAjaxCheckBoxValue, formData::setAjaxCheckBoxValue)).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxCheckBoxField onFieldChanged", BooleanUtils.toStringOnOff(formData.getAjaxCheckBoxValue()));
            }
        });

        fieldsForm.addOrReplace(new CheckBoxGroupField(FieldArgs.Builder.of(
                "CheckBoxGroupField", "CheckBoxGroupField", new ListModel<CheckBoxGroupModel>(getGroupChoices1())).build()));
        fieldsForm.addOrReplace(new AjaxCheckBoxGroupField(FieldArgs.Builder.of(
                "AjaxCheckBoxGroupField", "AjaxCheckBoxGroupField", new ListModel<CheckBoxGroupModel>(getGroupChoices2())).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                List<String> selected = new ArrayList<>();
                if(BooleanUtils.isTrue(formData.getGroup4Choice())) { selected.add("Choice 1"); }
                if(BooleanUtils.isTrue(formData.getGroup5Choice())) { selected.add("Choice 2"); }
                if(BooleanUtils.isTrue(formData.getGroup6Choice())) { selected.add("Choice 3"); }
                showToast(target, "AjaxCheckBoxGroupField onFieldChanged", StringUtils.join(selected, ", "));
            }
        });

        fieldsForm.addOrReplace(new RadioField(FieldArgs.Builder.of(
                "RadioField", "RadioField", LambdaModel.of(formData::getRadioValue, formData::setRadioValue)).build()));
        fieldsForm.addOrReplace(new AjaxRadioField(FieldArgs.Builder.of(
                "AjaxRadioField", "AjaxRadioField", LambdaModel.of(formData::getAjaxRadioValue, formData::setAjaxRadioValue)).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxRadioField onFieldChanged", BooleanUtils.toStringOnOff(formData.getAjaxRadioValue()));
            }
        });

        fieldsForm.addOrReplace(new AjaxSwitchField(FieldArgs.Builder.of(
                "AjaxSwitchField", "AjaxSwitchField", LambdaModel.of(formData::getAjaxSwitchValue, formData::setAjaxSwitchValue)).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxSwitchField onFieldChanged", BooleanUtils.toStringOnOff(formData.getAjaxSwitchValue()));
            }
        });

        fieldsForm.addOrReplace(new YesNoUnknownField(FieldArgs.Builder.of(
                "YesNoUnknownField", "YesNoUnknownField", LambdaModel.of(formData::getYesNoUnknownValue, formData::setYesNoUnknownValue)).build()));
        fieldsForm.addOrReplace(new AjaxYesNoUnknownField(FieldArgs.Builder.of(
                "AjaxYesNoUnknownField", "AjaxYesNoUnknownField", LambdaModel.of(formData::getAjaxYesNoUnknownValue, formData::setAjaxYesNoUnknownValue)).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxYesNoUnknownField onFieldChanged", formData.getAjaxYesNoUnknownValue() != null ? formData.getAjaxYesNoUnknownValue().getDescription() : "");
            }
        });

    }

    private void addDateFields() {

        fieldsForm.addOrReplace(new AjaxLocalDateField(FieldArgs.Builder.of(
                "AjaxLocalDateField", "AjaxLocalDateField", LambdaModel.of(formData::getAjaxLocalDateValue, formData::setAjaxLocalDateValue)).build()));

        fieldsForm.addOrReplace(new AjaxTimeField(FieldArgs.Builder.of(
                "AjaxTimeField", "AjaxTimeField", LambdaModel.of(formData::getAjaxTimeValue, formData::setAjaxTimeValue)).build()));

        fieldsForm.addOrReplace(new LocalDateField(FieldArgs.Builder.of(
                "LocalDateField", "LocalDateField", LambdaModel.of(formData::getLocalDateValue, formData::setLocalDateValue)).build()));

        fieldsForm.addOrReplace(new LocalDateRangeField(FieldArgs.Builder.of(
                "LocalDateRangeField", "LocalDateRangeField", LambdaModel.of(formData::getLocalDateRangeValue, formData::setLocalDateRangeValue)).build()));

        fieldsForm.addOrReplace(new LocalDateTimeField(FieldArgs.Builder.of(
                "LocalDateTimeField", "LocalDateTimeField", LambdaModel.of(formData::getLocalDateTimeValue, formData::setLocalDateTimeValue)).build()));

        fieldsForm.addOrReplace(new MonthDayField(FieldArgs.Builder.of(
                "MonthDayField", "MonthDayField", LambdaModel.of(formData::getMonthDayValue, formData::setMonthDayValue)).build()));

        fieldsForm.addOrReplace(new TimeField(FieldArgs.Builder.of(
                "TimeField", "TimeField", LambdaModel.of(formData::getTimeValue, formData::setTimeValue)).build()));
    }

    private void addDropdownFields() {

        fieldsForm.addOrReplace(new DropdownField<State>(FieldArgs.Builder.of(
                "DropdownField", "DropdownField", LambdaModel.of(formData::getDropdownValue, formData::setDropdownValue)).choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer()).build()));

        fieldsForm.addOrReplace(new AjaxDropdownField<State>(FieldArgs.Builder.of(
                "AjaxDropdownField", "AjaxDropdownField", LambdaModel.of(formData::getAjaxDropdownValue, formData::setAjaxDropdownValue))
                .choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer()).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxDropdownField onFieldChanged", formData.getAjaxDropdownValue() != null ? formData.getAjaxDropdownValue().getAbbreviation() : "");
            }
        });

        fieldsForm.addOrReplace(new MultiDropdownField<State>(FieldArgs.Builder.of(
                "MultiDropdownField", "MultiDropdownField", LambdaModel.of(formData::getMultiDropdownValue, formData::setMultiDropdownValue)).choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer()).build()));

        fieldsForm.addOrReplace(new AjaxMultiDropdownField<State>(FieldArgs.Builder.of(
                "AjaxMultiDropdownField", "AjaxMultiDropdownField", LambdaModel.of(formData::getAjaxMultiDropdownValue, formData::setAjaxMultiDropdownValue)).choiceList(new ListModel(State.VALUES)).cr(new StateChoiceRenderer()).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxMultiDropdownField onFieldChanged", StringUtils.join(formData.getAjaxMultiDropdownValue(), ","));
            }
        });



        fieldsForm.addOrReplace(new GroupedDropdownField(FieldArgs.Builder.of(
                "GroupedDropdownField", "GroupedDropdownField", LambdaModel.of(formData::getGroupedDropdownValue, formData::setGroupedDropdownValue)).choiceList(getGroupedChoices()).r(5).build()));

        fieldsForm.addOrReplace(new AjaxGroupedDropdownField(FieldArgs.Builder.of(
                "AjaxGroupedDropdownField", "AjaxGroupedDropdownField", LambdaModel.of(formData::getAjaxGroupedDropdownValue, formData::setAjaxGroupedDropdownValue)).choiceList(getGroupedChoices()).r(5).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxGroupedDropdownField onFieldChanged", formData.getAjaxGroupedDropdownValue());
            }
        });


    }

    private void addNumericFields() {

        fieldsForm.addOrReplace(new MoneyField(FieldArgs.Builder.of(
                "MoneyField", "MoneyField", LambdaModel.of(formData::getMoneyValue, formData::setMoneyValue)).build()));

        fieldsForm.addOrReplace(new AjaxMoneyField(FieldArgs.Builder.of(
                "AjaxMoneyField", "AjaxMoneyField", LambdaModel.of(formData::getAjaxMoneyValue, formData::setAjaxMoneyValue)).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxMoneyField onFieldChanged", formData.getAjaxMoneyValue() != null ? formData.getAjaxMoneyValue().toString() : "");
            }
        });

        fieldsForm.addOrReplace(new NumberSpinnerField<Integer>(FieldArgs.Builder.of(
                "NumberSpinnerField", "NumberSpinnerField", LambdaModel.of(formData::getNumberSpinnerValue, formData::setNumberSpinnerValue)).inputType(Integer.class).max(10).build()));

        fieldsForm.addOrReplace(new AjaxNumberSpinnerField<Double>(FieldArgs.Builder.of(
                "AjaxNumberSpinnerField", "AjaxNumberSpinnerField", LambdaModel.of(formData::getAjaxNumberSpinnerValue, formData::setAjaxNumberSpinnerValue))
                .inputType(Double.class).step(0.5).max(5.0).build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxNumberSpinnerField onFieldChanged", formData.getAjaxNumberSpinnerValue() != null ? formData.getAjaxNumberSpinnerValue().toString() : "");
            }
        });
    }

    private void addTxtFields() {

        fieldsForm.addOrReplace(new AjaxTextAreaWithCounterField(FieldArgs.Builder.of(
                "AjaxTextAreaWithCounterField", "AjaxTextAreaWithCounterField", LambdaModel.of(formData::getAjaxTextAreaWithCounterValue, formData::setAjaxTextAreaWithCounterValue))
                .maxLength(500).rows(5).propertiesId("AjaxTextAreaWithCounterField").build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxTextAreaWithCounterField onFieldChanged", formData.getAjaxTextAreaWithCounterValue());
            }
        });

        fieldsForm.addOrReplace(new AjaxTxtField<String>(FieldArgs.Builder.of(
                "AjaxTxtField", "AjaxTxtField", LambdaModel.of(formData::getAjaxTxtValue, formData::setAjaxTxtValue)).propertiesId("AjaxTxtField").build()) {
            @Override
            public void onFieldChanged(AjaxRequestTarget target) {
                showToast(target, "AjaxTxtField onFieldChanged", formData.getAjaxTxtValue());
            }
        });

        fieldsForm.addOrReplace(new LabelField<String>(FieldArgs.Builder.of(
                "LabelField", "LabelField", LambdaModel.of(() -> DateUtils.format(LocalDateTime.now()))).propertiesId("LabelField").build()));

        fieldsForm.addOrReplace(new LinkField<UiFieldsDemo>(FieldArgs.Builder.of(
                "LinkField", "LinkField", null).pageClass(UiFieldsDemo.class).txtModel(Model.of("Demo page")).propertiesId("LinkField").build()));

        fieldsForm.addOrReplace(new TextAreaField(FieldArgs.Builder.of(
                "TextAreaField", "TextAreaField", LambdaModel.of(formData::getTextAreaValue, formData::setTextAreaValue)).propertiesId("TextAreaField").build()));

        fieldsForm.addOrReplace(new TextAreaWithCounterField(FieldArgs.Builder.of(
                "TextAreaWithCounterField", "TextAreaWithCounterField", LambdaModel.of(formData::getTextAreaWithCounterValue, formData::setTextAreaWithCounterValue))
                .maxLength(200).propertiesId("TextAreaWithCounterField").build()));

        fieldsForm.addOrReplace(new TxtField<String>(FieldArgs.Builder.of(
                "TxtField", "TxtField", LambdaModel.of(formData::getTxtValue, formData::setTxtValue)).propertiesId("TxtField").build()));

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
}
