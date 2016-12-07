Ext.define('EmployeeApp.view.AdminLeaveDisplayPanel',
 {
extend:'Ext.panel.Panel',
id:'adminLeaveDisplayPanel',
alias:'widget.adminLeaveDisplayPanel',
title:'Applied Leave Details',

items:[
       {
    	   xtype:'toolbar',
    	   items:[{
    	          xtype:'button',
    	          text:'HOME',
    	          handler: function()
    	          {   
    	        	 
    	        	  Ext.getCmp('adminLeaveDisplayPanel').hide();
    	        	  Ext.getCmp('displayViewGridPanel').show();
    	        	  Ext.getCmp('adminLeaveDisplayGrid').getStore().reload();
	        		 
    	          }
    	   }]
       },
      {
       xtype:'panel',
	     //layout:'fit',
	    // autoHeight: true,
	    // margins: '5 5 0 0',
	     items :[{xtype:'adminLeaveDisplayGrid'
	    	 
	            // hidden:true
	     }]
      }
       
      ]




 });