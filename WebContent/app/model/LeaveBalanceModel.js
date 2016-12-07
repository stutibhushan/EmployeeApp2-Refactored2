Ext.define('EmployeeApp.model.LeaveBalanceModel', {
	extend: 'Ext.data.Model',
	

	 fields: [
	            
	            'leaveType', 'credit', 'availed', 'finalBalance',
	            {name: 'employeeId', type: 'int'}
	         ]
	        
});

