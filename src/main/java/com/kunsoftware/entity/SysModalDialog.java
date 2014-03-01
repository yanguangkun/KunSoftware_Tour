package com.kunsoftware.entity;

public class SysModalDialog {
    private Integer id;

    private String code;

    private String name;

    private String dialogDescribe;

    private String dialogSql;

    private String dialogCondition;

    private String showColumn;

    private String showColumnWidth;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDialogDescribe() {
        return dialogDescribe;
    }

    public void setDialogDescribe(String dialogDescribe) {
        this.dialogDescribe = dialogDescribe;
    }

    public String getDialogSql() {
        return dialogSql;
    }

    public void setDialogSql(String dialogSql) {
        this.dialogSql = dialogSql;
    }

    public String getDialogCondition() {
        return dialogCondition;
    }

    public void setDialogCondition(String dialogCondition) {
        this.dialogCondition = dialogCondition;
    }

    public String getShowColumn() {
        return showColumn;
    }

    public void setShowColumn(String showColumn) {
        this.showColumn = showColumn;
    }

    public String getShowColumnWidth() {
        return showColumnWidth;
    }

    public void setShowColumnWidth(String showColumnWidth) {
        this.showColumnWidth = showColumnWidth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}