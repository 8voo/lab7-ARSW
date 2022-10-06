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
    },

    createBP:function(autName, bpName){
        var bp = JSON.stringify({author:autName, "points":[{}], "name": bpName});
        return new Promise((resolve) => {
            resolve($.ajax({
                type: "POST",
                url: 'blueprint/',
                data: bp,
                contentType: "application/json"
            }))
        })
    },


    delete:function(autName, bpName){
        return new Promise((resolve) => {
            resolve($.ajax({
                type: "DELETE",
                url: 'blueprint/' + autName +"/" + bpName,
            }))
            
        })
    }


}	

})();