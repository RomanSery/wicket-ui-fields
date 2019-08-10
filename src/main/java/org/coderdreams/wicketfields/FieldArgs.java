package org.coderdreams.wicketfields;


import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public final class FieldArgs {

    private final String id;
    private final String fieldLabel;
    private final String propertiesId;
    private final int l;
    private final int r;
    private final IModel model;
    private final IModel choiceList;
    private final IModel<String> txtModel;
    private final IChoiceRenderer cr;
    private final Class inputType;
    private final Class pageClass;
    private final Number min;
    private final Number max;
    private final String yesText;
    private final String noText;
    private final String placeholder;
    private final int rows;
    private final int maxLength;
    private final String markupId;
    private final boolean forceDisable;
    private final boolean dontDisplayClearOpt;

    private FieldArgs(Builder builder) {
        this.id = builder.id;
        this.fieldLabel = builder.fieldLabel;
        this.propertiesId = builder.propertiesId;
        this.l = builder.l;
        this.r = builder.r;
        this.model = builder.model;
        this.cr = builder.cr;
        this.inputType = builder.inputType;
        this.min = builder.min;
        this.max = builder.max;
        this.choiceList = builder.choiceList;
        this.yesText = builder.yesText;
        this.noText = builder.noText;
        this.txtModel = builder.txtModel;
        this.rows = builder.rows;
        this.maxLength = builder.maxLength;
        this.placeholder = builder.placeholder;
        this.pageClass = builder.pageClass;
        this.markupId = builder.markupId;
        this.forceDisable = builder.forceDisable;
        this.dontDisplayClearOpt = builder.dontDisplayClearOpt;
    }

    public String getId() { return id; }
    public String getFieldLabel() { return fieldLabel; }
    public String getPropertiesId() { return propertiesId; }
    public int getL() { return l; }
    public int getR() { return r; }
    public IModel getModel() { return model; }
    public IChoiceRenderer getCr() { return cr; }
    public Class getInputType() { return inputType; }
    public Number getMin() { return min; }
    public Number getMax() { return max; }
    public IModel getChoiceList() { return choiceList; }
    public String getYesText() { return yesText; }
    public String getPlaceholder() { return placeholder; }
    public String getNoText() { return noText; }
    public IModel<String> getTxtModel() { return txtModel; }
    public int getRows() { return rows; }
    public int getMaxLength() { return maxLength; }
    public Class getPageClass() { return pageClass; }
    public String getMarkupId() { return markupId; }
    public boolean isForceDisable() { return forceDisable; }
    public boolean isDontDisplayClearOpt() { return dontDisplayClearOpt; }

    public static final class Builder {
        private final String id;
        private final String fieldLabel;
        private IModel model;
        private String propertiesId;
        private int l = BaseUiField.DEF_L;
        private int r = BaseUiField.DEF_R;
        private IChoiceRenderer cr;
        private Class inputType;
        private Class pageClass;
        private IModel<String> txtModel;
        private Number min;
        private Number max;
        private IModel choiceList;
        private String yesText;
        private String noText;
        private int rows = 3;
        private int maxLength;
        private String placeholder;
        private String markupId;
        private boolean forceDisable;
        private boolean dontDisplayClearOpt;

        private Builder(String id, String fieldLabel, IModel model) {
            this.id = id;
            this.fieldLabel = fieldLabel;
            this.model = model;
        }

        public static Builder of(String id, String fieldLabel, IModel model) {
            return new Builder(id, fieldLabel, model);
        }

        public Builder model(IModel model) {
            this.model = model;
            return this;
        }
        public Builder propertiesId(String propertiesId) {
            this.propertiesId = propertiesId;
            return this;
        }
        public Builder l(int l) {
            this.l = l;
            return this;
        }
        public Builder r(int r) {
            this.r = r;
            return this;
        }
        public Builder cr(IChoiceRenderer cr) {
            this.cr = cr;
            return this;
        }
        public Builder inputType(Class inputType) {
            this.inputType = inputType;
            return this;
        }
        public Builder min(Number min) {
            this.min = min;
            return this;
        }
        public Builder max(Number max) {
            this.max = max;
            return this;
        }
        public Builder choiceList(IModel choiceList) {
            this.choiceList = choiceList;
            return this;
        }
        public Builder yesText(String yesText) {
            this.yesText = yesText;
            return this;
        }
        public Builder placeholder(String placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder noText(String noText) {
            this.noText = noText;
            return this;
        }
        public Builder txtModel(IModel<String> txtModel) {
            this.txtModel = txtModel;
            return this;
        }
        public Builder rows(int rows) {
            this.rows = rows;
            return this;
        }
        public Builder maxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }
        public Builder pageClass(Class pageClass) {
            this.pageClass = pageClass;
            return this;
        }
        public Builder markupId(String markupId) {
            this.markupId = markupId;
            return this;
        }
        public Builder forceDisable(boolean forceDisable) {
            this.forceDisable = forceDisable;
            return this;
        }
        public Builder dontDisplayClearOpt(boolean dontDisplayClearOpt) {
            this.dontDisplayClearOpt = dontDisplayClearOpt;
            return this;
        }


        public FieldArgs build() {
            return new FieldArgs(this);
        }

    }
	
}
