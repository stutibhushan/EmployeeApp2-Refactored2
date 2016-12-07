Ext.define('EmployeeApp.view.AddEmployeeFormParentPanel',{
	
	extend:'Ext.panel.Panel',
	id: 'addEmployeeFormParentPanel',
	alias:'widget.addEmployeeFormParentPanel',
	autoHeight: true,
	border: false,
	//title: 'Employee Data',



	//margins: '5 5 0 0',
	items: [
	        {
	        	xtype:'addEmployeeFormPanel'
	        },
	        {
	        	xtype:'buttonToolbar'
	        }
	]
	
	
	
})