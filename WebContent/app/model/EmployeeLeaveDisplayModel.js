Ext.define('EmployeeApp.model.EmployeeLeaveDisplayModel', {
	extend: 'Ext.data.Model',
	

	 fields: [
	            
	            'leaveType','status',
	            {name: 'employeeId', type: 'int'},
	            {name: 'date', type:'date'}
	          
	         ]
	        
});