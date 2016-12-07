Ext.define('EmployeeApp.store.DepartmentStore',{
	extend:'Ext.data.Store',
	model:'EmployeeApp.model.DepartmentModel',
	remoteGroup: true,
	pageSize: 10,
	//storeId:'employeeDetailStore',
	
	proxy: {
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        type: 'jsonp',
        url: 'http://localhost:8080/EmployeeApp2/departmentList',
        reader: {
            root: 'departmentList'
            
        }
        // sends single sort as multi parameter
        //simpleSortMode: false,
    },
    autoLoad: false
});