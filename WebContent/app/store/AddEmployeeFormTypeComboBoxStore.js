Ext.define('EmployeeApp.store.AddEmployeeFormTypeComboBoxStore', {
	extend:'Ext.data.Store',
	alias:'widget.typeComboBoxStore',
	id:'typeComboBoxStore',
    fields: ['id', 'typeName'],
    data : [
        {"id":1, "typeName":"PERMANENT"},
        {"id":2, "typeName":"CONTRACT"},
        {"id":3, "typeName":"INTERN"}
    ]
});