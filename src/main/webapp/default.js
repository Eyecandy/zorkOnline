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
            myString +="<br>" + "hola" +x + "<br>" + data

            console.log("SUCCESS")
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
            myString +="<br>" + "> "+dir + "<br>" + data
            console.log("SUCCESS")
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
        url:  '/toMe',
        type: 'POST',
        data: {
            'input' : 'x'
        },

        success: function(data){
            myString += data
            console.log("SUCCESS")
            console.log(data)
            document.getElementById("scrll").innerHTML = myString
        },
        error: function(){
            alert("Error");
        }
    })


    //Use the onload event so that we can make sure the DOM is at
    //least mostly loaded before trying to get elements
    window.onload = function() {
        //Get the DOM element that represents the <button> element.
        //And set the onclick event
        document.getElementById("LearnMoreBtn").onclick = function(){
            //Set a variable to contain the DOM element of the overly
            var overlay = document.getElementById("overlay");
            //Set a variable to contain the DOM element of the popup
            var popup = document.getElementById("popup");

            //Changing the display css style from none to block will make it visible
            overlay.style.display = "block";
            //Same goes for the popup
            popup.style.display = "block";
        };
    };

}