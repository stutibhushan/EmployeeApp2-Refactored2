
//var leaveBalanceStore=Ext.create('EmployeeApp.store.LeaveBalanceStore');

Ext.define('EmployeeApp.view.ListEmployeeView',
{
id:'displayViewGridPanel',
extend:'Ext.panel.Panel',
alias:'widget.viewPanel',
autoHeight: false,
border: false,
title: 'Employee Data',



margins: '5 5 0 0',
items: [
          {xtype:'toolbar',
	        id:'toolbarType',
	             items: [
	         {
	             xtype: 'typeComboBox', // default for Toolbars
	             id:'type'
	            
	         },{
   	        	 xtype: 'button',
	        	 text: 'ADD EMPLOYEE',
	        		 align:'right',
	        		 
	        		 handler:function()
	        		 {
	        			 Ext.getCmp('displayViewGridPanel').hide();
	        			 
	        			 if(obj!=null)
	        				 {
	        				 console.log('obj is not null');
	        				 Ext.getCmp('name').setValue(obj.name);
	        				 Ext.getCmp('startDate').setValue(obj.startDate);
	        				 Ext.getCmp('endDate').setValue(obj.endDate);
	        				 Ext.getCmp('description').setValue(obj.description);
	        				 Ext.getCmp('salary').setValue(obj.salary);
	        				 Ext.getCmp('address').setValue(obj.address);
	        				 Ext.getCmp('city').setValue(obj.city);
	        				 Ext.getCmp('state').setValue(obj.state);
	        				 Ext.getCmp('country').setValue(obj.country);
	        				 
	        				 }
	        			 
	        			
	        			 Ext.getCmp('addEmployeeFormParentPanel').show();
	        			 
	        			 
	        		 }
	         
	         },
	         {
	        	 xtype:'button',
	        	 text: 'EDIT EMPLOYEE',
	        	 handler: function()
	        	 {   
	        		 console.log("length----->"+Ext.getCmp('display-view').getSelectionModel().getSelection().length);
	        		 if (Ext.getCmp('display-view').getSelectionModel().hasSelection() && Ext.getCmp('display-view').getSelectionModel().getSelection().length <2) {
	        			   var row = Ext.getCmp('display-view').getSelectionModel().getSelection()[0];
	        			   console.log(row.get('name'));
	        			   Ext.getCmp('displayViewGridPanel').hide();
	        			   Ext.getCmp('employeeId').setValue(row.get('id'));
	        			   Ext.getCmp('name').setValue(row.get('name'));
	        			   Ext.getCmp('startDate').setValue(row.get('startDate'));
	        			   Ext.getCmp('endDate').setValue(row.get('endDate'));
	        			   Ext.getCmp('description').setValue(row.get('description'));
	        			   Ext.getCmp('salary').setValue(row.get('salary'));
	        			   Ext.getCmp('address').setValue(row.get('address'));
	        			   Ext.getCmp('city').setValue(row.get('city'));
	        			   Ext.getCmp('state').setValue(row.get('state'));
	        			   Ext.getCmp('country').setValue(row.get('country'));
	        			   Ext.getCmp('employeeTypeId').setValue(row.get('type'));
	        			   
	        			   Ext.getCmp('addEmployeeFormParentPanel').show();
	        			}
	        		 else
	        			 {
	        			 Ext.Msg.alert("Error","Please select an Employee to proceed");
	        			 }
	        		 
	        		  
	        	 }
	       
	         },
	         {
	        	 xtype:'button',
	        	 text:'DELETE EMPLOYEE',
	        	 handler: function()
	        	 {
	        		 if(Ext.getCmp('display-view').getSelectionModel().hasSelection())
	        			 {
	        			 Ext.Msg.confirm('Confirm','Do you wanna delete the Employee',function(btnText){
	        				 if(btnText==="yes")
	        					 {
	        			  var employeeId= [];
	        			  var employeeIds="";
	        			  console.log("length--->"+Ext.getCmp('display-view').getSelectionModel().getSelection().length)
	        			 for(var i=0; i<Ext.getCmp('display-view').getSelectionModel().getSelection().length;i++)
	        				 {
	        				 var row= Ext.getCmp('display-view').getSelectionModel().getSelection()[i];
	        				 
	        				 employeeId.push(row.get('id'));
	        				 }
	        			  console.log("employeeid length--->"+employeeId.length);
	        			 for(var i=0; i<employeeId.length;i++)
	        				 {
	        				 employeeIds=employeeIds+employeeId[i];
	        				 if(i<employeeId.length-1)
	        					 employeeIds=employeeIds+",";
	        				 }
	        			 console.log("employeeId array"+employeeId);
	        			 console.log("employeeIds string"+ employeeIds);
	        				Ext.Ajax.request({
	        	           		url:'http://10.104.15.23:8080/EmployeeApp2/BulkDeleteEmployee',
	        	           		method:'get',
	        	           		params:{
	        	           			employeeIds: employeeIds
	        	           		},
	        	           		success:function(response)
	        	           		{
	        	           			
	        	           			var options= Ext.decode(response.responseText);
	        	           			if(options.success==="true")
	        	           				{
	        	           				Ext.Msg.alert('Notification',options.message);
	        	           			    Ext.getCmp('display-view').getStore().load();
	        	           				}
	        	           			else
	        	           				{
	        	           				Ext.Msg.alert('Notification',options.message);
	        	           			 Ext.getCmp('display-view').getStore().load();
	        	           				}
	        	           		}
	        	           	});
	        					 }
	        				 else if(btnText==="no")
	        					 {
	        					 
	        					 }
	        			 }, this);
	        			 }
	        			
	        		 else
	        			 {
	        			 Ext.Msg.alert('Error', 'No Record Selected');
	        			 }
	        	 }
	         
	         
	         },
	         {
	        	 xtype:'button',
	        	 text:'LEAVE BALANCE',
	        	handler:function(){
	        		
	        		if (Ext.getCmp('display-view').getSelectionModel().hasSelection() && Ext.getCmp('display-view').getSelectionModel().getSelection().length <2)
	        	
	        		 {
	        			 var row= Ext.getCmp('display-view').getSelectionModel().getSelection()[0];
		        		 var employeeId= row.get("id");
		        		 
		        		
		        		 var proxy1=Ext.getCmp("leaveBalanceGrid").getStore().getProxy();
		        		 proxy1.setExtraParam('employeeId', employeeId);
		        		 Ext.getCmp("leaveBalanceGrid").getStore().load();
		        		 

		        		 var proxy2=Ext.getCmp("employeeLeaveDisplayGrid").getStore().getProxy();
		        		 proxy2.setExtraParam('employeeId', employeeId);
		        		 Ext.getCmp("employeeLeaveDisplayGrid").getStore().load();
		        		 
	        		  	Ext.getCmp('displayViewGridPanel').hide();
	        		  
	        		  
	        		    Ext.getCmp('leaveBalancePanel').show();
	        		 }
	        		
	        		else
	        			{
	        			Ext.Msg.alert("Error","Please select an Employee to proceed");
	        			}
	        	}
	         },
	         {
	        	 xtype:'button',
	        	 text:'ADMIN',
	        	 handler: function()
	        	 {
	        		 Ext.getCmp('displayViewGridPanel').hide();
	        		
	        		 Ext.getCmp('adminLeaveDisplayPanel').show();
	        		
	        	 }
	         }
]
},
        {
	
	     xtype:'panel',
	     //layout:'fit',
	    // autoHeight: true,
	    // margins: '5 5 0 0',
	     items :[{xtype:'displayViewGrid',
	    	      
	                }]
        
        }
        
        

]
});