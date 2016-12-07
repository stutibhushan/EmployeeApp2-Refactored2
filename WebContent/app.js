Ext.application({
	name : 'EmployeeApp',
	controllers : [ 'EmployeeController' ],
	appFolder : 'app',
	autoCreateViewport : true,

	statics : {
		enableComboSelection : undefined
	},
	launch : function() {
		console.log("in app");
	}
});