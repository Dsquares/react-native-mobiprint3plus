// Mobiprint3plusModule.java

package com.mm.treka.mobiprint3plus;

import android.content.Context;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.mobiwire.CSAndroidGoLib.AndroidGoCSApi;
import com.mobiwire.CSAndroidGoLib.CsPrinter;

public class Mobiprint3plusModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private CsPrinter printer;

    public Mobiprint3plusModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Mobiprint3plus";
    }

    @ReactMethod
    public void addTextToPrint(String text, String textTwo, int textSize, boolean isBold, boolean isUnderline, int align) {
        printer.addTextToPrint(text,textTwo,textSize,isBold,isUnderline,align);
    }

    @ReactMethod
    public void printLine() {
        printer.addTextToPrint("===========================", null, 25, true, false, 1);
    }

    @ReactMethod
    public void connectPOS() {
        Context context = this.reactContext.getCurrentActivity();
        this.printer = new CsPrinter();
        try {
            new AndroidGoCSApi(context);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @ReactMethod
    public void print() {
        Context context = this.reactContext.getCurrentActivity();
        printer.print(context);
    }

}
