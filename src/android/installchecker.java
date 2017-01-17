package jaradacl.installchecker;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException; 
import android.content.Intent;





/**
 * This class echoes a string called from JavaScript.
 */
public class installchecker extends CordovaPlugin {
    Context context;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("check")) {
            Log.d("installchecker execute", "success");
            this.check(callbackContext, context);
            return true;
        } else if (action.equals("opensettings")) {
            Log.d("opensettings execute", "success");
            this.cordova.getActivity().startActivity(new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS));
            return true;
        }
        return false;
    }

    private void check(CallbackContext callbackContext, Context context) {
        Log.d("check run", "success");
        String msg = "Installcheck: success";
        this.context = context;
        try {
            Boolean isNonPlayAppAllowed = Settings.Secure.getInt(this.cordova.getActivity().getContentResolver(), Settings.Secure.INSTALL_NON_MARKET_APPS) == 1;
            String installAllowed = String.valueOf(isNonPlayAppAllowed);

            Log.d("Install of non play-store apps allowed: ", installAllowed);
            callbackContext.success(installAllowed);
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
            callbackContext.error("Couldn't find the right setting");
        }
    }
}
