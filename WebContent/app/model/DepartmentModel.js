Ext.define('EmployeeApp.model.DepartmentModel', {
	extend: 'Ext.data.Model',
	

	 fields: [
	            'departmentName',
	            {name:'id',type:'int'}
	            
	            
	        ],
	        
	        idProperty:'id'
	       
});


