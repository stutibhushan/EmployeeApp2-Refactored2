Ext.define('EmployeeApp.model.EmployeeVOModel', {
	extend: 'Ext.data.Model',
	

	 fields: [
	            'name','address','city','state','country','salary','description', 'profileCompleteness','type',
	            {name: 'id', type: 'int'},
	            {name: 'startDate', type:'date'},
	            {name:'endDate', type:'date'},
	            
	            
	        ],
	        idProperty: 'id'
});


