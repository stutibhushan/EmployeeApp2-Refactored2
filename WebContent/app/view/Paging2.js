Ext.define('EmployeeApp.view.Paging2',
		{
	    extend:'Ext.PagingToolbar',
	    alias:'widget.paging2',
	    store: 'AdminLeaveDisplayStore',
        displayInfo: true,
        displayMsg: 'Displaying topics {0} - {1} of {2}',
        emptyMsg: "No topics to display",
        items:[
            '-', {
            text: 'Show Preview',
            //pressed: pluginExpanded,
            enableToggle: true
//            toggleHandler: function(btn, pressed) {
//                var preview = Ext.getCmp('gv').getPlugin('preview');
//                preview.toggleExpanded(pressed);
//            }
        }]
	
	
	
		});