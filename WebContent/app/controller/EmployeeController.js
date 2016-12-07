Ext.define('EmployeeApp.controller.EmployeeController',{
	extend:'Ext.app.Controller',
	stores:['EmployeeDetailStore','EmployeeTypeStore','DepartmentStore', 'AddEmployeeFormTypeComboBoxStore', 'LeaveBalanceStore', 'LeaveTypeComboBoxStore', 'AdminLeaveDisplayStore', 'EmployeeLeaveDisplayStore'],
	models:['EmployeeVOModel','EmployeeTypeModel','DepartmentModel', 'LeaveBalanceModel', 'AdminLeaveDisplayModel', 'EmployeeLeaveDisplayModel'],
	views:['EmployeeGrid','ListEmployeeView','TypeComboBox', 'Paging','AddEmployeeForm','AddEmployeeFormPanel','AddDepartmentForm', 'Buttons','AddEmployeeFormParentPanel','TypeComboBox2', 'LeaveBalancePanel', 'LeaveBalanceGrid', 'ApplyLeaveForm', 'LeaveTypeComboBox', 'AdminLeaveDisplayPanel', 'AdminLeaveDisplayGrid','Paging2', 'EmployeeLeaveDisplayGrid', 'Paging3', 'LoginForm', 'AuthenticationTypeComboBox'],
	refs:[
	      
	],
	init:function()
	{
		 console.log("in init");
		
	},
	
	addEmployee: function(){
	  console.log("employee id value"+Ext.getCmp('employeeId').getValue());   
       var name= Ext.getCmp('name').getValue();
       var startDate= Ext.getCmp('startDate').getValue();
       var endDate= Ext.getCmp('endDate').getValue();
       var description= Ext.getCmp('description').getValue();
       var salary= Ext.getCmp('salary').getValue();
       var address= Ext.getCmp('address').getValue();
       var city= Ext.getCmp('city').getValue();
       var state= Ext.getCmp('state').getValue();
       var country= Ext.getCmp('country').getValue();
       var departmentList="";
    	   for(var i=0;i<Ext.getCmp('itemselector-field').getValue().length;i++)
    		   {
    		   departmentList=departmentList+Ext.getCmp('itemselector-field').getValue()[i];
    		   if(i<(Ext.getCmp('itemselector-field').getValue().length-1))
    			   {
    			   departmentList= departmentList+",";
    			   }
    		   
    		   }
    	   
    	   console.log("employee type value"+Ext.getCmp('employeeTypeId').getValue());
    	  var type=Ext.getCmp('employeeTypeId').getValue();
    	 
       
       console.log(departmentList);
      
       	Ext.Ajax.request({
       		url:'http://localhost:8080/EmployeeApp2/AddEmployee',
       		method:'post',
       		//jsonData: departmentList,
       		params:{
       			name: name,
       			startDate:startDate,
       			endDate:endDate,
       			salary:salary,
       			address:address,
       			city:city,
       			state:state,
       			description:description,
       			country:country,
       			departmentList:departmentList,
       			type:type
       			
       			/*callback : function(options, success, response){ 
       				console.log('alert');
       				addEmployeeResponseJson = Ext.JSON.decode(response.responseText);
       				console.log(addEmployeeResponseJson);*/
              
       		},
        success: function(response) {
        	
    	    // resp is the XmlHttpRequest object
    	    var options = Ext.decode(response.responseText);
    	    //console.log(options["success"]);
    	    //console.log(globalProperties.saveEmployeeMessage);
    	    if(options.success==="true")
    	    {
    	    	Ext.Msg.alert('Notification',globalProperties.saveEmployeeMessage);
    	    	Ext.getCmp('employeeId').setValue(options.employeeId);
    	    	console.log(Ext.getCmp('employeeId').getValue());
    	    	Ext.getCmp('addEmployeeFormPanel').setActiveTab(0);
    	    	
    	    	 //Ext.getCmp('addEmployeeFormParentPanel').hide();
    	    	//Ext.getCmp('displayViewGridPanel').show();
    	    }
    	    else
    	    	{
    	    	Ext.Msg.alert("unable to add");
    	    	}
    	    
    	  }     
       		
       	});
       	
       	
       
       
   },
   updateEmployee: function()
   {   
	  /* var id= Ext.getCmp("employeeId").getValue();
	   var name= Ext.getCmp('name').getValue();
       var startDate= Ext.getCmp('startDate').getValue();
       var endDate= Ext.getCmp('endDate').getValue();
       var description= Ext.getCmp('description').getValue();
       var salary= Ext.getCmp('salary').getValue();
       var address= Ext.getCmp('address').getValue();
       var city= Ext.getCmp('city').getValue();
       var state= Ext.getCmp('state').getValue();
       var country= Ext.getCmp('country').getValue();
       var departmentList="";
    	   for(var i=0;i<Ext.getCmp('itemselector-field').getValue().length;i++)
    		   {
    		   departmentList=departmentList+Ext.getCmp('itemselector-field').getValue()[i];
    		   if(i<(Ext.getCmp('itemselector-field').getValue().length-1))
    			   {
    			   departmentList= departmentList+",";
    			   }
    		   
    		   }
    	   
    	   var type=Ext.getCmp('employeeTypeId').getValue();
           if(type==="PERMANENT")
        	   {
        	   type=1;
        	   }
            if(type==="CONTRACT")
    	     {
    	      type=2;
    	      }
             if(type==="INTERN")
    	     {
    	     type=3;
    	     }
       console.log(departmentList);
      
       	Ext.Ajax.request({
       		url:'http://localhost:8080/EmployeeApp2/UpdateEmployee',
       		method:'post',
       		//jsonData: departmentList,
       		params:{
       			
       		    id: id,
       			name: name,
       			startDate:startDate,
       			endDate:endDate,
       			salary:salary,
       			address:address,
       			city:city,
       			state:state,
       			description:description,
       			country:country,
       			departmentList:departmentList,
       			type:type
       			
       			callback : function(options, success, response){ 
       				console.log('alert');
       				addEmployeeResponseJson = Ext.JSON.decode(response.responseText);
       				console.log(addEmployeeResponseJson);
              
       		},
        success: function(response) {
        	
    	    // resp is the XmlHttpRequest object
    	    var options = Ext.decode(response.responseText);
    	    //console.log(options["success"]);
    	    //console.log(globalProperties.saveEmployeeMessage);
    	    if(options.success==="true")
    	    {
    	    	Ext.Msg.alert('Notification',options.message);
    	    	Ext.getCmp('addEmployeeFormPanel').setActiveTab(0);
    	    	
    	    	
    	    	
    	    	
    	    	 //Ext.getCmp('addEmployeeFormParentPanel').hide();
    	    	//Ext.getCmp('displayViewGridPanel').show();
    	    }
    	    else
    	    	{
    	    	Ext.Msg.alert("unable to add");
    	    	}
    	    
  	  }     
     		
     	});*/
	   var employee={};
	   employee.id=Ext.getCmp("employeeId").getValue();
	   employee.name=Ext.getCmp("name").getValue();
	   employee.startDate=Ext.getCmp("startDate").getValue();
	   employee.endDate=Ext.getCmp("endDate").getValue();
	   employee.description=Ext.getCmp("description").getValue();
	   employee.salary=Ext.getCmp("salary").getValue();
	   employee.address=Ext.getCmp("address").getValue();
	   employee.city=Ext.getCmp("city").getValue();
	   employee.state=Ext.getCmp("state").getValue();
	   employee.country=Ext.getCmp("country").getValue();
	   employee.departmentList=Ext.getCmp('itemselector-field').getValue();
	   employee.type=Ext.getCmp('employeeTypeId').getValue();
                 if(employee.type==="PERMANENT")
	               {
	                employee.type=1;
	               }
                  if(type==="CONTRACT")
                    {
                     employee.type=2;
                     }
                  if(type==="INTERN")
                    {
                     employee.type=3;
                    }
             
               employee.startDate = employee.startDate.getDate()+"-"+(employee.startDate.getMonth()+1)+"-"+employee.startDate.getFullYear();
               console.log("start date---->>>>"+ employee.startDate);
               employee.endDate = employee.endDate.getDate()+"-"+(employee.endDate.getMonth()+1)+"-"+employee.endDate.getFullYear(); 
                var employeeJson=JSON.stringify(employee);
                console.log(employeeJson);
                Ext.Ajax.request({
                	  url : 'http://localhost:8080/EmployeeApp2/UpdateEmployee2',
                	  method: 'post',
                	  headers: { 'Content-Type': 'application/json' , 'employeeJson': employeeJson},  
                	  
                	  success: function(response) {
                      	
                  	    // resp is the XmlHttpRequest object
                  	    var options = Ext.decode(response.responseText);
                  	    //console.log(options["success"]);
                  	    //console.log(globalProperties.saveEmployeeMessage);
                  	    if(options.success==="true")
                  	    {
                  	    	Ext.Msg.alert('Notification',options.message);
                  	    	Ext.getCmp('addEmployeeFormPanel').setActiveTab(0);
                  	    	
                  	    	
                  	    	
                  	    	
                  	    	 //Ext.getCmp('addEmployeeFormParentPanel').hide();
                  	    	//Ext.getCmp('displayViewGridPanel').show();
                  	    }
                  	    else
                  	    	{
                  	    	Ext.Msg.alert("unable to add");
                  	    	}
                  	    
                	  }     
                	 
                	
                });
                  
     	
    },
    
    deleteEmployee:function(grid, rowIndex){
    	
    	
    	Ext.Msg.confirm('Confirm','Do you wanna delete the Employee',function(btnText){
	        if(btnText === "no"){
	        	
	       	    //Ext.getCmp('display-view').getStore().load();
	        }
	        else if(btnText === "yes"){
	        	var employeeVO= Ext.getCmp('display-view').getStore().getAt(rowIndex);
	        	var employeeId=employeeVO.get('id');
	           	Ext.Ajax.request({
	           		url:'http://localhost:8080/EmployeeApp2/DeleteEmployee',
	           		method:'get',
	           		params:{
	           			employeeId: employeeId
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
	    }, this);
    		   
           	/*var employeeVO= Ext.getCmp('display-view').getStore().getAt(rowIndex);
           	var employeeId=employeeVO.get('id');
           	Ext.Ajax.request({
           		url:'http://localhost:8080/EmployeeApp2/DeleteEmployee',
           		method:'get',
           		params:{
           			employeeId: employeeId
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
           	});*/
           	
       	   // Ext.getCmp('display-view').getStore().load();
           
       },
       
       acceptLeave:function(grid, rowIndex){
       	
       	
       	Ext.Msg.confirm('Confirm','Do you wanna accept the Leave',function(btnText){
   	        if(btnText === "no"){
   	        	
   	       	    //Ext.getCmp('display-view').getStore().load();
   	        }
   	        else if(btnText === "yes"){
   	        	var leaveGridRow= Ext.getCmp('adminLeaveDisplayGrid').getStore().getAt(rowIndex);
   	        	var employeeId=leaveGridRow.get('employeeId');
   	        	var leaveType=leaveGridRow.get('leaveType');
   	        	var date=leaveGridRow.get('date');
   	           	Ext.Ajax.request({
   	           		url:'http://localhost:8080/EmployeeApp2/AcceptLeave',
   	           		method:'get',
   	           		params:{
   	           			employeeId: employeeId,
   	           			leaveType: leaveType,
   	           			date:date
   	           		},
   	           		success:function(response)
   	           		{
   	           			
   	           			var options= Ext.decode(response.responseText);
   	           			if(options.success==="true")
   	           				{
   	           				Ext.Msg.alert('Notification',options.message);
   	           			    Ext.getCmp('adminLeaveDisplayGrid').getStore().load();
   	           				}
   	           			else
   	           				{
   	           				Ext.Msg.alert('Notification',options.message);
   	           			    Ext.getCmp('adminLeaveDisplayGrid').getStore().load();
   	           				}
   	           		}
   	           	});
   	           
   	        }
   	    }, this);
       		   
              	
              
          },
          
          rejectLeave:function(grid, rowIndex){
             	
             	
             	Ext.Msg.confirm('Confirm','Do you wanna reject the Leave',function(btnText){
         	        if(btnText === "no"){
         	        	
         	       	    //Ext.getCmp('display-view').getStore().load();
         	        }
         	        else if(btnText === "yes"){
         	        	var leaveGridRow= Ext.getCmp('adminLeaveDisplayGrid').getStore().getAt(rowIndex);
         	        	var employeeId=leaveGridRow.get('employeeId');
         	        	var leaveType=leaveGridRow.get('leaveType');
         	        	var date=leaveGridRow.get('date');
         	           	Ext.Ajax.request({
         	           		url:'http://localhost:8080/EmployeeApp2/RejectLeave',
         	           		method:'get',
         	           		params:{
         	           			employeeId: employeeId,
         	           			leaveType: leaveType,
         	           			date:date
         	           		},
         	           		success:function(response)
         	           		{
         	           			
         	           			var options= Ext.decode(response.responseText);
         	           			if(options.success==="true")
         	           				{
         	           				Ext.Msg.alert('Notification',options.message);
         	           			    Ext.getCmp('adminLeaveDisplayGrid').getStore().load();
         	           				}
         	           			else
         	           				{
         	           				Ext.Msg.alert('Notification',options.message);
         	           			    Ext.getCmp('adminLeaveDisplayGrid').getStore().load();
         	           				}
         	           		}
         	           	});
         	           
         	        }
         	    }, this);
             		   
                    	
                    
                },
                
                loginEmployee:function()
            	{
            		var username=Ext.getCmp('username').getValue();
            		var password=Ext.getCmp('password').getValue();
            		console.log(Ext.getCmp('authenticationType'));
            		var authenticationType=Ext.getCmp('authenticationType').getValue();
            		console.log(authenticationType);
            		Ext.Ajax.request({
            			
            			
            			url:'http://localhost:8080/EmployeeApp2/LoginServlet',
            			method:'post',
            			params:{
            				username:username,
            				password:password,
            				authenticationType:authenticationType
            				},
            				success:function(response) {
            		        	
            		    	    // resp is the XmlHttpRequest object
            		    	    var options = Ext.decode(response.responseText);
            		    	    console.log(options.success);
            		    	    console.log(options.message);
            		    	    //console.log(options["success"]);
            		    	    //console.log(globalProperties.saveEmployeeMessage);
            		    	    if(options.success===true)
            		    	    {
            		    	    	Ext.Msg.alert('Notification',options.message);
            		    	    	
            		    	    	Ext.getCmp('display-view').getStore().load();
            		    	    	 Ext.getCmp('loginForm').hide();
            		    	    	Ext.getCmp('displayViewGridPanel').show();
            		    	    }
            		    	    else
            		    	    	{
            		    	    	Ext.Msg.alert("Error",options.message);
            		    	    	}
            		    	    
            		    	  }     
            		});
            	}
             
       
    
   
});