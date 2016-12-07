Ext.require([
    'Ext.form.Panel',
    'Ext.ux.form.MultiSelect',
    'Ext.ux.form.ItemSelector',
    'Ext.tip.QuickTipManager',
    'Ext.ux.ajax.JsonSimlet',
    'Ext.ux.ajax.SimManager'
]);
var ds= Ext.create('EmployeeApp.store.DepartmentStore');
ds.load();

 function createDockedItems(fieldId) {
        return [{
            xtype: 'toolbar',
            dock: 'bottom',
            items:  {
            xtype: 'toolbar',
            dock: 'bottom',
            ui: 'footer',
            defaults: {
                minWidth: 75
            },
            items: ['->', {
                text: 'Clear',
                handler: function(){
                    var field = Ext.getCmp(fieldId);
                    if (!field.disabled) {
                        field.clearValue();
                    }
                }
            }, {
                text: 'Reset',
                handler: function() {
                    Ext.getCmp(fieldId).up('form').getForm().reset();
                }
            }, {
                text: 'Save',
                handler: function(){
                    var form = Ext.getCmp(fieldId).up('form').getForm();
                    form.getValues(true);
                    if (form.isValid()){
                        Ext.Msg.alert('Submitted Values', 'The following will be sent to the server: <br />'+
                            form.getValues(true));
                    }
                }
            }]
            }
        }];
    }

            

Ext.define('EmployeeApp.view.AddDepartmentForm', {
	
	extend:'Ext.form.Panel',
   
    id:'addDepartmentForm',
    alias: 'widget.addDepartmentForm',
   /* 
    layout:{
    	
    	type:'table',
    	columns:3,
    	margin:10
    },*/
   // renderTo: Ext.getBody(),
    bodyPadding: 5,
  
    // Any configuration items here will be automatically passed along to
    // the Ext.form.Basic instance when it gets created.

    // The form will submit an AJAX request to this URL when submitted
    //url: 'save-form.php',
  
    items: [
    
    {    
    	
    	 xtype:'form',
    	 id:'addDepartmentSelectorForm',
    	 title: 'Department List',
         width: 700,
         bodyPadding: 10,
         height: 400,
        // renderTo: 'itemselector',
         layout: 'fit',
         items:[{
             xtype: 'itemselector',
             name: 'itemselector',
             id: 'itemselector-field',
             anchor: '100%',
             fieldLabel: 'Departments',
             imagePath: 'ext/ux/images/',
            store: ds,
             displayField: 'departmentName',
             valueField: 'id',
             allowBlank: false,
             msgTarget: 'side',
             fromTitle: 'Available',
             toTitle: 'Selected'
         }]/*,
         dockedItems: createDockedItems('itemselector-field')*/
     

    	
    }
    
    ]
    
   /* buttons: [{
        text: 'Save',
        handler: function() {
           // this.up('form').getForm().isValid();
           
           	 EmployeeApp.app.getController('EmployeeController').addEmployee();
        	//console.log(Ext.getCmp('name').getValue());
           
            
        }
    },
    {
        text: 'Copy',
        handler: function() {
        	 var name=Ext.getCmp('name').getValue();
        	 
        	 
             var startDate= Ext.getCmp('startDate').getValue();
             var endDate= Ext.getCmp('endDate').getValue();
             var description= Ext.getCmp('description').getValue();
             var salary= Ext.getCmp('salary').getValue();
             var address= Ext.getCmp('address').getValue();
             var city= Ext.getCmp('city').getValue();
             var state= Ext.getCmp('state').getValue();
             var country= Ext.getCmp('country').getValue();
        }
    },
    {
        text: 'Reset',
        handler: function() {
        	this.up('form').getForm().reset();
        }
    },
    
    {
        text: 'Cancel',
        handler: function() {
        	this.up('form').getForm().reset();
        	
        	 Ext.getCmp('addEmployeeFormPanel').hide();
        	 Ext.getCmp('displayViewGridPanel').show();
 			
			
        }
    }]

    
    */

    
 });

