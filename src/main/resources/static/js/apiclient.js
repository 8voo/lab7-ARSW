var apiclient = ( function(){
return {

    getBlueprintsByAuthor:function(authname,callback){
        callback(
            JSON.parse($.ajax({type:'GET', url:'blueprint/' + authname, async:false}).responseText)
        );
    },

    getBlueprintsByNameAndAuthor:function(authname,bpname,callback){

        callback(
            JSON.parse($.ajax({type:'GET', url:'blueprint/' + authname + "/" + bpname, async:false}).responseText)
        );
    }
}	

})();