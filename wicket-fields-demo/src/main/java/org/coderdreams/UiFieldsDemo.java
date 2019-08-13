package org.coderdreams;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.resources.UiFieldsBehavior;
import org.coderdreams.wicketfields.event.InitPanelFieldsEvent;
import org.coderdreams.wicketfields.fields.bool.AjaxCheckBoxField;
import org.coderdreams.wicketfields.fields.bool.AjaxCheckBoxGroupField;
import org.coderdreams.wicketfields.fields.bool.AjaxCheckBoxReverseField;
import org.coderdreams.wicketfields.fields.bool.AjaxRadioField;
import org.coderdreams.wicketfields.fields.bool.AjaxSwitchField;
import org.coderdreams.wicketfields.fields.bool.AjaxYesNoUnknownField;
import org.coderdreams.wicketfields.fields.bool.CheckBoxField;
import org.coderdreams.wicketfields.fields.bool.CheckBoxGroupField;
import org.coderdreams.wicketfields.fields.bool.CheckBoxReverseField;
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

        fieldsForm.add(new SingleClickIndicatingAjaxButton("saveButton", fieldsForm, true) {
            private static final long serialVersionUID = 1L;
            @Override protected boolean submit(AjaxRequestTarget target) {
                //System.out.println(formData.getTxtField());
                return true;
            }
        });

        add(new UiFieldsBehavior());

        send(this, Broadcast.BREADTH, new InitPanelFieldsEvent(null));
    }

    private void addBoolFields() {
        fieldsForm.addOrReplace(new AjaxCheckBoxField(FieldArgs.Builder.of(
                "AjaxCheckBoxField", "AjaxCheckBoxField", LambdaModel.of(formData::getAjaxCheckBoxValue, formData::setAjaxCheckBoxValue)).build()));

        fieldsForm.addOrReplace(new AjaxCheckBoxGroupField(FieldArgs.Builder.of(
                "AjaxCheckBoxGroupField", "AjaxCheckBoxGroupField", LambdaModel.of(formData::getAjaxCheckBoxGroupValue, formData::setAjaxCheckBoxGroupValue)).build()));

        fieldsForm.addOrReplace(new AjaxCheckBoxReverseField(FieldArgs.Builder.of(
                "AjaxCheckBoxReverseField", "AjaxCheckBoxReverseField", LambdaModel.of(formData::getAjaxCheckBoxReverseValue, formData::setAjaxCheckBoxReverseValue)).build()));

        fieldsForm.addOrReplace(new AjaxRadioField(FieldArgs.Builder.of(
                "AjaxRadioField", "AjaxRadioField", LambdaModel.of(formData::getAjaxRadioValue, formData::setAjaxRadioValue)).build()));

        fieldsForm.addOrReplace(new AjaxSwitchField(FieldArgs.Builder.of(
                "AjaxSwitchField", "AjaxSwitchField", LambdaModel.of(formData::getAjaxSwitchValue, formData::setAjaxSwitchValue)).build()));

        fieldsForm.addOrReplace(new AjaxYesNoUnknownField(FieldArgs.Builder.of(
                "AjaxYesNoUnknownField", "AjaxYesNoUnknownField", LambdaModel.of(formData::getAjaxYesNoUnknownValue, formData::setAjaxYesNoUnknownValue)).build()));

        fieldsForm.addOrReplace(new CheckBoxField(FieldArgs.Builder.of(
                "CheckBoxField", "CheckBoxField", LambdaModel.of(formData::getCheckBoxValue, formData::setCheckBoxValue)).build()));

        fieldsForm.addOrReplace(new CheckBoxGroupField(FieldArgs.Builder.of(
                "CheckBoxGroupField", "CheckBoxGroupField", LambdaModel.of(formData::getCheckBoxGroupValue, formData::setCheckBoxGroupValue)).build()));

        fieldsForm.addOrReplace(new CheckBoxReverseField(FieldArgs.Builder.of(
                "CheckBoxReverseField", "CheckBoxReverseField", LambdaModel.of(formData::getCheckBoxReverseValue, formData::setCheckBoxReverseValue)).build()));

        fieldsForm.addOrReplace(new RadioField(FieldArgs.Builder.of(
                "RadioField", "RadioField", LambdaModel.of(formData::getRadioValue, formData::setRadioValue)).build()));

        fieldsForm.addOrReplace(new YesNoUnknownField(FieldArgs.Builder.of(
                "YesNoUnknownField", "YesNoUnknownField", LambdaModel.of(formData::getYesNoUnknownValue, formData::setYesNoUnknownValue)).build()));
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

        fieldsForm.addOrReplace(new AjaxDropdownField(FieldArgs.Builder.of(
                "AjaxDropdownField", "AjaxDropdownField", LambdaModel.of(formData::getAjaxDropdownValue, formData::setAjaxDropdownValue)).choiceList(new ListModel(List.of("choice1", "choice2", "choice3"))).build()));

        fieldsForm.addOrReplace(new AjaxGroupedDropdownField(FieldArgs.Builder.of(
                "AjaxGroupedDropdownField", "AjaxGroupedDropdownField", LambdaModel.of(formData::getAjaxGroupedDropdownValue, formData::setAjaxGroupedDropdownValue)).build()));

        fieldsForm.addOrReplace(new AjaxMultiDropdownField(FieldArgs.Builder.of(
                "AjaxMultiDropdownField", "AjaxMultiDropdownField", LambdaModel.of(formData::getAjaxMultiDropdownValue, formData::setAjaxMultiDropdownValue)).choiceList(new ListModel(List.of("choice1", "choice2", "choice3"))).build()));

        fieldsForm.addOrReplace(new DropdownField(FieldArgs.Builder.of(
                "DropdownField", "DropdownField", LambdaModel.of(formData::getDropdownValue, formData::setDropdownValue)).choiceList(new ListModel(List.of("choice1", "choice2", "choice3"))).build()));

        fieldsForm.addOrReplace(new GroupedDropdownField(FieldArgs.Builder.of(
                "GroupedDropdownField", "GroupedDropdownField", LambdaModel.of(formData::getGroupedDropdownValue, formData::setGroupedDropdownValue)).build()));

        fieldsForm.addOrReplace(new MultiDropdownField(FieldArgs.Builder.of(
                "MultiDropdownField", "MultiDropdownField", LambdaModel.of(formData::getMultiDropdownValue, formData::setMultiDropdownValue)).choiceList(new ListModel(List.of("choice1", "choice2", "choice3"))).build()));
    }

    private void addNumericFields() {

        fieldsForm.addOrReplace(new AjaxMoneyField(FieldArgs.Builder.of(
                "AjaxMoneyField", "AjaxMoneyField", LambdaModel.of(formData::getAjaxMoneyValue, formData::setAjaxMoneyValue)).build()));

        fieldsForm.addOrReplace(new AjaxNumberSpinnerField(FieldArgs.Builder.of(
                "AjaxNumberSpinnerField", "AjaxNumberSpinnerField", LambdaModel.of(formData::getAjaxNumberSpinnerValue, formData::setAjaxNumberSpinnerValue)).build()));

        fieldsForm.addOrReplace(new MoneyField(FieldArgs.Builder.of(
                "MoneyField", "MoneyField", LambdaModel.of(formData::getMoneyValue, formData::setMoneyValue)).build()));

        fieldsForm.addOrReplace(new NumberSpinnerField(FieldArgs.Builder.of(
                "NumberSpinnerField", "NumberSpinnerField", LambdaModel.of(formData::getNumberSpinnerValue, formData::setNumberSpinnerValue)).build()));

    }

    private void addTxtFields() {

        fieldsForm.addOrReplace(new AjaxTextAreaWithCounterField(FieldArgs.Builder.of(
                "AjaxTextAreaWithCounterField", "AjaxTextAreaWithCounterField", LambdaModel.of(formData::getAjaxTextAreaWithCounterValue, formData::setAjaxTextAreaWithCounterValue)).build()));

        fieldsForm.addOrReplace(new AjaxTxtField<String>(FieldArgs.Builder.of(
                "AjaxTxtField", "AjaxTxtField", LambdaModel.of(formData::getAjaxTxtValue, formData::setAjaxTxtValue)).build()));

        fieldsForm.addOrReplace(new LabelField(FieldArgs.Builder.of(
                "LabelField", "LabelField", null).build()));

        fieldsForm.addOrReplace(new LinkField(FieldArgs.Builder.of(
                "LinkField", "LinkField", null).pageClass(UiFieldsDemo.class).build()));

        fieldsForm.addOrReplace(new TextAreaField(FieldArgs.Builder.of(
                "TextAreaField", "TextAreaField", LambdaModel.of(formData::getTextAreaValue, formData::setTextAreaValue)).build()));

        fieldsForm.addOrReplace(new TextAreaWithCounterField(FieldArgs.Builder.of(
                "TextAreaWithCounterField", "TextAreaWithCounterField", LambdaModel.of(formData::getTextAreaWithCounterValue, formData::setTextAreaWithCounterValue)).build()));

        fieldsForm.addOrReplace(new TxtField<String>(FieldArgs.Builder.of(
                "TxtField", "TxtField", LambdaModel.of(formData::getTxtValue, formData::setTxtValue)).build()));

    }
}
