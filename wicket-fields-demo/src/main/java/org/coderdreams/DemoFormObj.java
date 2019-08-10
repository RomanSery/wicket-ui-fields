package org.coderdreams;


import java.io.Serializable;

public class DemoFormObj implements Serializable {
    private static final long serialVersionUID = 1L;

    private String txtField;



    public String getTxtField() {
        return txtField;
    }

    public void setTxtField(String txtField) {
        this.txtField = txtField;
    }
}
