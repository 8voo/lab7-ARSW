var apiclient = ( function(){
return {

    getBlueprintsByAuthor:function(authname,callback){
        callback(
            JSON.parse($.ajax({type:'GET', url:'blueprint/' + authname, async:false}).responseText)
        );
    },

    putBlueprintsByAuthor:function(authname,bpname,newData){
            $.ajax({type:'PUT', 
            url:'blueprint/'+ authname + "/" + bpname, 
            data: newData,
            contentType: "application/json"})
    },

    getBlueprintsByNameAndAuthor:function(authname,bpname,callback){
        callback(
            JSON.parse($.ajax({type:'GET', url:'blueprint/' + authname + "/" + bpname, async:false}).responseText)
        );
    }
}	

})();