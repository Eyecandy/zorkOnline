function hello() {
    console.log("hello")
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
            myString +="<br>"  +"> "+x + "<br>" + data

            console.log("SUCCESS click me!")
            console.log(data)
            document.getElementById("scrll").innerHTML = myString
        },
        error: function(){
            alert("Error");
        }
    })
}

function moveDir(dir){
    $.ajax({
        url:  '/toMe',
        type: 'POST',
        data: {
            'input' : dir
        },
        success: function(data){
            myString +="<br>" + "> " +dir + "<br>" + data
            console.log("SUCCESS move dir")
            console.log(data)

            document.getElementById("scrll").innerHTML = myString
        },
        error: function(){
            alert("Error");
        }
    })
}



function getPlayerDirOnLoad() {

    $.ajax({
        url: '/toMe',
        type: 'POST',
        data: {
            'input': 'x'
        },

        success: function (data) {
            myString += data
            console.log("SUCCESS")
            console.log(data)
            document.getElementById("scrll").innerHTML = myString
        },
        error: function () {
            alert("Error");
        }
    })

}