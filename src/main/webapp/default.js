function hello() {
    console.log("hello there noob")
}

function alertThis() {
    alert("Alert Me!X")
}
var myString = ""

function clickMe(){
    var x =  $("#txt").val()
    console.log("Print: ", x)

    $.ajax({
        url:  '/toMe',
        type: 'POST',
        data: {
            'input' : x
        },
        success: function(data){
            myString +="<br> " + x + "<br>" + data
            console.log("SUCCESS")
            console.log(data)
            document.getElementById("scrll").innerHTML = myString
        },
        error: function(){
            alert("Error");
        }
    })
}