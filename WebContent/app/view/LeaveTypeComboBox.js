var store = Ext.create('EmployeeApp.store.LeaveTypeComboBoxStore');

// Create the combo box, attached to the states data store
Ext.define('EmployeeApp.view.LeaveTypeComboBox', {
	extend:'Ext.form.ComboBox',
    fieldLabel: 'Leave Type',
    allowBlank:false,
    alias:'widget.leaveTypeComboBox',
    id:'leaveTypeComboBox',
    store: store,
    queryMode: 'local',
    displayField: 'leaveType',
    valueField: 'leaveType',
  });