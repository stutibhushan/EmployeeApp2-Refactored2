Ext.define('EmployeeApp.store.LeaveTypeComboBoxStore', {
	extend:'Ext.data.Store',
	alias:'widget.leaveTypeComboBoxStore',
	id:'leaveTypeComboBoxStore',
    fields: ['leaveType'],
    data : [
        { "leaveType":"CASUAL\SICK LEAVE"},
        { "leaveType":"EARNED LEAVE"},
        { "leaveType":"MATERNITY LEAVE"},
        { "leaveType":"VOLUNTARY DAY"}
    ]
});