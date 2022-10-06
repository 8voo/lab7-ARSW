


var App = (function(){
    var actAuthor;
    var actBPName;
    var actBP;
    var actRow;
    this.puntosGlobales;


    var getBlueprints = function(){
        var autname = $("#autname").val();
        actAuthor = autname;
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
        this.puntosGlobales = totalPuntos;
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
        actRow = row;
        var autname = $("#autname").val();
        var bpName = row.firstElementChild.innerText;
        actAuthor = autname;
        actBPName = bpName;
        $('#blueprint-name').text("Blueprint name: " + bpName);
        apimock.getBlueprintsByNameAndAuthor(autname, bpName ,paintPoints);
    }
    
    var paintPoints = function(bpData){
        actBP = bpData;
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
        newPoint(bpData);
    }

    var newPoint = function(bp){
        if(window.PointerEvent) {
            canva.addEventListener("pointerdown", function(event){
                let rect = canva.getBoundingClientRect();
                let x = event.clientX - rect.left;
                let y = event.clientY - rect.top;
                var coordenadas = {"x": x, "y": y}; 
                var points = bp.points;
                bp.points.push(coordenadas);
                ctx = canva.getContext("2d");
                ctx.moveTo(points[points.length-2].x,points[points.length-2].y);
                ctx.lineTo(points[points.length-1].x,points[points.length-1].y);
                ctx.stroke();
            });
        }
    }

    var saveBP = function(){
        apiclient.putBlueprintsByAuthor(actAuthor, actBPName, JSON.stringify(actBP));
        var pointsField = actRow.firstElementChild.nextElementSibling;
        var numPoints = pointsField.innerText;
        var newPoints = actBP.points.length - numPoints;
        pointsField.innerText = actBP.points.length;
        $('#total-points').text("Total user points: " + (puntosGlobales + newPoints));
        puntosGlobales = puntosGlobales + newPoints;
    }
        
    var addBP = function(){
        var name = prompt("Digite el nombre del nuevo plano", "");
        if(name != undefined && name != ""){
            console.log("entro");
            apiclient.createBP(actAuthor, name).then(() =>{
                getBlueprints();
            })
            .catch(error => console.log(error));
        }
        
    }

    var deleteBP = function(){
        apiclient.delete(actAuthor, actBPName).then((actBPName) =>{
            getBlueprints();
            var ctx = canva.getContext("2d");
            ctx.clearRect(0, 0, canva.width, canva.height);
            ctx.beginPath();
            }
        ).catch(err => console.log(err))
    }
    

    return{
        getUniqueBlueprint : getUniqueBlueprint,
        getBlueprints : getBlueprints,
        saveBP : saveBP,
        addBP:addBP,
        deleteBP:deleteBP
    }
})();