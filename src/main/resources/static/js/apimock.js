//@author hcadavid

apimock=(function(){

	var mockdata=[];

	mockdata["johnconnor"]=	[{author:"johnconnor","points":[{"x":150,"y":120},{"x":215,"y":115}],"name":"house"},
	 {author:"johnconnor","points":[{"x":100,"y":100},{"x":15,"y":215}],"name":"gear"}];
	mockdata["maryweyland"]=[{author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"house2"},
	 {author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"gear2"}];
	 mockdata["Enrique"] = [{author:"Enrique","points":[{"x":150,"y":120},{"x":215,"y":115},{"x":110,"y":50}],"name":"3points"},
	 {author:"Enrique","points":[{"x":10,"y":10},{"x":150,"y":10},{"x":150,"y":150},{"x":10,"y":150},{"x":9,"y":9}],"name":"square"}];

    var authorname = $('#autname').val();
    var tamPlanos = [];


	return {

		getBlueprintsByAuthor:function(authname,callback){
			if(typeof mockdata[authname] != 'object'){
				apiclient.getBlueprintsByAuthor(authname, callback);
			}else {
				callback(
					mockdata[authname]
			);
				}
			
		},

		getBlueprintsByNameAndAuthor:function(authname,bpname,callback){
			if(typeof mockdata[authname] != 'object'){
				apiclient.getBlueprintsByNameAndAuthor(authname, bpname, callback);}
			else{
				callback(
					mockdata[authname].find(function(e){return e.name===bpname})
				);
			}
		}
	}	

})();

/*
Example of use:
var fun=function(list){
	console.info(list);
}
apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/