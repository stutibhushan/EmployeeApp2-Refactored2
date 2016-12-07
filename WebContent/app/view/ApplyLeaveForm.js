Ext.define('EmployeeApp.view.ApplyLeaveForm',{
	
extend:'Ext.form.Panel',

alias:'widget.applyLeaveForm',

id:'applyLeaveForm',

layout:{
	
	type:'table',
	columns:2,
	margin:10
},
	
items:[
       
       {
    	   xtype: 'leaveTypeComboBox', 
           padding:20,// default for Toolbars
           id:'leaveTypeComboBox'
       },
       
       {
           xtype: 'datefield',
           fieldLabel: 'From Date',
           allowBlank:false,
           name: 'fromDate',
           id:'fromDate',
           //validateOnChange:true,
           padding:20,
           listeners   : {
           	change: function(field, newValue, oldValue, eOpts) {
                   Ext.getCmp('toDate').setMinValue(field.getValue());
                   Ext.getCmp('toDate').setValue(field.getValue());
           	}
           }
       },
       
       {
    	   xtype: 'textareafield', 
           padding:20,// default for Toolbars
           fieldLabel:'REASON',
           id:'reason'
       },
       {
    	   xtype: 'datefield', 
           padding:20,// default for Toolbars
           fieldLabel:'TO',
           name:'toDate',
           id:'toDate',
           validateOnChange:true,
           validator:	function(value)
           {  
           	
           //	console.log(value);
           	var fromDate=Ext.getCmp('fromDate').getValue();
           	var toDate=Ext.getCmp('toDate').getValue();
           	//console.log(startDate);
           	
           	if(fromDate>toDate)
           		{
           		console.log("invalid date");
           		return "To Date should be greater than or equal to start Date";
           		}
           	
           	else
           		{
           		var datesArray=[];
           		var newDate=fromDate;
           		var diffDays = parseInt((toDate - fromDate) / (1000 * 60 * 60 * 24))+1; 
           		for(i=0;i<diffDays; i++)
           			{
           			if(newDate.getDay()!=0 && newDate.getDay()!=6)
           				{
           				datesArray.push(newDate);
           				}
           			newDate=Ext.Date.add(newDate,Ext.Date.DAY,1);
           			}
           		console.log("dates array"+datesArray);
           		Ext.getCmp('leaveDuration').setValue(datesArray.length);
           		return true;
           		}
           	
           }
           	
       },
       
      
       {
    	   xtype: 'button', 
           //padding:40,// default for Toolbars
           text:'APPLY',
           handler:function()
           {
        	 var row= Ext.getCmp('display-view').getSelectionModel().getSelection()[0];
        	 var employeeId= row.get("id");
      		 console.log("id----->>>>>>>"+employeeId);
      		 var leaveType=Ext.getCmp('leaveTypeComboBox').getValue();
      		 console.log("leave type------>>>>"+leaveType);
      		 var duration= Ext.getCmp('leaveDuration').getValue();
      		 console.log("leave duration------>>>"+ duration);
      		 var fromDate= Ext.getCmp('fromDate').getValue();
      		 var toDate= Ext.getCmp('toDate').getValue();
      		 console.log('todate---->>'+toDate);
      		 console.log('fromdate---->>'+fromDate);
      		 var applyLeaveDates=[];
        	 var leaveDuration=0;
        	 var leaveBalanceGridList= Ext.getCmp('leaveBalanceGrid').getStore().data.items;
        	 var employeeLeaveDisplayGridList= Ext.getCmp('employeeLeaveDisplayGrid').getStore().data.items;
        	 var applyLeaveDatesList="";
        	 var flag=0;
        	 
        	 
             var datesArray=[];
            		
            		
            		
        	 
        	 for(var i=0; i<leaveBalanceGridList.length; i++)
        		 {
        		 if(leaveType===leaveBalanceGridList[i].get("leaveType"))
        			 {
        			 if(duration>leaveBalanceGridList[i].get("finalBalance"))
        				 {
        				 Ext.Msg.alert("Your final Leave balance is less");
        				 flag=1;
        				 }
        			 //else
        			 //}
        		// else
        			 }
        		 }
        	   
        	   if(flag===0)
        		 {
        			 
        		   var newDateArray=fromDate;
           		   var diffDays = parseInt((toDate - fromDate) / (1000 * 60 * 60 * 24))+1; 
           		   for(i=0;i<diffDays; i++)
           			{
           			if(newDateArray.getDay()!=0 && newDateArray.getDay()!=6)
           				{
           				datesArray.push(newDateArray);
           				}
           			newDateArray=Ext.Date.add(newDateArray,Ext.Date.DAY,1);
           			}
        			 
        			 //console.log("..............jhgjhmbvjhv"+(employeeLeaveDisplayGridList.length>0));
        			 if(employeeLeaveDisplayGridList.length>0)
        			  {
        			 //var newDate=fromDate;
        			
        				 for(var i=0; i<datesArray.length; i++)
          			       {
          			         var flag2=0;
          			 
          			
          			     for(var j=0; j<employeeLeaveDisplayGridList.length; j++)
          				 {
          				 
          				 console.log(datesArray[i].getTime()===new Date(employeeLeaveDisplayGridList[j].get("date")).getTime());
          				 if(datesArray[i].getTime()===new Date(employeeLeaveDisplayGridList[j].get("date")).getTime() && (employeeLeaveDisplayGridList[j].get("status")==="pending" || employeeLeaveDisplayGridList[j].get("status")==="approved"))
          					 {
          					 
          					 console.log('in inf');
          					 Ext.Msg.alert("Error","Already applied for the leave on date"+datesArray[i]);
          					 flag2=1;
          					 }
          				 
          				 }
          			 
          			 if(flag2===0)
          				 {
          				 applyLeaveDates.push(datesArray[i]);
          				 }
          			  //newDate=Ext.Date.add(newDate,Ext.Date.DAY,1);
          			  //console.log("newDtae"+newDate);
          			 }
        			 
        			 console.log("apply leave dates array"+applyLeaveDates);
        			 if(applyLeaveDates.length>0)
         			  {
        				 console.log("applied leaves array"+applyLeaveDates);
        			 duration=applyLeaveDates.length;
         			 //var applyLeaveDatesList="";
             	     for(var i=0;i<applyLeaveDates.length;i++)
             		   {
             		   applyLeaveDatesList=applyLeaveDatesList+applyLeaveDates[i];
             		   if(i<(applyLeaveDates.length-1))
             			   {
             			   applyLeaveDatesList= applyLeaveDatesList+",";
             			   }
             		   
             		   }
         			  }
        			  }
        			 
        			 else
        				 {
        				 
        				 for(var i=0; i<datesArray.length; i++)
              			 {
        					 applyLeaveDates.push(datesArray[i]);
        					 
        					 
              			 }
        				 if(applyLeaveDates.length>0)
            			  {
           			     duration=applyLeaveDates.length;
            			 //var applyLeaveDatesList="";
                	     for(var i=0;i<applyLeaveDates.length;i++)
                		   {
                		   applyLeaveDatesList=applyLeaveDatesList+applyLeaveDates[i];
                		   if(i<(applyLeaveDates.length-1))
                			   {
                			   applyLeaveDatesList= applyLeaveDatesList+",";
                			   }
                		   
                		   }
            			  }
        				 
        				 }
             	     console.log('applyLeaveDatesList---------->>>>'+applyLeaveDatesList);
             	    Ext.Ajax.request({
                   		url:'http://10.104.15.23:8080/EmployeeApp2/ApplyLeaveServlet',
                   		method:'get',
                   		//jsonData: departmentList,
                   		params:{
                   			employeeId: employeeId,
                   			leaveType: leaveType,
                   			duration: leaveDuration,
                   			applyLeaveDatesList:applyLeaveDatesList
                   			},
                    success: function(response) {
                    	//Ext.getCmp('leaveBalanceGrid').getStore().load();
                	    // resp is the XmlHttpRequest object
                	    var options = Ext.decode(response.responseText);
                	    //console.log(options["success"]);
                	    //console.log(globalProperties.saveEmployeeMessage);
                	    if(options.success==="true")
                	    {
                	    	Ext.Msg.alert('Notification',options.message);
                	    	 Ext.getCmp('employeeLeaveDisplayGrid').getStore().reload();
                	    	 Ext.getCmp('leaveBalanceGrid').getStore().reload();
                	    	 
                	    	//Ext.getCmp('employeeId').setValue(options.employeeId);
                	    	//console.log(Ext.getCmp('employeeId').getValue());
                	    	//Ext.getCmp('addEmployeeFormPanel').setActiveTab(0);
                	    	
                	    	 //Ext.getCmp('addEmployeeFormParentPanel').hide();
                	    	//Ext.getCmp('displayViewGridPanel').show();
                	    }
                	    else
                	    	{
                	    	Ext.Msg.alert('Notification',options.message);
                	    	}
                	    
                	  }     
                   		
                   	});
             	    // Ext.getCmp('employeeLeaveDisplayGrid').getStore().reload();
         			   
        		 }
        		 }
        		 

      		
        	// var applyLeaveDates=[];
        	
        	
      		
      		 
      		
      		 
      		  
      		 
      		/*Ext.Ajax.request({
           		url:'http://10.104.15.23:8080/EmployeeApp2/ApplyLeaveServlet',
           		method:'get',
           		//jsonData: departmentList,
           		params:{
           			employeeId: employeeId,
           			leaveType: leaveType,
           			duration: duration,
           			fromDate: fromDate,
           			toDate: toDate,
           			duration: duration
           			},
            success: function(response) {
            	//Ext.getCmp('leaveBalanceGrid').getStore().load();
        	    // resp is the XmlHttpRequest object
        	    var options = Ext.decode(response.responseText);
        	    //console.log(options["success"]);
        	    //console.log(globalProperties.saveEmployeeMessage);
        	    if(options.success==="true")
        	    {
        	    	Ext.Msg.alert('Notification',options.message);
        	    	//Ext.getCmp('employeeId').setValue(options.employeeId);
        	    	//console.log(Ext.getCmp('employeeId').getValue());
        	    	//Ext.getCmp('addEmployeeFormPanel').setActiveTab(0);
        	    	
        	    	 //Ext.getCmp('addEmployeeFormParentPanel').hide();
        	    	//Ext.getCmp('displayViewGridPanel').show();
        	    }
        	    else
        	    	{
        	    	Ext.Msg.alert('Notification',options.message);
        	    	}
        	    
        	  }     
           		
           	});*/
      		//Ext.getCmp('leaveBalanceGrid').getStore().load();
        		 
           

       
       
       
       
       
       
       },
       {
    	   xtype:'numberfield',
    	   fieldLabel:'DURATION(DAYS)',
    	   name:'leaveDuration',
    	   id:'leaveDuration',
    	   padding:20,
    	   minValue:0,
    	   listeners   : {
          spinup: function(field, e, eOpts) {
                   
           		//console.log("number field value"+field.getValue());
           		var i=field.getValue();
                   Ext.getCmp('toDate').setValue(Ext.Date.add(Ext.getCmp('fromDate').getValue(),Ext.Date.DAY,i));
                
           	},
       spindown: function(field, newValue, oldValue, eOpts) {
           
           Ext.getCmp('toDate').setValue(Ext.Date.add(Ext.getCmp('toDate').getValue(),Ext.Date.DAY,-1));
   	}
           }
       }
       
       
       
       ]
	
	
	
	
});