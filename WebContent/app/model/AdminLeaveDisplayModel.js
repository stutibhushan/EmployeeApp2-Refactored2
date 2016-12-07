Ext.define('EmployeeApp.model.AdminLeaveDisplayModel', {
	extend: 'Ext.data.Model',
	

	 fields: [
	           'employeeId', 'leaveType', 'status',
	            
	            {name: 'date', type:'date'}
	           
	            
	        ]
	        
});
