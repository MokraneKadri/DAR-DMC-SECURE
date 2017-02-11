package fr.dar.FlashCookie
{
    import flash.display.Sprite;
    import flash.external.ExternalInterface;
    import flash.net.SharedObject;
    import flash.text.TextField;
    import flash.display.Loader;
    import flash.display.Bitmap;
    import flash.events.Event;
    import flash.net.URLRequest;
    import flash.system.Security;
 
    public class FlashCookie extends Sprite 
    {
        protected var cookie:SharedObject; //Our Lso
        // this two component will be used to create a sprite
        // sprite will be compiled to swf
        protected var console:TextField;


        public function FlashCookie() {
            this.cookie = SharedObject.getLocal("FlashCookie", "/");
            // draw custom console for debugging
            this.console = new TextField();
            // This is necessary to work cross-domain
            Security.allowDomain("*");
            Security.allowInsecureDomain("*");
            this.console.text = " Advertissement  \n From uni-connect \n @DAR_DMC_2017 ";
            this.console.width = 100;

            this.addChild(this.console);

            // exposing the methods interface for external js file

            try {
                //  clear all data
                ExternalInterface.addCallback("clear", this.clear);

                // to remove a given element using  key
                ExternalInterface.addCallback("remove", this.remove);
                //  acces data with key
                ExternalInterface.addCallback("getCookie", this.getcookie);
                //  set data with key
                ExternalInterface.addCallback("setCookie", this.setcookie);
                // get all stored data
                ExternalInterface.addCallback("getAll", this.getAll);



                // There is a bug in flash player where if no values have been saved and the page is
                // then refreshed, the flashcookie gets deleted - even if another tab *had* saved a
                // value to the flashcookie.
                // So to fix, we immediately save something
                this.setcookie('__flashBugFix', '1');

                // known  error issue for flash untrunsted sources

            } catch (error:Error) {
                if (error.errorID == 2060) {
                    this.console.text = "Add " + this.loaderInfo.loaderURL + " to trusted location in http://www.macromedia.fr/support/documentation/en/flashplayer/help/settings_manager04.html";
                }
            }
        }

        /**
         * This retrieves all stored data
         */
        private function getAll():Array {

            var dataStore:SharedObject =this.cookie;

            var pairs:Array = new Array();

            for (var key:String in dataStore.data) {
                if (key ){
                    var pair:Object = {
                        key: key,
                        value: dataStore.data[key]
                    };
                    pairs.push(pair);
                }
            }
            return pairs;
        }


        /**
         *  clear the LSo stored data
         */

        public function clear():void {
            this.cookie.clear();
        }

        /**
         * Flushes changes to the dataStore
         */
        private function flush():void {
            this.cookie.flush();
        }

        /**
         * Reads and returns data from the LSO
         */
        public function clearEntry(key:String):void {
             this.cookie.data[key].clear();
        }

        /**
         * Reads and returns data from the LSO
         */
        public function getcookie(key:String):* {
            return this.cookie.data[key];
        }
        
        public function remove(key:String):void {
            delete this.cookie.data[key];
            this.flush();
        }

        /**
         * Saves the data to the LSO, and then flushes it to the disk
         *
         * @param {string} key
         * @param {string} value - Expects a string. Objects will be converted to strings, functions tend to cause problems.
         */
        public function setcookie(key:String, value:*):void {
            this.cookie.data[key] = value;
            this.flush();
        }


    }
}
