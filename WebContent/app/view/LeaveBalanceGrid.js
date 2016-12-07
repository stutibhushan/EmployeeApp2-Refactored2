Ext.define('EmployeeApp.view.LeaveBalanceGrid',
{
	extend:'Ext.grid.Panel',
	alias: 'widget.leaveBalanceGrid',
	id:'leaveBalanceGrid',
	
	store:'LeaveBalanceStore',
	
	  columns:[{
	        // id assigned so we can apply custom css (e.g. .x-grid-cell-topic b { color:#333 })
	        // TODO: This poses an issue in subclasses of Grid now because Headers are now Components
	        // therefore the id will be registered in the ComponentManager and conflict. Need a way to
	        // add additional CSS classes to the rendered cells.
	        
	        text:'EMPLOYEE_ID',
	        dataIndex: 'employeeId',
	        width:200,
	        hidden: true,
	       
	    },
	    {
	       
	        text: "LEAVE_TYPE",
	        dataIndex: 'leaveType',
	        width: 200,
	        sortable: false
	    },
	    {
	        text: "CREDIT",
	        dataIndex: 'credit',
	        width: 200,
	        sortable: false
	    },
	    
	    {
	        text: "AVAILED",
	        dataIndex: 'availed',
	        width: 200,
	        sortable: false
	    },
	    
	    {
	        text: "FINAL_BALANCE",
	        dataIndex: 'finalBalance',
	        width: 200,
	        sortable: false
	    }
	    
	    
	 ],
	 
	 
});