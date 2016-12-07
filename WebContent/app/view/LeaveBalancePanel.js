Ext.define('EmployeeApp.view.LeaveBalancePanel',
 {
extend:'Ext.panel.Panel',
id:'leaveBalancePanel',
alias:'widget.leaveBalancePanel',
title:'EMPLOYEE LEAVE BALANCE',

items:[
       {
    	   xtype:'toolbar',
    	   items:[{
    	          xtype:'button',
    	          text:'HOME',
    	          handler: function()
    	          {   
    	        	 // Ext.getCmp('applyLeaveForm').getForm().reset();
    	        	  Ext.getCmp('leaveBalancePanel').hide();
    	        	  Ext.getCmp('displayViewGridPanel').show();
    	        	  Ext.getCmp('adminLeaveDisplayGrid').getStore().load();
	        		 
    	          }
    	   }]
       },
      {
       xtype:'panel',
	     //layout:'fit',
	    // autoHeight: true,
	    // margins: '5 5 0 0',
	     items :[{xtype:'leaveBalanceGrid',
	    	 
	            // hidden:true
	     }]
      },
      
      {
    	  xtype:'panel',
    	  items:[{xtype:'applyLeaveForm'}]
      },
      
      {
    	  xtype:'panel',
    	  title:'LEAVE HISTORY',
    	  items:[{xtype:'employeeLeaveDisplayGrid'}]
      }
       
      ]




 });