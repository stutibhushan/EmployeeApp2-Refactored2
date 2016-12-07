Ext.define('EmployeeApp.store.EmployeeLeaveDisplayStore',{
	extend:'Ext.data.Store',
	model:'EmployeeApp.model.EmployeeLeaveDisplayModel',
	remoteGroup: true,
	pageSize: 10,
	//storeId:'employeeDetailStore',
	
	proxy: {
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        type: 'jsonp',
        method: 'get',
        url: 'http://localhost:8080/EmployeeApp2/EmployeeLeaveDisplay',
       
        reader: {
        	root: 'appliedLeaveDisplay',
            totalProperty: 'totalCount'
            
        }
        // sends single sort as multi parameter
        //simpleSortMode: false,
    },
    autoLoad: true
});