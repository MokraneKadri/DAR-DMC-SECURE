/**
 * in charge of creating dom node
 * positioning ans some style settings
 *  broadcasting event once  ready
 */
;(function (window) {

    'use strict';
    var movieName = 'FlashCookie';
    // the flash object
    var movieURL = '//elysane.net:8080/DAR2/cookies/dist/FlashCookie1.swf';
    //event to be fired once ready
    var ready = new CustomEvent('ready', {});

    // creating some dom elements
    // specially the embeded element that contains our flash 
    var object = document.createElement('object');
    var param1 = document.createElement('param');
    var param2 = document.createElement('param');
    var embed = document.createElement('embed');
    object.id = movieName;

    
    object.width = '100';
    object.height = '100';

    // change those if you want the flash object to be visble
    object.style.top = '-9999px';
    object.style.left = '-9999px';
    object.style.backgroundColor='red';
    param1.name = movieName;
    param1.value = movieURL;
    object.appendChild(param1);

   // necessary to allow cross domain use of the swf file
    param2.name = 'allowScriptAccess';
    param2.value = 'sameDomain';


    object.appendChild(param2);
    embed.name = movieName;
    embed.src = movieURL;
    embed.width = '400';
    embed.height = '400';
    embed.allowscriptaccess = '*';
    object.appendChild(embed);

    document.body.appendChild(object);



    window.FlashCookie = {};
    window.FlashCookie.onReady = function (callback) {
        document.body.addEventListener('ready', function () {
            callback.call(this, document[movieName]);
        });
    };


    window.FlashCookie.showConsole = function () {
        var object = document.getElementById(movieName);
        object.style.top = 0;
        object.style.left = 0;
    };

    //sends the ready event after a given time
    document.body.onload = function () {
        setTimeout(function () {
            document.body.dispatchEvent(ready);
        }, 2000);
    };
})(this);