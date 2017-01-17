module.exports = {
    installCheck: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "installchecker", "check", ['']);
    },
    openSettings: function(successCallback, errorCallback) {
    	cordova.exec(successCallback, errorCallback, "installchecker", "opensettings", [''])
    }
};