## Basic Usage

**WicketFieldsUI**

In your WebApplication.init() method, call WicketFieldsUI.init(this).  This will load the field properties from resources/field_props.properties.
The properties file is used to configure each field based on a unique propertyId passed to FieldArgs.

You can customize this to select a different properties file, which will be discussed below.

**UiFieldsBehavior**

Add the UiFieldsBehavior to your WebPage which will add all the necessary resources.  You can override this class if you need different versions of the js,css files.  Or you can define your own class.

It adds several js & css files that are neccessary for the fields to work. 

**FieldArgs**

FieldArgs is a builder pattern style class to define a consistent syntax for all fields.  Each field has a constructor that accepts a FieldArgs object which contains all the information necessary to instantiate that field.

**InitPanelFieldsEvent**

Fields that are added to a WebPage do not initialize immediately.  Once you are done adding all of your fields, use Wicket events to broadcast InitPanelFieldsEvent to the page which will notify the fields to init themselves.


## field_props.properties

To demonstrate usage of field_props, let's use as an example:

new TxtField<String>(FieldArgs.Builder.of("userFirstName", "First Name", null).propertiesId("ufn").build())

Here we set the unique propertyId "ufn" for this field, we can now define these properties:

1. .lbl - Customize the field label, ex: ufn.lbl=Custom text
2. .hidden - Hide or show the field completly, ex: ufn.hidden=1
3. .nullChoice - The value return by getNullValidDisplayValue() for DropdownChoice fields, ex: ufn.nullChoice=Select something

### Customizing properties for different environments

FieldPropsResourceLoader will by default load resources/field_props.properties.

If a JVM environment variable with the name of "env" exists, FieldPropsResourceLoader will than attempt to load resources/field_props_{ENV}.properties and merge it with the first file.

In this way you can define defaults values, and then override them in environment-specific files.