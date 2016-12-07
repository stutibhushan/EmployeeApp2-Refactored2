Ext.define('EmployeeApp.view.EmployeeLeaveDisplayGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.employeeLeaveDisplayGrid',
	id: 'employeeLeaveDisplayGrid',
	store: 'EmployeeLeaveDisplayStore',
	//multiSelect: true,
	
	
    //title: 'Active Promotions',
	
	
    columns:[{
        // id assigned so we can apply custom css (e.g. .x-grid-cell-topic b { color:#333 })
        // TODO: This poses an issue in subclasses of Grid now because Headers are now Components
        // therefore the id will be registered in the ComponentManager and conflict. Need a way to
        // add additional CSS classes to the rendered cells.
        
        text:'EMPLOYEE_ID',
        dataIndex: 'employeeId',
        flex: 1,
        width: 150,
        sortable: false
    },
    {
       
        text: "LEAVE_TYPE",
        dataIndex: 'leaveType',
        width: 150,
       
        sortable: false
    },
    /*,
    {
        id: 'departmentName',
        text: "DEPARTMENT_NAME",
        dataIndex: 'departmentList',
        width: 50,
        renderer: function(value)
        {   
        	console.log("in renderer");
        	var department= "";
        	for(var i=0; i<value.length; i++ )
        		{
        		department= value[i]+","+department;
        		}
        	return department;
        },
        sortable: false
    }*/
   
   
   
    
    
    {
       
        text: "DATE",
        dataIndex: 'date',
        width: 50,
        //renderer: renderFirst,
        sortable: false
    },/*,
    {
       
        text: "TO_DATE",
        dataIndex: 'toDate',
        sortable: false
    },*/
    
    {
    	text:"STATUS",
    	dataIndex:'status'
    }
    ],
   
    
   bbar: [{xtype:'paging3'}]
   
});
