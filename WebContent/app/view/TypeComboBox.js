var e=Ext.create('EmployeeApp.store.EmployeeTypeStore');



Ext.define('EmployeeApp.view.TypeComboBox',{
	
	extend:'Ext.form.ComboBox',
	
	fieldLabel: 'Type',
	
	queryMode: 'remote',
	
	store:e,
	
	alias:'widget.typeComboBox',
	
	id:'comboBox',
	
	
	    queryMode: 'remote',
	    displayField: 'typeName',
	    valueField: 'id',
	   
	    
	    listeners:{
	         
	         'select': function(combo)
	         {
	        	 console.log('EmployeeType',combo.getValue());
	        	 var employeeTypeId=combo.getValue();
	        	 var proxy=Ext.getCmp('display-view').getStore().getProxy();
	             proxy.setExtraParam('id', employeeTypeId);
	             Ext.getCmp('display-view').getStore().load();
	        	
	         }

	    }

	
	
	
	
});

