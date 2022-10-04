//api = apiclient;
api = apimock;


var App = (function(){
    


    var getBlueprints = function(){
        var autname = $("#autname").val();
        if (autname == ""){
            alert("cagastes");
        }        
        else{
            apimock.getBlueprintsByAuthor(autname,crearData);
        }
    };

    var crearData = function(autdata){
        //console.log(JSON.stringify(autdata) + " " + typeof autdata + "algo")
        var bps = autdata;
        var newPbs = bps.map(function(bp){
            return {
                name : bp.name,
                points : bp.points.length
            }
        });
        var totalPuntos = newPbs.reduce((curr, {points}) => curr+points, 0);
        $('#total-points').text("Total user points: " + totalPuntos);
        var bodyTable = $('tbody');
        bodyTable.html('');
        newPbs.map(function(bp){
            var table = $('#table');
            table.append("<tr> \n <td class = \"bp-name\">"+ bp.name +"</td> \n <td class = \"bp-points\">"+ bp.points +"</td> \n <td><button onclick=App.getUniqueBlueprint(this)>Open</button></td> \n </tr>");
        });
    }

    var getUniqueBlueprint = function(bp){
        var row = bp.parentElement.parentElement;
        var autname = $("#autname").val();
        var bpName = row.firstElementChild.innerText;
        apimock.getBlueprintsByNameAndAuthor(autname, bpName ,paintPoints);
    }
    
    var paintPoints = function(bpData){
        var canva = document.querySelector('canvas');
        var points = bpData.points;
        var quanPoints = points.length
        var ctx = canva.getContext("2d");
        ctx.clearRect(0, 0, canva.width, canva.height);
        ctx.beginPath();
        for (var i = 0 ; i < quanPoints-1; i++){
            ctx = canva.getContext("2d");
            ctx.moveTo(points[i].x,points[i].y);
            ctx.lineTo(points[i+1].x,points[i+1].y);
            ctx.stroke();
        }
        
    }

    return{
        getUniqueBlueprint : getUniqueBlueprint,
        getBlueprints : getBlueprints
    }
})();