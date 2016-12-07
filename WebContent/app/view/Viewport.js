Ext.define('EmployeeApp.view.Viewport',{
	extend:'Ext.container.Viewport',	
	initComponent: function(){
      
        Ext.apply(this, {
            //id: 'dashboard-viewport',
            layout: {
                //type: 'fit',
                //padding: '35 5 5 5' // pad the layout from the window edges
            },
			defaults: {
		        //collapsible: true,
		       // split: true
		        //bodyPadding: 10
		    },
            items: [
				
				/*{xtype:'toolbar',
					id:'toolbarType',
		        	 items: [
		        	         {
		        	             xtype: 'typeComboBox', // default for Toolbars
		        	            
		        	         },{
		           	        	 xtype: 'button',
		        	        	 text: 'ADD EMPLOYEE',
		        	        		 align:'right',
		        	        		 
		        	        		 handler:function()
		        	        		 {
		        	        			 Ext.getCmp('displayViewGridPanel').hide();
		        	        			 Ext.getCmp('toolbarType').hide();
		        	        			 Ext.getCmp('addEmployeeForm').show();
		        	        			 
		        	        			 
		        	        		 }
		        	         
		        	         }
		        ]
		        },*/
		        {
					xtype:'viewPanel',
					hidden:true
	            },
	            
	            {
	            	xtype:'addEmployeeFormParentPanel',
	            	hidden:'true',
	            	            	
	            },
	            
	            {
	            	xtype:'leaveBalancePanel',
	            	hidden:'true'
	            },
	            {
	            	xtype:'adminLeaveDisplayPanel',
	            	hidden:'true'
	            },
	            {
					xtype:'loginFormModule',
					hidden:false
				}
					
			]
        });
        this.callParent();
    }
});